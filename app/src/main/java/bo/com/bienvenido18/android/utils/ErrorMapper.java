package bo.com.bienvenido18.android.utils;

import android.content.Context;

public class ErrorMapper {
    public static String getError(Context context, int errorCode) {
        switch (errorCode) {
            case Constants.ERROR_EMPTY_VALUES:
                return "Please fill all de values";
            case Constants.ERROR_LOGIN:
                return "Please review your internet connection";
            case Constants.ERROR_INVALID_EMAIL:
                return "Email or Password incorrect";
            default:
                return "Unknown error";
        }
    }
}
