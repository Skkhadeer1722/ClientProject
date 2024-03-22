package scrips.datamigration.rtgs.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.ControlTotalReportDto;
import scrips.datamigration.rtgs.model.JpaAccount;

@Repository
public interface ControlTotalReportDAO extends JpaRepository<JpaAccount, String> {

	@Query("select new scrips.datamigration.reports.dto.ControlTotalReportDto('Account' as table_name, count(*) as l_count,"
			+ " (select count(*) from JpaAccountSource i where :date is null or DATE(i.migratedDate)= :date) as s_count) from JpaAccountSource s right join JpaAccountTemp t on s.id=t.id right join JpaAccount l "
			+ "on t.id=l.id  and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	ControlTotalReportDto getAccountCount(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.ControlTotalReportDto('Account Position' as table_name, count(*) as l_count,\r\n"
			+ "	(select count(*) from JpaAccountPositionSource i where :date is null or DATE(i.migratedDate)= :date) as s_count) from JpaAccountPositionSource s right join JpaAccountPositionTemp t on s.id=t.id right join JpaAccountPosition l \r\n"
			+ "	on t.accountId=l.accountId and t.remarks is null where :date is null or DATE(l.modifiedDate)= :date")
	ControlTotalReportDto getAccountPositionCount(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.ControlTotalReportDto('Cbm Deposit Rate' as table_name, count(*) as l_count,\r\n"
			+ "	(select count(*) from JpaCbmDepositRateSource i where :date is null or DATE(i.migratedDate)= :date) as s_count) from JpaCbmDepositRateSource s right join JpaCbmDepositRateTemp t on s.id=t.id right join JpaCbmDepositRate l \r\n"
			+ "	on t.id=l.id and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	ControlTotalReportDto getCbmDepositRateCount(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.ControlTotalReportDto('Cbm Cost Centre' as table_name, count(*) as l_count,\r\n"
			+ "	(select count(*) from JpaCbmCostCentreSource i where :date is null or DATE(i.migratedDate)= :date) as s_count) from JpaCbmCostCentreSource s right join JpaCbmCostCentreTemp t on s.id=t.id right join JpaCbmCostCentre l \r\n"
			+ "	on t.costCentre=l.costCentre and t.remarks is null  where :date is null or DATE(l.createdDate)= :date")
	ControlTotalReportDto getCbmCostCentre(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.ControlTotalReportDto('Cbm Gl Account' as table_name, count(*) as l_count,\r\n"
			+ "	(select count(*) from JpaCbmGlAccountSource i where :date is null or DATE(i.migratedDate)= :date) as s_count) from JpaCbmGlAccountSource s  right join JpaCbmGlAccountTemp t on s.id=t.id right join JpaCbmGlAccount l \r\n"
			+ "	on t.glAccount=l.glAccount and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	ControlTotalReportDto getCbmGlAccount(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.ControlTotalReportDto('Cbm Sora Rate' as table_name, count(*) as l_count,\r\n"
			+ "	(select count(*) from JpaCbmSoraRateSource i where :date is null or DATE(i.migratedDate)= :date) as s_count) from JpaCbmSoraRateSource s right join JpaCbmSoraRateTemp t on s.id=t.id right join JpaCbmSoraRate l \r\n"
			+ "	on t.referenceRate=l.referenceRate and t.valueDate=l.valueDate and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	ControlTotalReportDto getCbmSoraRate(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.ControlTotalReportDto('Cbm Liabilities Base' as table_name, count(*) as l_count,\r\n"
			+ "	(select count(*) from JpaCbmLiabilitiesBaseSource i where :date is null or DATE(i.migratedDate)= :date) as s_count) from JpaCbmLiabilitiesBaseSource s"
			+ " right join JpaCbmLiabilitiesBaseTemp t on s.id=t.id right join JpaCbmLiabilitiesBase l on t.memberId=l.memberId and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	ControlTotalReportDto getCbmLiabilitiesBase(@Param("date") Date date);

	@Query("select new scrips.datamigration.reports.dto.ControlTotalReportDto('Cbm Liabilities Base Detail' as table_name, count(*) as l_count,\r\n"
			+ "	(select count(*) from JpaCbmLiabilitiesBaseDetailSource i where :date is null or DATE(i.migratedDate)= :date) as s_count) from JpaCbmLiabilitiesBaseDetailSource s"
			+ " right join JpaCbmLiabilitiesBaseDetailTemp t on s.id=t.id right join JpaCbmLiabilitiesBaseDetail l \r\n"
			+ " on t.liveTableId=l.id and t.remarks is null where :date is null or DATE(t.migratedDate)= :date")
	ControlTotalReportDto getCbmLiabilitiesBaseDetail(@Param("date") Date date);

}
