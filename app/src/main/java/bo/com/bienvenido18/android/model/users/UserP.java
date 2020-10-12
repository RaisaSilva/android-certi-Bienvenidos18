package bo.com.bienvenido18.android.model.users;

public class UserP extends UserO {
    public UserP(String email, String password) {
        super(email, password);
        profile = UserProfile.USERP;
    }
}
