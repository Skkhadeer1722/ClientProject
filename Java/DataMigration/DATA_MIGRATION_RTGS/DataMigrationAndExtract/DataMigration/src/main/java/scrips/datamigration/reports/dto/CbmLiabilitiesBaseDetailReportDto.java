package scrips.datamigration.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CbmLiabilitiesBaseDetailReportDto {
	private String x_id;
	private String y_id;
	private String z_id;
	private String x_member_id;
	private String y_member_id;
	private String z_member_id;
	private String x_start_date;
	private String y_start_date;
	private String z_start_date;
	private String x_end_date;
	private String y_end_date;
	private String z_end_date;
	private String x_ql_lb;
	private String y_ql_lb;
	private String z_ql_lb;
	private String y_remark;

}
