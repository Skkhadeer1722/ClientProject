/**
 @(#)LogUtil.java
 @version 0.0.1 27-Apr-2016

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
package scrips.datamigration.decrypt.util.common;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LogUtil
 * @version  1.0.0 27-Apr-2016
 * @author   Win Htut Aung
 * 
 * @ver		03-Jan-2022 
 * @auth	Khin Su
 * @change	MEPSSUP-2021-1164
 * 			change import log4j 1x to 2x
 */

public class LogUtil {
	private static Properties errorMessageProperties = null;
	private static final String errorMessageFileName = "errormsg.properties";
	
	private static Properties logConfigProperties = null;
	private static final String logConfigFileName = "logconfig.properties";
	final static Logger logger = LogManager.getLogger(LogUtil.class);
	
	private static final String ISLOGENABLE = "isLogEnable";
	private static final String ISLOGFILESIZE = "isLogFileSize";
	
	
	/**
	 * load error message properties from the properties file
	 * @throws      IOException when properties file are not found
	 */
	private static void loadErrorMessageProperties() throws IOException {
		if (errorMessageProperties == null) {
			errorMessageProperties = PropertiesLoader.getInstance().loadProperties(errorMessageFileName);
		}
	}
	
	/**
	 * load error message properties from the properties file
	 * @throws      IOException when properties file are not found
	 */
	private static void loadLogConfigProperties() throws IOException {
		if (logConfigProperties == null) {
			logConfigProperties = PropertiesLoader.getInstance().loadProperties(logConfigFileName);
		}
	}
	
	/**
	 * Get Properties Value from config.properties file using given parameter
	 * @param      	error code
	 * @return     	error message from properties file
	 */
	public static String getErrorMessage(String errorCode) {
		String errMsg = "";
		
		try {
			loadErrorMessageProperties();
			
			if (errorMessageProperties.containsKey(errorCode)) {
				errMsg = errorMessageProperties.getProperty(errorCode);
			} else {
				throw new Exception("Error Code " + errorCode + " not found.");
			}
			
		} catch (Exception e) {
			logger.error(e);
		}
		return errMsg;
	} 
	
	/**
	 * Get Properties Value from config.properties file using given parameter
	 * @param      	error code
	 * @return     	error code + error message from properties file
	 */
	public static String getErrorMessageWithCode(String errorCode) {
		StringBuffer errMsgWithCode = new StringBuffer();
		String errorMessage = getErrorMessage(errorCode);
		errMsgWithCode.append("[").append(errorCode).append("]").append(" ").append(errorMessage).append(" -");
		return errMsgWithCode.toString();
	}
	
	/**
	 * Prepare Log Format
	 * @param      	start time
	 * @param 		end time
	 * @param		file Path for knowing file size
	 * @param		status
	 * @param		destination path
	 * @return		Log String
	 * @throws		IOException
	 */
	public static String prepareLog(long startTime, long endTime, Path filePath, String status, String destination) throws IOException {
		StringBuffer sb = new StringBuffer();
		loadLogConfigProperties();
		
		
		sb.append(prepareLog(startTime, endTime, filePath.toFile(), status, destination));
		
		return sb.toString();
		
	}
	
	/**
	 * Prepare Log Format
	 * @param      	start time
	 * @param 		end time
	 * @param		File for knowing file size
	 * @param		status
	 * @param		destination path
	 * @return		Log String
	 * @throws		IOException
	 */
	public static String prepareLog(long startTime, long endTime, File file, String status, String destination) throws IOException {
		StringBuffer sb = new StringBuffer();
		loadLogConfigProperties();
		
		String isLogEnable = logConfigProperties.getProperty(ISLOGENABLE, "true");
		String isLogFileSize = logConfigProperties.getProperty(ISLOGFILESIZE, "false");
		
		if ("true".equals(isLogEnable) && "true".equals(isLogFileSize)){
			sb.append(prepareLog(startTime, endTime, file.getName(), FileUtil.getStringSizeLengthFile(file.length()), status, destination));
		} else if("true".equals(isLogEnable) && !"true".equals(isLogFileSize)){
			sb.append(prepareLog(startTime, endTime, file.getName(), status, destination));
		}
		
		return sb.toString();
	}
	
	/**
	 * Prepare Log Format
	 * @param      	start time
	 * @param 		end time
	 * @param		File Channel for knowing file size
	 * @param		status
	 * @param		destination path
	 * @return		Log String
	 * @throws		IOException
	 */
	public static String prepareLog(long startTime, long endTime, String message, FileChannel fileChannel, String status, String destination) throws IOException {
		StringBuffer sb = new StringBuffer();
		
		loadLogConfigProperties();
		
		String isLogEnable = logConfigProperties.getProperty(ISLOGENABLE, "true");
		String isLogFileSize = logConfigProperties.getProperty(ISLOGFILESIZE, "false");

		if ("true".equals(isLogEnable) && "true".equals(isLogFileSize)){
			sb.append(prepareLog(startTime, endTime, message, FileUtil.getStringSizeLengthFile(fileChannel.size()), status, destination));
		} else if("true".equals(isLogEnable) && !"true".equals(isLogFileSize)){
			sb.append(prepareLog(startTime, endTime, message, status, destination));
		}

		
		return sb.toString();
	}
	
	/**
	 * Prepare Log Format
	 * @param      	start time
	 * @param 		end time
	 * @param		file Name
	 * @param		file size
	 * @param		status
	 * @param		destination path
	 * @return		Log String
	 */
	private static String prepareLog(long startTime, long endTime, String fileName, String fileSize, String status, String destination) {
		StringBuffer sb = new StringBuffer();
		sb.append(fileName);
		
		sb.append("[Size : ").append(fileSize).append("] ");
		sb.append("[Start : ").append(formatYYYYMMddHHMMSS_SSS(startTime)).append("] ");
		sb.append("[End : ").append(formatYYYYMMddHHMMSS_SSS(endTime)).append("] ");
		sb.append("[Duration : ").append(TimeUtilities.formatMillis(calculateDuration(startTime, endTime), TimeUnit.DAYS, TimeUnit.SECONDS)).append("] ");
		
		sb.append("[").append(status).append("] ");
		sb.append("[").append(destination).append("]");
		
		return sb.toString();
	}
	
	/**
	 * Prepare Log Format
	 * @param      	start time
	 * @param 		end time
	 * @param		fileName
	 * @param		file size
	 * @param		status
	 * @param		destination path
	 * @return		Log String
	 */
	private static String prepareLog(long startTime, long endTime, String fileName, String status, String destination) {
		StringBuffer sb = new StringBuffer();
		sb.append(fileName);
		
		sb.append(" - ");
		sb.append("[Start : ").append(formatYYYYMMddHHMMSS_SSS(startTime)).append("] ");
		sb.append("[End : ").append(formatYYYYMMddHHMMSS_SSS(endTime)).append("] ");
		sb.append("[Duration : ").append(TimeUtilities.formatMillis(calculateDuration(startTime, endTime), TimeUnit.DAYS, TimeUnit.SECONDS)).append("] ");
		
		sb.append("[").append(status).append("] ");
		sb.append("[").append(destination).append("]");
		
		return sb.toString();
	}

	/**
	 * Calcualtion duration between start time and end time
	 * @param      	start time
	 * @param 		end time
	 * @return 		duration
	 */
	public static long calculateDuration(long startTime, long endTime) {
		return (endTime - startTime);
	}
	
	
	/**
	 * Format curretTimeMilliSeconds to YYYYMMddHHMMSS_SSS
	 * @param      	currentTimeMilliSeconds
	 * @return		String representation of YYYYMMddHHMMSS_SSS
	 */
	public static String formatYYYYMMddHHMMSS_SSS(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		return sdf.format(new Date(time));
	}
}
