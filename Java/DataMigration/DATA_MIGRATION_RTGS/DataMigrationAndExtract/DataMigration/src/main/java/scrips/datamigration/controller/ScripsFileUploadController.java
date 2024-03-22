package scrips.datamigration.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.jcraft.jsch.ChannelSftp;

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

	private static List<String> migrationFileNames = null;

	@PostConstruct
	public void loadData() {
		migrationFileNames = Arrays.asList("ACCOUNT_FILE_UPLOAD", "ACCOUNT_POSITION_FILE_UPLOAD",
				"CBM_COST_CENTRE_FILE_UPLOAD", "CBM_SORA_RATE_FILE_UPLOAD", "CBM_DEPOSIT_RATE_FILE_UPLOAD",
				"CBM_LIABILITIES_BASE_FILE_UPLOAD", "CBM_LIABILITIES_BASE_DETAIL_FILE_UPLOAD", "CBM_GL_ACCOUNT_FILE_UPLOAD",
				"SSS_ALLOTMENT_FILE_UPLOAD", "SSS_FLOATING_RATE_FILE_UPLOAD", "SSS_SECURITIES_CODE_FILE_UPLOAD",
				"SSS_SECURITIES_CODE_STATISTICS_FILE_UPLOAD", "SSS_SECURITIES_PRICE_FILE_UPLOAD",
				"SSS_SECURITIES_POSITION_STATS_FILE_UPLOAD", "SSS_TRANSACTION_FILE_UPLOAD");
	}

	public String migrateFile(String fileUploadCode, BindingResult errors) throws Exception {
		String result = null;
		try {
			fkChecksEnableDisable.disableFkChecks();
			fkChecksEnableDisable.fkStatus();
			if (!preValidateHasErrors(fileUploadCode)) {

				List<String> fileRecords = new ArrayList<String>();
				logger.info("fileUploadCode - {}", fileUploadCode);
				if (fileUploadCode != null) {
					if (migrationFileNames.contains(fileUploadCode)) {
						// Get file details from FILE_UPLOAD_HEADER DB to connect to FTP
						JpaFileUploadHeader fileHeaderObj = fileService.getFileHeaderByCode(fileUploadCode);
						logger.info("Live table name ->{}", fileHeaderObj.getLiveTableName());
						// perform validation and get table column details using FILE_UPLOAD_DETAILS

						if (fileHeaderObj != null) {
							List<JpaFileUploadDetails> draftDBDetails = fileService
									.getFileDetailsByCode(fileUploadCode);

							if (fileUploadCode.equals("SSS_SECURITIES_CODE_FILE_UPLOAD")
									|| fileUploadCode.equals("SSS_SECURITIES_CODE_STATISTICS_FILE_UPLOAD")
									|| fileUploadCode.equals("SSS_SECURITIES_POSITION_STATS_FILE_UPLOAD")
									|| fileUploadCode.equals("SSS_SECURITIES_PRICE_FILE_UPLOAD")) {
								fileRecords = sssSecuritiesFileDataService.loadSecuritiesDataFile(fileUploadCode,
										fileHeaderObj);
								// fileData.stream().forEach(a -> logger.info("fileData - " + a));
							} else
								fileRecords = fileService.readFile(fileHeaderObj, fileUploadCode);

							if (fileRecords != null && fileRecords.size() > 0) {
								fileRecords.stream().forEach(a -> logger.info("fileRecords - " + a));
								draftDBDetails.stream().forEach(
										fud -> logger.info(fud.getSequenceNo() + ":" + fud.getTableFieldName()));
								if (fileRecords != null) {
									switch (fileHeaderObj.getLiveTableName()) {

									case "account":
										result = accountService.migrateAccount(fileHeaderObj, draftDBDetails,
												fileRecords);
										break;

									case "account_position":
										result = accountPositionService.migrateAccountPosition(fileHeaderObj,
												draftDBDetails, fileRecords);
										break;

									case "sss_transaction":
										result = sssTransactionService.migrateSssTransaction(fileHeaderObj,
												draftDBDetails, fileRecords);
										break;

									case "allotment":
										result = sssAllotmentService.migrateSssAllotment(fileHeaderObj, draftDBDetails,
												fileRecords);
										break;

									case "sss_securities_price":
										result = securitiesPriceService.migrateSssSecuritiesPrice(fileHeaderObj,
												draftDBDetails, fileRecords);
										break;

									case "sss_securities_position_stats":
										result = securitiesPositionStats.migrateSssSecuritiesPositionStats(
												fileHeaderObj, draftDBDetails, fileRecords);
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
										result = sssSecuritiesCodeStatisticsService.migrateSssSecuritiesCodeStatistics(
												fileHeaderObj, draftDBDetails, fileRecords);
										break;

									case "cbm_deposit_rate":
										result = cbmDepositRateService.migrateCbmDepositRates(fileHeaderObj,
												draftDBDetails, fileRecords);
										break;

									case "cbm_liabilities_base":
										result = cbmLiabilitiesBaseService.migrateCbmLiabilitiesBase(fileHeaderObj,
												draftDBDetails, fileRecords);
										break;

									case "cbm_liabilities_base_detail":
										result = cbmLiabilitiesBaseDetailService.migrateCbmLiabilitiesBaseDetail(
												fileHeaderObj, draftDBDetails, fileRecords);
										break;

									case "cbm_cost_centre":
										result = cbmCostCentreService.migrateCbmCostCentre(fileHeaderObj,
												draftDBDetails, fileRecords);
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
										logger.error("Default case");
										break;
									}
								}
							}
						}
					}
				} else
					logger.error("Invalid Migration FileName --> {}", fileUploadCode);
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

	private boolean preValidateHasErrors(String fileUploadCode) throws Exception {
		if (env.getProperty(fileUploadCode + ".FILE_NAME") == null
				|| env.getProperty(fileUploadCode + ".FILE_NAME").trim().isEmpty()) {
			logger.error("File name is missing in file upload properties");
			return true;
		}

		String fileExtention = FileNameUtils.getExtension(env.getProperty(fileUploadCode + ".FILE_NAME"));
		if (env.getProperty("FTP.ENABLED") != null && env.getProperty("FTP.ENABLED").trim().equalsIgnoreCase("y")) {
			if (env.getProperty("FTP.PORT") == null || env.getProperty("FTP.PORT").trim().isEmpty()
					|| !CommonUtils.validateParseInteger(env.getProperty("FTP.PORT"))) {
				logger.error("FTP.PORT property should not be empty or invalid");
				return true;
			}
			ChannelSftp channelSftp = sftpService.createChannelSftp();
			if (channelSftp == null) {
				logger.error(
						"Unable to connect sftp server,sftp server service not available or Please check the sftp properties");
				sftpService.disconnectChannelSftp(channelSftp);
				return true;
			} else {
				String path = null;
				try {
					channelSftp.cd(env.getProperty("FTP.FILE_PATH"));
					path = channelSftp.ls(env.getProperty(fileUploadCode + ".FILE_NAME")).toString();
				} catch (Exception e) {
					logger.error("File {} not present in sftp server, Please check and re run the process",
							env.getProperty(fileUploadCode + ".FILE_NAME"));
					sftpService.disconnectChannelSftp(channelSftp);
					return true;
				}
				if (!path.contains(env.getProperty(fileUploadCode + ".FILE_NAME"))) {
					logger.error("File {} not present in sftp server, Please check and re run the process",
							env.getProperty(fileUploadCode + ".FILE_NAME"));
					sftpService.disconnectChannelSftp(channelSftp);
					return true;
				} else {
					if (env.getProperty("ENCRYPTION.ENABLED") != null
							&& env.getProperty("ENCRYPTION.ENABLED").equalsIgnoreCase("y")) {

						if (!fileExtention.equalsIgnoreCase("enc")) {
							logger.error("Sftp file extention should be enc");
							sftpService.disconnectChannelSftp(channelSftp);
							return true;
						}
					} else if (fileExtention.equalsIgnoreCase("enc")) {
						logger.error("sftp file extention should not be enc");
						sftpService.disconnectChannelSftp(channelSftp);
						return true;
					}
				}
			}

		} else {
			File file = new File(env.getProperty("remotefolderpath") + "/" + "file/MigrationFiles/"
					+ env.getProperty(fileUploadCode + ".FILE_NAME"));
			if (!file.exists()) {
				logger.error("Source file {}  not present in {}file/MigrationFiles path",
						env.getProperty(fileUploadCode + ".FILE_NAME"), env.getProperty("remotefolderpath") + "/");
				return true;
			}

			if (fileExtention.equalsIgnoreCase("enc")) {
				logger.error("sftp file extention should not be enc");
				return true;
			}
		}
		return false;
	}

}