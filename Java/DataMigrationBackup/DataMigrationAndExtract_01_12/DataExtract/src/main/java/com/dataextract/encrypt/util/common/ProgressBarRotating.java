/**
 @(#)ProgressBarRotating.java
 @version 13-May-2016

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Loading Sign for DOS Application
 * @version  1.0.0 13-May-2016
 * @author   Win Htut Aung
 * 
 * @ver     10-Jul-2017
 * @auth    Jayapradha
 * @change  MEPSUAT-2017-0036_05
 * 		    Improper Error Handling - Empty Catch Block 
 * 
 * @ver		03-Jan-2022 
 * @auth	Khin Su
 * @change	MEPSSUP-2021-1164
 * 			change import log4j 1x to 2x
 */
public class ProgressBarRotating extends Thread {
	public boolean showProgress = true;
	// 10-Jul-2017 - Jayapradha - MEPSUAT-2017-0036_05- Start
	final static Logger logger = LogManager.getLogger(ProgressBarRotating.class);
	// 10-Jul-2017 - Jayapradha - MEPSUAT-2017-0036_05- End

	public void run() {
		while (showProgress) {
			try {
				System.out.print((char) (8) + "\\");
				Thread.sleep(100);
				System.out.print((char) (8) + "|");
				Thread.sleep(100);
				System.out.print((char) (8) + "/");
				Thread.sleep(100);
				System.out.print((char) (8) + "-");

			} catch (Exception e) {
				// 10-Jul-2017 - Jayapradha - MEPSUAT-2017-0036_05- Start
				logger.error(e.getMessage());
				// 10-Jul-2017 - Jayapradha - MEPSUAT-2017-0036_05- End
			}
		}
	}
}
