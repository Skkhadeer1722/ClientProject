package scrips.datamigration.sss.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.JpaSssSecuritiesPositionStatsReportDto;
import scrips.datamigration.sss.model.JpaSssSecuritiesPositionStats;
@Repository
public interface SssSecuritiesPositionStatsDAO extends JpaRepository<JpaSssSecuritiesPositionStats,String>{
	
	JpaSssSecuritiesPositionStats findBySecuritiesCode(String securitiesCode);

//	JpaSssSecuritiesPositionStats findByAccountId(String accountId);

	JpaSssSecuritiesPositionStats findBySecuritiesCodeAndAccountId(String securitiesCode, String string);
	@Query("select new scrips.datamigration.reports.dto.JpaSssSecuritiesPositionStatsReportDto( case when x.securitiesCode is null then '' else x.securitiesCode end AS x_securities_code,\r\n"
			+ " case when y.securitiesCode is null then '' else y.securitiesCode end AS y_securities_code,\r\n"
			+ " case when z.securitiesCode is null then '' else  z.securitiesCode end AS z_securities_code,\r\n"
			+ " case when x.valueDate is null then '' else x.valueDate end AS x_value_date,\r\n"
			+ " case when y.valueDate is null then '' else y.valueDate end AS y_value_date,\r\n"
			+ " case when z.valueDate is null then '' else z.valueDate end AS z_value_date,\r\n"
			+ " case when x.openingNominalAmount is null then '' else x.openingNominalAmount end AS x_opening_nominal_amount,\r\n"
			+ " case when y.openingNominalAmount is null then '' else y.openingNominalAmount end AS y_opening_nominal_amount,\r\n"
			+ " case when z.openingNominalAmount is null then '' else z.openingNominalAmount end AS z_opening_nominal_amount,\r\n" 
			+ " case when x.closingNominalAmount is null then '' else x.closingNominalAmount end AS x_closing_nominal_amount,\r\n"
			+ " case when y.closingNominalAmount is null then '' else y.closingNominalAmount end AS y_closing_nominal_amount,\r\n"
			+ " case when z.closingNominalAmount is null then '' else z.closingNominalAmount end AS z_closing_nominal_amount,\r\n" 
			+ " case when x.settledCount is null then '' else x.settledCount end AS x_settled_count,\r\n"
			+ " case when y.settledCount is null then '' else y.settledCount end AS y_settled_count,\r\n"
			+ " case when z.settledCount is null then '' else z.settledCount end AS z_settled_count,\r\n"
			+ " case when x.settledAmount is null then '' else x.settledAmount end AS x_settled_amount,\r\n"
			+ " case when y.settledAmount is null then '' else y.settledAmount end AS y_settled_amount,\r\n"
			+ " case when z.settledAmount is null then '' else z.settledAmount end AS z_settled_amount,\r\n "
			+ " case when x.receiptCount is null then '' else x.receiptCount end AS x_receipt_count,\r\n"
			+ " case when y.receiptCount is null then '' else y.receiptCount end AS y_receipt_count,\r\n"
			+ " case when z.receiptCount is null then '' else z.receiptCount end AS z_receipt_count,\r\n"
			+ " case when x.receiptAmount is null then '' else x.receiptAmount end AS x_receipt_amount,\r\n"
			+ " case when y.receiptAmount is null then '' else y.receiptAmount end AS y_receipt_amount,\r\n"
			+ " case when z.receiptAmount is null then '' else z.receiptAmount end AS z_receipt_amount,\r\n" 
			+ " case when x.queuedCount is null then '' else x.queuedCount end AS x_queued_count,\r\n"
			+ " case when y.queuedCount is null then '' else y.queuedCount end AS y_queued_count,\r\n"
			+ " case when z.queuedCount is null then '' else z.queuedCount end AS z_queued_count,\r\n"
			+ " case when x.queuedAmount is null then '' else x.queuedAmount end AS x_queued_amount,\r\n"
			+ " case when y.queuedAmount is null then '' else y.queuedAmount end AS y_queued_amount,\r\n"
			+ " case when z.queuedAmount is null then '' else z.queuedAmount end AS z_queued_amount,\r\n "
			+ " case when x.rejectedCount is null then '' else x.rejectedCount end AS x_rejected_count,\r\n"
			+ " case when y.rejectedCount is null then '' else y.rejectedCount end AS y_rejected_count,\r\n"
			+ " case when z.rejectedCount is null then '' else z.rejectedCount end AS z_rejected_count,\r\n"
			+ " case when x.rejectedAmount is null then '' else x.rejectedAmount end AS x_rejected_amount,\r\n"
			+ " case when y.rejectedAmount is null then '' else y.rejectedAmount end AS y_rejected_amount,\r\n"
			+ " case when z.rejectedAmount is null then '' else z.rejectedAmount end AS z_rejected_amount,\r\n" 
			+ " case when x.cancelledCount is null then '' else x.cancelledCount end AS x_cancelled_count,\r\n"
			+ " case when y.cancelledCount is null then '' else y.cancelledCount end AS y_cancelled_count,\r\n"
			+ " case when z.cancelledCount is null then '' else z.cancelledCount end AS z_cancelled_count,\r\n"
			+ " case when x.cancelledAmount is null then '' else x.cancelledAmount end AS x_cancelled_amount,\r\n"
			+ " case when y.cancelledAmount is null then '' else y.cancelledAmount end AS y_cancelled_amount,\r\n"
			+ " case when z.cancelledAmount is null then '' else z.cancelledAmount end AS z_cancelled_amount,\r\n "
			+ " case when x.accountId is null then '' else x.accountId end AS x_account_id,\r\n"
			+ " case when y.accountId is null then '' else y.accountId end AS y_account_id,\r\n"
			+ " case when z.accountId is null then '' else z.accountId end AS z_account_id,\r\n "
			+ " case when z.remarks is null then case when y.remarks is null then '' else y.remarks end else z.remarks end AS y_remark)\r\n"
			+ "FROM JpaSssSecuritiesPositionStatsSource z  left join\r\n"
			+ " JpaSssSecuritiesPositionStatsTemp y on z.id=y.id left join\r\n"
			+ " JpaSssSecuritiesPositionStats x on x.securitiesCode=z.securitiesCode AND y.remarks is null where :date is null or DATE(z.migratedDate)= :date \r\n"
			+ "ORDER BY z.seq asc")
	List<JpaSssSecuritiesPositionStatsReportDto> getReportData(@Param("date") Date date);
	@Query("select count(*) from JpaSssSecuritiesPositionStatsSource s inner join  JpaSssSecuritiesPositionStatsTemp t on s.id=t.id inner join JpaSssSecuritiesPositionStats l on l.securitiesCode=t.securitiesCode and t.remarks is null where :date is null or DATE(t.migratedDate)= :date ")
	Integer getMigratedCount(@Param("date") Date date);


}
