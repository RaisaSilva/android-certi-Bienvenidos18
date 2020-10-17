package bo.com.bienvenido18.android.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.model.users.UserP;
import bo.com.bienvenido18.android.ui.adapter.Universidades;
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
        fakeUsers.add(new UserO("benjamin.soto@gmail.com", "contrasena4"));
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

    @Override
    public LiveData<Base<List<Universidades>>> getUniveridades(String unis) {
        MutableLiveData<Base<List<Universidades>>> results = new MutableLiveData<>();
        String json = "[{\"uuid\":\"1\",\"displayName\":\"UPB\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRCIVg4c77tHkiOsB8N-8O0gMDlWN3y8i71vA&usqp=CAU\",\"address\":\"Para más info comunicate con: ...\"},{\"uuid\":\"2\",\"displayName\":\"UMSA\",\"coverPhoto\":\"https://www.universidadesonline.com.bo/logos/original/logo-universidad-mayor-de-san-andres.png\",\"address\":\"Para más info comunicate con: ...\"},{\"uuid\":\"3\",\"displayName\":\"LA SALLE\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT5FULZCQqOH4E2jIx--Ew3dN4pYuq6-Lxpew&usqp=CAU\",\"address\":\"Para más info comunicate con: ...\"},{\"uuid\":\"4\",\"displayName\":\"UCB\",\"coverPhoto\":\"https://cba.ucb.edu.bo/wp-content/uploads/2018/04/escudo-ucb.png\",\"address\":\"Para más info comunicate con: ...\"},{\"uuid\":\"5\",\"displayName\":\"UTB\",\"coverPhoto\":\"https://2.bp.blogspot.com/-1X1bV0MdqR8/WV63XyOgknI/AAAAAAAAACs/7uXif8MlXgww421ilF-CZjWTuIz7hJV-ACLcBGAs/s1600/UTB-logo.png\",\"address\":\"Para más info comunicate con: ...\"},{\"uuid\":\"6\",\"displayName\":\"UNIFRANZ\",\"coverPhoto\":\"https://www.universidadesonline.com.bo/logos/original/logo-universidad-privada-franz-tamayo.png\",\"address\":\"Para más info comunicate con: ...\"},{\"uuid\":\"7\",\"displayName\":\"UNIVALLE\",\"coverPhoto\":\"https://www.universidadesonline.com.bo/logos/original/logo-universidad-privada-del-valle.png\",\"address\":\"Para más info comunicate con: ...\"},{\"uuid\":\"8\",\"displayName\":\"EMI\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQpg0HZ6Q3xs2UtvU6vFDqzOyQ_JPeSgWZvZQ&usqp=CAU\",\"address\":\"Para más info comunicate con: ...\"}]";
        Type listType = new TypeToken<ArrayList<Universidades>>() {
        }.getType();
        List<Universidades> uniList = new Gson().fromJson(json, listType);
        results.postValue(new Base<>(uniList));
        return results;

    }

    @Override
    public LiveData<Base<List<Tramites>>> getTramites(String trans) {
        MutableLiveData<Base<List<Tramites>>> results = new MutableLiveData<>();
        String json = "[{\"uuid\":\"1\",\"titlePhoto\":\"venta de autos\",\"coverPhoto\":\"https://i.prcdn.co/img?regionKey=8FCcrEagurNyAFzvjqo%2F6w%3D%3D\"},{\"uuid\":\"2\",\"titlePhoto\":\"Procedimiento legislativo\",\"coverPhoto\":\"https://image.slidesharecdn.com/procedimientolegislativovigente-1-120921151702-phpapp01/95/procedimiento-legislativo-vigente-en-bolivia-1-728.jpg?cb=1348240889\"},{\"uuid\":\"3\",\"titlePhoto\":\"Compra de propiedad\",\"coverPhoto\":\"https://i.prcdn.co/img?regionKey=diG8G3PK6WpRfMqmsbQiIQ%3D%3D\"},{\"uuid\":\"4\",\"titlePhoto\":\"Viviendas\",\"coverPhoto\":\"https://i.pinimg.com/originals/b1/e9/ab/b1e9ab014f4440c04e7dd23752b978c0.jpg.\"},{\"uuid\":\"5\",\"titlePhoto\":\"Elección de vivienda\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSZtXLr-1A_eYCHQkZ2k5JlMD7dcN4gwwxnTQ&usqp=CAU\"},{\"uuid\":\"3\",\"titlePhoto\":\"Compra de propiedad\",\"coverPhoto\":\"https://i.prcdn.co/img?regionKey=diG8G3PK6WpRfMqmsbQiIQ%3D%3D\"},{\"uuid\":\"4\",\"titlePhoto\":\"Viviendas\",\"coverPhoto\":\"https://i.pinimg.com/originals/b1/e9/ab/b1e9ab014f4440c04e7dd23752b978c0.jpg.\"},{\"uuid\":\"5\",\"titlePhoto\":\"Elección de vivienda\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSZtXLr-1A_eYCHQkZ2k5JlMD7dcN4gwwxnTQ&usqp=CAU\"},{\"uuid\":\"3\",\"titlePhoto\":\"Compra de propiedad\",\"coverPhoto\":\"https://i.prcdn.co/img?regionKey=diG8G3PK6WpRfMqmsbQiIQ%3D%3D\"},{\"uuid\":\"4\",\"titlePhoto\":\"Viviendas\",\"coverPhoto\":\"https://i.pinimg.com/originals/b1/e9/ab/b1e9ab014f4440c04e7dd23752b978c0.jpg.\"},{\"uuid\":\"5\",\"titlePhoto\":\"Elección de vivienda\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSZtXLr-1A_eYCHQkZ2k5JlMD7dcN4gwwxnTQ&usqp=CAU\"}]";

        Type listType = new TypeToken<ArrayList<Tramites>>() {
        }.getType();
        List<Tramites> uniList = new Gson().fromJson(json, listType);
        results.postValue(new Base<>(uniList));
        return results;


    }
}
