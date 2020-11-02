package bo.com.bienvenido18.android.repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.PostTramite;
import bo.com.bienvenido18.android.model.users.Comentarios;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.model.users.UniversidadCocha;
import bo.com.bienvenido18.android.model.users.UniversidadSantaCruz;
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
        if (!Validations.isValidPasswoord(password)) {
            results.postValue(new Base(Constants.ERROR_wRONG_PASSWORD, null));
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
        String json = "[{\"uuid\":\"1\",\"displayName\":\"UPB\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRCIVg4c77tHkiOsB8N-8O0gMDlWN3y8i71vA&usqp=CAU\",\"links\":\" Para mas info visitanos en: https://www.upb.edu/es/sede_la_paz\",\"direccion\":\" Dirección: Av Hernando Siles, La Paz.\",\"telefonos\":\" Teléfonos: 2 2170000 \",\"carreras\":\"Carreras: Ingeniería de Sistemas, Administración de Empresas, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional, Música,Comunicación Social.\"},{\"uuid\":\"2\",\"displayName\":\"UMSA\",\"coverPhoto\":\"https://mir-s3-cdn-cf.behance.net/projects/404/084ce265138891.Y3JvcCwxNTEzLDExODQsNDI2LDA.png\",\"links\":\" Para mas info visitanos en: https://www.umsa.bo/\",\"direccion\":\"Dirección: Av. Villazón Nro.1995, Monoblock Central\",\"telefonos\":\"Teléfonos: 800-16-0013\",\"carreras\":\"Carreras: Ingeniería, Medicina, Exactas, Economía, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"3\",\"displayName\":\"LA SALLE\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT5FULZCQqOH4E2jIx--Ew3dN4pYuq6-Lxpew&usqp=CAU\",\"links\":\" Para mas info visitanos en: https://www.ulasalle.edu.bo/sp/\",\"direccion\":\"Dirección: Av. Jorge Carrasco esq. Las Palmas Nº450 (Bolognia)\",\"telefonos\":\"Teléfonos: 2 2723588\",\"carreras\":\"Carreras: Ingeniería, Economía, Administración de empresas, Arquitectura, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"4\",\"displayName\":\"UCB\",\"coverPhoto\":\"https://upload.wikimedia.org/wikipedia/commons/1/1f/Ucatolica2.jpg\",\"links\":\" Para mas info visitanos en: https://www.ucb.edu.bo/\",\"direccion\":\"Dirección:  Av 14 de Septiembre Nº 4807\",\"telefonos\":\"Teléfonos: 2 2782222 \",\"carreras\":\"Carreras: Ingeniería Comercial , Derecho, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"5\",\"displayName\":\"UTB\",\"coverPhoto\":\"https://utb.edu.bo/wp-content/uploads/2018/04/logo.jpg\",\"links\":\" Para mas info visitanos en: https://utb.edu.bo/\",\"direccion\":\" Dirección: Calle Colombia #154, 14100 Ciudad La Paz, Bolivia.\",\"telefonos\":\"Teléfonos: 2 2180808\",\"carreras\":\"Economía, Contabilidad, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"6\",\"displayName\":\"UNIFRANZ\",\"coverPhoto\":\"https://www.universidadesonline.com.bo/logos/original/logo-universidad-privada-franz-tamayo.png\",\"links\":\" Para mas info visitanos en: https://unifranz.edu.bo/\",\"direccion\":\"Dirección: Calle Héroes del Acre No. 1855 esquina Landaeta\",\"telefonos\":\" Teléfonos:  2487700\",\"carreras\":\"Odontología, Comunicación, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática\"},{\"uuid\":\"7\",\"displayName\":\"UNIVALLE\",\"coverPhoto\":\"https://www.paginasiete.bo/u/fotografias/m/2020/10/4/f1280x720-328051_459726_5050.jpg\",\"links\":\" Para mas info visitanos en: http://www.univalle.edu/lapaz/\",\"direccion\":\"Dirección: Av. Argentina Nº 2083, La Paz\",\"telefonos\":\"Teléfono: 2 2246725\",\"carreras\":\"Carreras: Medicina, Odontología,Gastronomia,Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"8\",\"displayName\":\"EMI\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQpg0HZ6Q3xs2UtvU6vFDqzOyQ_JPeSgWZvZQ&usqp=CAU\",\"links\":\" Para mas info visitanos en: https://www.emi.edu.bo/\",\"direccion\":\"Dirección:  Central Av. Arce No. 2642\",\"telefonos\":\"Telefonos: 2775536 - 2799505\",\"carreras\":\" Carreras: Ingeniería Ambiental,Ingeniería Civil,Ingeniería Industrial,Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"}]";
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

    @Override
    public LiveData<Base<List<Comentarios>>> getComentarios(String comen) {
        MutableLiveData<Base<List<Comentarios>>> results = new MutableLiveData<>();
        String json ="[{\"uuid\":\"1\",\"alias\":\"Nuria\",\"date\":\"10:30 am\",\"comentario\":\"Más información por favor.\"},{\"uuid\":\"2\",\"alias\":\"Raisa\",\"date\":\"13:00 pm\",\"comentario\":\"Saben si tiene tiene otra sucursal???\"},{\"uuid\":\"3\",\"alias\":\"Liz\",\"date\":\"17:24 pm\",\"comentario\":\"Hay que llevar carnet?\"},{\"uuid\":\"4\",\"alias\":\"Benjamin\",\"date\":\"22:00 pm\",\"comentario\":\"De 11:00 a 13:00 no atienden, por si acaso.\"}]";
        Type listType = new TypeToken<ArrayList<Comentarios>>() {
        }.getType();
        List<Comentarios> uniList = new Gson().fromJson(json, listType);
        results.postValue(new Base<>(uniList));
        return results;
    }

    @Override
    public LiveData<Base<List<UniversidadCocha>>> getUniversidadesCocha(String cocha) {
        MutableLiveData<Base<List<UniversidadCocha>>> results = new MutableLiveData<>();
        String json = "[{\"uuid\":\"1\",\"displayNameCocha\":\"UMSS\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTy3NzLxQ1igVCxSBysoC_2v-nMy4aSmblOOQ&usqp=CAU\",\"linksCocha\":\" Para mas info visitanos en: www.umss.edu.bo\",\"direccionCocha\":\" Dirección: Rectorado Av. Ballivián esquina Reza #591 Cochabamba, Bolivia\",\"telefonosCocha\":\" Teléfonos: 4-4524768 / 4220717 \",\"carrerasCocha\":\"Carreras: Ingeniería de Sistemas, Administración de Empresas, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional, Música,Comunicación Social.\"},{\"uuid\":\"2\",\"displayNameCocha\":\"UAB\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSzSKje-CUIhULZSvzFCdaIg-2oQIW2BWhgUw&usqp=CAU\",\"linksCocha\":\" Para mas info visitanos en: www.uab.edu.bo\",\"direccionCocha\":\"Dirección:  Av. Simón I. Patiño Km 1, Vinto\",\"telefonosCocha\":\"Teléfonos: 4-4263330 / 4263331\",\"carrerasCocha\":\"Carreras: Ingeniería, Medicina, Exactas, Economía, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"3\",\"displayNameCocha\":\"UNICEN\",\"coverPhoto\":\"https://telescopi.upsa.edu.bo/universidades/LOGO_UNICEN.jpg\",\"linksCocha\":\" Para mas info visitanos en: www.unicen.edu.bo\",\"direccionCocha\":\"Dirección: Calle Santiváñez N° 216\",\"telefonosCocha\":\"Teléfonos:  4–4252987 \",\"carrerasCocha\":\"Carreras: Ingeniería, Economía, Administración de empresas, Arquitectura, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"4\",\"displayNameCocha\":\"ULAT\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQV6VQ9UCYniDEcC8AsXU6nb8p2laqHGK5FPQ&usqp=CAU\",\"linksCocha\":\" Para mas info visitanos en: www.ulat.edu.bo\",\"direccionCocha\":\"Dirección: Calle Uruguay No E-0836\",\"telefonosCocha\":\"Teléfonos: 4572321 \",\"carrerasCocha\":\"Carreras: Ingeniería Comercial , Derecho, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"5\",\"displayNameCocha\":\"NUR\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSZWo3OrJXQIkIgEJc5lJjme8tD3fwavIuiAQ&usqp=CAU\",\"linksCocha\":\" Para mas info visitanos en: www.nur.edu\",\"direccionCocha\":\" Dirección: Calle La Reza N° 265 entre Junín y Hamiraya\",\"telefonosCocha\":\"Teléfonos: 4527607\",\"carrerasCocha\":\"Economía, Contabilidad, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"6\",\"displayNameCocha\":\"USIP\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT96V3EFWiO9tPSjWKMAmveHU2sZUQgI_6uig&usqp=CAU\",\"linksCocha\":\" Para mas info visitanos en: www.usip.edu.bo\",\"direccionCocha\":\"Dirección: Av. Villazón Nº 22, km 1 a Sacaba.\",\"telefonosCocha\":\" Teléfonos:  4539930\",\"carrerasCocha\":\"Odontología, Comunicación, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática\"},{\"uuid\":\"7\",\"displayNameCocha\":\"UNITEPC\",\"coverPhoto\":\"https://unitepc.edu.bo/uploads-page/2019-07/NEW190719183410-392_1838-392_n.jpg\",\"linksCocha\":\" Para mas info visitanos en: www.unitepc.edu.bo\",\"direccionCocha\":\"Dirección: Av. Blanco Galindo Km 71/2, zona Florida norte\",\"telefonosCocha\":\"Teléfono: 4258862\",\"carrerasCocha\":\"Carreras: Medicina, Odontología,Gastronomia,Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"}]";
        Type listType = new TypeToken<ArrayList<UniversidadCocha>>() {
        }.getType();
        List<UniversidadCocha> unicocha = new Gson().fromJson(json, listType);
        results.postValue(new Base<>(unicocha));
        return results;
    }

    @Override
    public LiveData<Base<List<UniversidadSantaCruz>>> getUniversidadesSanta(String scz) {
        MutableLiveData<Base<List<UniversidadSantaCruz>>> results = new MutableLiveData<>();
        String json = "[{\"uuid\":\"1\",\"displayNameS\":\"UAGRM\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRe_aX03w0B-SDHUpEQOsE1ExI3GzWTla4WTw&usqp=CAU\",\"linksS\":\" Para mas info visitanos en:www.uagrm.edu.bo\",\"direccionS\":\" Dirección: Plaza 24 de Septiembre\",\"telefonosS\":\" Teléfonos: (591) 3-3365544 \",\"carrerasS\":\"Carreras: Ingeniería de Sistemas, Administración de Empresas, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional, Música,Comunicación Social.\"},{\"uuid\":\"2\",\"displayNameS\":\"UNO\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhojWWI6pjrGXmeP0pcscTPSpxVnRkbqgg6g&usqp=CAU\",\"linksS\":\" Para mas info visitanos en: www.uno.edu.bo\",\"direccionS\":\"Dirección:  España Nº 383 entre Seoane y Buenos Aires\",\"telefonosS\":\"Teléfonos:  3 37 79 51\",\"carrerasS\":\"Carreras: Ingeniería, Medicina, Exactas, Economía, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"3\",\"displayNameS\":\"UPSA\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSys2ub2qjPTyyc7wYlfAP_VIgbw5dDumBnag&usqp=CAU\",\"linksS\":\" Para mas info visitanos en: www.upsa.edu.bo\",\"direccionS\":\"Dirección: Campus Universitario: Av. Paraguá y 4to. Anillo\",\"telefonosS\":\"Teléfonos: 346 4000\",\"carrerasS\":\"Carreras: Ingeniería, Economía, Administración de empresas, Arquitectura, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"4\",\"displayNameS\":\"UTEPSA\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS9YcTSB_Tey2PTlENQx1_dsu15k0eyXOzFWg&usqp=CAU\",\"linksS\":\" Para mas info visitanos en: www.utepsa.edu\",\"direccionS\":\"Campus: 3er. anillo interno Nº 715 entre Av. Busch y Av. San Martín\",\"telefonosS\":\"Teléfonos:  363-9000 \",\"carrerasS\":\"Carreras: Ingeniería Comercial , Derecho, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"5\",\"displayNameS\":\"UPDS\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBReKr1V9_GrwpYPKnoajjX9_qxbPu3cozFA&usqp=CAU\",\"linksS\":\" Para mas info visitanos en: www.upds.edu.bo\",\"direccionS\":\" Dirección: Av. Beni y 3er. Anillo Externo\",\"telefonosS\":\"Teléfonos: 342 6600\",\"carrerasS\":\"Economía, Contabilidad, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática,\"},{\"uuid\":\"6\",\"displayNameS\":\"UCEBOL\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTTaqJ8hAKlVDI1LS_Hk9hlzYhGCq9CYuRQzQ&usqp=CAU\",\"linksS\":\" Para mas info visitanos en:  www.ucebol.edu.bo\",\"direccionS\":\"Dirección:  Km. 5 Carretera al Norte\",\"telefonosS\":\" Teléfonos:  3453247\",\"carrerasS\":\"Odontología, Comunicación, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática\"},{\"uuid\":\"7\",\"displayNameS\":\"UEB\",\"coverPhoto\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTnn8EaVlpin65nbZC7dq7ItmQq85bI8l57gw&usqp=CAU\",\"linksS\":\" Para mas info visitanos en:  www.www.ueb.edu.bo\",\"direccionS\":\"Dirección: Campus Universitario: Barrio Cruz del sur U.V.117 Av. Moscú.\",\"telefonosS\":\" Teléfonos: 356-0991\",\"carrerasS\":\"Odontología, Comunicación, Psicología,Relaciones internacionales,Cinematografía,Comercio internacional,Música,Comunicación Social,Veterinaria,Nutrición,Fisioterapia,Infromática\"}]";
        Type listType = new TypeToken<ArrayList<UniversidadSantaCruz>>() {
        }.getType();
        List<UniversidadSantaCruz> uniscz = new Gson().fromJson(json, listType);
        results.postValue(new Base<>(uniscz));
        return results;

    }

    @Override
    public LiveData<Base<String>> addPostToTramite(String uuidTramite, PostTramite postTramite, Uri image) {
        return null;
    }

    @Override
    public LiveData<Base<List<PostTramite>>> observeTramitePost(String uuiTramite) {
        return null;
    }
    @Override
    public LiveData<Base<UserO>> registerUser(UserO user) {
        return null;
    }

    @Override
    public LiveData<Base<UserO>> getCurrentUser() {
        return null;
    }
    @Override
    public void signOut() {

    }


}
