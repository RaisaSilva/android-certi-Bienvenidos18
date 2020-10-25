package bo.com.bienvenido18.android.repository.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.repository.local.dao.TramitesDao;
import bo.com.bienvenido18.android.utils.ListasToStringConverter;

@Database(entities = {Tramites.class}, version = 1)
@TypeConverters(ListasToStringConverter.class)
public abstract class Bienvenido18Database extends RoomDatabase {

    private static  volatile Bienvenido18Database INSTANCE;

    static public Bienvenido18Database getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (Bienvenido18Database.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Bienvenido18Database.class, "bienvenido18_database")
                            .build();
                }

            }
        }
        return INSTANCE;

    }

    public abstract TramitesDao tramitesDao();

}
