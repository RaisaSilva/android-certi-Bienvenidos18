package bo.com.bienvenido18.android.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.Comentarios;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.repository.api.ApiRepository;
import bo.com.bienvenido18.android.ui.adapter.Universidades;

public class Repository implements RepositoryImpl {
    @Override
    public LiveData<Base<UserO>> loginWithEmailPassword(String email, String password) {
        return null;
    }

    @Override
    public LiveData<Base<List<Universidades>>> getUniveridades(String unis) {
        return null;
    }


    @Override
    public LiveData<Base<List<Tramites>>> getTramites(String tram) {
        return ApiRepository.getInstance().getTramites();
    }

    @Override
    public LiveData<Base<List<Comentarios>>> getComentarios(String comen) {
        return ApiRepository.getInstance().getComentarios();
    }


}
