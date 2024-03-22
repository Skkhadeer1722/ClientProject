/**
 @(#)MutltiResult.java
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

package scrips.datamigration.decrypt.util.common;

import java.util.ArrayList;

/**
 * Multi Result Wrapper
 * @version  1.0.0 20-Apr-2016
 * @author   Win Htut Aung
 */

public class MultiResult {
	private ArrayList<Result> successResults;
	private ArrayList<Result> failedResults;
	private ArrayList<Result> otherResults;
	
	public ArrayList<Result> getSuccessResults() {
		return successResults;
	}

	public void setSuccessResults(ArrayList<Result> results) {
		this.successResults = results;
	}

	public ArrayList<Result> getFailedResults() {
		return failedResults;
	}

	public void setFailedResults(ArrayList<Result> failedResults) {
		this.failedResults = failedResults;
	}
	
	public ArrayList<Result> getOtherResults() {
		return otherResults;
	}

	public void setOtherResults(ArrayList<Result> otherResults) {
		this.otherResults = otherResults;
	}
	
	private void clearProperties() {
		this.successResults = new ArrayList<Result>();
		this.failedResults = new ArrayList<Result>();
		this.otherResults = new ArrayList<>();
	}

	public MultiResult() {
		clearProperties();
	}
}
