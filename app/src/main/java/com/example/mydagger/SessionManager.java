package com.example.mydagger;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.mydagger.Model.User;
import com.example.mydagger.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";

    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source)
    {
        if (cachedUser != null)
        {
            cachedUser.setValue(AuthResource.loading(null));
            cachedUser.addSource(source, authResource -> {
                cachedUser.setValue(authResource);
                cachedUser.removeSource(source);
            });
        }
        else
        {
            Log.d(TAG, "authenticateWithId: id alredy authenticated " );
        }
    }

    public void logout()
    {
        Log.d(TAG, "logout: id loadding");
        cachedUser.setValue(AuthResource.logout());
    }

    public LiveData<AuthResource<User>> getCachedUser() {
        return cachedUser;
    }
}
