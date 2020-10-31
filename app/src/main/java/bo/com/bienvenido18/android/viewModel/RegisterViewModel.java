package bo.com.bienvenido18.android.viewModel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.repository.Repository;
import bo.com.bienvenido18.android.repository.RepositoryImpl;


public class RegisterViewModel extends AndroidViewModel {

    private RepositoryImpl repository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<UserO>> register(UserO user) {
        return repository.registerUser(user);
    }
}
