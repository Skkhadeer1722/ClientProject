package scrips.datamigration.common.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.SftpException;

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;
import scrips.datamigration.rtgs.model.JpaMemberTemp;
import scrips.datamigration.rtgs.repository.MemberDAO;
import scrips.datamigration.rtgs.repository.MemberLiaisonDAO;
import scrips.datamigration.rtgs.repository.MemberTempDAO;

/**
 * @author Siva Kuruva
 */
@Configuration
@Service
public class FileUploadService {
	private final Logger logger = LogManager.getLogger(FileUploadService.class);
    @Autowired
    private Environment env;

    @Autowired
    MemberDAO memberRepo;

    @Autowired
    MemberTempDAO memberTempRepo;

    @Autowired
    MemberLiaisonDAO memberLisonRepo;

    @Autowired
    SftpService sftpService;

	public List<String> readFileFromFTP(JpaFileUploadHeader fileHeaderObj, String fileUploadCode) throws SftpException,IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, InterruptedException{
		List<String> fileRecords = new ArrayList<String>();
		logger.info("ftp status {}",env.getProperty("FTP.ENABLED"));
		String ftpStatus = env.getProperty("FTP.ENABLED");
		if (ftpStatus != null && ftpStatus.trim().equalsIgnoreCase("y")) {
			// Read file from FTP
			logger.info("FTP Enabled");
			try {
			fileRecords.clear();
			fileRecords = sftpService.readFileFromSFTPDirectroy(fileHeaderObj.getFilePath(),fileHeaderObj.getFileName());
			}catch (IOException|SftpException e) {
				logger.error("error while reading file from SFTP Directory {}", e.getMessage());
				throw e;
			}
			
		} else {
			try {
				logger.info("FTP Not Enabled,Reading files from MigrationFiles folder");
				InputStream fileStream = new BufferedInputStream(new FileInputStream(env.getProperty("remotefolderpath")+"/"+"file/MigrationFiles/" +fileHeaderObj.getFileName() ));
				Scanner sc = new Scanner(fileStream).useDelimiter("\\A");
				while (sc.hasNextLine()) {
					fileRecords.add(sc.nextLine());
				}
				sc.close();
			} catch (Exception e) {
				logger.error("error while parsing {} file {}",fileUploadCode, e.getMessage());
				throw e;
			}
		}
		return fileRecords;
	}
    public JpaFileUploadHeader getFileHeaderByCode(String fileUploadCode){
        JpaFileUploadHeader fileUpldHeaderOut = new JpaFileUploadHeader();
        logger.info("getFileHeaderByCode entered");
        fileUpldHeaderOut.setIpAddress(env.getProperty("FTP.IP_ADDRESS"));
        fileUpldHeaderOut.setFilePath(env.getProperty("FTP.FILE_PATH"));
        fileUpldHeaderOut.setFileName(env.getProperty(fileUploadCode+".FILE_NAME"));
        fileUpldHeaderOut.setLiveTableName(env.getProperty(fileUploadCode+".LIVE_TABLE_NAME"));
        fileUpldHeaderOut.setFileDelimiter(env.getProperty("FILE_DELIMITER"));
        logger.info("fileUpldHeaderOut - {}",fileUpldHeaderOut.getLiveTableName());
        return fileUpldHeaderOut;
    }

    public List<JpaFileUploadDetails> getFileDetailsByCode(String fileUploadCode){
        List<JpaFileUploadDetails> fileUpldDetailsOut = new ArrayList<JpaFileUploadDetails>();
        String delimiter=CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
		String[] liveTableFieldNames = env.getProperty(fileUploadCode+".TABLE_FIELD_NAMES").split(delimiter);
        int seqNo=1; 
        for(String fieldname:liveTableFieldNames){
            System.out.println(fieldname);
            JpaFileUploadDetails fileUpldDetObj = new JpaFileUploadDetails();
            fileUpldDetObj.setSequenceNo(seqNo++);
            fileUpldDetObj.setTableFieldName(fieldname);
            fileUpldDetailsOut.add(fileUpldDetObj);
        }

        System.out.println("fileUploadDetails - "+fileUpldDetailsOut);
        return fileUpldDetailsOut;
    }

    public static Object objectMapper(Object object){
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    	JpaMemberTemp memberTempObj = mapper.convertValue(object, JpaMemberTemp.class);
        return memberTempObj;
    }
}
