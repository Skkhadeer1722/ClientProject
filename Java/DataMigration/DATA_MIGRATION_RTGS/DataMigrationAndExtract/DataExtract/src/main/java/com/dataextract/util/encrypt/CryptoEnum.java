/**
 @CryptoEnum.java
 @version 1.1.0 13-May-2016

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

/**
 * CryptoEnum
 * @version  1.0.0 13-May-2016
 * @author   Win Htut Aung
 */
public enum CryptoEnum {
	AES128("AES128"),
	AES256("AES256"),
	DES("DES");
	
	private final String value;
	CryptoEnum(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
