package com.dataextract.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataextract.common.CommonUtils;
import com.dataextract.sgs.model.SssAllotment;
import com.dataextract.sgs.model.SssAllotmentMapping;
import com.dataextract.sgs.model.SssAllotmentModel;
import com.dataextract.sgs.repository.SssAllotmentDAO;
import com.dataextract.sgs.repository.SssAllotmentMappingDAO;

@Service
public class SssAllotmentService {

	@Autowired
	private SssAllotmentDAO allotmentDAO;

	@Autowired
	private WriteFileService fileService;

	@Autowired
	private CommonUtils commonUtils;

	@Autowired
	private SssAllotmentMappingDAO allotmentMappingDAO;

	public String getAll(String name) {
		List<SssAllotment> allotment = allotmentDAO.findAll();
		List<SssAllotmentMapping> allotmentMapping = allotmentMappingDAO.findAll();
		
		List<SssAllotmentModel> allotmentModel = new ArrayList<SssAllotmentModel>();
		for (SssAllotmentMapping allotmentMappingList : allotmentMapping) 
		{
			for (SssAllotment allotmentList : allotment) 
			{
				if (allotmentMappingList.getAldAlmtDate().equals(allotmentList.getAlmAlmtDate()) && 
						allotmentMappingList.getAldIsin().equals(allotmentList.getAlmIsin())
						&& allotmentMappingList.getAldIsinType().equals(allotmentList.getAlmIsinType())) 
				{
					SssAllotmentModel modelObject = new SssAllotmentModel();
					modelObject.setAlmIsin(allotmentList.getAlmIsin());
					modelObject.setAlmAcutionDate(allotmentList.getAlmAcutionDate());
					modelObject.setAlmUpdDtStamp(allotmentList.getAlmUpdDtStamp());
					modelObject.setAldAlmtPrice(allotmentMappingList.getAldAlmtPrice());
					modelObject.setAldNomAmt(allotmentMappingList.getAldNomAmt());
					modelObject.setAlmFirstAlmt(allotmentList.getAlmFirstAlmt());
					modelObject.setAldSettAmt(allotmentMappingList.getAldSettAmt());
//					modelObject.setAlmStat(allotmentList.getAlmStat());
					allotmentModel.add(modelObject);
				}
			}
		}
		List<String> fieldsDataList = new ArrayList<String>();
		for (SssAllotmentModel allotmentModelList : allotmentModel) 
		{
			StringBuffer sb = new StringBuffer();
			sb.append(commonUtils.nullReplacement(allotmentModelList.getAlmIsin())).append("|")
			.append(commonUtils.nullReplacement(allotmentModelList.getAlmAcutionDate())).append("|")
			.append(commonUtils.nullReplacement(allotmentModelList.getAlmUpdDtStamp())).append("|")
					.append(commonUtils.nullReplacement(allotmentModelList.getAldAlmtPrice().toString())).append("|")
					.append(commonUtils.nullReplacement(allotmentModelList.getAldNomAmt().toString())).append("|")
					.append(commonUtils.nullReplacement(allotmentModelList.getAlmFirstAlmt())).append("|")
					.append(commonUtils.nullReplacement(allotmentModelList.getAldSettAmt().toString()));
//					.append(commonUtils.nullReplacement(allotmentModelList.getAlmStat()));
			String fieldsData = sb.toString();
			fieldsDataList.add(fieldsData);
		}


		fileService.createFolder(fieldsDataList, name);
		fileService.doFileEncodeZipEncryptAndUpload(name);

		return "success";
	}
}
