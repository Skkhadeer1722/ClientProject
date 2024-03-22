package com.dataextract.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataextract.common.CommonUtils;
import com.dataextract.rtgs.model.RtgsAccount;
import com.dataextract.rtgs.repository.AccountDAO;

@Service
public class AccountService {

	@Autowired(required = false)
	private AccountDAO accountDAO;
	@Autowired
	private WriteFileService fileService;
	@Autowired
	private CommonUtils commonUtils;

	public String getAll(String name) {

		List<RtgsAccount> accountlist = accountDAO.findAll();

		List<String> fieldsDataList = new ArrayList<String>();

		for (RtgsAccount account : accountlist) {

			StringBuffer sb = new StringBuffer();
			sb.append(commonUtils.nullReplacement(account.getAdMbrCode())).append("|")
					.append(commonUtils.nullReplacement(account.getAdAccNo())).append("|")
					.append(commonUtils.nullReplacement(account.getAdType())).append("|")
					.append(commonUtils.nullReplacement(account.getAdCcyCode())).append("|")
					.append(commonUtils.nullReplacement(account.getAdStat()));
			String fieldsData = sb.toString();
			fieldsDataList.add(fieldsData);
		}

		fileService.createFolder(fieldsDataList, name);
		fileService.doFileEncodeZipEncryptAndUpload(name);
		return "success";
	}

}
