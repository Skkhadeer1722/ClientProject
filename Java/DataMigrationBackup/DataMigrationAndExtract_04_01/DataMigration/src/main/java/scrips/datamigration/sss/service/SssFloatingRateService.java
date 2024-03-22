package scrips.datamigration.sss.service;

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
import scrips.datamigration.sss.model.JpaSssFloatingRates;
import scrips.datamigration.sss.model.JpaSssFloatingRatesSource;
import scrips.datamigration.sss.model.JpaSssFloatingRatesTemp;
import scrips.datamigration.sss.repository.SssFloatingRatesDAO;
import scrips.datamigration.sss.repository.SssFloatingRatesSourceDAO;
import scrips.datamigration.sss.repository.SssFloatingRatesTempDAO;

@Service

public class SssFloatingRateService {
	private final Logger logger = LogManager.getLogger(SssFloatingRateService.class);

	@Autowired
	SssFloatingRatesDAO floatingratesDAO;
	
	@Autowired
	private Environment env;

	@Autowired
	SssFloatingRatesTempDAO sssFloatingRatesTempDAO;

	@Autowired
	SssFloatingRatesDAO sssFloatingRatesDAO;

	@Autowired
	SssFloatingRatesSourceDAO sssFloatingRatesSourceDAO;

	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	private UserData userData;

	public String migrateSssFloatingRates(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) {
		List<JpaSssFloatingRatesSource> sssFloatingRateSourceList = createAndSaveSssFloatingRateSourceData(fileRecords,
				draftDBDetails);
		List<JpaSssFloatingRatesTemp> sssFloatingRateTempList = convertFloatingRateSourceToFloatingRateTemp(
				sssFloatingRateSourceList);
		boolean noErrors =saveFloatingRateLiveTableData(sssFloatingRateTempList);
		return (sssFloatingRateSourceList.size()==sssFloatingRateTempList.size() && noErrors)? "noErrors" : "hasErrors";
	}

	private boolean saveFloatingRateLiveTableData(List<JpaSssFloatingRatesTemp> sssFloatingRateTempList) {
		boolean noErrors = true;
		for (JpaSssFloatingRatesTemp sssFloatingRateTemp : sssFloatingRateTempList) {
			if (sssFloatingRateTemp.getRemarks() == null || sssFloatingRateTemp.getRemarks().isEmpty()
					|| sssFloatingRateTemp.getRemarks().isBlank()) {
				try {
					JpaSssFloatingRates sssFloatingRate = converttoJpaSssFloatingRate(sssFloatingRateTemp);
					String remarks = duplicateKey(sssFloatingRate);

					remarks = remarks.concat(validationJpaSssFloatingRateLiveData(sssFloatingRate));
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						sssFloatingRateTemp.setRemarks(String.join(", ", remarks));
						sssFloatingRatesTempDAO.save(sssFloatingRateTemp);
					} else
						sssFloatingRatesDAO.save(sssFloatingRate);
				} catch (Exception e) {
					sssFloatingRateTemp.setRemarks("error while saving sss floating rate live table data");
					e.printStackTrace();
					sssFloatingRatesTempDAO.save(sssFloatingRateTemp);
					noErrors= false;

				}
			}
		}
		return noErrors;
	}

	private String validationJpaSssFloatingRateLiveData(JpaSssFloatingRates sssFloatingRate) {
		StringBuffer remarks = new StringBuffer("");

		remarks.append(regexValidation.regexValidator(sssFloatingRate.getReferenceRate(), "alphabetswithspace",
				"Reference Rate"));
		remarks.append(
				regexValidation.regexValidator(sssFloatingRate.getReferenceRate(), "length20", "Reference Rate"));
		remarks.append(regexValidation.regexValidator(String.valueOf(sssFloatingRate.getPublicationDate()), "numeric",
				"Publication Date"));
		remarks.append(regexValidation.regexValidator(String.valueOf(sssFloatingRate.getValueDate()), "numeric",
				"Value Date"));
		if (sssFloatingRate.getModifiedBy() != null && !sssFloatingRate.getModifiedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssFloatingRate.getModifiedBy(), "length36", "Modified By"));
		}
		if (sssFloatingRate.getApprovedBy() != null && !sssFloatingRate.getApprovedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssFloatingRate.getApprovedBy(), "length36", "Approved By"));
		}

		return remarks.toString().trim();
	}

	private String duplicateKey(JpaSssFloatingRates sssFloatingRate) {
		JpaSssFloatingRates duplicateKey = sssFloatingRatesDAO
				.findByReferenceRateAndValueDate(sssFloatingRate.getReferenceRate(), sssFloatingRate.getValueDate());
		String remarks = "";
		if (duplicateKey != null) {
			remarks = remarks.concat(",Duplicate ReferenceRate,Duplicate ValueDate");
		}
		return remarks;
	}

	private JpaSssFloatingRates converttoJpaSssFloatingRate(JpaSssFloatingRatesTemp sssFloatingRateTemp) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaSssFloatingRates jpaSssFloatingRate = mapper.map(sssFloatingRateTemp, JpaSssFloatingRates.class);
		return jpaSssFloatingRate;
	}

	private List<JpaSssFloatingRatesSource> createAndSaveSssFloatingRateSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaSssFloatingRatesSource> sssFloatingRateSourceList = new ArrayList<JpaSssFloatingRatesSource>();

		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter=CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 3, draftDBDetails.size(),delimiter);
				if (error != null) {
					JpaSssFloatingRatesSource sssFloatingRateSource = new JpaSssFloatingRatesSource();
					sssFloatingRateSource.setRemarks(error);
					sssFloatingRatesSourceDAO.save(sssFloatingRateSource);
					continue;
				}

				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line,delimiter);

				JpaSssFloatingRatesSource sssFloatingRateSource = JpaSssFloatingRatesSource.builder().id(id)
						.referenceRate(dbRecordsMap.get("reference_rate"))
						.publicationDate(dbRecordsMap.get("publication_date")).valueDate(dbRecordsMap.get("value_date"))
						.rate(dbRecordsMap.get("rate")).action(null).status(null).modifiedBy(null).modifiedDate(null)
						.approvedBy(null).approvedDate(null).createdDate(null).effectiveDate(null).approvalRemark(null)
						.workflowStatusId(null).build();
				String remarks = validationJpaSssFloatingRateSource(sssFloatingRateSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				sssFloatingRateSource.setRemarks(remarks);
				sssFloatingRateSourceList.add(sssFloatingRateSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return sssFloatingRateSourceList;
	}

	private String validationJpaSssFloatingRateSource(JpaSssFloatingRatesSource sssFloatingRateSource) {
		StringBuffer remarks = new StringBuffer("");

		remarks.append(regexValidation.regexValidator(sssFloatingRateSource.getReferenceRate(), "alphabetswithspace",
				"Reference Rate"));
		remarks.append(
				regexValidation.regexValidator(sssFloatingRateSource.getReferenceRate(), "length20", "Reference Rate"));
		remarks.append(regexValidation.regexValidator(sssFloatingRateSource.getPublicationDate(), "numeric",
				"Publication Date"));
		remarks.append(regexValidation.regexValidator(sssFloatingRateSource.getValueDate(), "numeric", "Value Date"));

		if (sssFloatingRateSource.getRate() != null && !sssFloatingRateSource.getRate().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssFloatingRateSource.getRate(), "decimal", "Rate"));
			remarks.append(regexValidation.regexValidator(sssFloatingRateSource.getRate(), "length13", "Rate"));
		}
		return remarks.toString().trim();
	}

	private List<JpaSssFloatingRatesTemp> convertFloatingRateSourceToFloatingRateTemp(
			List<JpaSssFloatingRatesSource> sssFloatingRateSourceList) {
		List<JpaSssFloatingRatesTemp> sssFloatingRateTempList = new ArrayList<JpaSssFloatingRatesTemp>();
		String id = userData.getSystemUserId();
		for (JpaSssFloatingRatesSource sourceRec : sssFloatingRateSourceList) {
			sssFloatingRatesSourceDAO.save(sourceRec);
			try {
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {

					Date date = new Date();
					JpaSssFloatingRatesTemp jpaSssFloatingRateTemp = JpaSssFloatingRatesTemp.builder()
							.id(sourceRec.getId()).referenceRate(sourceRec.getReferenceRate())
							.publicationDate(Integer.parseInt(sourceRec.getPublicationDate()))
							.valueDate(Integer.parseInt(sourceRec.getValueDate()))
							.rate(!CommonUtils.isNullOrEmpty(sourceRec.getRate()) ? null
									: Double.parseDouble(sourceRec.getRate()))
							.action(" ").status("ACTIVE").modifiedBy(id).modifiedDate(date).approvedBy(id)
							.approvedDate(date).createdDate(date).effectiveDate(date).approvalRemark("Data Migration")
							.workflowStatusId(null).build();

					sssFloatingRatesTempDAO.save(jpaSssFloatingRateTemp);
					sssFloatingRateTempList.add(jpaSssFloatingRateTemp);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving sss floating rate temp table data");
				sssFloatingRatesSourceDAO.save(sourceRec);
			}
		}
		return sssFloatingRateTempList;
	}
}