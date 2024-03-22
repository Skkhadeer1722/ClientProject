
package scrips.datamigration.contloller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import scrips.datamigration.common.CommonUtils;
import scrips.datamigration.report.GenerateReportService;

//import io.swagger.annotation.Api;

/**
 * @author Siva Kuruva
 */

@Controller
//@RequestMapping("/rtgs")
public class ReportController {
	
	@Autowired
	GenerateReportService generateReportService;
	@Autowired
	private Environment env;
	private final Logger logger = LogManager.getLogger(ReportController.class);
	//@GetMapping("/report/{format}")
    public String generateReport(String format,HttpServletResponse response,String iDate,String reportName) throws Exception {
    	CommonUtils.createFolder("reports",env.getProperty("remotefolderpath")+"/");
    	String decodedIdate = URLDecoder.decode(iDate, "UTF-8");
    	  String decodedreportName = URLDecoder.decode(reportName, "UTF-8");
    	 // System.out.println("decodedIdate=="+decodedIdate+"decoded report name=="+decodedreportName);
    	  logger.info("{} - {}",decodedIdate, decodedreportName);
        return generateReportService.exportReport(format, response,decodedIdate,decodedreportName);
    }

}