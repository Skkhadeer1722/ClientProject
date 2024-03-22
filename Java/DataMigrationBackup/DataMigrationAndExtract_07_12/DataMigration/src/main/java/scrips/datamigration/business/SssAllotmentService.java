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
import scrips.datamigration.jpa.fileupload.JpaFileUploadDetails;
import scrips.datamigration.jpa.fileupload.JpaFileUploadHeader;
import scrips.datamigration.jpa.sss.securities.JpaSssAllotment;
import scrips.datamigration.jpa.sss.securities.JpaSssAllotmentTemp;
import scrips.datamigration.jpa.sss.securities.SssAllotmentDAO;
import scrips.datamigration.jpa.sss.securities.SssAllotmentTempDAO;
import scrips.datamigration.jpa.sss.securities.SssSecuritiesCodeDAO;
@Service
@Slf4j
public class SssAllotmentService {
	private final Logger logger = LogManager.getLogger(SssAllotmentService.class);
	@Autowired
	ReadFileAndConvertService fileConvertService;
	@Autowired
	ValidationService validationService;

	@Autowired
	FileUploadExecutionService fileuploadExecService;

	@Autowired
	FileUploadErrorService fileErrorService;

	@Autowired
	SssAllotmentDAO sssAllotmentDAO;

	@Autowired
	SssAllotmentTempDAO sssAllotmentTempDAO;

	@Autowired
	FileUploadService fileService;
	
	@Autowired
	SssSecuritiesCodeDAO securitiesCodeDAO;

	public String migrateSssAllotment(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) throws NumberFormatException, ParseException, DatabaseException {
//		List<JpaSssAllotmentTemp> tempSssAllotmentList = fileConvertService.convertToSssAllotmentList(fileRecords, draftDBDetails);
		fileConvertService.createAndSaveAllotmentSourceData(fileRecords, draftDBDetails);

		boolean status=true;
		List<JpaSssAllotmentTemp> tempSssAllotmentList=null;
		List<JpaSssAllotment> SssAllotmentList=null;
		
		try {
			tempSssAllotmentList = fileConvertService.convertToSssAllotmentList();
			SssAllotmentList = convertToJpaSssAllotment(tempSssAllotmentList);
		}catch (Exception e) {
			status=false;
			e.printStackTrace();
		}	
		
//		List<JpaFileUploadExecution> fileExecList = new ArrayList<JpaFileUploadExecution>();
//		List<JpaFileUploadError> fileErrList = new ArrayList<JpaFileUploadError>();
	
		if(tempSssAllotmentList!=null && SssAllotmentList!=null && !SssAllotmentList.isEmpty()) {
			for(JpaSssAllotment sssAllotment:SssAllotmentList) {
				try {
					JpaSssAllotment duplicateSecurityCode = sssAllotmentDAO.findBySecuritiesCode(sssAllotment.getSecuritiesCode());
					String remarks = validationService.validationJpaSssAllotment(sssAllotment);
					if (duplicateSecurityCode != null) {
						remarks = remarks.concat(",Duplicate Securities Code");
					}	
					if (remarks.isEmpty()  && duplicateSecurityCode == null) {
						Optional<JpaSssAllotmentTemp> tempObj = tempSssAllotmentList.stream()
								.filter(x -> x.getSecuritiesCode().equals(sssAllotment.getSecuritiesCode()))
								.findAny();
						
							JpaSssAllotmentTemp temp = tempObj.get();
							log.info("validated");
							boolean isLiveDataHasError = false;
							sssAllotmentTempDAO.save(temp);
							try {
								sssAllotmentDAO.save(sssAllotment);
							} catch (Exception e) {
								isLiveDataHasError = true;
								logger.error("error while saving sss allotment live table data {}", e.getMessage());
								e.printStackTrace();
							}
							if (isLiveDataHasError)
							{
								status=false;
								temp.setRemarks("Error while saving sss allotment live table data");
								sssAllotmentTempDAO.save(temp);
							}
							
//							JpaFileUploadExecution fileUplodExec = fileuploadExecService.createFileUploadExecution(
//									fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//									UUID.randomUUID().toString(), "Migratted Sucessfully");
//							fileExecList.add(fileUplodExec);
						} else {
							
							Optional<JpaSssAllotmentTemp> tempObj = tempSssAllotmentList.stream()
									.filter(x -> x.getSecuritiesCode().equals(sssAllotment.getSecuritiesCode()))
									.findAny();
							
							if (duplicateSecurityCode != null ) {
								List<JpaSssAllotmentTemp> tempList = new ArrayList<JpaSssAllotmentTemp>();
								tempList.add(tempObj.get());
								tempObj = tempSssAllotmentList.stream()
										.filter(x -> x.getSecuritiesCode().equals(sssAllotment.getSecuritiesCode())
												&& !x.getId().equals(tempList.get(0).getId()))
										.findFirst();
							}
							
							if (tempObj != null && tempObj.get()!=null) 
							{
								remarks = remarks.substring(1);
								JpaSssAllotmentTemp temp = tempObj.get();
								temp.setRemarks(remarks);
								sssAllotmentTempDAO.save(temp);
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

	public List<JpaSssAllotment> convertToJpaSssAllotment(List<JpaSssAllotmentTemp> sssAllotmentListTemp) {
		List<JpaSssAllotment> list = new ArrayList<>();
		for (JpaSssAllotmentTemp sssAllotmentTemp : sssAllotmentListTemp) {
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			JpaSssAllotment sssAllotment= mapper.map(sssAllotmentTemp, JpaSssAllotment.class);
			list.add(sssAllotment);
			//log.info(sssAllotmentTemp.getAccountId());
		}
		return list;
	}


}