package com.dataextract.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.dataextract.common.CommonUtils;
import com.dataextract.encrypt.util.common.FileUtil;
import com.dataextract.util.encrypt.GoldenCopyEncryptApp;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

@Service
public class WriteFileService {

	@Autowired
	private SftpService sftpService;
	@Autowired
	private Environment env;
	@Autowired
	private EncryptionService encryptionService;

	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	private GoldenCopyEncryptApp goldenCopyEncryptApp;

	public void createFolder(List<String> s, String name) {
		// String filePath = env.getProperty("filepath");
		String newPath = "ExtractionedDataFiles";
		String folder = env.getProperty("remotefolderpath") + "/" + newPath;

		File f1 = new File(folder);
		try {
			if (!Files.exists(Paths.get(folder))) {
				f1.mkdir();
				createFile(f1, s, name);

			} else if (f1.exists()) {
				createFile(f1, s, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createFile(File createFile, List<String> s, String name) {
		File myObj = null;
		try {
			myObj = new File(createFile + "/" + name + ".txt");
			if (myObj.getName().equals(name + ".txt")) // returns Boolean value
			{
				myObj.delete();
				myObj.createNewFile();
				writeFile(myObj, s);

			} else if (!myObj.getName().equals(name + ".txt")) {
				myObj.createNewFile();
				writeFile(myObj, s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeFile(File createFolder, List<String> list) throws IOException {
		FileWriter writer = new FileWriter(createFolder);
		int size = list.size();
		int count = 0;
		for (String str : list) {
			count++;
			writer.write(str);

			if (size != count) // This prevent creating a blank like at the end of the file**
				writer.write("\n");
		}
		writer.close();

	}

	// creating hashfile base64
	public void encodeFileToBase64(String name, File input, String path) {
		File output = new File(path + ".hashfile");
		try {
			byte[] fileContent = Files.readAllBytes(input.toPath());
			FileOutputStream outputStream = new FileOutputStream(output);
			byte[] str = Base64.getEncoder().encodeToString(fileContent).getBytes(StandardCharsets.UTF_16);
			if (str != null) {
				outputStream.write(str);
			}
			outputStream.close();
		} catch (IOException e) {
			throw new IllegalStateException("could not read file " + input, e);
		}
	}

	public void doFileEncodeZipEncryptAndUpload(String name) {
		if (env.getProperty("FTP.ENABLED") != null
				&& env.getProperty("FTP.ENABLED").trim().equalsIgnoreCase("y")) {
			
		ChannelSftp channelSftp = null;
		String path = env.getProperty("remotefolderpath") + "/ExtractionedDataFiles/" + name;

		if (env.getProperty("ENCRYPTION.ENABLED") != null
				&& env.getProperty("ENCRYPTION.ENABLED").trim().equalsIgnoreCase("n")) {
			
			channelSftp = createChannelSftp();
			String filepath = checkAndCreateDir(env.getProperty("FTP.FILE_PATH"), channelSftp);

			try {
				sftpService.uploadFileToSftpDirectory(path + ".txt", filepath, name);
			} catch (SftpException e) {
				e.printStackTrace();
			} finally {
				sftpService.disconnectChannelSftp(channelSftp);
				File textFile = new File(path + ".txt");
				commonUtils.fileDelete(textFile);
			}
		} else {
			try {
				//creating hash file
				String hashString = hashFile(path + ".txt");
				FileUtil.createFile(path + ".hash", hashString);
				
				//zipping source file and hash file
				CreatZipFileWithSourceFileAndHashFiles(name, path);
				
				// encrypt zip file
				goldenCopyEncryptApp.doEncryption(name);
				
				File encryptedFile = new File(path + ".enc");
				File zipFile = new File(path + ".zip");
				
				//deleting source file
				commonUtils.fileDelete(new File(path + ".txt"));
				//deleting hash file
				commonUtils.fileDelete(new File(path + ".hash"));
				
				//deleting zip file
				commonUtils.fileDelete(new File(path + ".zip"));

				channelSftp = createChannelSftp();
				String filepath = checkAndCreateDir(env.getProperty("FTP.FILE_PATH"), channelSftp);
				if (env.getProperty("FTP.ENABLED") != null && env.getProperty("FTP.ENABLED").equalsIgnoreCase("y"))
					sftpService.uploadFileToSftpDirectory(path + ".enc", filepath, name);
				commonUtils.fileDelete(encryptedFile);
				commonUtils.fileDelete(zipFile);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sftpService.disconnectChannelSftp(channelSftp);
			}
		}
		}
	}

	public String checkAndCreateDir(String dir, ChannelSftp channelSftp) {
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

	public ChannelSftp createChannelSftp() {
		try {
			JSch jSch = new JSch();
			// Session session = jSch.getSession(username, host, port);
			Session session = jSch.getSession(env.getProperty("FTP.USER_NAME"), env.getProperty("FTP.IP_ADDRESS"),
					Integer.parseInt(env.getProperty("FTP.PORT")));
			session.setPassword(env.getProperty("FTP.PASSWORD"));
			session.setConfig("StrictHostKeyChecking", "no");
			// session.setPassword(password);
			session.connect(10000);
			Channel channel = session.openChannel("sftp");
			channel.connect(10000);
			return (ChannelSftp) channel;
		} catch (JSchException ex) {
//			logger.error("Create ChannelSftp error", ex);
		}

		return null;
	}

	public void CreatZipFileWithSourceFileAndHashFiles(String name, String path) {
		String zipPath = path + ".zip";
		String file1 = path + ".txt";
		String file2 = path + ".hash";
		FileInputStream fis1 = null;
		FileInputStream fis2 = null;
		try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath))) {
			File f1 = new File(file1);
			fis1 = new FileInputStream(f1);
			ZipEntry entry = new ZipEntry(f1.getName());
			zipOut.putNextEntry(entry);

			byte[] b = new byte[1024];
			int length;
			while ((length = fis1.read(b)) >= 0) {
				zipOut.write(b, 0, length);
			}
			zipOut.closeEntry();
			File f2 = new File(file2);
			fis2 = new FileInputStream(f2);
			entry = new ZipEntry(f2.getName());
			zipOut.putNextEntry(entry);

			while ((length = fis2.read(b)) >= 0) {
				zipOut.write(b, 0, length);
			}
			zipOut.closeEntry();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis1.close();
				fis2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
