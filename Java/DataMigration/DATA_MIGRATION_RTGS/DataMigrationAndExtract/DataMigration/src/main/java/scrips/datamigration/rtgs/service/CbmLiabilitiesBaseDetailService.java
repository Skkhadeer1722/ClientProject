package scrips.datamigration.rtgs.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.regex.RegexValidation;
import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBaseDetail;
import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBaseDetailSource;
import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBaseDetailTemp;
import scrips.datamigration.rtgs.model.JpaMember;
import scrips.datamigration.rtgs.repository.JpaCbmLiabilitiesBaseDetailDAO;
import scrips.datamigration.rtgs.repository.JpaCbmLiabilitiesBaseDetailSourceDAO;
import scrips.datamigration.rtgs.repository.JpaCbmLiabilitiesBaseDetailTempDAO;
import scrips.datamigration.rtgs.repository.JpaMemberDAO;

@Service
public class CbmLiabilitiesBaseDetailService {
	private final Logger logger = LogManager.getLogger(CbmLiabilitiesBaseDetailService.class);

	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	JpaCbmLiabilitiesBaseDetailDAO liabilitiesBaseDetailDAO;

	@Autowired
	JpaCbmLiabilitiesBaseDetailTempDAO liabilitiesBaseDetailTempDAO;

	@Autowired
	JpaCbmLiabilitiesBaseDetailSourceDAO cbmLiabilitiesBaseDetailSourceDAO;

	@Autowired
	private Environment env;

	@Autowired
	JpaMemberDAO memberDAO;

	public String migrateCbmLiabilitiesBaseDetail(JpaFileUploadHeader fileHeaderObj,
			List<JpaFileUploadDetails> draftDBDetails, List<String> fileRecords) {
		List<JpaCbmLiabilitiesBaseDetailSource> cbmLiabilitiesBaseDetailSourceList = createAndSaveCbmLiabilitiesBaseDetailSourceData(
				fileRecords, draftDBDetails);
		List<JpaCbmLiabilitiesBaseDetailTemp> cbmLiabilitiesBaseDetailTempList = convertLiabilitiesBaseDetailSourceToLiabilitiesBaseDetailTemp(
				cbmLiabilitiesBaseDetailSourceList);
		boolean noErrors = saveLiabilitiesBaseDetailLiveTableData(cbmLiabilitiesBaseDetailTempList);
		return (cbmLiabilitiesBaseDetailSourceList.size() == cbmLiabilitiesBaseDetailTempList.size() && noErrors)
				? "noErrors"
				: "hasErrors";
	}

	private List<JpaCbmLiabilitiesBaseDetailSource> createAndSaveCbmLiabilitiesBaseDetailSourceData(
			List<String> fileRecords, List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaCbmLiabilitiesBaseDetailSource> cbmLiabilitiesBaseDetailSourceList = new ArrayList<JpaCbmLiabilitiesBaseDetailSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 3, draftDBDetails.size(), delimiter);
				if (error != null) {
					JpaCbmLiabilitiesBaseDetailSource jpaCbmLiabilitiesBaseDetailSource = new JpaCbmLiabilitiesBaseDetailSource();
					jpaCbmLiabilitiesBaseDetailSource.setRemarks(error);
					cbmLiabilitiesBaseDetailSourceDAO.save(jpaCbmLiabilitiesBaseDetailSource);
					continue;
				}
				Date date = new Date();
				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line, delimiter);
				JpaCbmLiabilitiesBaseDetailSource jpaCbmLiabilitiesBaseDetailSource = JpaCbmLiabilitiesBaseDetailSource
						.builder().id(id).memberId(dbRecordsMap.get("member_code"))
						.startDate(dbRecordsMap.get("start_date")).endDate(dbRecordsMap.get("end_date"))
						.qlLb(dbRecordsMap.get("ql_lb"))
						.migratedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date))
						.build();

				String remarks = validationJpaCbmLiabilitiesBaseDetailSource(jpaCbmLiabilitiesBaseDetailSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				jpaCbmLiabilitiesBaseDetailSource.setRemarks(remarks);
				cbmLiabilitiesBaseDetailSourceList.add(jpaCbmLiabilitiesBaseDetailSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return cbmLiabilitiesBaseDetailSourceList;
	}

	private String validationJpaCbmLiabilitiesBaseDetailSource(
			JpaCbmLiabilitiesBaseDetailSource cbmLiabilitiesBaseDetailsSource) {
		StringBuffer remarks = new StringBuffer("");
		if (!CommonUtils.validateParseDate_yyyyMMdd_HHmmss(cbmLiabilitiesBaseDetailsSource.getStartDate()))
			remarks.append(",Invalid Start Date");
		if (!CommonUtils.validateParseDate_yyyyMMdd_HHmmss(cbmLiabilitiesBaseDetailsSource.getEndDate()))
			remarks.append(",Invalid End Date");
		remarks.append(
				regexValidation.regexValidator(cbmLiabilitiesBaseDetailsSource.getMemberId(), "special", "Member ID"));
		remarks.append(
				regexValidation.regexValidator(cbmLiabilitiesBaseDetailsSource.getMemberId(), "length36", "Member ID"));
		if (cbmLiabilitiesBaseDetailsSource.getQlLb() != null
				&& !cbmLiabilitiesBaseDetailsSource.getQlLb().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(cbmLiabilitiesBaseDetailsSource.getQlLb(), "decimal", "QL/LB"));
			remarks.append(
					regexValidation.regexValidator(cbmLiabilitiesBaseDetailsSource.getQlLb(), "decimal_23", "QL/LB"));
		}
		return remarks.toString().trim();
	}

	private List<JpaCbmLiabilitiesBaseDetailTemp> convertLiabilitiesBaseDetailSourceToLiabilitiesBaseDetailTemp(
			List<JpaCbmLiabilitiesBaseDetailSource> cbmLiabilitiesBaseDetailSourceList) {
		List<JpaCbmLiabilitiesBaseDetailTemp> jpaCbmLiabilitiesBaseDetailTempList = new ArrayList<JpaCbmLiabilitiesBaseDetailTemp>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (JpaCbmLiabilitiesBaseDetailSource sourceRec : cbmLiabilitiesBaseDetailSourceList) {
			cbmLiabilitiesBaseDetailSourceDAO.save(sourceRec);

			try {
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {

					String memberId = "";
					List<JpaMember> memberList = memberDAO.findByMemberCode(sourceRec.getMemberId());
					if (memberList != null && memberList.size() > 0)
						memberId = memberList.get(0).getId();
					String remarks = "";
					if (memberId == null || memberId.trim().isEmpty())
						remarks = remarks.concat(",member_code is not found in Member Table");

					JpaCbmLiabilitiesBaseDetailTemp jpaCbmLiabilitiesBaseDetailTemp = JpaCbmLiabilitiesBaseDetailTemp
							.builder().id(sourceRec.getId()).memberId(memberId)
							.startDate(sdf.parse(sourceRec.getStartDate())).endDate(sdf.parse(sourceRec.getEndDate()))
							.qlLb(!CommonUtils.isNullOrEmpty(sourceRec.getQlLb()) ? null
									: new BigDecimal(sourceRec.getQlLb()))
							.migratedDate(sourceRec.getMigratedDate()).build();
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						jpaCbmLiabilitiesBaseDetailTemp.setRemarks(String.join(", ", remarks));
					}

					liabilitiesBaseDetailTempDAO.save(jpaCbmLiabilitiesBaseDetailTemp);
					jpaCbmLiabilitiesBaseDetailTempList.add(jpaCbmLiabilitiesBaseDetailTemp);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving cbm_liabilities_base_detail_temp table data");
				cbmLiabilitiesBaseDetailSourceDAO.save(sourceRec);
			}
		}
		return jpaCbmLiabilitiesBaseDetailTempList;
	}

	private boolean saveLiabilitiesBaseDetailLiveTableData(
			List<JpaCbmLiabilitiesBaseDetailTemp> cbmLiabilitiesBaseDetailTempList) {
		boolean noErrors = true;
		for (JpaCbmLiabilitiesBaseDetailTemp cbmLiabilitiesBaseDetailTemp : cbmLiabilitiesBaseDetailTempList) {
			if (cbmLiabilitiesBaseDetailTemp.getRemarks() == null || cbmLiabilitiesBaseDetailTemp.getRemarks().isEmpty()
					|| cbmLiabilitiesBaseDetailTemp.getRemarks().isBlank()) {
				try {
					Date sourceStartDate = cbmLiabilitiesBaseDetailTemp.getStartDate();
					Date sourceEndDate = cbmLiabilitiesBaseDetailTemp.getEndDate();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date startDate = sdf.parse("2023-06-29");
					Date endDate = sdf.parse("2023-07-12");

					String remarks = "";
					if (sourceStartDate.compareTo(startDate) <= 0)
						remarks = remarks.concat(",Start Date should be Greater than or equal to 29th June 2023");
					if (sourceEndDate.compareTo(endDate) >= 0)
						remarks = remarks.concat(",End Date should be Less than or equal to 12th July 2023");

					JpaCbmLiabilitiesBaseDetail cbmLiabilitiesBaseDetail = converttoJpaCbmLiabilitiesBaseDetail(
							cbmLiabilitiesBaseDetailTemp);

					remarks = remarks.concat(validationJpaCbmLiabilitiesBaseDetailLiveData(cbmLiabilitiesBaseDetail));
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						cbmLiabilitiesBaseDetailTemp.setRemarks(String.join(", ", remarks));
						liabilitiesBaseDetailTempDAO.save(cbmLiabilitiesBaseDetailTemp);
					} else {
						Long live_id = liabilitiesBaseDetailDAO.save(cbmLiabilitiesBaseDetail).getId();
						if (live_id != null) {
							cbmLiabilitiesBaseDetailTemp.setLiveTableId(live_id);
							liabilitiesBaseDetailTempDAO.save(cbmLiabilitiesBaseDetailTemp);
						}
					}
				} catch (Exception e) {
					cbmLiabilitiesBaseDetailTemp
							.setRemarks("error while saving cbm_liabilities_base_detail live table data");
					e.printStackTrace();
					liabilitiesBaseDetailTempDAO.save(cbmLiabilitiesBaseDetailTemp);
					noErrors = false;
				}
			}
		}
		return noErrors;
	}

	private JpaCbmLiabilitiesBaseDetail converttoJpaCbmLiabilitiesBaseDetail(
			JpaCbmLiabilitiesBaseDetailTemp jpaCbmLiabilitiesBaseDetailTempList) {
		JpaCbmLiabilitiesBaseDetail jpaCbmLiabilitiesBaseDetail = JpaCbmLiabilitiesBaseDetail.builder()
//				.id(Long.parseLong("0"))
				.memberId(jpaCbmLiabilitiesBaseDetailTempList.getMemberId())
				.startDate(jpaCbmLiabilitiesBaseDetailTempList.getStartDate())
				.endDate(jpaCbmLiabilitiesBaseDetailTempList.getEndDate())
				.qlLb(jpaCbmLiabilitiesBaseDetailTempList.getQlLb()).build();
		return jpaCbmLiabilitiesBaseDetail;
	}

	private String validationJpaCbmLiabilitiesBaseDetailLiveData(
			JpaCbmLiabilitiesBaseDetail cbmLiabilitiesBaseDetails) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(cbmLiabilitiesBaseDetails.getMemberId(), "Id", "Member ID"));
		remarks.append(
				regexValidation.regexValidator(cbmLiabilitiesBaseDetails.getMemberId(), "length36", "Member ID"));
		if (cbmLiabilitiesBaseDetails.getQlLb() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(cbmLiabilitiesBaseDetails.getQlLb()),
					"decimal", "QL/LB"));
			remarks.append(regexValidation.regexValidator(String.valueOf(cbmLiabilitiesBaseDetails.getQlLb()),
					"decimal_23", "QL/LB"));
		}
		return remarks.toString().trim();
	}

}
