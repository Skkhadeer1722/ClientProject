package scrips.datamigration.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CbmGlAccountReportDto {
	private String x_gl_account;
	private String y_gl_account;
	private String z_gl_account;
	private String x_gl_account_indicator;
	private String y_gl_account_indicator;
	private String z_gl_account_indicator;
	private String x_gl_account_description;
	private String y_gl_account_description;
	private String z_gl_account_description;
	private String x_created_date;
	private String y_created_date;
	private String z_created_date;
	private String x_modified_date;
	private String y_modified_date;
	private String z_modified_date;
	private String y_remark;

}
