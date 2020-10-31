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

import bo.com.bienvenido18.android.model.users.UniversidadSantaCruz;

import bo.com.bienvenido18.android.ui.adapter.AdapterSanta;
import bo.com.bienvenido18.android.ui.callBack.USantaCruzCallback;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.utils.ErrorMapper;
import bo.com.bienvenido18.android.viewModel.SantaViewModel;;

public class ActivityUSantaCruz  extends AppCompatActivity implements USantaCruzCallback {
    private static final String LOG = ActivityUCocha.class.getName();
    private Context context;
    private SantaViewModel viewModel;
    private LinearLayout listaSanta;
    private RecyclerView unisSantaRecyclerView;
    private AdapterSanta adapter;
    private List<UniversidadSantaCruz> uSanta = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(LOG, "onCreate");
        setContentView(R.layout.list_uni_santa);
        context = this;
        viewModel = new ViewModelProvider(this).get(SantaViewModel.class);
        getSupportActionBar().hide();

        initViews();
        initEvents();
        getIntentValues();
        subscribeToData();
    }

    private void subscribeToData() {
        viewModel.getUniversidadesSanta("").observe(this, new Observer<Base<List<UniversidadSantaCruz>>>() {

            @Override
            public void onChanged(Base<List<UniversidadSantaCruz>> listBase) {
                if (listBase.isSuccess()) {
                    uSanta = listBase.getData();
                    adapter.updateItems(uSanta);
                    Log.e("getUSanta", new Gson().toJson(listBase));
                } else {
                    Toast.makeText(context, ErrorMapper.getError(context, listBase.getErrorCode()),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_DISPLAY_NAME)) {
            String displayNameScz = intent.getStringExtra(Constants.KEY_DISPLAY_NAME);
            Toast.makeText(context, displayNameScz, Toast.LENGTH_SHORT).show();
        }

    }

    private void initEvents() {
        adapter.setCallback(this);

    }

    private void initViews() {
        listaSanta = findViewById(R.id.listaSantaCruz);
        unisSantaRecyclerView = findViewById(R.id.recyclerSanta);
        adapter = new AdapterSanta(uSanta, context);
        unisSantaRecyclerView.setAdapter(adapter);
        unisSantaRecyclerView.setLayoutManager(
                new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        unisSantaRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onUniversidadSantaClicked(UniversidadSantaCruz universidadSantaCruz) {
        Intent intent = new Intent(context,SantaDetails.class);
        intent.putExtra(Constants.KEY_STARTUP_SELECTED, new Gson().toJson(universidadSantaCruz));
        startActivity(intent);

    }
}
