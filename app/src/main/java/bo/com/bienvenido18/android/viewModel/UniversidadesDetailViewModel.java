package bo.com.bienvenido18.android.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import bo.com.bienvenido18.android.ui.adapter.Universidades;

public class UniversidadesDetailViewModel extends ViewModel {
    private MutableLiveData<Universidades> universidades = new MutableLiveData<>();

    public MutableLiveData<Universidades> getUniversidades() {
        return universidades;
    }

    public void setUniversidades(Universidades universidades) {

        this.universidades.postValue(universidades);
    }
}
