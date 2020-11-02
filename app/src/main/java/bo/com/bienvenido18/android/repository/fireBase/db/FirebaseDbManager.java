package bo.com.bienvenido18.android.repository.fireBase.db;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.PostTramite;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.utils.Constants;

public class FirebaseDbManager {

    private FirebaseDatabase db;

    public FirebaseDbManager() {
        db = FirebaseDatabase.getInstance();
    }

    public LiveData<Base<String>> addPostToTramite(String uuidTramite, PostTramite postTramite) { //push
        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        String path = Constants.FIREBASE_PATH_TRAMITES + "/" + uuidTramite;

        db.getReference(path).push().setValue(postTramite).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                      results.postValue(new Base<>("ID creado"));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_REGISTER_DB,task.getException()));
                }
            }
        });
        return results;
    }

    public LiveData<Base<Boolean>> updateCoverPhoto(String uuidStartup, String uuidPost, String url) {
        MutableLiveData<Base<Boolean>> results = new MutableLiveData<>();
        String path = Constants.FIREBASE_PATH_TRAMITES + "/" + uuidStartup + "/" + uuidPost;
        path += "/coverPhoto";
        db.getReference(path).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>(true));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_REGISTER_DB, task.getException()));
                }
            }
        });
        return results;
    }

    public LiveData<Base<List<PostTramite>>> observeTramitePost(String uuidTramite) { //observe
        MutableLiveData<Base<List<PostTramite>>> results = new MutableLiveData<>();
        return results;
    }



}
