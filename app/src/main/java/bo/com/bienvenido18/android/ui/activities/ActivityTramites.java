package bo.com.bienvenido18.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.PostTramite;
import bo.com.bienvenido18.android.model.users.Tramites;
import bo.com.bienvenido18.android.ui.adapter.AdapterTramites;
import bo.com.bienvenido18.android.ui.callBack.TramitesCallback;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.utils.ErrorMapper;
import bo.com.bienvenido18.android.viewModel.TramitesViewModel;

public class ActivityTramites extends AppCompatActivity implements TramitesCallback {

    private static final String LOG = ActivityU.class.getName();
    private Context context;
    private TramitesViewModel viewModel;
    private LinearLayout linearLayout;
    private RecyclerView transRecyclerView;
    private AdapterTramites adapter;
    private List<Tramites> tramites = new ArrayList<>();
    private Button nuevoT;
    private PostTramite postTselected;
    // private LinearLayout mapaTramitesInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(LOG, "onCreate");
        setContentView(R.layout.list_procedures);
        context = this;

        //Injectando el viewModel
        viewModel = new ViewModelProvider(this).get(TramitesViewModel.class);
        getSupportActionBar().hide();

        initViews();
        initEvents();
        getIntentValues();
        subscribeToData();

        nuevoT= (Button)findViewById(R.id.nuevoTra);

        nuevoT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityTramites.this, CreatePostTramitesActivity.class));
            }
        });
    }
    private void initViews() {
        linearLayout = findViewById(R.id.layoutTramite);
        transRecyclerView = findViewById(R.id.RecyclerIdTramites);
        //mapaTramitesInfo=findViewById(R.id.mapaTramitesInfo);

        adapter = new AdapterTramites(tramites, context);
        transRecyclerView.setAdapter(adapter);
        transRecyclerView.setLayoutManager(
                new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        transRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        nuevoT = findViewById(R.id.nuevoTra);

    }
    private void initEvents() {
        adapter.setCallback(this);
       /* nuevoT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CreatePostTramitesActivity.class);
                intent.putExtra(Constants.KEY_TRAMITE_UUID_SELECTED, postTselected.getUuid());
                startActivity(intent);
            }
        });*/
    }



    private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_DISPLAY_NAME)) {
            String displayName = intent.getStringExtra(Constants.KEY_DISPLAY_NAME);
            Toast.makeText(context, displayName, Toast.LENGTH_SHORT).show();
        }
    }

    private void subscribeToData() {

        viewModel.getTramites("").observe(this, new Observer<Base<List<Tramites>>>() {

            @Override
            public void onChanged(Base<List<Tramites>> listBase) {
                if (listBase.isSuccess()) {
                    tramites = listBase.getData();
                    adapter.updateItems(tramites);
                    Log.e("getTramites", new Gson().toJson(listBase));
                } else {
                    Toast.makeText(context, ErrorMapper.getError(context, listBase.getErrorCode()),
                            Toast.LENGTH_SHORT).show();
                }




            }
        });
    }



    @Override
    public void OnTramitesClicked(Tramites tramites) {
        Intent intent = new Intent(context,TramitesDetail.class);
        intent.putExtra(Constants.KEY_STARTUP_SELECTED, new Gson().toJson(tramites));
        startActivity(intent);

    }

}
