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
}