/**
 @(#)IntfCrypto.java
 @version 1.1.0 13-Apr-2016

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

import java.io.InputStream;
import java.io.OutputStream;

import scrips.datamigration.decrypt.util.common.Result;

/**
 * Interface for Crypto
 * @version  1.0.0 13-Apr-2016
 * @author   Win Htut Aung
 * 
 * @ver	    15-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_17 
 *  	    Sensitive Information Disclosure - Privacy Violation (Heap Inspection) 
 */

public interface IntfCrypto {
	
	/**
	 * File encryption method
	 * @param      	inputFile / file path of input file
	 * @param     	outputFile / file path of output file
	 * @throws 		Exception
	 * @return 		Result
	 */
	Result encrypt(String inputFile, String outputFile)  throws Exception;
	
	/**
	 * File encryption method
	 * @param      	file input stream of input file
	 * @param     	file output stream of output file
	 * @return 		Result
	 * @throws 		Exception
	 */
	Result encrypt(InputStream fis, OutputStream fos) throws Exception;
	
	/**
	 * String encryption method
	 * @param      	inputString
	 * @return 		encrypted string
	 * @throws 		Exception
	 */
	String encrypt(String inputString) throws Exception;
	
	/**
	 * File decryption method
	 * @param      	inputFile / encrypted file for decryption
	 * @param 		outputFile / decrypted file
	 * @return		resi;t
	 * @throws 		Exception
	 */
	Result decrypt(String inputFile, String outputFile)  throws Exception;
	

	/**
	 * File decryption method
	 * @param      	inputFileStream / encrypted file input stream for decryption
	 * @param 		outputFileStream / decrypted file output stream
	 * @return 		Result
	 * @throws 		Exception
	 */
	Result decrypt(InputStream fis, OutputStream fos)  throws Exception;
	
	/**
	 * String decryption method
	 * @param      	encrypted String
	 * @return 		original String
	 * @throws 		Exception
	 */
	String decrypt(String encryptedString)  throws Exception;
	
	
	/**
	 * Pass Code Setter
	 * @param      	passCode
	 */
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
	//void setPassCode(String passCode);
	void setPassCode(char[] passCode);
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End
	
	/**
	 * Salt Setter
	 * @param      	salt
	 */
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
	//void setSalt(String salt); 
	void setSalt(char[] salt);
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
	
	/**
	 * Iteration Count Setter
	 * @param      	iterationCount
	 */
	void setIterationCount(int iterationCount);
	
	
}
