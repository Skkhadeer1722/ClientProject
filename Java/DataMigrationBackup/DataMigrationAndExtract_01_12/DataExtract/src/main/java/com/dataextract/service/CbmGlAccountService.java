package com.dataextract.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataextract.cas.model.CbmGlAccount;
import com.dataextract.cas.repository.CbmGlAccountDAO;
import com.dataextract.common.CommonUtils;

@Service
public class CbmGlAccountService {
	@Autowired
	private CbmGlAccountDAO cbmGlAccountDAO;

	@Autowired
	private WriteFileService fileService;
	@Autowired
	private CommonUtils commonUtils;

	public String getAll(String name) {

		List<CbmGlAccount> cbmGlAccountList = cbmGlAccountDAO.findAll();
		List<String> fieldsDataList = new ArrayList<String>();

		for (CbmGlAccount cbmGlAccount : cbmGlAccountList) {
			StringBuffer sb = new StringBuffer();
			sb.append(commonUtils.nullReplacement(cbmGlAccount.getGlAccNo())).append("|")
					.append(commonUtils.nullReplacement(cbmGlAccount.getGlDesc()));
			String fieldsData = sb.toString();
			fieldsDataList.add(fieldsData);
		}

		fileService.createFolder(fieldsDataList, name);
		fileService.doFileEncodeZipEncryptAndUpload(name);

//		fileService.createFolder(fieldsDataList, name);
//		
//		File f=new File(".//ExtractionedDataFiles//"+name+".txt");
//		if(f.exists()) {
//		fileService.encodeFileToBase64(name,f);
//		}
		return "success";
	}
}
