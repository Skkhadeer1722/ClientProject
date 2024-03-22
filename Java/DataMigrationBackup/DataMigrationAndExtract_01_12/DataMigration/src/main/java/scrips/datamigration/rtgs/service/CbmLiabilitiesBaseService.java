package scrips.datamigration.rtgs.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.common.UserData;
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.regex.RegexValidation;
import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBase;
import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBaseSource;
import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBaseTemp;
import scrips.datamigration.rtgs.model.JpaMember;
import scrips.datamigration.rtgs.repository.JpaCbmLiabilitiesBaseDAO;
import scrips.datamigration.rtgs.repository.JpaCbmLiabilitiesBaseSourceDAO;
import scrips.datamigration.rtgs.repository.JpaCbmLiabilitiesBaseTempDAO;
import scrips.datamigration.rtgs.repository.MemberDAO;

@Service

public class CbmLiabilitiesBaseService {

	private final Logger logger = LogManager.getLogger(CbmLiabilitiesBaseService.class);

	@Autowired
	JpaCbmLiabilitiesBaseDAO jpaCbmLiabilitiesBaseDAO;

	@Autowired
	JpaCbmLiabilitiesBaseTempDAO cbmLiabilitiesBaseTempDAO;

	@Autowired
	MemberDAO memberDAO;

	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	private UserData userData;

	@Autowired
	JpaCbmLiabilitiesBaseDAO cbmLiabilitiesBaseDAO;

	@Autowired
	JpaCbmLiabilitiesBaseSourceDAO cbmLiabilitiesBaseSourceDAO;
	
	@Autowired
	private Environment env;

	public String migrateCbmLiabilitiesBase(JpaFileUploadHeader fileHeaderObj,
			List<JpaFileUploadDetails> draftDBDetails, List<String> fileRecords)
			 {
		List<JpaCbmLiabilitiesBaseSource> cbmLiabilitiesBaseSourceList = createAndSaveCbmLiabilitiesBaseSourceData(
				fileRecords, draftDBDetails);
		List<JpaCbmLiabilitiesBaseTemp> cbmLiabilitiesBaseTempList = convertLiabilitiesBaseSourceToLiabilitiesBaseTemp(
				cbmLiabilitiesBaseSourceList);
		boolean noErrors =saveLiabilitiesBaseLiveTableData(cbmLiabilitiesBaseTempList);
		return (cbmLiabilitiesBaseSourceList.size()==cbmLiabilitiesBaseTempList.size() && noErrors)? "noErrors" : "hasErrors";
	}

	private boolean saveLiabilitiesBaseLiveTableData(List<JpaCbmLiabilitiesBaseTemp> cbmLiabilitiesBaseTempList) {
		boolean noErrors = true;
		for (JpaCbmLiabilitiesBaseTemp cbmLiabilitiesBaseTemp : cbmLiabilitiesBaseTempList) {
			if (cbmLiabilitiesBaseTemp.getRemarks() == null || cbmLiabilitiesBaseTemp.getRemarks().isEmpty()
					|| cbmLiabilitiesBaseTemp.getRemarks().isBlank()) {
				try {
					JpaCbmLiabilitiesBase cbmLiabilitiesBase = converttoJpaCbmLiabilitiesBase(cbmLiabilitiesBaseTemp);
					String remarks = duplicateMemberId(cbmLiabilitiesBase);
					remarks = remarks.concat(validationJpaCbmLiabilitiesBaseLiveData(cbmLiabilitiesBase));
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						cbmLiabilitiesBaseTemp.setRemarks(String.join(", ", remarks));
						cbmLiabilitiesBaseTempDAO.save(cbmLiabilitiesBaseTemp);
					} else
						cbmLiabilitiesBaseDAO.save(cbmLiabilitiesBase);
				} catch (Exception e) {
					cbmLiabilitiesBaseTemp.setRemarks("error while saving cbm liabilities base live table data");
					e.printStackTrace();
					cbmLiabilitiesBaseTempDAO.save(cbmLiabilitiesBaseTemp);
					noErrors= false;
				}
			}
		}
		return noErrors;
	}

	private String duplicateMemberId(JpaCbmLiabilitiesBase cbmLiabilitiesBase) {
		JpaCbmLiabilitiesBase duplicateMemberId = cbmLiabilitiesBaseDAO
				.findByMemberId(cbmLiabilitiesBase.getMemberId());
		String remarks = "";
		if (duplicateMemberId != null)
			remarks = remarks.concat(",Duplicate member_id");
		return remarks;
	}
	
	private String validationJpaCbmLiabilitiesBaseLiveData(JpaCbmLiabilitiesBase cbmLiabilitiesBase) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(cbmLiabilitiesBase.getMemberId(), "Id", "Member ID"));
		remarks.append(regexValidation.regexValidator(cbmLiabilitiesBase.getMemberId(), "length36", "Member ID"));
		remarks.append(
				regexValidation.regexValidator(cbmLiabilitiesBase.getCurrencyCode(), "currencycode", "Currency Code"));
		remarks.append(
				regexValidation.regexValidator(cbmLiabilitiesBase.getCurrencyCode(), "currencycode", "Currency Code"));
		remarks.append(regexValidation.regexValidator(cbmLiabilitiesBase.getQlLbType(), "special", "QL/LB Type"));
		remarks.append(regexValidation.regexValidator(cbmLiabilitiesBase.getQlLbType(), "length3", "QL/LB Type"));

		if (cbmLiabilitiesBase.getModifiedBy() != null && !cbmLiabilitiesBase.getModifiedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(cbmLiabilitiesBase.getModifiedBy(), "length36", "Modified By"));
		}
		if (cbmLiabilitiesBase.getApprovedBy() != null && !cbmLiabilitiesBase.getApprovedBy().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(cbmLiabilitiesBase.getApprovedBy(), "length36", "Approved By"));
		}
		return remarks.toString().trim();
	}

	private List<JpaCbmLiabilitiesBaseSource> createAndSaveCbmLiabilitiesBaseSourceData(List<String> fileRecords,
			List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaCbmLiabilitiesBaseSource> cbmLiabilitiesBaseSourceList = new ArrayList<JpaCbmLiabilitiesBaseSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter=CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 3, draftDBDetails.size(),delimiter);
				if (error != null) {
					JpaCbmLiabilitiesBaseSource jpaCbmLiabilitiesBaseSource = new JpaCbmLiabilitiesBaseSource();
					jpaCbmLiabilitiesBaseSource.setRemarks(error);
					cbmLiabilitiesBaseSourceDAO.save(jpaCbmLiabilitiesBaseSource);
					continue;
				}

				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line,delimiter);
				JpaCbmLiabilitiesBaseSource jpaCbmLiabilitiesBaseSource = JpaCbmLiabilitiesBaseSource.builder().id(id)
						.memberId(dbRecordsMap.get("member_code")).sectorId(null)
						.currencyCode(dbRecordsMap.get("currency_code")).mcbId(null)
						.qlLbType(dbRecordsMap.get("ql_lb_type")).action(null).status(null).modifiedBy(null)
						.modifiedDate(null).approvedBy(null).approvedDate(null).createdDate(null)
						.effectiveDate(dbRecordsMap.get("effective_date")).approvalRemark(null).workflowStatusId(null)
						.build();

				String remarks = validationJpaCbmLiabilitiesBaseSource(jpaCbmLiabilitiesBaseSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				jpaCbmLiabilitiesBaseSource.setRemarks(remarks);
				cbmLiabilitiesBaseSourceList.add(jpaCbmLiabilitiesBaseSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return cbmLiabilitiesBaseSourceList;
	}

	private String validationJpaCbmLiabilitiesBaseSource(JpaCbmLiabilitiesBaseSource cbmLiabilitiesBase) {
		StringBuffer remarks = new StringBuffer("");
		remarks.append(regexValidation.regexValidator(cbmLiabilitiesBase.getMemberId(), "special", "Member ID"));
		remarks.append(regexValidation.regexValidator(cbmLiabilitiesBase.getMemberId(), "length36", "Member ID"));
		remarks.append(
				regexValidation.regexValidator(cbmLiabilitiesBase.getCurrencyCode(), "currencycode", "Currency Code"));
		remarks.append(
				regexValidation.regexValidator(cbmLiabilitiesBase.getCurrencyCode(), "currencycode", "Currency Code"));
		remarks.append(regexValidation.regexValidator(cbmLiabilitiesBase.getQlLbType(), "special", "QL/LB Type"));
		remarks.append(regexValidation.regexValidator(cbmLiabilitiesBase.getQlLbType(), "length3", "QL/LB Type"));
		if (!CommonUtils.validateParseDate_yyyyMMdd_HHmmss(cbmLiabilitiesBase.getEffectiveDate()))
			remarks.append(",Invalid Effective Date");

		return remarks.toString().trim();
	}

	private List<JpaCbmLiabilitiesBaseTemp> convertLiabilitiesBaseSourceToLiabilitiesBaseTemp(
			List<JpaCbmLiabilitiesBaseSource> cbmLiabilitiesBaseSourceList) {
		List<JpaCbmLiabilitiesBaseTemp> jpaCbmLiabilitiesBaseTempList = new ArrayList<JpaCbmLiabilitiesBaseTemp>();
		String id = userData.getSystemUserId();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (JpaCbmLiabilitiesBaseSource sourceRec : cbmLiabilitiesBaseSourceList) {
			cbmLiabilitiesBaseSourceDAO.save(sourceRec);
			try {
				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {
					String memberId = null;
					List<JpaMember> memberList = memberDAO.findByMemberCode(sourceRec.getMemberId());
					if (memberList != null && memberList.size() > 0)
						memberId = memberList.get(0).getId();
					List<String> validationRemarks = new ArrayList<String>();
					if (memberId == null || memberId.trim().isEmpty())
						validationRemarks.add("MemberCode is not found in Member Table");

					Date date = new Date();
					JpaCbmLiabilitiesBaseTemp jpaCbmLiabilitiesBase = JpaCbmLiabilitiesBaseTemp.builder()
							.id(sourceRec.getId()).memberId(memberId)
//								.sectorId(sourceRec.getSectorId())
							.currencyCode(sourceRec.getCurrencyCode())
//								.mcbId(sourceRec.getMcbId())
							.qlLbType(sourceRec.getQlLbType()).action(" ").status("ACTIVE").modifiedBy(id)
							.modifiedDate(date).approvedBy(id).approvedDate(date).createdDate(date)
							.effectiveDate(simpleDateFormat.parse(sourceRec.getEffectiveDate()))
							.approvalRemark("Data Migration").workflowStatusId(null).build();

					if (validationRemarks.size() > 0) {
						jpaCbmLiabilitiesBase.setRemarks(String.join(", ", validationRemarks));
					}

					cbmLiabilitiesBaseTempDAO.save(jpaCbmLiabilitiesBase);
					jpaCbmLiabilitiesBaseTempList.add(jpaCbmLiabilitiesBase);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving cbm liabilities base temp table data");
				cbmLiabilitiesBaseSourceDAO.save(sourceRec);
			}
		}
		return jpaCbmLiabilitiesBaseTempList;
	}



	private JpaCbmLiabilitiesBase converttoJpaCbmLiabilitiesBase(JpaCbmLiabilitiesBaseTemp cbmLiabilitiesBasesTempObj) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaCbmLiabilitiesBase jpaCbmLiabilitiesBase = mapper.map(cbmLiabilitiesBasesTempObj,
				JpaCbmLiabilitiesBase.class);
		return jpaCbmLiabilitiesBase;
	}
}
