package com.rockwellstudios.rockwellshop.dagger;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrew on 27.07.17.
 */

@Module
public class BusModule {

    @Provides
    @Singleton
    Bus provideBus(){
        return new Bus();
    }
}
