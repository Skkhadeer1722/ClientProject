/**
(#)CommonConstant.java
@version 1.0.0 28-June-2016

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

import java.util.ArrayList;

/**
 * CommonConstant
 * @version  1.0.0 28-June-2016
 * @author   Win Htut Aung
 * 
 * @ver	    15-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_17 
 *  	    Sensitive Information Disclosure - Privacy Violation (Heap Inspection) 
*/
public class CommonConstant {
	public static final String MAX_FILE_LIEFTIME= "maxFileLifeTime";
	public static final String NEEDED_COPY_FILES = "neededCopyFiles";
	// 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- Start
	/*public static String mixIt(String secondHalf) {
		return "#)0xFE8A3!z5+KDo".concat(secondHalf);
	}*/
	
	public static char[] mixIt(char[] secondHalf) {

		char[] mixIt = "#)0xFE8A3!z5+KDo".toCharArray();
		ArrayList<Character> mixArr = new ArrayList<Character>();

		for (int i = 0; i < mixIt.length; i++) {
			mixArr.add(mixIt[i]);
		}

		for (int i = 0; i < secondHalf.length; i++) {
			mixArr.add(secondHalf[i]);
		}

		char[] mixItReturn = new char[mixArr.size()];

		for (int i = 0; i < mixArr.size(); i++) {
			mixItReturn[i] = mixArr.get(i);
		}

		return mixItReturn;
	}
	// 02-Mar-2017 - Suma - MEPSUAT-2017-0035_17- End  
}
