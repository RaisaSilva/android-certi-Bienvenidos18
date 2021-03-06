package bo.com.bienvenido18.android.model.users;

public class UserO {
    protected String uuid;
    protected String email;
    protected String password;
    protected String displayName;
    protected UserProfile profile = UserProfile.USERO;

    public UserO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDisplayName() {

        return displayName;
    }

    public void setDisplayName(String displayName) {

        this.displayName = displayName;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
