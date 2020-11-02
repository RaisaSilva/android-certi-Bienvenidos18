package bo.com.bienvenido18.android.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.viewModel.LauncherViewModel;

public class Launcher extends AppCompatActivity {
    private LauncherViewModel viewModel;
    Button tramite;
    Button universidad;
    Button sabias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        viewModel = new ViewModelProvider(this).get(LauncherViewModel.class);
        getSupportActionBar().hide();




        tramite = (Button) findViewById(R.id.tramite);
        universidad = (Button) findViewById(R.id.universidad);
        sabias = (Button) findViewById(R.id.sabias);


        tramite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Launcher.this, ActivityTramites.class));
            }
        });

        universidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Launcher.this, menuCuidades.class));
            }
        });

        sabias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Launcher.this, SabiasActivity.class));
            }
        });
    }
    public void logout(View view){
        viewModel.logout();
        finish();
    }
}