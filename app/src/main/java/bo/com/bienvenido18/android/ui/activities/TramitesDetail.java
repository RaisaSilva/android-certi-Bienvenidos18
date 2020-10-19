package bo.com.bienvenido18.android.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.ui.adapter.Universidades;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.viewModel.TramiteDetailViewModel;
import bo.com.bienvenido18.android.viewModel.UniversidadesDetailViewModel;

public class TramitesDetail extends AppCompatActivity {

    private static final String LOG = TramitesDetail.class.getSimpleName();
    private Context context;
    private LinearLayout linearLayout;
    private ImageView foto;
    private TextView title;
    private TramiteDetailViewModel viewModel;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tramite_detail);
        context = this;
        viewModel = new ViewModelProvider(this).get(TramiteDetailViewModel.class);
        Tramites tramites = this.gson.fromJson(getIntent().
                getStringExtra(Constants.KEY_STARTUP_SELECTED), Tramites.class);

        initViews();
        fillTramitesData(tramites);
        //initEvents();

    }

    private void fillTramitesData(Tramites tramites) {

        this.title.setText(tramites.getTitlePhoto());
        Picasso.get().load(tramites.getCoverPhoto()).into(foto);

    }

    private void initViews() {
        this.title = findViewById(R.id.textView);
        this.foto = findViewById(R.id.imageView);

    }

}
