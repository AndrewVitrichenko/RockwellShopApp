package com.rockwellstudios.rockwellshop.ui.productlist;

import com.rockwellstudios.rockwellshop.MainApplication;
import com.rockwellstudios.rockwellshop.common.ShoppingCart;
import com.rockwellstudios.rockwellshop.core.listeners.onDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.model.LineItem;
import com.rockwellstudios.rockwellshop.model.Product;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by andrew on 27.07.17.
 */

public class ProductPresenter implements ProductListContract.Presenter, onDatabaseOperationCompleteListener {

    private ProductListContract.View view;

    @Inject
    ProductListContract.Model repository;
    @Inject
    ShoppingCart mCart;

    public ProductPresenter() {
        MainApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void bindView(ProductListContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void loadProducts() {
        List<Product> availableProducts = repository.getAllProducts();
        if (availableProducts != null && availableProducts.size() > 0) {
            view.hideEmptyText();
            view.showProducts(availableProducts);
        } else {
            view.showEmptyText();
        }
    }

    @Override
    public void onAddProductButtonClicked() {
        view.showAddProductForm();
    }

    @Override
    public void onAddToCartButtonClicked(Product product) {
        LineItem item = new LineItem(product, 1);
        mCart.addItemToCart(item);
    }

    @Override
    public void onDeleteProductButtonClicked(Product product) {
        view.showDeleteProductPromt(product);
    }

    @Override
    public void onEditProductButtonCLicked(Product product) {
        view.showEditProductForm(product);
    }

    @Override
    public void updateProduct(Product product) {
        repository.updateProduct(product, this);
    }

    @Override
    public void onGoogleButtonSearchClicked(Product product) {
        view.showGoogleSearch(product);
    }

    @Override
    public Product getProduct(long id) {
        return repository.getProductById(id);
    }

    @Override
    public void addProduct(Product product) {
        repository.addProduct(product, this);
    }

    @Override
    public void deletedProduct(Product product) {
        repository.deleteProduct(product, this);
    }

    @Override
    public void onDatabaseOperationFailed(String error) {
        view.showMessage(error);
    }

    @Override
    public void onDatabaseOperationSucceed(String message) {
        view.showMessage(message);
    }
}
