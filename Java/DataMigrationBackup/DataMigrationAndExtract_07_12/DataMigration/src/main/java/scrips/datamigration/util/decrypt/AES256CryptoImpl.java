/**
 @(#)AESCryptoImpl.java
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

package scrips.datamigration.util.decrypt;

import java.io.InputStream;
import java.io.OutputStream;

import scrips.datamigration.decrypt.util.common.Result;

/**
 * AES Crypto Algorithm Implementation Wrapper Class
 * @version  1.0.0 13-May-2016
 * @author   Win Htut Aung
 * 
 * @ver	    15-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_17 
 *  	    Sensitive Information Disclosure - Privacy Violation (Heap Inspection) 
 */

public class AES256CryptoImpl implements IntfCrypto {
	
	private PasswordCrypto passwordCrypto;
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	//private String passCode;
	//private String salt;
	private char[] passCode;
	private char[] salt;
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
	private int iterationCount = 1000;
	private static final String CIPHER_ALGORITHM = "PBEWithSHA256And256BitAES-CBC-BC";
	
	/**
	 * Constructor AES Crypto Impl
	 * @param      	salt
	 * @param     	passCode
	 * @param      	iterationCount
	 */
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	//public AES256CryptoImpl(String salt, String passCode, int iterationCount) {
	public AES256CryptoImpl(char[] salt, char[] passCode, int iterationCount) {
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
		this.salt = salt;
		this.passCode = passCode;
		this.iterationCount = iterationCount;
		passwordCrypto = new PasswordCrypto(salt);
		passwordCrypto.setIterationCount(iterationCount);
		passwordCrypto.setCipherAlgorithm(CIPHER_ALGORITHM);
	}
	
	
	/**
	 * Constructor AES Crypto Impl
	 * @param      	salt
	 * @param     	passCode
	 */
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	// public AES256CryptoImpl(String salt, String passCode) {
	public AES256CryptoImpl(char[] salt, char[] passCode) {
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
		this.salt = salt;
		this.passCode = passCode;
		passwordCrypto = new PasswordCrypto(this.salt);
		passwordCrypto.setIterationCount(iterationCount);
		passwordCrypto.setCipherAlgorithm(CIPHER_ALGORITHM);
	}

	
	/**
	 * File encryption method
	 * @param      	inputFile / file path of input file
	 * @param     	outputFile / file path of output file
	 * @throws 		Exception
	 * @return 		Result
	 */
	@Override
	public Result encrypt(String inputFile, String outputFile) throws Exception {
		Result result = new Result();
		result = passwordCrypto.encrypt(passCode, inputFile, outputFile);
		return result;
	}

	/**
	 * File encryption method
	 * @param      	file input stream of input file
	 * @param     	file output stream of output file
	 * @return 		Result
	 * @throws 		Exception
	 */
	@Override
	public Result encrypt(InputStream fis, OutputStream fos) throws Exception {
		Result result = new Result();
		result = passwordCrypto.encrypt(passCode, fis, fos);
		return result;
	}

	/**
	 * String encryption method
	 * @param      	inputString
	 * @return 		encrypted string
	 * @throws 		Exception
	 */
	@Override
	public String encrypt(String inputString) throws Exception {
		return passwordCrypto.encrypt(passCode, inputString);
	}

	/**
	 * File decryption method
	 * @param      	inputFile / encrypted file for decryption
	 * @param 		outputFile / decrypted file
	 * @return		resi;t
	 * @throws 		Exception
	 */
	@Override
	public Result decrypt(String inputFile, String outputFile) throws Exception {
		return passwordCrypto.decrypt(passCode, inputFile, outputFile);
	}

	/**
	 * File decryption method
	 * @param      	inputFileStream / encrypted file input stream for decryption
	 * @param 		outputFileStream / decrypted file output stream
	 * @return 		Result
	 * @throws 		Exception
	 */
	@Override
	public Result decrypt(InputStream fis, OutputStream fos) throws Exception {
		return passwordCrypto.decrypt(passCode, fis, fos);
	}

	/**
	 * String decryption method
	 * @param      	encrypted String
	 * @return 		original String
	 * @throws 		Exception
	 */
	@Override
	public String decrypt(String encryptedString) throws Exception {
		return passwordCrypto.decrypt(passCode, encryptedString);
	}
	
	/**
	 * Pass Code Setter
	 * @param      	passCode
	 */
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
	//public void setPassCode(String passCode) {
	@Override
	public void setPassCode(char[] passCode) {
		this.passCode = passCode;
	}
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End

	/**
	 * Salt Setter
	 * @param      	salt
	 */
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	//@Override
	//public void setSalt(String salt) {
	//	this.salt = salt;
	//}
	@Override
	public void setSalt(char[] salt) {
		this.salt = salt;
	}
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
	
	/**
	 * Iteration Count Setter
	 * @param      	iterationCount
	 */
	@Override
	public void setIterationCount(int count) {
		this.iterationCount = count;
	}

}
