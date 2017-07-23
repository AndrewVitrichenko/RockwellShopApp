package com.rockwellstudios.rockwellshop.dagger;

import android.content.Context;

import com.rockwellstudios.rockwellshop.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Andrew on 18.06.2017.
 */

@Module
public class AppModule {

    private final MainApplication application;

    public AppModule(MainApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return application;
    }
}
