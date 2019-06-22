package com.example.mydagger.di.Auth;

import androidx.lifecycle.ViewModel;

import com.example.mydagger.di.ViewModelKey;
import com.example.mydagger.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindsAuthViewModel(AuthViewModel authViewModel);

}
