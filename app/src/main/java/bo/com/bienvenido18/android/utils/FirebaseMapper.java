package bo.com.bienvenido18.android.utils;

import com.google.firebase.auth.FirebaseUser;

import bo.com.bienvenido18.android.model.users.UserO;


public class FirebaseMapper {
    static public UserO firebaseUserToUser(FirebaseUser firebaseUser) {
        UserO user = new UserO(firebaseUser.getEmail(), null);
        user.setUuid(firebaseUser.getUid());
        user.setDisplayName(firebaseUser.getDisplayName());
       // user.setPhoto(firebaseUser.getPhotoUrl() != null ? firebaseUser.getPhotoUrl().toString() : "");
        return user;
    }
}
