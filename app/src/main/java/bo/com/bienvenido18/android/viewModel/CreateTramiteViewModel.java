package bo.com.bienvenido18.android.viewModel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.PostTramite;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.repository.Repository;
import bo.com.bienvenido18.android.repository.RepositoryImpl;

public class CreateTramiteViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public CreateTramiteViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<String>> createPost(String uuidTramite, PostTramite postTramite, Uri image) {
        return repository.addPostToTramite(uuidTramite, postTramite, image);
    }
}
