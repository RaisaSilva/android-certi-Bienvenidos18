package bo.com.bienvenido18.android.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.model.users.UserP;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.utils.Validations;

public class MockRepository implements RepositoryImpl {

    public List<UserO> getFakeUsers() {
        List<UserO> fakeUsers = new ArrayList<>();
        UserO nuria = new UserO("nuria.michel@gmail.com", "contrasena1");
        nuria.setUuid("1");
        nuria.setDisplayName("Nuria Michel");
        fakeUsers.add(nuria);

        fakeUsers.add(new UserO("raisa.silva@gmail.com", "contrasena2"));
        fakeUsers.add(new UserP("liz.vasquez@gmail.com", "contrasena3"));
        fakeUsers.add(new UserO("benjamin.soto@gmail.com","contrasena4"));
        return fakeUsers;
    }

    @Override
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


        for (UserO user : getFakeUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                results.postValue(new Base(user));
                return results;
            }
        }

        results.postValue(new Base(Constants.ERROR_LOGIN, null));
        return results;
    }


}
