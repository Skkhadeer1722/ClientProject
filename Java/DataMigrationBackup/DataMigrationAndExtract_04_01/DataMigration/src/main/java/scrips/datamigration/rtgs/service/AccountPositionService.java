package scrips.datamigration.rtgs.service;

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
import scrips.datamigration.rtgs.model.JpaAccountPosition;
import scrips.datamigration.rtgs.model.JpaAccountPositionSource;
import scrips.datamigration.rtgs.model.JpaAccountPositionTemp;
import scrips.datamigration.rtgs.model.JpaMember;
import scrips.datamigration.rtgs.repository.AccountPositionDAO;
import scrips.datamigration.rtgs.repository.AccountPositionDAOTemp;
import scrips.datamigration.rtgs.repository.AccountPositionSourceDAO;
import scrips.datamigration.rtgs.repository.MemberDAO;

@Service
public class AccountPositionService {
	private final Logger logger = LogManager.getLogger(AccountPositionService.class);

	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	MemberDAO memberDAO;

	@Autowired
	AccountPositionDAO accountPosDAO;

	@Autowired
	AccountPositionDAOTemp accountPositionTempDAO;

	@Autowired
	AccountPositionSourceDAO accountPositionSourceDAO;

	@Autowired
	private Environment env;

	public String migrateAccountPosition(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) {
		List<JpaAccountPositionSource> accountPositionSourceList = createAndSaveAccountPositionSourceData(fileRecords,
				draftDBDetails);
		List<JpaAccountPositionTemp> accountPositionTempList = convertAccountPositionSourceToAccountPositionTemp(
				accountPositionSourceList);
		boolean noErrors =saveAccountPositionLiveTableData(accountPositionTempList);
		return (accountPositionSourceList.size()==accountPositionTempList.size() && noErrors)? "noErrors" : "hasErrors";
	}

	private boolean saveAccountPositionLiveTableData(List<JpaAccountPositionTemp> accountPositionTempList) {
		boolean noErrors = true;
		for (JpaAccountPositionTemp accountPositionTemp : accountPositionTempList) {
			if (accountPositionTemp.getRemarks() == null || accountPositionTemp.getRemarks().isEmpty()
					|| accountPositionTemp.getRemarks().isBlank()) {
				try {
					JpaAccountPosition accountPosition = converttoJpaAccPosition(accountPositionTemp);
					String remarks = duplicateAccountId(accountPosition);
					remarks = remarks.concat(validationJpaAccountPositionLiveData(accountPosition));
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						accountPositionTemp.setRemarks(String.join(", ", remarks));
						accountPositionTempDAO.save(accountPositionTemp);
					} else
						accountPosDAO.save(accountPosition);
				} catch (Exception e) {
					accountPositionTemp.setRemarks("error while saving account position live table data");
					e.printStackTrace();
					accountPositionTempDAO.save(accountPositionTemp);
					noErrors= false;

				}
			}
		}
		return noErrors;
	}

	private String duplicateAccountId(JpaAccountPosition accountPosition) {
		JpaAccountPosition duplicateAcountId = accountPosDAO.findByAccountId(accountPosition.getAccountId());
		String remarks = "";
		if (duplicateAcountId != null)
			remarks = remarks.concat(",Duplicate account_id");
		return remarks;
	}

	private List<JpaAccountPositionSource> createAndSaveAccountPositionSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaAccountPositionSource> accountPositionSourceList = new ArrayList<JpaAccountPositionSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 6, draftDBDetails.size(), delimiter);
				if (error != null) {
					JpaAccountPositionSource accountPositionSource = new JpaAccountPositionSource();
					accountPositionSource.setRemarks(error);
					accountPositionSourceDAO.save(accountPositionSource);
					continue;
				}

				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line, delimiter);
				JpaAccountPositionSource accountPosSource = JpaAccountPositionSource.builder().id(id)
						.accountId(dbRecordsMap.get("account_id")).currencyCode(dbRecordsMap.get("currency_code"))
						.memberId(dbRecordsMap.get("member_code"))
						.currentAccountBalance(dbRecordsMap.get("current_account_balance"))
						.openingAccountBalance(dbRecordsMap.get("opening_account_balance"))
						.availableBalance(dbRecordsMap.get("available_balance")).queueCount(null).queueAmount(null)
						.settledPaymentsCount(null).settledPaymentsAmount(null).settledReceiptsCount(null)
						.settledReceiptsAmount(null).createdDate(dbRecordsMap.get("created_date")).modifiedDate(null)
						.build();

				String remarks = validationJpaAccountPositionSource(accountPosSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				accountPosSource.setRemarks(remarks);
				accountPositionSourceList.add(accountPosSource);

			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return accountPositionSourceList;

	}

	private String validationJpaAccountPositionSource(JpaAccountPositionSource accountPosition) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(accountPosition.getAccountId(), "numeric", "Account Number"));
		remarks.append(regexValidation.regexValidator(accountPosition.getAccountId(), "length36", "Account Number"));
		remarks.append(regexValidation.regexValidator(accountPosition.getMemberId(), "special", "Member Code"));
		remarks.append(regexValidation.regexValidator(accountPosition.getMemberId(), "length36", "Member Code"));
		remarks.append(
				regexValidation.regexValidator(accountPosition.getCurrencyCode(), "currencycode", "Currency Code"));
		remarks.append(
				regexValidation.regexValidator(accountPosition.getCurrencyCode(), "currencycode", "Currency Code"));
		remarks.append(regexValidation.regexValidator(accountPosition.getOpeningAccountBalance(), "decimal",
				"Opening Balance "));
//		remarks.append(regexValidation.regexValidator(accountPosition.getOpeningAccountBalance(), "length28", "Opening Balance "));
		remarks.append(
				regexValidation.regexValidator(accountPosition.getAvailableBalance(), "decimal", "Available Balance "));
//		remarks.append(regexValidation.regexValidator(accountPosition.getAvailableBalance(),"length28", "Available Balance "));
		remarks.append(regexValidation.regexValidator(accountPosition.getCurrentAccountBalance(), "decimal",
				"Current Account Balance"));
//		remarks.append(regexValidation.regexValidator(accountPosition.getCurrentAccountBalance(),"length28", "Current Account Balance"));
		if (!CommonUtils.validateParseDate_yyyyMMdd_HHmmss(accountPosition.getCreatedDate()))
			remarks.append(",Invalid Created Date");
		return remarks.toString().trim();
	}

	private List<JpaAccountPositionTemp> convertAccountPositionSourceToAccountPositionTemp(
			List<JpaAccountPositionSource> accountPositionSourceList) {
		List<JpaAccountPositionTemp> accountPostitionTempList = new ArrayList<JpaAccountPositionTemp>();
		for (JpaAccountPositionSource sourceRec : accountPositionSourceList) {
			accountPositionSourceDAO.save(sourceRec);
			try {

				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {
					String memberId = null;
					List<JpaMember> memberList = memberDAO.findByMemberCode(sourceRec.getMemberId());
					if (memberList != null && memberList.size() > 0)
						memberId = memberList.get(0).getId();
					List<String> validationRemarks = new ArrayList<String>();
					if (memberId == null || memberId.trim().isEmpty())
						validationRemarks.add("MemberCode is not found in  Member Table");

					Date date = new Date();
					JpaAccountPositionTemp jpaAccountPositionTemp = JpaAccountPositionTemp.builder()
							.id(sourceRec.getId()).accountId(sourceRec.getAccountId())
							.currencyCode(sourceRec.getCurrencyCode()).memberId(memberId)
							.currentAccountBalance(Double.parseDouble(sourceRec.getCurrentAccountBalance()))
							.openingAccountBalance(Double.parseDouble(sourceRec.getOpeningAccountBalance()))
							.availableBalance(Double.parseDouble(sourceRec.getAvailableBalance())).queueCount(0)
							.queueAmount(0.0).settledPaymentsCount(0).settledPaymentsAmount(0.0).settledReceiptsCount(0)
							.settledReceiptsAmount(0.0)
							.createdDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sourceRec.getCreatedDate()))
							.modifiedDate(date).build();
					if (validationRemarks.size() > 0) {
						jpaAccountPositionTemp.setRemarks(String.join(", ", validationRemarks));
					}
					accountPositionTempDAO.save(jpaAccountPositionTemp);
					accountPostitionTempList.add(jpaAccountPositionTemp);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving account position temp table data");
				accountPositionSourceDAO.save(sourceRec);
			}
		}
		return accountPostitionTempList;
	}

	private JpaAccountPosition converttoJpaAccPosition(JpaAccountPositionTemp tempObj) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaAccountPosition liveObj = mapper.map(tempObj, JpaAccountPosition.class);
		return liveObj;
	}

	private String validationJpaAccountPositionLiveData(JpaAccountPosition accountPosition) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(accountPosition.getAccountId(), "numeric", "Account Number"));
		remarks.append(regexValidation.regexValidator(accountPosition.getAccountId(), "length36", "Account Number"));
		remarks.append(regexValidation.regexValidator(accountPosition.getMemberId(), "Id", "Member Code"));
		remarks.append(regexValidation.regexValidator(accountPosition.getMemberId(), "length36", "Member Code"));
		remarks.append(
				regexValidation.regexValidator(accountPosition.getCurrencyCode(), "currencycode", "Currency Code"));
		remarks.append(
				regexValidation.regexValidator(accountPosition.getCurrencyCode(), "currencycode", "Currency Code"));
		remarks.append(regexValidation.regexValidator(String.valueOf(accountPosition.getOpeningAccountBalance()),
				"decimal", "Opening Balance "));
		remarks.append(regexValidation.regexValidator(String.valueOf(accountPosition.getAvailableBalance()), "decimal",
				"Available Balance "));
		remarks.append(regexValidation.regexValidator(String.valueOf(accountPosition.getCurrentAccountBalance()),
				"decimal", "Current Account Balance"));
		return remarks.toString().trim();
	}

}