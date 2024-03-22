package scrips.datamigration;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class TestFile {
	public static void main(String[] args) {
//		String str = "MASGSGSG|21110223|Settlement Account|SETT|Y|Y||Active|SGD|NO_STATEMENT|ctgy1|cost1|123|";
//		str.replaceAll(str, "//|");
//
//		Map<String, Long> result = Arrays.stream(str.split("")).map(String::toLowerCase)
//				.collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()));
//		System.out.println(result);
//
//		String regex = "^\\d*\\.?\\d+|\\d+\\.\\d*$";
//		String regex1 = "^\\d*\\.?\\d*$";
//		Pattern pattern = Pattern.compile(regex);
//
//		String d1 = ".0";
//		if (d1.matches(regex))
//			System.out.println("true");
//		else
//			System.out.println("false");
//
//		Double d = 1.23456789123456784E17;
//
//		System.out.println(BigDecimal.valueOf(d).toPlainString());
//		long l = System.currentTimeMillis();
//
//		System.out.println(convertStringToDecimal("00215000", 8, 5));
//		long l1 = System.currentTimeMillis();
//
//		long e = l1 - 1;
//		int seconds = (int) (e / 1000) % 60;
//		int minutes = (int) ((e / (1000 * 60)) % 60);
//		int hours = (int) ((e / (1000 * 60 * 60)) % 24);
//		System.out.println(seconds + " " + minutes + " " + hours);
//
//	}
//
//	public static BigDecimal convertStringToDecimal(String str, int intLength, int decimalLength) {
//		String decimalStr = new String();
//		try {
//			if (str.length() > intLength)
//				return null;
//			decimalStr = str.substring(0, (str.length() - decimalLength)) + "."
//					+ str.substring((str.length() - decimalLength));
//		} catch (Exception e) {
//			decimalStr = "0";
//			while (e.getCause() != null)
//				e = (Exception) e.getCause();
//
//		}
//		return new BigDecimal(decimalStr);
	 

        // type MM yyyy
//        Scanner in = new Scanner(System.in);
//        System.out.print("Enter month and year: MM yyyy ");
//        int month = in.nextInt();
//        int year = in.nextInt();
//        in.close();
//        // checks valid month
//        try {
//
//            if (month < 1 || month > 12)
//                throw new Exception("Invalid index for month: " + month);
//            printCalendarMonthYear(month, year);} 
//
//         catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//    private static void printCalendarMonthYear(int month, int year) {
//        Calendar cal = new GregorianCalendar();
//        cal.clear();
//        cal.set(year, month - 1, 1); // setting the calendar to the month and year provided as parameters 
//        System.out.println("Calendar for "+ cal.getDisplayName(Calendar.MONTH, Calendar.LONG,
//                        Locale.US) + " " + cal.get(Calendar.YEAR));//to print Calendar for month and year 
//        int firstWeekdayOfMonth = cal.get(Calendar.DAY_OF_WEEK);//which weekday was the first in month
//        int numberOfMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //lengh of days in a month 
//        printCalendar(numberOfMonthDays, firstWeekdayOfMonth);
//    }
//    private static void printCalendar(int numberOfMonthDays, int firstWeekdayOfMonth) {
//        int weekdayIndex = 0; 
//        System.out.println("Su  Mo  Tu  We  Th  Fr  Sa"); // The order of days depends on your calendar
//
//        for (int day = 1; day < firstWeekdayOfMonth; day++) {
//            System.out.print("    "); //this loop to print the first day in his correct place
//            weekdayIndex++;
//        }
//        for (int day = 1; day <= numberOfMonthDays; day++) {
//
//            if (day<10) // this is just for better visialising because unit number take less space of course than 2
//            System.out.print(day+" ");
//            else System.out.print(day); 
//            weekdayIndex++;
//            if (weekdayIndex == 7) {
//                weekdayIndex = 0;
//                System.out.println();
//            } else { 
//                System.out.print("  ");
//            }}
		   Scanner input = new Scanner(System.in); // Scan for user input
	        System.out.print("Please enter a month between 1 and 12 (e.g. 5): "); // Prompt user to enter month
	        int m = input.nextInt();

	        System.out.print("Please enter a full year (e.g. 2018): "); // Prompt user to enter year
	        int y = input.nextInt();
	        printMonth(y, m);
	    }

	    static void printMonth(int year, int month) {
	        YearMonth ym = YearMonth.of(year, month);
	        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
	        int counter = 1;

	        // Get day of week of 1st date of the month and print space for as many days as
	        // distant from SUN
	        int dayValue = LocalDate.of(year, month, 1).getDayOfWeek().getValue();
	        for (int i = 0; i < dayValue; i++, counter++) {
	            System.out.printf("%-4s", "");
	        }

	        for (int i = 1; i <= ym.getMonth().length(ym.isLeapYear()); i++, counter++) {
	            System.out.printf("%-4s", i);

	            // Break the line if the value of the counter is multiple of 7
	            if (counter % 7 == 0) {
	                System.out.println();
	            }
	        }
		
		
	}
}
