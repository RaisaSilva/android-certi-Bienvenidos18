package bo.com.bienvenido18.android.repository;

import androidx.lifecycle.LiveData;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.UserO;

public class Repository implements RepositoryImpl {
    @Override
    public LiveData<Base<UserO>> loginWithEmailPassword(String email, String password) {
        return null;
    }
}
