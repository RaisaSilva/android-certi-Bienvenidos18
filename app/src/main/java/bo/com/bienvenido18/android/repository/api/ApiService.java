package bo.com.bienvenido18.android.repository.api;

import bo.com.bienvenido18.android.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static Retrofit retrofitComen = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_COMENTARIOS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static Retrofit retrofitSabias = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_SABIAS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createServiceSabias(Class<S> serviceClass) {
        return retrofitSabias.create(serviceClass);

    }

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);

    }
    public static <S> S createServiceComen(Class<S> serviceClass) {
        return retrofitComen.create(serviceClass);

    }
}
