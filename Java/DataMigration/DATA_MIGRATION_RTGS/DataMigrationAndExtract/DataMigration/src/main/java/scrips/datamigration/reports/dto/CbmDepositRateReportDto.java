package scrips.datamigration.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CbmDepositRateReportDto {
	private String x_id;
	private String y_id;
	private String z_id;
	private String x_tenor_period;
	private String y_tenor_period;
	private String z_tenor_period;	
	private String x_deposit_rate;
	private String y_deposit_rate;
	private String z_deposit_rate;
	private String x_facility_id;
	private String y_facility_id;
	private String z_facility_id;
	private String x_action;
	private String y_action;
	private String z_action;
	private String x_status;
	private String y_status;
	private String z_status;
	private String x_modified_by;
	private String y_modified_by;
	private String z_modified_by;
	private String x_modified_date;
	private String y_modified_date;
	private String z_modified_date;	
	private String x_approved_by;
	private String y_approved_by;
	private String z_approved_by;
	private String x_approved_date;
	private String y_approved_date;
	private String z_approved_date;
	private String x_created_date;
	private String y_created_date;
	private String z_created_date;
	private String x_effective_date;
	private String y_effective_date;
	private String z_effective_date;	
	private String x_approval_remark;
	private String y_approval_remark;
	private String z_approval_remark;
	private String x_workflow_status_id;
	private String y_workflow_status_id;
	private String z_workflow_status_id;
	private String y_remark;
}
