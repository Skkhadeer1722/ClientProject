/**
 @(#)FileUtil.java
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
*
*
*/


package scrips.datamigration.decrypt.util.common;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utilities class for File
 * @version  1.0.0 23-Apr-2016
 * @author   Win Htut Aung
 * 
 * @ver     09-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_13
 *		    Not aligned with Coding Best Practices - Unchecked Return Value 
 *
 * @ver		03-Jan-2022 
 * @auth	Khin Su
 * @change	MEPSSUP-2021-1164
 * 			change import log4j 1x to 2x
 */

public class FileUtil {
	//09-Feb-2017 - Suma -MEPSUAT-2017-0035_13 - Start	
	final static Logger logger = LogManager.getLogger(FileUtil.class);
	//09-Feb-2017 - Suma -MEPSUAT-2017-0035_13 - Start	
	/**
	 * Create a file with given file name and file content
	 * @param      fileName
	 * @param	   fileContent
	 * @return 		boolean
	 */
	public static boolean createFile(String fileName, String fileContent) throws IOException {
		boolean result = false;
		
		Path file = Paths.get(fileName);
		byte[] data = fileContent.getBytes();
	
		try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(
				file))) {
			out.write(data, 0, data.length);
			
			result = true;
		} catch (IOException x) {
			throw x;
		} 
		return result;
	}
	
	/**
	 * Create directories fot the given path
	 * @param      directory path
	 * @return 	   boolean
	 */
	public static boolean createDirectory(Path path) throws IOException {
		boolean result = false;
		
		Files.createDirectories(path);
		
		return result;
	}
	
	
	/**
	 * Create a file with given file name and file content
	 * @param      path file
	 * @param	   fileContent
	 * @return     boolean
	 */
	public static boolean createFile(Path file, String fileContent) throws IOException {
		boolean result = false;
		
		byte[] data = fileContent.getBytes();
		
		try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(
				file))) {
			out.write(data, 0, data.length);
			
			result = true;
		} catch (IOException x) {
			throw x;
		}
		return result;
	}
	
	/**
	 * Read the given file
	 * @param      fileName
	 * @return 	   fileContent
	 */
	public static String readFile(String fileName) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		
		Path file = Paths.get(fileName);
		Charset charset = Charset.forName("US-ASCII");

		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	sb.append(line);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}

		
		return sb.toString();
	}

	/**
	 * Delete the directory, sub files and sub directory for the given path
	 * @param      file path of the directory
	 * @return 	   boolean
	 */
	public static boolean deleteDirectory(File path) {
		if (path.exists() && path.isDirectory()) {
			File[] files = path.listFiles();
			for (int i=0 ; files != null && i<files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					//09-Feb-2017 - Suma -MEPSUAT-2017-0035_13 - Start
	                //files[i].delete();
	                boolean checkDelete = files[i].delete();
	                if(!checkDelete) 
	                {
	                	 logger.error("Unable to delete the File  :"+ files[i]);
	                }
	                //09-Feb-2017 - Suma -MEPSUAT-2017-0035_13 - End
					
				}
			}
		}
		return (path.delete());
	}
	
	/**
	 * Delete the given file 
	 * @param      file path
	 * @return	   boolean
	 */
	public static boolean deleteFile(File path) {
		if (path.exists() && !path.isDirectory()) {
			return path.delete();
		}
		return false;
	}
	
	/**
	 * Intelligent file size representation for bytes 
	 * @param      size in byte
	 * @return	   Intelligent string representation file size
	 */
	public static String getStringSizeLengthFile(long size) {

	    DecimalFormat df = new DecimalFormat("0.00");

	    float sizeKB = 1024.0f;
	    float sizeMB = sizeKB * sizeKB;
	    float sizeGb = sizeMB * sizeKB;
	    float sizeTerra = sizeGb * sizeKB;


	    if(size < sizeMB)
	        return df.format(size / sizeKB)+ " KB";
	    else if(size < sizeGb)
	        return df.format(size / sizeMB) + " MB";
	    else if(size < sizeTerra)
	        return df.format(size / sizeGb) + " GB";

	    return "";
	}
}
