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
import scrips.datamigration.jpa.accountposition.AccountPositionDAO;
import scrips.datamigration.jpa.accountposition.AccountPositionDAOTemp;
import scrips.datamigration.jpa.accountposition.JpaAccountPosition;
import scrips.datamigration.jpa.accountposition.JpaAccountPositionTemp;
import scrips.datamigration.jpa.fileupload.JpaFileUploadDetails;
import scrips.datamigration.jpa.fileupload.JpaFileUploadHeader;
import scrips.datamigration.jpa.member.MemberDAO;

@Service
@Slf4j
public class AccountPositionServvice {
	private final Logger logger = LogManager.getLogger(AccountPositionServvice.class);
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
	MemberDAO memberDAO;
	@Autowired
	AccountPositionDAO accountPosDAO;
	@Autowired
	AccountPositionDAOTemp accountPosTempDAO;

	public String migrateAccountPosition(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) throws DatabaseException, FailedValidationException, ParseException {
		fileConvertService.createAndSaveAccountPositionSourceData(fileRecords, draftDBDetails);
		boolean status = true;
		List<JpaAccountPositionTemp> accountObjlistTemp = null;
		List<JpaAccountPosition> accountObjlist = null;

		try {
			accountObjlistTemp = fileConvertService.convertToJpaAccountPosition();
			accountObjlist = converttoJpaAccPosition(accountObjlistTemp);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
//		List<JpaFileUploadExecution> fileExecList = new ArrayList<JpaFileUploadExecution>();
//		List<JpaFileUploadError> fileErrList = new ArrayList<JpaFileUploadError>();
		if (accountObjlistTemp != null && accountObjlist != null && !accountObjlist.isEmpty()) {
			for (JpaAccountPosition accountPosition : accountObjlist) {
//		accountObjlistTemp.forEach(accountPositionTemp -> {
				try {
					JpaAccountPosition duplicateAccount = accountPosDAO.findByAccountId(accountPosition.getAccountId());
					String remarks = validationService.validationJpaAccountPosition(accountPosition);
					if (duplicateAccount != null) {
						remarks = remarks.concat(",Duplicate AccountId");
					}
					if (remarks.isEmpty() && duplicateAccount == null) {

						Optional<JpaAccountPositionTemp> tempObj = accountObjlistTemp.stream()
								.filter(x -> x.getAccountId().equals(accountPosition.getAccountId())).findAny();
						JpaAccountPositionTemp temp = tempObj.get();
						boolean isLiveDataHasError = false;
						accountPosTempDAO.save(temp);
						try {
							accountPosDAO.save(accountPosition);
						} catch (Exception e) {
							isLiveDataHasError = true;
							logger.error(", error while saving account position live table data {}", e.getMessage());
							e.printStackTrace();
						}

						if (isLiveDataHasError) {
							status = false;
							temp.setRemarks(", Error while saving account position live table data");
							accountPosTempDAO.save(temp);
						}
						
//					JpaFileUploadExecution fileUplodExec = fileuploadExecService.createFileUploadExecution(
//							fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//							UUID.randomUUID().toString(), "Migratted Sucessfully");
//					fileExecList.add(fileUplodExec);
					} else {
						Optional<JpaAccountPositionTemp> tempObj = accountObjlistTemp.stream()
								.filter(x -> x.getAccountId().equals(accountPosition.getAccountId())).findFirst();
						if (duplicateAccount != null) {
							List<JpaAccountPositionTemp> tempList = new ArrayList<JpaAccountPositionTemp>();
							tempList.add(tempObj.get());
							tempObj = accountObjlistTemp.stream()
									.filter(x -> x.getAccountId().equals(accountPosition.getAccountId())
											&& !x.getId().equals(tempList.get(0).getId()))
									.findFirst();
						}

						if (tempObj != null && tempObj.get() != null) {
							remarks = remarks.substring(1);
							JpaAccountPositionTemp temp = tempObj.get();
							temp.setRemarks(remarks);
							accountPosTempDAO.save(temp);
						}
//					JpaFileUploadError errObj = fileErrorService.createFileUploadError(
//							fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//							UUID.randomUUID().toString(), 101);
//					fileErrList.add(errObj);
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

	public List<JpaAccountPosition> converttoJpaAccPosition(List<JpaAccountPositionTemp> list2) {
		List<JpaAccountPosition> list = new ArrayList<>();
		for (JpaAccountPositionTemp jpaAccountTemp : list2) {
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			JpaAccountPosition jpaAccount = mapper.map(jpaAccountTemp, JpaAccountPosition.class);
			list.add(jpaAccount);
			// log.info(jpaAccountTemp.getAccountSettlementPurpose().getCurrencyCode());
		}
		return list;
	}
}