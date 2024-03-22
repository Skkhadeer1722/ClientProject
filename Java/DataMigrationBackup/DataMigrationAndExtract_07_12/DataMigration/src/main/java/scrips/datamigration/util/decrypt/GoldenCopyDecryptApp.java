package scrips.datamigration.util.decrypt;

import java.io.Console;
import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import scrips.datamigration.decrypt.util.common.CommonConstant;
import scrips.datamigration.decrypt.util.common.StringUtil;
//import com.dataextract.util.decrypt.constant.DecryptAppConstant;
/**
 * Program Amendment History
 * 
 * @ver	    15-Feb-2017
 * @auth    Suma
 * @change  MEPSUAT-2017-0035_17 
 *  	    Sensitive Information Disclosure - Privacy Violation (Heap Inspection) 
 *  
 * @ver	    12-May-2021
 * @auth    Khin Su
 * @change  NSSIT-2020-0019 
 *  	    Changed to get the VERSION number from external properties
 *  
 * @ver		03-Jan-2022 
 * @auth	Khin Su
 * @change	MEPSSUP-2021-1164
 * 			change import log4j 1x to 2x
 */
@Service
public class GoldenCopyDecryptApp {
	final static Logger logger = LogManager.getLogger(GoldenCopyDecryptApp.class);
	@Autowired
	private Environment env;
	
	private String inputBasePath = "", outputBasePath = "";
	private String inputDataFolder = "", outputDataFolder = "";
	private String filePattern = "", cryptoAlgo = "";
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	//private String passCode = "", salt = "";
	private char[] passCode = null, salt = null;
	 // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
	private String userInput = "";
	private String userInputDirectory = "";
	//12-May-2021 - Khin su - NSSIT-2020-0019 - Start 
	private String versionNo = "";
	//12-May-2021 - Khin su - NSSIT-2020-0019 - End

	private HashMap<String, String> hmDirectory;
	Scanner scanIn = new Scanner(System.in);
	
	public String getInputBasePath() {
		return inputBasePath;
	}

	public void setInputBasePath(String inputBasePath) {
		this.inputBasePath = inputBasePath;
	}
	public String getOutputBasePath() {
		return outputBasePath;
	}

	public void setOutputBasePath(String outputBasePath) {
		this.outputBasePath = outputBasePath;
	}

	public String getInputDataFolder() {
		return inputDataFolder;
	}

	public void setInputDataFolder(String inputDataFolder) {
		this.inputDataFolder = inputDataFolder;
	}

	public String getOutputDataFolder() {
		return outputDataFolder;
	}

	public void setOutputDataFolder(String outputDataFolder) {
		this.outputDataFolder = outputDataFolder;
	}
	public String getFilePattern() {
		return filePattern;
	}

	public void setFilePattern(String filePattern) {
		this.filePattern = filePattern;
	}

	public String getCryptoAlgo() {
		return cryptoAlgo;
	}

	public void setCryptoAlgo(String cryptoAlgo) {
		this.cryptoAlgo = cryptoAlgo;
	}
	
	// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
	/*public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}*/
	//public String getSalt() {
	//	return salt;
	//}

	//public void setSalt(String salt) {
	//	this.salt = salt;
	//}
	public char[] getPassCode() {
		return passCode;
	}

	public void setPassCode(char[] passCode) {
		this.passCode = passCode;
	}	
	public char[] getSalt() {
		return salt;
	}

	public void setSalt(char[] salt) {
		this.salt = salt;
	} 
	 // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
	public String getUserInput() {
		return userInput;
	}
	
	public String getUserInputDirectory() {
		return userInputDirectory;
	}


	
	public void loadProperties() throws Exception{
		inputBasePath =  env.getProperty("remotefolderpath")+"/file";
		outputBasePath =  env.getProperty("remotefolderpath")+"/file";
		inputDataFolder = "MigrationFiles";
		outputDataFolder = "MigrationFiles";
		filePattern = env.getProperty("decryptFilePattern");
		cryptoAlgo = env.getProperty("cryptAlgo");
	}
	
	public void doDecryption(String fileName) throws Exception {
		loadProperties();
		
		DecryptFileGenerator decryptFileGenerator = new DecryptFileGenerator(filePattern, inputBasePath, "",
				inputDataFolder, outputBasePath, "", outputDataFolder, cryptoAlgo, CommonConstant.mixIt(env.getProperty("passCode").toCharArray()), env.getProperty("salt").toCharArray());
	
		decryptFileGenerator.generateDecryptFiles(fileName);
		decryptFileGenerator.showDecryptedSummary();
	}
	
	public void showEncryptedDirectory() throws Exception {
		logger.info("####################################################");
		logger.info("#                                                  #");
		//12-May-2021 - Khin su - NSSIT-2020-0019 - Start
		loadProperties();
		//logger.info("#             Decryption Utility (v" + GoldenCopyDecryptWithUnZippingApp.VERSION + ")            #");
		logger.info("#             Decryption Utility (v" + versionNo + ")            #");
		//12-May-2021 - Khin su - NSSIT-2020-0019 - End
		logger.info("#                                                  #");
		logger.info("####################################################");
		logger.info("");
		
		logger.info("Type the number or the directory name for decryption.");
		logger.info("----------------------");
		logger.info(StringUtil.rightPad(" ", "No.", 7) + "Directory Name");
		logger.info("----------------------");
		hmDirectory = new HashMap<String, String>();
		//12-May-2021 - Khin su - NSSIT-2020-0019 - Start
		//loadProperties();
		//12-May-2021 - Khin su - NSSIT-2020-0019 - End
		int folderCount = 0;
		
		File[] directories = getDirectoriesFromInputBasePath();
		
		for (File file : directories) {
			folderCount ++;
			logger.info(StringUtil.rightPad(" ", folderCount + "", 7) + file.getName());
			hmDirectory.put(folderCount + "", file.getName());
		}
		logger.info("----------------------");
//		logger.info("Enter Sequence No. or Directory name : ");
	}
	
	public File[] getDirectoriesFromInputBasePath() {
		File[] directories = new File(inputBasePath).listFiles(new FileFilter() {
		    @Override
		    public boolean accept(File file) {
		        return file.isDirectory();
		    }
		});
		return directories;
	}
	
	public void askPassCode() {
		Console console = System.console();
		char pw [] = console.readPassword("Enter pass code for decryption : ");
		 // 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start  
		// passCode = new String(pw);    
		// passCode = CommonConstant.mixIt(passCode);
		passCode = CommonConstant.mixIt(pw);
		// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End  
	}
	
	public void askSalt() {
		Console console = System.console();
		char salt [] = console.readPassword("Enter salt for decryption : ");

		// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- Start 
		//this.setSalt(new String(salt));
		this.setSalt(salt);
		// 15-Feb-2017 - Suma - MEPSUAT-2017-0035_17- End 
	}


	
	public void readConsole() {
		Console console = System.console();
		
		userInput = console.readLine("Enter Sequence No. or Directory name : ");
//		 userInput = scanIn.nextLine();
	}
	
	public void decryptViaUserInput() throws Exception {
		String inputTimeStampFolder = "";
		if (hmDirectory.containsKey(userInput)) {
			userInputDirectory = inputTimeStampFolder = hmDirectory.get(userInput);
		} else if (hmDirectory.containsValue(userInput)) {
			userInputDirectory = inputTimeStampFolder = userInput;
		} else {
			logger.info("Unable to locate folder for decryption");
			return;
		}
		doDecryption(inputTimeStampFolder);
	}
			
	public void showDirectoryAndDoDecryption() throws Exception {
		showEncryptedDirectory();
		readConsole();
		askPassCode();
		askSalt();
		decryptViaUserInput();
		passCode = null;
	}
	//12-May-2021 - Khin su - NSSIT-2020-0019 - Start 
	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	//12-May-2021 - Khin su - NSSIT-2020-0019 - End	
}
