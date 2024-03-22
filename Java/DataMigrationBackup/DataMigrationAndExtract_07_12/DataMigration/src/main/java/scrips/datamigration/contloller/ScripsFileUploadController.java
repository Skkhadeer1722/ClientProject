package scrips.datamigration.contloller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.jcraft.jsch.SftpException;

import scrips.datamigration.business.AccountPositionServvice;
//import scrips.datamigration.business.AccountPositionServvice;
import scrips.datamigration.business.AccountService;
import scrips.datamigration.business.CbmCostCenterService;
import scrips.datamigration.business.CbmDepositRateService;
import scrips.datamigration.business.CbmSoraRateService;
import scrips.datamigration.business.CbmGlAccountService;
import scrips.datamigration.business.CbmLiabilitiesBaseDetailService;
import scrips.datamigration.business.CbmLiabilitiesBaseService;
import scrips.datamigration.business.FileUploadService;
import scrips.datamigration.business.FkChecksEnableDisable;
import scrips.datamigration.business.MemberService;
import scrips.datamigration.business.SftpService;
import scrips.datamigration.business.SssAccountService;
import scrips.datamigration.business.SssAllotmentService;
import scrips.datamigration.business.SssFloatingRatesService;
import scrips.datamigration.business.SssMemberService;
import scrips.datamigration.business.SssSecuritesPositionStatsService;
import scrips.datamigration.business.SssSecuritiesCodeService;
import scrips.datamigration.business.SssSecuritiesCodeStatisticsService;
import scrips.datamigration.business.SssSecuritiesPriceService;
import scrips.datamigration.business.SssTransactionService;
import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.jpa.fileupload.JpaFileUploadDetails;
import scrips.datamigration.jpa.fileupload.JpaFileUploadHeader;
import scrips.datamigration.jpa.sss.Member.SssMemberDAO;

/**
 * @author Siva Kuruva
 */

//@RestController
//@RequestMapping("/scrips/migrate")
//@Api(value="SCRIPS migrate")
@Controller
public class ScripsFileUploadController {
	private final Logger logger = LogManager.getLogger(ScripsFileUploadController.class);
	@Autowired
	FileUploadService fileService;

	@Autowired
	MemberService memberService;

	@Autowired
	AccountService accountService;

	@Autowired
	AccountPositionServvice accountPositionServvice;

	@Autowired
	SssAllotmentService sssAllotmentService;

	@Autowired
	SssSecuritiesPriceService securityPriceService;

	@Autowired
	SssSecuritesPositionStatsService securityPosStats;

	@Autowired
	SssAccountService sssAccountService;

	@Autowired
	SssTransactionService sssTransactionService;

	@Autowired
	SssFloatingRatesService sssFloatingRatesService;

	@Autowired
	SssSecuritiesCodeService sssSecuritiesCodeService;

	@Autowired
	SssMemberService sssMemberService;

	@Autowired
	CbmCostCenterService cbmCostCenterService;

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
	CbmSoraRateService cbmFloatingRateService;

	@Autowired
	SftpService sftpService;
	@Autowired
	private Environment env;

	@Autowired
	private FkChecksEnableDisable fkChecksEnableDisable;

	private static List<String> fileNames = null;

	@PostConstruct
	public void loadData() {
		fileNames = Arrays.asList("SSS_SECURITIES_CODE_FILE_UPLOAD", "MEMBER_FILE_UPLOAD");
		// fileNames = Arrays.asList(env.getProperty("MIGRATION_FILE.NAMES"));
	}

	public void migrateFile() throws Exception {
		File directoryPath = null;
		directoryPath = new File("./MigrationFiles/");
		// List of all files and directories
		String contents[] = directoryPath.list();
		logger.info("List of files and directories in the specified directory:");
		if (contents != null && contents.length > 0) {
			for (String fileName : contents) {
				fileName = fileName.substring(0, fileName.indexOf("."));
				if (fileNames.contains(fileName)) {
					logger.info("process started for :" + fileName);
					migrateFile(fileName, null);
				}
			}
		}
	}

	// @RequestMapping(value = "/fromfiletodb", method = RequestMethod.POST)
	public ResponseEntity<String> migrateFile(@RequestBody String fileUploadCode, BindingResult errors)
			throws Exception {
		String result = "Failed to migrate";
		boolean status = true;
		boolean sftpException = false;
		try {
			fkChecksEnableDisable.disableFkChecks();
			fkChecksEnableDisable.fkStatus();

			CommonUtils.createFolder("logs", env.getProperty("remotefolderpath")+"/");

			List<String> fileRecords = new ArrayList<String>();
			logger.info("fileUploadCode - {}", fileUploadCode);
			if (fileUploadCode != null) {
				// Get file details from FILE_UPLOAD_HEADER DB to connect to FTP
				JpaFileUploadHeader fileHeaderObj = fileService.getFileHeaderByCode(fileUploadCode);
				logger.info("Live table name ->{}", fileHeaderObj.getLiveTableName());
				// perform validation and get table column details using FILE_UPLOAD_DETAILS

				if (fileHeaderObj != null) {
					List<JpaFileUploadDetails> draftDBDetails = fileService.getFileDetailsByCode(fileUploadCode);
					fileRecords = fileService.readFileFromFTP(fileHeaderObj, fileUploadCode);
					if (fileRecords != null && fileRecords.size() > 0) {
						fileRecords.stream().forEach(a -> logger.info("fileRecords - " + a));
						draftDBDetails.stream()
								.forEach(fud -> logger.info(fud.getSequenceNo() + ":" + fud.getTableFieldName()));
						if (fileRecords != null) {
							switch (fileHeaderObj.getLiveTableName()) {
							case "member":
								result = memberService.migrateMember(fileHeaderObj, draftDBDetails, fileRecords);
								break;

							case "account":
								result = accountService.migrateAccount(fileHeaderObj, draftDBDetails, fileRecords);
								break;

							case "account_position":
								result = accountPositionServvice.migrateAccountPosition(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;

							case "sss_account":
								result = sssAccountService.migrateSssAccount(fileHeaderObj, draftDBDetails,
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
								result = securityPriceService.migrateSssSecurityPrice(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;

							case "sss_securities_position_stats":
								result = securityPosStats.migrateSssSecurityPos(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;

							case "sss_floating_rates":
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
							case "sss_member":
								result = sssMemberService.migrateSssMember(fileHeaderObj, draftDBDetails, fileRecords);
								break;
							case "cbm_cost_centre":
								result = cbmCostCenterService.migrateCbmCostCentre(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;
							case "cbm_gl_account":
								result = cbmGlAccountService.migrateCbmGlAccount(fileHeaderObj, draftDBDetails,
										fileRecords);
								break;
							case "cbm_sora_rate":
								result = cbmFloatingRateService.migrateCbmFloatingRates(fileHeaderObj, draftDBDetails,
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
		} catch (SftpException e) {
			e.printStackTrace();
			sftpException = true;
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
			throw e;
		} finally {
			fkChecksEnableDisable.enableFkChecks();
			fkChecksEnableDisable.fkStatus();
			if (env.getProperty("FTP.ENABLED") != null && env.getProperty("FTP.ENABLED").trim().equalsIgnoreCase("y")
					&& !sftpException) {
				if (!status || result.equals("hasErrors")) {
					sftpService.moveSourcefileBasedOnMigrationStatus(false, env.getProperty("FTP.FILE_PATH"),
							env.getProperty(fileUploadCode + ".FILE_NAME"));
				} else {
					sftpService.moveSourcefileBasedOnMigrationStatus(true, env.getProperty("FTP.FILE_PATH"),
							env.getProperty(fileUploadCode + ".FILE_NAME"));
				}

				if (fileUploadCode.equalsIgnoreCase("SSS_SECURITIES_CODE_FILE_UPLOAD")) {
					if (!status || result.equals("hasErrors")) {
						sftpService.moveSourcefileBasedOnMigrationStatus(false, env.getProperty("FTP.FILE_PATH"),
								env.getProperty("STEPUP_FILE_NAME"));
					} else {
						sftpService.moveSourcefileBasedOnMigrationStatus(true, env.getProperty("FTP.FILE_PATH"),
								env.getProperty("STEPUP_FILE_NAME"));
					}
				}
			}
			
		}
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}