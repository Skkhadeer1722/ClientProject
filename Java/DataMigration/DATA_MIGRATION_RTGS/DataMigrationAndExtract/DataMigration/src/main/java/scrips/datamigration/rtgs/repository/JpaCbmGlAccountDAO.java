package scrips.datamigration.rtgs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import scrips.datamigration.reports.dto.CbmGlAccountReportDto;
import scrips.datamigration.rtgs.model.JpaCbmGlAccount;

public interface JpaCbmGlAccountDAO extends JpaRepository<JpaCbmGlAccount,String>{

	JpaCbmGlAccount findByGlAccount(String glAccount);

	@Query("select new scrips.datamigration.reports.dto.CbmGlAccountReportDto( case when x.glAccount is null then '' else x.glAccount end AS x_gl_account,\r\n"
			+ " case when y.glAccount is null then '' else y.glAccount end AS y_gl_account,\r\n"
			+ " case when z.glAccount is null then '' else  z.glAccount end AS z_gl_account,\r\n"
			+ " case when x.glAccountIndicator is null then '' else x.glAccountIndicator end AS x_gl_account_indicator,\r\n"
			+ " case when y.glAccountIndicator is null then '' else y.glAccountIndicator end AS y_gl_account_indicator,\r\n"
			+ " case when z.glAccountIndicator is null then '' else z.glAccountIndicator end AS z_gl_account_indicator,\r\n"
			+ " case when x.glAccountDescription is null then '' else x.glAccountDescription end AS x_gl_account_description,\r\n"
			+ " case when y.glAccountDescription is null then '' else y.glAccountDescription end AS y_gl_account_description,\r\n"
			+ " case when z.glAccountDescription is null then '' else z.glAccountDescription end AS z_gl_account_description,\r\n" 
			+ " case when x.createdDate is null then '' else x.createdDate end AS x_created_date,\r\n"
			+ " case when y.createdDate is null then '' else y.createdDate end AS y_created_date,\r\n"
			+ " case when z.createdDate is null then '' else z.createdDate end AS z_created_date,\r\n"
			+ " case when x.modifiedDate is null then '' else x.modifiedDate end AS x_modified_date,\r\n"
			+ " case when y.modifiedDate is null then '' else y.modifiedDate end AS y_modified_date,\r\n"
			+ " case when z.modifiedDate is null then '' else z.modifiedDate end AS z_modified_date, "
			+ " case when z.remarks is null then case when y.remarks is null then '' else y.remarks end else z.remarks end AS y_remark)\r\n"
			+ "FROM JpaCbmGlAccountSource z  left join\r\n"
			+ " JpaCbmGlAccountTemp y on z.id=y.id left join\r\n"
			+ " JpaCbmGlAccount x on z.glAccount=x.glAccount AND y.remarks is null where :date is null or  DATE(z.migratedDate)=:date \r\n"
			+ "ORDER BY z.seq asc")
	List<CbmGlAccountReportDto> getReportData(@Param("date") Date date);
	@Query("select count(*) from JpaCbmGlAccountTemp t inner join JpaCbmGlAccount l on l.glAccount=t.glAccount and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);
}
