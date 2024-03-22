package scrips.datamigration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
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
		ConfigurableEnvironment configEnv = applicationContext.getEnvironment();
		MutablePropertySources propertySources = configEnv.getPropertySources();
		MapPropertySource propMap = new MapPropertySource("DataMigrationProperties", loadProperties());
		propertySources.addLast(propMap);
		configEnv.setDefaultProfiles("app");
		configureUserDefLogPath(propMap.getProperty("log.config.file.path").toString(),
				propMap.getProperty("log.file.path").toString());
	}

	private Map<String, Object> loadProperties() {
		Map<String, Object> allProperties = new HashedMap<>();
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(new FileSystemResource("DataMigration.properties"));
		} catch (IOException ioException) {
			logger.error("Exception while loading the DataMigration.properties, Error = {}", ioException.getMessage());
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
			logger.error("Exception while loading the application.properties, Error = {}", ioException.getMessage());
			ioException.printStackTrace();
		}
		if (props != null) {
			props.forEach((key, value) -> {
				allProperties.put((String) key, value);
			});
		}

		try {
			props = PropertiesLoaderUtils
					.loadProperties(new FileSystemResource(getRemoteFolderPath() + "config/fileupload.properties"));
		} catch (IOException ioException) {
			logger.error("Exception while loading the fileupload.properties, Error = {}", ioException.getMessage());
			ioException.printStackTrace();
		}
		if (props != null) {
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
			File file = new File("DataMigration.properties");
			if (file.exists()) {
				prop.load(new FileSystemResource(file).getInputStream());
				if (prop.get("remotefolderpath") == null || prop.get("remotefolderpath").equals("")) {
					logger.info("Remote Folder Path-{}", "remotefolderpath property cannot be empty");
				} else {
					remoteFolderPath = (String) prop.get("remotefolderpath");
					remoteFolderPath = appendFileSeperator(remoteFolderPath);
				}
			} else {
				logger.error("Properties-{}", "DataMigration.properties file does not exists");
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

	private String LOG_FILE_PATH = "logfilepath";

	public void configureUserDefLogPath(String log4jXmlPath, String logGenPath) {
		try {
			System.setProperty(LOG_FILE_PATH, "");
			LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
			String userDefinedLoggingPath = getRemoteFolderPath();
			if (Optional.ofNullable(userDefinedLoggingPath).isPresent() && !"".equals(userDefinedLoggingPath.strip())) {
				userDefinedLoggingPath = userDefinedLoggingPath + File.separator;
				System.setProperty(LOG_FILE_PATH, logGenPath);
				context.setConfigLocation(Path.of(userDefinedLoggingPath + log4jXmlPath).toUri());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
