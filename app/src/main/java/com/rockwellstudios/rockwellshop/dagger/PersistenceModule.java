package com.rockwellstudios.rockwellshop.dagger;

import com.rockwellstudios.rockwellshop.ui.customerlist.CustomerInMemoryRepository;
import com.rockwellstudios.rockwellshop.ui.customerlist.CustomerListContract;
import com.rockwellstudios.rockwellshop.ui.productlist.ProductInMemoryRepository;
import com.rockwellstudios.rockwellshop.ui.productlist.ProductListContract;
import com.rockwellstudios.rockwellshop.ui.productlist.ProductListSqlRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrew on 29.09.17.
 */

@Module
public class PersistenceModule {

    @Provides
    @Singleton
    CustomerListContract.Model provideCustomerRepository(){
        return new CustomerInMemoryRepository();
    }

    @Provides
    @Singleton
    ProductListContract.Model provideProductRepository(){
        return new ProductListSqlRepository();
    }
}
