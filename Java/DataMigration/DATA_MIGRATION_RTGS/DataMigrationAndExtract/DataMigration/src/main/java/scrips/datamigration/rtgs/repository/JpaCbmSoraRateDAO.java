package scrips.datamigration.rtgs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.CbmSoraRateReportDto;
import scrips.datamigration.rtgs.model.JpaCbmSoraRate;
import scrips.datamigration.rtgs.model.JpaCbmSoraRateId;
@Repository
public interface JpaCbmSoraRateDAO extends JpaRepository<JpaCbmSoraRate, JpaCbmSoraRateId>{

	JpaCbmSoraRate findByReferenceRateAndValueDate(String referenceRate,Integer valueDate);
	@Query("select new scrips.datamigration.reports.dto.CbmSoraRateReportDto( case when x.referenceRate is null then '' else x.referenceRate end AS x_reference_rate,\r\n"
			+ " case when y.referenceRate is null then '' else y.referenceRate end AS y_reference_rate,\r\n"
			+ " case when z.referenceRate is null then '' else  z.referenceRate end AS z_reference_rate,\r\n"
			+ " case when x.valueDate is null then '' else x.valueDate end AS x_value_date,\r\n"
			+ " case when y.valueDate is null then '' else y.valueDate end AS y_value_date,\r\n"
			+ " case when z.valueDate is null then '' else z.valueDate end AS z_value_date,\r\n"
			+ " case when x.publicationDate is null then '' else x.publicationDate end AS x_publication_date,\r\n"
			+ " case when y.publicationDate is null then '' else y.publicationDate end AS y_publication_date,\r\n"
			+ " case when z.publicationDate is null then '' else z.publicationDate end AS z_publication_date,\r\n" 
			+ " case when x.rate is null then '' else x.rate end AS x_rate,\r\n"
			+ " case when y.rate is null then '' else y.rate end AS y_rate,\r\n"
			+ " case when z.rate is null then '' else z.rate end AS z_rate,\r\n" 
			+ " case when x.action is null then '' else x.action end AS x_action,\r\n"
			+ " case when y.action is null then '' else y.action end AS y_action,\r\n"
			+ " case when z.action is null then '' else z.action end AS z_action,\r\n"
			+ " case when x.status is null then '' else x.status end AS x_status,\r\n"
			+ " case when y.status is null then '' else y.status end AS y_status,\r\n"
			+ " case when z.status is null then '' else z.status end AS z_status,\r\n "
			+ " case when x.modifiedBy is null then '' else x.modifiedBy end AS x_modified_by,\r\n"
			+ " case when y.modifiedBy is null then '' else y.modifiedBy end AS y_modified_by,\r\n"
			+ " case when z.modifiedBy is null then '' else z.modifiedBy end AS z_modified_by,\r\n"
			+ " case when x.modifiedDate is null then '' else x.modifiedDate end AS x_modified_date,\r\n"
			+ " case when y.modifiedDate is null then '' else y.modifiedDate end AS y_modified_date,\r\n"
			+ " case when z.modifiedDate is null then '' else z.modifiedDate end AS z_modified_date,\r\n" 
			+ " case when x.approvedBy is null then '' else x.approvedBy end AS x_approved_by,\r\n"
			+ " case when y.approvedBy is null then '' else y.approvedBy end AS y_approved_by,\r\n"
			+ " case when z.approvedBy is null then '' else z.approvedBy end AS z_approved_by,\r\n"
			+ " case when x.approvedDate is null then '' else x.approvedDate end AS x_approved_date,\r\n"
			+ " case when y.approvedDate is null then '' else y.approvedDate end AS y_approved_date,\r\n"
			+ " case when z.approvedDate is null then '' else z.approvedDate end AS z_approved_date,\r\n "
			+ " case when x.createdDate is null then '' else x.createdDate end AS x_created_date,\r\n"
			+ " case when y.createdDate is null then '' else y.createdDate end AS y_created_date,\r\n"
			+ " case when z.createdDate is null then '' else z.createdDate end AS z_created_date,\r\n"
			+ " case when x.effectiveDate is null then '' else x.effectiveDate end AS x_effective_date,\r\n"
			+ " case when y.effectiveDate is null then '' else y.effectiveDate end AS y_effective_date,\r\n"
			+ " case when z.effectiveDate is null then '' else z.effectiveDate end AS z_effective_date,\r\n" 
			+ " case when x.approvalRemark is null then '' else x.approvalRemark end AS x_approval_remark,\r\n"
			+ " case when y.approvalRemark is null then '' else y.approvalRemark end AS y_approval_remark,\r\n"
			+ " case when z.approvalRemark is null then '' else z.approvalRemark end AS z_approval_remark,\r\n"
			+ " case when x.workflowStatusId is null then '' else x.workflowStatusId end AS x_workflow_status_id,\r\n"
			+ " case when y.workflowStatusId is null then '' else y.workflowStatusId end AS y_workflow_status_id,\r\n"
			+ " case when z.workflowStatusId is null then '' else z.workflowStatusId end AS z_workflow_status_id,\r\n "
			+ " case when z.remarks is null then case when y.remarks is null then '' else y.remarks end else z.remarks end AS y_remark)\r\n"
			+ "FROM JpaCbmSoraRateSource z  left join\r\n"
			+ " JpaCbmSoraRateTemp y on z.id=y.id left join\r\n"
			+ " JpaCbmSoraRate x on y.referenceRate=x.referenceRate AND y.valueDate =x.valueDate AND y.remarks is null where :date is null or DATE(z.migratedDate)=:date\r\n"
			+ "ORDER BY z.seq asc")
	List<CbmSoraRateReportDto> getReportData(@Param("date") Date date);
	@Query("select count(*) from JpaCbmSoraRateTemp t inner join JpaCbmSoraRate l on t.referenceRate=l.referenceRate AND t.valueDate =l.valueDate and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);
}
