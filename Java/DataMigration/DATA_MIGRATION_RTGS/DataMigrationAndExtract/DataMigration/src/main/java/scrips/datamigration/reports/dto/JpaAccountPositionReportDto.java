package scrips.datamigration.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JpaAccountPositionReportDto {
	private String x_account_id;
	private String y_account_id;
	private String z_account_id;
	private String x_currency_code;
	private String y_currency_code;
	private String z_currency_code;
	private String x_member_id;
	private String y_member_id;
	private String z_member_id;
	private String x_current_account_balance;
	private String y_current_account_balance;
	private String z_current_account_balance;	
	private String x_opening_account_balance;
	private String y_opening_account_balance;
	private String z_opening_account_balance;	
	private String x_available_balance;
	private String y_available_balance;
	private String z_available_balance;	
	private String x_queue_count;
	private String y_queue_count;
	private String z_queue_count;
	private String x_queue_amount;
	private String y_queue_amount;
	private String z_queue_amount;
	private String x_settled_payments_count;
	private String y_settled_payments_count;
	private String z_settled_payments_count;	
	private String x_settled_payments_amount;
	private String y_settled_payments_amount;
	private String z_settled_payments_amount;
	private String x_settled_receipts_count;
	private String y_settled_receipts_count;
	private String z_settled_receipts_count;
	private String x_settled_receipts_amount;
	private String y_settled_receipts_amount;
	private String z_settled_receipts_amount;
	private String x_created_date;
	private String y_created_date;
	private String z_created_date;
	private String x_modified_date;
	private String y_modified_date;
	private String z_modified_date;	
	private String y_remark;
}
