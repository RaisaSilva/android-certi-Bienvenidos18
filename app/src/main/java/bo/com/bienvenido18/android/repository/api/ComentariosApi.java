package bo.com.bienvenido18.android.repository.api;

import java.util.List;

import bo.com.bienvenido18.android.model.users.Comentarios;
import bo.com.bienvenido18.android.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ComentariosApi {
    @GET(Constants.RESOURCE_COMENTARIOS)
    Call<List<Comentarios>> getComentarios(@Query("alt") String alt);
}
