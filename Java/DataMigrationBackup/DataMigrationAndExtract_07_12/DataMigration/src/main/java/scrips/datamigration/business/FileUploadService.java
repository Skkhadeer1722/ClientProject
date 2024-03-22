package scrips.datamigration.business;

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

import scrips.datamigration.jpa.fileupload.FileUploadErrorRepository;
import scrips.datamigration.jpa.fileupload.FileUploadExecutionRepository;
import scrips.datamigration.jpa.fileupload.FileUploadRepository;
import scrips.datamigration.jpa.fileupload.FileUploadValidationRepository;
import scrips.datamigration.jpa.fileupload.JpaFileUploadDetails;
import scrips.datamigration.jpa.fileupload.JpaFileUploadError;
import scrips.datamigration.jpa.fileupload.JpaFileUploadExecution;
import scrips.datamigration.jpa.fileupload.JpaFileUploadHeader;
import scrips.datamigration.jpa.member.JpaMember;
import scrips.datamigration.jpa.member.JpaMemberTemp;
import scrips.datamigration.jpa.member.MemberDAO;
import scrips.datamigration.jpa.member.MemberLiaisonDAO;
import scrips.datamigration.jpa.member.MemberTempDAO;

/**
 * @author Siva Kuruva
 */
@Configuration
//@PropertySource("file:C:\\DataMigrationService\\config\\fileupload.properties")
@Service
public class FileUploadService {
	private final Logger logger = LogManager.getLogger(FileUploadService.class);
    @Autowired
    private Environment env;

    @Autowired
    FileUploadRepository fileUploadRepo;

    @Autowired
    FileUploadValidationRepository fileUploadDetailsRepo;
    
    @Autowired
    MemberDAO memberRepo;

    @Autowired
    MemberTempDAO memberTempRepo;

    @Autowired
    MemberLiaisonDAO memberLisonRepo;

    @Autowired
    FileUploadExecutionRepository fileExecRepo;

    @Autowired
    FileUploadErrorRepository fileErrorRepo;

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
        // if(fileUploadCode!=null){  
        //     fileUpldHeaderOut=fileUploadRepo.findByFileUploadCode(fileUploadCode);
        // }
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
        // if(fileUploadCode!=null)
        //     fileUpldDetailsOut=fileUploadDetailsRepo.getFileUploadDetails(fileUploadCode);

        String[] liveTableFieldNames = env.getProperty(fileUploadCode+".TABLE_FIELD_NAMES").split("\\|");
        int seqNo=1;
        // for(int i=0; i< liveTableFieldNames.length; i++)  
        // {  
        //     System.out.println(liveTableFieldNames[i]);  
        // }  
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

    public void saveMember(List<JpaMember> memberObj){
        if(memberObj!=null){
            memberRepo.saveAll(memberObj);
            System.out.println("Completed MEMBER save operation");
        }
    }

    public void saveMemberTemp(List<JpaMemberTemp> memberObj){
        if(memberObj!=null){
            memberTempRepo.saveAll(memberObj);
            System.out.println("Completed MEMBER_TEMP save operation");
        }
    }

    public void saveMemberLiaison(List<JpaMemberTemp> memberObj){
        if(memberObj!=null){
            memberTempRepo.saveAll(memberObj);
            System.out.println("Completed MEMBER_TEMP save operation");
        }
    }

    public void saveFileUploadExecutions(List<JpaFileUploadExecution> fileUploadExecObj){
        if(fileUploadExecObj!=null){
            fileExecRepo.saveAll(fileUploadExecObj);
            System.out.println("Completed FILE_UPLOAD_EXECUTION save operation");
        }
    }

    public void saveFileUploadErrors(List<JpaFileUploadError> fileUploadErrorObj){
        if(fileUploadErrorObj!=null) {
            fileErrorRepo.saveAll(fileUploadErrorObj);
            System.out.println("Completed FILE_UPLOAD_ERROR save operation");
        }
    }


    public static Object objectMapper(Object object){
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    	JpaMemberTemp memberTempObj = mapper.convertValue(object, JpaMemberTemp.class);
        return memberTempObj;
    }
}
