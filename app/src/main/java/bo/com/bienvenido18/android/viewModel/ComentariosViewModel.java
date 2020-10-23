package bo.com.bienvenido18.android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.Comentarios;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.repository.MockRepository;
import bo.com.bienvenido18.android.repository.RepositoryImpl;

public class ComentariosViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public ComentariosViewModel(@NonNull Application application) {
        super(application);
        repository = new MockRepository();
    }
    public LiveData<Base<List<Comentarios>>> getComentarios(String comen) {
        return repository.getComentarios(comen);//
    }
}
