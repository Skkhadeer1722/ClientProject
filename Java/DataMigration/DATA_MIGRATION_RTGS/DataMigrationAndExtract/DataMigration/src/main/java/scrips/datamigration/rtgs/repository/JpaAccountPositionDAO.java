package scrips.datamigration.rtgs.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.JpaAccountPositionReportDto;
import scrips.datamigration.rtgs.model.JpaAccountPosition;
@Transactional
@Repository
public interface JpaAccountPositionDAO extends JpaRepository<JpaAccountPosition, Long>{

	JpaAccountPosition findByAccountId(String accountId);
	
	
	@Query("select new scrips.datamigration.reports.dto.JpaAccountPositionReportDto(case when x.accountId is null then '' else x.accountId end AS x_account_id,\r\n"
			+ " case when y.accountId is null then '' else y.accountId end AS y_account_id,\r\n"
			+ " case when z.accountId is null then '' else  z.accountId end AS z_account_id,\r\n"
			+ " case when x.currencyCode is null then '' else x.currencyCode end AS x_currency_code,\r\n"
			+ " case when y.currencyCode is null then '' else y.currencyCode end AS y_currency_code,\r\n"
			+ " case when z.currencyCode is null then '' else z.currencyCode end AS z_currency_code,\r\n"
			+ " case when x.memberId is null then '' else x.memberId end AS x_member_id,\r\n"
			+ " case when y.memberId is null then '' else y.memberId end AS y_member_id,\r\n"
			+ " case when z.memberId is null then '' else z.memberId end AS z_member_id,\r\n" 
			+ " case when x.currentAccountBalance is null then '' else x.currentAccountBalance end AS x_current_account_balance,\r\n"
			+ " case when y.currentAccountBalance is null then '' else y.currentAccountBalance end AS y_current_account_balance,\r\n"
			+ " case when z.currentAccountBalance is null then '' else z.currentAccountBalance end AS z_current_account_balance,\r\n" 
			+ " case when x.openingAccountBalance is null then '' else x.openingAccountBalance end AS x_opening_account_balance,\r\n"
			+ " case when y.openingAccountBalance is null then '' else y.openingAccountBalance end AS y_opening_account_balance,\r\n"
			+ " case when z.openingAccountBalance is null then '' else z.openingAccountBalance end AS z_opening_account_balance,\r\n" 
			+ " case when x.availableBalance is null then '' else x.availableBalance end AS x_available_balance,\r\n"
			+ " case when y.availableBalance is null then '' else y.availableBalance end AS y_available_balance,\r\n"
			+ " case when z.availableBalance is null then '' else  z.availableBalance end AS z_available_balance,\r\n"
			+ " case when x.queueCount is null then '' else x.queueCount end AS x_queue_count,\r\n"
			+ " case when y.queueCount is null then '' else y.queueCount end AS y_queue_count,\r\n"
			+ " case when z.queueCount is null then '' else z.queueCount end AS z_queue_count,\r\n"
			+ " case when x.queueAmount is null then '' else x.queueAmount end AS x_queue_amount,\r\n"
			+ " case when y.queueAmount is null then '' else y.queueAmount end AS y_queue_amount,\r\n"
			+ " case when z.queueAmount is null then '' else z.queueAmount end AS z_queue_amount,\r\n" 
			+ " case when x.settledPaymentsCount is null then '' else x.settledPaymentsCount end AS x_settled_payments_count, \r\n"
			+ " case when y.settledPaymentsCount is null then '' else y.settledPaymentsCount end AS y_settled_payments_count,\r\n"
			+ " case when z.settledPaymentsCount is null then '' else z.settledPaymentsCount end AS z_settled_payments_count,\r\n" 
			+ " case when x.settledPaymentsAmount is null then '' else x.settledPaymentsAmount end AS x_settled_payments_amount,\r\n"
			+ " case when y.settledPaymentsAmount is null then '' else y.settledPaymentsAmount end AS y_settled_payments_amount,\r\n"
			+ " case when z.settledPaymentsAmount is null then '' else z.settledPaymentsAmount end AS z_settled_payments_amount,\r\n"
			+ " case when x.settledReceiptsCount is null then '' else x.settledReceiptsCount end AS x_ssettled_receipts_count,\r\n"
			+ " case when y.settledReceiptsCount is null then '' else y.settledReceiptsCount end AS y_settled_receipts_count,\r\n"
			+ " case when z.settledReceiptsCount is null then '' else z.settledReceiptsCount end AS z_settled_receipts_count,\r\n" 
			+ " case when x.settledReceiptsAmount is null then '' else x.settledReceiptsAmount end AS x_settled_receipts_amount,\r\n"
			+ " case when y.settledReceiptsAmount is null then '' else y.settledReceiptsAmount end AS y_settled_receipts_amount,\r\n"
			+ " case when z.settledReceiptsAmount is null then '' else  z.settledReceiptsAmount end AS z_settled_receipts_amount,\r\n"		
			+ " case when x.createdDate is null then '' else x.createdDate end AS x_created_date,\r\n"
			+ " case when y.createdDate is null then '' else y.createdDate end AS y_created_date,\r\n"
			+ " case when z.createdDate is null then '' else z.createdDate end AS z_created_date,\r\n"	
			+ " case when x.modifiedDate is null then '' else x.modifiedDate end AS x_modified_date,\r\n"
			+ " case when y.modifiedDate is null then '' else y.modifiedDate end AS y_modified_date,\r\n"
			+ " case when z.modifiedDate is null then '' else z.modifiedDate end AS z_modified_date,\r\n" 		
			+ " case when z.remarks is null then case when y.remarks is null then '' else y.remarks end else z.remarks end AS y_remark)\r\n"
			+ "FROM JpaAccountPositionSource z  left join\r\n"
			+ " JpaAccountPositionTemp y on z.id=y.id left join\r\n"
			+ " JpaAccountPosition x on y.accountId=x.accountId AND y.remarks is null  where :date is null or DATE(z.migratedDate)=:date \r\n"
			+ "ORDER BY z.seq asc")
	List<JpaAccountPositionReportDto> getReportData(@Param("date") Date date);

	@Query("select count(*) from JpaAccountPositionTemp t inner join JpaAccountPosition l on t.accountId=l.accountId and t.remarks is null where :date is null or DATE(l.modifiedDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);
}
