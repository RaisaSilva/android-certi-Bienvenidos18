package bo.com.bienvenido18.android.repository.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private static final String LOG = ApiRepository.class.getSimpleName();

    private static ApiRepository instance;
    private TramitesApi tramitesApi;


    public static ApiRepository getInstance() {
        if (instance == null) {
            instance = new ApiRepository();
        }
        return instance;
    }

    public ApiRepository() {
        tramitesApi = ApiService.createService(TramitesApi.class);
    }

    public LiveData<Base<List<Tramites>>> getTramites() {
        MutableLiveData<Base<List<Tramites>>> results = new MutableLiveData<>();

        tramitesApi.getTramites(Constants.QUERY_PARAM_ALT).enqueue(new Callback<List<Tramites>>() {
            @Override
            public void onResponse(Call<List<Tramites>> call, Response<List<Tramites>> response) {
                if (response.isSuccessful()) {
                    results.postValue(new Base<>(response.body()));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_SERVER,
                            new Exception(response.message())));
                }
            }

            @Override
            public void onFailure(Call<List<Tramites>> call, Throwable t) {
                results.postValue(new Base<>(Constants.ERROR_NO_CONNECTION, new Exception(t)));
            }
        });
        return results;
    }

}


