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
import scrips.datamigration.common.UserData;
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.regex.RegexValidation;
import scrips.datamigration.rtgs.model.JpaAccount;
import scrips.datamigration.rtgs.model.JpaAccountSource;
import scrips.datamigration.rtgs.model.JpaAccountTemp;
import scrips.datamigration.rtgs.model.JpaMember;
import scrips.datamigration.rtgs.repository.JpaAccountDAO;
import scrips.datamigration.rtgs.repository.JpaAccountSourceDAO;
import scrips.datamigration.rtgs.repository.JpaAccountTempDAO;
import scrips.datamigration.rtgs.repository.JpaMemberDAO;

@Service
public class AccountService {

	private final Logger logger = LogManager.getLogger(AccountService.class);

	@Autowired
	private Environment env;

	@Autowired
	JpaAccountDAO accountDAO;
	@Autowired
	private UserData userData;
	@Autowired
	JpaAccountTempDAO accountTempDAO;

	@Autowired
	JpaMemberDAO memberDAO;
	@Autowired
	JpaAccountSourceDAO accountSourceDAO;
	@Autowired
	private RegexValidation regexValidation;

	public String migrateAccount(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) {
		List<JpaAccountSource> accountSourceList = createAndSaveAccountSourceData(fileRecords, draftDBDetails);
		List<JpaAccountTemp> accountTemplist = convertAccountSourceToAccountTemp(accountSourceList);
		boolean noErrors = saveAccountLiveTableData(accountTemplist);
		return (accountSourceList.size() == accountTemplist.size() && noErrors) ? "noErrors" : "hasErrors";
	}

	private List<JpaAccountSource> createAndSaveAccountSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaAccountSource> accountSourceList = new ArrayList<JpaAccountSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 13, draftDBDetails.size(), delimiter);
				if (error != null) {
					JpaAccountSource jpaAccountSource = new JpaAccountSource();
					jpaAccountSource.setRemarks(error);
					accountSourceDAO.save(jpaAccountSource);
					continue;
				}
				Date date = new Date();
				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line, delimiter);
				String remarks = "";
				String accountIndicator = dbRecordsMap.get("main_account_indicator");
				String defaultMainAccount = dbRecordsMap.get("default_main_account");
				if (accountIndicator.equalsIgnoreCase("Y") || accountIndicator.equalsIgnoreCase("N"))
					accountIndicator = dbRecordsMap.get("main_account_indicator");
				else
					remarks = String.join(",", remarks, "main_account_indicator data should be Y or N");

				if (defaultMainAccount.equalsIgnoreCase("Y") || defaultMainAccount.equalsIgnoreCase("N"))
					defaultMainAccount = dbRecordsMap.get("default_main_account");
				else
					remarks = String.join(",", remarks, "default_main_account data should be Y or N");

				JpaAccountSource accountSourceObj = JpaAccountSource.builder().id(id)
						.accountNumber(dbRecordsMap.get("account_number")).description(dbRecordsMap.get("description"))
						.memberId(dbRecordsMap.get("member_code")).accountType(dbRecordsMap.get("account_type"))
						.mainAccountIndicator(accountIndicator).defaultMainAccount(defaultMainAccount)
						.statementDeliveryBic(dbRecordsMap.get("statement_delivery_bic"))
						.accountStatus(dbRecordsMap.get("account_status"))
						.currencyCode(dbRecordsMap.get("currency_code"))
						.endOfDayStatement(dbRecordsMap.get("end_of_day_statement")).payerReference(null)
						.relatedReference(null).counterpartyId(null).valueDate(null).activationDate(null)
						.glCategory(dbRecordsMap.get("gl_category")).costCentre(dbRecordsMap.get("cost_centre"))
						.glAccountNumber(dbRecordsMap.get("gl_account_number"))
						.statementInterval(dbRecordsMap.get("statement_interval")).action(null).status(null)
						.modifiedBy(null).modifiedDate(null).approvedBy(null).approvedDate(null).createdDate(null)
						.effectiveDate(null).approvalRemark(null)
						.workflowStatusId(dbRecordsMap.get("workflow_status_id"))
						.complianceCalculation(dbRecordsMap.get("compliance_calculation"))
						.migratedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)).build();
				remarks = remarks.concat(validationJpaAccountSource(accountSourceObj));
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				accountSourceObj.setRemarks(remarks);
				accountSourceList.add(accountSourceObj);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return accountSourceList;
	}

	private String validationJpaAccountSource(JpaAccountSource accountSource) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(accountSource.getAccountNumber(), "special", "Account Number"));
		remarks.append(regexValidation.regexValidator(accountSource.getAccountNumber(), "length34", "Account Number"));
		remarks.append(regexValidation.regexValidator(accountSource.getMemberId(), "special", "Member Id"));
		remarks.append(regexValidation.regexValidator(accountSource.getMemberId(), "length36", "Member Id"));
		remarks.append(regexValidation.regexValidator(accountSource.getDescription(), "length35", "Description"));
		remarks.append(regexValidation.regexValidator(accountSource.getCurrencyCode(), "special", "Currency Code"));
		remarks.append(regexValidation.regexValidator(accountSource.getCurrencyCode(), "length3", "Currency Code"));
		remarks.append(regexValidation.regexValidator(accountSource.getAccountType(), "accounttype", "Account Type"));
		remarks.append(regexValidation.regexValidator(accountSource.getAccountType(), "length30", "Account Type"));
		remarks.append(regexValidation.regexValidator(String.valueOf(accountSource.getDefaultMainAccount()), "special",
				"Default Main Account"));
		remarks.append(regexValidation.regexValidator(String.valueOf(accountSource.getDefaultMainAccount()), "length1",
				"Default Main Account"));

		if (accountSource.getStatementDeliveryBic() != null
				&& !accountSource.getStatementDeliveryBic().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(accountSource.getStatementDeliveryBic(), "special",
					"Statement Delivery Bic"));
			remarks.append(regexValidation.regexValidator(accountSource.getStatementDeliveryBic(), "length11",
					"Statement Delivery Bic"));
		}
		if (accountSource.getAccountStatus() != null && !accountSource.getAccountStatus().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(accountSource.getAccountStatus(), "special", "Account Status"));
			remarks.append(
					regexValidation.regexValidator(accountSource.getAccountStatus(), "length30", "Account Status"));
		}

		if (accountSource.getEndOfDayStatement() != null && !accountSource.getEndOfDayStatement().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(accountSource.getEndOfDayStatement(),
					"alphabetswithunderscore", "End Of Day Statement"));
			remarks.append(regexValidation.regexValidator(accountSource.getEndOfDayStatement(), "length30",
					"End Of Day Statement"));
		}
		if (accountSource.getGlCategory() != null && !accountSource.getGlCategory().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(accountSource.getGlCategory(), "special", "GL Category"));

			remarks.append(regexValidation.regexValidator(accountSource.getGlCategory(), "length5", "GL Category"));
		}
		if (accountSource.getCostCentre() != null && !accountSource.getCostCentre().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(accountSource.getCostCentre(), "special", "Cost Centre"));
			remarks.append(regexValidation.regexValidator(accountSource.getCostCentre(), "length10", "Cost Centre"));
		}
		if (accountSource.getGlAccountNumber() != null && !accountSource.getGlAccountNumber().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(accountSource.getGlAccountNumber(), "special", "GL Account Number"));
			remarks.append(regexValidation.regexValidator(accountSource.getGlAccountNumber(), "length50",
					"GL Account Number"));
		}
		if (accountSource.getStatementInterval() != null && !accountSource.getStatementInterval().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(accountSource.getStatementInterval(), "special",
					"Statement Interval"));
			remarks.append(regexValidation.regexValidator(accountSource.getStatementInterval(), "length11",
					"Statement Interval"));
		}
		return remarks.toString().trim();
	}

	private List<JpaAccountTemp> convertAccountSourceToAccountTemp(List<JpaAccountSource> accountSourceList) {
		List<JpaAccountTemp> accountList = new ArrayList<JpaAccountTemp>();

		String id = userData.getSystemUserId();
		for (JpaAccountSource sourceRec : accountSourceList) {
			accountSourceDAO.save(sourceRec);
			try {
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {

					Date date = new Date();
					String memberId = null;
					List<JpaMember> memberList = memberDAO.findByMemberCode(sourceRec.getMemberId());
					if (memberList != null && memberList.size() > 0)
						memberId = memberList.get(0).getId();
					List<String> validationRemarks = new ArrayList<String>();
					if (memberId == null || memberId.trim().isEmpty())
						validationRemarks.add("member_code is not found in  Member Table");
					String accountIndicator = sourceRec.getMainAccountIndicator();
					String defaultMainAccount = sourceRec.getDefaultMainAccount();
					if (sourceRec.getMainAccountIndicator().equalsIgnoreCase("Y"))
						accountIndicator = "1";
					else if (sourceRec.getMainAccountIndicator().equalsIgnoreCase("N"))
						accountIndicator = "0";

					if (sourceRec.getDefaultMainAccount().equalsIgnoreCase("Y"))
						defaultMainAccount = "1";
					else if (sourceRec.getDefaultMainAccount().equalsIgnoreCase("N"))
						defaultMainAccount = "0";
					JpaAccountTemp accountTempObj = JpaAccountTemp.builder().id(sourceRec.getId())
							.accountNumber(sourceRec.getAccountNumber()).description(sourceRec.getDescription())
							.memberId(memberId).accountType(sourceRec.getAccountType())
							.mainAccountIndicator(Integer.parseInt(accountIndicator))
							.defaultMainAccount(Integer.parseInt(defaultMainAccount))
							.statementDeliveryBic(!CommonUtils.isNullOrEmpty(sourceRec.getStatementDeliveryBic()) ? null
									: sourceRec.getStatementDeliveryBic())
							.accountStatus(!CommonUtils.isNullOrEmpty(sourceRec.getAccountStatus()) ? null
									: sourceRec.getAccountStatus())
							.currencyCode(sourceRec.getCurrencyCode())
							.endOfDayStatement(!CommonUtils.isNullOrEmpty(sourceRec.getEndOfDayStatement()) ? null
									: sourceRec.getEndOfDayStatement())
							.payerReference("NA").relatedReference("NA").counterpartyId("NA").valueDate(0)
							.glCategory(!CommonUtils.isNullOrEmpty(sourceRec.getGlCategory()) ? null
									: sourceRec.getGlCategory())
							.costCentre(!CommonUtils.isNullOrEmpty(sourceRec.getCostCentre()) ? null
									: sourceRec.getCostCentre())
							.glAccountNumber(!CommonUtils.isNullOrEmpty(sourceRec.getGlAccountNumber()) ? null
									: sourceRec.getGlAccountNumber())
							.statementInterval(!CommonUtils.isNullOrEmpty(sourceRec.getStatementInterval()) ? null
									: sourceRec.getStatementInterval())
							.activationDate(date).workflowStatusId(null).complianceCalculation(Short.parseShort("0"))
							.action(" ").status("ACTIVE").modifiedBy(id).modifiedDate(date).approvedBy(id)
							.approvedDate(date).createdDate(date).effectiveDate(date).approvalRemark("Data Migration")
							.build();
					if (validationRemarks.size() > 0) {
						accountTempObj.setRemarks(String.join(", ", validationRemarks));
					}
					accountTempDAO.save(accountTempObj);
					accountList.add(accountTempObj);
				}

			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving account_temp table data");
				accountSourceDAO.save(sourceRec);
			}
		}
		return accountList;
	}

	private boolean saveAccountLiveTableData(List<JpaAccountTemp> accountTemplist) {
		boolean noErrors = true;
		for (JpaAccountTemp accountTemp : accountTemplist) {
			if (accountTemp.getRemarks() == null || accountTemp.getRemarks().isEmpty()
					|| accountTemp.getRemarks().isBlank()) {
				try {
					JpaAccount account = converttoJpaAccount(accountTemp);
					String remarks = validateAccountLiveData(account);
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						accountTemp.setRemarks(String.join(", ", remarks));
						accountTempDAO.save(accountTemp);
					} else
						accountDAO.save(account);
				} catch (Exception e) {
					accountTemp.setRemarks("error while saving account live table data");
					e.printStackTrace();
					accountTempDAO.save(accountTemp);
					noErrors = false;
				}
			}
		}
		return noErrors;
	}

	private JpaAccount converttoJpaAccount(JpaAccountTemp tempObj) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaAccount liveObj = mapper.map(tempObj, JpaAccount.class);
		return liveObj;
	}

	private String validateAccountLiveData(JpaAccount account) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(account.getId(), "Id", "Id"));
		remarks.append(regexValidation.regexValidator(account.getId(), "length36", "Id"));
		remarks.append(regexValidation.regexValidator(account.getAccountNumber(), "special", "Account Number"));
		remarks.append(regexValidation.regexValidator(account.getAccountNumber(), "length34", "Account Number"));
		remarks.append(regexValidation.regexValidator(account.getMemberId(), "Id", "Member Id"));
		remarks.append(regexValidation.regexValidator(account.getMemberId(), "length36", "Member Id"));
		remarks.append(regexValidation.regexValidator(account.getDescription(), "length35", "Description"));
		remarks.append(regexValidation.regexValidator(account.getAccountType(), "accounttype", "Account Type"));
		remarks.append(regexValidation.regexValidator(account.getAccountType(), "length30", "Account Type"));
		remarks.append(regexValidation.regexValidator(String.valueOf(account.getDefaultMainAccount()), "special",
				"Default Main Account"));
		remarks.append(regexValidation.regexValidator(String.valueOf(account.getDefaultMainAccount()), "length1",
				"Default Main Account"));
		remarks.append(regexValidation.regexValidator(account.getCurrencyCode(), "special", "Currency Code"));
		remarks.append(regexValidation.regexValidator(account.getCurrencyCode(), "length3", "Currency Code"));

		if (account.getMainAccountIndicator() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(account.getMainAccountIndicator()), "length1",
					"Main Account Indicator"));
		}
		if (account.getStatementDeliveryBic() != null && !account.getStatementDeliveryBic().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(account.getStatementDeliveryBic(), "special",
					"Statement Delivery Bic"));
			remarks.append(regexValidation.regexValidator(account.getStatementDeliveryBic(), "length11",
					"Statement Delivery Bic"));
		}
		if (account.getAccountStatus() != null && !account.getAccountStatus().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(account.getAccountStatus(), "special", "Account Status"));
			remarks.append(regexValidation.regexValidator(account.getAccountStatus(), "length30", "Account Status"));
		}

		if (account.getEndOfDayStatement() != null && !account.getEndOfDayStatement().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(account.getEndOfDayStatement(), "alphabetswithunderscore",
					"End Of Day Statement"));
			remarks.append(
					regexValidation.regexValidator(account.getEndOfDayStatement(), "length30", "End Of Day Statement"));
		}
		if (account.getPayerReference() != null && !account.getPayerReference().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(account.getPayerReference(), "length36", "Payer Reference"));
		}
		if (account.getRelatedReference() != null && !account.getRelatedReference().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(account.getRelatedReference(), "length36", "Related Reference"));
		}
		if (account.getCounterpartyId() != null && !account.getCounterpartyId().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(account.getCounterpartyId(), "length36", "CounterParty Id"));
		}

		if (account.getGlCategory() != null && !account.getGlCategory().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(account.getGlCategory(), "special", "GL Category"));
			remarks.append(regexValidation.regexValidator(account.getGlCategory(), "length5", "GL Category"));
		}
		if (account.getCostCentre() != null && !account.getCostCentre().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(account.getCostCentre(), "special", "Cost Centre"));
			remarks.append(regexValidation.regexValidator(account.getCostCentre(), "length10", "Cost Centre"));
		}
		if (account.getGlAccountNumber() != null && !account.getGlAccountNumber().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(account.getGlAccountNumber(), "special", "GL Account Number"));
			remarks.append(
					regexValidation.regexValidator(account.getGlAccountNumber(), "length50", "GL Account Number"));
		}
		if (account.getStatementInterval() != null && !account.getStatementInterval().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(account.getStatementInterval(), "special", "Statement Interval"));
			remarks.append(
					regexValidation.regexValidator(account.getStatementInterval(), "length11", "Statement Interval"));
		}

		if (account.getModifiedBy() != null && !account.getModifiedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(account.getModifiedBy(), "length36", "Modified By"));
		}
		if (account.getApprovedBy() != null && !account.getApprovedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(account.getApprovedBy(), "length36", "Approved By"));
		}

		return remarks.toString().trim();
	}

}