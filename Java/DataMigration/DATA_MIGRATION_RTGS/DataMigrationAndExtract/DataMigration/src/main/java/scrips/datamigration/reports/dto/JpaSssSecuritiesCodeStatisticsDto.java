package scrips.datamigration.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JpaSssSecuritiesCodeStatisticsDto {
	private String x_securities_code;
	private String y_securities_code;
	private String z_securities_code;
	private String x_total_redeemed_amount;
	private String y_total_redeemed_amount;
	private String z_total_redeemed_amount;	
	private String x_total_nominal_amount_issued_for_erf;
	private String y_total_nominal_amount_issued_for_erf;
	private String z_total_nominal_amount_issued_for_erf;
	private String x_total_redeemed_amount_for_erf;
	private String y_total_redeemed_amount_for_erf;
	private String z_total_redeemed_amount_for_erf;
	private String x_outstanding_nominal_amount;
	private String y_outstanding_nominal_amount;
	private String z_outstanding_nominal_amount;
	private String x_next_coupon_date;
	private String y_next_coupon_date;
	private String z_next_coupon_date;
	private String x_last_coupon_date;
	private String y_last_coupon_date;
	private String z_last_coupon_date;
	private String x_next_index_end_date;
	private String y_next_index_end_date;
	private String z_next_index_end_date;	
	private String x_amount_allotted_to_central_bank;
	private String y_amount_allotted_to_central_bank;
	private String z_amount_allotted_to_central_bank;
	private String x_amount_allotted_to_others;
	private String y_amount_allotted_to_others;
	private String z_amount_allotted_to_others;
	private String y_remark;
}
