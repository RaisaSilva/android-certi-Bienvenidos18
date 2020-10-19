package bo.com.bienvenido18.android.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.ui.adapter.Universidades;

public class TramiteDetailViewModel extends ViewModel {
    private MutableLiveData<Tramites> tramites = new MutableLiveData<>();

    public MutableLiveData<Tramites> getTramites() {
        return tramites;
    }

    public void setTramites(Tramites tramites) {

        this.tramites.postValue(tramites);
    }
}
