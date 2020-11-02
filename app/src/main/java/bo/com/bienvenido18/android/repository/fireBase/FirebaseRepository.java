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

    public FirebaseRepository() {
        auth = new FirebaseAuthManager();
        storage = new FirebaseStorageManager();
        db = new FirebaseDbManager();
    }

    public LiveData<Base<UserO>> loginWithEmailPassword(String email, String password) {
        return auth.loginWithEmailPassword(email, password);
    }


    public LiveData<Base<String>> addPostToTramite(String uuidTramite, PostTramite postTramite, Uri image) {

        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        //Step 1: Create record
        db.addPostToTramite(uuidTramite, postTramite).observeForever(uuidPostBase -> {
            if (uuidPostBase.isSuccess()) {
                //Step 2: Upload image
                String uuidPost = uuidPostBase.getData();
               /* storage.uploadPostImage(uuidPost, image).observeForever(urlBase -> {
                    if (urlBase.isSuccess()) {
                        //Step 3: Update record
                        String url = urlBase.getData();
                        db.updateCoverPhoto(uuidTramite, uuidPost, url).observeForever(resultUpdateBase -> {
                            if (resultUpdateBase.isSuccess()) {
                                results.postValue(new Base<>(uuidPost));
                            } else {
                                results.postValue(new Base<>(resultUpdateBase.getErrorCode(), resultUpdateBase.getException()));
                            }
                        });
                    } else {
                        results.postValue(new Base<>(urlBase.getErrorCode(), urlBase.getException()));
                    }
                });*/
            } else {
                results.postValue(new Base<>(uuidPostBase.getErrorCode(), uuidPostBase.getException()));
            }
        });
        return results;
    }




    public LiveData<Base<List<PostTramite>>> observeTramitePost(String uuiTramite) {
        return db.observeTramitePost(uuiTramite);
    }
    public LiveData<Base<UserO>> register(UserO user) {
        return auth.registerUser(user);
    }

    public LiveData<Base<UserO>> getCurrentUser() {
        return auth.getCurrentUser();
    }
    public void signOut() {
        auth.signOut();
    }

}
