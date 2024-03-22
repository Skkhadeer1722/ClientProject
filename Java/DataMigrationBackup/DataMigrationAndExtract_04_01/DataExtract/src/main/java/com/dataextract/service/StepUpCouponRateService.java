package com.dataextract.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataextract.common.CommonUtils;
import com.dataextract.sgs.model.StepUpCouponRate;
import com.dataextract.sgs.repository.StepUpCouponRateDAO;

@Service
public class StepUpCouponRateService {

	@Autowired
	private StepUpCouponRateDAO couponRateDAO;
	@Autowired
	private WriteFileService fileService;
	@Autowired
	private CommonUtils commonUtils;

	public String getAll(String name) {

		List<StepUpCouponRate> stepUplist = couponRateDAO.findAll();

		List<String> fieldsDataList = new ArrayList<String>();

		for (StepUpCouponRate stepUp: stepUplist) {
			StringBuffer sb = new StringBuffer();
			sb.append(commonUtils.nullReplacement(stepUp.getScrIsinCode())).append("|")
					.append(commonUtils.nullReplacement(stepUp.getScrCouponDate())).append("|")
					.append(commonUtils.nullReplacement(stepUp.getScrCouponRate().toString()));
			String fieldsData = sb.toString();
			fieldsDataList.add(fieldsData);
		}
		fileService.createFolder(fieldsDataList, name);
		fileService.doFileEncodeZipEncryptAndUpload(name);
		return "success";
	}

}
