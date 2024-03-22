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
import scrips.datamigration.jpa.cbm.JpaCbmDepositRate;
import scrips.datamigration.jpa.cbm.JpaCbmDepositRateDAO;
import scrips.datamigration.jpa.cbm.JpaCbmDepositRateTemp;
import scrips.datamigration.jpa.cbm.JpaCbmDepositRateTempDAO;
import scrips.datamigration.jpa.fileupload.JpaFileUploadDetails;
import scrips.datamigration.jpa.fileupload.JpaFileUploadHeader;

@Service
@Slf4j
public class CbmDepositRateService {

	private final Logger logger = LogManager.getLogger(CbmDepositRateService.class);
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
	JpaCbmDepositRateDAO jpaCbmDepositRateDAO;

	@Autowired
	JpaCbmDepositRateTempDAO jpaCbmDepositRateTempDAO;

	public String migrateCbmDepositRates(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) throws NumberFormatException, ParseException, DatabaseException {
		fileConvertService.createAndSaveCbmDepositRateSourceData(fileRecords, draftDBDetails);
		boolean status=true;
		List<JpaCbmDepositRateTemp> cbmDepositRateTemp=null;
		List<JpaCbmDepositRate> cbmDepositRate=null;
		
		try {
			cbmDepositRateTemp = fileConvertService.convertToJpaCbmDepositRateList();
			cbmDepositRate = converttoJpaCbmDepositRate(cbmDepositRateTemp);
		}catch (Exception e) {
			status=false;
			e.printStackTrace();
		}
//		List<JpaFileUploadExecution> fileExecList = new ArrayList<JpaFileUploadExecution>();
//		List<JpaFileUploadError> fileErrList = new ArrayList<JpaFileUploadError>();


		if(cbmDepositRateTemp!=null && cbmDepositRate!=null && !cbmDepositRate.isEmpty()) {
			for(JpaCbmDepositRate cbmDeposit : cbmDepositRate ) {
//			cbmDepositRateTemp.stream().forEach(cbmDepositRatesTemp -> {
				try {
					String remarks = validationService.validationJpaCbmDepositRate(cbmDeposit);
					if (remarks.isEmpty()) {
						Optional<JpaCbmDepositRateTemp> tempObj = cbmDepositRateTemp.stream()
								.filter(x -> x.getId().equals(cbmDeposit.getId()))
								.findAny();
						JpaCbmDepositRateTemp temp = tempObj.get();
						boolean isLiveDataHasError = false;
						jpaCbmDepositRateTempDAO.save(temp);
						try {
							jpaCbmDepositRateDAO.save(cbmDeposit);
						} catch (Exception e) {
							isLiveDataHasError = true;
							logger.error("error while saving deposit rate live table data {}", e.getMessage());
							e.printStackTrace();
						}
						if (isLiveDataHasError)
						{
							status=false;
							temp.setRemarks("Error while saving deposit rate live table data");
							jpaCbmDepositRateTempDAO.save(temp);
						}
							
//						JpaFileUploadExecution fileUplodExec = fileuploadExecService.createFileUploadExecution(
//								fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//								UUID.randomUUID().toString(), "Migratted Sucessfully");
//						fileExecList.add(fileUplodExec);
					} else {
						Optional<JpaCbmDepositRateTemp> tempObj = cbmDepositRateTemp.stream()
								.filter(x -> x.getId().equals(cbmDeposit.getId()))
								.findFirst();
						if (tempObj != null&& tempObj.get()!=null) {
						remarks = remarks.substring(1);
						JpaCbmDepositRateTemp temp = tempObj.get();
						temp.setRemarks(remarks);
						jpaCbmDepositRateTempDAO.save(temp);
						}

//						JpaFileUploadError errObj = fileErrorService.createFileUploadError(
//								fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//								UUID.randomUUID().toString(), 101);
//						fileErrList.add(errObj);
					}
				} catch (FailedValidationException Fve) {
//					JpaFileUploadError errObj = fileErrorService.createFileUploadError(fileHeaderObj.getFileUploadId(),
//							fileHeaderObj.getFileUploadCode(), UUID.randomUUID().toString(), 102);
//					fileErrList.add(errObj);
					log.info("{} - {}", Fve.getMessage());
				} catch (Exception e) {
					log.error("Error {}", e.getMessage());
					status=false;
				}
			} 
		}
		
		return status?"noErrors":"hasErrors";
//		return fileErrList.size() > 0 ? "Partially Migrated" : "Migrated Sucessfully";
	}


	public List<JpaCbmDepositRate> converttoJpaCbmDepositRate(List<JpaCbmDepositRateTemp> cbmDepositRateTemp) {
		List<JpaCbmDepositRate> list = new ArrayList<>();
		for (JpaCbmDepositRateTemp jpaCbmDepositRatesTemp : cbmDepositRateTemp) {
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			JpaCbmDepositRate jpaCbmDepositRate = mapper.map(jpaCbmDepositRatesTemp, JpaCbmDepositRate.class);
			list.add(jpaCbmDepositRate);
		}
		return list;
	}
}
