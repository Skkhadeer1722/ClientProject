package scrips.datamigration.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CbmCostCentreReportDto {
	private String x_cost_centre;
	private String y_cost_centre;
	private String z_cost_centre;
	private String x_description;
	private String y_description;
	private String z_description;
	private String x_created_date;
	private String y_created_date;
	private String z_created_date;
	private String x_modified_date;
	private String y_modified_date;
	private String z_modified_date;
	private String y_remark;
	
	 
}
