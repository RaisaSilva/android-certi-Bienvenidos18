package bo.com.bienvenido18.android.utils;

public class Constants {

    public static final int ERROR_EMPTY_VALUES = 1000;
    public static final int ERROR_NO_CONNECTION = 1001;

    public static final int ERROR_LOGIN = 2000;
    public static final int ERROR_INVALID_EMAIL = 2001;
    public static final int ERROR_wRONG_PASSWORD = 2002;
    public static final int ERROR_REGISTER_DB = 2005;
    public static final int ERROR_REGISTER = 2007;
    public static final int ERROR_REGISTER_EMAIL_ALREADY_EXISTS = 2003;

    //Server
    public static final int ERROR_SERVER = 3000;

    public static final String KEY_UUID = "uuid";
    public static final String KEY_DISPLAY_NAME = "displayName";
    public static final String KEY_STARTUP_SELECTED = "startupSelected";
    public static final String BASE_URL = "https://firebasestorage.googleapis.com/v0/b/bienvenido18-15d9f.appspot.com/o/";
    public static final String BASE_URL_COMENTARIOS = "https://us-central1-emprende-ya-2cd09.cloudfunctions.net/api/bienvenidos18/";
    public static final String RESOURCE_TRAMITES = "nuevo_tramites.json";
    public static final String RESOURCE_COMENTARIOS = ".";

    public static final String QUERY_PARAM_ALT = "media";
    public static final String QUERY_PARAM_ALT_COMENTARIOS = "media";

    public static final String KEY_TRAMITE_UUID_SELECTED = "uuidTramite";

    //Firebase
    public static final String FIREBASE_PATH_TRAMITES = "/Tramites";

}
