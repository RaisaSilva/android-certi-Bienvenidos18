package bo.com.bienvenido18.android.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class MapToStringConverter {

    @TypeConverter
    public String fromMap(Map<String, String> map) {
        return new Gson().toJson(map);
    }

    @TypeConverter
    public Map<String, String> fromString(String string) {
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        return new Gson().fromJson(string, mapType);
    }
}
