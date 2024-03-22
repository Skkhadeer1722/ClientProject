package com.dataextract.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataextract.cas.model.CbmLiabilitiesBaseDetail;
import com.dataextract.cas.repository.CbmLiabilitiesBaseDetailDAO;
import com.dataextract.common.CommonUtils;

@Service
public class CbmLiabilitiesBaseDetailService {
	
	@Autowired
	private CbmLiabilitiesBaseDetailDAO cbmLiabilitiesBaseDetailDAO;

	@Autowired
	private WriteFileService fileService;
	
	@Autowired
	private CommonUtils commonUtils;

	public String getAll(String name) {
		List<CbmLiabilitiesBaseDetail> cbmLiabilitiesBaseDetailList = cbmLiabilitiesBaseDetailDAO.findAll();
		List<String> fieldsDataList = new ArrayList<String>();

		for (CbmLiabilitiesBaseDetail cbmLiabilitiesBaseDetail : cbmLiabilitiesBaseDetailList) {

			StringBuffer sb = new StringBuffer();
			sb.append(commonUtils.nullReplacement(cbmLiabilitiesBaseDetail.getLbMbrCode())).append("|")
					.append(commonUtils.nullReplacement(cbmLiabilitiesBaseDetail.getLbStartDate())).append("|")
					.append(commonUtils.nullReplacement(cbmLiabilitiesBaseDetail.getLbEndDate())).append("|")
					.append(commonUtils.nullReplacement(cbmLiabilitiesBaseDetail.getLbValue().toString()));
			String fieldsData = sb.toString();
			fieldsDataList.add(fieldsData);
		}

		fileService.createFolder(fieldsDataList, name);
		fileService.doFileEncodeZipEncryptAndUpload(name);
		return "success";
	}
}
