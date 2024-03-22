package scrips.datamigration.business;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import scrips.datamigration.exception.DatabaseException;
import scrips.datamigration.exception.FailedValidationException;
import scrips.datamigration.jpa.cbm.JpaCbmSoraRate;
import scrips.datamigration.jpa.cbm.JpaCbmSoraRateDAO;
import scrips.datamigration.jpa.cbm.JpaCbmSoraRateTemp;
import scrips.datamigration.jpa.cbm.JpaCbmSoraRateTempDAO;
import scrips.datamigration.jpa.fileupload.JpaFileUploadDetails;
import scrips.datamigration.jpa.fileupload.JpaFileUploadHeader;

@Service
@Slf4j
public class CbmSoraRateService {
	private final Logger logger = LogManager.getLogger(CbmSoraRateService.class);

	@Autowired
	ReadFileAndConvertService fileConvertService;

	@Autowired
	ValidationService validationService;

	@Autowired
	FileUploadExecutionService fileuploadExecService;

	@Autowired
	FileUploadErrorService fileErrorService;

	@Autowired
	FileUploadService fileService;

	@Autowired
	JpaCbmSoraRateDAO jpaCbmFloatingRateDAO;
	@Autowired
	JpaCbmSoraRateTempDAO jpaCbmFloatingRateTempDAO;

	public String migrateCbmFloatingRates(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) throws NumberFormatException, ParseException, DatabaseException {
		fileConvertService.createAndSaveCbmSoraRateSourceData(fileRecords, draftDBDetails);

		boolean status = true;
		List<JpaCbmSoraRateTemp> cbmFloatingRateTemp = null;
		List<JpaCbmSoraRate> cbmFloatingRate = null;

		try {
			cbmFloatingRateTemp = fileConvertService.convertToCbmSoraRateList();
			cbmFloatingRate = converttoJpaCbmSoraRate(cbmFloatingRateTemp);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
//		List<JpaFileUploadExecution> fileExecList = new ArrayList<JpaFileUploadExecution>();
//		List<JpaFileUploadError> fileErrList = new ArrayList<JpaFileUploadError>();
		if (cbmFloatingRateTemp != null && cbmFloatingRate != null && !cbmFloatingRate.isEmpty()) {
			for (JpaCbmSoraRate cbmFloating : cbmFloatingRate) {
//			cbmFloatingRateTemp.stream().forEach(cbmFloatingRatesTemp -> {
				try {
					JpaCbmSoraRate duplicateKey = jpaCbmFloatingRateDAO.findByReferenceRateAndValueDate(
							cbmFloating.getReferenceRate(), cbmFloating.getValueDate());
					String remarks = validationService.validationJpaCbmFloatingRate(cbmFloating);

					if (duplicateKey != null) {
						remarks = remarks.concat(",Duplicate ReferenceRate,Duplicate ValueDate");
					}

					if (remarks.isEmpty() && duplicateKey == null) {
						Optional<JpaCbmSoraRateTemp> tempObj = cbmFloatingRateTemp.stream()
								.filter(x -> x.getValueDate().equals(cbmFloating.getValueDate())).findAny();
						JpaCbmSoraRateTemp temp = tempObj.get();
						boolean isLiveDataHasError = false;
						jpaCbmFloatingRateTempDAO.save(temp);
						try {
							jpaCbmFloatingRateDAO.save(cbmFloating);
						} catch (Exception e) {
							isLiveDataHasError = true;
							logger.error("error while saving floating rate live table data {}", e.getMessage());
							e.printStackTrace();
						}
						if (isLiveDataHasError) {
							status = false;
							temp.setRemarks("Error while saving floating rate live table data");
							jpaCbmFloatingRateTempDAO.save(temp);
						}
						
//						JpaFileUploadExecution fileUplodExec = fileuploadExecService.createFileUploadExecution(
//								fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//								UUID.randomUUID().toString(), "Migratted Sucessfully");
//						fileExecList.add(fileUplodExec);
					} else {
						Optional<JpaCbmSoraRateTemp> tempObj = cbmFloatingRateTemp.stream()
								.filter(x -> x.getValueDate().equals(cbmFloating.getValueDate())).findFirst();
						if (duplicateKey != null) {
							List<JpaCbmSoraRateTemp> tempList = new ArrayList<JpaCbmSoraRateTemp>();
							tempList.add(tempObj.get());
							tempObj = cbmFloatingRateTemp.stream()
									.filter(x -> x.getValueDate().equals(cbmFloating.getValueDate())
											&& !x.getId().equals(tempList.get(0).getId()))
									.findFirst();
						}
						if (tempObj != null && tempObj.get() != null) {

							remarks = remarks.substring(1);
							JpaCbmSoraRateTemp temp = tempObj.get();
							temp.setRemarks(remarks);
							jpaCbmFloatingRateTempDAO.save(temp);
						}
//						JpaFileUploadError errObj = fileErrorService.createFileUploadError(
//								fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//								UUID.randomUUID().toString(), 101);
//						fileErrList.add(errObj);
					}
				} catch (FailedValidationException Fve) {
//				JpaFileUploadError errObj = fileErrorService.createFileUploadError(fileHeaderObj.getFileUploadId(),
//						fileHeaderObj.getFileUploadCode(), UUID.randomUUID().toString(), 102);
//				fileErrList.add(errObj);
					log.info("{} - {}", Fve.getMessage());
				} catch (Exception e) {
					log.error("Error {}", e.getMessage());
					status = false;
				}
			}
		}

		return status ? "noErrors" : "hasErrors";
//	return fileErrList.size() > 0 ? "Partially Migrated" : "Migrated Sucessfully";
	}

	public List<JpaCbmSoraRate> converttoJpaCbmSoraRate(List<JpaCbmSoraRateTemp> cbmFloatingRateTemp) {
		List<JpaCbmSoraRate> list = new ArrayList<>();
		for (JpaCbmSoraRateTemp jpaCbmFloatingRatesTemp : cbmFloatingRateTemp) {
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			JpaCbmSoraRate jpaCbmFloatingRate = mapper.map(jpaCbmFloatingRatesTemp, JpaCbmSoraRate.class);
			list.add(jpaCbmFloatingRate);
		}
		return list;
	}

}
