package scrips.datamigration.sss.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.JpaSssSecuritiesCodeStatisticsDto;
import scrips.datamigration.sss.model.JpaSssSecuritiesCodeStatistics;
@Repository
public interface SssSecuritiesCodeStatisticsDAO extends JpaRepository<JpaSssSecuritiesCodeStatistics, String>{

	JpaSssSecuritiesCodeStatistics findBySecuritiesCode(String securitiesCode);

	@Query("select new scrips.datamigration.reports.dto.JpaSssSecuritiesCodeStatisticsDto( case when x.securitiesCode is null then '' else x.securitiesCode end AS x_securities_code,\r\n"
			+ " case when y.securitiesCode is null then '' else y.securitiesCode end AS y_securities_code,\r\n"
			+ " case when z.securitiesCode is null then '' else  z.securitiesCode end AS z_securities_code,\r\n"
			+ " case when x.totalRedeemedAmount is null then '' else x.totalRedeemedAmount end AS x_total_redeemed_amount,\r\n"
			+ " case when y.totalRedeemedAmount is null then '' else y.totalRedeemedAmount end AS y_total_redeemed_amount,\r\n"
			+ " case when z.totalRedeemedAmount is null then '' else z.totalRedeemedAmount end AS z_total_redeemed_amount,\r\n"
			+ " case when x.totalNominalAmountIssuedForErf is null then '' else x.totalNominalAmountIssuedForErf end AS x_total_nominal_amount_issued_for_erf,\r\n"
			+ " case when y.totalNominalAmountIssuedForErf is null then '' else y.totalNominalAmountIssuedForErf end AS y_total_nominal_amount_issued_for_erf,\r\n"
			+ " case when z.totalNominalAmountIssuedForErf is null then '' else z.totalNominalAmountIssuedForErf end AS z_total_nominal_amount_issued_for_erf,\r\n" 
			+ " case when x.totalRedeemedAmountForErf is null then '' else x.totalRedeemedAmountForErf end AS x_total_redeemed_amount_for_erf,\r\n"
			+ " case when y.totalRedeemedAmountForErf is null then '' else y.totalRedeemedAmountForErf end AS y_total_redeemed_amount_for_erf,\r\n"
			+ " case when z.totalRedeemedAmountForErf is null then '' else z.totalRedeemedAmountForErf end AS z_total_redeemed_amount_for_erf,\r\n" 
			+ " case when x.outstandingNominalAmount is null then '' else x.outstandingNominalAmount end AS x_outstanding_nominal_amount,\r\n"
			+ " case when y.outstandingNominalAmount is null then '' else y.outstandingNominalAmount end AS y_outstanding_nominal_amount,\r\n"
			+ " case when z.outstandingNominalAmount is null then '' else z.outstandingNominalAmount end AS z_outstanding_nominal_amount,\r\n"
			+ " case when x.nextCouponDate is null then '' else x.nextCouponDate end AS x_next_coupon_date,\r\n"
			+ " case when y.nextCouponDate is null then '' else y.nextCouponDate end AS y_next_coupon_date,\r\n"
			+ " case when z.nextCouponDate is null then '' else z.nextCouponDate end AS z_next_coupon_date,\r\n "
			+ " case when x.lastCouponDate is null then '' else x.lastCouponDate end AS x_last_coupon_date,\r\n"
			+ " case when y.lastCouponDate is null then '' else y.lastCouponDate end AS y_last_coupon_date,\r\n"
			+ " case when z.lastCouponDate is null then '' else z.lastCouponDate end AS z_last_coupon_date,\r\n"
			+ " case when x.nextIndexEndDate is null then '' else x.nextIndexEndDate end AS x_next_index_end_date,\r\n"
			+ " case when y.nextIndexEndDate is null then '' else y.nextIndexEndDate end AS y_next_index_end_date,\r\n"
			+ " case when z.nextIndexEndDate is null then '' else z.nextIndexEndDate end AS z_next_index_end_date,\r\n" 
			+ " case when x.amountAllotedToCentralBank is null then '' else x.amountAllotedToCentralBank end AS x_amount_allotted_to_central_bank,\r\n"
			+ " case when y.amountAllotedToCentralBank is null then '' else y.amountAllotedToCentralBank end AS y_amount_allotted_to_central_bank,\r\n"
			+ " case when z.amountAllotedToCentralBank is null then '' else z.amountAllotedToCentralBank end AS z_amount_allotted_to_central_bank,\r\n"
			+ " case when x.amountAllotedToOthers is null then '' else x.amountAllotedToOthers end AS x_amount_allotted_to_others,\r\n"
			+ " case when y.amountAllotedToOthers is null then '' else y.amountAllotedToOthers end AS y_amount_allotted_to_others,\r\n"
			+ " case when z.amountAllotedToOthers is null then '' else z.amountAllotedToOthers end AS z_amount_allotted_to_others,\r\n "
			+ " case when z.remarks is null then case when y.remarks is null then '' else y.remarks end else z.remarks end AS y_remark)\r\n"
			+ "FROM JpaSssSecuritiesCodeStatisticsSource z  left join\r\n"
			+ " JpaSssSecuritiesCodeStatisticsTemp y on z.id=y.id left join\r\n"
			+ " JpaSssSecuritiesCodeStatistics x on x.securitiesCode=z.securitiesCode AND y.remarks is null where :date is null or DATE(z.migratedDate)= :date \r\n"
			+ "ORDER BY z.seq asc")
	List<JpaSssSecuritiesCodeStatisticsDto> getReportData(@Param("date") Date date);
	@Query("select count(*) from JpaSssSecuritiesCodeStatisticsSource s inner join  JpaSssSecuritiesCodeStatisticsTemp t on s.id=t.id inner join JpaSssSecuritiesCodeStatistics l on l.securitiesCode=t.securitiesCode and t.remarks is null where :date is null or DATE(t.migratedDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);

}
