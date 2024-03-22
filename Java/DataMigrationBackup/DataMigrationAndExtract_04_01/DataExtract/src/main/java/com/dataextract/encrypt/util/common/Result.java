/**
 @(#)Result.java
 @version 20-Apr-2016

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
 * Result Wrapper
 * @version  1.0.0 20-Apr-2016
 * @author   Win Htut Aung
 */
public class Result {
	private boolean success;
	private String message;
	private String fileName;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	private void clearProperties() {
		this.success = false;
		this.message = "";
		this.fileName = "";
	}
	
	public Result() {
		clearProperties();
	}
}
