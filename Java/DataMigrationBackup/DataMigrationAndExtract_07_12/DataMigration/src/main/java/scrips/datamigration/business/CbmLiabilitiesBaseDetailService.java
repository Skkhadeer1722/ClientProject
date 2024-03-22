package scrips.datamigration.business;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import scrips.datamigration.exception.DatabaseException;
import scrips.datamigration.exception.FailedValidationException;
import scrips.datamigration.jpa.cbm.JpaCbmLiabilitiesBaseDetail;
import scrips.datamigration.jpa.cbm.JpaCbmLiabilitiesBaseDetailDAO;
import scrips.datamigration.jpa.cbm.JpaCbmLiabilitiesBaseDetailTemp;
import scrips.datamigration.jpa.cbm.JpaCbmLiabilitiesBaseDetailTempDAO;
import scrips.datamigration.jpa.fileupload.JpaFileUploadDetails;
import scrips.datamigration.jpa.fileupload.JpaFileUploadHeader;
import scrips.datamigration.jpa.member.MemberDAO;

@Service
@Slf4j
public class CbmLiabilitiesBaseDetailService {
	private final Logger logger = LogManager.getLogger(CbmLiabilitiesBaseDetailService.class);

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
	JpaCbmLiabilitiesBaseDetailDAO liabilitiesBaseDetailDAO;

	@Autowired
	JpaCbmLiabilitiesBaseDetailTempDAO liabilitiesBaseDetailTempDAO;
	@Autowired
	MemberDAO memberDAO;

	public String migrateCbmLiabilitiesBaseDetail(JpaFileUploadHeader fileHeaderObj,
			List<JpaFileUploadDetails> draftDBDetails, List<String> fileRecords)
			throws NumberFormatException, ParseException, DatabaseException, SQLException {
		fileConvertService.createAndSaveCbmLiabilitiesBaseDetailSourceData(fileRecords, draftDBDetails);

		boolean status = true;
		List<JpaCbmLiabilitiesBaseDetailTemp> cbmLiabilitiesBaseDetailTemp = null;
		List<JpaCbmLiabilitiesBaseDetail> cbmLiabilitiesBaseDetail = null;

		try {
			cbmLiabilitiesBaseDetailTemp = fileConvertService.convertToJpaCbmLiabilitiesBaseDetailList();
			cbmLiabilitiesBaseDetail = converttoJpaCbmLiabilitiesBaseDetail(cbmLiabilitiesBaseDetailTemp);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}

//		List<JpaFileUploadExecution> fileExecList = new ArrayList<JpaFileUploadExecution>();
//		List<JpaFileUploadError> fileErrList = new ArrayList<JpaFileUploadError>();
		if (cbmLiabilitiesBaseDetailTemp != null && cbmLiabilitiesBaseDetail != null
				&& !cbmLiabilitiesBaseDetail.isEmpty()) {
			for (JpaCbmLiabilitiesBaseDetailTemp cbmLiabilitiesBaseDetails : cbmLiabilitiesBaseDetailTemp) {
//			cbmLiabilitiesBaseDetailTemp.stream().forEach(cbmLiabilitiesBaseDetails -> {
				try {
					Date sourceStartDate = cbmLiabilitiesBaseDetails.getStartDate();
					Date sourceEndDate = cbmLiabilitiesBaseDetails.getEndDate();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date startDate = sdf.parse("2023-06-29");
					Date endDate = sdf.parse("2023-07-12");

					List<JpaCbmLiabilitiesBaseDetail> duplicateAccounts = liabilitiesBaseDetailDAO
							.findByMemberId(cbmLiabilitiesBaseDetails.getMemberId());

					String remarks = validationService.validationJpaCbmLiabilitiesBaseDetail(cbmLiabilitiesBaseDetails);

					if (sourceStartDate.compareTo(startDate) <= 0)
						remarks = remarks.concat(",Start Date should be Greater than or equal to 29th June 2023");
					if (sourceEndDate.compareTo(endDate) >= 0)
						remarks = remarks.concat(",End Date should be Less than or equal to 12th July 2023");
					if (remarks.isEmpty() && duplicateAccounts.size() == 0) {
						JpaCbmLiabilitiesBaseDetailTemp tempObj = cbmLiabilitiesBaseDetails;

						log.info("validated");
						boolean isLiveDataHasError = false;
						liabilitiesBaseDetailTempDAO.save(tempObj);
						try {
							JpaCbmLiabilitiesBaseDetail jpaCbmLiabilitiesBaseDetail = liabilitiesBaseDetailDAO
									.save(converttoJpaCbmLiabilitiesBaseDetail(Arrays.asList(tempObj)).get(0));
							cbmLiabilitiesBaseDetails.setLiveTableId(jpaCbmLiabilitiesBaseDetail.getId());
						} catch (Exception e) {
							isLiveDataHasError = true;
							logger.error("error while saving cbm liabilities base detail live table data {}",
									e.getMessage());
							e.printStackTrace();
						}
						if (isLiveDataHasError) {
							status = false;
							tempObj.setRemarks("Error while saving cbm liabilities base detail live table data");
							liabilitiesBaseDetailTempDAO.save(tempObj);
						}
						
//							JpaFileUploadExecution fileUplodExec = fileuploadExecService.createFileUploadExecution(
//									fileHeaderObj.getFileUploadId(), fileHeaderObj.getFileUploadCode(),
//									UUID.randomUUID().toString(), "Migratted Sucessfully");
//							fileExecList.add(fileUplodExec);
					} else {
						if (duplicateAccounts.size() > 0) {
							remarks = remarks.concat(",Duplicate MemberId");
						}
						JpaCbmLiabilitiesBaseDetailTemp tempObj = cbmLiabilitiesBaseDetails;
						if (tempObj != null) {
							remarks = remarks.substring(1);
							JpaCbmLiabilitiesBaseDetailTemp temp = cbmLiabilitiesBaseDetails;
							temp.setRemarks(remarks);
							liabilitiesBaseDetailTempDAO.save(temp);
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

	public List<JpaCbmLiabilitiesBaseDetail> converttoJpaCbmLiabilitiesBaseDetail(
			List<JpaCbmLiabilitiesBaseDetailTemp> jpaCbmLiabilitiesBaseDetailTempList) {
		List<JpaCbmLiabilitiesBaseDetail> jpaCbmLiabilitiesBaseDetailList = new ArrayList<JpaCbmLiabilitiesBaseDetail>();
		for (JpaCbmLiabilitiesBaseDetailTemp jpaCbmLiabilitiesBaseDetailTemp : jpaCbmLiabilitiesBaseDetailTempList) {
			JpaCbmLiabilitiesBaseDetail jpaCbmLiabilitiesBaseDetail = JpaCbmLiabilitiesBaseDetail.builder()
//				.id(Long.parseLong("0"))
					.memberId(jpaCbmLiabilitiesBaseDetailTemp.getMemberId())
					.startDate(jpaCbmLiabilitiesBaseDetailTemp.getStartDate())
					.endDate(jpaCbmLiabilitiesBaseDetailTemp.getEndDate())
					.qlLb(jpaCbmLiabilitiesBaseDetailTemp.getQlLb()).build();
			jpaCbmLiabilitiesBaseDetailList.add(jpaCbmLiabilitiesBaseDetail);
		}
		return jpaCbmLiabilitiesBaseDetailList;
	}
}
