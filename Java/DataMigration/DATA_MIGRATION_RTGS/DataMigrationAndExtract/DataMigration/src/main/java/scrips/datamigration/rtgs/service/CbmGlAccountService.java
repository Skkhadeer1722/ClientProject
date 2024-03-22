package scrips.datamigration.rtgs.service;

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
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.regex.RegexValidation;
import scrips.datamigration.rtgs.model.JpaCbmGlAccount;
import scrips.datamigration.rtgs.model.JpaCbmGlAccountSource;
import scrips.datamigration.rtgs.model.JpaCbmGlAccountTemp;
import scrips.datamigration.rtgs.repository.JpaCbmGlAccountDAO;
import scrips.datamigration.rtgs.repository.JpaCbmGlAccountSourceDAO;
import scrips.datamigration.rtgs.repository.JpaCbmGlAccountTempDAO;

@Service
public class CbmGlAccountService {
	private final Logger logger = LogManager.getLogger(CbmGlAccountService.class);

	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	JpaCbmGlAccountDAO glAccountDAO;

	@Autowired
	JpaCbmGlAccountTempDAO glAccountTempDAO;
	@Autowired
	JpaCbmGlAccountSourceDAO glAccountSourceDAO;
	@Autowired
	private Environment env;

	public String migrateCbmGlAccount(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) {
		List<JpaCbmGlAccountSource> cbmGlAccountSourceList = createAndSaveCbmGlAccountSourceData(fileRecords,
				draftDBDetails);
		List<JpaCbmGlAccountTemp> cbmGlAccountTempList = convertGlAccountSourceToGlAccountTemp(cbmGlAccountSourceList);
		boolean noErrors = saveGlAccountLiveTableData(cbmGlAccountTempList);
		return (cbmGlAccountSourceList.size() == cbmGlAccountTempList.size() && noErrors) ? "noErrors" : "hasErrors";
	}

	private List<JpaCbmGlAccountSource> createAndSaveCbmGlAccountSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaCbmGlAccountSource> glAccountSourceList = new ArrayList<JpaCbmGlAccountSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 1, draftDBDetails.size(), delimiter);
				if (error != null) {
					JpaCbmGlAccountSource cbmGlAccountSource = new JpaCbmGlAccountSource();
					cbmGlAccountSource.setRemarks(error);
					glAccountSourceDAO.save(cbmGlAccountSource);
					continue;
				}
				Date date = new Date();
				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line, delimiter);
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				JpaCbmGlAccountSource glAccountSource = JpaCbmGlAccountSource.builder().id(id).glAccountIndicator(null)
						.glAccount(dbRecordsMap.get("gl_account"))
						.glAccountDescription(dbRecordsMap.get("gl_account_description")).createdDate(null)
						.modifiedDate(null)
						.migratedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date))
						.build();

				String remarks = validationCbmGlAccountSource(glAccountSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				glAccountSource.setRemarks(remarks);
				glAccountSourceList.add(glAccountSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return glAccountSourceList;
	}

	private String validationCbmGlAccountSource(JpaCbmGlAccountSource cbmGlAccountSource) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(cbmGlAccountSource.getGlAccount(), "special", "GL Account"));
		remarks.append(regexValidation.regexValidator(cbmGlAccountSource.getGlAccount(), "length50", "GL Account"));
		remarks.append(regexValidation.regexValidator(cbmGlAccountSource.getGlAccountDescription(), "length50",
				"GL Account Description"));
//		remarks.append(regexValidation.regexValidator(cbmGlAccount.getGlAccountDescription(),"special", "GL Account Description"));
		return remarks.toString().trim();
	}

	private List<JpaCbmGlAccountTemp> convertGlAccountSourceToGlAccountTemp(
			List<JpaCbmGlAccountSource> cbmGlAccountSourceList) {
		List<JpaCbmGlAccountTemp> cbmGlAccountTempList = new ArrayList<JpaCbmGlAccountTemp>();
		for (JpaCbmGlAccountSource sourceRec : cbmGlAccountSourceList) {
			glAccountSourceDAO.save(sourceRec);
			try {
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {
					List<String> validationRemarks = new ArrayList<String>();
					Date date = new Date();
					JpaCbmGlAccountTemp jpaCbmGlAccountTemp = JpaCbmGlAccountTemp.builder()//
							.id(sourceRec.getId()).glAccountIndicator("Account 1").glAccount(sourceRec.getGlAccount())
							.glAccountDescription(sourceRec.getGlAccountDescription()).createdDate(date)
							.modifiedDate(date).build();

					if (validationRemarks.size() > 0) {
						jpaCbmGlAccountTemp.setRemarks(String.join(", ", validationRemarks));
					}

					glAccountTempDAO.save(jpaCbmGlAccountTemp);
					cbmGlAccountTempList.add(jpaCbmGlAccountTemp);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving gl_account_temp table data");
				glAccountSourceDAO.save(sourceRec);
			}
		}
		return cbmGlAccountTempList;
	}

	private boolean saveGlAccountLiveTableData(List<JpaCbmGlAccountTemp> cbmGlAccountTempList) {
		boolean noErrors = true;
		for (JpaCbmGlAccountTemp cbmGlAccountTemp : cbmGlAccountTempList) {
			if (cbmGlAccountTemp.getRemarks() == null || cbmGlAccountTemp.getRemarks().isEmpty()
					|| cbmGlAccountTemp.getRemarks().isBlank()) {
				try {
					JpaCbmGlAccount glAccountObj = converttoJpaCbmGlAccount(cbmGlAccountTemp);
					String remarks = duplicateGlAccount(glAccountObj);
					remarks = remarks.concat(validationCbmGlAccountLiveData(glAccountObj));
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						cbmGlAccountTemp.setRemarks(String.join(",", remarks));
						glAccountTempDAO.save(cbmGlAccountTemp);
					} else
						glAccountDAO.save(glAccountObj);
				} catch (Exception e) {
					cbmGlAccountTemp.setRemarks("error while saving gl_account live table data");
					e.printStackTrace();
					glAccountTempDAO.save(cbmGlAccountTemp);
					noErrors = false;
				}
			}
		}
		return noErrors;
	}

	private String validationCbmGlAccountLiveData(JpaCbmGlAccount cbmGlAccount) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(cbmGlAccount.getGlAccount(), "special", "GL Account"));
		remarks.append(regexValidation.regexValidator(cbmGlAccount.getGlAccount(), "length50", "GL Account"));
		remarks.append(regexValidation.regexValidator(cbmGlAccount.getGlAccountDescription(), "length50",
				"GL Account Description"));
		return remarks.toString().trim();
	}

	private String duplicateGlAccount(JpaCbmGlAccount glAccountObj) {
		JpaCbmGlAccount duplicateGlAccountId = glAccountDAO.findByGlAccount(glAccountObj.getGlAccount());
		String remarks = "";
		if (duplicateGlAccountId != null)
			remarks = remarks.concat(",Duplicate gl_account");
		return remarks;
	}

	private JpaCbmGlAccount converttoJpaCbmGlAccount(JpaCbmGlAccountTemp cbmGlAccountTemp) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaCbmGlAccount jpaCbmGlAccount = mapper.map(cbmGlAccountTemp, JpaCbmGlAccount.class);
		return jpaCbmGlAccount;
	}
}