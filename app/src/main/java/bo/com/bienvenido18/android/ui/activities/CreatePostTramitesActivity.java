package bo.com.bienvenido18.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import bo.com.bienvenido18.android.R;
import bo.com.bienvenido18.android.model.Base;
import bo.com.bienvenido18.android.model.PostTramite;
import bo.com.bienvenido18.android.repository.Repository;
import bo.com.bienvenido18.android.utils.Constants;
import bo.com.bienvenido18.android.viewModel.CreateTramiteViewModel;

public class CreatePostTramitesActivity extends AppCompatActivity {

    private static final String LOG = CreatePostTramitesActivity.class.getSimpleName();

    private CreateTramiteViewModel viewModel;
    private Context context;
    private String uuidTramite;

    private TextInputLayout postTitleTextInputLayout;
    private TextInputEditText titleEditText;
    private Button pickImageButton;
    private EditText contentEditText;
    private Button cancelButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_create_tramite);
        context = this;
        viewModel = new ViewModelProvider(this).get(CreateTramiteViewModel.class);

        initViews();
        initEvents();
        getItemValues();

    }

    private void initViews() {
        postTitleTextInputLayout = findViewById(R.id.postTitleTextInputLayout);
        titleEditText = findViewById(R.id.titleEditText);
        pickImageButton = findViewById(R.id.pickImageButton);
        contentEditText = findViewById(R.id.contentEditText);
        cancelButton = findViewById(R.id.cancelButton);
        saveButton = findViewById(R.id.saveButton);

    }
    private void initEvents() {
        pickImageButton.setOnClickListener(view -> {
            ///
        });

        cancelButton.setOnClickListener(view -> {
            finish();
        });

        saveButton.setOnClickListener(view -> {
            PostTramite post = new PostTramite();
            post.setTitle(titleEditText.getText().toString());
            post.setContent(contentEditText.getText().toString());

            if (!post.getTitle().isEmpty() && !post.getContent().isEmpty() ){

                viewModel.createPost(uuidTramite, post, null).observe(this, new Observer<Base<String>>() {
                    @Override
                    public void onChanged(Base<String> stringBase) {
                        if (stringBase.isSuccess()) {
                            Log.e(LOG, "createPost.isSuccess:" + stringBase.getData());
                        } else {
                            Log.e(LOG, "createPost.error", stringBase.getException());
                        }
                    }
                });
            } else {
                Toast.makeText(context, context.getString(R.string.error_fill_values),
                        Toast.LENGTH_SHORT).show();
            }


        });


    }

    private void getItemValues() {
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_TRAMITE_UUID_SELECTED)) {
            uuidTramite = intent.getStringExtra(Constants.KEY_TRAMITE_UUID_SELECTED);
        }
    }

}
