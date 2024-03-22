/**
 @CryptoFactorty.java
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

/**
 * Factory class for Cryptography
 * @version  1.0.0 13-May-2016
 * @author   Win Htut Aung
 * 
 * @ver	    15-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_17 
 *  	    Sensitive Information Disclosure - Privacy Violation (Heap Inspection) 
 */

public class CryptoFactory {
	/**
	 * Factory method for cryptography
	 * @param algorithm
	 * @param passCode
	 * @param salt
	 * @return Crypto Algorithm Implementation according via parameters
	 */
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	//public static IntfCrypto getEncrypter(String algorithm, String passCode, String salt) throws ClassNotFoundException {
	public static IntfCrypto getEncrypter(String algorithm, char[] passCode, char[] salt) throws ClassNotFoundException {	
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End
	    if (CryptoEnum.AES256.getValue().equals(algorithm)) {
			return new AES256CryptoImpl(salt, passCode);
		} else if(CryptoEnum.AES128.getValue().equals(algorithm)) {
			return new AES128CryptoImpl(salt, passCode);
		} else if (CryptoEnum.DES.getValue().equals(algorithm)) {
			//return new AESCryptoImpl();
		} else {
			throw new ClassNotFoundException(algorithm + " implemntation class can't be loaded.");
		}
		return null;
	}
}
