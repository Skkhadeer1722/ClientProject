package com.dataextract;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	private final Logger logger = LogManager.getLogger(PropertiesInitializer.class);

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		// TODO Auto-generated method stub
		ConfigurableEnvironment configEnv = applicationContext.getEnvironment();
		MutablePropertySources propertySources = configEnv.getPropertySources();
		MapPropertySource propMap = new MapPropertySource("DataMigrationProperties", loadProperties());
		propertySources.addLast(propMap);
		configEnv.setDefaultProfiles("app");
	}

	private Map<String, Object> loadProperties() {
		Map<String, Object> allProperties = new HashedMap<>();
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(new FileSystemResource("DataExtraction.properties"));
		} catch (IOException ioException) {
			logger.error("Exception while loading the ParcelSystem.properties, Error = {}", ioException.getMessage());
			ioException.printStackTrace();
		}
		if (props != null) {
			props.forEach((key, value) -> {
				allProperties.put((String) key, value);
			});
		}

		try {
			props = PropertiesLoaderUtils
					.loadProperties(new FileSystemResource(getRemoteFolderPath() + "config/application.properties"));
		} catch (IOException ioException) {
			logger.error("Exception while loading the application.properties, Error = {}",
					ioException.getMessage());
			ioException.printStackTrace();
		}
		if (props != null) {
			props.forEach((key, value) -> {
				allProperties.put((String) key, value);
			});
		}

	
		try {
			props = PropertiesLoaderUtils.loadProperties(new FileSystemResource(
					getRemoteFolderPath() + "config/FileName.properties"));
		} catch (IOException ioException) {
			logger.error("Exception while loading the FileName.properties, Error = {}",ioException.getMessage());
			ioException.printStackTrace();
		}
		if (props!=null) {
			props.forEach((key, value) -> {
//				logger.info("key {} - value {}",(String) key, value);
				allProperties.put((String) key, value);
			});
		}
		
		return allProperties;

	}

	private String getRemoteFolderPath() {
		String remoteFolderPath = "";
		Properties prop = new Properties();
		try {
			File file = new File("DataExtraction.properties");
			if (file.exists()) {
				prop.load(new FileSystemResource(file).getInputStream());
				if (prop.get("remotefolderpath") == null || prop.get("remotefolderpath").equals("")) {

					logger.error("remotefolderpath property cannot be empt");
				} else {
					remoteFolderPath = (String) prop.get("remotefolderpath");
					remoteFolderPath = appendFileSeperator(remoteFolderPath);
				}
			} else {

				logger.error("DataExtraction.properties file does not exists");
			}
		} catch (Exception e) {
			logger.error("Unable to get the remotefolderpath, Error = {}", e.getMessage());
			e.printStackTrace();
		}
		return remoteFolderPath;
	}

	public String appendFileSeperator(String fileOrDirName) {
		if (!fileOrDirName.endsWith(System.getProperty("file.separator")))
			fileOrDirName += System.getProperty("file.separator");
		return fileOrDirName;
	}

}
