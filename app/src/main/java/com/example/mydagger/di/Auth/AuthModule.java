package com.example.mydagger.di.Auth;

import com.example.mydagger.Network.Auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit)
    {
        return retrofit.create(AuthApi.class);
    }

}
