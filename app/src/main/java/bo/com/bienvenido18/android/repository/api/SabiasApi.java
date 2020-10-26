package bo.com.bienvenido18.android.repository.api;

import java.util.List;

import bo.com.bienvenido18.android.model.users.Sabias;
import bo.com.bienvenido18.android.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SabiasApi {
    @GET(Constants.RESOURCE_SABIAS)
    Call<List<Sabias>> getSabias(@Query("alt") String alt);
}
