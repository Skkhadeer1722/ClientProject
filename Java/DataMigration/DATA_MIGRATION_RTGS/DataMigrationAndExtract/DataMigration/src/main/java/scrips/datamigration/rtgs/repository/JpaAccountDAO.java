package scrips.datamigration.rtgs.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scrips.datamigration.reports.dto.JpaAccountReportDto;
import scrips.datamigration.rtgs.model.JpaAccount;
@Repository
@Transactional
public interface JpaAccountDAO extends JpaRepository<JpaAccount, String>{

	JpaAccount findByAccountNumber(String accountNumber);
	@Query("select new scrips.datamigration.reports.dto.JpaAccountReportDto( case when x.id is null then '' else x.id end AS x_id,\r\n"
			+ " case when y.id is null then '' else y.id end AS y_id,\r\n"
			+ " case when z.id is null then '' else  z.id end AS z_id,\r\n"
			+ " case when x.accountNumber is null then '' else x.accountNumber end AS x_account_number,\r\n"
			+ " case when y.accountNumber is null then '' else y.accountNumber end AS y_account_number,\r\n"
			+ " case when z.accountNumber is null then '' else z.accountNumber end AS z_account_number,\r\n"
			+ " case when x.description is null then '' else x.description end AS x_description,\r\n"
			+ " case when y.description is null then '' else y.description end AS y_description,\r\n"
			+ " case when z.description is null then '' else z.description end AS z_description,\r\n" 
			+ " case when x.memberId is null then '' else x.memberId end AS x_member_id,\r\n"
			+ " case when y.memberId is null then '' else y.memberId end AS y_member_id,\r\n"
			+ " case when z.memberId is null then '' else z.memberId end AS z_member_id,\r\n" 
			+ " case when x.accountType is null then '' else x.accountType end AS x_account_type,\r\n"
			+ " case when y.accountType is null then '' else y.accountType end AS y_account_type,\r\n"
			+ " case when z.accountType is null then '' else  z.accountType end AS z_account_type,\r\n"
			+ " case when x.mainAccountIndicator is null then '' else x.mainAccountIndicator end AS x_main_account_indicator,\r\n"
			+ " case when y.mainAccountIndicator is null then '' else y.mainAccountIndicator end AS y_main_account_indicator,\r\n"
			+ " case when z.mainAccountIndicator is null then '' else z.mainAccountIndicator end AS z_main_account_indicator,\r\n"
			+ " case when x.defaultMainAccount is null then '' else x.defaultMainAccount end AS x_default_main_account,\r\n"
			+ " case when y.defaultMainAccount is null then '' else y.defaultMainAccount end AS y_default_main_account,\r\n"
			+ " case when z.defaultMainAccount is null then '' else z.defaultMainAccount end AS z_default_main_account,\r\n" 
			+ " case when x.statementDeliveryBic is null then '' else x.statementDeliveryBic end AS x_statement_delivery_bic, \r\n"
			+ " case when y.statementDeliveryBic is null then '' else y.statementDeliveryBic end AS y_statement_delivery_bic,\r\n"
			+ " case when z.statementDeliveryBic is null then '' else z.statementDeliveryBic end AS z_statement_delivery_bic,\r\n" 
			+ " case when x.accountStatus is null then '' else x.accountStatus end AS x_account_status,\r\n"
			+ " case when y.accountStatus is null then '' else y.accountStatus end AS y_account_status,\r\n"
			+ " case when z.accountStatus is null then '' else z.accountStatus end AS z_account_status,\r\n"
			+ " case when x.currencyCode is null then '' else x.currencyCode end AS x_currency_code,\r\n"
			+ " case when y.currencyCode is null then '' else y.currencyCode end AS y_currency_code,\r\n"
			+ " case when z.currencyCode is null then '' else z.currencyCode end AS z_currency_code,\r\n" 
			+ " case when x.endOfDayStatement is null then '' else x.endOfDayStatement end AS x_end_of_day_statement,\r\n"
			+ " case when y.endOfDayStatement is null then '' else y.endOfDayStatement end AS y_end_of_day_statement,\r\n"
			+ " case when z.endOfDayStatement is null then '' else z.endOfDayStatement end AS z_end_of_day_statement,\r\n" 
			+ " case when x.payerReference is null then '' else x.payerReference end AS x_payer_reference,\r\n"
			+ " case when y.payerReference is null then '' else y.payerReference end AS y_payer_reference,\r\n"
			+ " case when z.payerReference is null then '' else  z.payerReference end AS z_payer_reference,\r\n"
			+ " case when x.relatedReference is null then '' else x.relatedReference end AS x_related_reference,\r\n"
			+ " case when y.relatedReference is null then '' else y.relatedReference end AS y_related_reference,\r\n"
			+ " case when z.relatedReference is null then '' else z.relatedReference end AS z_related_reference,\r\n"
			+ " case when x.counterpartyId is null then '' else x.counterpartyId end AS x_counterparty_id,\r\n"
			+ " case when y.counterpartyId is null then '' else y.counterpartyId end AS y_counterparty_id,\r\n"
			+ " case when z.counterpartyId is null then '' else z.counterpartyId end AS z_counterparty_id,\r\n" 
			+ " case when x.valueDate is null then '' else x.valueDate end AS x_value_date, \r\n"
			+ " case when y.valueDate is null then '' else y.valueDate end AS y_value_date,\r\n"
			+ " case when z.valueDate is null then '' else z.valueDate end AS z_value_date,\r\n"			
			+ " case when x.activationDate is null then '' else x.activationDate end AS x_activation_date,\r\n"
			+ " case when y.activationDate is null then '' else y.activationDate end AS y_activation_date,\r\n"
			+ " case when z.activationDate is null then '' else z.activationDate end AS z_activation_date,\r\n" 
			+ " case when x.glCategory is null then '' else x.glCategory end AS x_gl_category,\r\n"
			+ " case when y.glCategory is null then '' else y.glCategory end AS y_gl_category,\r\n"
			+ " case when z.glCategory is null then '' else z.glCategory end AS z_gl_category,\r\n" 
			+ " case when x.costCentre is null then '' else x.costCentre end AS x_cost_centre,\r\n"
			+ " case when y.costCentre is null then '' else y.costCentre end AS y_cost_centre,\r\n"
			+ " case when z.costCentre is null then '' else  z.costCentre end AS z_cost_centre,\r\n"
			+ " case when x.glAccountNumber is null then '' else x.glAccountNumber end AS x_gl_account_number,\r\n"
			+ " case when y.glAccountNumber is null then '' else y.glAccountNumber end AS y_gl_account_number,\r\n"
			+ " case when z.glAccountNumber is null then '' else z.glAccountNumber end AS z_gl_account_number,\r\n"
			+ " case when x.statementInterval is null then '' else x.statementInterval end AS x_statement_interval,\r\n"
			+ " case when y.statementInterval is null then '' else y.statementInterval end AS y_statement_interval,\r\n"
			+ " case when z.statementInterval is null then '' else z.statementInterval end AS z_statement_interval,\r\n" 
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
			+ " case when x.complianceCalculation is null then '' else x.complianceCalculation end AS x_compliance_calculation,\r\n"
			+ " case when y.complianceCalculation is null then '' else y.complianceCalculation end AS y_compliance_calculation,\r\n"
			+ " case when z.complianceCalculation is null then '' else z.complianceCalculation end AS z_compliance_calculation,\r\n "
			+ " case when z.remarks is null then case when y.remarks is null then '' else y.remarks end else z.remarks end AS y_remark)\r\n"
			+ "FROM JpaAccountSource z  left join\r\n"
			+ " JpaAccountTemp y on z.id=y.id left join\r\n"
			+ " JpaAccount x on z.id=x.id AND y.remarks is null where :date is null or DATE(z.migratedDate)= :date\r\n"
			+ "ORDER BY z.seq asc")
	List<JpaAccountReportDto> getReportData(@Param("date") Date date);
	@Query("select count(*) from JpaAccountTemp t inner join JpaAccount l on t.id=l.id and t.remarks is null  where :date is null or DATE(l.createdDate)= :date")
	Integer getMigratedCount(@Param("date") Date date);
}
