package bo.com.bienvenido18.android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import bo.com.bienvenido18.android.repository.Repository;
import bo.com.bienvenido18.android.repository.RepositoryImpl;

public class LauncherViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public LauncherViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }
    public void logout() {
        repository.signOut();
    }
}
