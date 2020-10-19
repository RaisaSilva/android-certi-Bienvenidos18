package bo.com.bienvenido18.android.repository.api;

import java.util.List;

import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TramitesApi {
    @GET(Constants.RESOURCE_TRAMITES)
    Call<List<Tramites>> getTramites(@Query("alt") String alt);

}
