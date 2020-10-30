package bo.com.bienvenido18.android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.UniversidadCocha;
import bo.com.bienvenido18.android.repository.MockRepository;
import bo.com.bienvenido18.android.repository.RepositoryImpl;

public class CochaViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public CochaViewModel(@NonNull Application application) {
        super(application);
        repository = new MockRepository(application);
    }

    public LiveData<Base<List<UniversidadCocha>>> getUniversidadesCocha(String unisCocha) {
        return repository.getUniversidadesCocha(unisCocha);
    }
}
