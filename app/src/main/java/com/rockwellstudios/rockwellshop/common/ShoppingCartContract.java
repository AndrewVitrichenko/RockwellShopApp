package com.rockwellstudios.rockwellshop.common;

import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.model.LineItem;

import java.util.List;

/**
 * Created by Andrew on 23.07.2017.
 */

public interface ShoppingCartContract {

    void addItemToCart(LineItem item);

    void removeItemFromCart(LineItem item);

    void clearAllItemsFromCart();

    List<LineItem> getShoppingCart();

    void setCustomer(Customer customer);

    void updateItemQty(LineItem item, int qty);

    Customer getSelectedCustomer();

    void completeCheckout();
}
