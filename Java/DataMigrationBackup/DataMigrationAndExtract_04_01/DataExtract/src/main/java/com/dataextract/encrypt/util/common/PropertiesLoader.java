/**
 @(#)Properties Loader.java
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
package com.dataextract.encrypt.util.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Properties Loader
 * @version  1.0.0 27-Apr-2016
 * @author   Win Htut Aung
 * 
 * @ver   10-Jul-2017
 * @auth  Sanjuckta
 * @change MEPSUAT-2017-0036_15
 *         Prevention of sensitive information disclosure 
 *         
 * @ver		03-Jan-2022 
 * @auth	Khin Su
 * @change	MEPSSUP-2021-1164
 * 			change import log4j 1x to 2x
 */

public class PropertiesLoader {
	//11-Jul-2017 - Sanjuckta - MEPSUAT-2017-0036_15 - Start
	final static Logger logger = LogManager.getLogger(FileUtil.class);
	//11-Jul-2017 - Sanjuckta - MEPSUAT-2017-0036_15 - End
	public static PropertiesLoader getInstance() {
		return new PropertiesLoader();
	}

	/**
	 * Get Properties file from given parameter
	 * @param      	fileName
	 * @return     	Properties object
	 * @throws      IO exception for properties file not found
	 */

	public Properties loadProperties(String fileName) throws IOException {
		Properties prop = new Properties();
		InputStream inputStream = null;
		
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException(fileName + " file not found.");
			}
		} catch (Exception e) {
			//11-Jul-2017 - Sanjuckta - MEPSUAT-2017-0036_15 - Start
			/*e.printStackTrace();*/
			logger.error("Unable to load property File  :"+fileName);
			//11-Jul-2017 - Sanjuckta - MEPSUAT-2017-0036_15 - End
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			inputStream = null;
		}
		return prop;
	}
}
