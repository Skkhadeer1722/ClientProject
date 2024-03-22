package scrips.datamigration.sss.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.JpaSssSecuritiesPriceReportDto;
import scrips.datamigration.sss.model.JpaSssSecuritiesPrice;
@Repository
public interface SssSecuritiesPriceDAO extends JpaRepository<JpaSssSecuritiesPrice, String>{

	JpaSssSecuritiesPrice findBySecuritiesCode(String securitiesCode);

	@Query("select new scrips.datamigration.reports.dto.JpaSssSecuritiesPriceReportDto( case when x.securitiesCode is null then '' else x.securitiesCode end AS x_securities_code,\r\n"
			+ " case when y.securitiesCode is null then '' else y.securitiesCode end AS y_securities_code,\r\n"
			+ " case when z.securitiesCode is null then '' else  z.securitiesCode end AS z_securities_code,\r\n"
			+ " case when x.pricingDate is null then '' else x.pricingDate end AS x_pricing_date,\r\n"
			+ " case when y.pricingDate is null then '' else y.pricingDate end AS y_pricing_date,\r\n"
			+ " case when z.pricingDate is null then '' else z.pricingDate end AS z_pricing_date,\r\n"
			+ " case when x.description is null then '' else x.description end AS x_description,\r\n"
			+ " case when y.description is null then '' else y.description end AS y_description,\r\n"
			+ " case when z.description is null then '' else z.description end AS z_description,\r\n" 
			+ " case when x.price is null then '' else x.price end AS x_price,\r\n"
			+ " case when y.price is null then '' else y.price end AS y_price,\r\n"
			+ " case when z.price is null then '' else z.price end AS z_price,\r\n" 
			+ " case when x.issueCode is null then '' else x.issueCode end AS x_issue_code,\r\n"
			+ " case when y.issueCode is null then '' else y.issueCode end AS y_issue_code,\r\n"
			+ " case when z.issueCode is null then '' else z.issueCode end AS z_issue_code,\r\n" 
			+ " case when x.pricingType is null then '' else x.pricingType end AS x_pricing_type,\r\n"
			+ " case when y.pricingType is null then '' else y.pricingType end AS y_pricing_type,\r\n"
			+ " case when z.pricingType is null then '' else z.pricingType end AS z_pricing_type,\r\n" 
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
			+ " FROM JpaSssSecuritiesPriceSource z left join\r\n"
			+ " JpaSssSecuritiesPriceTemp y on z.id=y.id left join\r\n"
			+ " JpaSssSecuritiesPrice x on y.securitiesCode=x.securitiesCode and y.pricingType=x.pricingType and y.pricingDate=x.pricingDate AND y.remarks is null where :date is null or DATE(z.migratedDate)= :date \r\n"
			+ " ORDER BY z.seq asc")
	List<JpaSssSecuritiesPriceReportDto> getReportData(@Param("date") Date date);
	@Query("select count(*) from JpaSssSecuritiesPriceTemp t inner join JpaSssSecuritiesPrice l on t.securitiesCode=l.securitiesCode and t.pricingType=l.pricingType and t.pricingDate=l.pricingDate and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);
	
}

