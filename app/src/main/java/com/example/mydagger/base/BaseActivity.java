package com.example.mydagger.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.mydagger.Model.User;
import com.example.mydagger.SessionManager;
import com.example.mydagger.di.Auth.AuthModule;
import com.example.mydagger.ui.auth.AuthActivity;
import com.example.mydagger.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subscribeObserver();
    }

    private void subscribeObserver()
    {
        sessionManager.getCachedUser().observe(this, userAuthResource -> {

            if (userAuthResource != null)
            {
                switch (userAuthResource.status)
                {
                    case LOADING:
                        break;

                    case AUTHENTICATED:
                        Log.d(TAG, "onChanged: AUTHENTICATED " + userAuthResource.data.getEmail());
                        break;

                    case NOT_AUTHENTICATED:
                        Log.d(TAG, "onChanged: NOT_AUTHENTICATED");
                        break;

                    case ERROR:
                        navLogin();
                        break;


                }
            }

        });
    }

    private void navLogin() {

        startActivity(new Intent(this, AuthActivity.class));
        finish();
    }

    public void debug(String tag, String message)
    {
        Log.d(tag, message);
    }
}
