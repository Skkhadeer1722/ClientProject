package scrips.datamigration.sss.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.JpaSssAllotmentReportDto;
import scrips.datamigration.sss.model.JpaSssAllotment;
@Repository
public interface SssAllotmentDAO extends JpaRepository<JpaSssAllotment,String>{

	JpaSssAllotment findBySecuritiesCode(String securitiesCode);
	@Query("select new scrips.datamigration.reports.dto.JpaSssAllotmentReportDto(case when x.id is null then '' else x.id end AS x_id,\r\n"
			+ " case when y.id is null then '' else y.id end AS y_id,\r\n"
			+ " case when z.id is null then '' else  z.id end AS z_id,\r\n"
			+ " case when x.securitiesCode is null then '' else x.securitiesCode end AS x_securities_code,\r\n"
			+ " case when y.securitiesCode is null then '' else y.securitiesCode end AS y_securities_code,\r\n"
			+ " case when z.securitiesCode is null then '' else z.securitiesCode end AS z_securities_code,\r\n"
			+ " case when x.auctionDate is null then '' else x.auctionDate end AS x_auction_date,\r\n"
			+ " case when y.auctionDate is null then '' else y.auctionDate end AS y_auction_date,\r\n"
			+ " case when z.auctionDate is null then '' else z.auctionDate end AS z_auction_date,\r\n" 
			+ " case when x.issuanceDate is null then '' else x.issuanceDate end AS x_issuance_date,\r\n"
			+ " case when y.issuanceDate is null then '' else y.issuanceDate end AS y_issuance_date,\r\n"
			+ " case when z.issuanceDate is null then '' else z.issuanceDate end AS z_issuance_date,\r\n" 
			+ " case when x.allotmentPrice is null then '' else x.allotmentPrice end AS x_allotment_price,\r\n"
			+ " case when y.allotmentPrice is null then '' else y.allotmentPrice end AS y_allotment_price,\r\n"
			+ " case when z.allotmentPrice is null then '' else z.allotmentPrice end AS z_allotment_price,\r\n"
			+ " case when x.totalNominalAmountAlloted is null then '' else x.totalNominalAmountAlloted end AS x_total_nominal_amount_alloted,\r\n"
			+ " case when y.totalNominalAmountAlloted is null then '' else y.totalNominalAmountAlloted end AS y_total_nominal_amount_alloted,\r\n"
			+ " case when z.totalNominalAmountAlloted is null then '' else z.totalNominalAmountAlloted end AS z_total_nominal_amount_alloted,\r\n" 
			+ " case when x.totalNominalAmountToBeAlloted is null then '' else x.totalNominalAmountToBeAlloted end AS x_total_nominal_amount_to_be_alloted,\r\n"
			+ " case when y.totalNominalAmountToBeAlloted is null then '' else y.totalNominalAmountToBeAlloted end AS y_total_nominal_amount_to_be_alloted,\r\n"
			+ " case when z.totalNominalAmountToBeAlloted is null then '' else z.totalNominalAmountToBeAlloted end AS z_total_nominal_amount_to_be_alloted,\r\n" 
			+ " case when x.firstAllotment is null then '' else x.firstAllotment end AS x_first_allotment,\r\n"
			+ " case when y.firstAllotment is null then '' else y.firstAllotment end AS y_first_allotment,\r\n"
			+ " case when z.firstAllotment is null then '' else z.firstAllotment end AS z_first_allotment,\r\n"
			+ " case when x.totalSettlementAmount is null then '' else x.totalSettlementAmount end AS x_total_settlement_amount,\r\n"
			+ " case when y.totalSettlementAmount is null then '' else y.totalSettlementAmount end AS y_total_settlement_amount,\r\n"
			+ " case when z.totalSettlementAmount is null then '' else z.totalSettlementAmount end AS z_total_settlement_amount,\r\n" 
			+ " case when x.processed is null then '' else x.processed end AS x_processed,\r\n"
			+ " case when y.processed is null then '' else y.processed end AS y_processed,\r\n"
			+ " case when z.processed is null then '' else z.processed end AS z_processed,\r\n" 
			+ " case when x.plannedTotalNominalAmountAllotted is null then '' else x.plannedTotalNominalAmountAllotted end AS x_planned_total_nominal_amount_allotted,\r\n"
			+ " case when y.plannedTotalNominalAmountAllotted is null then '' else y.plannedTotalNominalAmountAllotted end AS y_planned_total_nominal_amount_allotted,\r\n"
			+ " case when z.plannedTotalNominalAmountAllotted is null then '' else z.plannedTotalNominalAmountAllotted end AS z_planned_total_nominal_amount_allotted,\r\n "
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
			+ "FROM JpaSssAllotmentSource z  left join\r\n"
			+ " JpaSssAllotmentTemp y on z.id=y.id left join\r\n"
			+ " JpaSssAllotment x on y.id=x.id AND y.remarks is null  where :date is null or DATE(z.migratedDate)=:date \r\n"
			+ "ORDER BY z.seq asc")
	List<JpaSssAllotmentReportDto> getReportData(@Param("date") Date date);
	@Query("select count(*) from JpaSssAllotmentTemp t inner join JpaSssAllotment l on l.id=t.id and t.remarks is null where :date is null or DATE(l.createdDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);
}