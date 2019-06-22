package com.example.mydagger.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mydagger.Model.User;
import com.example.mydagger.Network.Auth.AuthApi;
import com.example.mydagger.SessionManager;

import javax.inject.Inject;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    AuthApi authApi;

    SessionManager sessionManager;

//    private MediatorLiveData<User> authUser = new MediatorLiveData<>();
    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {

        this.authApi = authApi;
        this.sessionManager = sessionManager;

        if (authApi == null)
            Log.d(TAG, "AuthViewModel: is null");
        else
            Log.d(TAG, "AuthViewModel: is not null");


/*        authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User users) {
                        Log.d(TAG, "onNext: " + users.getEmail());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
                */


    }


    public void authenticationWithId(int id)
    {

//        authUser.setValue(AuthResource.loading(((User) null)));

        Log.d(TAG, "authenticationWithId: am attepting to login");

        sessionManager.authenticateWithId(queryUserId(id));

    }

    public LiveData<AuthResource<User>> queryUserId(int id)
    {
        return LiveDataReactiveStreams.
            fromPublisher(

                    authApi.getUser(id)
                            .onErrorReturn(throwable -> {

                                User user = new User();
                                user.setId(-1);
                                return user;

                            })
                            .map((Function<User, AuthResource<User>>) user -> {

                                if (user.getId() == -1)
                                {
                                    return AuthResource.error("Could not authenticated", null);
                                }

                                return AuthResource.authenticated(user);
                            })
                            .subscribeOn(Schedulers.io())

            );


    }


    public LiveData<AuthResource<User>> getAuthUser() {
        return sessionManager.getCachedUser();
    }
}
