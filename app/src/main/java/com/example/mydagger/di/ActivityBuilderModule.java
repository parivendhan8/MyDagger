package com.example.mydagger.di;

import com.example.mydagger.di.Auth.AuthModule;
import com.example.mydagger.di.Auth.AuthViewModelModule;
import com.example.mydagger.di.main.MainFragmentBuilderModule;
import com.example.mydagger.di.main.MainViewModelModule;
import com.example.mydagger.ui.main.MainActivity;
import com.example.mydagger.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(

            modules = {

                    AuthViewModelModule.class,
                    AuthModule.class,

            }
    )
    abstract AuthActivity contributeAuthAcutivity();

    @ContributesAndroidInjector(

            modules = {

                    MainFragmentBuilderModule.class,
                    MainViewModelModule.class
            }

    )
    abstract MainActivity contributingMainActivity();

}
