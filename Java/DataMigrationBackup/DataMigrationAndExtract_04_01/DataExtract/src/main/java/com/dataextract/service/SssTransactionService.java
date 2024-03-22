package com.dataextract.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataextract.common.CommonUtils;
import com.dataextract.sgs.model.SssTransaction;
import com.dataextract.sgs.model.SssTransactionMapping;
import com.dataextract.sgs.repository.SssTransactionDAO;

@Service
public class SssTransactionService {
	@Autowired
	private SssTransactionDAO sssTransactionDAO;

	@Autowired
	private WriteFileService fileService;

	@Autowired
	private CommonUtils commonUtils;

	public String getAll(String name) {
		try {
			List<SssTransaction> transactionList = sssTransactionDAO.findAll();
			List<String> fieldsDataList = new ArrayList<String>();
			for (SssTransaction transaction : transactionList) {
				StringBuffer sb = new StringBuffer();
				SssTransactionMapping transactionMapping = transaction.getTransactionMapping();
				sb.append(commonUtils.nullReplacement(transaction.getSstSsssRefNo())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstIsin())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstTxnType())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstSettDate())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstTradDate())).append("|")
						.append(commonUtils.nullReplacement(transactionMapping.getSsdCcyCode())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstSettAmt().toString())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstNomAmt().toString())).append("|")
						.append(commonUtils.nullReplacement(transactionMapping.getSsdDealPrice())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstDelvAgent())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstRecvAgent())).append("|")
						.append(commonUtils.nullReplacement(transactionMapping.getSsdRelvSsssRefNo())).append("|")
						.append(commonUtils.nullReplacement(transactionMapping.getSsdDelvRecvDtStamp().toString()))
						.append("|")
						.append(commonUtils.nullReplacement(transactionMapping.getSsdUpdDtStamp().toString()))
						.append("|").append(commonUtils.nullReplacement(transactionMapping.getSsdRecvMsgSnder()))
						.append("|").append(commonUtils.nullReplacement(transactionMapping.getSsdRecvMsgType()))
						.append("|").append(commonUtils.nullReplacement(transaction.getSstRecvAgent())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstDelvAgent())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstDelvCustody())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstRecvCustody())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstRecvAgent())).append("|")
						.append(commonUtils.nullReplacement(transaction.getSstDelvAgent())).append("|")

						.append(commonUtils.nullReplacement(transactionMapping.getSsdAccruedint().toString()));
				String fieldsData = sb.toString();
				fieldsDataList.add(fieldsData);
			}
			fileService.createFolder(fieldsDataList, name);
			fileService.doFileEncodeZipEncryptAndUpload(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
