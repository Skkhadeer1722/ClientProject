package scrips.datamigration.common.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.decrypt.util.common.FileUtil;
import scrips.datamigration.util.decrypt.GoldenCopyDecryptApp;

@Service
public class SftpService {
	private final Logger logger = LogManager.getLogger(SftpService.class);

	@Autowired
	private Environment env;

	@Autowired
	private GoldenCopyDecryptApp goldenCopyDecryptApp;
	public List<String> readFileFromSFTPDirectroy(String remoteFilePath, String remoteFileName)
			throws SftpException, IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException,
			InterruptedException {

		List<String> fileRecords = new ArrayList<String>();
		ChannelSftp channelSftp = createChannelSftp();
		if(channelSftp !=null) {
		String fileNameWithPath = remoteFilePath + "/" + remoteFileName;
		String fileNameWithoutExt = FileNameUtils.getBaseName(fileNameWithPath);

		logger.info("Encryption status {}", env.getProperty("ENCRYPTION.ENABLED"));
		String encryptionStatus = env.getProperty("ENCRYPTION.ENABLED");
		String hashVerificationRequired = env.getProperty("HASHFILE_VERIFICATION.ENABLED");

		if (encryptionStatus != null && encryptionStatus.trim().equalsIgnoreCase("y")) {
			CommonUtils.createFolder("file/MigrationFiles", env.getProperty("remotefolderpath") + "/");
			logger.info("Encryption Enabled");
			try {
				String downloadPath = env.getProperty("remotefolderpath") + "/" + "/file/MigrationFiles/";
				String migrationFileNameWithLocalPath = downloadPath + fileNameWithoutExt;
				channelSftp.get(fileNameWithPath, downloadPath);
				
				//decrypting  the enc file 
				goldenCopyDecryptApp.doDecryption(fileNameWithoutExt);
				
				// unzipping the zip file
				unZipFiles(new File(migrationFileNameWithLocalPath+".zip"), downloadPath);
				//	Thread.sleep(50000);

				File decryptedTextFile = new File(migrationFileNameWithLocalPath + ".txt");
				
				if (hashVerificationRequired != null && hashVerificationRequired.trim().equalsIgnoreCase("y")) {
					String newHash= hashFile(migrationFileNameWithLocalPath + ".txt");
					String oldHashValue = FileUtil.readFile(migrationFileNameWithLocalPath + ".hash");
					if(!newHash.equals(oldHashValue)){
						System.out.println("both hashfiles are different");
						try {
							throw new IOException("File Corrupted");
						} finally {
							fileDelete(decryptedTextFile);
							//fileDelete(oldHashFile);
							//fileDelete(decrypted);
							//fileDelete(encrypted);
							//fileDelete(newHashFile);
							disconnectChannelSftp(channelSftp);
						}
					} else {
						System.out.println("both hashfiles are same");
					}
				}
					try {
						InputStream fileStream = new BufferedInputStream(
								new FileInputStream(migrationFileNameWithLocalPath + ".txt"));
						Scanner s = new Scanner(fileStream).useDelimiter("\\A");
						while (s.hasNextLine()) {
							fileRecords.add(s.nextLine());
						}
						fileStream.close();
						s.close();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						fileDelete(decryptedTextFile);
						//fileDelete(oldHashFile);
					//	fileDelete(decrypted);
						//fileDelete(encrypted);
						//fileDelete(newHashFile);
						disconnectChannelSftp(channelSftp);
					}
					return fileRecords;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("File not present in path");
			} finally {
				disconnectChannelSftp(channelSftp);
			}
			return null;
		} else {
			logger.info("File Name with Path {}", fileNameWithPath);
			try {
				InputStream i = channelSftp.get(fileNameWithPath);
				Scanner sc = null;
				try {
					sc = new Scanner(i).useDelimiter("\\A");
					while (sc.hasNextLine()) {
						fileRecords.add(sc.nextLine());
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					sc.close();
					disconnectChannelSftp(channelSftp);
				}
			return fileRecords;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println();
			} finally {
				disconnectChannelSftp(channelSftp);
			}	
		}
	} else {
		logger.error("Error connecting SFTP server,please check the SFTP properties and run again");
		System.exit(0);
	}
		return null;
	}

	public void fileDelete(File fileName) {
		if (fileName.exists()) {
			fileName.delete();
		} else
			System.out.println("file not found");

	}

	public void uploadFileToSftpDirectory(File localFile, String remoteSftpDir, ChannelSftp channelSftp)
			throws SftpException {

		if (localFile.exists()) {
			channelSftp.put(localFile.toString(), remoteSftpDir);
		} else
			System.out.println("file not found");

	}

	public ChannelSftp createChannelSftp() {
		Channel channel = null;
		try {
			JSch jSch = new JSch();
			// Session session = jSch.getSession(username, host, port);
			Session session = jSch.getSession(env.getProperty("FTP.USER_NAME"), env.getProperty("FTP.IP_ADDRESS"),
					Integer.parseInt(env.getProperty("FTP.PORT")));
			session.setPassword(env.getProperty("FTP.PASSWORD"));
			session.setConfig("StrictHostKeyChecking", "no");
			// session.setPassword(password);
			session.connect(10000);
			channel = session.openChannel("sftp");
			channel.connect(10000);
			return (ChannelSftp) channel;
		} catch (JSchException ex) {
			logger.error("Create ChannelSftp error {}", ex.getLocalizedMessage());
			disconnectChannelSftp((ChannelSftp) channel);
		}

		return null;
	}

	public void unZipFiles(File output, String downloadPath) throws IOException {

		File save = new File(downloadPath);
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(output));
		ZipEntry entry = zipIn.getNextEntry();
		while (entry != null) {
			String filePath = save + File.separator + entry.getName();
			if (!entry.isDirectory()) {
				extractFile(zipIn, filePath);
			} else {
				File dir = new File(filePath);
				dir.mkdirs();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
	}

	private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[4096];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}


	public void moveSourcefileBasedOnMigrationStatus(boolean status, String remoteFilePath, String remoteFileName)
			throws IOException {
		ChannelSftp channelSftp = createChannelSftp();
		try {
			String newPath = new File(remoteFilePath).getParent();
//			String newPath = new File(remoteFilePath).getPath();
			newPath = newPath.equals("\\") ? "" : newPath;
			String ext = FileNameUtils.getExtension(remoteFileName);
			if (status) {
				checkAndCreateDir(env.getProperty("FTP.FILE_PATH_OK") + "/OK", channelSftp);
				moveFileToSpecificDir(channelSftp, remoteFilePath, newPath + "/OK/",
						addDateTimeToFileName(remoteFileName, ext), remoteFileName);
			} else {
				checkAndCreateDir(env.getProperty("FTP.FILE_PATH_ERROR") + "/ERROR", channelSftp);
				moveFileToSpecificDir(channelSftp, remoteFilePath, newPath + "/ERROR/",
						addDateTimeToFileName(remoteFileName, ext), remoteFileName);
			}
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			disconnectChannelSftp(channelSftp);
		}
	}

	private String checkAndCreateDir(String dir, ChannelSftp channelSftp) {
		try {
			String currentDirectory = channelSftp.pwd();

			SftpATTRS attrs = null;
			try {
				attrs = channelSftp.stat(currentDirectory + "/" + dir);
			} catch (Exception e) {
				System.out.println(currentDirectory + "/" + dir + " not found");
			}

			if (attrs != null) {
				System.out.println("Directory exists IsDir=" + attrs.isDir());
			} else {
				System.out.println("Creating dir " + dir);
				channelSftp.mkdir(dir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dir;
	}

	private void moveFileToSpecificDir(ChannelSftp channelSftp, String FileDirectory, String newDir, String newFile,
			String existingfile) throws SftpException {
		channelSftp.cd(FileDirectory);
		if (channelSftp.get(existingfile) != null) {
			channelSftp.rename(FileDirectory + "/" + existingfile, newDir + newFile);
		}
	}

	private String addDateTimeToFileName(String fileName, String extension) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String date = sdf.format(new Date());
		return fileName.substring(0, fileName.lastIndexOf(".")) + "_" + date + "." + extension;
	}

	public void disconnectChannelSftp(ChannelSftp channelSftp) {
		try {
			if (channelSftp == null)
				return;

			if (channelSftp.isConnected())
				channelSftp.disconnect();

			if (channelSftp.getSession() != null)
				channelSftp.getSession().disconnect();

		} catch (Exception ex) {
			logger.error("SFTP disconnect error", ex);
		}
	}
	
	public String hashFile(String fileName) throws NoSuchAlgorithmException, IOException {
		StringBuffer sbHashString = new StringBuffer();
		String hashString = "";
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		FileInputStream fis = new FileInputStream(fileName);
		
		byte [] dataBytes = new byte[1024];
		
		int nread = 0;
		try {
			while ((nread = fis.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}
			byte[] mdbytes = md.digest();
			
			
			for (int i=0; i < mdbytes.length; i++) {
				sbHashString.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			fis.close();
		} catch (IOException e) {
			throw e;
		} finally {
			fis.close();
			fis = null;
		}
		
		hashString = sbHashString.toString();
		return hashString;
	}

}
