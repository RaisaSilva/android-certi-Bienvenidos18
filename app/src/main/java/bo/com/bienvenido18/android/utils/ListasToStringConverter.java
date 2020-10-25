package bo.com.bienvenido18.android.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ListasToStringConverter {
    @TypeConverter
    public String fromList(List<String> lista) {
        return new Gson().toJson(lista);
    }

    @TypeConverter
    public List<String> fromString(String string) {
        Type listaType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(string, listaType);
    }
}
