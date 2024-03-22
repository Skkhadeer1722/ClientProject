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
import scrips.datamigration.rtgs.model.JpaCbmCostCentre;
import scrips.datamigration.rtgs.model.JpaCbmCostCentreSource;
import scrips.datamigration.rtgs.model.JpaCbmCostCentreTemp;
import scrips.datamigration.rtgs.repository.JpaCbmCostCentreDAO;
import scrips.datamigration.rtgs.repository.JpaCbmCostCentreSourceDAO;
import scrips.datamigration.rtgs.repository.JpaCbmCostCentreTempDAO;

@Service
public class CbmCostCentreService {
	private final Logger logger = LogManager.getLogger(CbmCostCentreService.class);

	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	JpaCbmCostCentreDAO costCentreDAO;

	@Autowired
	JpaCbmCostCentreTempDAO costCentreTempDAO;

	@Autowired
	JpaCbmCostCentreSourceDAO cbmCostCentreSourceDAO;

	@Autowired
	private Environment env;

	public String migrateCbmCostCentre(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) {
		List<JpaCbmCostCentreSource> cbmCostCentreSourceObjList = createAndSaveCbmCostCentreSourceData(fileRecords,
				draftDBDetails);
		List<JpaCbmCostCentreTemp> cbmCostCentreTempList = convertCbmCentreSourceToCbmCostCentreTemp(
				cbmCostCentreSourceObjList);
		boolean noErrors = saveCbmCostCentreLiveTableData(cbmCostCentreTempList);
		return (cbmCostCentreSourceObjList.size() == cbmCostCentreTempList.size() && noErrors) ? "noErrors"
				: "hasErrors";
	}

	private List<JpaCbmCostCentreSource> createAndSaveCbmCostCentreSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaCbmCostCentreSource> cbmCentreSourceList = new ArrayList<JpaCbmCostCentreSource>();
		try {

			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 0, draftDBDetails.size(), delimiter);
				if (error != null) {
					JpaCbmCostCentreSource cbmCostCentreSource = new JpaCbmCostCentreSource();
					cbmCostCentreSource.setRemarks(error);
					cbmCostCentreSourceDAO.save(cbmCostCentreSource);
					continue;
				}
				Date date = new Date();
				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line, delimiter);
				JpaCbmCostCentreSource costCentreSource = JpaCbmCostCentreSource.builder().id(id)
						.costCentre(dbRecordsMap.get("cost_centre")).description(null).createdDate(null)
						.modifiedDate(null)
						.migratedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date))
						.build();

				String remarks = validationCbmCostCentreSource(costCentreSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				costCentreSource.setRemarks(remarks);
				cbmCentreSourceList.add(costCentreSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return cbmCentreSourceList;

	}

	private String validationCbmCostCentreSource(JpaCbmCostCentreSource cbmCostCentreSource) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(cbmCostCentreSource.getCostCentre(), "special", "Cost Centre"));
		remarks.append(regexValidation.regexValidator(cbmCostCentreSource.getCostCentre(), "length6", "Cost Centre"));
		return remarks.toString().trim();
	}

	private List<JpaCbmCostCentreTemp> convertCbmCentreSourceToCbmCostCentreTemp(
			List<JpaCbmCostCentreSource> costCentreSourceList) {
		List<JpaCbmCostCentreTemp> jpaCbmCostCentreTempList = new ArrayList<JpaCbmCostCentreTemp>();
		for (JpaCbmCostCentreSource sourceRec : costCentreSourceList) {
			cbmCostCentreSourceDAO.save(sourceRec);
			try {
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {

					Date date = new Date();
					JpaCbmCostCentreTemp jpaCbmCostCentreTemp = JpaCbmCostCentreTemp.builder().id(sourceRec.getId())
							.costCentre(sourceRec.getCostCentre()).description(" ").createdDate(date).modifiedDate(date)
							.build();

					costCentreTempDAO.save(jpaCbmCostCentreTemp);
					jpaCbmCostCentreTempList.add(jpaCbmCostCentreTemp);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving cost_centre_temp table data");
				cbmCostCentreSourceDAO.save(sourceRec);
			}
		}
		return jpaCbmCostCentreTempList;
	}

	private boolean saveCbmCostCentreLiveTableData(List<JpaCbmCostCentreTemp> cbmCostCentreTempList) {
		boolean noErrors = true;
		for (JpaCbmCostCentreTemp cbmCostCentreTemp : cbmCostCentreTempList) {
			if (cbmCostCentreTemp.getRemarks() == null || cbmCostCentreTemp.getRemarks().isEmpty()
					|| cbmCostCentreTemp.getRemarks().isBlank()) {
				try {
					JpaCbmCostCentre costCentreObj = converttoJpaCbmCostCentre(cbmCostCentreTemp);
					String remarks = duplicateCostCentre(costCentreObj);
					remarks = remarks.concat(validationCbmCostCentreLiveData(costCentreObj));
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						cbmCostCentreTemp.setRemarks(String.join(", ", remarks));
						costCentreTempDAO.save(cbmCostCentreTemp);
					} else
						costCentreDAO.save(costCentreObj);
				} catch (Exception e) {
					cbmCostCentreTemp.setRemarks("error while saving cost_centre live table data");
					e.printStackTrace();
					costCentreTempDAO.save(cbmCostCentreTemp);
					noErrors = false;
				}
			}
		}
		return noErrors;
	}

	private JpaCbmCostCentre converttoJpaCbmCostCentre(JpaCbmCostCentreTemp costCentreTempObj) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaCbmCostCentre jpaCbmCostCentre = mapper.map(costCentreTempObj, JpaCbmCostCentre.class);
		return jpaCbmCostCentre;
	}

	private String duplicateCostCentre(JpaCbmCostCentre costCentreObj) {
		JpaCbmCostCentre duplicateCostCentreId = costCentreDAO.findByCostCentre(costCentreObj.getCostCentre());
		String remarks = "";
		if (duplicateCostCentreId != null)
			remarks = remarks.concat(",Duplicate cost_centre");
		return remarks;
	}

	private String validationCbmCostCentreLiveData(JpaCbmCostCentre cbmCostCentre) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(cbmCostCentre.getCostCentre(), "special", "Cost Centre"));
		remarks.append(regexValidation.regexValidator(cbmCostCentre.getCostCentre(), "length6", "Cost Centre"));
		return remarks.toString().trim();
	}

}
