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

import com.jcraft.jsch.SftpException;

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.fileupload.JpaFileUploadDetails;
import scrips.datamigration.fileupload.JpaFileUploadHeader;

@Configuration
@Service
public class FileUploadService {
	private final Logger logger = LogManager.getLogger(FileUploadService.class);

	@Autowired
	private Environment env;

	@Autowired
	SftpService sftpService;

	public List<String> readFile(JpaFileUploadHeader fileHeaderObj, String fileUploadCode)
			throws SftpException, IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException,
			InterruptedException {
		List<String> fileRecords = new ArrayList<String>();
		logger.info("ftp status {}", env.getProperty("FTP.ENABLED"));
		String ftpStatus = env.getProperty("FTP.ENABLED");
		if (ftpStatus != null && ftpStatus.trim().equalsIgnoreCase("y")) {
			// Read file from FTP
			logger.info("FTP Enabled");
			try {
				fileRecords.clear();
				fileRecords = sftpService.readFileFromSFTPDirectroy(fileHeaderObj.getFilePath(),
						fileHeaderObj.getFileName());
			} catch (IOException | SftpException e) {
				logger.error("error while reading file from SFTP Directory {}", e.getMessage());
				throw e;
			}

		} else {
			try {
				logger.info("FTP Not Enabled,Reading files from MigrationFiles folder");
				InputStream fileStream = new BufferedInputStream(new FileInputStream(env.getProperty("remotefolderpath")
						+ "/" + "file/MigrationFiles/" + fileHeaderObj.getFileName()));
				Scanner sc = new Scanner(fileStream).useDelimiter("\\A");
				while (sc.hasNextLine()) {
					fileRecords.add(sc.nextLine());
				}
				sc.close();
			} catch (Exception e) {
				logger.error("error while parsing {} file {}", fileUploadCode, e.getMessage());
				throw e;
			}
		}
		return fileRecords;
	}

	public JpaFileUploadHeader getFileHeaderByCode(String fileUploadCode) {
		JpaFileUploadHeader fileUploadHeaderOut = new JpaFileUploadHeader();
		logger.info("getFileHeaderByCode entered");
		fileUploadHeaderOut.setIpAddress(env.getProperty("FTP.IP_ADDRESS"));
		fileUploadHeaderOut.setFilePath(env.getProperty("FTP.FILE_PATH"));
		fileUploadHeaderOut.setFileName(env.getProperty(fileUploadCode + ".FILE_NAME"));
		fileUploadHeaderOut.setLiveTableName(env.getProperty(fileUploadCode + ".LIVE_TABLE_NAME"));
		fileUploadHeaderOut.setFileDelimiter(env.getProperty("FILE_DELIMITER"));
		logger.info("fileUploadHeaderOut - {}", fileUploadHeaderOut.getLiveTableName());
		return fileUploadHeaderOut;
	}

	public List<JpaFileUploadDetails> getFileDetailsByCode(String fileUploadCode) {
		List<JpaFileUploadDetails> fileUploadDetails = new ArrayList<JpaFileUploadDetails>();
		String delimiter = CommonUtils.getDelimiterValue(env.getProperty("FILE_DELIMITER"));
		String[] liveTableFieldNames = env.getProperty(fileUploadCode + ".TABLE_FIELD_NAMES").split(delimiter);
		int seqNo = 1;
		for (String fieldname : liveTableFieldNames) {
			logger.info("fieldName  -  {}", fieldname);
			JpaFileUploadDetails fileUploadDetailObj = new JpaFileUploadDetails();
			fileUploadDetailObj.setSequenceNo(seqNo++);
			fileUploadDetailObj.setTableFieldName(fieldname);
			fileUploadDetails.add(fileUploadDetailObj);
		}
		logger.info("fileUploadDetails - " + fileUploadDetails);
		return fileUploadDetails;
	}

}
