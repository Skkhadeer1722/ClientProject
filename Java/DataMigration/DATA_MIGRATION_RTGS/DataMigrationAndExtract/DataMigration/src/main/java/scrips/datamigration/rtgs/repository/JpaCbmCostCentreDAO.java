package scrips.datamigration.rtgs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import scrips.datamigration.reports.dto.CbmCostCentreReportDto;
import scrips.datamigration.rtgs.model.JpaCbmCostCentre;

public interface JpaCbmCostCentreDAO extends JpaRepository<JpaCbmCostCentre, String> {

	JpaCbmCostCentre findByCostCentre(String costCentre);

	@Query("select new scrips.datamigration.reports.dto.CbmCostCentreReportDto( case when x.costCentre is null then '' else x.costCentre end AS x_cost_centre,\r\n"
			+ " case when y.costCentre is null then '' else y.costCentre end AS y_cost_centre,\r\n"
			+ " case when z.costCentre is null then '' else  z.costCentre end AS z_cost_centre,\r\n"
			+ " case when x.description is null then '' else x.description end AS x_description,\r\n"
			+ " case when y.description is null then '' else y.description end AS y_description,\r\n"
			+ " case when z.description is null then '' else z.description end AS z_description,\r\n"
			+ " case when x.createdDate is null then '' else x.createdDate end AS x_created_date,\r\n"
			+ " case when y.createdDate is null then '' else y.createdDate end AS y_created_date,\r\n"
			+ " case when z.createdDate is null then '' else z.createdDate end AS z_created_date,\r\n"
			+ " case when x.modifiedDate is null then '' else x.modifiedDate end AS x_modified_date,\r\n"
			+ " case when y.modifiedDate is null then '' else y.modifiedDate end AS y_modified_date,\r\n"
			+ " case when z.modifiedDate is null then '' else z.modifiedDate end AS z_modified_date, "
			+ " case when z.remarks is null then case when y.remarks is null then '' else y.remarks end else z.remarks end AS y_remark)\r\n"
			+ "FROM JpaCbmCostCentreSource z  left join\r\n" + " JpaCbmCostCentreTemp y on z.id=y.id left join\r\n"
			+ " JpaCbmCostCentre x on z.costCentre=x.costCentre AND y.remarks is null where :date is null or DATE(z.migratedDate)= :date \r\n"
			+ "ORDER BY z.seq asc")
	List<CbmCostCentreReportDto> getReportData(@Param("date") Date date);

	@Query("select count(*) from JpaCbmCostCentreTemp t inner join JpaCbmCostCentre l on t.costCentre=l.costCentre and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);
}
