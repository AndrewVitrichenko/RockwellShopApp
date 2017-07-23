package com.rockwellstudios.rockwellshop.dagger;

import com.rockwellstudios.rockwellshop.common.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Andrew on 18.06.2017.
 */

@Component(modules = {AppModule.class,ShoppingCartModule.class})
@Singleton
public interface AppComponent {

    void inject(MainActivity target);
}
