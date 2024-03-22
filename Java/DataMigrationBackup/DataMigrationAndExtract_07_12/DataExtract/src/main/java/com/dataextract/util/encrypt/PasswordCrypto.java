/**
 @(#)PasswordCrypto.java
 @version 1.0.0    21 Mar 2006

*******************************************************************************

        Copyright(c) 2004 BCS Information Systems Pte Ltd
                     11 Tampines Central 1
                OCBC Tampines Centre One #08-00,
                        Singapore 529542

                       All Right Reserved.

*******************************************************************************
This software is the confidential and proprietary information of BCSIS Pte Ltd.
("Confidential Information"). You shall not disclose such Confidential Information
and shall use it only in accordance with the terms of the license agreement
you entered into with BCSIS.
*******************************************************************************
* 
*/

package com.dataextract.util.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Iterator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.dataextract.encrypt.util.common.Result;

/**
 * PasswordCrypto. This class provides methods for encryption and decrytions of strings. 
 *
 * @version 1.0.0 21-Mar-2006
 * @author Mendu Ravi
 */

/**
 * Program Amendment History
 *
 * @ver    14-Jan-2011
 * @auth   Mendu Ravi
 * @change CR2011R201 Comments
 *         Changes related to WAS Upgrade
 *
 * @ver    15-Jan-2015
 * @auth   Tatang
 * @change MEPSSUP20140090
 *         Source code review finding on weak cipher algorithm
 *         
 * @ver    20-Aug-2015
 * @auth   RMCB
 * @change MEPSSUP-2014-0092_A2
 *         Code Correctness: Byte Array to String Conversion     
 *         
 * @ver    11-Apr-2016
 * @auth   Khin Su
 * @change BCSISSIT-2016-0001_14
 *         [BCSIS SCR 2015] Weak Cryptographic Hash: Hardcoded PBE Salt.
 *         
 * @ver		13-May-2016
 * @auth	Win Htut Aung
 * @change  Added mehtod for file encryption and decryption
 * 			setter for iteration count 
 * 
 * @ver     09-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_13
 *	 	    Not aligned with Coding Best Practices - Unchecked Return Value 
 *
 * @ver	    15-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_17 
 *  	    Sensitive Information Disclosure - Privacy Violation (Heap Inspection)
 *  
 * @ver		06-Jun-2017
 * @auth    Win
 * @change  MEPSUAT-2017-0035_17
 * 			Sensitive Information Disclosure - Privacy Violation (Heap Inspection)
 *  
 * @ver     10-Jul-2017
 * @auth    Jayapradha
 * @change  MEPSUAT-2017-0036_05
 * 		    Improper Error Handling - Empty Catch Block
 * 
 * @ver    21-Feb-2018
 * @auth   Jayapradha    
 * @change MEPSUAT-2017-0036_18
 *         Unreleased Resources
 *         
 * @ver		03-Jan-2022 
 * @auth	Khin Su
 * @change	MEPSSUP-2021-1164
 * 			change import log4j 1x to 2x
 */

public class PasswordCrypto
{   
    private Cipher eCipher;
    private Cipher dCipher;
    
    final static Logger logger = LogManager.getLogger(PasswordCrypto.class);
    // 15-Jan-2015 - Tatang - MEPSSUP20140090 - Start
    //private String keyAlgorithm = "PBEWithMD5AndDES";         // Algorithm Used for Key generation
    private String keyAlgorithm = "PBEWithMD5And128BitAES-CBC-OpenSSL";         // Algorithm Used for Key generation
    // 15-Jan-2015 - Tatang - MEPSSUP20140090 - End
    // 14-Jan-2011 - Mendu Ravi - CR2011R201 - Start
    //PBEWithMD5AndTripleDES is a proprietary algorithm that has not been standardized in Java 1.6
    //Use PBEWithSHA1AndDESede algorithm to generate Cipher
    //private String cipherAlgorithm = "PBEWithMD5AndTripleDES";// Algorithm Used for Encryption/Decryption
    // 15-Jan-2015 - Tatang - MEPSSUP20140090 - Start
    //private String cipherAlgorithm = "PBEWithSHA1AndDESede";
    private String cipherAlgorithm = "PBEWithSHA256And128BitAES-CBC-BC";
    // 15-Jan-2015 - Tatang - MEPSSUP20140090 - End
    // 14-Jan-2011 - Mendu Ravi - CR2011R201 - End
    
    private int blockSize = 16;

	byte[] buf = new byte[blockSize]; // input buffer
	byte[] obuf = new byte[512]; // output buffer

    
    static{
        //Add the security provider.
        // 15-Jan-2015 - Tatang - MEPSSUP20140090 - Start
        //Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Security.addProvider(new BouncyCastleProvider());
        // 15-Jan-2015 - Tatang - MEPSSUP20140090 - End
    }

    // 11-Apr-2016 - Khin Su - BCSISSIT-2016-0001_14 - Start
    private static final byte[] salt = { 
        (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, 
        (byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99,
//        (byte) 0x9E, (byte) 0xA5, (byte) 0xA9, (byte) 0x83,
//        (byte) 0xA6, (byte) 0xA4, (byte) 0x3A, (byte) 0x23
        }; // 16-byte Salt used for PBE Specification
//    private static final byte[] salt = { 
//        (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c
//        }; // First 4 byte of 8-byte salt used for PBE Specification
    private ArrayList<Byte> inputSalt; 
    // 11-Apr-2016 - Khin Su - BCSISSIT-2016-0001_14 - End
    
    // Iteration count
    int iterationCount = 1000;
    
    public void setIterationCount(int iterationCount) {
    	this.iterationCount = iterationCount;
    }
    
    public String getCipherAlgorithm() {
		return cipherAlgorithm;
	}

	public void setCipherAlgorithm(String cipherAlgorithm) {
		this.cipherAlgorithm = cipherAlgorithm;
	}



	// 11-Apr-2016 - Khin Su - BCSISSIT-2016-0001_14 - Start
    /**
	 * @param salt
	 */
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
	//public PasswordCrypto(String salt2) {
	public PasswordCrypto(char[] salt2) {
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End
		super();
		// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
		//StringTokenizer st = new StringTokenizer(salt2);
		// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
		this.inputSalt = new ArrayList<Byte>();
		// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
		/*
		while (st.hasMoreTokens()) {
			try {
				int singleByte = Integer.parseInt(st.nextToken(), 16);
				this.inputSalt.add((byte)singleByte);
			} catch (NumberFormatException nfe) {
//				ACSException.log(1, this.getClass().toString(), "Extract PBE SALT", nfe.getMessage());
			}
		}
		*/
//		for (int i=0; i < salt2.length; i++ ) {
//			try {     
//				int singleByte = Integer.parseInt(String.valueOf(salt2[i]), 16);
//				this.inputSalt.add((byte)singleByte);
//			} catch (NumberFormatException nfe) {
//				logger.error(nfe.getMessage());
//			}
//		}
		
		//06-Jun-2017 - Win -  MEPSUAT-2017-0035_17-  Start
		int saltCounter = 0;
		char concatSalt [] = new char [2];
		for (int i=0; i < salt2.length; i++ ) {
			try {     
				if (salt2[i] == ' ') {
					continue;
					
				} else {
					saltCounter ++;
					if (saltCounter == 1) {
						concatSalt[0]  = salt2[i];
					} else if (saltCounter == 2) {
						concatSalt[1]  = salt2[i];
					}
				}
				
				if (saltCounter == 2) {
					int singleByte = Integer.parseInt(String.valueOf(concatSalt[0]) + String.valueOf(concatSalt[1]), 16);
					this.inputSalt.add((byte)singleByte);
					saltCounter = 0;
				}
			} catch (NumberFormatException nfe) {
				logger.error(nfe.getMessage());
			}
		}
		//06-Jun-2017 - Win -  MEPSUAT-2017-0035_17 - End
		
		// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End
	}
    // 11-Apr-2016 - Khin Su - BCSISSIT-2016-0001_14 - End

    /**
     * This method initializes the Cipher used for 
     * @param passCode. Passcode used to generate the key
     * @exception java.lang.Exception. All exceptions are wrapped into java.lang.Exception.
     */    
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
    //private void initEncrypt(String passCode) throws Exception
	private void initEncrypt(char[] passCode) throws Exception
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
    {
        //Create the key
		// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
        //KeySpec keySpec = new PBEKeySpec(passCode.toCharArray());
		KeySpec keySpec = new PBEKeySpec(passCode);
        // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
        SecretKey key = SecretKeyFactory.getInstance(keyAlgorithm).generateSecret(keySpec);        
        eCipher = Cipher.getInstance(cipherAlgorithm);
        // Prepare the parameter to the ciphers
        // 11-Apr-2016 - Khin Su - BCSISSIT-2016-0001_14 - Start
        //AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(finalPBESalt(), iterationCount);
        // 11-Apr-2016 - Khin Su - BCSISSIT-2016-0001_14 - End

        //Create the ciphers for Decryption 
        eCipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        
    }
    
    /**
     * This method initializes the Cipher used for decryption
     * @param passCode. Passcode used to generate the key.
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     * @throws NoSuchPaddingException 
     * @throws InvalidAlgorithmParameterException 
     * @throws InvalidKeyException 
     * @exception java.lang.Exception. All exceptions are wrapped into java.lang.Exception.
     */
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
    //private void initDecrypt(String passCode) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException
	private void initDecrypt(char[] passCode) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException
 // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End
    {
        //Create the key
		// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
        //KeySpec keySpec = new PBEKeySpec(passCode.toCharArray());
		KeySpec keySpec = new PBEKeySpec(passCode);
        // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End
        SecretKey key = SecretKeyFactory.getInstance(keyAlgorithm).generateSecret(keySpec);
        dCipher = Cipher.getInstance(cipherAlgorithm);
        // Prepare the parameter to the ciphers
        // 11-Apr-2016 - Khin Su - BCSISSIT-2016-0001_14 - Start
        //AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(finalPBESalt(), iterationCount);
        // 11-Apr-2016 - Khin Su - BCSISSIT-2016-0001_14 - End
        // Create the ciphers for Decryption          
        dCipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        
    }
    
    /**
     * This method encrypts the given string.
     * @param       passCode. Passcode used to generate the key.
     * @param       strToBeEncrypted. String to be encrypted.
     * @return      encrypted string.
     * @exception   java.lang.Exception. All exceptions are wrapped into java.lang.Exception.
     */
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
    //public String encrypt(String passCode, String strToBeEncrypted) throws Exception
    public String encrypt(char[] passCode, String strToBeEncrypted) throws Exception
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End
    {
        //init ciphers
        initEncrypt(passCode);            
        
        // Encode the string into bytes using utf-8
        byte[] utf8 = strToBeEncrypted.getBytes();

        // Encrypt
        byte[] enc = eCipher.doFinal(utf8);
        
        //Encode bytes to base64 to get a string
        //20-Aug-2015 - RMCB - MEPSSUP-2014-0092_A2 - Start
        //return new String (new Base64().encode(enc));
        return new Base64().encodeToString(enc);
        //20-Aug-2015 - RMCB - MEPSSUP-2014-0092_A2 - End
    }
    
    /**
     * This method decrypts the given string.
     * @param       passCode. Passcode used to generate the key.
     * @param       strToBeEncrypted. String to be encrypted.
     * @return      encrypted string.
     * @exception   java.lang.Exception. All exceptions are wrapped into java.lang.Exception.
     */    
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
    //public String decrypt(String passCode, String strToBeDecrypted) throws Exception
    public String decrypt(char[] passCode, String strToBeDecrypted) throws Exception
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End
    {
        //init ciphers
        initDecrypt(passCode);
        
        //Decode base64 to get bytes
        byte[] dec = new Base64().decode(strToBeDecrypted.getBytes());

        // Decrypt
        byte[] strBytes = dCipher.doFinal(dec);
        
        //20-Aug-2015 - RMCB - MEPSSUP-2014-0092_A2 - Start
        //return new String(strBytes);
        return new String(strBytes, "UTF-8");
        //20-Aug-2015 - RMCB - MEPSSUP-2014-0092_A2 - End
    }    
    
    // 11-Apr-2016 - Khin Su - BCSISSIT-2016-0001_14 - Start
    /**
     * This method combine 1st half of PBE salt (within code) and 2nd half of PBE salt (from properties).
     * @return      PBE salt.
     */
    protected byte[] finalPBESalt() {
        byte[] pbeSalt = new byte[salt.length + inputSalt.size()];
        System.arraycopy(salt, 0, pbeSalt, 0, salt.length);
        Iterator<Byte> it = inputSalt.iterator();
        for (int idx=salt.length; it.hasNext(); idx++) {
        	pbeSalt[idx] = it.next().byteValue();
        }
        return pbeSalt;
    }
    // 11-Apr-2016 - Khin Su - BCSISSIT-2016-0001_14 - End
    

    /**
     * Encrypt the given file and result into the given output file
     * @param passCode
     * @param inputFile
     * @param outputFile
     * @return      Result
     * @throws Exception
     */
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
    /*public Result encrypt(String passCode, String inputFile, String outputFile)
			 throws Exception {*/
    public Result encrypt(char[] passCode, String inputFile, String outputFile) throws Exception {    
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End    	
		Result result = new Result();

		File input = new File(inputFile);
		File output = new File(outputFile);

		FileInputStream fis = new FileInputStream(input);
		FileOutputStream fos = new FileOutputStream(output);

		try {
			result = encrypt(passCode, fis, fos);
		} catch (Exception e) {
			//09-Feb-2017 - Suma -MEPSUAT-2017-0035_13 - Start
            //output.delete();
            boolean checkDelete = output.delete();;
            if(!checkDelete) 
            {
            	logger.error(e.getMessage());
            	// 10-Jul-2017 - Jayapradha - MEPSUAT-2017-0036_05- Start
            	throw e;
            	// 10-Jul-2017 - Jayapradha - MEPSUAT-2017-0036_05- End
            }
            //09-Feb-2017 - Suma -MEPSUAT-2017-0035_13 - End
			
			throw e;
		//21-Feb-2018 - Jayapradha -  MEPSUAT-2017-0036_18 - Start	
		} finally {
			try {
				if(fis != null){
					fis.close();
					fis = null;
				}
				if(fos != null){
					fos.close();
					fos = null;
				}
			} catch(IOException ex) {
				logger.debug("Problem in closing the Stream : "+ ex.toString());
			}
		}//21-Feb-2018 - Jayapradha -  MEPSUAT-2017-0036_18 - End

		return result;
	}
    
    
    /**
     * Encrypt the given input stream into the output stream
     * @param passCode
     * @param inputStream
     * @param outputStream
     * @return      Result
     * @throws Exception
     */
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
    /*public Result encrypt(String passCode, InputStream fis, OutputStream fos)
			throws Exception {*/
    public Result encrypt(char[] passCode, InputStream fis, OutputStream fos)
			throws Exception {    
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
		Result result = new Result();
		
		try {
			initEncrypt(passCode);
			
			byte[] buffer = new byte[blockSize];
			int noBytes = 0;
			byte[] cipherBlock = new byte[eCipher.getOutputSize(buffer.length)];
			int cipherBytes;
			while ((noBytes = fis.read(buffer)) != -1) {
				cipherBytes = eCipher.update(buffer, 0, noBytes, cipherBlock);
				fos.write(cipherBlock, 0, cipherBytes);
			}
			
			// always call doFinal
			cipherBytes = eCipher.doFinal(cipherBlock, 0);
			fos.write(cipherBlock, 0, cipherBytes);
		} catch (Exception e) {
			result.setSuccess(false);
			throw e;
		} finally {
			// close the files
			try {
				fos.close();
				fis.close();
			} catch (Exception ioe) {
				logger.error(ioe.getMessage());
				// 10-Jul-2017 - Jayapradha - MEPSUAT-2017-0036_05- Start
				throw ioe;
				// 10-Jul-2017 - Jayapradha - MEPSUAT-2017-0036_05- End
			}
		}
		
		result.setSuccess(true);
		return result;
	}
    
    
    /**
     * Decrypt the given file and result into the given output file
     * @param passCode
     * @param inputFile
     * @param outputFile
     * @return      Result
     * @throw Exception
     */
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
    //public Result decrypt(String passCode, String inputFile, String outputFile) throws Exception
    public Result decrypt(char[] passCode, String inputFile, String outputFile) throws Exception
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End
    {
		Result result = new Result();

		File input = new File(inputFile);
		File output = new File(outputFile);
		
		FileInputStream fis = new FileInputStream(input);
		FileOutputStream fos = new FileOutputStream(output);

		try {
			result = decrypt(passCode, fis, fos);
		} catch (Exception e) {
			//09-Feb-2017 - Suma -MEPSUAT-2017-0035_13 - Start
            //output.delete();
            boolean checkDelete = output.delete();;
            if(!checkDelete) 
            {
            	logger.error(e.getMessage());
            }
            //09-Feb-2017 - Suma -MEPSUAT-2017-0035_13 - End
			throw e;
			//21-Feb-2018 - Jayapradha -  MEPSUAT-2017-0036_18 - Start	
			} finally {
				try {
					if(fis != null){
						fis.close();
						fis = null;
					}
					if(fos != null){
						fos.close();
						fos = null;
					}
				} catch(IOException ex) {
					logger.debug("Problem in closing the Stream : "+ ex.toString());
				}
			}//21-Feb-2018 - Jayapradha -  MEPSUAT-2017-0036_18 - End
		return result;
	}

    /**
     * Decrypt the given Input Stream into the given output Stream
     * @param passCode
     * @param inputStream
     * @param outputStream
     * @return      Result
     * @throws InvalidAlgorithmParameterException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     * @throws InvalidKeyException 
     * @throws IOException 
     * @throws ShortBufferException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws Exception 
     * @throw Exception
     */
    // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
    //public Result decrypt(String passCode, InputStream fis, OutputStream fos) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, ShortBufferException, IOException, IllegalBlockSizeException, BadPaddingException, Exception {
    public Result decrypt(char[] passCode, InputStream fis, OutputStream fos) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, ShortBufferException, IOException, IllegalBlockSizeException, BadPaddingException, Exception {
  	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start
    	Result result = new Result();
    	
    	try {
    		initDecrypt(passCode);

    		byte[] buffer = new byte[blockSize];
    		int noBytes = 0;
    		byte[] cipherBlock = new byte[dCipher.getOutputSize(buffer.length)];
    		int cipherBytes;
    		while ((noBytes = fis.read(buffer)) != -1) {
    			cipherBytes = dCipher.update(buffer, 0, noBytes, cipherBlock);
    			fos.write(cipherBlock, 0, cipherBytes);
    		}
    		// allways call doFinal
    		cipherBytes = dCipher.doFinal(cipherBlock, 0);
    		fos.write(cipherBlock, 0, cipherBytes);
    		
		} catch (Exception e) {
			result.setSuccess(false);
			throw e;
		} finally {
			try {
				fos.close();
				fis.close();
			} catch (Exception ioe) {
				logger.error(ioe.getMessage());
				// 10-Jul-2017 - Jayapradha - MEPSUAT-2017-0036_05- Start
				throw ioe;
				// 10-Jul-2017 - Jayapradha - MEPSUAT-2017-0036_05- End
			}
		}
		result.setSuccess(true);
    	return result;
    }
}
