/**
 (#)DecryptFileGenerator.java
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

package scrips.datamigration.util.decrypt;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import scrips.datamigration.decrypt.util.common.ErrorMessageCode;
import scrips.datamigration.decrypt.util.common.FileUtil;
import scrips.datamigration.decrypt.util.common.LogUtil;
import scrips.datamigration.decrypt.util.common.MultiResult;
import scrips.datamigration.decrypt.util.common.Result;
import scrips.datamigration.decrypt.util.common.StringUtil;





/**
 * Decrypt File Generator for any type of files
 * @version  1.0.0 20-Apr-2016
 * @author   Win Htut Aung
 * 
 * @ver	    15-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_17 
 *  	    Sensitive Information Disclosure - Privacy Violation (Heap Inspection) 
 *  
 * @ver	    22-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_14 
 *  	    Insufficient Input Validation - Path Manipulation 
 *  
 * @ver		03-Jan-2022 
 * @auth	Khin Su
 * @change	MEPSSUP-2021-1164
 * 			change import log4j 1x to 2x
 */


public class DecryptFileGenerator{
	final static Logger logger = LogManager.getLogger(DecryptFileGenerator.class);
	private String filePattern;
	private String inputPath;
	private String inputFolder;
	private String inputDataFolder;
	private String outputPath;
	private String outputFolder;
	private String outputDataFolder;
	private String cryptoAlgo;
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	//private String passCode;
	//private String salt;
	private char[] passCode;
	private char[] salt;
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 

	private int fileTotalCount = 0;
	private int fileDecrytedCount = 0;

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

	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	/*public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}*/
	public char[] getPassCode() {
		return passCode;
	}

	public void setPassCode(char[] passCode) {
		this.passCode = passCode;
	}
	/*public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}*/
	public char[] getSalt() {
		return salt;
	}

	public void setSalt(char[] salt) {
		this.salt = salt;
	}
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
	public int getFileTotalCount() {
		return fileTotalCount;
	}

	public int getFileDecrytedCount() {
		return fileDecrytedCount;
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
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	//public DecryptFileGenerator(String filePattern, String inputPath, String inputFolder, String inputDataFolder,
	//		String outputPath, String outputFolder, String outputDataFolder, String cryptoAlgo, String passCode, String salt) {
		public DecryptFileGenerator(String filePattern, String inputPath, String inputFolder, String inputDataFolder,
				String outputPath, String outputFolder, String outputDataFolder, String cryptoAlgo, char[] passCode, char[] salt) {
   // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
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
	 * @param Crypto Algorithm
	 * @param passCode
	 * @param salt
	 * @throws ClassNotFoundException when the input algorithm code is wrong or not implemented
	 */
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	//public DecryptFileGenerator(String filePattern, String inputPath, String outputPath, String cryptoAlgo, String passCode, char[] salt) {
	public DecryptFileGenerator(String filePattern, String inputPath, String outputPath, String cryptoAlgo, char[] passCode, char[] salt) {
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 	
		clearProperties();
		this.filePattern = filePattern;
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		this.cryptoAlgo = cryptoAlgo;
		this.passCode = passCode;
		this.salt = salt;
	}

	// 22-Feb-2017 - Suma - MEPSUAT-2017-0035_14- Start
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
    // 22-Feb-2017 - Suma - MEPSUAT-2017-0035_14- End
	/**
	 * Generate the enc file according to given parameter via constructor
	 * @return MultiResult object for hash file creation success or not and error messages
	 * @throws ClassNotFoundException when the input algorithm code is wrong or not implemented
	 */
	public MultiResult generateDecryptFiles(String fileName) throws Exception {
		MultiResult multiResult = new MultiResult();
		Result result = new Result();
		
		String encFileName = "";
		long startTime = 0, endTime = 0;
		// 22-Feb-2017 - Suma - MEPSUAT-2017-0035_14- Start
		 if(isFileNameValid(inputPath) && isFileNameValid(outputPath)){
         	
		Path dir = Paths.get(inputPath, inputFolder);
		Path outputLocation = Paths.get(this.outputPath, outputFolder);
         
		Path dataPath = null;

		IntfCrypto decrypter = CryptoFactory.getEncrypter(cryptoAlgo, passCode, salt);

		if (Files.isDirectory(dir)) {
			dataPath = Paths.get(inputPath, inputFolder, inputDataFolder);

			try (DirectoryStream<Path> streams = Files.newDirectoryStream(dataPath, filePattern)) {
				for (Path path : streams) {
					fileTotalCount ++;
					encFileName = "";
					outputLocation = null;
					startTime = 0;
					endTime = 0;
					
					result = new Result();
					try {
						// logger [start-time][end-time][duration][filename][size][Status]
						startTime = System.currentTimeMillis();
						
						encFileName = path.getFileName().normalize().toString();
						if(encFileName.substring(0, encFileName.indexOf(".")).equals(fileName))
						{
						encFileName = encFileName.substring(0, encFileName.indexOf(".")) + ".zip";
						// 22-Feb-2017 - Suma - MEPSUAT-2017-0035_14- Start
						 if(!isFileNameValid(encFileName)){
				         	 	logger.error("Invalid File"+encFileName);
				         	 	throw new Exception("Invalid File : " + encFileName);
				         }else{
						outputLocation = Paths.get(this.outputPath, outputFolder, outputDataFolder, encFileName);
						
						Path outputLocationParent = outputLocation.getParent();
						
						if (outputLocationParent != null && !Files.isDirectory(outputLocationParent)) {
							FileUtil.createDirectory(outputLocationParent);
						}
						 if(!isFileNameValid(outputLocation.toFile().getName())){
				         	 	logger.error("Invalid File"+outputLocation.toFile().getName());
				         	 	throw new Exception("Invalid File : " + outputLocation.toFile().getName());
				         }else{
						result = decrypter.decrypt(path.normalize().toString(), outputLocation.normalize().toString());
						result.setFileName(path.toFile().getName());
						result.setMessage(LogUtil.prepareLog(startTime, endTime, path, "SUCCESS", outputLocation.toFile().getName()));
						
						multiResult.getSuccessResults().add(result);
						logger.debug(result.getMessage());
						
						fileDecrytedCount ++;
				         }
				         }// 22-Feb-2017 - Suma - MEPSUAT-2017-0035_14- End
						}} catch (Exception e) {
						  e.printStackTrace();
						result.setSuccess(false);
						result.setMessage(LogUtil.getErrorMessage(ErrorMessageCode.UTL04002) + " " + path.toFile().getName() + " " + e.getMessage());
						multiResult.getOtherResults().add(result);
						
						logger.error(result.getMessage());
					}
					
				}
				
				
			} catch (Exception e) {
				result = new Result();
				result.setSuccess(false);
				result.setMessage(e.getMessage());
				multiResult.getOtherResults().add(result);

				throw e;
			}
			
		} else {
			result = new Result();
			result.setSuccess(false);
			result.setMessage(LogUtil.getErrorMessage(ErrorMessageCode.UTL04001) + " " + inputFolder);
			multiResult.getOtherResults().add(result);

			throw new Exception(result.getMessage());
		}
		
        }else{
        	logger.error("Invalid File  ");
            throw new Exception("Invalid File  ");
        }	// 23-Feb-2017 - Suma - MEPSUAT-2017-0035_14- End
		return multiResult;
         
	}
	
	public void showDecryptedSummary() {
		logger.info("===================================================");
		logger.info("Decryption Status");
		logger.info("===================================================");
		logger.info(StringUtil.rightPad(" ", "Folder to be decrypted", 34) + " : " + inputFolder);
		logger.info(StringUtil.rightPad(" ", "Total files to be decrypted", 34) + " : " + StringUtil.leftPad(" ", "" + fileTotalCount, 4));
		logger.info(StringUtil.rightPad(" ", "Total decrypted files", 34) + " : " + StringUtil.leftPad(" ", "" + fileDecrytedCount, 4));
		logger.info("---------------------------------------------------");
		logger.info(StringUtil.rightPad(" ", "Overall Decryption Status", 34) + StringUtil.leftPad(" ", "[" + getDecryptionStatus() + "]", 17));
		logger.info("===================================================");
	}
	
	public String getDecryptionStatus() {
		String status = "";
		if (fileTotalCount != 0 && (fileTotalCount == fileDecrytedCount)) {
			status = "PASSED";
		} else if (fileTotalCount != 0 && fileDecrytedCount !=0 && fileDecrytedCount < fileTotalCount) {
			status = "PARTIALLY-PASSED";
		} else {
			status = "FAILED";
		}
		
		return status;
	}
	
}
