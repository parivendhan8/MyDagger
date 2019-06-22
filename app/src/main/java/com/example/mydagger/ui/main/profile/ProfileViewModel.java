package com.example.mydagger.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mydagger.Model.User;
import com.example.mydagger.SessionManager;
import com.example.mydagger.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";

    SessionManager sessionManager;

    @Inject
    public ProfileViewModel() {
        Log.d(TAG, "ProfileViewModel: is ready");
    }

    public LiveData<AuthResource<User>> getAuthuUser()
    {
       return sessionManager.getCachedUser();
    }
}
