/**
 @(#)Error Message Code.java
 @version 0.0.1 23-Apr-2016

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
/**
 * ErrorMessageCode
 * @version  1.0.0 23-Apr-2016
 * @author   Win Htut Aung
 */

public class ErrorMessageCode {
	// UTL01001 to UTL01999 for events related to data extraction program
	public static final String UTL01001 = "UTL01001";
	
	// UTL02001 to UTL02999 for events related to file hashing program
	public static final String UTL02001 = "UTL02001";
	public static final String UTL02002 = "UTL02002";
	public static final String UTL02003 = "UTL02003";
	public static final String UTL02004 = "UTL02004";
	
	// UTL03001 to UTL03999 for events related to file encryption program
	public static final String UTL03001 = "UTL03001";
	public static final String UTL03002 = "UTL03002";
	public static final String UTL03003 = "UTL03003";
	public static final String UTL03004 = "UTL03004";
	
	// UTL04001 to UTL04999 for events related to file decryption program
	public static final String UTL04001 = "UTL04001";
	public static final String UTL04002 = "UTL04002";
	
	// UTL05001 to UTL05999 for events related to hash verification program
	public static final String UTL05001 = "UTL05001";
	public static final String UTL05002 = "UTL05002";
	
	// UTL06001 to UTL06999 for events related to the common
	public static final String UTL06001 = "UTL06001";
	public static final String UTL06002 = "UTL06002";
}

