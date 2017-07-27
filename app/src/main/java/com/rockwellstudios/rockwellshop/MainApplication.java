package com.rockwellstudios.rockwellshop;

import android.app.Application;

import com.rockwellstudios.rockwellshop.dagger.AppComponent;
import com.rockwellstudios.rockwellshop.dagger.AppModule;
import com.rockwellstudios.rockwellshop.dagger.BusModule;
import com.rockwellstudios.rockwellshop.dagger.DaggerAppComponent;
import com.rockwellstudios.rockwellshop.dagger.ShoppingCartModule;

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
