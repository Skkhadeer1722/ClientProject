/**
 (#)EncryptFileGenerator.java
 @version 1.0.0 13-May-2016

********************************************************************************

Copyright(c) 2016 BCS Information Systems Pte Ltd
11 Tampines Concourse 
#02-01/05,
Singapore 528729

All Right Reserved.

********************************************************************************
This software is the confidential and proprietary information of 
BCSIS Pte Ltd. ("Confidential Information"). You shall not disclose 
such Confidential Information and shall use it only in accordance with 
the terms of the license agreement you entered into with BCSIS.
********************************************************************************
*/

package com.dataextract.util.encrypt;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dataextract.encrypt.util.common.ErrorMessageCode;
import com.dataextract.encrypt.util.common.FileUtil;
import com.dataextract.encrypt.util.common.LogUtil;
import com.dataextract.encrypt.util.common.MultiResult;
import com.dataextract.encrypt.util.common.Result;



/**
 * Encrypt File Generator for any type of files via given algorithm
 * @version  1.0.0 20-Apr-2016
 * @author   Win Htut Aung
 * 
 * @ver	    15-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_17 
 *  	    Sensitive Information Disclosure - Privacy Violation (Heap Inspection) 
 *  
 * @ver	    23-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_14 
 *  	    Insufficient Input Validation - Path Manipulation 
 *  
 * @ver		03-Jan-2022 
 * @auth	Khin Su
 * @change	MEPSSUP-2021-1164
 * 			change import log4j 1x to 2x
 */


public class EncryptFileGenerator{
	final static Logger logger = LogManager.getLogger(EncryptFileGenerator.class);

	private String filePattern;
	private String inputPath;
	private String inputFolder;
	private String inputDataFolder;
	private String outputPath;
	private String outputFolder;
	private String outputDataFolder;
	private String cryptoAlgo;
	// 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	//private String passCode;
	//private String salt;
	private char[] passCode;
	private char[] salt;
	// 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- End
	private String encFileExtension;

	public String getFilePattern() {
		return filePattern;
	}

	public void setFilePattern(String filePattern) {
		this.filePattern = filePattern;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getCryptoAlgo() {
		return cryptoAlgo;
	}

	public void setCryptoAlgo(String cryptoAlgo) {
		this.cryptoAlgo = cryptoAlgo;
	}

	public String getInputFolder() {
		return inputFolder;
	}

	public void setInputFolder(String inputFolder) {
		this.inputFolder = inputFolder;
	}

	public String getOutputFolder() {
		return outputFolder;
	}

	public void setInputDataFolder(String inputDataFolder) {
		this.inputDataFolder = inputDataFolder;
	}

	public String getInputDataFolder() {
		return inputDataFolder;
	}

	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}
	
	public String getOutputDataFolder() {
		return outputDataFolder;
	}

	public void setOutputDataFolder(String outputDataFolder) {
		this.outputDataFolder = outputDataFolder;
	}

	// 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	/*public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}	*/
	public char[] getPassCode() {
		return passCode;
	}

	public void setPassCode(char[] passCode) {
		this.passCode = passCode;
	}	
	

	public char[] getSalt() {
		return salt;
	}

	public void setSalt(char[] salt) {
		this.salt = salt;
	}
	// 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	public String getEncFileExtension() {
		return encFileExtension;
	}

	public void setEncFileExtension(String encFileExtension) {
		this.encFileExtension = encFileExtension;
	}

	private void clearProperties() {
		 filePattern = "";
		 inputPath = "";
		 inputFolder = "";
		 inputDataFolder = "";
		 outputPath = "";
		 outputFolder = "";
		 outputDataFolder = "";
		 cryptoAlgo = "";
		 passCode = null;
		 salt = null;
		 encFileExtension = ".enc";
	}

	/**
	* Generate the hashfilegenerator according to given parameter via constructor
	 * @param filePattern glob expression for type of files, for eg., *.{csv, data} for hashing both .csv and .dat files
	 * @param inputPath path for hash files
	 * @param inputFolder folder for hash files
	 * @param inputDataFolder data folder for hash files
	 * @param outputPath for hash files
	 * @param outputFolder for hash files
	 * @param Crypto Algorithm
	 * @param passCode
	 * @param salt
	 * @throws ClassNotFoundException when the input algorithm code is wrong or not implemented
	 */
	// 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	/*public EncryptFileGenerator(String filePattern, String inputPath, String inputFolder, String inputDataFolder,
			String outputPath, String outputFolder, String outputDataFolder, String cryptoAlgo, String passCode, String salt) {*/
	public EncryptFileGenerator(String filePattern, String inputPath, String inputFolder, String inputDataFolder,
			String outputPath, String outputFolder, String outputDataFolder, String cryptoAlgo, char[] passCode, char[] salt) {	
    // 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- End 	
		clearProperties();
		this.filePattern = filePattern;
		this.inputPath = inputPath;
		this.inputFolder = inputFolder;
		this.inputDataFolder = inputDataFolder;
		this.outputPath = outputPath;
		this.outputFolder = outputFolder;
		this.outputDataFolder = outputDataFolder;
		this.cryptoAlgo = cryptoAlgo;
		this.passCode = passCode;
		this.salt = salt;
	}

	/**
	 * Generate the encrypt file according to given parameter via constructor
	 * @param filePattern glob expression for type of files, for eg., *.{zip} 
	 * @param inputPath path for hash files
	 * @param outputPath for hash files
	 * @param Hashing Algorithm
	 * @param passCode
	 * @param salt
	 * @throws ClassNotFoundException when the input algorithm code is wrong or not implemented
	 */
	// 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	//public EncryptFileGenerator(String filePattern, String inputPath, String outputPath, String cryptoAlgo, String passCode, String salt) {
	public EncryptFileGenerator(String filePattern, String inputPath, String outputPath, String cryptoAlgo, char[] passCode, char[] salt) {
	// 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- End 
		clearProperties();
		this.filePattern = filePattern;
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		this.cryptoAlgo = cryptoAlgo;
		this.passCode = passCode;
		this.salt = salt;
	}
	
	// 23-Feb-2017 - Suma - MEPSUAT-2017-0035_14- Start
    private static boolean isFileNameValid(String fileName)
    {
        boolean result = true;
        if ( (fileName != null) && ( fileName.length() !=0 ) )
        {
            if( fileName.indexOf("..") != -1)
            {
                result =  false;
            }
        }
        return result;
    }
    // 23-Feb-2017 - Suma - MEPSUAT-2017-0035_14- End

	
	/**
	 * Generate the enc file according to given parameter via constructor
	 * @return MultiResult object for hash file creation success or not and error messages
	 * @throws ClassNotFoundException when the input algorithm code is wrong or not implemented
	 */
	public MultiResult generateEncryptFiles(String sourceFileName) throws Exception {
		MultiResult multiResult = new MultiResult();
		Result result = new Result();
		
		String encFileName = "";
		long startTime = 0, endTime = 0;
		// 23-Feb-2017 - Suma - MEPSUAT-2017-0035_14- Start
		 if(isFileNameValid(inputPath) && isFileNameValid(outputPath)){
       	 
		Path dir = Paths.get(inputPath, inputFolder);
		Path outputLocation = Paths.get(this.outputPath, outputFolder);
		
		Path dataPath = null;
		int fileCount = 0;

		IntfCrypto encrypter = CryptoFactory.getEncrypter(cryptoAlgo, passCode, salt);
		
		if (Files.isDirectory(dir)) {
			dataPath = Paths.get(inputPath, inputFolder, inputDataFolder);

			try (DirectoryStream<Path> streams = Files.newDirectoryStream(dataPath, filePattern)) {
				for (Path path : streams) {
					fileCount ++;
					
					encFileName = "";
					outputLocation = null;
					startTime = 0;
					endTime = 0;
					
					result = new Result();
					try {
						// logger [start-time][end-time][duration][filename][size][Status]
						startTime = System.currentTimeMillis();
						
						encFileName = path.getFileName().normalize().toString();
						if(sourceFileName.equalsIgnoreCase(encFileName.substring(0, encFileName.indexOf(".")))) {
						encFileName = encFileName.substring(0, encFileName.indexOf(".")) + encFileExtension;
						// 23-Feb-2017 - Suma - MEPSUAT-2017-0035_14- Start
						 if(!isFileNameValid(encFileName)){
				       	 	logger.error("Invalid File : " + encFileName);
				       	   throw new Exception("Invalid File : " + encFileName);
				       }else{
						outputLocation = Paths.get(this.outputPath, outputFolder, outputDataFolder, encFileName);
						
						Path outputLocationParent = outputLocation.getParent();
						
						if (outputLocationParent != null && !Files.isDirectory(outputLocationParent)) {
							FileUtil.createDirectory(outputLocationParent);
						}
						
						result = encrypter.encrypt(path.normalize().toString(), outputLocation.normalize().toString());
						result.setFileName(path.toFile().getName());
						result.setMessage(LogUtil.prepareLog(startTime, endTime, path, "SUCCESS", outputLocation.toFile().getName()));
						
						multiResult.getSuccessResults().add(result);
						logger.info(result.getMessage());
				       }	// 23-Feb-2017 - Suma - MEPSUAT-2017-0035_14- End
						} else
						{
							System.out.println("source file not found");
						}
					} catch (Exception e) {
						result.setSuccess(false);
						result.setMessage(LogUtil.getErrorMessageWithCode(ErrorMessageCode.UTL03003) + " " + encFileName);
						logger.error(result.getMessage());
						multiResult.getOtherResults().add(result);
						throw e;
					}
				}
			} catch (Exception e) {
				result = new Result();
				result.setSuccess(false);
				result.setMessage(e.getMessage());
				multiResult.getOtherResults().add(result);
								
				throw e;
			}
			
			if (fileCount == 0) {
				logger.error(LogUtil.getErrorMessageWithCode(ErrorMessageCode.UTL03002) + " " + inputFolder);
				
				throw new Exception(LogUtil.getErrorMessage(ErrorMessageCode.UTL03002) + " " + inputFolder);
			}
		} else {
			result = new Result();
			result.setSuccess(false);
			result.setMessage(LogUtil.getErrorMessageWithCode(ErrorMessageCode.UTL03001) + " " + inputFolder);
			multiResult.getOtherResults().add(result);
			throw new Exception(LogUtil.getErrorMessage(ErrorMessageCode.UTL03001) + " " + inputFolder);
		}
       }else{
       	logger.error("Invalid File  ");
        throw new Exception("Invalid File  ");
    }	// 23-Feb-2017 - Suma - MEPSUAT-2017-0035_14- End
		return multiResult;
	}
	
	
}
