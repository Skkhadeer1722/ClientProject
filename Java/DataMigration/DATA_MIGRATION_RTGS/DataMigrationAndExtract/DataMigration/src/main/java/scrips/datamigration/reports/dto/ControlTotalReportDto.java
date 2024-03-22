package scrips.datamigration.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ControlTotalReportDto {
	private String table_name;
	private Long l_count;
	private Long s_count;
}
