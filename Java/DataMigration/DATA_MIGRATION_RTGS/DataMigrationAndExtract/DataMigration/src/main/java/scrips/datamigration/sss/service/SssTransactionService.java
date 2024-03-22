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
import scrips.datamigration.sss.model.JpaSssAccount;
import scrips.datamigration.sss.model.JpaSssIncomingTransaction;
import scrips.datamigration.sss.model.JpaSssMember;
import scrips.datamigration.sss.model.JpaSssTransaction;
import scrips.datamigration.sss.model.JpaSssTransactionSource;
import scrips.datamigration.sss.model.JpaSssTransactionTemp;
import scrips.datamigration.sss.repository.SssAccountDAO;
import scrips.datamigration.sss.repository.SssIncomingTransactionDAO;
import scrips.datamigration.sss.repository.SssMemberDAO;
import scrips.datamigration.sss.repository.SssTransactionDAO;
import scrips.datamigration.sss.repository.SssTransactionSourceDAO;
import scrips.datamigration.sss.repository.SssTransactionTempDAO;

@Service
public class SssTransactionService {

	@Autowired
	SssTransactionDAO sssTransactionDAO;

	@Autowired
	private SssMemberDAO sssMemberDAO;

	@Autowired
	private Environment env;
	@Autowired
	private SssAccountDAO sssAccountDAO;

	@Autowired
	SssTransactionTempDAO sssTransactionTempDAO;

	@Autowired
	SssTransactionSourceDAO sssTransactionSourceDAO;
	@Autowired
	SssIncomingTransactionDAO sssIncomingTransactionDAO;

	@Autowired
	private RegexValidation regexValidation;

	private final Logger logger = LogManager.getLogger(SssTransactionService.class);

	public String migrateSssTransaction(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) {
		List<JpaSssTransactionSource> sssTransactionSourceList = createAndSaveTransactionSourceData(fileRecords,
				draftDBDetails);
		List<JpaSssTransactionTemp> sssTransactionTempList = convertTransactionSourceToTransactionTemp(
				sssTransactionSourceList);
		boolean noErrors = saveTransactionLiveTableData(sssTransactionTempList);
		return (sssTransactionSourceList.size() == sssTransactionTempList.size() && noErrors) ? "noErrors"
				: "hasErrors";
	}

	private List<JpaSssTransactionSource> createAndSaveTransactionSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaSssTransactionSource> sssTransactionSourceList = new ArrayList<JpaSssTransactionSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 22, draftDBDetails.size(), delimiter);
				if (error != null) {
					JpaSssTransactionSource jpaSssTransactionSource = new JpaSssTransactionSource();
					jpaSssTransactionSource.setRemarks(error);
					sssTransactionSourceDAO.save(jpaSssTransactionSource);
					continue;
				}

				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line, delimiter);
				Date date = new Date();
				JpaSssTransactionSource jpasssTransactionSource = JpaSssTransactionSource.builder().id(id)
						.sssReference(dbRecordsMap.get("sss_reference")).messageLogId(null)
						.securitiesCode(dbRecordsMap.get("securities_code"))
						.transactionType(dbRecordsMap.get("transaction_type")).status(null)
						.settlementDate(dbRecordsMap.get("settlement_date")).tradeDate(dbRecordsMap.get("trade_date"))
						.currencyCode(dbRecordsMap.get("currency_code"))
						.settlementAmount(dbRecordsMap.get("settlement_amount"))
						.nominalAmount(dbRecordsMap.get("nominal_amount")).dealPrice(dbRecordsMap.get("deal_price"))
						.delivererMemberCode(dbRecordsMap.get("deliverer_member_code"))
						.receiverMemberCode(dbRecordsMap.get("receiver_member_code")).repoClosingDate(null)
						.repoClosingSettlementAmount(null).reasonCode(null)
						.transactionReference(dbRecordsMap.get("transaction_reference")).processedDate(null)
						.createdDate(dbRecordsMap.get("created_date")).modifiedDate(dbRecordsMap.get("modified_date"))
						.senderMemberSwiftCode(null).payerMemberSwiftCode(null).payeeMemberSwiftCode(null)
						.receiverMemberSwiftCode(dbRecordsMap.get("receiver_member_swift_code")).senderMemberCode(null)
						.messageType(dbRecordsMap.get("message_type")).senderType(null).receiverRtgsAccount(null)
						.delivererRtgsAccount(null)
						.receiverRtgsMemberCode(dbRecordsMap.get("receiver_rtgs_member_code"))
						.delivererRtgsMemberCode(dbRecordsMap.get("deliverer_rtgs_member_code")).holdIndicator(null)
						.paymentType(null).gridLockIndicator(null).rolloverCount(null)
						.pendingCancellationIndicator(null).delivererMemberId(null).receiverMemberId(null)
						.receiverAccountId(null).delivererAccountId(null).senderMemberId(null).pdmIndicator(null)
						.erfReference(null).delivererAccountNo(dbRecordsMap.get("deliverer_account_no"))
						.receiverAccountNo(dbRecordsMap.get("receiver_account_no")).msgReceivedTimestamp(null)
						.receiverSenderId(dbRecordsMap.get("receiver_sender_id"))
						.delivererSenderId(dbRecordsMap.get("deliverer_sender_id")).delivererOnbehalf(null)
						.receiverOnbehalf(null).processFlag(null).delivererBeneficiaryAccount(null).underlyingId(null)
						.balanceType(null).accuredInterestAmount(dbRecordsMap.get("accured_interest_amount"))
						.delivererChannel(null).receiverChannel(null).repoRate(null).haircutRate(null)
						.originalAccountNo(null)
						.migratedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date))
						.build();

				String remarks = validationJpaSSSTransactionSource(jpasssTransactionSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				jpasssTransactionSource.setRemarks(remarks);
				sssTransactionSourceList.add(jpasssTransactionSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return sssTransactionSourceList;
	}

	private String validationJpaSSSTransactionSource(JpaSssTransactionSource sssTransactionSource) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(
				regexValidation.regexValidator(sssTransactionSource.getSssReference(), "special", "Sss Reference"));
		remarks.append(
				regexValidation.regexValidator(sssTransactionSource.getSssReference(), "length36", "Sss Reference"));
		remarks.append(
				regexValidation.regexValidator(sssTransactionSource.getSecuritiesCode(), "special", "Securities Code"));
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getSecuritiesCode(), "length35",
				"Securities Code"));
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getTransactionType(), "special",
				"Transaction type"));
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getTransactionType(), "length4",
				"Transaction type"));
		remarks.append(
				regexValidation.regexValidator(sssTransactionSource.getSettlementDate(), "numeric", "Settlement Date"));
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getTradeDate(), "numeric", "Trade Date "));
		remarks.append(
				regexValidation.regexValidator(sssTransactionSource.getNominalAmount(), "decimal", "Nominal Amount"));
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getNominalAmount(), "decimal_23",
				"Nominal Amount"));

		remarks.append(regexValidation.regexValidator(sssTransactionSource.getDelivererMemberCode(), "special",
				"Deliverer Member Code"));
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getDelivererMemberCode(), "length12",
				"Deliverer Member Code"));
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getReceiverMemberCode(), "special",
				"Receiver Member Code"));
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getReceiverMemberCode(), "length12",
				"Receiver Member Code"));
		if (!CommonUtils.validateParseDate_yyyyMMdd_HHmmss(sssTransactionSource.getCreatedDate()))
			remarks.append(",Invalid Created Date");
		if (!CommonUtils.validateParseDate_yyyyMMdd_HHmmss(sssTransactionSource.getModifiedDate()))
			remarks.append(",Invalid Modified Date");
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getDelivererAccountNo(), "special",
				"Deliverer Account No"));
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getDelivererAccountNo(), "length35",
				"Deliverer Account No"));
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getReceiverAccountNo(), "special",
				"Receiver Account No"));
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getReceiverAccountNo(), "length35",
				"Receiver Account No"));

//		String settlmentAmount = regexValidation.regexValidator(sssTransactionSource.getSettlementAmount(), "decimal",
//				"Settlement Amount");
		remarks.append(regexValidation.regexValidator(sssTransactionSource.getSettlementAmount(), "decimal",
				"Settlement Amount"));
//		if (settlmentAmount.trim().isEmpty() || settlmentAmount.trim().isBlank()) 
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getSettlementAmount(), "decimal_23",
					"Settlement Amount"));
		

		if (sssTransactionSource.getCurrencyCode() != null
				&& !sssTransactionSource.getCurrencyCode().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getCurrencyCode(), "currencycode",
					"Currency Code"));
		}

		if (sssTransactionSource.getDealPrice() != null && !sssTransactionSource.getDealPrice().trim().isEmpty()) {
			String dealPrice = regexValidation.regexValidator(sssTransactionSource.getDealPrice(), "decimal",
					"Deal Price");
			remarks.append(dealPrice);
			if (dealPrice.trim().isEmpty() || dealPrice.trim().isBlank())
				remarks.append(regexValidation.regexValidator(sssTransactionSource.getDealPrice(), "decimal_23",
						"Deal Price"));
		}

		if (sssTransactionSource.getTransactionReference() != null
				&& !sssTransactionSource.getTransactionReference().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getTransactionReference(), "special",
					"Transaction Reference"));
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getTransactionReference(), "length36",
					"Transaction Reference"));
		}
		if (sssTransactionSource.getReceiverMemberSwiftCode() != null
				&& !sssTransactionSource.getReceiverMemberSwiftCode().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getReceiverMemberSwiftCode(), "special",
					"Receiver Member Swift Code "));
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getReceiverMemberSwiftCode(), "length12",
					"Receiver Member Swift Code "));
		}
		if (sssTransactionSource.getMessageType() != null && !sssTransactionSource.getMessageType().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getMessageType(), "specialwithdots",
					"Message Type"));
			remarks.append(
					regexValidation.regexValidator(sssTransactionSource.getMessageType(), "length26", "Message Type"));
		}
		if (sssTransactionSource.getReceiverRtgsMemberCode() != null
				&& !sssTransactionSource.getReceiverRtgsMemberCode().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getReceiverRtgsMemberCode(), "special",
					"Receiver Rtgs Member Code"));
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getReceiverRtgsMemberCode(), "length36",
					"Receiver Rtgs Member Code"));
		}
		if (sssTransactionSource.getDelivererRtgsMemberCode() != null
				&& !sssTransactionSource.getDelivererRtgsMemberCode().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getDelivererRtgsMemberCode(), "special",
					"Deliverer Rtgs Member Code"));
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getDelivererRtgsMemberCode(), "length36",
					"Deliverer Rtgs Member Code"));
		}
		if (sssTransactionSource.getReceiverSenderId() != null
				&& !sssTransactionSource.getReceiverSenderId().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getReceiverSenderId(), "special",
					"Receiver Sender ID"));
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getReceiverSenderId(), "length36",
					"Receiver Sender ID"));
		}
		if (sssTransactionSource.getDelivererSenderId() != null
				&& !sssTransactionSource.getDelivererSenderId().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getDelivererSenderId(), "special",
					"Deliverer Sender ID"));
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getDelivererSenderId(), "length36",
					"Deliverer Sender ID"));
		}
		if (sssTransactionSource.getAccuredInterestAmount() != null
				&& !sssTransactionSource.getAccuredInterestAmount().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getAccuredInterestAmount(), "decimal",
					"Accured Interest Amount"));
			remarks.append(regexValidation.regexValidator(sssTransactionSource.getAccuredInterestAmount(), "decimal_23",
					"Accured Interest Amount"));
		}

		return remarks.toString().trim();
	}

	private List<JpaSssTransactionTemp> convertTransactionSourceToTransactionTemp(
			List<JpaSssTransactionSource> sssTransactionSourceList) {
		List<JpaSssTransactionTemp> sssTransactionTempList = new ArrayList<JpaSssTransactionTemp>();

		for (JpaSssTransactionSource sourceRec : sssTransactionSourceList) {
			sssTransactionSourceDAO.save(sourceRec);
			try {

				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String transaction_type = sourceRec.getTransactionType();
					String receiver_sender_id = sourceRec.getReceiverSenderId();
					String status = "FUTU";
					String deliverer_member_id = null; // dbRecordsMap.get("deliverer_member_code");
					String receiver_member_id = null; // dbRecordsMap.get("receiver_member_code");
					String deliverer_account_id = null; // dbRecordsMap.get("deliverer_account_no");
					String receiver_account_id = null; // dbRecordsMap.get("receiver_account_no");
					String payee_member_swift_code = null; // dbRecordsMap.get("deliverer_member_code");
					String payer_member_swift_code = null; // dbRecordsMap.get("receiver_member_code");
					String transaction_types = null;

					List<String> validationRemarks = new ArrayList<String>();

					List<JpaSssMember> delivererMemberCode = sssMemberDAO
							.findBymemberCode(sourceRec.getDelivererMemberCode());

					if (delivererMemberCode != null && delivererMemberCode.size() > 0) {
						deliverer_member_id = delivererMemberCode.get(0).getId();
						payee_member_swift_code = delivererMemberCode.get(0).getSwiftBic();
					}
					if (deliverer_member_id == null || deliverer_member_id.trim().isEmpty())
						validationRemarks.add("deliverer_member_id is Not Present in SSS Member Table");
					if (payee_member_swift_code == null || payee_member_swift_code.trim().isEmpty())
						validationRemarks.add("payee_member_swift_code is Not Present in SSS Member Table");

					List<JpaSssMember> receiverMemberList = sssMemberDAO
							.findBymemberCode(sourceRec.getReceiverMemberCode());
					if (receiverMemberList != null && receiverMemberList.size() > 0) {
						receiver_member_id = receiverMemberList.get(0).getId();
						payer_member_swift_code = receiverMemberList.get(0).getSwiftBic();
					}
					if (receiver_member_id == null || receiver_member_id.trim().isEmpty())
						validationRemarks.add("receiver_member_id is Not Present in SSS Member Table");
					if (payer_member_swift_code == null || payer_member_swift_code.trim().isEmpty())
						validationRemarks.add("payer_member_swift_code is Not Present in SSS Member Table");

					List<JpaSssAccount> delivererAccountList = sssAccountDAO
							.findByAccountId(sourceRec.getDelivererAccountNo());
					if (delivererAccountList != null && delivererAccountList.size() > 0) {
						deliverer_account_id = delivererAccountList.get(0).getId();
					}
					if (deliverer_account_id == null || deliverer_account_id.trim().isEmpty())
						validationRemarks.add("deliverer_account_id is Not Present in SSS Account Table");

					List<JpaSssAccount> receiverAccountList = sssAccountDAO
							.findByAccountId(sourceRec.getReceiverAccountNo());
					if (receiverAccountList != null && !receiverAccountList.isEmpty()) {
						receiver_account_id = receiverAccountList.get(0).getId();
					}
					if (receiver_account_id == null || receiver_account_id.trim().isEmpty())
						validationRemarks.add("receiver_account_id is Not Present in SSS Account Table");

					if (transaction_type.equals("RPC") && receiver_sender_id.equals("ERF"))
						transaction_types = "ESRC";
					else if (transaction_type.equals("RPC") && !receiver_sender_id.equals("ERF")) {
						transaction_types = "REPC";
					}
					if (transaction_types.equals("REPC") || transaction_types.equals("ESRC")) {
						JpaSssTransactionTemp jpaSssTransactionTemp = JpaSssTransactionTemp.builder()
								.id(sourceRec.getId()).sssReference(sourceRec.getSssReference()).messageLogId(null)
								.securitiesCode(sourceRec.getSecuritiesCode()).transactionType(transaction_types)
								.status(status).settlementDate(Integer.parseInt(sourceRec.getSettlementDate()))
								.tradeDate(Integer.parseInt(sourceRec.getTradeDate()))
								.currencyCode(!CommonUtils.isNullOrEmpty(sourceRec.getCurrencyCode()) ? null
										: sourceRec.getCurrencyCode())
								.settlementAmount(new BigDecimal(sourceRec.getSettlementAmount()))
								.nominalAmount(new BigDecimal(sourceRec.getNominalAmount()))
								.dealPrice(!CommonUtils.isNullOrEmpty(sourceRec.getDealPrice()) ? null
										: new BigDecimal(sourceRec.getDealPrice()))
								.delivererMemberCode(sourceRec.getDelivererMemberCode())
								.receiverMemberCode(sourceRec.getReceiverMemberCode()).repoClosingDate(null)
								.repoClosingSettlementAmount(null).reasonCode(null)
								.transactionReference(
										!CommonUtils.isNullOrEmpty(sourceRec.getTransactionReference()) ? null
												: sourceRec.getTransactionReference())
								.processedDate(null).createdDate(sdf.parse(sourceRec.getCreatedDate()))
								.modifiedDate(sdf.parse(sourceRec.getModifiedDate())).senderMemberSwiftCode(null)
								.payeeMemberSwiftCode(payee_member_swift_code)
								.payerMemberSwiftCode(payer_member_swift_code)
								.receiverMemberSwiftCode(
										!CommonUtils.isNullOrEmpty(sourceRec.getReceiverMemberSwiftCode()) ? null
												: sourceRec.getReceiverMemberSwiftCode())
								.senderMemberCode(null)
								.messageType(!CommonUtils.isNullOrEmpty(sourceRec.getMessageType()) ? null
										: sourceRec.getMessageType())
								.senderType(null).receiverRtgsAccount(" ").delivererRtgsAccount(" ")
								.receiverRtgsMemberCode(
										!CommonUtils.isNullOrEmpty(sourceRec.getReceiverRtgsMemberCode()) ? null
												: sourceRec.getReceiverRtgsMemberCode())
								.delivererRtgsMemberCode(
										!CommonUtils.isNullOrEmpty(sourceRec.getDelivererRtgsMemberCode()) ? null
												: sourceRec.getDelivererRtgsMemberCode())
								.holdIndicator(Short.parseShort("0")).paymentType("APMT")
								.gridLockIndicator(Short.parseShort("0")).rolloverCount(Integer.parseInt("0"))
								.pendingCancellationIndicator(Short.parseShort("0"))
								.delivererMemberId(deliverer_member_id).receiverMemberId(receiver_member_id)
								.receiverAccountId(receiver_account_id).delivererAccountId(deliverer_account_id)
								.senderMemberId(null).pdmIndicator(Short.parseShort("0")).erfReference(null)
								.delivererAccountNo(sourceRec.getDelivererAccountNo())
								.receiverAccountNo(sourceRec.getReceiverAccountNo()).msgReceivedTimestamp(date)
								.receiverSenderId(!CommonUtils.isNullOrEmpty(sourceRec.getReceiverSenderId()) ? null
										: sourceRec.getReceiverSenderId())
								.delivererSenderId(!CommonUtils.isNullOrEmpty(sourceRec.getDelivererSenderId()) ? null
										: sourceRec.getDelivererSenderId())
								.delivererOnbehalf(Short.parseShort("0")).receiverOnbehalf(Short.parseShort("0"))
								.processFlag("No").delivererBeneficiaryAccount(null).underlyingId(null)
								.balanceType(null)
								.accuredInterestAmount(
										!CommonUtils.isNullOrEmpty(sourceRec.getAccuredInterestAmount()) ? null
												: new BigDecimal(sourceRec.getAccuredInterestAmount()))
								.delivererChannel("SWIFT").receiverChannel("SWIFT").repoRate(null).haircutRate(null)
								.originalAccountNo(null)
							    .migratedDate(sourceRec.getMigratedDate())
								.build();

						if (validationRemarks.size() > 0) {
							jpaSssTransactionTemp.setRemarks(String.join(", ", validationRemarks));
						}
						sssTransactionTempDAO.save(jpaSssTransactionTemp);
						sssTransactionTempList.add(jpaSssTransactionTemp);
					}

					Long random = (long) (Math.random() * 100000L);
					String s = new SimpleDateFormat("yyyyMMddHHmmssSS").format(date);
					String sss_ref = "ST".concat(s).concat(random.toString());
					String id = UUID.randomUUID().toString().replaceAll("-", "");
					if (transaction_types.equals("ESRC")) {
						JpaSssTransactionTemp jpaSssTransactionTempEsrc = JpaSssTransactionTemp.builder().id(id)
								.sssReference(sss_ref).messageLogId(null).securitiesCode(sourceRec.getSecuritiesCode())
								.transactionType("ERIC").status(status)
								.settlementDate(Integer.parseInt(sourceRec.getSettlementDate()))
								.tradeDate(Integer.parseInt(sourceRec.getTradeDate()))
								.currencyCode(!CommonUtils.isNullOrEmpty(sourceRec.getCurrencyCode()) ? null
										: sourceRec.getCurrencyCode())
								.settlementAmount(new BigDecimal(sourceRec.getSettlementAmount()))
								.nominalAmount(new BigDecimal(sourceRec.getNominalAmount())).dealPrice(null)
								.delivererMemberCode(sourceRec.getDelivererMemberCode()).receiverMemberCode("MASGSGSG")
								.repoClosingDate(null).repoClosingSettlementAmount(null).reasonCode(null)
								.transactionReference(null).processedDate(null).createdDate(date).modifiedDate(date)
								.senderMemberSwiftCode(null).payeeMemberSwiftCode(payee_member_swift_code)
								.payerMemberSwiftCode(payer_member_swift_code)
								.receiverMemberSwiftCode(
										!CommonUtils.isNullOrEmpty(sourceRec.getReceiverMemberSwiftCode()) ? null
												: sourceRec.getReceiverMemberSwiftCode())
								.senderMemberCode(null)
								.messageType(!CommonUtils.isNullOrEmpty(sourceRec.getMessageType()) ? null
										: sourceRec.getMessageType())
								.senderType(null).receiverRtgsAccount(" ").delivererRtgsAccount(" ")
								.receiverRtgsMemberCode(
										!CommonUtils.isNullOrEmpty(sourceRec.getReceiverRtgsMemberCode()) ? null
												: sourceRec.getReceiverRtgsMemberCode())
								.delivererRtgsMemberCode(
										!CommonUtils.isNullOrEmpty(sourceRec.getDelivererRtgsMemberCode()) ? null
												: sourceRec.getDelivererRtgsMemberCode())
								.holdIndicator(Short.parseShort("1")).paymentType("APMT")
								.gridLockIndicator(Short.parseShort("0")).rolloverCount(Integer.parseInt("0"))
								.pendingCancellationIndicator(Short.parseShort("0"))
								.delivererMemberId(deliverer_member_id).receiverMemberId(receiver_member_id)
								.receiverAccountId(receiver_account_id).delivererAccountId(deliverer_account_id)
								.senderMemberId(null).pdmIndicator(Short.parseShort("0")).erfReference(null)
								.delivererAccountNo(sourceRec.getDelivererAccountNo()).receiverAccountNo("ALO")
								.msgReceivedTimestamp(date).receiverSenderId(null).delivererSenderId(null)
								.delivererOnbehalf(Short.parseShort("0")).receiverOnbehalf(Short.parseShort("0"))
								.processFlag("No").delivererBeneficiaryAccount(null).underlyingId(null)
								.balanceType(null).accuredInterestAmount(null).delivererChannel("SWIFT")
								.receiverChannel("SWIFT").repoRate(null).haircutRate(null).originalAccountNo(null)
							    .migratedDate(sourceRec.getMigratedDate())
								.build();

						if (validationRemarks.size() > 0) {
							jpaSssTransactionTempEsrc.setRemarks(String.join(", ", validationRemarks));
						}
						sssTransactionTempDAO.save(jpaSssTransactionTempEsrc);
						sssTransactionTempList.add(jpaSssTransactionTempEsrc);

					}
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving transaction_temp table data");
				sssTransactionSourceDAO.save(sourceRec);
			}
		}
		return sssTransactionTempList;
	}

	private boolean saveTransactionLiveTableData(List<JpaSssTransactionTemp> sssTransactionTempList) {
		boolean noErrors = true;
		for (JpaSssTransactionTemp sssTransactionTemp : sssTransactionTempList) {
			if (sssTransactionTemp.getRemarks() == null || sssTransactionTemp.getRemarks().isEmpty()
					|| sssTransactionTemp.getRemarks().isBlank()) {
				try {

					JpaSssTransaction sssTransaction = converttoJpaSssTransaction(sssTransactionTemp);
					String remarks = duplicateSssReference(sssTransaction);
					remarks = remarks.concat(validationJpaSSSTransactionLiveData(sssTransaction));

					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						sssTransactionTemp.setRemarks(String.join(", ", remarks));
						sssTransactionTempDAO.save(sssTransactionTemp);
					} else {
						saveIncomingTransaction(sssTransactionTemp);
						sssTransactionDAO.save(sssTransaction);
					}
				} catch (Exception e) {
					sssTransactionTemp.setRemarks("error while saving transaction live table data");
					e.printStackTrace();
					sssTransactionTempDAO.save(sssTransactionTemp);
					noErrors = false;

				}
			}
		}
		return noErrors;
	}

	private JpaSssTransaction converttoJpaSssTransaction(JpaSssTransactionTemp ssstransactionTemp) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaSssTransaction jpaSssTransaction = mapper.map(ssstransactionTemp, JpaSssTransaction.class);
		return jpaSssTransaction;
	}

	private String duplicateSssReference(JpaSssTransaction sssTransaction) {
		JpaSssTransaction duplicateSssReference = sssTransactionDAO
				.findBySssReference(sssTransaction.getSssReference());
		String remarks = "";
		if (duplicateSssReference != null)
			remarks = remarks.concat(",Duplicate sss_reference");
		return remarks;
	}

	private String validationJpaSSSTransactionLiveData(JpaSssTransaction ssstransaction) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(ssstransaction.getSssReference(), "special", "Sss Reference"));
		remarks.append(regexValidation.regexValidator(ssstransaction.getSssReference(), "length36", "Sss Reference"));
		remarks.append(
				regexValidation.regexValidator(ssstransaction.getSecuritiesCode(), "special", "Securities Code"));
		remarks.append(
				regexValidation.regexValidator(ssstransaction.getSecuritiesCode(), "length35", "Securities Code"));
		remarks.append(
				regexValidation.regexValidator(ssstransaction.getTransactionType(), "special", "Transaction type"));
		remarks.append(
				regexValidation.regexValidator(ssstransaction.getTransactionType(), "length4", "Transaction type"));
		remarks.append(regexValidation.regexValidator(String.valueOf(ssstransaction.getSettlementDate()), "numeric",
				"Settlement Date"));
		remarks.append(regexValidation.regexValidator(String.valueOf(ssstransaction.getTradeDate()), "numeric",
				"Trade Date "));
		remarks.append(regexValidation.regexValidator(String.valueOf(ssstransaction.getNominalAmount()), "decimal",
				"Nominal Amount"));
		remarks.append(regexValidation.regexValidator(String.valueOf(ssstransaction.getNominalAmount()), "decimal_23",
				"Nominal Amount"));
		remarks.append(regexValidation.regexValidator(ssstransaction.getDelivererMemberCode(), "special",
				"Deliverer Member Code"));
		remarks.append(regexValidation.regexValidator(ssstransaction.getDelivererMemberCode(), "length12",
				"Deliverer Member Code"));
		remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverMemberCode(), "special",
				"Receiver Member Code"));
		remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverMemberCode(), "length12",
				"Receiver Member Code"));
		remarks.append(regexValidation.regexValidator(ssstransaction.getDelivererAccountNo(), "special",
				"Deliverer Account No"));
		remarks.append(regexValidation.regexValidator(ssstransaction.getDelivererAccountNo(), "length35",
				"Deliverer Account No"));
		remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverAccountNo(), "special",
				"Receiver Account No"));
		remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverAccountNo(), "length35",
				"Receiver Account No"));

		// default null
		if (ssstransaction.getCurrencyCode() != null && !ssstransaction.getCurrencyCode().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(ssstransaction.getCurrencyCode(), "currencycode", "Currency Code"));
		}
//		if (ssstransaction.getSettlementAmount() != null) {
		remarks.append(regexValidation.regexValidator(String.valueOf(ssstransaction.getSettlementAmount()), "decimal",
				"Settlement Amount"));
		remarks.append(regexValidation.regexValidator(String.valueOf(ssstransaction.getSettlementAmount()),
				"decimal_23", "Settlement Amount"));
//		}
		if (ssstransaction.getDealPrice() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(ssstransaction.getDealPrice()), "decimal",
					"Deal Price"));
			remarks.append(regexValidation.regexValidator(String.valueOf(ssstransaction.getDealPrice()), "decimal_23",
					"Deal Price"));
		}

		if (ssstransaction.getTransactionReference() != null
				&& !ssstransaction.getTransactionReference().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(ssstransaction.getTransactionReference(), "special",
					"Transaction Reference"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getTransactionReference(), "length36",
					"Transaction Reference"));
		}
		if (ssstransaction.getReceiverMemberSwiftCode() != null
				&& !ssstransaction.getReceiverMemberSwiftCode().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverMemberSwiftCode(), "special",
					"Receiver Member Swift Code "));
			remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverMemberSwiftCode(), "length12",
					"Receiver Member Swift Code "));
		}
		if (ssstransaction.getMessageType() != null && !ssstransaction.getMessageType().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(ssstransaction.getMessageType(), "specialwithdots", "Message Type"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getMessageType(), "length26", "Message Type"));
		}
		if (ssstransaction.getReceiverRtgsMemberCode() != null
				&& !ssstransaction.getReceiverRtgsMemberCode().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverRtgsMemberCode(), "special",
					"Receiver Rtgs Member Code"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverRtgsMemberCode(), "length36",
					"Receiver Rtgs Member Code"));
		}
		if (ssstransaction.getDelivererRtgsMemberCode() != null
				&& !ssstransaction.getDelivererRtgsMemberCode().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(ssstransaction.getDelivererRtgsMemberCode(), "special",
					"Deliverer Rtgs Member Code"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getDelivererRtgsMemberCode(), "length36",
					"Deliverer Rtgs Member Code"));
		}
		if (ssstransaction.getReceiverSenderId() != null && !ssstransaction.getReceiverSenderId().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverSenderId(), "special",
					"Receiver Sender ID"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverSenderId(), "length36",
					"Receiver Sender ID"));
		}
		if (ssstransaction.getDelivererSenderId() != null && !ssstransaction.getDelivererSenderId().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(ssstransaction.getDelivererSenderId(), "special",
					"Deliverer Sender ID"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getDelivererSenderId(), "length36",
					"Deliverer Sender ID"));
		}

		if (ssstransaction.getDelivererMemberId() != null && !ssstransaction.getDelivererMemberId().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(ssstransaction.getDelivererMemberId(), "Id", "Deliverer Member ID"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getDelivererMemberId(), "length36",
					"Deliverer Member ID"));
		}
		if (ssstransaction.getReceiverMemberId() != null && !ssstransaction.getReceiverMemberId().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(ssstransaction.getReceiverMemberId(), "Id", "Receiver Member ID"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverMemberId(), "length36",
					"Receiver Member ID"));
		}
		if (ssstransaction.getDelivererAccountId() != null
				&& !ssstransaction.getDelivererAccountId().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(ssstransaction.getDelivererAccountId(), "Id",
					"Deliverer Account ID"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getDelivererAccountId(), "length36",
					"Deliverer Account ID"));
		}
		if (ssstransaction.getReceiverAccountId() != null && !ssstransaction.getReceiverAccountId().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(ssstransaction.getReceiverAccountId(), "Id", "Receiver Account ID"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getReceiverAccountId(), "length36",
					"Receiver Account ID"));
		}
		if (ssstransaction.getPayeeMemberSwiftCode() != null
				&& !ssstransaction.getPayeeMemberSwiftCode().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(ssstransaction.getPayeeMemberSwiftCode(), "special",
					"Payee Member Swift Code"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getPayeeMemberSwiftCode(), "length36",
					"Payee Member Swift Code"));
		}
		if (ssstransaction.getPayerMemberSwiftCode() != null
				&& !ssstransaction.getPayerMemberSwiftCode().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(ssstransaction.getPayerMemberSwiftCode(), "special",
					"Payer Member Swift Code"));
			remarks.append(regexValidation.regexValidator(ssstransaction.getPayerMemberSwiftCode(), "length36",
					"Payer Member Swift Code"));
		}
		if (ssstransaction.getAccuredInterestAmount() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(ssstransaction.getAccuredInterestAmount()),
					"decimal", "Accured Interest Amount"));
			remarks.append(regexValidation.regexValidator(String.valueOf(ssstransaction.getAccuredInterestAmount()),
					"decimal_23", "Accured Interest Amount"));
		}
		return remarks.toString().trim();
	}

	private void saveIncomingTransaction(JpaSssTransactionTemp sssTransactionTemp) {
		if (sssTransactionTemp.getTransactionType().equals("ESRC")
				|| sssTransactionTemp.getTransactionType().equals("REPC")) {
			JpaSssIncomingTransaction delivererIncomingTranscation = JpaSssIncomingTransaction.builder()
					.sssReference(sssTransactionTemp.getSssReference())
					// .messageLogId(sssTransactionList.get(0).getMessageLogId())
					.messageLogId("123").securitiesCode(sssTransactionTemp.getSecuritiesCode())
					.transactionType(sssTransactionTemp.getTransactionType()).status(sssTransactionTemp.getStatus())
					.settlementDate(sssTransactionTemp.getSettlementDate()).tradeDate(sssTransactionTemp.getTradeDate())
					.currencyCode(sssTransactionTemp.getCurrencyCode())
					.settlementAmount(sssTransactionTemp.getSettlementAmount())
					.nominalAmount(sssTransactionTemp.getNominalAmount()).dealPrice(sssTransactionTemp.getDealPrice())
					.delivererMemberCode(sssTransactionTemp.getDelivererMemberCode())
					// .receiverMemberCode(sssTransactionTemp.getReceiverMemberCode())
					.receiverMemberCode("NULL").repoClosingDate(sssTransactionTemp.getRepoClosingDate())
					.repoClosingSettlementAmount(sssTransactionTemp.getRepoClosingSettlementAmount())
					.matchedSssReference(null)
//							.senderMemberCode(sssTransactionTemp.get(0).getSenderMemberCode())
					.senderMemberCode("123").senderReference(null).senderType(sssTransactionTemp.getSenderType())
					.createdDate(sssTransactionTemp.getCreatedDate()).modifiedDate(sssTransactionTemp.getModifiedDate())
					.messageType(sssTransactionTemp.getMessageType())
					// .receiverRtgsAccount(sssTransactionTemp.getReceiverRtgsAccount())
					.receiverRtgsAccount(null).delivererRtgsAccount(sssTransactionTemp.getDelivererRtgsAccount())
					// .receiverRtgsMemberCode(sssTransactionTemp.getReceiverRtgsMemberCode())
					.receiverRtgsMemberCode(null)
					.delivererRtgsMemberCode(sssTransactionTemp.getDelivererRtgsMemberCode())
					.holdIndicator(sssTransactionTemp.getHoldIndicator())
					.paymentType(sssTransactionTemp.getPaymentType())
					.delivererMemberId(sssTransactionTemp.getDelivererMemberId())
					// .receiverMemberId(sssTransactionTemp.getReceiverMemberId())
					.receiverMemberId(null)
					// .receiverAccountId(sssTransactionTemp.getReceiverAccountId())
					.receiverAccountId(null).delivererAccountId(sssTransactionTemp.getDelivererAccountId())
					.senderMemberId(sssTransactionTemp.getSenderMemberId())
					.pdmIndicator(sssTransactionTemp.getPdmIndicator())
					.pendingCancellationIndicator(sssTransactionTemp.getPendingCancellationIndicator())
					.delivererAccountNo(sssTransactionTemp.getDelivererAccountNo())
					// .receiverAccountNo(sssTransactionTemp.getReceiverAccountNo())
					.receiverAccountNo("NULL").msgReceivedTimestamp(sssTransactionTemp.getMsgReceivedTimestamp())
					.submittedCloSettleAmount(0.0).delivererBeneficiaryAccount(" ").accuredInterestAmount(null).build();
			sssIncomingTransactionDAO.save(delivererIncomingTranscation);

			Long random = (long) (Math.random() * 100000L);
			String sdf = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
			String sss_ref = "ST".concat(sdf).concat(random.toString());

			JpaSssIncomingTransaction receiverIncomingTransaction = JpaSssIncomingTransaction.builder()

					// .sssReference(sssTransactionTemp.getSssReference())
					.sssReference(sss_ref)
					// .messageLogId(sssTransactionTemp.getMessageLogId())
					.messageLogId("123").securitiesCode(sssTransactionTemp.getSecuritiesCode())
					.transactionType(sssTransactionTemp.getTransactionType()).status(sssTransactionTemp.getStatus())
					.settlementDate(sssTransactionTemp.getSettlementDate()).tradeDate(sssTransactionTemp.getTradeDate())
					.currencyCode(sssTransactionTemp.getCurrencyCode())
					.settlementAmount(sssTransactionTemp.getSettlementAmount())
					.nominalAmount(sssTransactionTemp.getNominalAmount()).dealPrice(sssTransactionTemp.getDealPrice())
					// .delivererMemberCode(sssTransactionTemp.getDelivererMemberCode())
					.delivererMemberCode("NULL").receiverMemberCode(sssTransactionTemp.getReceiverMemberCode())
					.repoClosingDate(sssTransactionTemp.getRepoClosingDate())
					.repoClosingSettlementAmount(sssTransactionTemp.getRepoClosingSettlementAmount())
					.matchedSssReference(null)
					// .senderMemberCode(sssTransactionTemp.getSenderMemberCode())
					.senderMemberCode("123").senderReference(null).senderType(sssTransactionTemp.getSenderType())
					.createdDate(sssTransactionTemp.getCreatedDate()).modifiedDate(sssTransactionTemp.getModifiedDate())
					.messageType(sssTransactionTemp.getMessageType())
					.receiverRtgsAccount(sssTransactionTemp.getReceiverRtgsAccount()).delivererRtgsAccount(null)
					// .delivererRtgsAccount(sssTransactionTemp.getDelivererRtgsAccount())
					.receiverRtgsMemberCode(sssTransactionTemp.getReceiverRtgsMemberCode())
					.delivererRtgsMemberCode(null)
					// .delivererRtgsMemberCode(sssTransactionTemp.getDelivererRtgsMemberCode())
					.holdIndicator(sssTransactionTemp.getHoldIndicator())
					.paymentType(sssTransactionTemp.getPaymentType())
					// .delivererMemberId(sssTransactionTemp.getDelivererMemberId())
					.delivererMemberId(null).receiverMemberId(sssTransactionTemp.getReceiverMemberId())
					.receiverAccountId(sssTransactionTemp.getReceiverAccountId())
					// .delivererAccountId(ssstransaction.getDelivererAccountId())
					.delivererAccountId(null).senderMemberId(sssTransactionTemp.getSenderMemberId())
					.pdmIndicator(sssTransactionTemp.getPdmIndicator())
					.pendingCancellationIndicator(sssTransactionTemp.getPendingCancellationIndicator())
					// .delivererAccountNo(ssstransaction.getDelivererAccountNo())
					.delivererAccountNo("NULL").receiverAccountNo(sssTransactionTemp.getReceiverAccountNo())
					.msgReceivedTimestamp(sssTransactionTemp.getMsgReceivedTimestamp()).submittedCloSettleAmount(0.0)
					// .delivererBeneficiaryAccount(sssTransactionTemp.getDelivererBeneficiaryAccount())
					.delivererBeneficiaryAccount(" ").accuredInterestAmount(null).build();
			sssIncomingTransactionDAO.save(receiverIncomingTransaction);
		}
	}

}
