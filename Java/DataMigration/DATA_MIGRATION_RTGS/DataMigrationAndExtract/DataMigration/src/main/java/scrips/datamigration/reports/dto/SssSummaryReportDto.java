package scrips.datamigration.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SssSummaryReportDto {
	private String table_name;
	private Long l_count;
	private Long s_count;
}
