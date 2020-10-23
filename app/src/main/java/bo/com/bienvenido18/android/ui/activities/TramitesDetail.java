package bo.com.bienvenido18.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.Comentarios;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.ui.adapter.AdapterTramites;
import bo.com.bienvenido18.android.ui.adapter.AdapterTramitesComentarios;
import bo.com.bienvenido18.android.ui.adapter.Universidades;
import bo.com.bienvenido18.android.ui.callBack.ComentariosCallback;
import bo.com.bienvenido18.android.ui.viewHolder.ViewHolderComentarios;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.utils.ErrorMapper;
import bo.com.bienvenido18.android.viewModel.ComentariosViewModel;
import bo.com.bienvenido18.android.viewModel.TramiteDetailViewModel;
import bo.com.bienvenido18.android.viewModel.UniversidadesDetailViewModel;

public class TramitesDetail extends AppCompatActivity implements ComentariosCallback {

    private static final String LOG = TramitesDetail.class.getSimpleName();
    private Context context;
    private LinearLayout linearLayout;
    private ImageView foto;
    private TextView title;
    private TramiteDetailViewModel viewModel;
    private Gson gson = new Gson();
    private RecyclerView recyclerViewComentario;
    private ComentariosViewModel comentariosViewModel;
    private AdapterTramitesComentarios adapterTramitesComentarios;
    private List<Comentarios> comentarioslist =new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tramite_detail);
        context = this;
        viewModel = new ViewModelProvider(this).get(TramiteDetailViewModel.class);
        comentariosViewModel = new ViewModelProvider(this).get(ComentariosViewModel.class);
        Tramites tramites = this.gson.fromJson(getIntent().

                getStringExtra(Constants.KEY_STARTUP_SELECTED), Tramites.class);

        initViews();
        fillTramitesData(tramites);
        //getIntentValues();
        suscribeToData();
        initEvents();

    }

    private void suscribeToData() {
        comentariosViewModel.getComentarios("").observe(this, new Observer<Base<List<Comentarios>>>() {
            @Override
            public void onChanged(Base<List<Comentarios>> listBase) {
                if (listBase.isSuccess()) {
                    comentarioslist = listBase.getData();
                    adapterTramitesComentarios.updateItems(comentarioslist);
                    Log.e("getTramites", new Gson().toJson(listBase));
                } else {
                    Toast.makeText(context, ErrorMapper.getError(context, listBase.getErrorCode()),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
 /*   private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_DISPLAY_NAME)) {
            String displayName = intent.getStringExtra(Constants.KEY_DISPLAY_NAME);
            Toast.makeText(context, displayName, Toast.LENGTH_SHORT).show();
        }
    }*/

    private void fillTramitesData(Tramites tramites) {

        this.title.setText(tramites.getTitlePhoto());
        Picasso.get().load(tramites.getCoverPhoto()).into(foto);


    }

    private void initViews() {
        this.title = findViewById(R.id.textView);
        this.foto = findViewById(R.id.imageView);
        linearLayout =findViewById(R.id.detallesCom);
        recyclerViewComentario = findViewById(R.id.recyclerComen);
        adapterTramitesComentarios = new AdapterTramitesComentarios(comentarioslist, context);
        recyclerViewComentario.setAdapter(adapterTramitesComentarios);
        recyclerViewComentario.setLayoutManager(
                new LinearLayoutManager(context,RecyclerView.VERTICAL,false));

    }

    @Override
    public void OnComentariosClicked(Comentarios comentarios) {

        Intent intent = new Intent(context,TramitesDetail.class);
        intent.putExtra(Constants.KEY_STARTUP_SELECTED, new Gson().toJson(comentarios));
        startActivity(intent);

    }

    private void initEvents() {
        adapterTramitesComentarios.setCallBackComentarios(this);
    }
}
