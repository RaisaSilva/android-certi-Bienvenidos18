package bo.com.bienvenido18.android.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.users.UserO;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.utils.ErrorMapper;
import bo.com.bienvenido18.android.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();
    private Context context;

    private RelativeLayout parentRelativeLayout;



    private LinearLayout backgroundLoginLinearLayout;
    private EditText emailEditText;
    private EditText passwordEditText;
    private ImageButton loginButton;

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG, "onCreate");

        setContentView(R.layout.activity_login);

        context = this;
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.getCurrentUser().observe(this, new Observer<Base<UserO>>() {
            @Override
            public void onChanged(Base<UserO> userBase) {
                if (userBase.isSuccess()) {
                    openNextActivity(userBase.getData());
                }
            }
        });
        getSupportActionBar().hide();

        initViews();
        initEvents();
    }

    private void initViews() {
        parentRelativeLayout = findViewById(R.id.parentRelativeLayout);
        backgroundLoginLinearLayout = findViewById(R.id.backgroundLoginLinearLayout);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        emailEditText.setText("nuria.michel@gmail.com");
        passwordEditText.setText("contrasena1");
}

    private void initEvents() {
    }


    public void login(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        viewModel.loginWithEmailPassword(email, password).observe(this, new Observer<Base<UserO>>() {
            @Override
            public void onChanged(Base<UserO> userBase) {
                if (userBase.isSuccess()) {
                    Intent intent = new Intent(context, Launcher.class);
                    intent.putExtra(Constants.KEY_UUID, userBase.getData().getUuid());
                    intent.putExtra(Constants.KEY_DISPLAY_NAME, userBase.getData().getDisplayName());
                    startActivity(intent);
                } else {
                    Snackbar.make(parentRelativeLayout,
                            ErrorMapper.getError(context, userBase.getErrorCode()),
                            BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void openNextActivity(UserO user) {
        Intent intent = new Intent(context, Launcher.class);
        intent.putExtra(Constants.KEY_UUID, user.getUuid());
        intent.putExtra(Constants.KEY_DISPLAY_NAME, user.getDisplayName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void openRegister(View view) {
        Intent intent = new Intent(context, RegisterActivity.class);
        startActivity(intent);
    }

}