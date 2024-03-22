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
import scrips.datamigration.jpa.cbm.CbmGlAccountDAO;
import scrips.datamigration.jpa.cbm.CbmGlAccountSourceDAO;
import scrips.datamigration.jpa.cbm.CbmGlAccountTempDAO;
import scrips.datamigration.jpa.cbm.JpaCbmGlAccount;
import scrips.datamigration.jpa.cbm.JpaCbmGlAccountTemp;
import scrips.datamigration.jpa.fileupload.JpaFileUploadDetails;
import scrips.datamigration.jpa.fileupload.JpaFileUploadHeader;

@Service
@Slf4j
public class CbmGlAccountService {
	private final Logger logger = LogManager.getLogger(CbmGlAccountService.class);
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
	CbmGlAccountDAO glAccountDAO;

	@Autowired
	CbmGlAccountTempDAO glAccountTempDAO;
	@Autowired
	CbmGlAccountSourceDAO glAccountSourceDAO;

	public String migrateCbmGlAccount(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) throws NumberFormatException, ParseException, DatabaseException {
		fileConvertService.createAndSaveCbmGlAccountSourceData(fileRecords, draftDBDetails);
		boolean status = true;
		List<JpaCbmGlAccountTemp> tempCbmGlAccountList = null;
		List<JpaCbmGlAccount> cbmGlAccount = null;

		try {
			tempCbmGlAccountList = fileConvertService.convertToCbmGlAccountList();
			cbmGlAccount = converttoJpaCbmGlAccount(tempCbmGlAccountList);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
//		List<JpaFileUploadExecution> fileExecList = new ArrayList<JpaFileUploadExecution>();
//		List<JpaFileUploadError> fileErrList = new ArrayList<JpaFileUploadError>();

		if (tempCbmGlAccountList != null && cbmGlAccount != null && !cbmGlAccount.isEmpty()) {
//			cbmGlAccountList.stream().forEach(cbmGlAccountTemp -> {
			for (JpaCbmGlAccount glAccount : cbmGlAccount) {
				try {
					JpaCbmGlAccount duplicateGlAccount = glAccountDAO.findByGlAccount(glAccount.getGlAccount());

					String remarks = validationService.validationCbmGlAccount(glAccount);
					if (duplicateGlAccount != null) {
						remarks = remarks.concat(",Duplicate Gl Account");
					}
					if (remarks.isEmpty() && duplicateGlAccount == null) {
						Optional<JpaCbmGlAccountTemp> tempObj = tempCbmGlAccountList.stream()
								.filter(x -> x.getGlAccount().equals(glAccount.getGlAccount())).findAny();
						JpaCbmGlAccountTemp temp = tempObj.get();
						log.info("validated");
						boolean isLiveDataHasError = false;
						glAccountTempDAO.save(temp);
						try {
							glAccountDAO.save(glAccount);
						} catch (Exception e) {
							isLiveDataHasError = true;
							logger.error("error while saving cbm gl account live table data {}", e.getMessage());
							e.printStackTrace();
						}
						if (isLiveDataHasError) {
							status = false;
							temp.setRemarks("Error while saving cbm gl account live table data");
							glAccountTempDAO.save(temp);
						}
//							JpaFileUploadExecution fileUplodExec = fileuploadExecService.createFileUploadExecution(
//									fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//									UUID.randomUUID().toString(), "Migratted Sucessfully");
//							fileExecList.add(fileUplodExec);

					} else {
						Optional<JpaCbmGlAccountTemp> tempObj = tempCbmGlAccountList.stream()
								.filter(x -> x.getGlAccount().equals(glAccount.getGlAccount())).findAny();
						if (duplicateGlAccount != null) {
							List<JpaCbmGlAccountTemp> tempList = new ArrayList<JpaCbmGlAccountTemp>();
							tempList.add(tempObj.get());
							tempObj = tempCbmGlAccountList.stream()
									.filter(x -> x.getGlAccount().equals(glAccount.getGlAccount())
											&& !x.getId().equals(tempList.get(0).getId()))
									.findFirst();
						}

						if (tempObj != null && tempObj.get() != null) {
							remarks = remarks.substring(1);
							JpaCbmGlAccountTemp temp = tempObj.get();
							temp.setRemarks(remarks);
							glAccountTempDAO.save(temp);
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

	private List<JpaCbmGlAccount> converttoJpaCbmGlAccount(List<JpaCbmGlAccountTemp> cbmGlAccountTemp) {
		List<JpaCbmGlAccount> list = new ArrayList<>();
		for (JpaCbmGlAccountTemp jpaCbmGlAccountTemp : cbmGlAccountTemp) {
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			JpaCbmGlAccount jpaCbmGlAccount = mapper.map(jpaCbmGlAccountTemp, JpaCbmGlAccount.class);
			list.add(jpaCbmGlAccount);
		}
		return list;

	}
}