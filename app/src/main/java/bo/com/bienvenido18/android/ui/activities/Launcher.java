package bo.com.bienvenido18.android.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import bo.com.bienvenido18.android.R;

public class Launcher extends AppCompatActivity {
    Button tramite;
    Button universidad;
    Button sabias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        try
        {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}

        setContentView(R.layout.activity_launcher);

        tramite = (Button)findViewById(R.id.tramite);
        universidad = (Button)findViewById(R.id.universidad);
        sabias = (Button)findViewById(R.id.sabias);


        tramite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Launcher.this, Procedures.class));
            }
        });

        universidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Launcher.this, ActivityU.class));
            }
        });
        /*
        sabias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Launcher.this, sabias.class));
            }
        });

         */
        //prueba


    }
}