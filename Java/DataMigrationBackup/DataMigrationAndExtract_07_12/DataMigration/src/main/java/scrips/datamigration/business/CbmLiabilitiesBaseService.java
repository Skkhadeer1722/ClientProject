package scrips.datamigration.business;

import java.sql.SQLException;
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
import scrips.datamigration.jpa.cbm.JpaCbmLiabilitiesBase;
import scrips.datamigration.jpa.cbm.JpaCbmLiabilitiesBaseDAO;
import scrips.datamigration.jpa.cbm.JpaCbmLiabilitiesBaseTemp;
import scrips.datamigration.jpa.cbm.JpaCbmLiabilitiesBaseTempDAO;
import scrips.datamigration.jpa.fileupload.JpaFileUploadDetails;
import scrips.datamigration.jpa.fileupload.JpaFileUploadHeader;
import scrips.datamigration.jpa.member.MemberDAO;

@Service
@Slf4j
public class CbmLiabilitiesBaseService {

	private final Logger logger = LogManager.getLogger(CbmLiabilitiesBaseService.class);
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
	JpaCbmLiabilitiesBaseDAO jpaCbmLiabilitiesBaseDAO;

	@Autowired
	JpaCbmLiabilitiesBaseTempDAO cbmLiabilitiesBaseTempDAO;

	@Autowired
	MemberDAO memberDAO;

	@Autowired
	JpaCbmLiabilitiesBaseDAO cbmLiabilitiesBaseDAO;

	public String migrateCbmLiabilitiesBase(JpaFileUploadHeader fileHeaderObj,
			List<JpaFileUploadDetails> draftDBDetails, List<String> fileRecords)
			throws NumberFormatException, ParseException, DatabaseException, SQLException {
		fileConvertService.createAndSaveCbmLiabilitiesBaseSourceData(fileRecords, draftDBDetails);
		boolean status = true;
		List<JpaCbmLiabilitiesBaseTemp> cbmLiabilitiesBaseTemp = null;
		List<JpaCbmLiabilitiesBase> cbmLiabilitiesBase = null;

		try {
			cbmLiabilitiesBaseTemp = fileConvertService.convertToJpaCbmLiabilitiesBaseList();
			cbmLiabilitiesBase = converttoJpaCbmLiabilitiesBase(cbmLiabilitiesBaseTemp);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}

//		List<JpaFileUploadExecution> fileExecList = new ArrayList<JpaFileUploadExecution>();
//		List<JpaFileUploadError> fileErrList = new ArrayList<JpaFileUploadError>();
		if (cbmLiabilitiesBaseTemp != null && cbmLiabilitiesBase != null && !cbmLiabilitiesBase.isEmpty()) {
			for (JpaCbmLiabilitiesBase cbmLiabilities : cbmLiabilitiesBase) {
//			cbmLiabilitiesBaseTemp.stream().forEach(cbmLiabilitiesTemp -> {
				try {

					JpaCbmLiabilitiesBase duplicateAccount = cbmLiabilitiesBaseDAO
							.findByMemberId(cbmLiabilities.getMemberId());
					String remarks = validationService.validationJpaCbmLiabilitiesBase(cbmLiabilities);
					if (duplicateAccount != null) {
						remarks = remarks.concat(",Duplicate MemberId");
					}
					if (remarks.isEmpty() && duplicateAccount == null) {
						Optional<JpaCbmLiabilitiesBaseTemp> tempObj = cbmLiabilitiesBaseTemp.stream()
								.filter(x -> x.getMemberId().equals(cbmLiabilities.getMemberId())).findAny();

						JpaCbmLiabilitiesBaseTemp temp = tempObj.get();
						log.info("validated");
						boolean isLiveDataHasError = false;
						cbmLiabilitiesBaseTempDAO.save(temp);
						try {
							cbmLiabilitiesBaseDAO.save(cbmLiabilities);
						} catch (Exception e) {
							isLiveDataHasError = true;
							logger.error("error while saving cbm liabilities base live table data {}", e.getMessage());
							e.printStackTrace();
						}
						if (isLiveDataHasError) {
							status = false;
							temp.setRemarks("Error while saving cbm liabilities base live table data");
							cbmLiabilitiesBaseTempDAO.save(temp);
						}
						
//							JpaFileUploadExecution fileUplodExec = fileuploadExecService.createFileUploadExecution(
//									fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//									UUID.randomUUID().toString(), "Migratted Sucessfully");
//							fileExecList.add(fileUplodExec);
					} else {
						Optional<JpaCbmLiabilitiesBaseTemp> tempObj = cbmLiabilitiesBaseTemp.stream()
								.filter(x -> x.getMemberId().equals(cbmLiabilities.getMemberId())).findAny();
						;
						if (duplicateAccount != null) {
							List<JpaCbmLiabilitiesBaseTemp> tempList = new ArrayList<JpaCbmLiabilitiesBaseTemp>();
							tempList.add(tempObj.get());
							tempObj = cbmLiabilitiesBaseTemp.stream()
									.filter(x -> x.getMemberId().equals(cbmLiabilities.getMemberId())
											&& !x.getId().equals(tempList.get(0).getId()))
									.findFirst();
						}
						if (tempObj != null && tempObj.get() != null) {
							remarks = remarks.substring(1);
							JpaCbmLiabilitiesBaseTemp temp = tempObj.get();
							temp.setRemarks(remarks);
							cbmLiabilitiesBaseTempDAO.save(temp);
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
					status = false;
				}
			}
		}

		return status ? "noErrors" : "hasErrors";
//		return fileErrList.size() > 0 ? "Partially Migrated" : "Migrated Sucessfully";
	}

	public List<JpaCbmLiabilitiesBase> converttoJpaCbmLiabilitiesBase(
			List<JpaCbmLiabilitiesBaseTemp> cbmLiabilitiesBasesTemp) {
		List<JpaCbmLiabilitiesBase> list = new ArrayList<>();
		for (JpaCbmLiabilitiesBaseTemp jpaCbmLiabilitiesBaseTemp : cbmLiabilitiesBasesTemp) {
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			JpaCbmLiabilitiesBase jpaCbmLiabilitiesBase = mapper.map(jpaCbmLiabilitiesBaseTemp,
					JpaCbmLiabilitiesBase.class);
			list.add(jpaCbmLiabilitiesBase);
		}
		return list;
	}
}
