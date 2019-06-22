package com.example.mydagger.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.mydagger.Model.User;
import com.example.mydagger.R;
import com.example.mydagger.base.BaseActivity;
import com.example.mydagger.ui.main.MainActivity;
import com.example.mydagger.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class AuthActivity extends BaseActivity {

    private static final String TAG = "AuthActivity";
    private AuthViewModel authviewModel;

    //    @BindView(R.id.editText)
    EditText editText;

    //    @BindView(R.id.button)
    Button button;

    ProgressBar progressBar;


    @Inject
    String mString;

    @Inject
    boolean mABoolean;

    @Inject
    Drawable mDrawable;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authviewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        debug(TAG, " " + mString);
        debug(TAG, " app is null ?.. " + mABoolean);
        setLogo();

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        progressBar = findViewById(R.id.progressBar);

        setObserver();

        setProgressBar(false);

        button.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(editText.getText().toString())) {
                authviewModel.authenticationWithId(Integer.parseInt(editText.getText().toString()));
            }

        });


    }
    private void setProgressBar(boolean isVisible)
    {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void setObserver() {

        authviewModel.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if (userAuthResource != null)
                {
                    switch (userAuthResource.status)
                    {
                        case LOADING:
                            setProgressBar(true);
                            break;

                        case AUTHENTICATED:
                            setProgressBar(false);
                            Log.d(TAG, "onChanged: AUTHENTICATED " + userAuthResource.data.getEmail());
                            startActivity(new Intent(AuthActivity.this, MainActivity.class));
                            break;

                        case NOT_AUTHENTICATED:
                            setProgressBar(false);
                            Log.d(TAG, "onChanged: NOT_AUTHENTICATED");
                            break;

                        case ERROR:
                            setProgressBar(false);
                            Toast.makeText(AuthActivity.this, TAG +" " + userAuthResource.message   , Toast.LENGTH_LONG).show();
                        break;



                    }
                }

            }
        });

/*

        authviewModel.getAuthUser()
                .observe(
                        this,
                        user -> Log.d(TAG, "onChanged: " + user.getEmail())
                );
*/

    }

    private void setLogo() {
        requestManager
                .load(mDrawable)
                .into(((ImageView) findViewById(R.id.imageView)));
    }


}
