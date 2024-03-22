package scrips.datamigration.regex;

import java.util.Arrays;
import java.util.Optional;

public enum RegexEnum {
	LENGTH1("length1", "^.{0,1}$", "Field length more then 1"),
	LENGTH3("length3", "^.{0,3}$", "Field length more then 3"),
	LENGTH4("length4", "^.{0,4}$", "Field length more then 4"),
	LENGTH5("length5", "^.{0,5}$", "Field length more then 5"),
	LENGTH6("length6", "^.{0,6}$", "Field length more then 6"),
	LENGTH8("length8", "^.{0,8}$", "Field length more then 8"),
	LENGTH9("length9", "^.{0,9}$", "Field length more then 9"),
	LENGTH10("length10", "^.{0,10}$", "Field length more then 10"),
	LENGTH11("length11", "^.{0,11}$", "Field length more then 11"),
	LENGTH12("length12", "^.{0,12}$", "Field length more then 12"),
	LENGTH13("length13", "^.{0,13}$", "Field length more then 13"),
	LENGTH14("length14", "^.{0,14}$", "Field length more then 14"),
	LENGTH16("length16", "^.{0,16}$", "Field length more then 16"),
	LENGTH18("length18", "^.{0,18}$", "Field length more then 18"),
	LENGTH19("length19", "^.{0,19}$", "Field length more then 19"),
	LENGTH20("length20", "^.{0,20}$", "Field length more then 20"),
	LENGTH26("length26", "^.{0,26}$", "Field length more then 26"),
	LENGTH28("length28", "^.{0,28}$", "Field length more then 28"),
	LENGTH30("length30", "^.{0,30}$", "Field length more then 30"),
	LENGTH34("length34", "^.{0,34}$", "Field length more then 34"),
	LENGTH35("length35", "^.{0,35}$", "Field length more then 35"),
	LENGTH36("length36", "^.{0,36}$", "Field length more then 36"),
	LENGTH45("length45", "^.{0,45}$", "Field length more then 45"),
	LENGTH50("length50", "^.{0,50}$", "Field length more then 50"),
	LENGTH100("length100", "^.{0,100}$", "Field length more then 100"),
	ASCII("ascii", "^\\p{ASCII}*$", "Field not ASCII character set"),
	NUMERIC("numeric", "^[0-9]+$", "Field not numeric"), BOOLEAN("boolean", "^[0-1]{1}$", "Field not boolean"),
	SPECIAL("special", "^[A-Za-z0-9]+$", "Field contains not allowed special characters"),
	CURRENCYCODE("currencycode", "^(\\bSGD\\b)+$", "Field contains not allowed currency code"),
	ACCOUNTTYPE("accounttype",
			"^(\\bAGDA\\b|\\bCCAC\\b|\\bRESV\\b|\\bSETT\\b|\\bVOST\\b|\\bRSUS\\b|\\bRCTL\\b|\\bCOSA\\b|\\bCSUS\\b|\\bCOCA\\b|\\bCCTL\\b)+$",
			"Field contains not allowed account type"),
	ACCOUNTSTATUS("accountstatus", "^(\\bACTIVE\\b)+$", "Field contains not allowed account status"),
	
//	DECIMAL("decimal", "^\\d*\\.?\\d*$", "Field not decimal"),
	DECIMAL("decimal", "^\\d+\\.\\d+||\\.\\d+||\\d+\\.||\\d+$", "Field not decimal"),
	DECIMAL5("decimal_5","([0-9]{0,3})?[.]([0-9]{0,2})?||[0-9]{0,3}","Field length should be 5,2"),
	DECIMAL8("decimal_8","([0-9]{0,3})?[.]([0-9]{0,5})?||[0-9]{0,3}","Field length should be 8,5"),
	DECIMAL9("decimal_9","([0-9]{0,4})?[.]([0-9]{0,5})?||[0-9]{0,4}","Field length should be 9,5"),
	DECIMAL18("decimal_18","([^.]?||[0-9]{0,16})?[.]([0-9]{0,2})?||[0-9]{0,16}","Field length should be 18,2"),
	DECIMAL23("decimal_23","([0-9]{0,18})?[.]([0-9]{0,5})?||[0-9]{0,18}","Field length should be 23,5"),
	DECIMAL13("decimal_13","([0-9]{0,3})?[.]([0-9]{0,10})?||[0-9]{0,3}","Field length should be 13,10"),
	
	ALPHABETSWITHSPACE("alphabetswithspace", "^[A-Za-z\\s]+$",
			"Field contains not allowed special characters except space"),
	SPECIALWITHDOTS("specialwithdots", "^[A-Za-z0-9.]+$",
			"Field contains not allowed special characters except dot"),
	ALPHABETSWITHUNDERSCORE("alphabetswithunderscore", "^[A-Za-z_]+$",
			"Field contains not allowed special characters except underscore"),
	Id("Id", "^[A-Za-z0-9-]+$", "Field contains not allowed special characters except hypens");

	private final String regexName;
	private final String regexPattern;
	private final String errorDescription;

	RegexEnum(String regexName, String regexPattern, String errorDescription) {
		this.regexName = regexName;
		this.regexPattern = regexPattern;
		this.errorDescription = errorDescription;
	}

	public String getName() {
		return regexName;
	}

	public String getPattern() {
		return regexPattern;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	// Reverse lookup methods
	public static Optional<RegexEnum> getRegeXValue(String value) {
		return Arrays.stream(RegexEnum.values()).filter(regexValue -> regexValue.regexName == value).findFirst();
	}
}