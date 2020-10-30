package bo.com.bienvenido18.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.UniversidadCocha;
import bo.com.bienvenido18.android.ui.adapter.AdapterCocha;
import bo.com.bienvenido18.android.ui.callBack.UCochaCallback;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.utils.ErrorMapper;
import bo.com.bienvenido18.android.viewModel.CochaViewModel;



public class ActivityUCocha extends AppCompatActivity implements UCochaCallback {
    private static final String LOG = ActivityUCocha.class.getName();
    private Context context;
    private CochaViewModel viewModel;
    private LinearLayout listaCocha;
    private RecyclerView unisCochaRecyclerView;
    private AdapterCocha adapter;
    private List<UniversidadCocha> uCocha = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(LOG, "onCreate");
        setContentView(R.layout.list_uni_cocha);
        context = this;

        viewModel = new ViewModelProvider(this).get(CochaViewModel.class);
        getSupportActionBar().hide();

        initViews();
        initEvents();
        getIntentValues();
        subscribeToData();
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_DISPLAY_NAME)) {
            String displayNameCocha = intent.getStringExtra(Constants.KEY_DISPLAY_NAME);
            Toast.makeText(context, displayNameCocha, Toast.LENGTH_SHORT).show();
        }
    }


    private void initViews() {
        listaCocha = findViewById(R.id.listaCocha);
        unisCochaRecyclerView = findViewById(R.id.RecyclerIdCocha);
        adapter = new AdapterCocha(uCocha, context);
        unisCochaRecyclerView.setAdapter(adapter);
        unisCochaRecyclerView.setLayoutManager(
                new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        unisCochaRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    private void initEvents() {
        adapter.setCallback(this);
    }

    private void subscribeToData() {

        viewModel.getUniversidadesCocha("").observe(this, new Observer<Base<List<UniversidadCocha>>>() {

            @Override
            public void onChanged(Base<List<UniversidadCocha>> listBase) {
                if (listBase.isSuccess()) {
                    uCocha = listBase.getData();
                    adapter.updateItems(uCocha);
                    Log.e("getUCocha", new Gson().toJson(listBase));
                } else {
                    Toast.makeText(context, ErrorMapper.getError(context, listBase.getErrorCode()),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onUniversidadCochaClicked(UniversidadCocha universidadCocha) {
        Intent intent = new Intent(context,CochaDetails.class);
        intent.putExtra(Constants.KEY_STARTUP_SELECTED, new Gson().toJson(universidadCocha));
        startActivity(intent);
    }
}
