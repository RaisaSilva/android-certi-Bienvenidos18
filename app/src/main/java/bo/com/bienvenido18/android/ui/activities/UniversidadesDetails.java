package bo.com.bienvenido18.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.ui.adapter.AdapterU;
import bo.com.bienvenido18.android.ui.adapter.Universidades;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.viewModel.UniversidadesDetailViewModel;
import bo.com.bienvenido18.android.viewModel.UniversidadesViewModel;

public class UniversidadesDetails extends AppCompatActivity {
    private static final String LOG = UniversidadesDetails.class.getSimpleName();
    private Context context;
    private LinearLayout layout;
    private ImageView foto;
    private TextView nombre;
    private TextView direc;
    private TextView telefono;
    private TextView carreras;

    private UniversidadesDetailViewModel viewModel;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universidad_details_layout);
        context = this;
        viewModel = new ViewModelProvider(this).get(UniversidadesDetailViewModel.class);
        Universidades universidades = this.gson.fromJson(getIntent().
                getStringExtra(Constants.KEY_STARTUP_SELECTED), Universidades.class);

        initViews();
        fillUniversidadData(universidades);
        //initEvents();

    }

    private void fillUniversidadData(Universidades universidades) {
        this.direc.setText(universidades.getAddress());
        this.nombre.setText(universidades.getDisplayName());
        this.telefono.setText(universidades.getTelefonos());
        this.carreras.setText(universidades.getCarreras());
        Picasso.get().load(universidades.getCoverPhoto()).into(foto);

    }

    private void initViews() {
        this.foto = findViewById(R.id.imagDetalle);
        this.direc = findViewById(R.id.textoDetalle);
        this.nombre =findViewById(R.id.nombreU);
        this.telefono =findViewById(R.id.telefonoDetalle);
        this.carreras =findViewById(R.id.CarrerasDetalle);

    }

    /*private void initViews() {
        layout = findViewById(R.id.layout_detail);
        foto = findViewById(R.id.imagDetalle);
        direc = findViewById(R.id.textoDetalle);
        //getIntentValues();

    }

    private void initEvents() {



    }

    @Override
    protected void onStart() {
        super.onStart();
        getIntentValues();
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_STARTUP_SELECTED)) {
            try {
                String json = intent.getStringExtra(Constants.KEY_STARTUP_SELECTED);
                Universidades universidades = new Gson().fromJson(json, Universidades.class);
                Log.e("Tiempo", "getIntentValues");
                viewModel.setUniversidades(universidades);
                Log.e("StartupName", universidades.getDisplayName());
            } catch (Exception ex) {
                Log.e(LOG, "getIntentValues", ex);
            }
        }
    }*/




}
