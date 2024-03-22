package com.dataextract.service;

import org.apache.commons.compress.utils.FileNameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Service
public class SftpService {

	private Logger logger = LoggerFactory.getLogger(SftpService.class);

	@Autowired
	private Environment env;

	public void uploadFileToSftpDirectory(String localFile, String remoteDir, String name) throws SftpException {
		ChannelSftp channelSftp = null;
		try {
			channelSftp = createChannelSftp();
			logger.info("CHANNEL STATUS {}", channelSftp.getExitStatus());

			String ext = FileNameUtils.getExtension(localFile);
//			String dir = addDateTimeToFileName(name, ext);
			channelSftp.put(localFile, remoteDir + name + "." + ext);
		} catch (SftpException ex) {
			ex.printStackTrace();
			logger.error("Error uploading {} file from SFT directory {}", ex.getMessage());
			throw ex;
		} finally {
			disconnectChannelSftp(channelSftp);
		}
	}

//	private String addDateTimeToFileName(String name, String ext) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
//		String date = sdf.format(new Date());
//		return name + "_" + date + "." + ext;
//	}

	private ChannelSftp createChannelSftp() {
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
			logger.error("Create ChannelSftp error", ex);
		}

		return null;
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

}
