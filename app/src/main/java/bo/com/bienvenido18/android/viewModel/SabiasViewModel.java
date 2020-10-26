package bo.com.bienvenido18.android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.Sabias;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.repository.Repository;
import bo.com.bienvenido18.android.repository.RepositoryImpl;

public class SabiasViewModel extends AndroidViewModel {
    private RepositoryImpl repository;


    public SabiasViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<List<Sabias>>> getSabias(String sa) {
        return repository.getSabias(sa);//
    }


}
