/**
 @(#)Config Properties Loader.java
 @version 0.0.1 20-Apr-2016

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

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Config Properties Loader
 * @version  1.0.0 20-Apr-2016
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

public class ConfigPropertiesLoader {
	private final String configFileName = "config.properties";
	//11-Jul-2017 - Sanjuckta - MEPSUAT-2017-0036_15 - Start
		final static Logger logger = LogManager.getLogger(FileUtil.class);
		//11-Jul-2017 - Sanjuckta - MEPSUAT-2017-0036_15 - End
	
	/**
	 * Get Properties Value from config.properties file using given parameter
	 * @param      	Key of Property
	 * @return     	Value of Property
	 * @throws      File Not Found exception for properties file not found,
	 * @throws		property not found exception for wrong property	
	 */
	
	public String getPropValues(String key) throws IOException {
		String value = "";
		try {
			Properties prop = new Properties();
			PropertiesLoader propLoader = PropertiesLoader.getInstance();
			prop = propLoader.loadProperties(configFileName);
			
			if (prop.containsKey(key)) {
				value = prop.getProperty(key);
			} else {
				throw new Exception("Not found propety " + key);
			}
			
		} catch (Exception e) {
			//11-Jul-2017 - Sanjuckta - MEPSUAT-2017-0036_15 - Start
			/*e.printStackTrace();*/
			logger.error("Unable to load property File  :"+configFileName);
			//11-Jul-2017 - Sanjuckta - MEPSUAT-2017-0036_15 - End
		} 
		return value;
	}
	
	/**
	 * Get Properties Value from config.properties file using given parameter
	 * @return     	Value of Property
	 * @throws      File Not Found exception for properties file not found,
	 */
	
	public Properties getProperties() throws IOException {
		Properties prop = new Properties();
		prop = PropertiesLoader.getInstance().loadProperties(configFileName);
		return prop;
	}
}
