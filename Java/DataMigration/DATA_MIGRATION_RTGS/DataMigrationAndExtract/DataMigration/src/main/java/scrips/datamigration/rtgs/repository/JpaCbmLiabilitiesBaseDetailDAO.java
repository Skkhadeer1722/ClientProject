package scrips.datamigration.rtgs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import scrips.datamigration.reports.dto.CbmLiabilitiesBaseDetailReportDto;
import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBaseDetail;

public interface JpaCbmLiabilitiesBaseDetailDAO extends JpaRepository<JpaCbmLiabilitiesBaseDetail, String>{

	List<JpaCbmLiabilitiesBaseDetail> findByMemberId(String memberId);


	@Query("select new scrips.datamigration.reports.dto.CbmLiabilitiesBaseDetailReportDto( case when x.id is null then '' else x.id end AS x_id,\r\n"
			+ " case when y.id is null then '' else y.id end AS y_id,\r\n"
			+ " case when z.id is null then '' else  z.id end AS z_id,\r\n"
			+ " case when x.memberId is null then '' else x.memberId end AS x_member_id,\r\n"
			+ " case when y.memberId is null then '' else y.memberId end AS y_member_id,\r\n"
			+ " case when z.memberId is null then '' else z.memberId end AS z_member_id,\r\n"
			+ " case when x.startDate is null then '' else x.startDate end AS x_start_date,\r\n"
			+ " case when y.startDate is null then '' else y.startDate end AS y_start_date,\r\n"
			+ " case when z.startDate is null then '' else z.startDate end AS z_start_date,\r\n" 
			+ " case when x.endDate is null then '' else x.endDate end AS x_end_date,\r\n"
			+ " case when y.endDate is null then '' else y.endDate end AS y_end_date,\r\n"
			+ " case when z.endDate is null then '' else z.endDate end AS z_end_date,\r\n"
			+ " case when x.qlLb is null then '' else x.qlLb end AS x_ql_lb,\r\n"
			+ " case when y.qlLb is null then '' else y.qlLb end AS y_ql_lb,\r\n"
			+ " case when z.qlLb is null then '' else z.qlLb end AS z_ql_lb, "
			+ " case when z.remarks is null then case when y.remarks is null then '' else y.remarks end else z.remarks end AS y_remark)\r\n"
			+ "FROM JpaCbmLiabilitiesBaseDetailSource z  left join\r\n"
			+ " JpaCbmLiabilitiesBaseDetailTemp y on z.id=y.id left join\r\n"
			+ " JpaCbmLiabilitiesBaseDetail x on y.liveTableId=x.id AND y.remarks is null where :date is null or DATE(z.migratedDate)=:date \r\n"
			+ "ORDER BY z.seq asc")
	List<CbmLiabilitiesBaseDetailReportDto> getReportData(@Param("date") Date date);

	@Query("select count(*) from JpaCbmLiabilitiesBaseDetailTemp t inner join JpaCbmLiabilitiesBaseDetail l on t.liveTableId=l.id and t.remarks is null where :date is null or DATE(t.migratedDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);
}
