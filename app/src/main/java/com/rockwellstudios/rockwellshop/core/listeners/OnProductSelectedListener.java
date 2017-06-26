package com.rockwellstudios.rockwellshop.core.listeners;

import com.rockwellstudios.rockwellshop.model.Product;

/**
 * Created by andrew on 26.06.17.
 */

public interface OnProductSelectedListener {

    void onSelectProduct(Product selectedProduct);

    void onLongClickProduct(Product selectedProduct);
}
