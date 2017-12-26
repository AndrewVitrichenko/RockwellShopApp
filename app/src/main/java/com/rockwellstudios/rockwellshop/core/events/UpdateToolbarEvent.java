package com.rockwellstudios.rockwellshop.core.events;

import com.rockwellstudios.rockwellshop.model.LineItem;

import java.util.List;

/**
 * Created by andrew on 27.07.17.
 */

public class UpdateToolbarEvent {

    private List<LineItem> shoppingCart;

    public UpdateToolbarEvent(List<LineItem> shoppingCart){
        this.shoppingCart = shoppingCart;
    }

    public List<LineItem> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<LineItem> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
