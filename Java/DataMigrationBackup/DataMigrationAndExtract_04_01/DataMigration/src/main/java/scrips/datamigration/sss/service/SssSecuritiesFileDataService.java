package scrips.datamigration.sss.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.common.service.SftpService;
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.sss.model.JpaSssSecuritiesFileSource;

@Service
public class SssSecuritiesFileDataService {

	private final Logger logger = LogManager.getLogger(SssSecuritiesFileDataService.class);

	@Autowired
	private Environment env;
	@Autowired
	SftpService sftpService;

	public List<String> loadSecuritiesDataFile(String fileUploadCode, JpaFileUploadHeader fileHeaderObj) {

		List<String> fileRecords = new ArrayList<String>();
		logger.info("ftp status {}", env.getProperty("FTP.ENABLED"));
		String ftpStatus = env.getProperty("FTP.ENABLED");
		List<JpaFileUploadDetails> draftDBDetails = getFileDetailsByCode();
		if (ftpStatus != null && ftpStatus.trim().equalsIgnoreCase("y")) {
			// Read file from FTP
			logger.info("FTP Enabled");
			try {
				fileRecords.clear();
				fileRecords = sftpService.readFileFromSFTPDirectroy(fileHeaderObj.getFilePath(),
						fileHeaderObj.getFileName());
			} catch (Exception e) {
				logger.error("error while reading file from SFTP Directory {}", e.getMessage());
				// throw e;
			}
		} else {
			try {
				InputStream fileStream = new BufferedInputStream(new FileInputStream(env.getProperty("remotefolderpath")
						+ "/" + "file/MigrationFiles/" + fileHeaderObj.getFileName()));
				Scanner sc = new Scanner(fileStream).useDelimiter("\\A");
				while (sc.hasNextLine()) {
					fileRecords.add(sc.nextLine());
				}
				sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (fileRecords != null && fileRecords.size() > 0) {
				fileRecords.stream().forEach(a -> logger.info("fileSourceData - " + a));
				draftDBDetails.stream()
						.forEach(a -> logger.info("Headings - " + a.getSequenceNo() + ":" + a.getTableFieldName()));
			}
		}
		List<JpaSssSecuritiesFileSource> list = createAndSaveSourceData(fileRecords, draftDBDetails);

		List<String> dataList = new ArrayList<String>();
		String delimiter = env.getProperty("FILE_DELIMITER");
		if (fileUploadCode.equalsIgnoreCase("SSS_SECURITIES_CODE_FILE_UPLOAD")) {

			for (JpaSssSecuritiesFileSource fileDataObj : list) {
				StringBuffer str = new StringBuffer("");
				str.append(fileDataObj.getIsInCd()).append(delimiter).append(fileDataObj.getIssueCode())
						.append(delimiter).append(fileDataObj.getDescription()).append(delimiter)
						.append(fileDataObj.getIsinStatus()).append(delimiter).append(fileDataObj.getCurrencyCode())
						.append(delimiter).append(fileDataObj.getDenomination()).append(delimiter)
						.append(fileDataObj.getAuctionDate()).append(delimiter)
						.append(fileDataObj.getFirstIssuanceDate()).append(delimiter)
						.append(fileDataObj.getFirstIssuanceAmount()).append(delimiter)
						.append(fileDataObj.getMaturityDate()).append(delimiter)
						.append(fileDataObj.getOptionalMaturityDate()).append(delimiter)
						.append(fileDataObj.getRedemptionPrice()).append(delimiter)
						.append(fileDataObj.getAverageYieldPrice()).append(delimiter)
						.append(fileDataObj.getTotalIssuedAmount()).append(delimiter)
						.append(fileDataObj.getCouponPaymentFrequency()).append(delimiter)
						.append(fileDataObj.getCouponRate()).append(delimiter).append(fileDataObj.getAverageYield())
						.append(delimiter).append(fileDataObj.getExDatePeriod()).append(delimiter)
						.append(fileDataObj.getTenure()).append(delimiter).append(fileDataObj.getCutoffYieldPrice())
						.append(delimiter).append(fileDataObj.getCutoffYield()).append(delimiter);
				if (fileDataObj.getLastCouponDate() == null || fileDataObj.getLastCouponDate().isEmpty())
					str.append(" ");
				else
					str.append(fileDataObj.getLastCouponDate());
				dataList.add(str.toString());
			}
		} else if (fileUploadCode.equalsIgnoreCase("SSS_SECURITIES_CODE_STATISTICS_FILE_UPLOAD")) {
			for (JpaSssSecuritiesFileSource fileDataObj : list) {
				StringBuffer str = new StringBuffer("");
				str.append(fileDataObj.getIsInCd()).append(delimiter).append(fileDataObj.getTotalRedeemedAmount())
						.append(delimiter)
//				.append(fileDataObj.getTotalRedeemedAmountForErf()).append(delimiter)
						.append(fileDataObj.getOutstandingNominalAmount()).append(delimiter)
						.append(fileDataObj.getOutstandingNominalAmount()).append(delimiter)
						.append(fileDataObj.getNextCouponDate()).append(delimiter)
						.append(fileDataObj.getLastCouponDate()).append(delimiter)
//				.append(fileDataObj.getNextIndexEndDate()).append(delimiter)
						.append(fileDataObj.getNextCouponDate()).append(delimiter)
						.append(fileDataObj.getAmountAllocatedToMas()).append(delimiter)
						.append(fileDataObj.getAmountAllocatedToOthers()).append(delimiter)
//				.append(fileDataObj.getTotalNominalAmountIssuedForErf()).append(delimiter);
						.append(fileDataObj.getOutstandingNominalAmount());
				dataList.add(str.toString());
			}
		} else if (fileUploadCode.equalsIgnoreCase("SSS_SECURITIES_POSITION_STATS_FILE_UPLOAD")) {
			for (JpaSssSecuritiesFileSource fileDataObj : list) {
				StringBuffer str = new StringBuffer("");
				str.append(fileDataObj.getIsInCd()).append(delimiter).append(fileDataObj.getOutstandingNominalAmount())
						.append(delimiter).append(fileDataObj.getOutstandingNominalAmount()).append(delimiter)
						.append("ALLOTEST1");// account_id

				dataList.add(str.toString());
			}
		} else if (fileUploadCode.equalsIgnoreCase("SSS_SECURITIES_PRICE_FILE_UPLOAD")) {
			for (JpaSssSecuritiesFileSource fileDataObj : list) {
				StringBuffer str = new StringBuffer("");
				str.append(fileDataObj.getIsInCd()).append(delimiter).append(fileDataObj.getLastCouponDate())
						.append(delimiter) // pricing
						.append(fileDataObj.getRedemptionPrice()).append(delimiter) // ilf price
						.append(fileDataObj.getIssueCode());
				dataList.add(str.toString());
			}
		}
		return dataList;
	}

	public List<JpaFileUploadDetails> getFileDetailsByCode() {
		List<JpaFileUploadDetails> fileUpldDetailsOut = new ArrayList<JpaFileUploadDetails>();
		String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));

		String[] liveTableFieldNames = env.getProperty("SSS_SECURITIES_FILE_DATA_FILE_UPLOAD.TABLE_FIELD_NAMES")
				.split(delimiter);
		int seqNo = 1;
		for (String fieldname : liveTableFieldNames) {
			System.out.println(fieldname);
			JpaFileUploadDetails fileUpldDetObj = new JpaFileUploadDetails();
			fileUpldDetObj.setSequenceNo(seqNo++);
			fileUpldDetObj.setTableFieldName(fieldname);
			fileUpldDetailsOut.add(fileUpldDetObj);
		}

		System.out.println("fileUploadDetails - " + fileUpldDetailsOut);
		return fileUpldDetailsOut;
	}

	public List<JpaSssSecuritiesFileSource> createAndSaveSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaSssSecuritiesFileSource> sourceList = new ArrayList<JpaSssSecuritiesFileSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				if (lineCount == 1)
					continue;
				String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));

				String error = CommonUtils.validateRawData(line, 43, draftDBDetails.size(), delimiter);
				if (error != null) {
					System.out.println(error);
					continue;
				}
				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line, delimiter);

				JpaSssSecuritiesFileSource sourceObj = JpaSssSecuritiesFileSource.builder()
						.IsInCd(dbRecordsMap.get("ISIN_CODE")).description(dbRecordsMap.get("DESCRIPTION"))
						.isinType(dbRecordsMap.get("ISIN_TYPE")).issueCode(dbRecordsMap.get("ISSUE_CODE"))
						.isinStatus(dbRecordsMap.get("ISIN_STATUS")).taxable(dbRecordsMap.get("TAXABLE"))
						.tradable(dbRecordsMap.get("TRADABLE")).creditRating(dbRecordsMap.get("CREDIT_RATING"))
						.eligibleForIlf(dbRecordsMap.get("ELIGIBLE_FOR_ILF"))
						.ilfHaircutSetting(dbRecordsMap.get("ILF_HAIRCUT_SETTING"))
						.haircutPercentageForIlf(dbRecordsMap.get("HAIRCUT_PERCENTAGE_FOR_ILF"))
						.maturityDate(dbRecordsMap.get("MATURITY_DATE"))
						.optionalMaturityDate(dbRecordsMap.get("OPTIONAL_MATURITY_DATE"))
						.auctionDate(dbRecordsMap.get("AUCTION_DATE"))
						.firstIssuanceDate(dbRecordsMap.get("FIRST_ISSUANCE_DATE"))
						.firstIssuanceAmount(dbRecordsMap.get("FIRST_ISSUANCE_AMOUNT"))
						.reOpeningAuctionDate(dbRecordsMap.get("RE_OPENING_AUCTION_DATE"))
						.reOpeningAllotmentDate(dbRecordsMap.get("RE_OPENING_ALLOTMENT_DATE"))
						.reOpeningTotalAllotmentAmount(dbRecordsMap.get("RE_OPENING_TOTAL_ALLOTMENT_AMOUNT"))
						.totalIssuedAmount(dbRecordsMap.get("TOTAL_ISSUED_AMOUNT"))
						.outstandingNominalAmount(dbRecordsMap.get("OUTSTANDING_NOMINAL_AMOUNT"))
						.amountAllocatedToMas(dbRecordsMap.get("AMOUNT_ALLOCATED_TO_MAS"))
						.amountAllocatedToOthers(dbRecordsMap.get("AMOUNT_ALLOCATED_TO_OTHERS"))
						.masAppliedAmount(dbRecordsMap.get("MAS_APPLIED_AMOUNT"))
						.unitSize(dbRecordsMap.get("UNIT_SIZE")).denomination(dbRecordsMap.get("DENOMINATION"))
						.currencyCode(dbRecordsMap.get("CURRENCY_CODE"))
						.redemptionPrice(dbRecordsMap.get("REDEMPTION_PRICE"))
						.couponRate(dbRecordsMap.get("COUPON_RATE")).exDatePeriod(dbRecordsMap.get("EX_DATE_PERIOD"))
						.exDate(dbRecordsMap.get("EX_DATE"))
						.couponPaymentFrequency(dbRecordsMap.get("COUPON_PAYMENT_FREQUENCY"))
						.lastCouponDate(dbRecordsMap.get("LAST_COUPON_DATE"))
						.nextCouponDate(dbRecordsMap.get("NEXT_COUPON_DATE"))
						.optionalCouponDate(dbRecordsMap.get("OPTIONAL_COUPON_DATE"))
						.daysOfAccruedInterest(dbRecordsMap.get("DAYS_OF_ACCRUED_INTEREST"))
						.tenure(dbRecordsMap.get("TENURE")).averageYield(dbRecordsMap.get("AVERAGE_YIELD"))
						.cutoffYield(dbRecordsMap.get("CUTOFF_YIELD"))
						.averageYieldPrice(dbRecordsMap.get("AVERAGE_YIELD_PRICE"))
						.cutoffYieldPrice(dbRecordsMap.get("CUTOFF_YIELD_PRICE"))
						.reOpeningMasAppliedAmount(dbRecordsMap.get("RE_OPENING_MAS_APPLIED_AMOUNT"))
						.totalRedeemedAmount(dbRecordsMap.get("TOTAL_REDEEMED_AMOUNT"))
						.isinSubType(dbRecordsMap.get("ISIN_SUB_TYPE")).build();

				sourceList.add(sourceObj);

			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return sourceList;

	}

}
