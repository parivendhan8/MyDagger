package com.example.mydagger.di.main;

import androidx.lifecycle.ViewModel;

import com.example.mydagger.di.ViewModelKey;
import com.example.mydagger.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindsProfileViewModule(ProfileViewModel profileViewModel);

}
