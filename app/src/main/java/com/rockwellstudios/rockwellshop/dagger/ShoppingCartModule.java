package com.rockwellstudios.rockwellshop.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.rockwellstudios.rockwellshop.common.ShoppingCart;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Andrew on 23.07.2017.
 */

@Module
public class ShoppingCartModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    ShoppingCart provideShoppingCart(SharedPreferences preferences){
        return new ShoppingCart(preferences);
    }
}
