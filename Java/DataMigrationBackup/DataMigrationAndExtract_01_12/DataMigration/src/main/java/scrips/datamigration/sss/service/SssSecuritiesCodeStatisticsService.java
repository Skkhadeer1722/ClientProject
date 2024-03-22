package scrips.datamigration.sss.service;

import java.util.ArrayList;
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
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.regex.RegexValidation;
import scrips.datamigration.sss.model.JpaSssSecuritiesCodeStatistics;
import scrips.datamigration.sss.model.JpaSssSecuritiesCodeStatisticsSource;
import scrips.datamigration.sss.model.JpaSssSecuritiesCodeStatisticsTemp;
import scrips.datamigration.sss.repository.SssSecuritiesCodeDAO;
import scrips.datamigration.sss.repository.SssSecuritiesCodeStatisticsDAO;
import scrips.datamigration.sss.repository.SssSecuritiesCodeStatisticsSourceDAO;
import scrips.datamigration.sss.repository.SssSecuritiesCodeStatisticsTempDAO;

@Service
public class SssSecuritiesCodeStatisticsService {
	private final Logger logger = LogManager.getLogger(SssSecuritiesCodeStatisticsService.class);
	

	@Autowired
	private RegexValidation regexValidation;

	@Autowired
	private Environment env;

	@Autowired
	SssSecuritiesCodeStatisticsDAO securitiesCodeStatisticsDAO;

	@Autowired
	SssSecuritiesCodeStatisticsSourceDAO securitiesCodeStatisticsSourceDAO;

	@Autowired
	SssSecuritiesCodeDAO securitesCodeDAO;

	@Autowired
	SssSecuritiesCodeStatisticsTempDAO securitiesCodeStatisticsTempDAO;


	public String migrateSssSecuritiesCodeStatistics(JpaFileUploadHeader fileHeaderObj,
			List<JpaFileUploadDetails> draftDBDetails, List<String> fileRecords){
		List<JpaSssSecuritiesCodeStatisticsSource> securitiesCodeStatisticsSourceList = createAndSaveSecuritiesCodeStatisticsSourceData(
				fileRecords, draftDBDetails);
		List<JpaSssSecuritiesCodeStatisticsTemp> securitiesCodeStatisticsTempList = convertCodeStatisticsSourceToCodeStatisticsTemp(
				securitiesCodeStatisticsSourceList);
		boolean noErrors =saveSecuritiesCodeStatisticsLiveTableData(securitiesCodeStatisticsTempList);
		return (securitiesCodeStatisticsSourceList.size()==securitiesCodeStatisticsTempList.size() && noErrors)? "noErrors" : "hasErrors";
	}

	private List<JpaSssSecuritiesCodeStatisticsSource> createAndSaveSecuritiesCodeStatisticsSourceData(
			List<String> fileRecords, List<JpaFileUploadDetails> draftDBDetails) {
		List<JpaSssSecuritiesCodeStatisticsSource> securitiesCodeStatisticsSourceListDAO = new ArrayList<JpaSssSecuritiesCodeStatisticsSource>();
		try {
			int lineCount = 0;
			for (String line : fileRecords) {
				lineCount++;
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				String delimiter=CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
				String error = CommonUtils.validateRawData(line, 9, draftDBDetails.size(),delimiter);
				if (error != null) {
					JpaSssSecuritiesCodeStatisticsSource securitiesCodeStatisticsSoure = new JpaSssSecuritiesCodeStatisticsSource();
					securitiesCodeStatisticsSoure.setRemarks(error);
					securitiesCodeStatisticsSourceDAO.save(securitiesCodeStatisticsSoure);
					continue;
				}
				Map<String, String> dbRecordsMap = CommonUtils.getDataMap(draftDBDetails, line,delimiter);
				JpaSssSecuritiesCodeStatisticsSource SssSecuritiesCodeStatisticsSource = JpaSssSecuritiesCodeStatisticsSource
						.builder().id(id).securitiesCode(dbRecordsMap.get("securities_code"))
						.totalRedeemedAmount(dbRecordsMap.get("total_redeemed_amount"))
						.totalNominalAmountIssuedForErf(dbRecordsMap.get("total_nominal_amount_issued_for_erf"))
						.totalRedeemedAmountForErf(dbRecordsMap.get("total_redeemed_amount_for_erf"))
						.outstandingNominalAmount(dbRecordsMap.get("outstanding_nominal_amount"))
						.nextCouponDate(dbRecordsMap.get("next_coupon_date"))
						.lastCouponDate(dbRecordsMap.get("last_coupon_date"))
						.nextIndexEndDate(dbRecordsMap.get("next_index_end_date"))
						.amountAllotedToCentralBank(dbRecordsMap.get("amount_alloted_to_central_bank"))
						.amountAllotedToOthers(dbRecordsMap.get("amount_alloted_to_others")).build();
				String remarks = validationSecuritiesCodeStatisticsSource(SssSecuritiesCodeStatisticsSource);
				remarks = !remarks.isBlank() || !remarks.isEmpty() ? remarks.substring(1) : null;
				SssSecuritiesCodeStatisticsSource.setRemarks(remarks);
				securitiesCodeStatisticsSourceListDAO.add(SssSecuritiesCodeStatisticsSource);
			}
			logger.info("Total records in File: {}" + lineCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while preparing source data ", e.getMessage());
		}
		return securitiesCodeStatisticsSourceListDAO;

	}

	private String validationSecuritiesCodeStatisticsSource(
			JpaSssSecuritiesCodeStatisticsSource sssSecuritiesCodeStatisticsSource) {
		StringBuffer remarks = new StringBuffer("");
		// Not Null Fields
		remarks.append(regexValidation.regexValidator(sssSecuritiesCodeStatisticsSource.getSecuritiesCode(), "special",
				"Securities Code"));
		remarks.append(regexValidation.regexValidator(sssSecuritiesCodeStatisticsSource.getSecuritiesCode(), "length12",
				"Securities Code"));
		remarks.append(regexValidation.regexValidator(
				String.valueOf(sssSecuritiesCodeStatisticsSource.getTotalRedeemedAmount()), "numeric",
				"Total Redeemed Amount"));
		remarks.append(regexValidation.regexValidator(sssSecuritiesCodeStatisticsSource.getOutstandingNominalAmount(),
				"numeric", "Outstanding Nominal Amount"));
		// Null Fields
		if (sssSecuritiesCodeStatisticsSource.getTotalRedeemedAmountForErf() != null
				&& !sssSecuritiesCodeStatisticsSource.getTotalRedeemedAmountForErf().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(sssSecuritiesCodeStatisticsSource.getTotalRedeemedAmountForErf(),
							"numeric", "Total Redeemed Amount For Erf"));
		}
		if (sssSecuritiesCodeStatisticsSource.getNextCouponDate() != null
				&& !sssSecuritiesCodeStatisticsSource.getNextCouponDate().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssSecuritiesCodeStatisticsSource.getNextCouponDate(),
					"numeric", "Next Coupon Date"));
		}
		if (sssSecuritiesCodeStatisticsSource.getLastCouponDate() != null
				&& !sssSecuritiesCodeStatisticsSource.getNextCouponDate().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssSecuritiesCodeStatisticsSource.getNextCouponDate(),
					"numeric", "Last Coupon Date"));
		}
		if (sssSecuritiesCodeStatisticsSource.getNextIndexEndDate() != null
				&& !sssSecuritiesCodeStatisticsSource.getNextIndexEndDate().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssSecuritiesCodeStatisticsSource.getNextIndexEndDate(),
					"numeric", "Next Index End Date"));
		}
		if (sssSecuritiesCodeStatisticsSource.getAmountAllotedToCentralBank() != null
				&& !sssSecuritiesCodeStatisticsSource.getAmountAllotedToCentralBank().trim().isEmpty()) {
			remarks.append(
					regexValidation.regexValidator(sssSecuritiesCodeStatisticsSource.getAmountAllotedToCentralBank(),
							"numeric", "Amount Alloted To Central Bank"));
		}
		if (sssSecuritiesCodeStatisticsSource.getAmountAllotedToOthers() != null
				&& !sssSecuritiesCodeStatisticsSource.getAmountAllotedToOthers().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(sssSecuritiesCodeStatisticsSource.getAmountAllotedToOthers(),
					"numeric", "Amount Alloted To Others"));
		}
		if (sssSecuritiesCodeStatisticsSource.getTotalNominalAmountIssuedForErf() != null
				&& !sssSecuritiesCodeStatisticsSource.getTotalNominalAmountIssuedForErf().trim().isEmpty()) {
			remarks.append(regexValidation.regexValidator(
					sssSecuritiesCodeStatisticsSource.getTotalNominalAmountIssuedForErf(), "numeric",
					"Total Nominal Amount Issued For Erf"));
		}
		return remarks.toString().trim();
	}

	private boolean saveSecuritiesCodeStatisticsLiveTableData(
			List<JpaSssSecuritiesCodeStatisticsTemp> securitiesCodeStatisticsTempList) {
		boolean noErrors = true;
		for (JpaSssSecuritiesCodeStatisticsTemp securitiesCodeStatisticsTemp : securitiesCodeStatisticsTempList) {
			if (securitiesCodeStatisticsTemp.getRemarks() == null || securitiesCodeStatisticsTemp.getRemarks().isEmpty()
					|| securitiesCodeStatisticsTemp.getRemarks().isBlank()) {
				try {
					JpaSssSecuritiesCodeStatistics securitiesCodeStatistics = converttoSecuritiesCodeStatistics(
							securitiesCodeStatisticsTemp);
					String remarks = duplicateSecuritiesCode(securitiesCodeStatistics);
					remarks = remarks.concat(validationSecuritiesCodeStatisticsLiveData(securitiesCodeStatistics));
					if (!remarks.isBlank() || !remarks.isEmpty()) {
						remarks = remarks.substring(1, remarks.length());
						securitiesCodeStatisticsTemp.setRemarks(String.join(", ", remarks));
						securitiesCodeStatisticsTempDAO.save(securitiesCodeStatisticsTemp);
					} else
						securitiesCodeStatisticsDAO.save(securitiesCodeStatistics);
				} catch (Exception e) {
					securitiesCodeStatisticsTemp
							.setRemarks("error while saving securities code statistics live table data");
					securitiesCodeStatisticsTempDAO.save(securitiesCodeStatisticsTemp);
					e.printStackTrace();
					noErrors= false; 
				}
			}
		}
		return noErrors;
	}
	
	private String duplicateSecuritiesCode(JpaSssSecuritiesCodeStatistics securitiesCodeStatistics) {
		JpaSssSecuritiesCodeStatistics duplicateSecuritiesCode = securitiesCodeStatisticsDAO
				.findBySecuritiesCode(securitiesCodeStatistics.getSecuritiesCode());
		String remarks = "";
		if (duplicateSecuritiesCode != null)
			remarks = remarks.concat(",Duplicate securities_code");
		return remarks;
	}


	private String validationSecuritiesCodeStatisticsLiveData(JpaSssSecuritiesCodeStatistics securitiesCodeStatistics) {
		StringBuffer remarks = new StringBuffer("");
		// Not Null Fields
		remarks.append(regexValidation.regexValidator(securitiesCodeStatistics.getSecuritiesCode(), "special",
				"Securities Code"));
		remarks.append(regexValidation.regexValidator(securitiesCodeStatistics.getSecuritiesCode(), "length12",
				"Securities Code"));
		remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCodeStatistics.getTotalRedeemedAmount()),
				"numeric", "Total Redeemed Amount"));
		remarks.append(
				regexValidation.regexValidator(String.valueOf(securitiesCodeStatistics.getOutstandingNominalAmount()),
						"numeric", "Outstanding Nominal Amount"));
		// Null Fields
		if (securitiesCodeStatistics.getTotalRedeemedAmountForErf() != null) {
			remarks.append(regexValidation.regexValidator(
					String.valueOf(securitiesCodeStatistics.getTotalRedeemedAmountForErf()), "numeric",
					"Total Redeemed Amount For Erf"));
		}
		if (securitiesCodeStatistics.getNextCouponDate() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCodeStatistics.getNextCouponDate()),
					"numeric", "Next Coupon Date"));
		}
		if (securitiesCodeStatistics.getLastCouponDate() != null) {
			remarks.append(regexValidation.regexValidator(String.valueOf(securitiesCodeStatistics.getNextCouponDate()),
					"numeric", "Last Coupon Date"));
		}
		if (securitiesCodeStatistics.getNextIndexEndDate() != null) {
			remarks.append(regexValidation.regexValidator(
					String.valueOf(securitiesCodeStatistics.getNextIndexEndDate()), "numeric", "Next Index End Date"));
		}
		if (securitiesCodeStatistics.getAmountAllotedToCentralBank() != null) {
			remarks.append(regexValidation.regexValidator(
					String.valueOf(securitiesCodeStatistics.getAmountAllotedToCentralBank()), "numeric",
					"Amount Alloted To Central Bank"));
		}
		if (securitiesCodeStatistics.getAmountAllotedToOthers() != null) {
			remarks.append(
					regexValidation.regexValidator(String.valueOf(securitiesCodeStatistics.getAmountAllotedToOthers()),
							"numeric", "Amount Alloted To Others"));
		}
		if (securitiesCodeStatistics.getTotalNominalAmountIssuedForErf() != null) {
			remarks.append(regexValidation.regexValidator(
					String.valueOf(securitiesCodeStatistics.getTotalNominalAmountIssuedForErf()), "numeric",
					"Total Nominal Amount Issued For Erf"));
		}
		return remarks.toString().trim();
	}


	private JpaSssSecuritiesCodeStatistics converttoSecuritiesCodeStatistics(
			JpaSssSecuritiesCodeStatisticsTemp tempObj) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		JpaSssSecuritiesCodeStatistics liveObj = mapper.map(tempObj, JpaSssSecuritiesCodeStatistics.class);
		return liveObj;
	}

	private List<JpaSssSecuritiesCodeStatisticsTemp> convertCodeStatisticsSourceToCodeStatisticsTemp(
			List<JpaSssSecuritiesCodeStatisticsSource> securitiesCodeStatisticsSourceList) {
		List<JpaSssSecuritiesCodeStatisticsTemp> securitiesCodeStatisticsTempList = new ArrayList<JpaSssSecuritiesCodeStatisticsTemp>();

		for (JpaSssSecuritiesCodeStatisticsSource sourceRec : securitiesCodeStatisticsSourceList) {
			securitiesCodeStatisticsSourceDAO.save(sourceRec);
			try {

				if (sourceRec.getRemarks() == null || sourceRec.getRemarks().isEmpty()
						|| sourceRec.getRemarks().isBlank()) {
					List<String> validationRemarks = new ArrayList<String>();
					JpaSssSecuritiesCodeStatisticsTemp SssSecuritiesCodeStatisticsTemp = JpaSssSecuritiesCodeStatisticsTemp
							.builder().id(sourceRec.getId()).securitiesCode(sourceRec.getSecuritiesCode())
							.totalRedeemedAmount(Long.parseLong(sourceRec.getTotalRedeemedAmount()))
							.totalRedeemedAmountForErf(Long.parseLong(sourceRec.getTotalRedeemedAmountForErf()))
							.totalNominalAmountIssuedForErf(
									Long.parseLong(sourceRec.getTotalNominalAmountIssuedForErf()))
							.outstandingNominalAmount(Long.parseLong(sourceRec.getOutstandingNominalAmount()))
							.nextCouponDate(!CommonUtils.isNullOrEmpty(sourceRec.getNextCouponDate()) ? null
									: Integer.parseInt(sourceRec.getNextCouponDate()))
							.lastCouponDate(!CommonUtils.isNullOrEmpty(sourceRec.getLastCouponDate()) ? null
									: Integer.parseInt(sourceRec.getLastCouponDate()))
							.nextIndexEndDate(!CommonUtils.isNullOrEmpty(sourceRec.getNextIndexEndDate()) ? null
									: Integer.parseInt(sourceRec.getNextIndexEndDate()))
							.amountAllotedToCentralBank(Long.parseLong(sourceRec.getAmountAllotedToCentralBank()))
							.amountAllotedToOthers(Long.parseLong(sourceRec.getAmountAllotedToOthers())).build();
					
					if (validationRemarks.size() > 0) {
						SssSecuritiesCodeStatisticsTemp.setRemarks(String.join(", ", validationRemarks));
					}
					securitiesCodeStatisticsTempDAO.save(SssSecuritiesCodeStatisticsTemp);
					securitiesCodeStatisticsTempList.add(SssSecuritiesCodeStatisticsTemp);
				}
			} catch (Exception e) {
				logger.error("Error in temp {}", e.getMessage());
				e.printStackTrace();
				sourceRec.setRemarks("Error while preparing or saving securities code statistics temp table data");
				securitiesCodeStatisticsSourceDAO.save(sourceRec);
			}
		}
		return securitiesCodeStatisticsTempList;
	}

}