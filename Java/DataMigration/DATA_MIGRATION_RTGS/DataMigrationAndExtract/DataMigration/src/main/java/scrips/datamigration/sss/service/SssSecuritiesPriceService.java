package scrips.datamigration.sss.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.regex.RegexValidation;
import scrips.datamigration.rtgs.service.AccountPositionService;
import scrips.datamigration.sss.model.JpaSssSecuritiesCode;
import scrips.datamigration.sss.model.JpaSssSecuritiesPrice;
import scrips.datamigration.sss.model.JpaSssSecuritiesPriceSource;
import scrips.datamigration.sss.model.JpaSssSecuritiesPriceTemp;
import scrips.datamigration.sss.repository.SssSecuritiesCodeDAO;
import scrips.datamigration.sss.repository.SssSecuritiesPriceDAO;
import scrips.datamigration.sss.repository.SssSecuritiesPriceSourceDAO;
import scrips.datamigration.sss.repository.SssSecuritiesPriceTempDAO;

@Service
public class SssSecuritiesPriceService {
	private final Logger logger = LogManager.getLogger(AccountPositionService.class);

	@Autowired
	private Environment env;

	@Autowired
	private UserData userData;

	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	SssSecuritiesPriceDAO securitiesPriceDAO;

	@Autowired
	SssSecuritiesPriceTempDAO securitiesPriceTempDAO;

	@Autowired
	SssSecuritiesCodeDAO sssSecuritiesCodeDAO;
	@Autowired
	private SssSecuritiesPriceSourceDAO sssSecuritiesPriceSourceDAO;

	public String migrateSssSecuritiesPrice(JpaFileUploadHeader fileHeaderObj,
			List<JpaFileUploadDetails> draftDBDetails, List<String> fileRecords) {
		List<JpaSssSecuritiesPriceSource> securitiesPriceSourceList = createAndSaveSecuritiesPriceSourceData(
				fileRecords, draftDBDetails);
		List<JpaSssSecuritiesPriceTemp> securitiesPriceTempList = convertSecuritiesPriceSourceToSecuritiesPriceTemp(
				securitiesPriceSourceList);
		boolean noErrors = saveSecuritiesPriceLiveTableData(securitiesPriceTempList);
		return (securitiesPriceSourceList.size() == securitiesPriceTempList.size() && noErrors) ? "noErrors"
				: "hasErrors";
	}

	private List<JpaSssSecuritiesPriceSource> createAndSaveSecuritiesPriceSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaSssSecuritiesPriceSource> sssSecuritiesPriceSourceList = new ArrayList<JpaSssSecuritiesPriceSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 3, draftDBDetails.size(), delimiter);
				if (error != null) {
					JpaSssSecuritiesPriceSource securitiesPriceSource = new JpaSssSecuritiesPriceSource();
					securitiesPriceSource.setRemarks(error);
					sssSecuritiesPriceSourceDAO.save(securitiesPriceSource);
					continue;
				}
				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line, delimiter);
				Date date = new Date();
				JpaSssSecuritiesPriceSource jpasecuritiesPriceSource = JpaSssSecuritiesPriceSource.builder().id(id)
						.securitiesCode(dbRecordsMap.get("securities_code"))
						.pricingDate(dbRecordsMap.get("pricing_date")).pricingType(null).description(null)
						.price(dbRecordsMap.get("price")).issueCode(dbRecordsMap.get("issue_code")).action(null)
						.status(null).modifiedBy(null).modifiedDate(null).approvedBy(null).approvedDate(null)
						.createdDate(null).effectiveDate(null).approvalRemark(null).workflowStatusId(null)
						.migratedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date))
						.build();

				String remarks = validationSecuritiesPriceSource(jpasecuritiesPriceSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				jpasecuritiesPriceSource.setRemarks(remarks);
				sssSecuritiesPriceSourceList.add(jpasecuritiesPriceSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return sssSecuritiesPriceSourceList;
	}

	private String validationSecuritiesPriceSource(JpaSssSecuritiesPriceSource jpasecuritiesPriceSource) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(jpasecuritiesPriceSource.getSecuritiesCode(), "special",
				"Securities Code"));
		remarks.append(regexValidation.regexValidator(jpasecuritiesPriceSource.getSecuritiesCode(), "length12",
				"Securities Code"));
		remarks.append(
				regexValidation.regexValidator(jpasecuritiesPriceSource.getIssueCode(), "length20", "Issue Code"));

		remarks.append(
				regexValidation.regexValidator(jpasecuritiesPriceSource.getPricingDate(), "numeric", "Pricing Date"));
		remarks.append(regexValidation.regexValidator(jpasecuritiesPriceSource.getPrice(), "decimal", "Price"));
		remarks.append(regexValidation.regexValidator(jpasecuritiesPriceSource.getPrice(), "length8", "Price"));
		remarks.append(
				regexValidation.regexValidator(jpasecuritiesPriceSource.getIssueCode(), "special", "Issue Code"));
		return remarks.toString().trim();
	}

	private List<JpaSssSecuritiesPriceTemp> convertSecuritiesPriceSourceToSecuritiesPriceTemp(
			List<JpaSssSecuritiesPriceSource> securitiesPriceSourceList) {
		List<JpaSssSecuritiesPriceTemp> securitiesPriceTempList = new ArrayList<JpaSssSecuritiesPriceTemp>();
		String id = userData.getSystemUserId();
		for (JpaSssSecuritiesPriceSource sourceRec : securitiesPriceSourceList) {
			sssSecuritiesPriceSourceDAO.save(sourceRec);
			try {
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {
					JpaSssSecuritiesCode securitiesCode = sssSecuritiesCodeDAO
							.findBySecuritiesCode(sourceRec.getSecuritiesCode());
					List<String> validationRemarks = new ArrayList<String>();
					if (securitiesCode == null)
						validationRemarks.add("securities_code is not found in Securities Code Table");
					BigDecimal price = null;
					if (sourceRec.getPrice() != null) {
						price = CommonUtils.convertStringToDecimal(sourceRec.getPrice(), 8, 5);
						if (price == null) {
							validationRemarks.add("Invalid Price");
						}
					}

					Date date = new Date();
					JpaSssSecuritiesPriceTemp securitiesPriceTemp = JpaSssSecuritiesPriceTemp.builder()
							.id(sourceRec.getId()).securitiesCode(sourceRec.getSecuritiesCode())
							.pricingDate(Integer.parseInt(sourceRec.getPricingDate())).pricingType("T Price")
							.description("SGCANCELSHA1-MASCANI1").price(price).issueCode(sourceRec.getIssueCode())
							.action(null).status("ACTIVE").modifiedBy(id).modifiedDate(date).approvedBy(id)
							.approvedDate(date).createdDate(date).effectiveDate(date).approvalRemark("Data Migration")
							.workflowStatusId(null).build();

					if (validationRemarks.size() > 0) {
						securitiesPriceTemp.setRemarks(String.join(", ", validationRemarks));
					}
					securitiesPriceTempDAO.save(securitiesPriceTemp);
					securitiesPriceTempList.add(securitiesPriceTemp);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving securities_price_temp table data");
				sssSecuritiesPriceSourceDAO.save(sourceRec);
			}
		}
		return securitiesPriceTempList;
	}

	private boolean saveSecuritiesPriceLiveTableData(List<JpaSssSecuritiesPriceTemp> securitiesPriceTempList) {
		boolean noErrors = true;
		for (JpaSssSecuritiesPriceTemp securitiesPriceTemp : securitiesPriceTempList) {
			if (securitiesPriceTemp.getRemarks() == null || securitiesPriceTemp.getRemarks().isEmpty()
					|| securitiesPriceTemp.getRemarks().isBlank()) {
				try {
					JpaSssSecuritiesPrice securitiesPrice = convertToJpaSssSecuritiesPrice(securitiesPriceTemp);
					String remarks = validationSssSecuritiesPriceLiveData(securitiesPrice);
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						securitiesPriceTemp.setRemarks(String.join(", ", remarks));
						securitiesPriceTempDAO.save(securitiesPriceTemp);
					} else
						securitiesPriceDAO.save(securitiesPrice);
				} catch (Exception e) {
					securitiesPriceTemp.setRemarks("error while saving securities_price live table data");
					securitiesPriceTempDAO.save(securitiesPriceTemp);
					e.printStackTrace();
					noErrors = false;
				}
			}
		}
		return noErrors;
	}

	private JpaSssSecuritiesPrice convertToJpaSssSecuritiesPrice(JpaSssSecuritiesPriceTemp securitiesPriceTemp) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaSssSecuritiesPrice securitiesPrice = mapper.map(securitiesPriceTemp, JpaSssSecuritiesPrice.class);
		return securitiesPrice;
	}

	private String validationSssSecuritiesPriceLiveData(JpaSssSecuritiesPrice securitiesPrice) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(
				regexValidation.regexValidator(securitiesPrice.getSecuritiesCode(), "special", "Securities Code"));
		remarks.append(
				regexValidation.regexValidator(securitiesPrice.getSecuritiesCode(), "length12", "Securities Code"));
		remarks.append(regexValidation.regexValidator(String.valueOf(securitiesPrice.getPricingDate()), "numeric",
				"Pricing Date"));
		remarks.append(regexValidation.regexValidator(String.valueOf(securitiesPrice.getPrice()), "decimal", "Price"));
		remarks.append(regexValidation.regexValidator(securitiesPrice.getIssueCode(), "special", "Issue Code"));
		remarks.append(regexValidation.regexValidator(securitiesPrice.getIssueCode(), "length20", "Issue Code"));

		if (securitiesPrice.getModifiedBy() != null && !securitiesPrice.getModifiedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(securitiesPrice.getModifiedBy(), "length36", "Modified By"));
		}
		if (securitiesPrice.getApprovedBy() != null && !securitiesPrice.getApprovedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(securitiesPrice.getApprovedBy(), "length36", "Approved By"));
		}
		return remarks.toString().trim();
	}

}