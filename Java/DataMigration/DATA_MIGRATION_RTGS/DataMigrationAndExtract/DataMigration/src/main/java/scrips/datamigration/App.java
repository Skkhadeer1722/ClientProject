package scrips.datamigration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import lombok.extern.slf4j.Slf4j;
import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.controller.ReportController;
import scrips.datamigration.controller.ScripsFileUploadController;

@SpringBootApplication
@EnableConfigurationProperties
@EnableEncryptableProperties
@Slf4j
public class App {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ApplicationContext context = new SpringApplicationBuilder(App.class).initializers(new PropertiesInitializer())
				.build().run(args);
		try {
			if (args != null && args.length >= 2) {
				System.out.println("Argument-1: " + args[0]);
				String service = args[0];
				if (service != null && service.equalsIgnoreCase("datamigration")) {
					if (args.length == 2) {
						String reqType = args[1];
						if (reqType != null && !reqType.isEmpty())
							context.getBean(ScripsFileUploadController.class).migrateFile(reqType, null);
					} else {
						log.error("Invalid parameters count,expecting 2 parameters");
						return;
					}
				} else if (service != null && service.equalsIgnoreCase("report")) {
					if (args.length == 4 || args.length == 3) {
						if (args[1].equalsIgnoreCase("pdf") || args[1].equalsIgnoreCase("html")) {
							if (args.length == 4) {
								String reqType = args[1];
								String reportName = args[2];
								String date = args[3];
								if (CommonUtils.validateParseDate_yyyy_MM_dd(date))
									context.getBean(ReportController.class).generateReport(reqType, date, reportName);
								else
									log.error("Invalid date format(should be yyyy-MM-dd) :" + date);
							} else {
								String reqType = args[1];
								String reportName = args[2];
//							String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
								context.getBean(ReportController.class).generateReport(reqType, " ", reportName);
							}
						} else {
							log.error("Invalid Report Format should be either pdf or html");
							return;
						}
					} else {
						log.error("Invalid parameters count,expecting 3 or 4 parameters");
						return;
					}
				} else if (service != null) {
					log.error("Invalid service name: " + service);
				}
			} else {
				log.error("Expecting atleast 2 arguments for migration and 3 arguments for report");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		long millis=end-start;
		
	    log.info("Process completed in (Hours:Minutes:Seconds) {}:{}:{}", TimeUnit.MILLISECONDS.toHours(millis),
	    		TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
	    		TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
	}

}