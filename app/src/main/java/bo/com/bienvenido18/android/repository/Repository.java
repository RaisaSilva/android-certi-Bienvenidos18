package bo.com.bienvenido18.android.repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.PostTramite;
import bo.com.bienvenido18.android.model.users.Comentarios;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.model.users.UniversidadCocha;
import bo.com.bienvenido18.android.model.users.UniversidadSantaCruz;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.repository.api.ApiRepository;
import bo.com.bienvenido18.android.repository.fireBase.FirebaseRepository;
import bo.com.bienvenido18.android.repository.local.LocalRepository;
import bo.com.bienvenido18.android.ui.adapter.Universidades;
import bo.com.bienvenido18.android.utils.Constants;

public class Repository implements RepositoryImpl {
    private LocalRepository local;

    public Repository(Application application) {
        local = new LocalRepository(application);
    }

    @Override
    public LiveData<Base<UserO>> loginWithEmailPassword(String email, String password) {
        return FirebaseRepository.getInstance().loginWithEmailPassword(email, password);
    }

    @Override
    public LiveData<Base<List<Universidades>>> getUniveridades(String unis) {
        return null;
    }


    @Override
    public LiveData<Base<List<Tramites>>> getTramites(String tram) {
        MutableLiveData<Base<List<Tramites>>> results = new MutableLiveData<>();
        //first local
        local.getTramites().observeForever(new Observer<List<Tramites>>() {
            @Override
            public void onChanged(List<Tramites> tramites) {
                results.postValue(new Base<>(tramites));
            }
        });

        //API
        ApiRepository.getInstance().getTramites().observeForever(new Observer<Base<List<Tramites>>>() {
            @Override
            public void onChanged(Base<List<Tramites>> listBase) {
                if (listBase.isSuccess()) {
                    //publicar ui
                    results.postValue(listBase);
                    //actualizar la db
                    local.update(listBase.getData());
                }else{
                    results.postValue(new Base<>(Constants.ERROR_NO_CONNECTION, listBase.getException()));

                }
            }
        });

        return results;
    }

    @Override
    public LiveData<Base<List<Comentarios>>> getComentarios(String comen) {
        return ApiRepository.getInstance().getComentarios();
    }

    @Override
    public LiveData<Base<List<UniversidadCocha>>> getUniversidadesCocha(String cocha) {
        return null;
    }

    @Override
    public LiveData<Base<List<UniversidadSantaCruz>>> getUniversidadesSanta(String scz) {
        return null;
    }

    @Override
    public LiveData<Base<String>> addPostToTramite(String uuidTramite, PostTramite postTramites, Uri image) {//, Uri image
        return FirebaseRepository.getInstance().addPostToTramite(uuidTramite, postTramites, image); //, image

    }

    @Override
    public LiveData<Base<List<PostTramite>>> observeTramitePost(String uuidTramite) {
        return FirebaseRepository.getInstance().observeTramitePost(uuidTramite);
    }
    @Override
    public LiveData<Base<UserO>> registerUser(UserO user) {
        return FirebaseRepository.getInstance().register(user);
    }
    @Override
    public LiveData<Base<UserO>> getCurrentUser() {
        return FirebaseRepository.getInstance().getCurrentUser();
    }
    @Override
    public void signOut() {
        FirebaseRepository.getInstance().signOut();
    }


}
