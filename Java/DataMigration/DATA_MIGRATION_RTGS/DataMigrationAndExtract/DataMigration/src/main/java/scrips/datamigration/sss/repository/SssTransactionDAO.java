package scrips.datamigration.sss.repository;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.JpaSssTransactionDto;
import scrips.datamigration.sss.model.JpaSssTransaction;
@Repository
public interface SssTransactionDAO extends JpaRepository<JpaSssTransaction, String>{

	JpaSssTransaction findBySssReference(String sssReference);

	@Query("select new scrips.datamigration.reports.dto.JpaSssTransactionDto( case when x.sssReference is null then '' else x.sssReference end AS x_sss_reference,\r\n"
			+ " case when y.sssReference is null then '' else y.sssReference end AS y_sss_reference,\r\n"
			+ " case when z.sssReference is null then '' else  z.sssReference end AS z_sss_reference,\r\n"
			+ " case when x.messageLogId is null then '' else x.messageLogId end AS x_message_log_id,\r\n"
			+ " case when y.messageLogId is null then '' else y.messageLogId end AS y_message_log_id,\r\n"
			+ " case when z.messageLogId is null then '' else z.messageLogId end AS z_message_log_id,\r\n"
			+ " case when x.securitiesCode is null then '' else x.securitiesCode end AS x_securities_code,\r\n"
			+ " case when y.securitiesCode is null then '' else y.securitiesCode end AS y_securities_code,\r\n"
			+ " case when z.securitiesCode is null then '' else z.securitiesCode end AS z_securities_code,\r\n"
			+ " case when x.transactionType is null then '' else x.transactionType end AS x_transaction_type,\r\n"
			+ " case when y.transactionType is null then '' else y.transactionType end AS y_transaction_type,\r\n"
			+ " case when z.transactionType is null then '' else z.transactionType end AS z_transaction_type,\r\n"
			+ " case when x.status is null then '' else x.status end AS x_status,\r\n"
			+ " case when y.status is null then '' else y.status end AS y_status,\r\n"
			+ " case when z.status is null then '' else z.status end AS z_status,\r\n"
			+ " case when x.settlementDate is null then '' else x.settlementDate end AS x_settlement_date,\r\n"
			+ " case when y.settlementDate is null then '' else y.settlementDate end AS ysettlement_date,\r\n"
			+ " case when z.settlementDate is null then '' else z.settlementDate end AS z_settlement_date,\r\n"
			+ " case when x.tradeDate is null then '' else x.tradeDate end AS x_trade_date,\r\n"
			+ " case when y.tradeDate is null then '' else y.tradeDate end AS x_trade_date,\r\n"
			+ " case when z.tradeDate is null then '' else z.tradeDate end AS x_trade_date,\r\n"
			+ " case when x.currencyCode is null then '' else x.currencyCode end AS x_currency_code,\r\n"
			+ " case when y.currencyCode is null then '' else y.currencyCode end AS y_currency_code,\r\n"
			+ " case when z.currencyCode is null then '' else z.currencyCode end AS z_currency_code,\r\n"
			+ " case when x.settlementAmount is null then '' else x.settlementAmount end AS x_settlement_amount,\r\n"
			+ " case when y.settlementAmount is null then '' else y.settlementAmount end AS y_settlement_amount,\r\n"
			+ " case when z.settlementAmount is null then '' else z.settlementAmount end AS z_settlement_amount,\r\n"
			+ " case when x.nominalAmount is null then '' else x.nominalAmount end AS x_nominal_amount,\r\n"
			+ " case when y.nominalAmount is null then '' else y.nominalAmount end AS y_nominal_amount,\r\n"
			+ " case when z.nominalAmount is null then '' else z.nominalAmount end AS z_nominal_amount,\r\n"
			+ " case when x.dealPrice is null then '' else x.dealPrice end AS x_deal_price,\r\n"
			+ " case when y.dealPrice is null then '' else y.dealPrice end AS y_deal_price,\r\n"
			+ " case when z.dealPrice is null then '' else z.dealPrice end AS z_deal_price,\r\n"
			+ " case when x.delivererMemberCode is null then '' else x.delivererMemberCode end AS x_deliverer_member_code,\r\n"
			+ " case when y.delivererMemberCode is null then '' else y.delivererMemberCode end AS y_deliverer_member_code,\r\n"
			+ " case when z.delivererMemberCode is null then '' else z.delivererMemberCode end AS z_deliverer_member_code,\r\n "
			+ " case when x.receiverMemberCode is null then '' else x.receiverMemberCode end AS x_receiver_member_code,\r\n"
			+ " case when y.receiverMemberCode is null then '' else y.receiverMemberCode end AS y_receiver_member_code,\r\n"
			+ " case when z.receiverMemberCode is null then '' else z.receiverMemberCode end AS z_receiver_member_code,\r\n "
			+ " case when x.delivererRtgsMemberCode is null then '' else x.delivererRtgsMemberCode end AS x_receiver_rtgs_member_code,\r\n"
			+ " case when y.delivererRtgsMemberCode is null then '' else y.delivererRtgsMemberCode end AS y_receiver_rtgs_member_code,\r\n"
			+ " case when z.delivererRtgsMemberCode is null then '' else z.delivererRtgsMemberCode end AS z_receiver_rtgs_member_code,\r\n "
			+ " case when x.receiverRtgsMemberCode is null then '' else x.receiverRtgsMemberCode end AS x_deliverer_rtgs_member_code,\r\n"
			+ " case when y.receiverRtgsMemberCode is null then '' else y.receiverRtgsMemberCode end AS y_deliverer_rtgs_member_code,\r\n"
			+ " case when z.receiverRtgsMemberCode is null then '' else z.receiverRtgsMemberCode end AS z_deliverer_rtgs_member_code,\r\n"
			+ " case when x.delivererMemberId is null then '' else x.delivererMemberId end AS x_deliverer_member_id,\r\n"
			+ " case when y.delivererMemberId is null then '' else y.delivererMemberId end AS y_deliverer_member_id,\r\n"
			+ " case when z.delivererMemberId is null then '' else z.delivererMemberId end AS z_deliverer_member_id,\r\n "
			+ " case when x.receiverMemberId is null then '' else x.receiverMemberId end AS x_receiver_member_id,\r\n"
			+ " case when y.receiverMemberId is null then '' else y.receiverMemberId end AS y_receiver_member_id,\r\n"
			+ " case when z.receiverMemberId is null then '' else z.receiverMemberId end AS z_receiver_member_id,\r\n "
			+ " case when x.delivererAccountId is null then '' else x.delivererAccountId end AS x_deliverer_account_id,\r\n"
			+ " case when y.delivererAccountId is null then '' else y.delivererAccountId end AS y_deliverer_account_id,\r\n"
			+ " case when z.delivererAccountId is null then '' else z.delivererAccountId end AS z_deliverer_account_id,\r\n"
			+ " case when x.receiverAccountId is null then '' else x.receiverAccountId end AS x_receiver_account_id,\r\n"
			+ " case when y.receiverAccountId is null then '' else y.receiverAccountId end AS y_receiver_account_id,\r\n"
			+ " case when z.receiverAccountId is null then '' else z.receiverAccountId end AS z_receiver_account_id,\r\n"
			+ " case when x.delivererRtgsAccount is null then '' else x.delivererRtgsAccount end AS x_deliverer_rtgs_account,\r\n"
			+ " case when y.delivererRtgsAccount is null then '' else y.delivererRtgsAccount end AS y_deliverer_rtgs_account,\r\n"
			+ " case when z.delivererRtgsAccount is null then '' else z.delivererRtgsAccount end AS z_deliverer_rtgs_account,\r\n"
			+ " case when x.receiverRtgsAccount is null then '' else x.receiverRtgsAccount end AS x_receiver_rtgs_account,\r\n"
			+ " case when y.receiverRtgsAccount is null then '' else y.receiverRtgsAccount end AS y_receiver_rtgs_account,\r\n"
			+ " case when z.receiverRtgsAccount is null then '' else z.receiverRtgsAccount end AS z_receiver_rtgs_account,\r\n"
			+ " case when x.repoClosingDate is null then '' else x.repoClosingDate end AS x_repo_closing_date,\r\n"
			+ " case when y.repoClosingDate is null then '' else y.repoClosingDate end AS y_repo_closing_date,\r\n"
			+ " case when z.repoClosingDate is null then '' else z.repoClosingDate end AS z_repo_closing_date,\r\n"
			+ " case when x.repoClosingSettlementAmount is null then '' else x.repoClosingSettlementAmount end AS x_repo_closing_settlement_amount,\r\n"
			+ " case when y.repoClosingSettlementAmount is null then '' else y.repoClosingSettlementAmount end AS y_repo_closing_settlement_amount,\r\n"
			+ " case when z.repoClosingSettlementAmount is null then '' else z.repoClosingSettlementAmount end AS z_repo_closing_settlement_amount,\r\n"
			+ " case when x.reasonCode is null then '' else x.reasonCode end AS x_reason_code,\r\n"
			+ " case when y.reasonCode is null then '' else y.reasonCode end AS y_reason_code,\r\n"
			+ " case when z.reasonCode is null then '' else z.reasonCode end AS z_reason_code,\r\n"			
			+ " case when x.processedDate is null then '' else x.processedDate end AS x_processed_date,\r\n"
			+ " case when y.processedDate is null then '' else y.processedDate end AS y_processed_date,\r\n"
			+ " case when z.processedDate is null then '' else z.processedDate end AS z_processed_date,\r\n"
			+ " case when x.createdDate is null then '' else x.createdDate end AS x_created_date,\r\n"
			+ " case when y.createdDate is null then '' else y.createdDate end AS y_created_date,\r\n"
			+ " case when z.createdDate is null then '' else z.createdDate end AS z_created_date,\r\n"
			+ " case when x.modifiedDate is null then '' else x.modifiedDate end AS x_modified_date,\r\n"
			+ " case when y.modifiedDate is null then '' else y.modifiedDate end AS y_modified_date,\r\n"
			+ " case when z.modifiedDate is null then '' else z.modifiedDate end AS z_modified_date,\r\n"
			+ " case when x.transactionReference is null then '' else x.transactionReference end AS x_transaction_reference,\r\n"
			+ " case when y.transactionReference is null then '' else y.transactionReference end AS y_transaction_reference,\r\n"
			+ " case when z.transactionReference is null then '' else z.transactionReference end AS z_transaction_reference,\r\n"
			+ " case when x.senderMemberSwiftCode is null then '' else x.senderMemberSwiftCode end AS x_sender_member_swift_code,\r\n"
			+ " case when y.senderMemberSwiftCode is null then '' else y.senderMemberSwiftCode end AS y_sender_member_swift_code,\r\n"
			+ " case when z.senderMemberSwiftCode is null then '' else z.senderMemberSwiftCode end AS z_sender_member_swift_code,\r\n"			
			+ " case when x.payerMemberSwiftCode is null then '' else x.payerMemberSwiftCode end AS x_payer_member_swift_code,\r\n"
			+ " case when y.payerMemberSwiftCode is null then '' else y.payerMemberSwiftCode end AS y_payer_member_swift_code,\r\n"
			+ " case when z.payerMemberSwiftCode is null then '' else z.payerMemberSwiftCode end AS z_payer_member_swift_code,\r\n"
			+ " case when x.payeeMemberSwiftCode is null then '' else x.payeeMemberSwiftCode end AS x_payee_member_swift_code,\r\n"
			+ " case when y.payeeMemberSwiftCode is null then '' else y.payeeMemberSwiftCode end AS y_payee_member_swift_code,\r\n"
			+ " case when z.payeeMemberSwiftCode is null then '' else z.payeeMemberSwiftCode end AS z_payee_member_swift_code,\r\n"
			+ " case when x.receiverMemberSwiftCode is null then '' else x.receiverMemberSwiftCode end AS x_receiver_member_swift_code,\r\n"
			+ " case when y.receiverMemberSwiftCode is null then '' else y.receiverMemberSwiftCode end AS y_receiver_member_swift_code,\r\n"
			+ " case when z.receiverMemberSwiftCode is null then '' else z.receiverMemberSwiftCode end AS z_receiver_member_swift_code,\r\n"
			+ " case when x.senderMemberCode is null then '' else x.senderMemberCode end AS x_sender_member_code,\r\n"
			+ " case when y.senderMemberCode is null then '' else y.senderMemberCode end AS y_sender_member_code,\r\n"
			+ " case when z.senderMemberCode is null then '' else z.senderMemberCode end AS z_sender_member_code,\r\n"
			+ " case when x.senderType is null then '' else x.senderType end AS x_sender_type,\r\n"
			+ " case when y.senderType is null then '' else y.senderType end AS y_sender_type,\r\n"
			+ " case when z.senderType is null then '' else z.senderType end AS z_sender_type,\r\n"	
			+ " case when x.accuredInterestAmount is null then '' else x.accuredInterestAmount end AS x_accured_interest_amount,\r\n"
			+ " case when y.accuredInterestAmount is null then '' else y.accuredInterestAmount end AS y_accured_interest_amount,\r\n"
			+ " case when z.accuredInterestAmount is null then '' else z.accuredInterestAmount end AS z_accured_interest_amount,\r\n"
			+ " case when z.remarks is null then case when y.remarks is null then '' else y.remarks end else z.remarks end AS y_remark)\r\n"
			+ "FROM JpaSssTransactionSource z left join\r\n"
			+ " JpaSssTransactionTemp y on z.id=y.id left join\r\n"
			+ " JpaSssTransaction x on y.sssReference=x.sssReference AND y.remarks is null where :date is null or DATE(z.migratedDate)= :date \r\n"
			+ "ORDER BY z.seq asc")
	List<JpaSssTransactionDto> getReportData(@Param("date") Date date);

	@Query("select count(*) from JpaSssTransactionSource s inner join  JpaSssTransactionTemp t on s.id=t.id inner join JpaSssTransaction l on l.sssReference=t.sssReference and t.remarks is null where :date is null or DATE(t.migratedDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);
	
}
