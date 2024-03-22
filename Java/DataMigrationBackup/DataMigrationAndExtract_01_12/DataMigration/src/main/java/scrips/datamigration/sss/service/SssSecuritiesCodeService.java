package scrips.datamigration.sss.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.common.UserData;
import scrips.datamigration.common.service.SftpService;
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.regex.RegexValidation;
import scrips.datamigration.sss.model.JpaCouponShedules;
import scrips.datamigration.sss.model.JpaIssuer;
import scrips.datamigration.sss.model.JpaSecuritiesCoupon;
import scrips.datamigration.sss.model.JpaSecuritiesType;
import scrips.datamigration.sss.model.JpaSssMember;
import scrips.datamigration.sss.model.JpaSssSecuritiesCode;
import scrips.datamigration.sss.model.JpaSssSecuritiesCodeSource;
import scrips.datamigration.sss.model.JpaSssSecuritiesCodeTemp;
import scrips.datamigration.sss.model.JpaStepupCoupon;
import scrips.datamigration.sss.model.JpaTaxScheme;
import scrips.datamigration.sss.repository.CouponScheduleDAO;
import scrips.datamigration.sss.repository.IssuerDao;
import scrips.datamigration.sss.repository.SecuritiesCouponDAO;
import scrips.datamigration.sss.repository.SecuritiesTypeDao;
import scrips.datamigration.sss.repository.SssMemberDAO;
import scrips.datamigration.sss.repository.SssSecuritiesCodeDAO;
import scrips.datamigration.sss.repository.SssSecuritiesCodeSourceDAO;
import scrips.datamigration.sss.repository.SssSecuritiesCodeTempDAO;
import scrips.datamigration.sss.repository.TaxSchemeDao;

@Service
public class SssSecuritiesCodeService {
	private final Logger log = LogManager.getLogger(SssSecuritiesCodeService.class);
	@Autowired
	private RegexValidation regexValidation;
	@Autowired
	private Environment env;

	@Autowired
	private IssuerDao issuerDao;

	@Autowired
	private SssSecuritiesCodeSourceDAO sssSecuritiesCodeSourceDAO;

	@Autowired
	SssSecuritiesCodeDAO sssSecuritiesCodeDAO;

	@Autowired
	SssSecuritiesCodeTempDAO sssSecuritiesCodeTempDAO;

	@Autowired
	CouponScheduleDAO couponSheduleDAO;

	@Autowired
	SecuritiesCouponDAO securitiesCouponDAO;

	@Autowired
	SftpService sftpService;

	@Autowired
	private UserData userData;

	@Autowired
	private TaxSchemeDao taxSchemeDao;
	@Autowired
	private SecuritiesTypeDao securitiesTypeDao;
	@Autowired
	private SssMemberDAO sssMemberDAO;

	public String migrateSssSecuritiesCode(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) {
		Map<String, JpaStepupCoupon> map = new HashMap<String, JpaStepupCoupon>();
		try {
			map = loadStepUpData(fileHeaderObj);
		} catch (Exception e) {
			log.error("errror while reading stepup data {}", e.getMessage());
			map = null;
		}
		if (map != null && !map.isEmpty()) {
			List<JpaSssSecuritiesCodeSource> securitiesSourceList = createAndSaveSecuritiesCodeSourceData(fileRecords,
					draftDBDetails);
			List<JpaSssSecuritiesCodeTemp> securitiesCodeTempList = convertToSssSecuritiesCodeTempList(
					securitiesSourceList);
			boolean noErrors = saveSecuritiesCodeLiveTableData(securitiesCodeTempList, map);
			return (securitiesSourceList.size() == securitiesCodeTempList.size() && noErrors) ? "noErrors"
					: "hasErrors";
		}
		return "noError";
	}

	private boolean saveSecuritiesCodeLiveTableData(List<JpaSssSecuritiesCodeTemp> securitiesCodeTempList,
			Map<String, JpaStepupCoupon> map) {
		boolean noErrors = true;
		for (JpaSssSecuritiesCodeTemp securitiesCodeTempObj : securitiesCodeTempList) {
			if (securitiesCodeTempObj.getRemarks() == null || securitiesCodeTempObj.getRemarks().isEmpty()
					|| securitiesCodeTempObj.getRemarks().isBlank()) {
				try {
					JpaSssSecuritiesCode securitiesCode = convertToJpaSssSecuritiesCode(securitiesCodeTempObj);
					String remarks = duplicateSecuritiesCode(securitiesCode);
					remarks = remarks.concat(validateSecuritiesCodeLiveData(securitiesCode));
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						securitiesCodeTempObj.setRemarks(String.join(", ", remarks));
						sssSecuritiesCodeTempDAO.save(securitiesCodeTempObj);

					} else {
						sssSecuritiesCodeDAO.save(securitiesCode);
						try {
							saveSecuritiesCoupon(securitiesCode);
							saveCouponSheduleData(securitiesCode, map);
						} catch (Exception e) {
							log.error("error while saving Coupon Schedule or coupon data {}", e.getMessage());
							e.printStackTrace();
							noErrors = false;
							securitiesCodeTempObj.setRemarks("error while saving Coupon Schedule data");
							sssSecuritiesCodeTempDAO.save(securitiesCodeTempObj);
						}
					}
				} catch (Exception e) {
					securitiesCodeTempObj.setRemarks("error while saving securities code live table data");
					e.printStackTrace();
					noErrors = false;
					sssSecuritiesCodeTempDAO.save(securitiesCodeTempObj);
				}
			}
		}
		return noErrors;
	}

	private String duplicateSecuritiesCode(JpaSssSecuritiesCode securitiesCode) {
		JpaSssSecuritiesCode duplicateSecuritiesCode = sssSecuritiesCodeDAO
				.findBySecuritiesCode(securitiesCode.getSecuritiesCode());
		String remarks = "";
		if (duplicateSecuritiesCode != null)
			remarks = remarks.concat(",Duplicate securities_code");
		return remarks;
	}

	private String validateSecuritiesCodeLiveData(JpaSssSecuritiesCode securitiesCode) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(
				regexValidation.regexValidator(securitiesCode.getSecuritiesCode(), "special", "Securities Code"));
		remarks.append(
				regexValidation.regexValidator(securitiesCode.getSecuritiesCode(), "length12", "Securities Code"));
		remarks.append(regexValidation.regexValidator(securitiesCode.getIssueCode(), "length8", "Issue Code"));
		remarks.append(regexValidation.regexValidator(securitiesCode.getDescription(), "length45", "Description"));
		remarks.append(
				regexValidation.regexValidator(securitiesCode.getSecuritiesTypeId(), "length10", "Securities Type Id"));
		remarks.append(
				regexValidation.regexValidator(securitiesCode.getSecuritiesStatus(), "length9", "Securities Status"));
		remarks.append(regexValidation.regexValidator(securitiesCode.getSecuritiesTenorPeriodUnit(), "length10",
				"Securities TenorPeriod Unit"));
		remarks.append(regexValidation.regexValidator(securitiesCode.getCurrencyCode(), "length3", "Currency Code"));
		remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getDenomination()), "numeric",
				"Denomination"));
		remarks.append(regexValidation.regexValidator(securitiesCode.getIssuerId(), "length36", "IssuerId"));
		remarks.append(regexValidation.regexValidator(securitiesCode.getIpa(), "length36", "Ipa"));
		remarks.append(
				regexValidation.regexValidator(String.valueOf(securitiesCode.getTradable()), "numeric", "Tradable"));
		remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getFirstIssuanceDate()), "numeric",
				"First Issuance Date"));
		remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getFirstIssuanceAmount()),
				"numeric", "First Issuance Amount"));
		remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getRedemptionPrice()), "decimal",
				"Redemption Price"));
//	remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getRedemptionPrice()), "length9",
//			"Redemption Price"));
		remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getCentralBankAppliedAmount()),
				"numeric", "Central Bank Applied Amount"));
		remarks.append(
				regexValidation.regexValidator(securitiesCode.getCouponStructure(), "length14", "Coupon Structure"));
		remarks.append(regexValidation.regexValidator(securitiesCode.getCouponPaymentFrequency(), "length18",
				"Coupon Payment Frequancy"));
		remarks.append(regexValidation.regexValidator(securitiesCode.getDayCountConvention(), "length20",
				"Day Count Convenstion"));
		remarks.append(
				regexValidation.regexValidator(securitiesCode.getRoundingOption(), "length20", "Rounding Option"));
		remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getRecordDatePeriod()), "numeric",
				"Record Date Period"));
		remarks.append(regexValidation.regexValidator(securitiesCode.getStatus(), "length30", "Status"));
		remarks.append(regexValidation.regexValidator(securitiesCode.getModifiedBy(), "length36", "Modified By"));
		remarks.append(regexValidation.regexValidator(securitiesCode.getBenchmarkIndicator(), "length36",
				"Benchmark Indicator"));

		if (securitiesCode.getFacilityEligibilityId() != null) {
			remarks.append(regexValidation.regexValidator(securitiesCode.getFacilityEligibilityId(), "length10",
					"Facility Eligibility Id"));
		}
		if (securitiesCode.getHaircutId() != null) {
			remarks.append(regexValidation.regexValidator(securitiesCode.getHaircutId(), "length35", "Haircut Id"));
		}
		if (securitiesCode.getRedemptionDate() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getRedemptionDate()), "numeric",
					"Redemption Date"));
		}
		if (securitiesCode.getOptionalRedemptionDate() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getOptionalRedemptionDate()),
					"numeric", "Optional Redemption Date"));
		}
		if (securitiesCode.getOptionalRedemptionPrice() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getOptionalRedemptionPrice()),
					"decimal", "Optional Redemption Price"));
//			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getOptionalRedemptionPrice()),
//					"length9", "Optional Redemption Price"));
		}
		if (securitiesCode.getCouponRate() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getCouponRate()), "decimal",
					"Coupon Rate"));
//			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getCouponRate()), "length8",
//					"Coupon Rate"));
		}
		if (securitiesCode.getAverageYield() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getAverageYield()), "decimal",
					"Average Yield"));
//			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getAverageYield()), "length9",
//					"Average Yield"));
		}
		if (securitiesCode.getTenorPeriod() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getTenorPeriod()), "numeric",
					"Tenor Period"));
		}
		if (securitiesCode.getIssuePrice() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getIssuePrice()), "decimal",
					"Issue Price"));
//			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getIssuePrice()), "length8",
//					"Issue Price"));
		}
		if (securitiesCode.getIssueYield() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getIssueYield()), "decimal",
					"Issue Yield"));
//			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getIssueYield()), "length5",
//					"Issue Yield"));
		}
		if (securitiesCode.getFirstCouponDate() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCode.getFirstCouponDate()),
					"numeric", "First Coupon Date"));
		}
		return remarks.toString().trim();
	}

	private Map<String, JpaStepupCoupon> loadStepUpData(JpaFileUploadHeader fileHeaderObj) throws Exception {
		Map<String, JpaStepupCoupon> map = new HashMap<String, JpaStepupCoupon>();
		log.info("ftp status {}", env.getProperty("FTP.ENABLED"));
		String ftpStatus = env.getProperty("FTP.ENABLED");
		if (ftpStatus != null && ftpStatus.trim().equalsIgnoreCase("y")) {
			try {
				map.clear();
				List<String> fileRecords = sftpService.readFileFromSFTPDirectroy(env.getProperty("FTP.FILE_PATH"),
						env.getProperty("STEPUP_FILE_NAME"));
				for (String str : fileRecords) {
					String id = UUID.randomUUID().toString();
//					String arr1[] = (str).split("\\|");
					String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
					String arr1[] = (str).split(delimiter);
					JpaStepupCoupon coupon = JpaStepupCoupon.builder().id(id).securityCode(arr1[0])
							.couponPaymentDate(arr1[1]).couponRate(arr1[2]).build();
					map.put(id, coupon);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				InputStream fileStream = null;
				fileStream = new BufferedInputStream(new FileInputStream(env.getProperty("remotefolderpath") + "/"
						+ "file/MigrationFiles/" + env.getProperty("STEPUP_FILE_NAME")));
				Scanner sc = new Scanner(fileStream).useDelimiter("\\A");
				while (sc.hasNextLine()) {
					String id = UUID.randomUUID().toString();
					String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
					String arr1[] = (sc.nextLine()).split(delimiter);
					JpaStepupCoupon coupon = JpaStepupCoupon.builder().id(id).securityCode(arr1[0])
							.couponPaymentDate(arr1[1]).couponRate(arr1[2]).build();
					map.put(id, coupon);
				}
				sc.close();
			} catch (Exception e) {
				log.error("error while parsing stepup file {}", e.getMessage());
				throw e;
			}
		}
		return map;
	}

	private List<JpaSssSecuritiesCodeSource> createAndSaveSecuritiesCodeSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaSssSecuritiesCodeSource> sssSecuritiesCodeSourceList = new ArrayList<JpaSssSecuritiesCodeSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 21, draftDBDetails.size(), delimiter);
				if (error != null) {
					JpaSssSecuritiesCodeSource jpaSssSecuritiesCodeSource = new JpaSssSecuritiesCodeSource();
					jpaSssSecuritiesCodeSource.setRemarks(error);
					sssSecuritiesCodeSourceDAO.save(jpaSssSecuritiesCodeSource);
					continue;
				}
				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line, delimiter);
				String remarks = "";

				JpaSssSecuritiesCodeSource jpaSssSecuritiesCodeSource = JpaSssSecuritiesCodeSource.builder().id(id)
						.securitiesCode(dbRecordsMap.get("securities_code")).issueCode(dbRecordsMap.get("issue_code"))
						.description(dbRecordsMap.get("description")).securitiesTypeId(null)
						.securitiesStatus(dbRecordsMap.get("securities_status")).securitiesTenorPeriodUnit(null)
						.currencyCode(dbRecordsMap.get("currency_code")).denomination(dbRecordsMap.get("denomination"))
						.issuerId(null).ipa(null).facilityEligibilityId(null).haircutId(null).tradable(null)
						.firstAuctionDate(dbRecordsMap.get("first_auction_date"))
						.firstIssuanceDate(dbRecordsMap.get("first_issuance_date"))
						.firstIssuanceAmount(dbRecordsMap.get("first_issuance_amount"))
						.redemptionDate(dbRecordsMap.get("redemption_date"))
						.optionalRedemptionDate(dbRecordsMap.get("optional_redemption_date"))
						.redemptionPrice(dbRecordsMap.get("redemption_price"))
						.optionalRedemptionPrice(dbRecordsMap.get("optional_redemption_price"))
						.centralBankAppliedAmount(dbRecordsMap.get("central_bank_applied_amount")).couponStructure(null)
						.couponPaymentFrequency(dbRecordsMap.get("coupon_payment_frequency"))
						.couponRate(dbRecordsMap.get("coupon_rate")).averageYield(dbRecordsMap.get("average_yield"))
						.dayCountConvention(null).roundingOption(null)
						.recordDatePeriod(dbRecordsMap.get("record_date_period")).action(null).status(null)
						.modifiedBy(null).modifiedDate(null).approvedBy(null).approvedDate(null).createdDate(null)
						.effectiveDate(null).approvalRemark(dbRecordsMap.get("approval_remark"))
						.workflowStatusId(dbRecordsMap.get("workflow_status_id"))
						.tenorPeriod(dbRecordsMap.get("tenor_period")).issuePrice(dbRecordsMap.get("issue_price"))
						.issueYield(dbRecordsMap.get("issue_yield")).redemptionReimburse(null)
						.taxSchemeId(dbRecordsMap.get("tax_scheme_id"))
						.firstCouponDate(dbRecordsMap.get("first_coupon_date")).benchmarkIndicator(null)
						.remarks(remarks).build();
				remarks = remarks.concat(validationJpaSssSecuritiesCodeSource(jpaSssSecuritiesCodeSource));
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				jpaSssSecuritiesCodeSource.setRemarks(remarks);
				sssSecuritiesCodeSourceList.add(jpaSssSecuritiesCodeSource);
			}
			log.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			log.error("Exception while preparing source data ", e.getMessage());
			throw e;
		}
		return sssSecuritiesCodeSourceList;
	}

	private List<JpaSssSecuritiesCodeTemp> convertToSssSecuritiesCodeTempList(
			List<JpaSssSecuritiesCodeSource> securitiesSourceList) {
		List<JpaSssSecuritiesCodeTemp> sssSecuritiesCodeTempList = new ArrayList<JpaSssSecuritiesCodeTemp>();
		for (JpaSssSecuritiesCodeSource sourceRec : securitiesSourceList) {
			sssSecuritiesCodeSourceDAO.saveAndFlush(sourceRec);

			try {
				String id = userData.getSystemUserId();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {
					List<String> validationRemarks = new ArrayList<String>();
					String masId = null;
					String agdId = null;
					String memberId = null;
					String facilityEligibilityId = null;
					String hairCutId = null;
					String benchMarkIndicator = null;
					String taxSchemeId = null;
					try {
						masId = getId(env.getProperty("MAS_ID"));
						agdId = getId(env.getProperty("AGD_ID"));
					} catch (Exception e) {
						validationRemarks.add("error reading issuer data");
					}
					try {
						List<JpaSssMember> memberList = sssMemberDAO.findByMemberType("CBK");
						if (memberList != null && !memberList.isEmpty())
							memberId = memberList.get(0).getId();
					} catch (Exception e) {
						validationRemarks.add("error reading member data");
					}
					try {
						List<JpaSecuritiesType> securitiesTypeList = securitiesTypeDao.findAll();
						if (securitiesTypeList != null && !securitiesTypeList.isEmpty()) {
							facilityEligibilityId = securitiesTypeList.get(0).getFacilityEligibilityId();
							hairCutId = securitiesTypeList.get(0).getHaircutId();
							benchMarkIndicator = securitiesTypeList.get(0).getBenchmarkIndicator();
						}
					} catch (Exception e) {
						validationRemarks.add("error reading securitiesType data");
					}
					try {
						List<JpaTaxScheme> taxSchemaList = taxSchemeDao.findByTaxSchemeId("SGWHT23");
						if (taxSchemaList != null && !taxSchemaList.isEmpty())
							taxSchemeId = taxSchemaList.get(0).getId();
					} catch (Exception e) {
						validationRemarks.add("error reading TaxScheme data");
					}
					String issuerId = "";
					String securityType = "";
					String securitiesTenorPeriod = "";
					String dayCountConvention = "";

					if (memberId == null)
						validationRemarks.add("Missing Member id for Mermber type CBK");

					if (masId == null || agdId == null)
						validationRemarks.add("Missing issuerId data");

					String couponStructure = sourceRec.getCouponStructure();

					Date issueDateAfterTwoHundDay = null;

					if (CommonUtils.validateParseDate_yyyyMMdd(sourceRec.getFirstIssuanceDate())) {
						Date firstIssueDate = simpleDateFormat.parse(sourceRec.getFirstIssuanceDate());
						Calendar c = Calendar.getInstance();
						c.setTime(firstIssueDate);
						c.add(Calendar.DAY_OF_YEAR, 200);
						String dateAfterSixMonths = simpleDateFormat.format(c.getTime());
						issueDateAfterTwoHundDay = simpleDateFormat.parse(dateAfterSixMonths);
					} else
						validationRemarks.add("Invalid firstIssuanceDate");

					Date redemptionDate = null;
					if (CommonUtils.validateParseDate_yyyyMMdd(sourceRec.getRedemptionDate()))
						redemptionDate = simpleDateFormat.parse(sourceRec.getRedemptionDate());
					else
						validationRemarks.add("Invalid redemptionDate");

					BigDecimal couponRate = null;
					if (sourceRec.getCouponRate() != null) {
						couponRate = CommonUtils.convertStringToDecimal(sourceRec.getCouponRate(), 8, 5);
						if (couponRate == null) {
							validationRemarks.add("Invalid couponRate");
						}
					}

					Integer recordDatePeriod = null;
					if (CommonUtils.validateParseInteger(sourceRec.getRecordDatePeriod()))
						recordDatePeriod = Integer.parseInt(sourceRec.getRecordDatePeriod()) + 1;
					else
						validationRemarks.add("Invalid recordDatePeriod");
					String issuerCode = sourceRec.getIssueCode();
					if (issuerCode.startsWith("BA")) {
						securityType = "CMTB";
						couponStructure = "Zero coupon";
//							securitiesTenorPeriod = "yearly";
						dayCountConvention = " ";
						issuerId = agdId;
					} else if (issuerCode.startsWith("M") || issuerCode.startsWith("M1")) {
						securityType = "MASBill";
						couponStructure = "Zero coupon";
						securitiesTenorPeriod = "daily";
						dayCountConvention = " ";
						issuerId = agdId;
					} else if (issuerCode.startsWith("B") && !(issuerCode.startsWith("BA"))) {
						securityType = "TBill";
						couponStructure = "Zero coupon";
						securitiesTenorPeriod = "daily";
						dayCountConvention = " ";
						issuerId = agdId;
					} else if (issuerCode.startsWith("N") && issuerCode.endsWith("2")) {
						securityType = "SGSISBond";
						couponStructure = "Fixed rate";
						securitiesTenorPeriod = "yearly";
						dayCountConvention = "30/360";
						issuerId = agdId;
					} else if (issuerCode.startsWith("N") && !(issuerCode.endsWith("3"))) {
						securityType = "SGSISGBond";
						couponStructure = "Fixed rate";
						securitiesTenorPeriod = "yearly";
						dayCountConvention = "30/360";
						issuerId = agdId;
					} else if (issuerCode.startsWith("N") && !(issuerCode.endsWith("2"))
							&& !(issuerCode.endsWith("3"))) {
						securityType = "SGSMDBond";
						couponStructure = "Fixed rate";
						securitiesTenorPeriod = "yearly";
						dayCountConvention = "30/360";
						issuerId = agdId;
					} else if (issuerCode.startsWith("M1")) {
						securityType = "MASRIMFRN";
						couponStructure = "Floating rate";
						securitiesTenorPeriod = "monthly";
						couponRate = null;
						issuerId = masId;
						if (issueDateAfterTwoHundDay.compareTo(redemptionDate) <= 0) {
							dayCountConvention = "Actual/Actual";
						} else
							dayCountConvention = "Actual/365";

					} else if (issuerCode.startsWith("GX")) {
						securityType = "SSB";
						couponStructure = "Fixed Multiple";
						securitiesTenorPeriod = "yearly";
						couponRate = null;
						dayCountConvention = "30/360";
						issuerId = agdId;

					} else {
						validationRemarks.add("Invalid Issue code");
					}
					Date date = new Date();

					BigDecimal redemtionPrice = null;
					if (sourceRec.getRedemptionPrice() != null) {
						redemtionPrice = CommonUtils.convertStringToDecimal(sourceRec.getRedemptionPrice(), 9, 5);
						if (redemtionPrice == null)
							validationRemarks.add("Invalid Redemption Price");
					}
					BigDecimal optionalRedemtionPrice = null;
					if (sourceRec.getOptionalRedemptionPrice() != null) {
						optionalRedemtionPrice = CommonUtils
								.convertStringToDecimal(sourceRec.getOptionalRedemptionPrice(), 9, 5);
						if (optionalRedemtionPrice == null)
							validationRemarks.add("Invalid Optional Redemption Price");
					}
					BigDecimal issueYield = null;
					if (sourceRec.getIssueYield() != null) {
						issueYield = CommonUtils.convertStringToDecimal(sourceRec.getIssueYield(), 5, 2);
						if (issueYield == null)
							validationRemarks.add("Invalid Issue Yield");
					}
					BigDecimal avgYield = null;
					if (sourceRec.getAverageYield() != null) {
						avgYield = CommonUtils.convertStringToDecimal(sourceRec.getAverageYield(), 9, 5);
						if (avgYield == null)
							validationRemarks.add("Invalid Average Yield");
					}
					BigDecimal issuePrice = null;
					if (sourceRec.getIssuePrice() != null) {
						issuePrice = CommonUtils.convertStringToDecimal(sourceRec.getIssuePrice(), 8, 5);
						if (issuePrice == null)
							validationRemarks.add("Invalid Issue Price");
					}

					if (couponStructure == null)
						validationRemarks.add("Invalid couponStructure");

					if (validationRemarks.size() > 0) {
						sourceRec.setRemarks(String.join(", ", validationRemarks));
						sssSecuritiesCodeSourceDAO.save(sourceRec);
						continue;
					}

					JpaSssSecuritiesCodeTemp jpaSssSecuritiesCodeTemp = (JpaSssSecuritiesCodeTemp.builder())
							.id(sourceRec.getId()).securitiesCode(sourceRec.getSecuritiesCode()).issueCode(issuerCode)
							.description(sourceRec.getDescription()).securitiesTypeId(securityType)
							.securitiesStatus(sourceRec.getSecuritiesStatus())
							.securitiesTenorPeriodUnit(securitiesTenorPeriod).currencyCode(sourceRec.getCurrencyCode())
							.denomination(Long.parseLong(sourceRec.getDenomination())).issuerId(issuerId).ipa(memberId)
							.facilityEligibilityId(facilityEligibilityId).haircutId(hairCutId)
							.tradable(Short.parseShort("1"))
							.firstAuctionDate(new SimpleDateFormat("yyyyMMdd").parse(sourceRec.getFirstAuctionDate()))
							.firstIssuanceDate(Integer.parseInt(sourceRec.getFirstIssuanceDate()))
							.firstIssuanceAmount(Long.parseLong(sourceRec.getFirstIssuanceAmount()))
							.redemptionDate(sourceRec.getRedemptionDate() == null
									|| sourceRec.getRedemptionDate().trim().isEmpty() ? null
											: Integer.parseInt(sourceRec.getRedemptionDate()))
							.optionalRedemptionDate(sourceRec.getOptionalRedemptionDate() == null
									|| sourceRec.getOptionalRedemptionDate().trim().isEmpty() ? null
											: Integer.parseInt(sourceRec.getOptionalRedemptionDate()))
							.redemptionPrice(redemtionPrice).optionalRedemptionPrice(optionalRedemtionPrice)
							.centralBankAppliedAmount(Long.parseLong(sourceRec.getCentralBankAppliedAmount()))
							.couponStructure(couponStructure)
							.couponPaymentFrequency(sourceRec.getCouponPaymentFrequency()).couponRate(couponRate)
							.averageYield(avgYield).dayCountConvention(dayCountConvention).roundingOption("Nearest")
							.recordDatePeriod(recordDatePeriod).action(" ").status("ACTIVE").modifiedBy(id)
							.modifiedDate(date).approvedBy(id).approvedDate(date).createdDate(date).effectiveDate(date)
							.approvalRemark("Data Migration").workflowStatusId(null)
							.tenorPeriod(
									sourceRec.getTenorPeriod() == null || sourceRec.getTenorPeriod().trim().isEmpty()
											? null
											: Integer.parseInt(sourceRec.getTenorPeriod()))
							.issuePrice(issuePrice)
//							.issueYield(Double.parseDouble(sourceRec.getIssueYield()))
							 .issueYield(issueYield)
							// .redemptionReimburse(dbRecordsMap.get("redemption_reimburse"))
							.redemptionReimburse("Fixed maturity").taxSchemeId(taxSchemeId)
							.firstCouponDate(sourceRec.getFirstCouponDate() == null
									|| sourceRec.getFirstCouponDate().trim().isEmpty() ? null
											: Integer.parseInt(sourceRec.getFirstCouponDate()))
							.benchmarkIndicator(benchMarkIndicator).build();
					if (validationRemarks.size() > 0) {
						jpaSssSecuritiesCodeTemp.setRemarks(String.join(", ", validationRemarks));
					}
					sssSecuritiesCodeTempDAO.save(jpaSssSecuritiesCodeTemp);
					sssSecuritiesCodeTempList.add(jpaSssSecuritiesCodeTemp);
				}
			} catch (Exception e) {
				log.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("error while saving securities code temp data");
				sssSecuritiesCodeSourceDAO.save(sourceRec);
			}
		}
		return sssSecuritiesCodeTempList;
	}

	private void saveSecuritiesCoupon(JpaSssSecuritiesCode securitiesCode) {
		JpaSecuritiesCoupon jpaSecuritiesCoupon = (JpaSecuritiesCoupon.builder())
				.securitiesCode(securitiesCode.getSecuritiesCode()).action(securitiesCode.getAction())
				.status(securitiesCode.getStatus()).modifiedBy(securitiesCode.getModifiedBy())
				.modifiedDate(securitiesCode.getModifiedDate()).approvedBy(securitiesCode.getApprovedBy())
				.approvedDate(securitiesCode.getApprovedDate()).createdDate(securitiesCode.getCreatedDate())
				.effectiveDate(securitiesCode.getEffectiveDate()).approvalRemark(securitiesCode.getApprovalRemark())
				.workflowStatusId(securitiesCode.getWorkflowStatusId()).build();
		securitiesCouponDAO.save(jpaSecuritiesCoupon);
	}

	private void saveCouponSheduleData(JpaSssSecuritiesCode securitiesCode, Map<String, JpaStepupCoupon> map)
			throws ParseException, SQLException {
		if (securitiesCode.getFirstCouponDate() != null && securitiesCode.getRedemptionDate() != null) {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			Date couponDate = simpleDateFormat.parse(securitiesCode.getFirstCouponDate().toString());
			log.info("coupon date : {}", couponDate);
			Date redemptionDate = simpleDateFormat.parse(securitiesCode.getRedemptionDate().toString());
			log.info("redemptionDate : {}", redemptionDate);
			c.setTime(couponDate);
			c.add(Calendar.MONTH, 6);
			String cDate1 = simpleDateFormat.format(couponDate);
			String dateAfterSixMonths = simpleDateFormat.format(c.getTime());
			Date dateAfterSixMonths2 = simpleDateFormat.parse(dateAfterSixMonths);
			int q = 0;
//		if (securitiesCode.getCouponPaymentFrequency().equals("3")) {

			while (dateAfterSixMonths2.compareTo(redemptionDate) <= 0) {
				q++;
				String s2 = simpleDateFormat.format(dateAfterSixMonths2);
				BigDecimal couponRate = securitiesCode.getCouponRate();
				if (securitiesCode.getSecuritiesTypeId().equals("SSB"))// SSB
				{
					for (String s : map.keySet()) {
						log.info(securitiesCode.getSecuritiesCode() + "  " + map.get(s).getSecurityCode());
						if (securitiesCode.getSecuritiesCode().equals(map.get(s).getSecurityCode())) {
							JpaCouponShedules couponSchedule = JpaCouponShedules.builder()
//									.id(UUID.randomUUID().toString())
									.securitiesCode(map.get(s).getSecurityCode())
									.couponDate(map.get(s).getCouponPaymentDate())
//		                            .security(jpaSssSecuritiesCode)
									.couponRate(map.get(s).getCouponRate()).build();
							couponSheduleDAO.save(couponSchedule);
							log.info("ssb  addeded successfully.....");
						}
					}
				} else if (securitiesCode.getSecuritiesTypeId().equals("MASRIMFRN")) {
					if (q == 1) {
						JpaCouponShedules couponSchedule = JpaCouponShedules.builder()
//								.id(UUID.randomUUID().toString())
								.securitiesCode(securitiesCode.getSecuritiesCode()).couponDate(cDate1).couponRate(null)
								.build();
						couponSheduleDAO.save(couponSchedule);
					}
					JpaCouponShedules couponSchedule = JpaCouponShedules.builder()
//							.id(UUID.randomUUID().toString())
							.securitiesCode(securitiesCode.getSecuritiesCode()).couponDate(s2).couponRate(null).build();
					couponSheduleDAO.save(couponSchedule);
				} else if (securitiesCode.getSecuritiesTypeId().equals("SGSISBond")) {
					if (q == 1) {
						JpaCouponShedules couponSchedule = JpaCouponShedules.builder()
//								.id(UUID.randomUUID().toString())
								.securitiesCode(securitiesCode.getSecuritiesCode()).couponDate(cDate1)
								.couponRate(couponRate.toString()).build();
						couponSheduleDAO.save(couponSchedule);
						couponRate = couponRate.add(new BigDecimal("0.1"));
					}
					JpaCouponShedules couponSchedule = JpaCouponShedules.builder()
//							.id(UUID.randomUUID().toString())
							.securitiesCode(securitiesCode.getSecuritiesCode()).couponDate(s2)
							.couponRate(couponRate.toString()).build();
					couponSheduleDAO.save(couponSchedule);
					couponRate = couponRate.add(new BigDecimal("0.1"));
				}
				c.setTime(dateAfterSixMonths2);
				c.add(Calendar.MONTH, 6);
				String ds = simpleDateFormat.format(c.getTime());
				dateAfterSixMonths2 = simpleDateFormat.parse(ds);
			}
		}
	}

	private JpaSssSecuritiesCode convertToJpaSssSecuritiesCode(JpaSssSecuritiesCodeTemp securitiesCodeTempObj) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaSssSecuritiesCode securitiesCodeObj = mapper.map(securitiesCodeTempObj, JpaSssSecuritiesCode.class);
		return securitiesCodeObj;
	}

	private String getId(String code) throws SQLException {
		List<JpaIssuer> issuerList = issuerDao.findByIssuerCode(code);
		if (issuerList == null || issuerList.isEmpty())
			return null;
		else
			return issuerList.get(0).getId();
	}

	private String validationJpaSssSecuritiesCodeSource(JpaSssSecuritiesCodeSource securitiesCodeSource) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(
				regexValidation.regexValidator(securitiesCodeSource.getSecuritiesCode(), "special", "Securities Code"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getSecuritiesCode(), "length12",
				"Securities Code"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getIssueCode(), "special", "Issue Code"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getIssueCode(), "length8", "Issue Code"));
		remarks.append(
				regexValidation.regexValidator(securitiesCodeSource.getDescription(), "length45", "Description"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getSecuritiesStatus(), "special",
				"Securities Status"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getSecuritiesStatus(), "length9",
				"Securities Status"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getCurrencyCode(), "currencycode",
				"Currency Code"));
		remarks.append(
				regexValidation.regexValidator(securitiesCodeSource.getCurrencyCode(), "length3", "Currency Code"));
		remarks.append(
				regexValidation.regexValidator(securitiesCodeSource.getDenomination(), "numeric", "Denomination"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getFirstIssuanceDate(), "numeric",
				"First Issuance Date"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getFirstIssuanceAmount(), "numeric",
				"First Issuance Amount"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getRedemptionPrice(), "decimal",
				"Redemption Price"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getRedemptionPrice(), "length9",
				"Redemption Price"));

		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getCentralBankAppliedAmount(), "numeric",
				"Central Bank Applied Amount"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getCouponPaymentFrequency(), "special",
				"Coupon Payment Frequency"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getCouponPaymentFrequency(), "length18",
				"Coupon Payment Frequency"));
		remarks.append(regexValidation.regexValidator(securitiesCodeSource.getRecordDatePeriod(), "numeric",
				"Record Date Period"));

		// Default null fields
		if (securitiesCodeSource.getRedemptionDate() != null
				&& !securitiesCodeSource.getRedemptionDate().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(securitiesCodeSource.getRedemptionDate(), "numeric",
					"Redemption Date"));
		}
		if (securitiesCodeSource.getOptionalRedemptionDate() != null
				&& !securitiesCodeSource.getOptionalRedemptionDate().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(securitiesCodeSource.getOptionalRedemptionDate(), "numeric",
					"Optional Redemption Date"));
		}
		if (securitiesCodeSource.getOptionalRedemptionPrice() != null
				&& !securitiesCodeSource.getOptionalRedemptionPrice().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(securitiesCodeSource.getOptionalRedemptionPrice(), "decimal",
					"Optional Redemption Price"));
			remarks.append(regexValidation.regexValidator(securitiesCodeSource.getOptionalRedemptionPrice(), "length9",
					"Optional Redemption Price"));
		}
		if (securitiesCodeSource.getCouponRate() != null && !securitiesCodeSource.getCouponRate().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(securitiesCodeSource.getCouponRate(), "decimal", "Coupon Rate"));
			remarks.append(
					regexValidation.regexValidator(securitiesCodeSource.getCouponRate(), "length8", "Coupon Rate"));

		}
		if (securitiesCodeSource.getAverageYield() != null
				&& !securitiesCodeSource.getAverageYield().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(securitiesCodeSource.getAverageYield(), "decimal", "Average Yield"));
			remarks.append(
					regexValidation.regexValidator(securitiesCodeSource.getAverageYield(), "length9", "Average Yield"));

		}
		if (securitiesCodeSource.getTenorPeriod() != null && !securitiesCodeSource.getTenorPeriod().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(securitiesCodeSource.getTenorPeriod(), "numeric", "Tenor Period"));
		}
		if (securitiesCodeSource.getIssuePrice() != null && !securitiesCodeSource.getIssuePrice().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(securitiesCodeSource.getIssuePrice(), "decimal", "Issue Price"));
			remarks.append(
					regexValidation.regexValidator(securitiesCodeSource.getIssuePrice(), "length8", "Issue Price"));

		}
		if (securitiesCodeSource.getIssueYield() != null && !securitiesCodeSource.getIssueYield().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(securitiesCodeSource.getIssueYield(), "decimal", "Issue Yield"));
			remarks.append(
					regexValidation.regexValidator(securitiesCodeSource.getIssueYield(), "length5", "Issue Yield"));

		}
		if (securitiesCodeSource.getFirstCouponDate() != null
				&& !securitiesCodeSource.getFirstCouponDate().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(securitiesCodeSource.getFirstCouponDate(), "numeric",
					"First Coupon Date"));
		}
		// Default Null Dates
		if (securitiesCodeSource.getFirstAuctionDate() != null
				&& !securitiesCodeSource.getFirstAuctionDate().trim().isEmpty()) {

			if (!CommonUtils.validateParseDate_yyyyMMdd(securitiesCodeSource.getFirstAuctionDate()))
				remarks.append(",Invalid First Auction Date");
		}
		return remarks.toString().trim();
	}
}