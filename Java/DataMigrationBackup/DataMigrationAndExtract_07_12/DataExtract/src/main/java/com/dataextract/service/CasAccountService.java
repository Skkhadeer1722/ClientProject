package com.dataextract.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataextract.cas.model.CasAccount;
import com.dataextract.cas.repository.CasAccountDAO;
import com.dataextract.common.CommonUtils;

@Service
public class CasAccountService {
	@Autowired
	private CasAccountDAO accountDAO;

	@Autowired
	private WriteFileService fileService;
	@Autowired
	private CommonUtils commonUtils;

	public String getAll(String name) {
		List<CasAccount> accountlist = accountDAO.findAll();

		List<String> fieldsDataList = new ArrayList<String>();

		for (CasAccount account : accountlist) {

			StringBuffer sb = new StringBuffer();
			sb.append(commonUtils.nullReplacement(account.getAdMbrCode())).append("|")
					.append(commonUtils.nullReplacement(account.getAdAccNo())).append("|")
					.append(commonUtils.nullReplacement(account.getAdDesc())).append("|")
					.append(commonUtils.nullReplacement(account.getAdAccType())).append("|")
//					.append(commonUtils.nullReplacement(account.getAdCollateralInd()))
					.append("Y").append("|")
//					.append(commonUtils.nullReplacement(account.getAdDefaultAcc()))
					.append("Y").append("|").append(commonUtils.nullReplacement(account.getAdStatSentMed())).append("|")
					.append(commonUtils.nullReplacement(account.getAdStat())).append("|")
					.append(commonUtils.nullReplacement(account.getAdCcyCode())).append("|")
					.append(commonUtils.nullReplacement(account.getAbEodStatSent())).append("|")
					.append(commonUtils.nullReplacement(account.getAdGlCat())).append("|")
					.append(commonUtils.nullReplacement(account.getAdGlAccCc())).append("|")
					.append(commonUtils.nullReplacement(account.getAdGlAccNo())).append("|")
					.append(commonUtils.nullReplacement(account.getAdStatSentMed()));
			String fieldsData = sb.toString();
			fieldsDataList.add(fieldsData);
		}
		fileService.createFolder(fieldsDataList, name);
		fileService.doFileEncodeZipEncryptAndUpload(name);

		return "success";
	}

}
