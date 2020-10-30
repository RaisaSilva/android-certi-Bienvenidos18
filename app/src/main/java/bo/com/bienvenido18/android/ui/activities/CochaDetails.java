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
import bo.com.bienvenido18.android.model.users.UniversidadCocha;
import bo.com.bienvenido18.android.ui.adapter.Universidades;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.viewModel.CochaDetailViewModel;
import bo.com.bienvenido18.android.viewModel.UniversidadesDetailViewModel;

public class CochaDetails extends AppCompatActivity {
    private static final String LOG = CochaDetails.class.getSimpleName();
    private Context context;
    private LinearLayout layout;
    private ImageView fotoC;
    private TextView nombreC;
    private TextView direcC;
    private TextView telefonoC;
    private TextView carrerasC;
    private TextView linksC;

    private CochaDetailViewModel viewModel;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cocha_detail);
        context = this;
        viewModel = new ViewModelProvider(this).get(CochaDetailViewModel.class);
        getSupportActionBar().hide();
        UniversidadCocha universidadCocha = this.gson.fromJson(getIntent().
                getStringExtra(Constants.KEY_STARTUP_SELECTED), UniversidadCocha.class);
        initViews();
        fillUniversidadCochaData(universidadCocha);


    }

    private void fillUniversidadCochaData(UniversidadCocha universidadCocha) {
        this.direcC.setText(universidadCocha.getAddressCocha());
        this.nombreC.setText(universidadCocha.getDisplayNameCocha());
        this.telefonoC.setText(universidadCocha.getTelefonosCocha());
        this.carrerasC.setText(universidadCocha.getCarrerasCocha());
        this.linksC.setText(universidadCocha.getLinksCocha());
        Picasso.get().load(universidadCocha.getCoverPhoto()).into(fotoC);

    }

    private void initViews() {
        this.fotoC = findViewById(R.id.imagDetalleCocha);
        this.direcC = findViewById(R.id.textoDetalleCocha);
        this.nombreC =findViewById(R.id.nombreUCocha);
        this.telefonoC =findViewById(R.id.telefonoDetalleCocha);
        this.carrerasC =findViewById(R.id.CarrerasDetalleCocha);
        this.linksC =findViewById(R.id.linkDetalleCocha);

    }
}
