package scrips.datamigration.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountSummaryReportDto {
	private String field_name;
	private Long x_account_type;
	private Long s_count;
}
