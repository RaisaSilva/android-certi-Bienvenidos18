package bo.com.bienvenido18.android.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import bo.com.bienvenido18.android.model.users.UniversidadSantaCruz;

public class SantaDetailViewModel extends ViewModel {
    private MutableLiveData<UniversidadSantaCruz> universidadSantaCruz= new MutableLiveData<>();

    public MutableLiveData<UniversidadSantaCruz> getUniversidadSantaCruz() {
        return universidadSantaCruz;
    }

    public void setUniversidadSantaCruz(UniversidadSantaCruz universidadSantaCruz) {

        this.universidadSantaCruz.postValue(universidadSantaCruz);
    }
}
