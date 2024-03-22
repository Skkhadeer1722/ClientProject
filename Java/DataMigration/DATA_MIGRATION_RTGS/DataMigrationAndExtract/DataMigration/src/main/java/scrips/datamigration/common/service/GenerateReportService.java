package scrips.datamigration.common.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import scrips.datamigration.reports.dto.AccountSummaryReportDto;
import scrips.datamigration.reports.dto.ControlTotalReportDto;
import scrips.datamigration.reports.dto.SssSummaryReportDto;
import scrips.datamigration.rtgs.repository.AccountSummaryReportDAO;
import scrips.datamigration.rtgs.repository.ControlTotalReportDAO;
import scrips.datamigration.rtgs.repository.JpaAccountDAO;
import scrips.datamigration.rtgs.repository.JpaAccountPositionDAO;
import scrips.datamigration.rtgs.repository.JpaCbmCostCentreDAO;
import scrips.datamigration.rtgs.repository.JpaCbmDepositRateDAO;
import scrips.datamigration.rtgs.repository.JpaCbmGlAccountDAO;
import scrips.datamigration.rtgs.repository.JpaCbmLiabilitiesBaseDAO;
import scrips.datamigration.rtgs.repository.JpaCbmLiabilitiesBaseDetailDAO;
import scrips.datamigration.rtgs.repository.JpaCbmSoraRateDAO;
import scrips.datamigration.sss.repository.SssAllotmentDAO;
import scrips.datamigration.sss.repository.SssFloatingRatesDAO;
import scrips.datamigration.sss.repository.SssSecuritiesCodeDAO;
import scrips.datamigration.sss.repository.SssSecuritiesCodeStatisticsDAO;
import scrips.datamigration.sss.repository.SssSecuritiesPositionStatsDAO;
import scrips.datamigration.sss.repository.SssSecuritiesPriceDAO;
import scrips.datamigration.sss.repository.SssSummaryReportDAO;
import scrips.datamigration.sss.repository.SssTransactionDAO;

@Service

public class GenerateReportService {

	private final Logger logger = LogManager.getLogger(GenerateReportService.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	Environment env;

	@Autowired
	JpaCbmCostCentreDAO jpaCbmCostCentreDAO;

	@Autowired
	JpaCbmGlAccountDAO jpaCbmGlAccountDAO;

	@Autowired
	JpaAccountDAO jpaAccountDAO;

	@Autowired
	JpaAccountPositionDAO jpaAccountPositionDAO;

	@Autowired
	JpaCbmLiabilitiesBaseDetailDAO jpaCbmLiabilitiesBaseDetailDAO;

	@Autowired
	JpaCbmLiabilitiesBaseDAO jpaCbmLiabilitiesBaseDAO;

	@Autowired
	JpaCbmDepositRateDAO jpaCbmDepositRateDAO;

	@Autowired
	JpaCbmSoraRateDAO jpaCbmSoraRateDAO;

	@Autowired
	SssAllotmentDAO sssAllotmentDAO;

	@Autowired
	SssFloatingRatesDAO sssFloatingRatesDAO;

	@Autowired
	SssSecuritiesPriceDAO sssSecuritiesPriceDAO;

	@Autowired
	SssSecuritiesCodeStatisticsDAO sssSecuritiesCodeStatisticsDAO;

	@Autowired
	SssSecuritiesPositionStatsDAO sssSecuritesPositionStatsDAO;

	@Autowired
	SssSecuritiesCodeDAO sssSecuritiesCodeDAO;

	@Autowired
	SssTransactionDAO sssTransactionDAO;

	@Autowired
	ControlTotalReportDAO controlTotalReportDAO;

	@Autowired
	SssSummaryReportDAO sssSummaryReportDAO;

	@Autowired
	AccountSummaryReportDAO accountSummaryReportDAO;

	public String exportReport(String reportFormat, String iDate, String reportName) throws Exception, IOException {
		logger.info("Process started for :" + reportName);
		List<?> reportData = null;
		Integer mgd_count = null;

		if (reportName.equals("ACCOUNT_SUMMARY_REPORT")) {
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
		} else {
			Date date = iDate.trim().isEmpty() ? null : new SimpleDateFormat("yyyy-MM-dd").parse(iDate);
			switch (reportName) {

//			case "ACCOUNT_SUMMARY_REPORT":{
//				List<AccountSummaryReportDto> datalist= new ArrayList<AccountSummaryReportDto>();
//				datalist.add(accountSummaryReportDAO.getAccountCount(date));
//				reportData=datalist;
//			}
//				break;

			case "CONTROL_TOTAL_REPORT": {
				List<ControlTotalReportDto> datalist = new ArrayList<ControlTotalReportDto>();
				datalist.add(controlTotalReportDAO.getAccountCount(date));
				datalist.add(controlTotalReportDAO.getAccountPositionCount(date));
				datalist.add(controlTotalReportDAO.getCbmDepositRateCount(date));
				datalist.add(controlTotalReportDAO.getCbmCostCentre(date));
				datalist.add(controlTotalReportDAO.getCbmGlAccount(date));
				datalist.add(controlTotalReportDAO.getCbmLiabilitiesBase(date));
				datalist.add(controlTotalReportDAO.getCbmLiabilitiesBaseDetail(date));
				reportData = datalist;
			}
				break;
//			case "SSS_SUMMARY_REPORT": {
//				List<SssSummaryReportDto> datalist = new ArrayList<SssSummaryReportDto>();
//				datalist.add(sssSummaryReportDAO.getAllotmentCount(date));
//				datalist.add(sssSummaryReportDAO.getFloatingRatesCount(date));
//				datalist.add(sssSummaryReportDAO.getSecuritiesCodeCount(date));
//				datalist.add(sssSummaryReportDAO.getSecuritiesCodeStatisticsCount(date));
//				datalist.add(sssSummaryReportDAO.getSecuritiesPriceCount(date));
//				datalist.add(sssSummaryReportDAO.getSecuritiesPositionStatsCount(date));
//				datalist.add(sssSummaryReportDAO.getTransactionCount(date));
//				reportData = datalist;
//			}
//				break;

			case "ACCOUNT_DETAIL_REPORT":
				reportData = jpaAccountDAO.getReportData(date);
				mgd_count = jpaAccountDAO.getMigratedCount(date);
				break;

			case "ACCOUNT_POSITION_DETAIL_REPORT":
				reportData = jpaAccountPositionDAO.getReportData(date);
				mgd_count = jpaAccountPositionDAO.getMigratedCount(date);
				break;

			case "CBM_COST_CENTRE_DETAIL_REPORT":
				reportData = jpaCbmCostCentreDAO.getReportData(date);
				mgd_count = jpaCbmCostCentreDAO.getMigratedCount(date);
				break;

			case "CBM_DEPOSIT_RATE_DETAIL_REPORT":
				reportData = jpaCbmDepositRateDAO.getReportData(date);
				mgd_count = jpaCbmDepositRateDAO.getMigratedCount(date);
				break;

			case "CBM_LIABILITIES_BASE_DETAIL_REPORT":
				reportData = jpaCbmLiabilitiesBaseDetailDAO.getReportData(date);
				mgd_count = jpaCbmLiabilitiesBaseDetailDAO.getMigratedCount(date);
				break;

			case "CBM_LIABILITIES_BASE_REPORT":
				reportData = jpaCbmLiabilitiesBaseDAO.getReportData(date);
				mgd_count = jpaCbmLiabilitiesBaseDAO.getMigratedCount(date);
				break;

			case "CBM_SORA_RATE_DETAIL_REPORT":
				reportData = jpaCbmSoraRateDAO.getReportData(date);
				mgd_count = jpaCbmSoraRateDAO.getMigratedCount(date);
				break;

			case "CBM_GL_ACCOUNT_DETAIL_REPORT":
				reportData = jpaCbmGlAccountDAO.getReportData(date);
				mgd_count = jpaCbmGlAccountDAO.getMigratedCount(date);
				break;

			case "SSS_ALLOTMENT_DETAIL_REPORT":
				reportData = sssAllotmentDAO.getReportData(date);
				mgd_count = sssAllotmentDAO.getMigratedCount(date);
				break;

			case "SSS_FLOATING_RATE_DETAIL_REPORT":
				reportData = sssFloatingRatesDAO.getReportData(date);
				mgd_count = sssFloatingRatesDAO.getMigratedCount(date);
				break;

			case "REPO_CLOSING_TRANSACTION_REPORT":
				reportData = sssTransactionDAO.getReportData(date);
				mgd_count = sssTransactionDAO.getMigratedCount(date);
				break;

			case "SSS_SECURITIES_PRICE_DETAIL_REPORT":
				reportData = sssSecuritiesPriceDAO.getReportData(date);
				mgd_count = sssSecuritiesPriceDAO.getMigratedCount(date);
				break;

			case "SSS_SECURITIES_POSITION_STATS_DETAIL_REPORT":
				reportData = sssSecuritesPositionStatsDAO.getReportData(date);
				mgd_count = sssSecuritesPositionStatsDAO.getMigratedCount(date);
				break;

			case "SSS_SECURITIES_CODE_DETAIL_REPORT":
				reportData = sssSecuritiesCodeDAO.getReportData(date);
				mgd_count = sssSecuritiesCodeDAO.getMigratedCount(date);
				break;

			case "SSS_SECURITIES_CODE_STATISTICS_DETAIL_REPORT":
				reportData = sssSecuritiesCodeStatisticsDAO.getReportData(date);
				mgd_count = sssSecuritiesCodeStatisticsDAO.getMigratedCount(date);
				break;
			default:
				logger.error("Default case");
				break;
			}
			return generateReportUsingJava(reportData, reportFormat, iDate, reportName, mgd_count);
		}
	}

	private String generateReportUsingJava(List<?> reportData, String reportFormat, String iDate, String reportName,
			Integer mgdCount) {

		String path = env.getProperty("remotefolderpath") + "/reports/";
		String jrxmlFilepath = env.getProperty("remotefolderpath") + "/file/jasperFiles/";
		logger.info("Path -> {}", path);
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFilepath + reportName + ".jrxml");
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("iDate", iDate);
			parameters.put("reportID", reportName);
			parameters.put("mgd_count", mgdCount);
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportData, false);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					jrBeanCollectionDataSource);
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
