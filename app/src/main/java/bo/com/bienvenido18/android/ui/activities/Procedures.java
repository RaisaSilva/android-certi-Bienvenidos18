package bo.com.bienvenido18.android.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.ui.adapter.RecyclerViewAdapter;

public class Procedures extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    int []arr={R.drawable.autos,R.drawable.proced, R.drawable.propiedad, R.drawable.vivienda, R.drawable.images,R.drawable.autos,R.drawable.proced, R.drawable.propiedad};

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_procedures);
        recyclerView=findViewById(R.id.RecyclerIdProcedure);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(arr);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);

    }
}
