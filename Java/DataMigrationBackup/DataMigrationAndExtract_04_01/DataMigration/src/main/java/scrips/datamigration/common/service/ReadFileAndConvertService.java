package scrips.datamigration.common.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.rtgs.model.JpaMember;
import scrips.datamigration.rtgs.model.JpaMemberSource;
import scrips.datamigration.rtgs.model.JpaMemberTemp;
import scrips.datamigration.rtgs.repository.AccountDAO;
import scrips.datamigration.rtgs.repository.AccountTempDAO;
import scrips.datamigration.rtgs.repository.JpaMemberSourceDAO;
import scrips.datamigration.sss.model.JpaSSSAccount;
import scrips.datamigration.sss.model.JpaSssMember;

@Service
public class ReadFileAndConvertService {
	private final Logger logger = LogManager.getLogger(ReadFileAndConvertService.class);

	@Autowired
	AccountTempDAO accountTempDAO;

	@Autowired
	AccountDAO accountDAO;

	@Autowired
	private JpaMemberSourceDAO memberSource;

	

	public List<JpaMember> convertToMemberList(List<String> list, List<JpaFileUploadDetails> draftDBDetails)
			throws  ParseException, SQLException {
		List<JpaMember> memberList = new ArrayList<JpaMember>();
		int lineCount = 0;
		Date date = new Date();
		for (String line : list) {
			lineCount++;
			List<String> content = Stream.of(line.split("\\|")).map(String::trim).collect(Collectors.toList());
			Map<String, String> dbRecorsMap = new HashMap<>();
			int count = 0;
			for (JpaFileUploadDetails db : draftDBDetails) {
				logger.info("{} - {} -{}", db.getTableFieldName(), content.get(count), content.get(count).length());
				dbRecorsMap.put(db.getTableFieldName(), content.get(count));
				count++;
			}
			JpaMemberSource jpaMemberSource = JpaMemberSource.builder().id(UUID.randomUUID().toString())
					.memberCode(dbRecorsMap.get("member_code")).swiftBic(dbRecorsMap.get("swift_bic"))
					.swiftMember(Short.parseShort(dbRecorsMap.get("swift_member")))
					.bankCode(Short.parseShort(dbRecorsMap.get("bank_code"))).name(dbRecorsMap.get("name"))
					.shortName(dbRecorsMap.get("short_name")).memberStatus(dbRecorsMap.get("member_status"))
					.memberType(dbRecorsMap.get("member_type")).sectorId(dbRecorsMap.get("sector_id"))
					.mcbId(dbRecorsMap.get("mcb_id")).uen(dbRecorsMap.get("uen")).lei(dbRecorsMap.get("lei"))
					.memberClassification(dbRecorsMap.get("member_classification"))
					.exemptedFromBilling(Short.parseShort(dbRecorsMap.get("exempted_from_billing")))
					.exemptedFromSystemLimit(Short.parseShort(dbRecorsMap.get("exempted_from_system_limit")))
					.location(dbRecorsMap.get("location"))
					.activationDate(
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dbRecorsMap.get("activation_date")))
					.action(dbRecorsMap.get("action")).status(dbRecorsMap.get("status")).modifiedBy(null)
					.modifiedDate(null).approvedBy(null).approvedDate(null).createdDate(null).effectiveDate(null)
					.approvalRemark(dbRecorsMap.get("approval_remark")).workflowStatusId(null)
					.fiGroup(dbRecorsMap.get("fi_group")).billingProfileId(dbRecorsMap.get("billing_profile_id"))
					.mcbIntraday(Double.parseDouble(dbRecorsMap.get("mcb_intraday")))
					.mcbEodMinimum(Double.parseDouble(dbRecorsMap.get("mcb_eod_minimum")))
					.mcbEodMaximum(Double.parseDouble(dbRecorsMap.get("mcb_eod_maximum")))
					.mcbAverage(Double.parseDouble(dbRecorsMap.get("mcb_average")))
					.taxProfileId(dbRecorsMap.get("tax_profile_id")).verifiedBy(dbRecorsMap.get("verified_by"))
					.verifiedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dbRecorsMap.get("verified_date")))
					.build();
			memberSource.save(jpaMemberSource);
			try {
				JpaMember jpaMember = JpaMember.builder().id(UUID.randomUUID().toString())
						.memberCode(dbRecorsMap.get("member_code")).swiftBic(dbRecorsMap.get("swift_bic"))
						.swiftMember(Short.parseShort(dbRecorsMap.get("swift_member")))
						.bankCode(Short.parseShort(dbRecorsMap.get("bank_code"))).name(dbRecorsMap.get("name"))
						.shortName(dbRecorsMap.get("short_name")).memberStatus(dbRecorsMap.get("member_status"))
						.memberType(dbRecorsMap.get("member_type")).sectorId(dbRecorsMap.get("sector_id"))
						.mcbId(dbRecorsMap.get("mcb_id")).uen(dbRecorsMap.get("uen")).lei(dbRecorsMap.get("lei"))
						.memberClassification(dbRecorsMap.get("member_classification"))
						.exemptedFromBilling(Short.parseShort(dbRecorsMap.get("exempted_from_billing")))
						.exemptedFromSystemLimit(Short.parseShort(dbRecorsMap.get("exempted_from_system_limit")))
						.location(dbRecorsMap.get("location"))
						.activationDate(
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dbRecorsMap.get("activation_date")))
						.action(null).status(dbRecorsMap.get("status")).modifiedBy("System").modifiedDate(date)
						.approvedBy("System").approvedDate(date).createdDate(date).effectiveDate(date)
						.approvalRemark(null).workflowStatusId(null).fiGroup(dbRecorsMap.get("fi_group"))
						.billingProfileId(dbRecorsMap.get("billing_profile_id"))
						.mcbIntraday(Double.parseDouble(dbRecorsMap.get("mcb_intraday")))
						.mcbEodMinimum(Double.parseDouble(dbRecorsMap.get("mcb_eod_minimum")))
						.mcbEodMaximum(Double.parseDouble(dbRecorsMap.get("mcb_eod_maximum")))
						.mcbAverage(Double.parseDouble(dbRecorsMap.get("mcb_average")))
						.taxProfileId(dbRecorsMap.get("tax_profile_id")).verifiedBy(dbRecorsMap.get("verified_by"))
						.verifiedDate(date).build();
				memberList.add(jpaMember);
			} catch (Exception e) {
				System.out.println("line -" + lineCount + " skip due to conversion issues");
				System.out.println("exception -" + e);
			}

		}
		return memberList;
	}

	public static Object objectMapper(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		JpaMemberTemp memberTempObj = mapper.convertValue(object, JpaMemberTemp.class);
		// logger.info("{} - {}", memberTempObj, memberTempObj.getMemberCode());
		return memberTempObj;
	}

	public static Object objectMapperJpaMember(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		JpaMember memberObj = mapper.convertValue(object, JpaMember.class);
		// logger.info("{} - {}", memberObj, memberObj.getMemberCode());
		return memberObj;
	}

	public HashMap<String, Integer> getTableDetailsMap(List<JpaFileUploadDetails> draftDBDetails) {
		HashMap<String, Integer> tableDetailsMap = new HashMap<String, Integer>();
		for (JpaFileUploadDetails fud : draftDBDetails) {
			tableDetailsMap.put(fud.getTableFieldName(), fud.getSequenceNo() - 1);
		}
		tableDetailsMap.forEach((x, y) -> System.out.println(x + "=" + y));

		return tableDetailsMap;
	}

	public List<JpaSSSAccount> convertToSssAccountList(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) throws NumberFormatException, ParseException {
		List<JpaSSSAccount> sssaccountList = new ArrayList<JpaSSSAccount>();
		Date date = new Date();
		for (String line : fileRecords) {
			List<String> content = Stream.of(line.split("\\|")).map(String::trim).collect(Collectors.toList());
			Map<String, String> dbRecorsMap = new HashMap<>();
			int count = 0;
			for (JpaFileUploadDetails db : draftDBDetails) {
				logger.info("{} - {} -{}", db.getTableFieldName(), content.get(count), content.get(count).length());
				dbRecorsMap.put(db.getTableFieldName(), content.get(count));
				count++;
			}

			JpaSSSAccount jpaSSSAccount = JpaSSSAccount.builder().id(UUID.randomUUID().toString())
					.accountId(dbRecorsMap.get("account_id")).memberId(dbRecorsMap.get("member_id"))
					.custodyAccountTypeId(dbRecorsMap.get("custody_account_type_id"))
					.description(dbRecorsMap.get("description"))
					.corporateInvestor(dbRecorsMap.get("corporate_investor"))
					.accountStatus(dbRecorsMap.get("account_status")).taxProfileId(dbRecorsMap.get("tax_profile_id"))
					.statementDeliveryBic(dbRecorsMap.get("statement_delivery_bic")).action(dbRecorsMap.get("action"))
					.status(dbRecorsMap.get("status")).modifiedBy(dbRecorsMap.get("modified_by")).modifiedDate(date)
					.approvedBy(dbRecorsMap.get("approved_by")).approvedDate(date).createdDate(date).effectiveDate(date)
					.approvalRemark(dbRecorsMap.get("approval_remark"))
					.workflowStatusId(Integer.parseInt(dbRecorsMap.get("workflow_status_id"))).build();
			sssaccountList.add(jpaSSSAccount);
		}
		return sssaccountList;
	}
	public List<JpaSssMember> convertToSssMemberList(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) throws NumberFormatException, ParseException {

		List<JpaSssMember> sssmemberList = new ArrayList<JpaSssMember>();
		for (String line : fileRecords) {

			List<String> content = Stream.of(line.split("\\|")).map(String::trim).collect(Collectors.toList());
			Map<String, String> dbRecorsMap = new HashMap<>();
			int count = 0;
			for (JpaFileUploadDetails db : draftDBDetails) {

				logger.info("{} - {} -{}", db.getTableFieldName(), content.get(count), content.get(count).length());
				dbRecorsMap.put(db.getTableFieldName(), content.get(count));
				count++;

			}
			try {

				JpaSssMember jpaSssMember = JpaSssMember.builder().id(UUID.randomUUID().toString())
//				.memberCode(dbRecorsMap.get("member_code"))
//				.memberName(dbRecorsMap.get("member_name"))
//				.activationDate(dbRecorsMap.get("activation_date"))
//				// .activationDate(new SimpleDateFormat("yyyyDDmm").parse(dbRecorsMap.get("activation_date")))
//				.memberShortName(dbRecorsMap.get("member_short_name"))
//				.memberStatus(dbRecorsMap.get("member_status"))
//				.memberType(Integer.parseInt(dbRecorsMap.get("member_type")))
//				// .memberType(dbRecorsMap.get("member_type"))
//				.sectorId(dbRecorsMap.get("sector_id"))
//				.bankCode(dbRecorsMap.get("bank_code"))
//				.autoDebitIndicator(dbRecorsMap.get("auto_debit_indicator"))
//				.location(dbRecorsMap.get("location"))
//				.facilityEligibilityId(dbRecorsMap.get("facility_eligibility_id"))
//				.lei(dbRecorsMap.get("lei"))
//				.uen(dbRecorsMap.get("uen"))
//				.zeroHoldingsStatement(dbRecorsMap.get("zero_holdings_statement"))
//				.exemptedFromBilling(dbRecorsMap.get("exempted_from_billing"))
//				.exemptedFromSystemLimit(dbRecorsMap.get("exempted_from_system_limit"))
//				// .exemptedFromSystemLimit(Integer.parseInt(dbRecorsMap.get("exempted_from_system_limit")))
//				.statementDeliveryBic(dbRecorsMap.get("statement_delivery_bic"))
//				.taxProfileId(dbRecorsMap.get("tax_profile_id"))
//				.feeProfileId(dbRecorsMap.get("fee_profile_id"))
//				.endOfDayStatement(dbRecorsMap.get("end_of_day_statement"))
//				.build();
//				sssmemberList.add(jpaSssMember);

						.memberCode(dbRecorsMap.get("member_code")).name(dbRecorsMap.get("name"))
						.shortName(dbRecorsMap.get("short_name")).memberType(dbRecorsMap.get("member_type"))
						.memberStatus(dbRecorsMap.get("member_status"))
						// .activationDate(dbRecorsMap.get("activation_date"))
						.activationDate(new SimpleDateFormat("yyyyDDmm").parse(dbRecorsMap.get("activation_date")))
						.swiftBic(dbRecorsMap.get("swift_bic")).location(dbRecorsMap.get("location"))

						.fiCode(dbRecorsMap.get("fi_code")).traderStatus(dbRecorsMap.get("trader_status"))
						.sectorId(dbRecorsMap.get("sector_id"))
						.autoDebitIndicator(Short.parseShort(dbRecorsMap.get("auto_debit_indicator")))
						.facilityEligibilityId(dbRecorsMap.get("facility_eligibility_id")).lei(dbRecorsMap.get("lei"))
						.uen(dbRecorsMap.get("uen")).statementDeliveryBic(dbRecorsMap.get("statement_delivery_bic"))
						.endOfDayStatement(dbRecorsMap.get("end_of_day_statement"))

						.exemptedFromBilling(Short.parseShort(dbRecorsMap.get("exempted_from_billing")))
						.exemptedFromSystemLimit(Short.parseShort(dbRecorsMap.get("exempted_from_system_limit")))
						// .exemptedFromSystemLimit(Integer.parseInt(dbRecorsMap.get("exempted_from_system_limit")))
						.zeroHoldingsStatement(dbRecorsMap.get("zero_holdings_statement"))
						.rtgsAccount(dbRecorsMap.get("rtgs_account")).taxProfileId(dbRecorsMap.get("tax_profile_id"))
						.billingProfileId(dbRecorsMap.get("billing_profile_id")).action(dbRecorsMap.get("action"))
						.status(dbRecorsMap.get("status")).modifiedBy(dbRecorsMap.get("modified_by"))
						.modifiedDate(
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dbRecorsMap.get("modified_date")))
						.approvedBy(dbRecorsMap.get("approved_by"))
						.approvedDate(
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dbRecorsMap.get("approved_date")))
						.createdDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dbRecorsMap.get("created_date")))
						.effectiveDate(
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dbRecorsMap.get("effective_date")))
						.workflowStatusId(Integer.parseInt(dbRecorsMap.get("work_flow_status_id")))
						.approvalRemark("Data Migration").build();
				sssmemberList.add(jpaSssMember);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sssmemberList;
	}

	

	

	


	

	

}