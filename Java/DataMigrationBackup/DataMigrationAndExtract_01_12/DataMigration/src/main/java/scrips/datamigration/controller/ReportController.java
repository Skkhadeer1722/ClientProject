
package scrips.datamigration.controller;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.common.service.GenerateReportService;

@Controller
public class ReportController {

	@Autowired
	GenerateReportService generateReportService;
	@Autowired
	private Environment env;
	private final Logger logger = LogManager.getLogger(ReportController.class);

	private static List<String> jasperFileNames = null;

	@PostConstruct
	public void loadData() {
		jasperFileNames = Arrays.asList("ACCOUNT_DETAIL_REPORT", "ACCOUNT_POSITION_DETAIL_REPORT",
				"CBM_COST_CENTRE_DETAIL_REPORT", "CBM_SORA_RATE_DETAIL_REPORT", "CBM_DEPOSIT_RATE_DETAIL_REPORT",
				"CBM_LIABILITIES_BASE_REPORT", "CBM_LIABILITIES_BASE_DETAIL_REPORT", "SSS_ALLOTMENT_DETAIL_REPORT",
				"SSS_FLOATING_RATE_DETAIL_REPORT", "SSS_SECURITIES_CODE_DETAIL_REPORT",
				"SSS_SECURITIES_CODE_STATISTICS_DETAIL_REPORT", "SSS_SECURITIES_PRICE_DETAIL_REPORT",
				"SSS_SECURITIES_POSITION_STATS_DETAIL_REPORT", "REPO_CLOSING_TRANSACTION_REPORT");
	}

	public String generateReport(String format, String iDate, String reportName) throws Exception {
		CommonUtils.createFolder("reports", env.getProperty("remotefolderpath") + "/");
		String decodedIdate = URLDecoder.decode(iDate, "UTF-8");
		String decodedreportName = URLDecoder.decode(reportName, "UTF-8");
		logger.info("{} - {}", decodedIdate, decodedreportName);
		if (jasperFileNames.contains(reportName)) {
			return generateReportService.exportReport(format, decodedIdate, decodedreportName);
		} else
			logger.info("Invalid Report Name --> {}", reportName);
		return null;
	}

}