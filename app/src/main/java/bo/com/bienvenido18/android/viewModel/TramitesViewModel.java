package bo.com.bienvenido18.android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.repository.MockRepository;
import bo.com.bienvenido18.android.repository.RepositoryImpl;
import bo.com.bienvenido18.android.ui.adapter.Universidades;

public class TramitesViewModel extends AndroidViewModel {
    private RepositoryImpl repository;


    public TramitesViewModel(@NonNull Application application) {
        super(application);
        repository = new MockRepository();
    }

    public LiveData<Base<List<Tramites>>> getTramites(String tram) {
        return repository.getTramites(tram);//
    }
}
