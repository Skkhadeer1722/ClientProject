package scrips.datamigration.common;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import scrips.datamigration.fileupload.JpaFileUploadDetails;

public class CommonUtils {
	private final static Logger logger = LogManager.getLogger(CommonUtils.class);

	public static boolean isNullOrEmpty(String field) {
		try {
			if (field == null || field.isBlank() || field.isEmpty()) {
				return false;
			} else
				return true;
		} catch (Exception e) {
			return false;
		}
	}

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

	public static void createFolder(String folderName, String path) {
		if (path == null) {

		} else {
			String folder = path + folderName;
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

	public static BigDecimal convertStringToDecimal(String str, int intLength, int decimalLength) {
		String decimalStr = new String();
		try {
			if (str.length() > intLength)
				return null;
			decimalStr = str.substring(0, (str.length() - decimalLength)) + "."
					+ str.substring((str.length() - decimalLength));
		} catch (Exception e) {
//			decimalStr = "0";
			while (e.getCause() != null)
				e = (Exception) e.getCause();
			logger.error(" ", e.getMessage());
			return null;
		}
		return new BigDecimal(decimalStr);
	}

	public static int getNumberOfColumns(String str, String delimiter) {
		return str.length() - str.replaceAll(delimiter, "").length();

	}

	public static String getDelimiterValue(String delimiter) {
		return delimiter.equals("|") ? "\\|" : delimiter;
	}

	public static String validateRawData(String line, int count, int dbDetailsCount, String delimiter) {
		if (line == null || line.trim().isEmpty()) {
			return "No columns present";
		}

		int contentLength = CommonUtils.getNumberOfColumns(line, delimiter);
		if (contentLength != count) {
			return ("Invalid number of columns received");

		}
		if (contentLength + 1 != dbDetailsCount) {
			return ("Header columns count in file upload properties not matching with source");

		}
		return null;
	}

	public static Map<String, String> getDataMap(List<JpaFileUploadDetails> draftDBDetails, String line,
			String delimiter) {
		Map<String, String> dbRecordsMap = new HashMap<>();
		try {
			List<String> content = Stream.of(line.split(delimiter)).map(String::trim).collect(Collectors.toList());

			boolean lastColumnDataIsEmpty = draftDBDetails.size() != content.size();

			int count = 0;
			for (JpaFileUploadDetails db : draftDBDetails) {
				if (lastColumnDataIsEmpty && count >= content.size()) {
//				logger.info("{} - {} -{}", db.getTableFieldName(), null, 0);
					dbRecordsMap.put(db.getTableFieldName(), null);
					count++;
				} else {
//				logger.info("{} - {} -{}", db.getTableFieldName(), content.get(count),	content.get(count).length());
					dbRecordsMap.put(db.getTableFieldName(), content.get(count));
					count++;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return dbRecordsMap;
	}

}