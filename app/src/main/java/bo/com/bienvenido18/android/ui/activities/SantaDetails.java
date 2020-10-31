package bo.com.bienvenido18.android.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.users.UniversidadSantaCruz;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.viewModel.SantaDetailViewModel;

public class SantaDetails extends AppCompatActivity {
    private static final String LOG = CochaDetails.class.getSimpleName();
    private Context context;
    private LinearLayout layout;
    private ImageView fotoS;
    private TextView nombreS;
    private TextView direcS;
    private TextView telefonoS;
    private TextView carrerasS;
    private TextView linksS;
    private SantaDetailViewModel viewModel;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.santa_detail);
        context = this;
        viewModel = new ViewModelProvider(this).get(SantaDetailViewModel.class);
        getSupportActionBar().hide();
        UniversidadSantaCruz universidadSantaCruz = this.gson.fromJson(getIntent().
                getStringExtra(Constants.KEY_STARTUP_SELECTED), UniversidadSantaCruz.class);
        initViews();
        fillUniversidadSantaData(universidadSantaCruz);
    }

    private void fillUniversidadSantaData(UniversidadSantaCruz universidadSantaCruz) {
        this.direcS.setText(universidadSantaCruz.getAddressS());
        this.nombreS.setText(universidadSantaCruz.getDisplayNameS());
        this.telefonoS.setText(universidadSantaCruz.getTelefonosS());
        this.carrerasS.setText(universidadSantaCruz.getCarrerasS());
        this.linksS.setText(universidadSantaCruz.getLinksS());
        Picasso.get().load(universidadSantaCruz.getCoverPhoto()).into(fotoS);

    }

    private void initViews() {
        this.fotoS = findViewById(R.id.imagDetalleSanta);
        this.direcS = findViewById(R.id.textoDetalleSanta);
        this.nombreS =findViewById(R.id.nombreUSanta);
        this.telefonoS =findViewById(R.id.telefonoDetalleSanta);
        this.carrerasS =findViewById(R.id.CarrerasDetalleSanta);
        this.linksS =findViewById(R.id.linkDetalleSanta);

    }



}
