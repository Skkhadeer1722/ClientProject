package scrips.datamigration.rtgs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import scrips.datamigration.reports.dto.CbmLiabilitiesBaseReportDto;
import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBase;

public interface JpaCbmLiabilitiesBaseDAO extends JpaRepository<JpaCbmLiabilitiesBase, String> {

	JpaCbmLiabilitiesBase findByMemberId(String memberId);

	@Query("select new scrips.datamigration.reports.dto.CbmLiabilitiesBaseReportDto( case when x.memberId is null then '' else x.memberId end AS x_member_id,\r\n"
			+ " case when y.memberId is null then '' else y.memberId end AS y_member_id,\r\n"
			+ " case when z.memberId is null then '' else z.memberId end AS z_member_id,\r\n"
			+ "case when x.currencyCode is null then '' else x.currencyCode end AS x_currency_code,\r\n"
			+ " case when y.currencyCode is null then '' else y.currencyCode end AS y_currency_code,\r\n"
			+ " case when z.currencyCode is null then '' else  z.currencyCode end AS z_currency_code,\r\n"
			+ " case when x.qlLbType is null then '' else x.qlLbType end AS x_ql_lb_type,\r\n"
			+ " case when y.qlLbType is null then '' else y.qlLbType end AS y_ql_lb_type,\r\n"
			+ " case when z.qlLbType is null then '' else z.qlLbType end AS z_ql_lb_type,\r\n"			
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
			+ "FROM JpaCbmLiabilitiesBaseSource z  left join\r\n"
			+ " JpaCbmLiabilitiesBaseTemp y on z.id=y.id left join\r\n"
			+ " JpaCbmLiabilitiesBase x on y.memberId=x.memberId AND y.remarks is null where :date is null or DATE(z.migratedDate)=:date \r\n"
			+ "ORDER BY z.seq asc")
	List<CbmLiabilitiesBaseReportDto> getReportData(@Param("date") Date date);

	@Query("select count(*) from JpaCbmLiabilitiesBaseTemp t inner join JpaCbmLiabilitiesBase l on t.memberId=l.memberId and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);
}
