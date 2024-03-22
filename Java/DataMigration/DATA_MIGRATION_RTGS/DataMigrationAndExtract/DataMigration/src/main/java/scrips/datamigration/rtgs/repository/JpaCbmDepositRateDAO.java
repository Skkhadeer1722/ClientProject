 package scrips.datamigration.rtgs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.CbmDepositRateReportDto;
import scrips.datamigration.rtgs.model.JpaCbmDepositRate;
@Repository
public interface JpaCbmDepositRateDAO extends JpaRepository<JpaCbmDepositRate, String> {
	@Query("select new scrips.datamigration.reports.dto.CbmDepositRateReportDto( case when x.id is null then '' else x.id end AS x_id,\r\n"
			+ " case when y.id is null then '' else y.id end AS y_id,\r\n"
			+ " case when z.id is null then '' else  z.id end AS z_id,\r\n"
			+ " case when x.tenorPeriod is null then '' else x.tenorPeriod end AS x_tenor_period,\r\n"
			+ " case when y.tenorPeriod is null then '' else y.tenorPeriod end AS y_tenor_period,\r\n"
			+ " case when z.tenorPeriod is null then '' else z.tenorPeriod end AS z_tenor_period,\r\n"
			+ " case when x.depositRate is null then '' else x.depositRate end AS x_deposit_rate,\r\n"
			+ " case when y.depositRate is null then '' else y.depositRate end AS y_deposit_rate,\r\n"
			+ " case when z.depositRate is null then '' else z.depositRate end AS z_deposit_rate,\r\n" 
			+ " case when x.facilityId is null then '' else x.facilityId end AS x_facility_id,\r\n"
			+ " case when y.facilityId is null then '' else y.facilityId end AS y_facility_id,\r\n"
			+ " case when z.facilityId is null then '' else z.facilityId end AS z_facility_id,\r\n" 
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
			+ "FROM JpaCbmDepositRateSource z  left join\r\n"
			+ " JpaCbmDepositRateTemp y on z.id=y.id left join\r\n"
			+ " JpaCbmDepositRate x on z.id=x.id AND y.remarks is null where :date is null or  DATE(z.migratedDate)=:date \r\n"
			+ "ORDER BY z.seq asc")
	List<CbmDepositRateReportDto> getReportData(@Param("date") Date date);
	@Query("select count(*) from JpaCbmDepositRateTemp t inner join JpaCbmDepositRate l on t.id=l.id and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);
}
