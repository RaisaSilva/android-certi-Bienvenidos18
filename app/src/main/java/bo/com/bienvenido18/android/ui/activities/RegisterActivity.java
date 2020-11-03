package bo.com.bienvenido18.android.ui.activities;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.utils.ErrorMapper;
import bo.com.bienvenido18.android.utils.Validations;
import bo.com.bienvenido18.android.viewModel.RegisterViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private static final String LOG = RegisterActivity.class.getName();

    private Context context;
    private RegisterViewModel registerViewModel;
    private RelativeLayout parentRelativeLayout;

    private TextInputEditText nameTextInputEditText;
    private TextInputEditText emailTextInputEditText;
    private TextInputEditText passwordTextInputEditText;
    private TextInputEditText passwordRepeatTextInputEditText;
    private ImageButton imageButton;


    private ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.context = this;
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        getSupportActionBar().hide();
        imageButton = findViewById(R.id.botonAtras);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });


        initViews();
        initEvents();
    }

    private void initViews() {
        parentRelativeLayout = findViewById(R.id.parentRelativeLayoutRegister);
        nameTextInputEditText = findViewById(R.id.nameTextInputEditText);
        emailTextInputEditText = findViewById(R.id.emailTextInputEditText);
        passwordTextInputEditText = findViewById(R.id.passwordTextInputEditText);
        passwordRepeatTextInputEditText = findViewById(R.id.passwordRepeatTextInputEditText);

    }

    private void initEvents() {

    }

    private void showLoading() {
        loading = new ProgressDialog(context);
        loading.setTitle("Cargando");
        loading.setCancelable(false);
        loading.show();
    }

    private void dismissLoading() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }

    public void registerUser(View view) {

        UserO user = new UserO(emailTextInputEditText.getText().toString(),
                passwordTextInputEditText.getText().toString());
        user.setDisplayName(nameTextInputEditText.getText().toString());
        /*if(!(passwordTextInputEditText.getText().toString().equals(passwordRepeatTextInputEditText.getText().tostr))) {

            Toast.makeText(context, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
        }*/

        showLoading();
        registerViewModel.register(user).observe(this, new Observer<Base<UserO>>() {
            @Override
            public void onChanged(Base<UserO> userBase) {
                dismissLoading();
                if (userBase.isSuccess()) {
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();


                } else {
                    Snackbar.make(parentRelativeLayout,
                            ErrorMapper.getError(context, userBase.getErrorCode()),
                            BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }
        });


    }


}