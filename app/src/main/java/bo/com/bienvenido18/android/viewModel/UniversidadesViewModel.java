package bo.com.bienvenido18.android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.repository.MockRepository;
import bo.com.bienvenido18.android.repository.RepositoryImpl;
import bo.com.bienvenido18.android.ui.adapter.Universidades;

public class UniversidadesViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public UniversidadesViewModel(@NonNull Application application) {
        super(application);
        repository = new MockRepository(application);
    }

    public LiveData<Base<List<Universidades>>> getUniversidades(String unis) {
        return repository.getUniveridades(unis);
    }
}
