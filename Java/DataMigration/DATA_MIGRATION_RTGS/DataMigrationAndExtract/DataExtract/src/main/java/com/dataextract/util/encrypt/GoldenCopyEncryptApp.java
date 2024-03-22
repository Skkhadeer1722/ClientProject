package com.dataextract.util.encrypt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.dataextract.encrypt.util.common.CommonConstant;


/**
 * CommonConstant
 * @version  1.0.0 28-June-2016
 * @author   Win Htut Aung
 * 
 * @ver	    02-Mar-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_17 
 *  	    Sensitive Information Disclosure - Privacy Violation (Heap Inspection) 
 *  
 * @ver		03-Jan-2022 
 * @auth	Khin Su
 * @change	MEPSSUP-2021-1164
 * 			change import log4j 1x to 2x
*/
@Service
public class GoldenCopyEncryptApp {
	final static Logger logger = LogManager.getLogger(GoldenCopyEncryptApp.class);
	@Autowired
	private Environment env;

//	public static void main(String[] args) throws Exception {
//		if (args.length > 0) {
//			doEncryption(args[0]);
//		} else {
//			logger.error(LogUtil.getErrorMessageWithCode(ErrorMessageCode.UTL03002));
//		}
//	}
	
	public  void doEncryption(String sourceFileName) throws Exception {
		String inputBasePath = env.getProperty("remotefolderpath");
		String inputDataFolder ="ExtractionedDataFiles";
		String inputTimeStampFolder ="";
		String outputBasePath =env.getProperty("remotefolderpath");
		String outputDataFolder = "ExtractionedDataFiles";
		String filePattern = "", cryptoAlgo = "";
		char[] passCode = null, salt = null ;
		
		filePattern = env.getProperty("encryptFilePattern");
		cryptoAlgo = env.getProperty("cryptAlgo");
		passCode = CommonConstant.mixIt(env.getProperty("passCode").toCharArray());
		salt = env.getProperty("salt").toCharArray();
		String encFileExtension = env.getProperty("encryptFileExtension");
		
		logger.info("Encryption Started...");
		EncryptFileGenerator encryptFileGenerator = new EncryptFileGenerator(filePattern, inputBasePath, inputTimeStampFolder,
				inputDataFolder, outputBasePath, inputTimeStampFolder, outputDataFolder, cryptoAlgo, passCode, salt);
		
		encryptFileGenerator.setEncFileExtension(encFileExtension);
		encryptFileGenerator.generateEncryptFiles(sourceFileName);
		
		// 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- Start 
		passCode = null;
		salt = null;
		// 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- End
		
		logger.info("Encryption finished...");
			
	}
	
	
}
