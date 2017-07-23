package com.rockwellstudios.rockwellshop;

import android.app.Application;

import com.rockwellstudios.rockwellshop.dagger.AppComponent;
import com.rockwellstudios.rockwellshop.dagger.AppModule;
import com.rockwellstudios.rockwellshop.dagger.DaggerAppComponent;
import com.rockwellstudios.rockwellshop.dagger.ShoppingCartModule;

/**
 * Created by Andrew on 23.07.2017.
 */

public class MainApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent();
    }

    private void getAppComponent() {
        if (component == null) {
            component = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .shoppingCartModule(new ShoppingCartModule())
                    .build();
        }
    }
}
