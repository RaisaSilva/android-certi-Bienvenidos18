package bo.com.bienvenido18.android.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import bo.com.bienvenido18.android.R;

public class menuCuidades  extends AppCompatActivity {
    Button LaPaz;
    Button Cochabamba;
    Button SantaCruz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_departamentos);
        getSupportActionBar().hide();




        LaPaz = (Button)findViewById(R.id.la_paz);
        Cochabamba = (Button)findViewById(R.id.cochabamba);
        SantaCruz = (Button)findViewById(R.id.santa_cruz);

        LaPaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuCuidades.this, ActivityU.class));

            }
        });

        Cochabamba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuCuidades.this, ActivityUCocha.class));

            }
        });

        SantaCruz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuCuidades.this, ActivityUSantaCruz.class));

            }
        });




}
    }

