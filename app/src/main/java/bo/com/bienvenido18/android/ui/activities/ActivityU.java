package bo.com.bienvenido18.android.ui.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.ui.adapter.AdapterU;
import bo.com.bienvenido18.android.ui.adapter.Universidades;

public class ActivityU extends AppCompatActivity {
    ArrayList<Universidades> listaUniversidades;
    RecyclerView recyclerU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_university);
        listaUniversidades = new ArrayList<>();
        recyclerU = findViewById(R.id.RecyclerId);
        recyclerU.setLayoutManager(new LinearLayoutManager(this));
        recyclerU.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        Init();
        AdapterU adapterU = new AdapterU(listaUniversidades);
        recyclerU.setAdapter(adapterU);

    }

    private void Init() {
        listaUniversidades.add(new Universidades("UPB",R.drawable.ic_school_black_24dp));
        listaUniversidades.add(new Universidades("UMSA",R.drawable.ic_school_black_24dp));
        listaUniversidades.add(new Universidades("LA SALLE",R.drawable.ic_school_black_24dp));
        listaUniversidades.add(new Universidades("UCB",R.drawable.ic_school_black_24dp));
        listaUniversidades.add(new Universidades("UTB",R.drawable.ic_school_black_24dp));
        listaUniversidades.add(new Universidades("UNIFRANZ",R.drawable.ic_school_black_24dp));
        listaUniversidades.add(new Universidades("UNIVALLE",R.drawable.ic_school_black_24dp));

    }

}
