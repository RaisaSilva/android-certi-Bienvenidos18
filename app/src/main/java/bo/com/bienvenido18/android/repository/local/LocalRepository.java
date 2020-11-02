package bo.com.bienvenido18.android.repository.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.repository.local.dao.TramitesDao;
import bo.com.bienvenido18.android.repository.local.db.Bienvenido18Database;

public class LocalRepository {
    public static final String LOG = LocalRepository.class.getSimpleName();

    private TramitesDao tramitesDao;

    public LocalRepository(Application application) {
        Bienvenido18Database db = Bienvenido18Database.getDatabase(application);
        tramitesDao = db.tramitesDao();
    }

    public LiveData<List<Tramites>> getTramites(){
        return tramitesDao.getAll();
    }

    public void update(List<Tramites> tramites){
        new Thread(() -> {
            tramitesDao.insert(tramites);
        }).start();

    }
}
