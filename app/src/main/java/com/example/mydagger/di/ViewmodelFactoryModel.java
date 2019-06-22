package com.example.mydagger.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.mydagger.viewmodel.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewmodelFactoryModel {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

    /*
    @Provides
    static ViewModelProvider.Factory factory(ViewModelProviderFactory viewModelProviderFactory)
    {
        return viewModelProviderFactory;
    }
    */

}
