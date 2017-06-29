package com.rockwellstudios.rockwellshop.core.listeners;

import com.rockwellstudios.rockwellshop.model.LineItem;

/**
 * Created by andrew on 29.06.17.
 */

public interface CartActionListener {

    void onItemDelete(LineItem item);

    void onItemQuantityChange(LineItem item);
}
