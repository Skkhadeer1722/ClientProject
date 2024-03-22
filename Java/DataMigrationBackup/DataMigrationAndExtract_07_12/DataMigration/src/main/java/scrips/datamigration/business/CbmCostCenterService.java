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
import scrips.datamigration.jpa.cbm.CbmCostCenterDAO;
import scrips.datamigration.jpa.cbm.CbmCostCenterTempDAO;
import scrips.datamigration.jpa.cbm.JpaCbmCostCentre;
import scrips.datamigration.jpa.cbm.JpaCbmCostCentreTemp;
import scrips.datamigration.jpa.fileupload.JpaFileUploadDetails;
import scrips.datamigration.jpa.fileupload.JpaFileUploadHeader;

@Service
@Slf4j
public class CbmCostCenterService {
	private final Logger logger = LogManager.getLogger(CbmCostCenterService.class);
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
	CbmCostCenterDAO costCentreDAO;

	@Autowired
	CbmCostCenterTempDAO costCenterTempDAO;

	public String migrateCbmCostCentre(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) throws NumberFormatException, ParseException, DatabaseException {
	fileConvertService.createAndSaveCbmCostCentreSourceData(fileRecords, draftDBDetails);
	boolean status=true;
	List<JpaCbmCostCentreTemp> cbmCostCentreTempList=null;
	List<JpaCbmCostCentre> CbmCostCentreList=null;
	
	try {
		cbmCostCentreTempList = fileConvertService.convertToCbmCostCenterList();
		CbmCostCentreList = converttoJpaCbmCostCentre(cbmCostCentreTempList);
	}catch (Exception e) {
		status=false;
		e.printStackTrace();
	}	
//	List<JpaFileUploadExecution> fileExecList = new ArrayList<JpaFileUploadExecution>();
//	List<JpaFileUploadError> fileErrList = new ArrayList<JpaFileUploadError>();
	

	if(cbmCostCentreTempList!=null && CbmCostCentreList!=null && !CbmCostCentreList.isEmpty()) {
		for(JpaCbmCostCentre cbmCostCentre : CbmCostCentreList ) {
//			cbmCostCentreTempList.stream().forEach(cbmCostCentreTemp -> {
				try {
					JpaCbmCostCentre duplicateAccount = costCentreDAO.findByCostCentre(cbmCostCentre.getCostCentre());
						String remarks = validationService.validationCbmCostCenterTemp(cbmCostCentre);
						if (duplicateAccount != null) {
							remarks = remarks.concat(",Duplicate Cost Centre");
						}
						if (remarks.isEmpty() && duplicateAccount == null) {
							Optional<JpaCbmCostCentreTemp> tempObj = cbmCostCentreTempList.stream()
									.filter(x -> x.getCostCentre().equals(cbmCostCentre.getCostCentre()))
									.findAny();
							JpaCbmCostCentreTemp temp = tempObj.get();
							boolean isLiveDataHasError = false;
							costCenterTempDAO.save(temp);
							try {
								costCentreDAO.save(cbmCostCentre);
							} catch (Exception e) {
								isLiveDataHasError = true;
								logger.error("error while saving cbm cost centre live table data {}", e.getMessage());
								e.printStackTrace();
							}
							if (isLiveDataHasError){
								status=false;
								temp.setRemarks(", Error while saving cbm cost centre live table data");
								costCenterTempDAO.save(temp);
							}
							
//							JpaFileUploadExecution fileUplodExec = fileuploadExecService.createFileUploadExecution(
//									fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//									UUID.randomUUID().toString(), "Migratted Sucessfully");
//							fileExecList.add(fileUplodExec);
						} else {
							Optional<JpaCbmCostCentreTemp> tempObj = cbmCostCentreTempList.stream()
									.filter(x -> x.getCostCentre().equals(cbmCostCentre.getCostCentre()))
									.findFirst();
							if (duplicateAccount != null ) {
								List<JpaCbmCostCentreTemp> tempList = new ArrayList<JpaCbmCostCentreTemp>();
								tempList.add(tempObj.get());
								tempObj = cbmCostCentreTempList.stream()
										.filter(x -> x.getCostCentre().equals(cbmCostCentre.getCostCentre())
												&& !x.getId().equals(tempList.get(0).getId()))
										.findFirst();
							}
							if (tempObj != null&& tempObj.get()!=null) {
								remarks = remarks.substring(1);
								JpaCbmCostCentreTemp temp = tempObj.get();
								temp.setRemarks(remarks);
								costCenterTempDAO.save(temp);
							}
//							JpaFileUploadError errObj = fileErrorService.createFileUploadError(
//									fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//									UUID.randomUUID().toString(), 101);
//							fileErrList.add(errObj);
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

	private List<JpaCbmCostCentre> converttoJpaCbmCostCentre(List<JpaCbmCostCentreTemp> cbmCostCentreTemp) {
		List<JpaCbmCostCentre> list = new ArrayList<>();
		for (JpaCbmCostCentreTemp jpaCbmCostCentreTemp : cbmCostCentreTemp) {
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			JpaCbmCostCentre jpaCbmCostCentre = mapper.map(jpaCbmCostCentreTemp, JpaCbmCostCentre.class);
			list.add(jpaCbmCostCentre);
		}
		return list;

	}
}
