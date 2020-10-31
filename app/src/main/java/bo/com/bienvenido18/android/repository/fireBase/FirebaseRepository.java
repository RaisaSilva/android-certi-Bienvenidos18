package bo.com.bienvenido18.android.repository.fireBase;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.PostTramite;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.repository.fireBase.auth.FirebaseAuthManager;
import bo.com.bienvenido18.android.repository.fireBase.db.FirebaseDbManager;
import bo.com.bienvenido18.android.repository.fireBase.storage.FirebaseStorageManager;
import bo.com.bienvenido18.android.utils.Constants;

public class FirebaseRepository {
    private static final String LOG = FirebaseRepository.class.getSimpleName();
    private static FirebaseRepository instance;
    private FirebaseAuthManager auth;
    private FirebaseStorageManager storage;
    private FirebaseDbManager db;

    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }
        return instance;
    }
    public FirebaseRepository(){
        auth = new FirebaseAuthManager();
        storage=new FirebaseStorageManager();
        db =new FirebaseDbManager();
    }

    public LiveData<Base<UserO>> loginWithEmailPassword(String email, String password) {
        return auth.loginWithEmailPassword(email, password);
    }

    public LiveData<Base<String>> addPostToTramite(String uuidTramite, PostTramite postTramite, Uri image) {


        //succes or failed at UI
        return db.addPostToTramite(uuidTramite, postTramite);
        //TODO Step 2: add to storage
        //TODO Step 3: update db

    }

    public LiveData<Base<List<PostTramite>>> observeTramitePost(String uuiTramite) {
        return db.observeTramitePost(uuiTramite);
    }

}
