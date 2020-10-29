package bo.com.bienvenido18.android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.PostTramite;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.repository.Repository;

public class PostTramiteViewModel extends AndroidViewModel {
    private Repository repository;


    public PostTramiteViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<List<PostTramite>>> observePosts(String uuid){
        return repository.observeTramitePost(uuid);
    }
}
