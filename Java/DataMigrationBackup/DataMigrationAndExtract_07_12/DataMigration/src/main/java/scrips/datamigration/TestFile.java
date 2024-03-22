package scrips.datamigration;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.io.FileUtils;

public class TestFile {
public static void main(String[] args) {
//	String str="D:/DataMigrationNEW/DATA_MIGRATION_RTGS/DataMigrationAndExtract/DataMigration.aa";
//	
//	File file=new File("/New/abc/");
////	System.out.println(file.getTotalSpace());
////	System.out.println(file.getAbsolutePath());
////	System.out.println(file.getParent());
////	System.out.println(file.getPath());
////	System.out.println(file.getParentFile());
////System.out.println(FileNameUtils.getBaseName(str));
//	System.out.println(new File(file.getParent()));
//System.out.println(new File(file.getParent()).getParent());
//;
//System.out.println(FileNameUtils.getExtension(str));
//}
//	File f =new File("D://ACCOUNT_POSITION_FILE_UPLOAD.txt");
//	 Desktop desktop = null;
//	  if (Desktop.isDesktopSupported()) {
// 	    desktop = Desktop.getDesktop();
// 	}
// 	try {
// 	 desktop.open(f);
// 	} catch (IOException e) {
// 		e.printStackTrace();
// 		System.out.println("file is opened");
// 	}
//	
//	try {	
//		
//		BufferedReader b=new BufferedReader(new FileReader(f));
//		  String st;
//	        while ((st = b.readLine()) != null)
//	        {
//	            System.out.println(st);
//	        }
//	}catch (Exception e) {
//		e.printStackTrace();
//	}
	
	String s="MEPSPSCRTG00_cas_account_dm_20221125_150435";
	System.out.println(s.substring(s.length()-15));
	
}

}
