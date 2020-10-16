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
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;
import java.util.List;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.ui.adapter.AdapterU;
import bo.com.bienvenido18.android.ui.adapter.Universidades;
import bo.com.bienvenido18.android.ui.callBack.UniversidadesCallback;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.utils.ErrorMapper;
import bo.com.bienvenido18.android.viewModel.UniversidadesViewModel;

public class ActivityU extends AppCompatActivity implements UniversidadesCallback {
    private static final String LOG = ActivityU.class.getName();
    private Context context;
    private UniversidadesViewModel viewModel;
    private LinearLayout parentLinearLayout;
    private RecyclerView unisRecyclerView;
    private AdapterU adapter;

    private List<Universidades> universidades = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(LOG, "onCreate");
        setContentView(R.layout.list_university);
        context = this;

        //Injectando el viewModel
        viewModel = new ViewModelProvider(this).get(UniversidadesViewModel.class);

        initViews();
        initEvents();
        getIntentValues();
        subscribeToData();

        //recyclerU.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    private void initViews() {
        parentLinearLayout = findViewById(R.id.parentLinearLayout);
        unisRecyclerView = findViewById(R.id.RecyclerId);

        adapter = new AdapterU(universidades, context);
        unisRecyclerView.setAdapter(adapter);
        unisRecyclerView.setLayoutManager(
                new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        unisRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }
    private void initEvents() {
        adapter.setCallback(this);
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_DISPLAY_NAME)) {
            String displayName = intent.getStringExtra(Constants.KEY_DISPLAY_NAME);
            Toast.makeText(context, displayName, Toast.LENGTH_SHORT).show();
        }
    }

    private void subscribeToData() {

        viewModel.getUniversidades("").observe(this, new Observer<Base<List<Universidades>>>() {


            @Override
            public void onChanged(Base<List<Universidades>> listBase) {
                if (listBase.isSuccess()) {
                    universidades = listBase.getData();
                    adapter.updateItems(universidades);
                    Log.e("getStartups", new Gson().toJson(listBase));
                } else {
                    Toast.makeText(context, ErrorMapper.getError(context, listBase.getErrorCode()),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onUniversidadClicked(Universidades universidades) {
                Intent intent = new Intent(context,UniversidadesDetails.class);
                intent.putExtra(Constants.KEY_STARTUP_SELECTED, new Gson().toJson(universidades));
                startActivity(intent);

    }
}
