package bo.com.bienvenido18.android.repository;

import android.net.Uri;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.PostTramite;
import bo.com.bienvenido18.android.model.users.Comentarios;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.ui.adapter.Universidades;


public interface RepositoryImpl {
    LiveData<Base<UserO>> loginWithEmailPassword(String email, String password);
    LiveData<Base<List<Universidades>>> getUniveridades(String unis);
    LiveData<Base<List<Tramites>>> getTramites(String tram);
    LiveData<Base<List<Comentarios>>> getComentarios(String comen);

    /* -------- Post de tramites ---------*/

    LiveData<Base<String>> addPostToTramite(String uuidTramite, PostTramite postTramite , Uri image);
    LiveData<Base<List<PostTramite>>> observeTramitePost(String uuiTramite);
}
