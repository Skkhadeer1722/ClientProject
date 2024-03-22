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
import scrips.datamigration.rtgs.model.JpaCbmSoraRate;
import scrips.datamigration.rtgs.model.JpaCbmSoraRateSource;
import scrips.datamigration.rtgs.model.JpaCbmSoraRateTemp;
import scrips.datamigration.rtgs.repository.JpaCbmSoraRateDAO;
import scrips.datamigration.rtgs.repository.JpaCbmSoraRateSourceDAO;
import scrips.datamigration.rtgs.repository.JpaCbmSoraRateTempDAO;

@Service
public class CbmSoraRateService {
	private final Logger logger = LogManager.getLogger(CbmSoraRateService.class);

	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	JpaCbmSoraRateDAO jpaCbmSoraRateDAO;

	@Autowired
	JpaCbmSoraRateTempDAO jpaCbmSoraRateTempDAO;

	@Autowired
	private UserData userData;
	
	@Autowired
	private Environment env;

	@Autowired
	JpaCbmSoraRateSourceDAO cbmSoraRateSourceDAO;

	public String migrateCbmSoraRate(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) {
		List<JpaCbmSoraRateSource> cbmSoraRateSourceList = createAndSaveCbmSoraRateSourceData(fileRecords,
				draftDBDetails);
		List<JpaCbmSoraRateTemp> cbmSoraRateTempList = convertSoraRateSourceToSoraRateTemp(cbmSoraRateSourceList);
		boolean noErrors =saveSoraRateLiveTableData(cbmSoraRateTempList);
		return (cbmSoraRateSourceList.size()==cbmSoraRateTempList.size() && noErrors)? "noErrors" : "hasErrors";
	}

	private boolean saveSoraRateLiveTableData(List<JpaCbmSoraRateTemp> cbmSoraRateTempList) {
		boolean noErrors = true;
		for (JpaCbmSoraRateTemp cbmSoraRateTemp : cbmSoraRateTempList) {
			if (cbmSoraRateTemp.getRemarks() == null || cbmSoraRateTemp.getRemarks().isEmpty()
					|| cbmSoraRateTemp.getRemarks().isBlank()) {
				try {
					JpaCbmSoraRate cbmSoraRate = converttoJpaCbmSoraRate(cbmSoraRateTemp);
					String remarks = duplicateKey(cbmSoraRate);

					remarks = remarks.concat(validationJpaCbmSoraRateLiveData(cbmSoraRate));
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						cbmSoraRateTemp.setRemarks(String.join(", ", remarks));
						jpaCbmSoraRateTempDAO.save(cbmSoraRateTemp);
					} else
						jpaCbmSoraRateDAO.save(cbmSoraRate);
				} catch (Exception e) {
					cbmSoraRateTemp.setRemarks("error while saving cbm sora rate live table data");
					e.printStackTrace();
					jpaCbmSoraRateTempDAO.save(cbmSoraRateTemp);
					noErrors= false;

				}
			}
		}
		return noErrors;
	}
	
	private String validationJpaCbmSoraRateLiveData(JpaCbmSoraRate cbmSoraRate) {
		StringBuffer remarks = new StringBuffer("");

		remarks.append(regexValidation.regexValidator(cbmSoraRate.getReferenceRate(), "alphabetswithspace",
				"Reference Rate"));
		remarks.append(
				regexValidation.regexValidator(cbmSoraRate.getReferenceRate(), "length20", "Reference Rate"));
		remarks.append(
				regexValidation.regexValidator(String.valueOf(cbmSoraRate.getPublicationDate()), "numeric", "Publication Date"));
		remarks.append(regexValidation.regexValidator(String.valueOf(cbmSoraRate.getValueDate()), "numeric", "Value Date"));
		if (cbmSoraRate.getModifiedBy() != null && !cbmSoraRate.getModifiedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(cbmSoraRate.getModifiedBy(), "length36", "Modified By"));
		}
		if (cbmSoraRate.getApprovedBy() != null && !cbmSoraRate.getApprovedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(cbmSoraRate.getApprovedBy(), "length36", "Approved By"));
		}
		
		return remarks.toString().trim();
	}

	private String duplicateKey(JpaCbmSoraRate cbmSoraRate) {
		JpaCbmSoraRate duplicateKey = jpaCbmSoraRateDAO.findByReferenceRateAndValueDate(cbmSoraRate.getReferenceRate(),
				cbmSoraRate.getValueDate());
		String remarks = "";
		if (duplicateKey != null) {
			remarks = remarks.concat(",Duplicate ReferenceRate,Duplicate ValueDate");
		}
		return remarks;
	}

		private JpaCbmSoraRate converttoJpaCbmSoraRate(JpaCbmSoraRateTemp cbmSoraRateTemp) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaCbmSoraRate jpaCbmSoraRate = mapper.map(cbmSoraRateTemp, JpaCbmSoraRate.class);
		return jpaCbmSoraRate;
	}

	private List<JpaCbmSoraRateSource> createAndSaveCbmSoraRateSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaCbmSoraRateSource> cbmSoraRateSourceList = new ArrayList<JpaCbmSoraRateSource>();

		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter=CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 3, draftDBDetails.size(),delimiter);
				if (error != null) {
					JpaCbmSoraRateSource jpaCbmSoraRateSource = new JpaCbmSoraRateSource();
					jpaCbmSoraRateSource.setRemarks(error);
					cbmSoraRateSourceDAO.save(jpaCbmSoraRateSource);
					continue;
				}

				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line,delimiter);

				JpaCbmSoraRateSource cbmSoraRateSource = JpaCbmSoraRateSource.builder().id(id)
						.referenceRate(dbRecordsMap.get("reference_rate"))
						.publicationDate(dbRecordsMap.get("publication_date")).valueDate(dbRecordsMap.get("value_date"))
						.rate(dbRecordsMap.get("rate")).action(null).status(null).modifiedBy(null).modifiedDate(null)
						.approvedBy(null).approvedDate(null).createdDate(null).effectiveDate(null).approvalRemark(null)
						.workflowStatusId(null).build();
				String remarks = validationJpaCbmSoraRateSource(cbmSoraRateSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				cbmSoraRateSource.setRemarks(remarks);
				cbmSoraRateSourceList.add(cbmSoraRateSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return cbmSoraRateSourceList;
	}

	private String validationJpaCbmSoraRateSource(JpaCbmSoraRateSource cbmSoraRateSource) {
		StringBuffer remarks = new StringBuffer("");

		remarks.append(regexValidation.regexValidator(cbmSoraRateSource.getReferenceRate(), "alphabetswithspace",
				"Reference Rate"));
		remarks.append(
				regexValidation.regexValidator(cbmSoraRateSource.getReferenceRate(), "length20", "Reference Rate"));
		remarks.append(
				regexValidation.regexValidator(cbmSoraRateSource.getPublicationDate(), "numeric", "Publication Date"));
		remarks.append(regexValidation.regexValidator(cbmSoraRateSource.getValueDate(), "numeric", "Value Date"));

		if (cbmSoraRateSource.getRate() != null && !cbmSoraRateSource.getRate().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(cbmSoraRateSource.getRate(), "decimal", "Rate"));
			remarks.append(regexValidation.regexValidator(cbmSoraRateSource.getRate(), "length13", "Rate"));
		}
		return remarks.toString().trim();
	}

	private List<JpaCbmSoraRateTemp> convertSoraRateSourceToSoraRateTemp(
			List<JpaCbmSoraRateSource> cbmSoraRateSourceList) {
		List<JpaCbmSoraRateTemp> cbmSoraRateTempList = new ArrayList<JpaCbmSoraRateTemp>();
		String id = userData.getSystemUserId();
		for (JpaCbmSoraRateSource sourceRec : cbmSoraRateSourceList) {
			cbmSoraRateSourceDAO.save(sourceRec);
			try {
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {

					Date date = new Date();
					JpaCbmSoraRateTemp jpaCbmSoraRateTemp = JpaCbmSoraRateTemp.builder().id(sourceRec.getId())
							.referenceRate(sourceRec.getReferenceRate())
							.publicationDate(Integer.parseInt(sourceRec.getPublicationDate()))
							.valueDate(Integer.parseInt(sourceRec.getValueDate()))
							.rate(!CommonUtils.isNullOrEmpty(sourceRec.getRate()) ? null
									: Double.parseDouble(sourceRec.getRate()))
							.action(" ").status("ACTIVE").modifiedBy(id).modifiedDate(date).approvedBy(id)
							.approvedDate(date).createdDate(date).effectiveDate(date).approvalRemark("Data Migration")
							.workflowStatusId(null).build();

					jpaCbmSoraRateTempDAO.save(jpaCbmSoraRateTemp);
					cbmSoraRateTempList.add(jpaCbmSoraRateTemp);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving cbm sora rate temp table data");
				cbmSoraRateSourceDAO.save(sourceRec);
			}
		}
		return cbmSoraRateTempList;
	}

}
