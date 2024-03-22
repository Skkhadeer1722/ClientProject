package com.dataextract.contloller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import com.dataextract.service.AccountService;
import com.dataextract.service.CasAccountService;
import com.dataextract.service.CbmDepositRateService;
import com.dataextract.service.CbmGlAccountService;
import com.dataextract.service.CbmLiabilitiesBaseDetailService;
import com.dataextract.service.CbmLiabilitiesBaseService;
import com.dataextract.service.CostCentreService;
import com.dataextract.service.SssAllotmentService;
import com.dataextract.service.SssTransactionService;
import com.dataextract.service.StepUpCouponRateService;

@Controller
public class DataExtractController {

	@Autowired
	private Environment env;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CasAccountService casAccountService;

	@Autowired
	private CbmGlAccountService cbmGlAccountService;

	@Autowired
	private CbmLiabilitiesBaseDetailService cbmLiabilitiesBaseDetailService;

	@Autowired
	private CbmLiabilitiesBaseService cbmLiabilitiesBaseService;

	@Autowired
	private CbmDepositRateService cbmDepositRateService;

	@Autowired
	private SssTransactionService sssTransactionService;

	@Autowired
	private SssAllotmentService sssAllotmentService;

	@Autowired
	private CostCentreService costCentreService;
	
	@Autowired
	private StepUpCouponRateService couponRateService;

	public String extraction(String name) {
		String result = "Failed to extraction";
		try {
			
			if (name != null) {
				switch (name) {

				case "ACCOUNT_FILE_UPLOAD_CAS":
					result = casAccountService.getAll(env.getProperty(name));
					break;

				case "ACCOUNT_FILE_UPLOAD_RTGS":
					result = accountService.getAll(env.getProperty(name));
					break;

				case "CBM_LIABILITIES_BASE_FILE_UPLOAD":
					result = cbmLiabilitiesBaseService.getAll(env.getProperty(name));
					break;

				case "CBM_LIABILITIES_BASE_DETAIL_FILE_UPLOAD":
					result = cbmLiabilitiesBaseDetailService.getAll(env.getProperty(name));
					break;

				case "CBM_GL_ACCOUNT_FILE_UPLOAD":
					result = cbmGlAccountService.getAll(env.getProperty(name));
					break;

				case "CBM_DEPOSIT_RATE_FILE_UPLOAD":
					result = cbmDepositRateService.getAll(env.getProperty(name));
					break;

				case "SSS_TRANSACTION_FILE_UPLOAD":
					result = sssTransactionService.getAll(env.getProperty(name));
					break;

				case "SSS_ALLOTMENT_FILE_UPLOAD":
					result = sssAllotmentService.getAll(env.getProperty(name));
					break;

				case "CBM_COST_CENTRE_FILE_UPLOAD":
					result = costCentreService.getAll(env.getProperty(name));
					break;
					
				case "STEPUP_COUPON_RATE_FILE_UPLOAD":
					result = couponRateService.getAll(env.getProperty(name));
					break;

				default:
					System.out.println("default case");
					break;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}