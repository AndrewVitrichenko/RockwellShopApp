package com.rockwellstudios.rockwellshop.ui.productlist;

import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.data.SampleProductData;
import com.rockwellstudios.rockwellshop.model.Category;
import com.rockwellstudios.rockwellshop.model.Product;

import java.util.List;

/**
 * Created by andrew on 29.09.17.
 */

public class ProductInMemoryRepository implements ProductListContract.Model{



    @Override
    public List<Product> getAllProducts() {
        return SampleProductData.getSampleProducts();
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Product getProductById(long id) {
        return null;
    }

    @Override
    public void deleteProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void addProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void updateProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }
}
