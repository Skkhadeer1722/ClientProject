package scrips.datamigration.sss.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.common.UserData;
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.regex.RegexValidation;
import scrips.datamigration.sss.model.JpaSssAllotment;
import scrips.datamigration.sss.model.JpaSssAllotmentSource;
import scrips.datamigration.sss.model.JpaSssAllotmentTemp;
import scrips.datamigration.sss.model.JpaSssSecuritiesCode;
import scrips.datamigration.sss.repository.SssAllotmentDAO;
import scrips.datamigration.sss.repository.SssAllotmentSourceDAO;
import scrips.datamigration.sss.repository.SssAllotmentTempDAO;
import scrips.datamigration.sss.repository.SssSecuritiesCodeDAO;

@Service

public class SssAllotmentService {
	private final Logger logger = LogManager.getLogger(SssAllotmentService.class);

	@Autowired
	SssAllotmentDAO sssAllotmentDAO;

	@Autowired
	private UserData userData;

	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	SssAllotmentTempDAO sssAllotmentTempDAO;

	@Autowired
	private Environment env;

	@Autowired
	SssAllotmentSourceDAO allotmentSourceDAO;

	@Autowired
	SssSecuritiesCodeDAO securitiesCodeDAO;

	public String migrateSssAllotment(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) {
		List<JpaSssAllotmentSource> sssAllotmentSourceList = createAndSaveAllotmentSourceData(fileRecords,
				draftDBDetails);
		List<JpaSssAllotmentTemp> sssAllotmentTempList = convertAllotmentSourceToAllotmentTemp(sssAllotmentSourceList);
		boolean noErrors = saveAllotmentLiveTableData(sssAllotmentTempList);
		return (sssAllotmentSourceList.size() == sssAllotmentTempList.size() && noErrors) ? "noErrors" : "hasErrors";
	}

	private List<JpaSssAllotmentSource> createAndSaveAllotmentSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaSssAllotmentSource> sssAllotmentSourceList = new ArrayList<JpaSssAllotmentSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 6, draftDBDetails.size(), delimiter);

				if (error != null) {
					JpaSssAllotmentSource allotmentSource = new JpaSssAllotmentSource();
					allotmentSource.setRemarks(error);
					allotmentSourceDAO.save(allotmentSource);
					continue;
				}

				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line, delimiter);
				Date date = new Date();
				JpaSssAllotmentSource jpaSssAllotmentSource = JpaSssAllotmentSource.builder().id(id)
						.securitiesCode(dbRecordsMap.get("securities_code"))
						.auctionDate(dbRecordsMap.get("auction_date")).issuanceDate(dbRecordsMap.get("issuance_date"))
						.allotmentPrice(dbRecordsMap.get("allotment_price")).totalNominalAmountAlloted(null)
						.totalNominalAmountToBeAlloted(dbRecordsMap.get("total_nominal_amount_to_be_alloted"))
						.firstAllotment(dbRecordsMap.get("first_allotment"))
						.totalSettlementAmount(dbRecordsMap.get("total_settlement_amount"))
//						.processed(dbRecordsMap.get("processed"))
						.processed(null).action(null).status(null).modifiedBy(null).modifiedDate(null).approvedBy(null)
						.approvedDate(null).effectiveDate(null).createdDate(null).approvalRemark(null)
						.workflowStatusId(null).plannedTotalNominalAmountAllotted(null)
						.migratedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)).build();
				String remarks = validationJpaSssAllotmentSource(jpaSssAllotmentSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				jpaSssAllotmentSource.setRemarks(remarks);
				sssAllotmentSourceList.add(jpaSssAllotmentSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return sssAllotmentSourceList;
	}

	private String validationJpaSssAllotmentSource(JpaSssAllotmentSource sssAllotmentSource) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(
				regexValidation.regexValidator(sssAllotmentSource.getSecuritiesCode(), "special", "Securities Code"));
		remarks.append(
				regexValidation.regexValidator(sssAllotmentSource.getSecuritiesCode(), "length12", "Securities Code"));
		remarks.append(
				regexValidation.regexValidator(sssAllotmentSource.getAllotmentPrice(), "decimal", "Allotment Price"));
		remarks.append(
				regexValidation.regexValidator(sssAllotmentSource.getAllotmentPrice(), "decimal_8", "Allotment Price"));
		remarks.append(
				regexValidation.regexValidator(sssAllotmentSource.getFirstAllotment(), "numeric", "First Allotment"));
		remarks.append(regexValidation.regexValidator(sssAllotmentSource.getTotalSettlementAmount(), "decimal",
				"Total Settlement Amount"));
		remarks.append(regexValidation.regexValidator(sssAllotmentSource.getTotalSettlementAmount(), "decimal_18",
				"Total Settlement Amount"));
		if (!CommonUtils.validateParseDate_yyyy_MM_dd(sssAllotmentSource.getIssuanceDate()))
			remarks.append(",Invalid Issuance Date");
		if (sssAllotmentSource.getAuctionDate() != null && !sssAllotmentSource.getAuctionDate().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(sssAllotmentSource.getAuctionDate(), "numeric", "Auction Date"));
		}
		if (sssAllotmentSource.getTotalNominalAmountToBeAlloted() != null
				&& !sssAllotmentSource.getTotalNominalAmountToBeAlloted().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssAllotmentSource.getTotalNominalAmountToBeAlloted(),
					"numeric", "Total Nominal Amount To Be Alloted"));
		}
		return remarks.toString().trim();
	}

	private List<JpaSssAllotmentTemp> convertAllotmentSourceToAllotmentTemp(
			List<JpaSssAllotmentSource> sssAllotmentSourceList) {
		List<JpaSssAllotmentTemp> allotmentTempList = new ArrayList<JpaSssAllotmentTemp>();
		String id = userData.getSystemUserId();
		for (JpaSssAllotmentSource sourceRec : sssAllotmentSourceList) {
			allotmentSourceDAO.save(sourceRec);
			try {
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {
					JpaSssSecuritiesCode securitiesCode = securitiesCodeDAO
							.findBySecuritiesCode(sourceRec.getSecuritiesCode());
					List<String> validationRemarks = new ArrayList<String>();
					if (securitiesCode == null)
						validationRemarks.add("securities_code is not found in Securities Code Table");

					String processed = "Yes";
					if (processed.equalsIgnoreCase("Yes"))
						processed = "1";
					else if (processed.equalsIgnoreCase("No"))
						processed = "0";

					Date date = null, date1 = null;
					SimpleDateFormat sdf, sdf1;
					date = new Date();
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					sdf1 = new SimpleDateFormat("yyyyMMdd");
					String issuanceDate = sourceRec.getIssuanceDate();
					date1 = sdf.parse(issuanceDate);
					String issuance = sdf1.format(date1);

					JpaSssAllotmentTemp jpaSssAllotmentTemp = JpaSssAllotmentTemp.builder().id(sourceRec.getId())
							.securitiesCode(sourceRec.getSecuritiesCode())
							.auctionDate(!CommonUtils.isNullOrEmpty(sourceRec.getAuctionDate()) ? null
									: sdf1.parse(sourceRec.getAuctionDate()))
							.issuanceDate(Integer.parseInt(issuance))
							.allotmentPrice(Double.parseDouble(sourceRec.getAllotmentPrice()))
							.totalNominalAmountAlloted(0L)
							.totalNominalAmountToBeAlloted(
									!CommonUtils.isNullOrEmpty(sourceRec.getTotalNominalAmountToBeAlloted()) ? null
											: Long.parseLong(sourceRec.getTotalNominalAmountToBeAlloted()))
							.firstAllotment(Short.parseShort(sourceRec.getFirstAllotment()))
							.totalSettlementAmount(new BigDecimal(sourceRec.getTotalSettlementAmount()))
							.processed(Short.parseShort(processed)).action(" ").status("ACTIVE").modifiedBy(id)
							.modifiedDate(date).approvedBy(id).approvedDate(date).effectiveDate(date).createdDate(date)
							.approvalRemark("Data Migration").workflowStatusId(null)
							.plannedTotalNominalAmountAllotted(null).build();
					if (validationRemarks.size() > 0) {
						jpaSssAllotmentTemp.setRemarks(String.join(", ", validationRemarks));
					}
					sssAllotmentTempDAO.save(jpaSssAllotmentTemp);
					allotmentTempList.add(jpaSssAllotmentTemp);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving allotment_temp table data");
				allotmentSourceDAO.save(sourceRec);
			}
		}
		return allotmentTempList;
	}

	private boolean saveAllotmentLiveTableData(List<JpaSssAllotmentTemp> sssAllotmentTempList) {
		boolean noErrors = true;
		for (JpaSssAllotmentTemp allotmentTemp : sssAllotmentTempList) {
			if (allotmentTemp.getRemarks() == null || allotmentTemp.getRemarks().isEmpty()
					|| allotmentTemp.getRemarks().isBlank()) {
				try {
					JpaSssAllotment allotment = convertToJpaSssAllotment(allotmentTemp);
					String remarks = validationJpaSssAllotmentLiveData(allotment);
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						allotmentTemp.setRemarks(String.join(", ", remarks));
						sssAllotmentTempDAO.save(allotmentTemp);
					} else
						sssAllotmentDAO.save(allotment);
				} catch (Exception e) {
					allotmentTemp.setRemarks("error while saving allotment live table data");
					e.printStackTrace();
					sssAllotmentTempDAO.save(allotmentTemp);
					noErrors = false;
				}
			}
		}
		return noErrors;
	}

	private JpaSssAllotment convertToJpaSssAllotment(JpaSssAllotmentTemp sssAllotmentListTemp) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaSssAllotment sssAllotment = mapper.map(sssAllotmentListTemp, JpaSssAllotment.class);
		return sssAllotment;
	}

	private String validationJpaSssAllotmentLiveData(JpaSssAllotment sssAllotment) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(sssAllotment.getSecuritiesCode(), "special", "Securities Code"));
		remarks.append(regexValidation.regexValidator(sssAllotment.getSecuritiesCode(), "length12", "Securities Code"));
		remarks.append(regexValidation.regexValidator(String.valueOf(sssAllotment.getAllotmentPrice()), "decimal",
				"Allotment Price"));
		remarks.append(regexValidation.regexValidator(String.valueOf(sssAllotment.getAllotmentPrice()), "decimal_8",
				"Allotment Price"));

		remarks.append(regexValidation.regexValidator(String.valueOf(sssAllotment.getFirstAllotment()), "numeric",
				"First Allotment"));
		remarks.append(regexValidation.regexValidator(String.valueOf(sssAllotment.getTotalSettlementAmount()),
				"decimal", "Total Settlement Amount"));
		remarks.append(regexValidation.regexValidator(String.valueOf(sssAllotment.getTotalSettlementAmount()),
				"decimal_18", "Total Settlement Amount"));
		if (sssAllotment.getTotalNominalAmountToBeAlloted() != null) {
			remarks.append(
					regexValidation.regexValidator(String.valueOf(sssAllotment.getTotalNominalAmountToBeAlloted()),
							"numeric", "Total Nominal Amount To Be Alloted"));
		}
		if (sssAllotment.getModifiedBy() != null && !sssAllotment.getModifiedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssAllotment.getModifiedBy(), "length36", "Modified By"));
		}
		if (sssAllotment.getApprovedBy() != null && !sssAllotment.getApprovedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssAllotment.getApprovedBy(), "length36", "Approved By"));
		}
		return remarks.toString().trim();
	}

}