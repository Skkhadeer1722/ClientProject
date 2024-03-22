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
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.regex.RegexValidation;
import scrips.datamigration.sss.model.JpaSSSAccount;
import scrips.datamigration.sss.model.JpaSssSecuritiesCode;
import scrips.datamigration.sss.model.JpaSssSecuritiesPositionStats;
import scrips.datamigration.sss.model.JpaSssSecuritiesPositionStatsSource;
import scrips.datamigration.sss.model.JpaSssSecuritiesPositionStatsTemp;
import scrips.datamigration.sss.repository.JpaSssSecuritiesPositionStatsSourceDAO;
import scrips.datamigration.sss.repository.SSSAccountDAO;
import scrips.datamigration.sss.repository.SssSecuritesPositionStatsDAO;
import scrips.datamigration.sss.repository.SssSecuritiesCodeDAO;
import scrips.datamigration.sss.repository.SsscSecuritesPositionStatsTempDAO;

@Service
public class SssSecuritiesPositionStatsService {
	private final Logger logger = LogManager.getLogger(SssSecuritiesPositionStatsService.class);

	@Autowired
	private Environment env;

	@Autowired
	SssSecuritesPositionStatsDAO securitiesPositionStatsDAO;

	@Autowired
	SsscSecuritesPositionStatsTempDAO securitiesPositionStatsTempDAO;

	@Autowired
	SssSecuritiesCodeDAO sssSecuritiesCodeDAO;

	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	SSSAccountDAO accountDAO;

	@Autowired
	private JpaSssSecuritiesPositionStatsSourceDAO sssSecuritiesPositionStatsSourceDAO;

	public String migrateSssSecuritiesPositionStats(JpaFileUploadHeader fileHeaderObj,
			List<JpaFileUploadDetails> draftDBDetails, List<String> fileRecords) {
		List<JpaSssSecuritiesPositionStatsSource> securitiesPositionStatsSourceList = createAndSaveSecuritiesPositionStatsSourceData(
				fileRecords, draftDBDetails);
		List<JpaSssSecuritiesPositionStatsTemp> securitiesPositionStatsTempList = convertSecuritiesPositionStatsSourceToSecuritiesPositionStatsTemp(
				securitiesPositionStatsSourceList);
		boolean noErrors =saveSecuritiesPosItionStatsLiveTableData(securitiesPositionStatsTempList);
		return (securitiesPositionStatsSourceList.size()==securitiesPositionStatsTempList.size() && noErrors)? "noErrors" : "hasErrors";
	}

	private List<JpaSssSecuritiesPositionStatsSource> createAndSaveSecuritiesPositionStatsSourceData(
			List<String> fileRecords, List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaSssSecuritiesPositionStatsSource> securitiesPositionStatsSourceList = new ArrayList<JpaSssSecuritiesPositionStatsSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 3, draftDBDetails.size(), delimiter);
				if (error != null) {
					JpaSssSecuritiesPositionStatsSource securitiesPositionSource = new JpaSssSecuritiesPositionStatsSource();
					securitiesPositionSource.setRemarks(error);
					sssSecuritiesPositionStatsSourceDAO.save(securitiesPositionSource);
					continue;
				}
				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line, delimiter);

				JpaSssSecuritiesPositionStatsSource securitiesPositionStatsSource = JpaSssSecuritiesPositionStatsSource
						.builder().id(id).accountId(dbRecordsMap.get("account_id"))
						.securitiesCode(dbRecordsMap.get("securities_code")).valueDate(null)
						.openingNominalAmount(dbRecordsMap.get("opening_nominal_amount"))
						.closingNominalAmount(dbRecordsMap.get("closing_nominal_amount")).settledCount(null)
						.settledAmount(null).receiptCount(null).receiptAmount(null).queuedCount(null).queuedAmount(null)
						.rejectedCount(null).rejectedAmount(null).cancelledCount(null).cancelledAmount(null).build();

				String remarks = validationSecuritiesPositionStatsSource(securitiesPositionStatsSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				securitiesPositionStatsSource.setRemarks(remarks);
				securitiesPositionStatsSourceList.add(securitiesPositionStatsSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return securitiesPositionStatsSourceList;
	}

	private String validationSecuritiesPositionStatsSource(
			JpaSssSecuritiesPositionStatsSource securitiesPositionStatsSource) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(securitiesPositionStatsSource.getSecuritiesCode(), "special",
				"Securities Code"));
		remarks.append(regexValidation.regexValidator(securitiesPositionStatsSource.getSecuritiesCode(), "length12",
				"Securities Code"));
		remarks.append(regexValidation.regexValidator(securitiesPositionStatsSource.getOpeningNominalAmount(),
				"decimal", "Opening Nominal Account"));
		remarks.append(regexValidation.regexValidator(securitiesPositionStatsSource.getOpeningNominalAmount(),
				"length23", "Opening Nominal Account"));
		if (securitiesPositionStatsSource.getClosingNominalAmount() != null
				&& !securitiesPositionStatsSource.getClosingNominalAmount().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(securitiesPositionStatsSource.getClosingNominalAmount(),
					"decimal", "Closing Nominal Account"));
			remarks.append(regexValidation.regexValidator(securitiesPositionStatsSource.getClosingNominalAmount(),
					"length23", "Closing Nominal Account"));
		}
		remarks.append(
				regexValidation.regexValidator(securitiesPositionStatsSource.getAccountId(), "Id", "Account Id"));
		remarks.append(
				regexValidation.regexValidator(securitiesPositionStatsSource.getAccountId(), "length36", "Account Id"));
		return remarks.toString().trim();
	}

	private List<JpaSssSecuritiesPositionStatsTemp> convertSecuritiesPositionStatsSourceToSecuritiesPositionStatsTemp(
			List<JpaSssSecuritiesPositionStatsSource> securitiesPositionStatsSourceList) {
		List<JpaSssSecuritiesPositionStatsTemp> securitiesPositionStatsTempList = new ArrayList<JpaSssSecuritiesPositionStatsTemp>();

		for (JpaSssSecuritiesPositionStatsSource sourceRec : securitiesPositionStatsSourceList) {
			sssSecuritiesPositionStatsSourceDAO.save(sourceRec);
			try {
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {
					Date d = new Date();
					String custodyAccountId = null;
					String date = new SimpleDateFormat("yyyyMMdd").format(d);
					List<String> validationRemarks = new ArrayList<String>();
					JpaSssSecuritiesCode securitiesCode = sssSecuritiesCodeDAO
							.findBySecuritiesCode(sourceRec.getSecuritiesCode());

					List<JpaSSSAccount> account_id = accountDAO.findByAccountId(sourceRec.getAccountId());
					if (account_id != null && !account_id.isEmpty()) {
						custodyAccountId = account_id.get(0).getCustodyAccountTypeId();
					}
					if (custodyAccountId == null || custodyAccountId.trim().isEmpty())
						validationRemarks.add("account_id is Not Present in SSS Account Table");

					if (securitiesCode == null)
						validationRemarks.add("securities_code is not found in Securities Code Table");

					BigDecimal openingNominalAmount = null;
					if (sourceRec.getOpeningNominalAmount() != null) {
						openingNominalAmount = CommonUtils.convertStringToDecimal(sourceRec.getOpeningNominalAmount(),
								23, 5);
						if (openingNominalAmount == null) {
							validationRemarks.add("Invalid Opening Nominal Amount");
						}
					}
					BigDecimal closingNominalAmount = null;
					if (sourceRec.getClosingNominalAmount() != null) {
						closingNominalAmount = CommonUtils.convertStringToDecimal(sourceRec.getClosingNominalAmount(),
								23, 5);
						if (closingNominalAmount == null) {
							validationRemarks.add("Invalid Closing Nominal Amount");
						}
					}

					JpaSssSecuritiesPositionStatsTemp securitiesPositionStatsTemp = JpaSssSecuritiesPositionStatsTemp
							.builder().id(sourceRec.getId()).accountId(custodyAccountId)
							.securitiesCode(sourceRec.getSecuritiesCode()).valueDate(Integer.parseInt(date))
							.openingNominalAmount(openingNominalAmount).closingNominalAmount(closingNominalAmount)
							.settledCount(0).settledAmount(0.0).receiptCount(0).receiptAmount(0.0).queuedCount(0)
							.queuedAmount(0.0).rejectedCount(0).rejectedAmount(0.0).cancelledCount(0)
							.cancelledAmount(0.0).build();
					if (validationRemarks.size() > 0) {
						securitiesPositionStatsTemp.setRemarks(String.join(", ", validationRemarks));
					}
					securitiesPositionStatsTempDAO.save(securitiesPositionStatsTemp);
					securitiesPositionStatsTempList.add(securitiesPositionStatsTemp);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving securities position stats temp table data");
				sssSecuritiesPositionStatsSourceDAO.save(sourceRec);
			}
		}
		return securitiesPositionStatsTempList;
	}

	private boolean saveSecuritiesPosItionStatsLiveTableData(
			List<JpaSssSecuritiesPositionStatsTemp> securitiesPositionStatsTempList) {
		boolean noErrors = true;
		for (JpaSssSecuritiesPositionStatsTemp securitiesPositionStatsTemp : securitiesPositionStatsTempList) {
			if (securitiesPositionStatsTemp.getRemarks() == null || securitiesPositionStatsTemp.getRemarks().isEmpty()
					|| securitiesPositionStatsTemp.getRemarks().isBlank()) {
				try {
					JpaSssSecuritiesPositionStats securitiesPositionStats = convertToJpaSssSecuritiesPositionStats(
							securitiesPositionStatsTemp);

					String remarks = validationSssSecuritiesPositionStatsLiveData(securitiesPositionStats);
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						securitiesPositionStatsTemp.setRemarks(String.join(", ", remarks));
						securitiesPositionStatsTempDAO.save(securitiesPositionStatsTemp);
					} else
						securitiesPositionStatsDAO.save(securitiesPositionStats);
				} catch (Exception e) {
					securitiesPositionStatsTemp
							.setRemarks("error while saving securities position stats live table data");
					e.printStackTrace();
					securitiesPositionStatsTempDAO.save(securitiesPositionStatsTemp);
					noErrors= false; 
				}
			}
		}
		return noErrors;
	}

	private JpaSssSecuritiesPositionStats convertToJpaSssSecuritiesPositionStats(
			JpaSssSecuritiesPositionStatsTemp securitiesPositionStatsTemp) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaSssSecuritiesPositionStats securitiesPositionStats = mapper.map(securitiesPositionStatsTemp,
				JpaSssSecuritiesPositionStats.class);
		return securitiesPositionStats;
	}

	private String validationSssSecuritiesPositionStatsLiveData(JpaSssSecuritiesPositionStats securitiesPositionStats) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(securitiesPositionStats.getSecuritiesCode(), "special",
				"Securities Code"));
		remarks.append(regexValidation.regexValidator(securitiesPositionStats.getSecuritiesCode(), "length12",
				"Securities Code"));

		remarks.append(regexValidation.regexValidator(String.valueOf(securitiesPositionStats.getOpeningNominalAmount()),
				"decimal", "Opening Nominal Account"));

		if (securitiesPositionStats.getClosingNominalAmount() != null) {
			remarks.append(
					regexValidation.regexValidator(String.valueOf(securitiesPositionStats.getClosingNominalAmount()),
							"decimal", "Closing Nominal Account"));
		}

		remarks.append(regexValidation.regexValidator(securitiesPositionStats.getAccountId(), "Id", "Account Id"));
		remarks.append(
				regexValidation.regexValidator(securitiesPositionStats.getAccountId(), "length36", "Account Id"));
		return remarks.toString().trim();
	}

}