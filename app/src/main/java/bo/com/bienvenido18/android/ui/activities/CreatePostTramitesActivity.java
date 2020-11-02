package bo.com.bienvenido18.android.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

    private static final int RC_PERMISSIONS = 201;
    private static final int RC_GALLERY = 202;

    private Uri fileCoverPhoto;

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
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                if  (hasPermissions()) {
                    openGallery();
                } else {
                    askPermissions();
                }
            } else {
                openGallery();
            }

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

    private boolean hasPermissions() {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askPermissions() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RC_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                } else {
                    Toast.makeText(context, "Sin permisos", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.añadir_imagen)),
                RC_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RC_GALLERY && data != null && data.getData() != null) {
                Uri image = data.getData();
              // fileCoverPhoto = CompressImage.compressImage(image, context);
            }
        }
    }

}
