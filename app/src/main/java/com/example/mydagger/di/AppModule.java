package com.example.mydagger.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.mydagger.Utils.Constants;
import com.example.mydagger.R;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static String getStringName()
    {
        return "Its a string";
    }

    @Singleton
    @Provides
    static boolean getApp(Application application)
    {
        return application == null;
    }


    @Singleton
    @Provides
    static RequestOptions provideRequestOption()
    {
        return RequestOptions
                .placeholderOf(R.drawable.glide_logo)
                .error(R.drawable.ic_launcher_foreground);
    }

//    Glide
    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions)
    {
        return Glide
                .with(application)
                .applyDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideDrawable(Application application)
    {
        return ContextCompat.getDrawable(application, R.drawable.apple);
    }

//    Retrofit
    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance()
    {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
