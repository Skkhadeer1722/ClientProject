package scrips.datamigration.regex;

public class RegexValidationTest {

    public static void main(String[] args) {
        RegexValidation val = new RegexValidation();
        // length validation
        System.out.println(val.regexValidator("123","length3","test"));
        System.out.println(val.regexValidator("1234","length3","test"));
        // ascii validation
        System.out.println(val.regexValidator("a","ascii","test"));
        System.out.println(val.regexValidator("网络","ascii","test"));
        // boolean validation (0,1)
        System.out.println(val.regexValidator("0","boolean","test"));
        System.out.println(val.regexValidator("3","boolean","test"));
        // numeric validation
        System.out.println(val.regexValidator("2","numeric","test"));
        System.out.println(val.regexValidator("A","numeric","test"));
        // special char validation
        System.out.println(val.regexValidator("a2","special","test"));
        System.out.println(val.regexValidator("#","special","test"));
        // currency code validation (only allows SGD)
        System.out.println(val.regexValidator("USD","currencycode","test"));
        System.out.println(val.regexValidator("SGD","currencycode","test"));
        // account type validation
        System.out.println(val.regexValidator("AGDAAGDA","accounttype","test"));
        System.out.println(val.regexValidator("AGDA","accounttype","test"));
        // account status validation (capital letter : ACTIVE)
        System.out.println(val.regexValidator("active","accountstatus","test"));
        System.out.println(val.regexValidator("ACTIVE","accountstatus","test"));
    };
}
