package scrips.datamigration.rtgs.service;

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
import scrips.datamigration.rtgs.model.JpaCbmDepositRate;
import scrips.datamigration.rtgs.model.JpaCbmDepositRateSource;
import scrips.datamigration.rtgs.model.JpaCbmDepositRateTemp;
import scrips.datamigration.rtgs.repository.JpaCbmDepositRateDAO;
import scrips.datamigration.rtgs.repository.JpaCbmDepositRateSourceDAO;
import scrips.datamigration.rtgs.repository.JpaCbmDepositRateTempDAO;

@Service
public class CbmDepositRateService {

	private final Logger logger = LogManager.getLogger(CbmDepositRateService.class);
	
	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	private UserData userData;

	@Autowired
	JpaCbmDepositRateDAO jpaCbmDepositRateDAO;

	@Autowired
	JpaCbmDepositRateTempDAO jpaCbmDepositRateTempDAO;
	@Autowired
	JpaCbmDepositRateSourceDAO cbmDepositRateSourceDAO;
	@Autowired
	private Environment env;

	public String migrateCbmDepositRates(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords)  {
		List<JpaCbmDepositRateSource> cbmDepositRateSourceObjList = createAndSaveCbmDepositRateSourceData(fileRecords,
				draftDBDetails);
		List<JpaCbmDepositRateTemp> cbmDepositRateTempList = convertDepositRateSourceToDepositRateTemp(
				cbmDepositRateSourceObjList);
		boolean noErrors =saveDepositRateLiveTableData(cbmDepositRateTempList);
		return (cbmDepositRateSourceObjList.size()==cbmDepositRateTempList.size() && noErrors)? "noErrors" : "hasErrors";
	}

	private boolean saveDepositRateLiveTableData(List<JpaCbmDepositRateTemp> cbmDepositRateTempList) {
		boolean noErrors = true;
		for (JpaCbmDepositRateTemp cbmDepositRateTemp : cbmDepositRateTempList) {
			if (cbmDepositRateTemp.getRemarks() == null || cbmDepositRateTemp.getRemarks().isEmpty()
					|| cbmDepositRateTemp.getRemarks().isBlank()) {

				try {
					JpaCbmDepositRate cbmDepositRate = converttoJpaCbmDepositRate(cbmDepositRateTemp);
					String remarks = validationJpaCbmDepositRateLiveData(cbmDepositRate);

					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						cbmDepositRateTemp.setRemarks(String.join(", ", remarks));
						jpaCbmDepositRateTempDAO.save(cbmDepositRateTemp);
					} else
						jpaCbmDepositRateDAO.save(cbmDepositRate);

				} catch (Exception e) {
					cbmDepositRateTemp.setRemarks("error while saving deposit rate live table data");
					e.printStackTrace();
					jpaCbmDepositRateTempDAO.save(cbmDepositRateTemp);
					noErrors= false;

				}
			}
		}
		return noErrors;
	}

	private String validationJpaCbmDepositRateLiveData(JpaCbmDepositRate cbmDepositRate) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(String.valueOf(cbmDepositRate.getTenorPeriod()), "numeric",
				"Tenor Period"));
		remarks.append(regexValidation.regexValidator(String.valueOf(cbmDepositRate.getDepositRate()), "decimal",
				"Deposit Rate"));

		if (cbmDepositRate.getModifiedBy() != null && !cbmDepositRate.getModifiedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(cbmDepositRate.getModifiedBy(), "length36", "Modified By"));
		}
		if (cbmDepositRate.getApprovedBy() != null && !cbmDepositRate.getApprovedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(cbmDepositRate.getApprovedBy(), "length36", "Approved By"));
		}
		return remarks.toString().trim();
	}

	private List<JpaCbmDepositRateSource> createAndSaveCbmDepositRateSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaCbmDepositRateSource> cbmDepositRateSourceList = new ArrayList<JpaCbmDepositRateSource>();

		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter=CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 1, draftDBDetails.size(),delimiter);
				if (error != null) {
					JpaCbmDepositRateSource jpaDepositRateSource = new JpaCbmDepositRateSource();
					jpaDepositRateSource.setRemarks(error);
					cbmDepositRateSourceDAO.save(jpaDepositRateSource);
					continue;
				}

				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line,delimiter);
				JpaCbmDepositRateSource jpaCbmDepositRateSource = JpaCbmDepositRateSource.builder().id(id)
						.tenorPeriod(dbRecordsMap.get("tenor_period")).depositRate(dbRecordsMap.get("deposit_rate"))
						.facilityId(null).action(null).status(null).modifiedBy(null).modifiedDate(null).approvedBy(null)
						.approvedDate(null).createdDate(null).effectiveDate(null).approvalRemark(null)
						.workflowStatusId(null).build();

				String remarks = validationJpaCbmDepositRateSource(jpaCbmDepositRateSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				jpaCbmDepositRateSource.setRemarks(remarks);
				cbmDepositRateSourceList.add(jpaCbmDepositRateSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return cbmDepositRateSourceList;
	}

	private String validationJpaCbmDepositRateSource(JpaCbmDepositRateSource cbmDepositRates) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(cbmDepositRates.getTenorPeriod(), "numeric", "Tenor Period"));
		remarks.append(regexValidation.regexValidator(cbmDepositRates.getDepositRate(), "decimal", "Deposit Rate"));
//		remarks.append(regexValidation.regexValidator(cbmDepositRates.getDepositRate(), "length13",
//				"Deposit Rate"));	
		return remarks.toString().trim();
	}

	private List<JpaCbmDepositRateTemp> convertDepositRateSourceToDepositRateTemp(
			List<JpaCbmDepositRateSource> depositRateSourceList) {
		List<JpaCbmDepositRateTemp> jpaCbmDepositRateList = new ArrayList<JpaCbmDepositRateTemp>();

		String id = userData.getSystemUserId();
		for (JpaCbmDepositRateSource sourceRec : depositRateSourceList) {
			cbmDepositRateSourceDAO.save(sourceRec);
			try {
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {
					List<String> validationRemarks = new ArrayList<String>();

					Date date = new Date();
					JpaCbmDepositRateTemp jpaCbmDepositRateTemp = JpaCbmDepositRateTemp.builder().id(sourceRec.getId())
							.tenorPeriod(Integer.parseInt(sourceRec.getTenorPeriod()))
							.depositRate(Double.parseDouble(sourceRec.getDepositRate())).facilityId("SF").action(" ")
							.status("ACTIVE").modifiedBy(id).modifiedDate(date).approvedBy(id).approvedDate(date)
							.createdDate(date).effectiveDate(date).approvalRemark("Data Migration")
							.workflowStatusId(null).build();
					if (validationRemarks.size() > 0) {
						jpaCbmDepositRateTemp.setRemarks(String.join(", ", validationRemarks));
					}

					jpaCbmDepositRateTempDAO.save(jpaCbmDepositRateTemp);

					jpaCbmDepositRateList.add(jpaCbmDepositRateTemp);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving depositRateTemp table data");
				cbmDepositRateSourceDAO.save(sourceRec);
			}
		}
		return jpaCbmDepositRateList;
	}

	private JpaCbmDepositRate converttoJpaCbmDepositRate(JpaCbmDepositRateTemp cbmDepositRateTempObj) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaCbmDepositRate jpaCbmDepositRate = mapper.map(cbmDepositRateTempObj, JpaCbmDepositRate.class);
		return jpaCbmDepositRate;
	}
}
