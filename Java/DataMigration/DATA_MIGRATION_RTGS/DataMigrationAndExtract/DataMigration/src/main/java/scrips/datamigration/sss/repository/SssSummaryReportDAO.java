package scrips.datamigration.sss.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.SssSummaryReportDto;
import scrips.datamigration.sss.model.JpaSssSecuritiesCode;

@Repository
public interface SssSummaryReportDAO extends JpaRepository<JpaSssSecuritiesCode, String> {
	@Query("select new scrips.datamigration.reports.dto.SssSummaryReportDto('Allotment' as table_name, count(*) as l_count,"
			+ " (select count(*) from JpaSssAllotmentSource i where :date is null or DATE(i.migratedDate)= :date) as s_count) from JpaSssAllotmentSource s inner join JpaSssAllotment l "
			+ "on s.id=l.id where :date is null or DATE(l.createdDate)= :date")
	SssSummaryReportDto getAllotmentCount(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.SssSummaryReportDto('Floating Rate' as table_name, count(*) as l_count,\r\n"
			+ "	(select count(*) from JpaSssFloatingRatesSource i where :date is null or DATE(i.migratedDate)= :date) as s_count) from JpaSssFloatingRatesSource s right join JpaSssFloatingRates l \r\n"
			+ "	on s.referenceRate=l.referenceRate and s.valueDate=l.valueDate and s.migratedDate=l.createdDate and s.remarks is null \r\n"
			+ " where :date is null or DATE(l.createdDate)= :date")
	SssSummaryReportDto getFloatingRatesCount(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.SssSummaryReportDto('Securities Code' as tableName, count(*) as lCount,\r\n"
			+ "	(select count(*) from JpaSssSecuritiesCodeSource i where :date is null or DATE(i.migratedDate)= :date) as sCount) from JpaSssSecuritiesCodeSource s \r\n"
			+ "	 right join JpaSssSecuritiesCodeTemp t on s.id=t.id right join JpaSssSecuritiesCode l \r\n"
			+ "	 on t.securitiesCode=l.securitiesCode and t.remarks is null \r\n"
			+ "	 where :date is null or DATE(l.createdDate)= :date")
	SssSummaryReportDto getSecuritiesCodeCount(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.SssSummaryReportDto('Securities Code Statistics' as tableName, count(*) as lCount,\r\n"
			+ "	(select count(*) from JpaSssSecuritiesCodeStatisticsSource i where :date is null or DATE(i.migratedDate)= :date) as sCount) from JpaSssSecuritiesCodeStatisticsSource s \r\n"
			+ "	 right join JpaSssSecuritiesCodeStatisticsTemp t on s.id=t.id right join JpaSssSecuritiesCodeStatistics l \r\n"
			+ "	 on t.securitiesCode=l.securitiesCode and t.remarks is null \r\n"
			+ "	 where :date is null or DATE(t.migratedDate)= :date")
	SssSummaryReportDto getSecuritiesCodeStatisticsCount(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.SssSummaryReportDto('Securities Price' as tableName, count(*) as lCount,\r\n"
			+ "	(select count(*) from JpaSssSecuritiesPriceSource i where :date is null or DATE(i.migratedDate)= :date) as sCount) from JpaSssSecuritiesPriceSource s \r\n"
			+ "	 right join JpaSssSecuritiesPriceTemp t on s.id=t.id right join JpaSssSecuritiesPrice l \r\n"
			+ "	 on t.securitiesCode=l.securitiesCode and s.migratedDate=l.createdDate and t.remarks is null \r\n"
			+ "	 where :date is null or DATE(l.createdDate)= :date")
	SssSummaryReportDto getSecuritiesPriceCount(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.SssSummaryReportDto('Securities Position Stats' as tableName, count(*) as lCount,\r\n"
			+ "	(select count(*) from JpaSssSecuritiesPositionStatsSource i where :date is null or DATE(i.migratedDate)= :date) as sCount) from JpaSssSecuritiesPositionStatsSource s \r\n"
			+ "	 right join JpaSssSecuritiesPositionStatsTemp t on s.id=t.id right join JpaSssSecuritiesPositionStats l \r\n"
			+ "	 on t.securitiesCode=l.securitiesCode and t.remarks is null \r\n"
			+ "	 where :date is null or DATE(t.migratedDate)= :date")
	SssSummaryReportDto getSecuritiesPositionStatsCount(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.SssSummaryReportDto('Transaction' as tableName, count(*) as lCount,\r\n"
			+ "	(select count(*) from JpaSssTransactionSource i where :date is null or DATE(i.migratedDate)= :date) as sCount) from JpaSssTransactionSource s \r\n"
			+ "	 right join JpaSssTransactionTemp t on s.id=t.id right join JpaSssTransaction l \r\n"
			+ "	 on t.sssReference=l.sssReference and t.remarks is null \r\n"
			+ "	 where :date is null or DATE(t.migratedDate)= :date")
	SssSummaryReportDto getTransactionCount(@Param("date") Date date);

//
//	@Query("select new scrips.datamigration.reports.dto.SssSummaryReportDto('Cbm Gl Account' as table_name, count(*) as l_count,\r\n"
//			+ "	(select count(*) from JpaCbmGlAccountSource) as s_count) from JpaCbmGlAccountSource s inner join JpaCbmGlAccount l \r\n"
//			+ "	on s.glAccount=l.glAccount  and s.migratedDate=l.createdDate and s.remarks is null")
//	SssSummaryReportDto getCbmGlAccount();
//
//	@Query("select new scrips.datamigration.reports.dto.SssSummaryReportDto('Cbm Sora Rate' as table_name, count(*) as l_count,\r\n"
//			+ "	(select count(*) from JpaCbmSoraRateSource) as s_count) from JpaCbmSoraRateSource s right join JpaCbmSoraRate l \r\n"
//			+ "	on s.referenceRate=l.referenceRate and s.valueDate=l.valueDate and s.migratedDate=l.createdDate and s.remarks is null")
//	SssSummaryReportDto getCbmSoraRate();
//	
//
//	@Query("select new scrips.datamigration.reports.dto.SssSummaryReportDto('Cbm Liabilities Base' as table_name, count(*) as l_count,\r\n"
//			+ "	(select count(*) from JpaCbmLiabilitiesBaseSource) as s_count) from JpaCbmLiabilitiesBaseSource s"
//			+ " inner join JpaCbmLiabilitiesBaseTemp t on s.id=t.id inner join JpaCbmLiabilitiesBase l on t.memberId=l.memberId and s.migratedDate=l.createdDate and s.remarks is null")
//	SssSummaryReportDto getCbmLiabilitiesBase();
//
//	@Query("select new scrips.datamigration.reports.dto.SssSummaryReportDto('Cbm Liabilities Base Detail' as table_name, count(*) as l_count,\r\n"
//			+ "	(select count(*) from JpaCbmLiabilitiesBaseDetailSource) as s_count) from JpaCbmLiabilitiesBaseDetailSource s"
//			+ " inner join JpaCbmLiabilitiesBaseDetailTemp t on s.id=t.id inner join JpaCbmLiabilitiesBaseDetail l \r\n"
//			+ " on t.liveTableId=l.id and s.remarks is null")
//	SssSummaryReportDto getCbmLiabilitiesBaseDetail();
}
