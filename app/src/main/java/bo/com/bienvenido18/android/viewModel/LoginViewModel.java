package bo.com.bienvenido18.android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.repository.MockRepository;
import bo.com.bienvenido18.android.repository.RepositoryImpl;

public class LoginViewModel extends AndroidViewModel {

    private RepositoryImpl repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new MockRepository(application);
    }

    public LiveData<Base<UserO>> loginWithEmailPassword(String email, String password) {
        return repository.loginWithEmailPassword(email, password);
    }
}
