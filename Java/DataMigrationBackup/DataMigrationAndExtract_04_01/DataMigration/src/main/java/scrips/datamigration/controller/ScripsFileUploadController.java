package scrips.datamigration.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.common.service.FileUploadService;
import scrips.datamigration.common.service.FkChecksEnableDisable;
import scrips.datamigration.common.service.SftpService;
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.rtgs.service.AccountPositionService;
import scrips.datamigration.rtgs.service.AccountService;
import scrips.datamigration.rtgs.service.CbmCostCentreService;
import scrips.datamigration.rtgs.service.CbmDepositRateService;
import scrips.datamigration.rtgs.service.CbmGlAccountService;
import scrips.datamigration.rtgs.service.CbmLiabilitiesBaseDetailService;
import scrips.datamigration.rtgs.service.CbmLiabilitiesBaseService;
import scrips.datamigration.rtgs.service.CbmSoraRateService;
import scrips.datamigration.sss.repository.SssMemberDAO;
import scrips.datamigration.sss.service.SssAllotmentService;
import scrips.datamigration.sss.service.SssFloatingRateService;
import scrips.datamigration.sss.service.SssSecuritiesCodeService;
import scrips.datamigration.sss.service.SssSecuritiesCodeStatisticsService;
import scrips.datamigration.sss.service.SssSecuritiesFileDataService;
import scrips.datamigration.sss.service.SssSecuritiesPositionStatsService;
import scrips.datamigration.sss.service.SssSecuritiesPriceService;
import scrips.datamigration.sss.service.SssTransactionService;


@Controller
public class ScripsFileUploadController {
	private final Logger logger = LogManager.getLogger(ScripsFileUploadController.class);
	@Autowired
	FileUploadService fileService;

	@Autowired
	AccountService accountService;

	@Autowired
	AccountPositionService accountPositionService;

	@Autowired
	SssAllotmentService sssAllotmentService;

	@Autowired
	SssSecuritiesPriceService securitiesPriceService;

	@Autowired
	SssSecuritiesPositionStatsService securitiesPositionStats;

	@Autowired
	SssSecuritiesFileDataService sssSecuritiesFileDataService;

	@Autowired
	SssTransactionService sssTransactionService;

	@Autowired
	SssFloatingRateService sssFloatingRatesService;

	@Autowired
	SssSecuritiesCodeService sssSecuritiesCodeService;

	@Autowired
	CbmCostCentreService cbmCostCentreService;

	@Autowired
	CbmGlAccountService cbmGlAccountService;

	@Autowired
	CbmDepositRateService cbmDepositRateService;

	@Autowired
	CbmLiabilitiesBaseService cbmLiabilitiesBaseService;

	@Autowired
	CbmLiabilitiesBaseDetailService cbmLiabilitiesBaseDetailService;

	@Autowired
	SssMemberDAO sssMemberDAO;

	@Autowired
	SssSecuritiesCodeStatisticsService sssSecuritiesCodeStatisticsService;

	@Autowired
	CbmSoraRateService cbmSoraRateService;

	@Autowired
	SftpService sftpService;

	@Autowired
	private Environment env;

	@Autowired
	private FkChecksEnableDisable fkChecksEnableDisable;

	public String migrateFile(String fileUploadCode, BindingResult errors) throws Exception {
		String result = null;
		try {
			fkChecksEnableDisable.disableFkChecks();
			fkChecksEnableDisable.fkStatus();

			CommonUtils.createFolder("logs", env.getProperty("remotefolderpath") + "/");

			List<String> fileRecords = new ArrayList<String>();
			logger.info("fileUploadCode - {}", fileUploadCode);
			if (fileUploadCode != null) {
				// Get file details from FILE_UPLOAD_HEADER DB to connect to FTP
				JpaFileUploadHeader fileHeaderObj = fileService.getFileHeaderByCode(fileUploadCode);
				logger.info("Live table name ->{}", fileHeaderObj.getLiveTableName());
				// perform validation and get table column details using FILE_UPLOAD_DETAILS

				if (fileHeaderObj != null) {
					List<JpaFileUploadDetails> draftDBDetails = fileService.getFileDetailsByCode(fileUploadCode);

					if (fileUploadCode.equals("SSS_SECURITIES_CODE_FILE_UPLOAD")
							|| fileUploadCode.equals("SSS_SECURITIES_CODE_STATISTICS_FILE_UPLOAD")
							|| fileUploadCode.equals("SSS_SECURITIES_POSITION_STATS_FILE_UPLOAD")
							|| fileUploadCode.equals("SSS_SECURITIES_PRICE_FILE_UPLOAD")) {
						fileRecords = sssSecuritiesFileDataService.loadSecuritiesDataFile(fileUploadCode,
								fileHeaderObj);
						// fileData.stream().forEach(a -> logger.info("fileData - " + a));
					} else
						fileRecords = fileService.readFileFromFTP(fileHeaderObj, fileUploadCode);

					if (fileRecords != null && fileRecords.size() > 0) {
						fileRecords.stream().forEach(a -> logger.info("fileRecords - " + a));
						draftDBDetails.stream()
								.forEach(fud -> logger.info(fud.getSequenceNo() + ":" + fud.getTableFieldName()));
						if (fileRecords != null) {
							switch (fileHeaderObj.getLiveTableName()) {

							case "account":
								result = accountService.migrateAccount(fileHeaderObj, draftDBDetails, fileRecords);
								break;

							case "account_position":
								result = accountPositionService.migrateAccountPosition(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;

							case "sss_transaction":
								result = sssTransactionService.migrateSssTransaction(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;

							case "allotment":
								result = sssAllotmentService.migrateSssAllotment(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;

							case "sss_securities_price":
								result = securitiesPriceService.migrateSssSecuritiesPrice(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;

							case "sss_securities_position_stats":
								result = securitiesPositionStats.migrateSssSecuritiesPositionStats(fileHeaderObj,
										draftDBDetails, fileRecords);
								break;

							case "floating_rate":
								sssFloatingRatesService.migrateSssFloatingRates(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;

							case "securities_code":
								result = sssSecuritiesCodeService.migrateSssSecuritiesCode(fileHeaderObj,
										draftDBDetails, fileRecords);
								break;

							case "sss_securities_code_statistics":
								result = sssSecuritiesCodeStatisticsService
										.migrateSssSecuritiesCodeStatistics(fileHeaderObj, draftDBDetails, fileRecords);
								break;

							case "cbm_deposit_rate":
								result = cbmDepositRateService.migrateCbmDepositRates(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;

							case "cbm_liabilities_base":
								result = cbmLiabilitiesBaseService.migrateCbmLiabilitiesBase(fileHeaderObj,
										draftDBDetails, fileRecords);
								break;

							case "cbm_liabilities_base_detail":
								result = cbmLiabilitiesBaseDetailService.migrateCbmLiabilitiesBaseDetail(fileHeaderObj,
										draftDBDetails, fileRecords);
								break;

							case "cbm_cost_centre":
								result = cbmCostCentreService.migrateCbmCostCentre(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;
							case "cbm_gl_account":
								result = cbmGlAccountService.migrateCbmGlAccount(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;
							case "cbm_sora_rate":
								result = cbmSoraRateService.migrateCbmSoraRate(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;

							default:
								System.out.println("default case");
								break;
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			fkChecksEnableDisable.enableFkChecks();
			fkChecksEnableDisable.fkStatus();
			moveSourceFile(result, fileUploadCode);
		}
		return result;
	}

	private void moveSourceFile(String result, String fileUploadCode) throws IOException {
		if (result != null && env.getProperty("FTP.ENABLED") != null
				&& env.getProperty("FTP.ENABLED").trim().equalsIgnoreCase("y")) {
			if (result.equals("hasErrors")) {
				sftpService.moveSourcefileBasedOnMigrationStatus(false, env.getProperty("FTP.FILE_PATH"),
						env.getProperty(fileUploadCode + ".FILE_NAME"));
			} else {
				sftpService.moveSourcefileBasedOnMigrationStatus(true, env.getProperty("FTP.FILE_PATH"),
						env.getProperty(fileUploadCode + ".FILE_NAME"));
			}

			if (result != null && fileUploadCode.equalsIgnoreCase("SSS_SECURITIES_CODE_FILE_UPLOAD")) {
				if (result.equals("hasErrors")) {
					sftpService.moveSourcefileBasedOnMigrationStatus(false, env.getProperty("FTP.FILE_PATH"),
							env.getProperty("STEPUP_FILE_NAME"));
				} else {
					sftpService.moveSourcefileBasedOnMigrationStatus(true, env.getProperty("FTP.FILE_PATH"),
							env.getProperty("STEPUP_FILE_NAME"));
				}
			}
		}
	}

}