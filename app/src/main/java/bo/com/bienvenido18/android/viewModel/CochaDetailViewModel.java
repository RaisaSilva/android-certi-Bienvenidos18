package bo.com.bienvenido18.android.viewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import bo.com.bienvenido18.android.model.users.UniversidadCocha;


public class CochaDetailViewModel extends ViewModel {
    private MutableLiveData<UniversidadCocha> universidadCocha = new MutableLiveData<>();

    public MutableLiveData<UniversidadCocha> getUniversidadCocha() {
        return universidadCocha;
    }

    public void setUniversidadCocha(UniversidadCocha universidadCocha) {

        this.universidadCocha.postValue(universidadCocha);
    }
}
