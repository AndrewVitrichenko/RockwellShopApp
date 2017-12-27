package com.rockwellstudios.rockwellshop;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.rockwellstudios.rockwellshop.dagger.AppComponent;
import com.rockwellstudios.rockwellshop.dagger.AppModule;
import com.rockwellstudios.rockwellshop.dagger.BusModule;
import com.rockwellstudios.rockwellshop.dagger.DaggerAppComponent;
import com.rockwellstudios.rockwellshop.dagger.ShoppingCartModule;
import com.rockwellstudios.rockwellshop.data.DataBaseHelper;
import com.rockwellstudios.rockwellshop.data.SimpleFillDataService;
import com.rockwellstudios.rockwellshop.util.DbConstants;

/**
 * Created by Andrew on 23.07.2017.
 */

public class MainApplication extends Application {

    private AppComponent component;
    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getAppComponent();
        DataBaseHelper.createInstance(instance);
        insertSampleData();
    }

    private void insertSampleData() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean(DbConstants.FIRST_RUN, true)) {
            Intent intent = new Intent(this, SimpleFillDataService.class);
            startService(intent);
            prefs.edit().putBoolean(DbConstants.FIRST_RUN, false).apply();
        }
    }

    public AppComponent getAppComponent() {
        if (component == null) {
            component = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .shoppingCartModule(new ShoppingCartModule())
                    .busModule(new BusModule())
                    .build();
        }
        return component;
    }

    public static MainApplication getInstance() {
        return instance;
    }
}
