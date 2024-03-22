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
import scrips.datamigration.jpa.account.AccountDAO;
import scrips.datamigration.jpa.account.AccountTempDAO;
import scrips.datamigration.jpa.account.JpaAccount;
import scrips.datamigration.jpa.account.JpaAccountTemp;
import scrips.datamigration.jpa.fileupload.JpaFileUploadDetails;
import scrips.datamigration.jpa.fileupload.JpaFileUploadHeader;
import scrips.datamigration.jpa.member.MemberDAO;

@Service
@Slf4j
public class AccountService {

	private final Logger logger = LogManager.getLogger(AccountService.class);
	@Autowired
	ReadFileAndConvertService fileConvertService;
	@Autowired
	ValidationService validationService;
	@Autowired
	FileUploadExecutionService fileuploadExecService;
	@Autowired
	FileUploadErrorService fileErrorService;
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	AccountTempDAO accountTempDAO;
	@Autowired
	FileUploadService fileService;
	@Autowired
	MemberDAO memberDAO;

	public String migrateAccount(JpaFileUploadHeader fileHeaderObj, List<JpaFileUploadDetails> draftDBDetails,
			List<String> fileRecords) throws NumberFormatException, ParseException, DatabaseException {
		fileConvertService.createAndSaveAccountSourceData(fileRecords, draftDBDetails);
		boolean status = true;
		List<JpaAccountTemp> accountObjlist = null;
		List<JpaAccount> jpaAccount = null;
		try {
			accountObjlist = fileConvertService.convertToAccountList();
			jpaAccount = converttoJpaAccount(accountObjlist);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
//			List<JpaFileUploadExecution> fileExecList = new ArrayList<JpaFileUploadExecution>();
//		List<JpaFileUploadError> fileErrList = new ArrayList<JpaFileUploadError>();

		if (accountObjlist != null && jpaAccount != null && !jpaAccount.isEmpty()) {
//		accountObjlist.stream().forEach(account -> {
			for (JpaAccount account : jpaAccount) {
				try {
					JpaAccount duplicateAccount = accountDAO.findByAccountNumber(account.getAccountNumber());

					String remarks = validationService.validationJpaAccount(account);
					if (duplicateAccount != null) {
						remarks = remarks.concat(",Duplicate AccountNo");
					}
					if (remarks.isEmpty() && duplicateAccount == null) {
						Optional<JpaAccountTemp> tempObj = accountObjlist.stream()
								.filter(x -> x.getAccountNumber().equals(account.getAccountNumber())).findAny();
						JpaAccountTemp temp = tempObj.get();

						boolean isLiveDataHasError = false;
						accountTempDAO.save(temp);
						try {
							accountDAO.save(account);
						} catch (Exception e) {
							isLiveDataHasError = true;
							logger.error("error while saving account live table data {}", e.getMessage());
							e.printStackTrace();
						}

						if (isLiveDataHasError) {
							status = false;
							temp.setRemarks("Error while saving account live table data");
							accountTempDAO.save(temp);
						}
						
//						JpaFileUploadExecution fileUplodExec = fileuploadExecService.createFileUploadExecution(
//								fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//								UUID.randomUUID().toString(), "Migratted Sucessfully");
//						fileExecList.add(fileUplodExec);
					} else {

						Optional<JpaAccountTemp> tempObj = accountObjlist.stream()
								.filter(x -> x.getAccountNumber().equals(account.getAccountNumber())).findFirst();
						if (duplicateAccount != null) {
							List<JpaAccountTemp> tempList = new ArrayList<JpaAccountTemp>();
							tempList.add(tempObj.get());
							tempObj = accountObjlist.stream()
									.filter(x -> x.getAccountNumber().equals(account.getAccountNumber())
											&& !x.getId().equals(tempList.get(0).getId()))
									.findFirst();
						}
						if (tempObj != null && tempObj.get() != null) {
							remarks = remarks.substring(1);
							JpaAccountTemp temp = tempObj.get();
							temp.setRemarks(remarks);
							accountTempDAO.save(temp);
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
					status = false;
				}
			}
		}

		return status ? "noErrors" : "hasErrors";
//		return fileErrList.size() > 0 ? "Partially Migrated" : "Migrated Sucessfully";
	}

	public List<JpaAccount> converttoJpaAccount(List<JpaAccountTemp> accountList) {
		List<JpaAccount> list = new ArrayList<>();
		for (JpaAccountTemp jpaAccountTemp : accountList) {
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			JpaAccount jpaAccount = mapper.map(jpaAccountTemp, JpaAccount.class);
			list.add(jpaAccount);
		}
		return list;
	}
}
