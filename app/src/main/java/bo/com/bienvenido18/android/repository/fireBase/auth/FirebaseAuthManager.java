package bo.com.bienvenido18.android.repository.fireBase.auth;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.utils.FirebaseMapper;

public class FirebaseAuthManager {
    private FirebaseAuth mAuth;

    public FirebaseAuthManager() {
        mAuth=FirebaseAuth.getInstance();
    }

    public LiveData<Base<UserO>> loginWithEmailPassword(String email, String password) {
        MutableLiveData<Base<UserO>> results = new MutableLiveData<>();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            results.postValue(new Base<>(FirebaseMapper.firebaseUserToUser(firebaseUser)));

                        } else {
                            results.postValue(new Base<>(Constants.ERROR_LOGIN,task.getException()));

                        }

                        // ...
                    }
                });

        return results;
    }
}
