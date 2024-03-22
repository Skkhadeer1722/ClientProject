package com.dataextract.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataextract.cas.model.CbmDepositRate;
import com.dataextract.cas.repository.CbmDepositRateDAO;
import com.dataextract.common.CommonUtils;
@Service
public class CbmDepositRateService {
	@Autowired
	private CbmDepositRateDAO cbmDepositRateDAO;

	@Autowired
	private WriteFileService fileService;
	
	@Autowired
	private CommonUtils commonUtils;
	
	public String getAll(String name) {
		try {
			List<CbmDepositRate> cbmDepositRateList = cbmDepositRateDAO.findAll();
			List<String> list = new ArrayList<String>();
			for (CbmDepositRate depositRate : cbmDepositRateList) {

				StringBuffer sb = new StringBuffer();
				sb.append(commonUtils.nullReplacement(depositRate.getSdrPeriod())).append("|")
						.append(commonUtils.nullReplacement(depositRate.getSdrDepoRate().toString()));
				String s = sb.toString();
				list.add(s);
			}
			fileService.createFolder(list, name);
			fileService.doFileEncodeZipEncryptAndUpload(name);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
