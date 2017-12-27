package com.rockwellstudios.rockwellshop.dagger;

import com.rockwellstudios.rockwellshop.common.MainActivity;
import com.rockwellstudios.rockwellshop.common.ShoppingCart;
import com.rockwellstudios.rockwellshop.ui.checkout.CheckoutPresenter;
import com.rockwellstudios.rockwellshop.ui.customerlist.CustomerPresenter;
import com.rockwellstudios.rockwellshop.ui.productlist.ProductPresenter;
import com.rockwellstudios.rockwellshop.ui.transaction.TransactionPresenter;
import com.rockwellstudios.rockwellshop.ui.transaction.TransactionSqlRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Andrew on 18.06.2017.
 */

@Component(
        modules = {AppModule.class,
                ShoppingCartModule.class,
                BusModule.class,
                PersistenceModule.class
        }
)
@Singleton
public interface AppComponent {

    void inject(MainActivity target);

    void inject(ShoppingCart target);

    void inject(ProductPresenter target);

    void inject(CustomerPresenter target);

    void inject(CheckoutPresenter target);

    void inject(TransactionPresenter target);

    void inject(TransactionSqlRepository target);
}
