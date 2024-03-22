package com.dataextract.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataextract.cas.model.CbmLiabilitiesBase;
import com.dataextract.cas.repository.CbmLiabilitiesBaseDAO;
import com.dataextract.common.CommonUtils;

@Service
public class CbmLiabilitiesBaseService {
	@Autowired
	private CbmLiabilitiesBaseDAO cbmLiabilitiesBaseDAO;

	@Autowired
	private WriteFileService fileService;

	@Autowired
	private CommonUtils commonUtils;

	public String getAll(String name) {

		List<CbmLiabilitiesBase> cbmLiabilitiesBaseList = cbmLiabilitiesBaseDAO.findAll();
		List<String> fieldsDataList = new ArrayList<String>();

		for (CbmLiabilitiesBase cbmLiabilitiesBase : cbmLiabilitiesBaseList) {

			StringBuffer sb = new StringBuffer();
			sb.append(commonUtils.nullReplacement(cbmLiabilitiesBase.getLbMbrCode())).append("|")
					.append(commonUtils.nullReplacement(cbmLiabilitiesBase.getLbCcyCode())).append("|")
					.append(commonUtils.nullReplacement(cbmLiabilitiesBase.getLbType())).append("|")
					.append(commonUtils.nullReplacement(cbmLiabilitiesBase.getLbUpdDtStamp()));
			String fieldsData = sb.toString();
			fieldsDataList.add(fieldsData);
		}

		fileService.createFolder(fieldsDataList, name);
		fileService.doFileEncodeZipEncryptAndUpload(name);

		return "success";
	}
}
