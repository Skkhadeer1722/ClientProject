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

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.rtgs.model.JpaMember;
import scrips.datamigration.rtgs.model.JpaMemberSource;
import scrips.datamigration.rtgs.model.JpaMemberTemp;
import scrips.datamigration.rtgs.repository.AccountDAO;
import scrips.datamigration.rtgs.repository.AccountTempDAO;
import scrips.datamigration.rtgs.repository.JpaMemberSourceDAO;
import scrips.datamigration.sss.model.JpaSSSAccount;
import scrips.datamigration.sss.model.JpaSssMember;
import scrips.datamigration.sss.model.JpaSssSecuritiesCode;
import scrips.datamigration.sss.model.JpaSssSecuritiesCodeStatisticsSource;
import scrips.datamigration.sss.model.JpaSssSecuritiesCodeStatisticsTemp;
import scrips.datamigration.sss.repository.SssSecuritiesCodeDAO;
import scrips.datamigration.sss.repository.SssSecuritiesCodeStatisticsSourceDAO;

@Service
public class ReadFileAndConvertService {
	private final Logger logger = LogManager.getLogger(ReadFileAndConvertService.class);

	@Autowired
	AccountTempDAO accountTempDAO;

	@Autowired
	AccountDAO accountDAO;

	@Autowired
	private JpaMemberSourceDAO memberSource;

	@Autowired
	private SssSecuritiesCodeStatisticsSourceDAO securitiesCodeStatisticsSourceDAO;

	@Autowired
	private SssSecuritiesCodeDAO securitiesCodeDAO;


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

	public List<JpaSssSecuritiesCodeStatisticsTemp> convertToSssSecuritiesCodeStatisticsList(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaSssSecuritiesCodeStatisticsTemp> securitiesCodeStatisticsList = new ArrayList<JpaSssSecuritiesCodeStatisticsTemp>();
		for (String line : fileRecords) {
			List<String> content = Stream.of(line.split("\\|")).map(String::trim).collect(Collectors.toList());
			Map<String, String> dbRecorsMap = new HashMap<>();
			int count = 0;
			for (JpaFileUploadDetails db : draftDBDetails) {
				logger.info("{} - {} -{}", db.getTableFieldName(), content.get(count), content.get(count).length());
				dbRecorsMap.put(db.getTableFieldName(), content.get(count));
				count++;
			}
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			JpaSssSecuritiesCodeStatisticsSource SssSecuritiesCodeStatisticsSource = JpaSssSecuritiesCodeStatisticsSource
					.builder().id(id).securitiesCode(dbRecorsMap.get("securities_code"))
					.totalRedeemedAmount(dbRecorsMap.get("total_redeemed_amount"))
					.totalNominalAmountIssuedForErf(dbRecorsMap.get("total_nominal_amount_issued_for_erf"))
					.totalRedeemedAmountForErf(dbRecorsMap.get("total_redeemed_amount_for_erf"))
					.outstandingNominalAmount(dbRecorsMap.get("outstanding_nominal_amount"))
					.nextCouponDate(dbRecorsMap.get("next_coupon_date"))
					.lastCouponDate(dbRecorsMap.get("last_coupon_date"))
					.nextIndexEndDate(dbRecorsMap.get("next_index_end_date"))
					.amountAllotedToCentralBank(dbRecorsMap.get("amount_alloted_to_central_bank"))
					.amountAllotedToOthers(dbRecorsMap.get("amount_alloted_to_others")).build();
			securitiesCodeStatisticsSourceDAO.save(SssSecuritiesCodeStatisticsSource);
			JpaSssSecuritiesCodeStatisticsTemp SssSecuritiesCodeStatisticsTemp = JpaSssSecuritiesCodeStatisticsTemp
					.builder().id(id).securitiesCode(dbRecorsMap.get("securities_code"))
					.totalRedeemedAmount(Long.parseLong(dbRecorsMap.get("total_redeemed_amount")))
					.totalRedeemedAmountForErf(Long.parseLong(dbRecorsMap.get("total_redeemed_amount_for_erf")))
					.totalNominalAmountIssuedForErf(
							Long.parseLong(dbRecorsMap.get("total_nominal_amount_issued_for_erf")))
					.outstandingNominalAmount(Long.parseLong(dbRecorsMap.get("outstanding_nominal_amount")))
					.nextCouponDate(Integer.parseInt(dbRecorsMap.get("next_coupon_date")))
					.lastCouponDate(Integer.parseInt(dbRecorsMap.get("last_coupon_date")))
					.nextIndexEndDate(Integer.parseInt(dbRecorsMap.get("next_index_end_date")))
					.amountAllotedToCentralBank(Long.parseLong(dbRecorsMap.get("amount_alloted_to_central_bank")))
					.amountAllotedToOthers(Long.parseLong(dbRecorsMap.get("amount_alloted_to_others"))).build();
			securitiesCodeStatisticsList.add(SssSecuritiesCodeStatisticsTemp);
		}
		return securitiesCodeStatisticsList;
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

	

	

	


	

	public List<JpaSssSecuritiesCodeStatisticsTemp> convertToSssSecuritiesCodeStatisticsList() {
		List<JpaSssSecuritiesCodeStatisticsTemp> securitiesCodeStatisticsTempList = new ArrayList<JpaSssSecuritiesCodeStatisticsTemp>();
		try {
			List<JpaSssSecuritiesCodeStatisticsSource> securitiyCodeStatistics = securitiesCodeStatisticsSourceDAO
					.findAll();
			if (securitiyCodeStatistics != null && !securitiyCodeStatistics.isEmpty()) {
				List<String> errors = new ArrayList<String>();
				for (JpaSssSecuritiesCodeStatisticsSource sourceRec : securitiyCodeStatistics) {
					JpaSssSecuritiesCode securitiesCodeRecord = securitiesCodeDAO
							.findBySecuritiesCode(sourceRec.getSecuritiesCode());
					if (sourceRec.getRemarks() == null) {
						if (errors.size() > 0) {
							sourceRec.setRemarks(String.join(", ", errors));
							securitiesCodeStatisticsSourceDAO.save(sourceRec);
							continue;
						}
						List<String> validationRemarks = new ArrayList<String>();
						if (securitiesCodeRecord == null)
							validationRemarks.add("SecuritesCode is not found in  SecuritiesCode Table");
						if (!CommonUtils.validateParseLong(sourceRec.getTotalRedeemedAmount()))
							validationRemarks.add("Invalid TotalRedeemedAmount");
						if (!CommonUtils.validateParseLong(sourceRec.getTotalNominalAmountIssuedForErf()))
							validationRemarks.add("Invalid TotalNominalAmountIssuedForErf");
						if (!CommonUtils.validateParseLong(sourceRec.getTotalRedeemedAmountForErf()))
							validationRemarks.add("Invalid RedeemedAmountForErf");
						if (!CommonUtils.validateParseLong(sourceRec.getOutstandingNominalAmount()))
							validationRemarks.add("Invalid OutstandingNominalAmount");
						if (!CommonUtils.validateParseLong(sourceRec.getAmountAllotedToCentralBank()))
							validationRemarks.add("Invalid AmountAllotedToCentralBank");
						if (!CommonUtils.validateParseLong(sourceRec.getAmountAllotedToOthers()))
							validationRemarks.add("Invalid AmountAllotedToOthers");
						if (!CommonUtils.validateParseInteger(sourceRec.getNextCouponDate()))
							validationRemarks.add("Invalid NextCouponDate");
						if (!CommonUtils.validateParseInteger(sourceRec.getLastCouponDate()))
							validationRemarks.add("Invalid LastCouponDate");
						if (!CommonUtils.validateParseInteger(sourceRec.getNextIndexEndDate()))
							validationRemarks.add("Invalid NextIndexEndDate");
//						
						if (validationRemarks.size() > 0) {
							sourceRec.setRemarks(String.join(", ", validationRemarks));
							securitiesCodeStatisticsSourceDAO.save(sourceRec);
							continue;
						}
						// Date date = new Date();
						JpaSssSecuritiesCodeStatisticsTemp SssSecuritiesCodeStatisticsTemp = JpaSssSecuritiesCodeStatisticsTemp
								.builder().id(sourceRec.getId()).securitiesCode(sourceRec.getSecuritiesCode())
								.totalRedeemedAmount(Long.parseLong(sourceRec.getTotalRedeemedAmount()))
								.totalRedeemedAmountForErf(Long.parseLong(sourceRec.getTotalRedeemedAmountForErf()))
								.totalNominalAmountIssuedForErf(
										Long.parseLong(sourceRec.getTotalNominalAmountIssuedForErf()))
								.outstandingNominalAmount(Long.parseLong(sourceRec.getOutstandingNominalAmount()))
								.nextCouponDate(Integer.parseInt(sourceRec.getNextCouponDate()))
								.lastCouponDate(Integer.parseInt(sourceRec.getLastCouponDate()))
								.nextIndexEndDate(Integer.parseInt(sourceRec.getNextIndexEndDate()))
								.amountAllotedToCentralBank(Long.parseLong(sourceRec.getAmountAllotedToCentralBank()))
								.amountAllotedToOthers(Long.parseLong(sourceRec.getAmountAllotedToOthers())).build();
						securitiesCodeStatisticsTempList.add(SssSecuritiesCodeStatisticsTemp);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error in temp {}", e.getMessage());
			e.printStackTrace();
		}
		return securitiesCodeStatisticsTempList;
	}

	

}