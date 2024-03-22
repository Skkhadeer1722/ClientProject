package scrips.datamigration;

import java.math.BigDecimal;

public class TestFile {
	public static void main(String[] args) {
//	String str="MASGSGSG|21110223|Settlement Account|SETT|Y|Y||Active|SGD|NO_STATEMENT|ctgy1|cost1|123|";
////     s.replaceAll(s, "//|");
//	
//     Map<String, Long> result = Arrays.stream(str.split("")).map(String::toLowerCase)
//    		 .collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()));  
//     System.out.println(result);  

//     String regex = "^\\d*\\.?\\d+|\\d+\\.\\d*$";
//     String regex = "^\\d*\\.?\\d*$";
//     Pattern pattern = Pattern.compile(regex);
//
//   String d=".0";
//   if(d.matches(regex))
//	    System.out.println("true");
//	else
//	    System.out.println("false");

//	String d1=BigDecimal.valueOf(securitiesPositionStats.getOpeningNominalAmount()).toPlainString();
//	securitiesPositionStats.setOpeningNominalAmount(Double.parseDouble(d1));

		Double d = 1.23456789123456784E17;
//    		9.02799998E11;
//	System.out.println(BigDecimal.valueOf(d).toPlainString());

		System.out.println(convertStringToDecimal("002150001", 8, 5));

	}

	public static BigDecimal convertStringToDecimal(String str, int intLength, int decimalLength) {
		String decimalStr = new String();
		try {
			if (str.length() > intLength)
				return null;
			decimalStr = str.substring(0, (str.length() - decimalLength)) + "."
					+ str.substring((str.length() - decimalLength));
		} catch (Exception e) {
			decimalStr = "0";
			while (e.getCause() != null)
				e = (Exception) e.getCause();

		}
		return new BigDecimal(decimalStr);
	}
}
