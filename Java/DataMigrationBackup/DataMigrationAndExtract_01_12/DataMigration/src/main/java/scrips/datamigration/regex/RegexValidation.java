package scrips.datamigration.regex;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;



@Component
public class RegexValidation {

    public String regexValidator(String inputField, String regexName, String cloumnName) {
		// StringBuilder errorString = new StringBuilder();

        Optional<RegexEnum> regexEnum = RegexEnum.getRegeXValue(regexName);
		var error = new String();
		if (inputField==null||inputField.equals(null)|| (inputField.equals(""))){
			error = "Null or Empty";
		}
		else {
			boolean validField = Pattern.compile(regexEnum.get().getPattern()).matcher(inputField).matches();  
			if (!validField){
				error = regexEnum.get().getErrorDescription();
			}
			else{
				error = "";
			}
		}
		if (error.equals(null) || error.equals("")){
			return String.format("%s", error);
			// return errorString.toString().trim();
		}
		else{
			return String.format(", %s : %s", cloumnName, error);
			// errorString.append(",");
			// errorString.append(cloumnName);
			// errorString.append(" : ");
			// errorString.append(error);
			// log.error(errorString.toString());
			// return errorString.toString().trim();
		}
	}
    
}
