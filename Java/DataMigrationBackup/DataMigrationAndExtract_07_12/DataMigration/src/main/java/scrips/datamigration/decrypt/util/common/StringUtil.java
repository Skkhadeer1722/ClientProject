package scrips.datamigration.decrypt.util.common;

public class StringUtil {
	/**
	 * Static method which pads the input string with n number of leading
	 * character c, up to the required length.
	 * 
	 * @param Padding
	 *            character
	 * @param Input
	 *            String
	 * @param Length
	 *            of final output string
	 * @return Padded Output String
	 */
	public static String leftPad(String strPadWith, String strField, int nLength) {
		String strTemp = "";

		if (strField == null) {
			strField = "";
		}

		for (int i = strField.length(); i < nLength; i++) {
			strTemp += strPadWith;
		}

		strField = strTemp + strField;

		return strField;
	}

	/**
	 * Static method which pads the input string with n number of trailing
	 * character c, up to the required length.
	 * 
	 * @param Padding
	 *            character
	 * @param Input
	 *            String
	 * @param Length
	 *            of final output string
	 * @return Padded Output String
	 */
	public static String rightPad(String strPadWith, String strField,
			int nLength) {
		String strTemp = "";

		if (strField == null) {
			strField = "";
		}

		for (int i = strField.length(); i < nLength; i++) {
			strTemp += strPadWith;
		}

		strField = strField + strTemp;

		return strField;
	}
}
