package scrips.datamigration.common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	
	public static boolean validateParseDate_yyyyMMdd(String dateStr) {
		try {
			new SimpleDateFormat("yyyyMMdd").parse(dateStr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean validateParseDate_yyyy_MM_dd(String dateStr) {
		try {
			new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean validateParseDate_yyyyMMdd_HHmmss(String dateStr) {
        try {
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	public static boolean validateParseDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean validateParseInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean validateParseLong(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static String getCurrentDateInString() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void createFolder(String folderName,String path) {
		if(path==null) {
		
		}else
		{
		String folder = path+folderName;
		File f1 = new File(folder);
		try {
			if (!Files.exists(Paths.get(folder))) {
				f1.mkdir();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
}