package scrips.datamigration.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JpaSssSecuritiesPositionStatsReportDto {
	private String x_securities_code;
	private String y_securities_code;
	private String z_securities_code;
	private String x_value_date;
	private String y_value_date;
	private String z_value_date;	
	private String x_opening_nominal_amount;
	private String y_opening_nominal_amount;
	private String z_opening_nominal_amount;
	private String x_closing_nominal_amount;
	private String y_closing_nominal_amount;
	private String z_closing_nominal_amount;
	private String x_settled_count;
	private String y_settled_count;
	private String z_settled_count;
	private String x_settled_amount;
	private String y_settled_amount;
	private String z_settled_amount;
	private String x_receipt_count;
	private String y_receipt_count;
	private String z_receipt_count;
	private String x_receipt_amount;
	private String y_receipt_amount;
	private String z_receipt_amount;	
	private String x_queued_count;
	private String y_queued_count;
	private String z_queued_count;
	private String x_queued_amount;
	private String y_queued_amount;
	private String z_queued_amount;
	private String x_rejected_count;
	private String y_rejected_count;
	private String z_rejected_count;
	private String x_rejected_amount;
	private String y_rejected_amount;
	private String z_rejected_amount;	
	private String x_cancelled_count;
	private String y_cancelled_count;
	private String z_cancelled_count;
	private String x_cancelled_amount;
	private String y_cancelled_amount;
	private String z_cancelled_amount;
	private String x_account_id;
	private String y_account_id;
	private String z_account_id;
	private String y_remark;
}
