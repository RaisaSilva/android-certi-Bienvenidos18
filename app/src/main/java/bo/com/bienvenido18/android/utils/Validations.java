package bo.com.bienvenido18.android.utils;

import java.util.regex.Pattern;

public class Validations {
    public static boolean isEmpty(String target) {
        return target.isEmpty();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        return pattern.matcher(email).matches();
    }
    public static boolean isValidPasswoord(String password) {
        Pattern pattern = Pattern.compile("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,50})$");
        return pattern.matcher(password).matches();

    }
}
