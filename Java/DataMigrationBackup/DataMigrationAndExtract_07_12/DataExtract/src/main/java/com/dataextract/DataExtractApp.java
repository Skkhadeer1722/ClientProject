package com.dataextract;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dataextract.contloller.DataExtractController;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class DataExtractApp {
	private static  final Logger logger = LogManager.getLogger(PropertiesInitializer.class);
	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(DataExtractApp.class).initializers(new PropertiesInitializer())
				.build().run(args);
	
		try {
			if (args != null && args.length==1) {
				String name = args[0];
				if (name != null && !name.isEmpty()) {
					context.getBean(DataExtractController.class).extraction(name);
				} else {
					logger.error("argument should not be empty or null");
				}
			} else {
				logger.error("please Enter Extraction file you required: ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
