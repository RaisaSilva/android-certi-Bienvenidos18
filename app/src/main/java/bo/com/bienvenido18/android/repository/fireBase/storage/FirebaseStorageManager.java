package bo.com.bienvenido18.android.repository.fireBase.storage;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.utils.Constants;

public class FirebaseStorageManager {
    private StorageReference storage;

    public FirebaseStorageManager(){
        storage = FirebaseStorage.getInstance().getReference();
    }

   /* public LiveData<Base<String>> uploadPostImage(String uuidPost, Uri image){
        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        String path = "images/" + uuidPost + ".jpg";
        StorageReference ref = storage.child(path);
        UploadTask uploadTask = ref.putFile(image);
        return results;

    }*/
}
