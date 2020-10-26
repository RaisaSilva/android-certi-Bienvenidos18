package bo.com.bienvenido18.android.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.Comentarios;
import bo.com.bienvenido18.android.model.users.Sabias;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.model.users.UserP;
import bo.com.bienvenido18.android.repository.api.ApiRepository;
import bo.com.bienvenido18.android.repository.local.LocalRepository;
import bo.com.bienvenido18.android.ui.adapter.Universidades;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.utils.Validations;

public class MockRepository implements RepositoryImpl {

    public MockRepository(Application application){

    }

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
        String json = "[{\"uuid\":\"1\",\"displayName\":\"UPB\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRCIVg4c77tHkiOsB8N-8O0gMDlWN3y8i71vA&usqp=CAU\",\"direccion\":\" Dirección: Av Hernando Siles, La Paz.\",\"telefonos\":\" Teléfonos: 2 2170000 \",\"carreras\":\"Carreras: Ingeniería de Sistemas, Administración de Empresas. \"},{\"uuid\":\"2\",\"displayName\":\"UMSA\",\"coverPhoto\":\"https://www.universidadesonline.com.bo/logos/original/logo-universidad-mayor-de-san-andres.png\",\"direccion\":\"Dirección: Av. Villazón Nro.1995, Monoblock Central\",\"telefonos\":\"Teléfonos: 800-16-0013\",\"carreras\":\"Carreras: Ingeniería, Medicina, Exactas, Economía\"},{\"uuid\":\"3\",\"displayName\":\"LA SALLE\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT5FULZCQqOH4E2jIx--Ew3dN4pYuq6-Lxpew&usqp=CAU\",\"direccion\":\"Dirección: Av. Jorge Carrasco esq. Las Palmas Nº450 (Bolognia)\",\"telefonos\":\"Teléfonos: 2 2723588\",\"carreras\":\"Carreras: Ingeniería, Economía, Administración de empresas, Arquitectura\"},{\"uuid\":\"4\",\"displayName\":\"UCB\",\"coverPhoto\":\"https://cba.ucb.edu.bo/wp-content/uploads/2018/04/escudo-ucb.png\",\"direccion\":\"Dirección:  Av 14 de Septiembre Nº 4807\",\"telefonos\":\"Teléfonos: 2 2782222 \",\"carreras\":\"Carreras: Ingeniería Comercial , Derecho \"},{\"uuid\":\"5\",\"displayName\":\"UTB\",\"coverPhoto\":\"https://2.bp.blogspot.com/-1X1bV0MdqR8/WV63XyOgknI/AAAAAAAAACs/7uXif8MlXgww421ilF-CZjWTuIz7hJV-ACLcBGAs/s1600/UTB-logo.png\",\"direccion\":\" Dirección: Calle Colombia #154, 14100 Ciudad La Paz, Bolivia.\",\"telefonos\":\"Teléfonos: 2 2180808\",\"carreras\":\"Economía, Contabilidad\"},{\"uuid\":\"6\",\"displayName\":\"UNIFRANZ\",\"coverPhoto\":\"https://www.universidadesonline.com.bo/logos/original/logo-universidad-privada-franz-tamayo.png\",\"direccion\":\"Dirección: Calle Héroes del Acre No. 1855 esquina Landaeta\",\"telefonos\":\" Teléfonos:  2487700\",\"carreras\":\"Odontología, Comunicación\"},{\"uuid\":\"7\",\"displayName\":\"UNIVALLE\",\"coverPhoto\":\"https://www.universidadesonline.com.bo/logos/original/logo-universidad-privada-del-valle.png\",\"direccion\":\"Dirección: Av. Argentina Nº 2083, La Paz\",\"telefonos\":\"Teléfono: 2 2246725\",\"carreras\":\"Carreras: Medicina, Odontología,Gastronomia\"},{\"uuid\":\"8\",\"displayName\":\"EMI\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQpg0HZ6Q3xs2UtvU6vFDqzOyQ_JPeSgWZvZQ&usqp=CAU\",\"direccion\":\"Dirección:  Central Av. Arce No. 2642\",\"telefonos\":\"Telefonos: 2775536 - 2799505\",\"carreras\":\" Carreras: Ingeniería Ambiental, Ingeniería Civil\"}]";
        Type listType = new TypeToken<ArrayList<Universidades>>() {
        }.getType();
        List<Universidades> uniList = new Gson().fromJson(json, listType);
        results.postValue(new Base<>(uniList));
        return results;

    }

    @Override
    public LiveData<Base<List<Tramites>>> getTramites(String trans) {
        return ApiRepository.getInstance().getTramites();
        /*MutableLiveData<Base<List<Tramites>>> results = new MutableLiveData<>();
        String json = "[{\"uuid\":\"1\",\"titlePhoto\":\"venta de autos\",\"coverPhoto\":\"https://i.prcdn.co/img?regionKey=8FCcrEagurNyAFzvjqo%2F6w%3D%3D\"},{\"uuid\":\"2\",\"titlePhoto\":\"Procedimiento legislativo\",\"coverPhoto\":\"https://image.slidesharecdn.com/procedimientolegislativovigente-1-120921151702-phpapp01/95/procedimiento-legislativo-vigente-en-bolivia-1-728.jpg?cb=1348240889\"},{\"uuid\":\"3\",\"titlePhoto\":\"Compra de propiedad\",\"coverPhoto\":\"https://i.prcdn.co/img?regionKey=diG8G3PK6WpRfMqmsbQiIQ%3D%3D\"},{\"uuid\":\"4\",\"titlePhoto\":\"Viviendas\",\"coverPhoto\":\"https://i.pinimg.com/originals/b1/e9/ab/b1e9ab014f4440c04e7dd23752b978c0.jpg.\"},{\"uuid\":\"5\",\"titlePhoto\":\"Elección de vivienda\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSZtXLr-1A_eYCHQkZ2k5JlMD7dcN4gwwxnTQ&usqp=CAU\"},{\"uuid\":\"3\",\"titlePhoto\":\"Compra de propiedad\",\"coverPhoto\":\"https://i.prcdn.co/img?regionKey=diG8G3PK6WpRfMqmsbQiIQ%3D%3D\"},{\"uuid\":\"4\",\"titlePhoto\":\"Viviendas\",\"coverPhoto\":\"https://i.pinimg.com/originals/b1/e9/ab/b1e9ab014f4440c04e7dd23752b978c0.jpg.\"},{\"uuid\":\"5\",\"titlePhoto\":\"Elección de vivienda\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSZtXLr-1A_eYCHQkZ2k5JlMD7dcN4gwwxnTQ&usqp=CAU\"},{\"uuid\":\"3\",\"titlePhoto\":\"Compra de propiedad\",\"coverPhoto\":\"https://i.prcdn.co/img?regionKey=diG8G3PK6WpRfMqmsbQiIQ%3D%3D\"},{\"uuid\":\"4\",\"titlePhoto\":\"Viviendas\",\"coverPhoto\":\"https://i.pinimg.com/originals/b1/e9/ab/b1e9ab014f4440c04e7dd23752b978c0.jpg.\"},{\"uuid\":\"5\",\"titlePhoto\":\"Elección de vivienda\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSZtXLr-1A_eYCHQkZ2k5JlMD7dcN4gwwxnTQ&usqp=CAU\"}]";

        Type listType = new TypeToken<ArrayList<Tramites>>() {
        }.getType();
        List<Tramites> uniList = new Gson().fromJson(json, listType);
        results.postValue(new Base<>(uniList));
        return results;


    }*/
    }
    @Override
    public LiveData<Base<List<Comentarios>>> getComentarios(String comen) {
        return ApiRepository.getInstance().getComentarios();
        /*MutableLiveData<Base<List<Comentarios>>> results = new MutableLiveData<>();
        String json ="[{\"uuid\":\"1\",\"alias\":\"Nuria\",\"date\":\"10:30 am\",\"comentario\":\"Más información por favor.\"},{\"uuid\":\"2\",\"alias\":\"Raisa\",\"date\":\"13:00 pm\",\"comentario\":\"Saben si tiene tiene otra sucursal???\"},{\"uuid\":\"3\",\"alias\":\"Liz\",\"date\":\"17:24 pm\",\"comentario\":\"Hay que llevar carnet?\"},{\"uuid\":\"4\",\"alias\":\"Benjamin\",\"date\":\"22:00 pm\",\"comentario\":\"De 11:00 a 13:00 no atienden, por si acaso.\"}]";
        Type listType = new TypeToken<ArrayList<Comentarios>>() {
        }.getType();
        List<Comentarios> uniList = new Gson().fromJson(json, listType);
        results.postValue(new Base<>(uniList));
        return results;*/
    }

    @Override
    public LiveData<Base<List<Sabias>>> getSabias(String sabia) {
        return ApiRepository.getInstance().getSabias();
    }


}
