package com.dataextract.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataextract.cas.model.CbmCostCentre;
import com.dataextract.cas.repository.CostCentreDAO;
import com.dataextract.common.CommonUtils;

@Service
public class CostCentreService {
	@Autowired
	private WriteFileService fileService;
	@Autowired
	private CostCentreDAO centreDAO;
	@Autowired
	private CommonUtils commonUtils;

	public String getAll(String name) {
		try {
			List<CbmCostCentre> costCentreList = centreDAO.findAll();
			List<String> s = new ArrayList<String>();
			for (CbmCostCentre cc : costCentreList) {
				StringBuffer sb = new StringBuffer();
				sb.append(commonUtils.nullReplacement(cc.getGlcCcCode()));
				String sa = sb.toString();
				s.add(sa);
			}
			fileService.createFolder(s, name);
			fileService.doFileEncodeZipEncryptAndUpload(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

}
