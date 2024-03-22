package scrips.datamigration.common.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service

public class GenerateReportService {

	private final Logger logger = LogManager.getLogger(GenerateReportService.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	Environment env;

	public String exportReport(String reportFormat, String iDate, String reportName) throws Exception, IOException {

		logger.info("process started for :" + reportName);
		String path = env.getProperty("remotefolderpath") + "/reports/";
		String jrxmlFilepath = env.getProperty("remotefolderpath") + "/file/jasperFiles/";
		logger.info("Path -> {}", path);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFilepath + reportName + ".jrxml");
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("iDate", iDate);
			parameters.put("reportID", reportName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					dataSource.getConnection());
			if (reportFormat.equalsIgnoreCase("html")) {
				JasperExportManager.exportReportToHtmlFile(jasperPrint, path + reportName + ".html");
			}
			if (reportFormat.equalsIgnoreCase("pdf")) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, path + reportName + ".pdf");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "report generated in path : " + path;

	}

}
