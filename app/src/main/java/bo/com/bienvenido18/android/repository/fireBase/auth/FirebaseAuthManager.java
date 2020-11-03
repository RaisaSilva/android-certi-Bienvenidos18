package bo.com.bienvenido18.android.repository.fireBase.auth;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.utils.FirebaseMapper;
import bo.com.bienvenido18.android.utils.Validations;

public class FirebaseAuthManager {
    private FirebaseAuth mAuth;

    public FirebaseAuthManager() {
        mAuth=FirebaseAuth.getInstance();
    }
    public LiveData<Base<UserO>> getCurrentUser() {
        MutableLiveData<Base<UserO>> results = new MutableLiveData<>();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            results.postValue(new Base<>(FirebaseMapper.firebaseUserToUser(firebaseUser)));
        }
        return results;
    }

    public LiveData<Base<UserO>> loginWithEmailPassword(String email, String password) {
        MutableLiveData<Base<UserO>> results = new MutableLiveData<>();
        if (Validations.isEmpty(email) || Validations.isEmpty(password)) {
            results.postValue(new Base(Constants.ERROR_EMPTY_VALUES, null));
            return results;
        }

        if (!Validations.isValidEmail(email)) {
            results.postValue(new Base(Constants.ERROR_INVALID_EMAIL, null));
            return results;
        }
        if (!Validations.isValidPasswoord(password)) {
            results.postValue(new Base(Constants.ERROR_wRONG_PASSWORD, null));
            return results;
        }

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

                    }
                });

        return results;
    }
    public LiveData<Base<UserO>> registerUser(UserO user) {
        MutableLiveData<Base<UserO>> results = new MutableLiveData<>();
        if (Validations.isEmpty(user.getEmail()) || Validations.isEmpty(user.getPassword())) {
            results.postValue(new Base(Constants.ERROR_EMPTY_VALUES, null));
            return results;
        }

        if (!Validations.isValidEmail(user.getEmail())) {
            results.postValue(new Base(Constants.ERROR_INVALID_EMAIL, null));
            return results;
        }
        if (!Validations.isValidPasswoord(user.getPassword())) {
            results.postValue(new Base(Constants.ERROR_wRONG_PASSWORD, null));
            return results;
        }

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            results.postValue(new Base<>(FirebaseMapper.firebaseUserToUser(firebaseUser)));
                        } else {
                            if (task.getException() instanceof FirebaseAuthException) {
                                if (((FirebaseAuthException) task.getException()).getErrorCode().equals("ERROR_EMAIL_ALREADY_IN_USE")) {
                                    results.postValue(new Base<>(Constants.ERROR_REGISTER_EMAIL_ALREADY_EXISTS, task.getException()));
                                } else {
                                    results.postValue(new Base<>(Constants.ERROR_REGISTER, task.getException()));
                                }
                            } else {
                                results.postValue(new Base<>(Constants.ERROR_REGISTER, task.getException()));
                            }
                        }
                    }
                });
        return results;
    }
    public void signOut() {
        this.mAuth.signOut();
    }
}
