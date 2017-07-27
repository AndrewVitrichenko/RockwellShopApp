package com.rockwellstudios.rockwellshop.ui.productlist;

import com.rockwellstudios.rockwellshop.model.Product;

import java.util.List;

/**
 * Created by andrew on 27.07.17.
 */

public interface ProductListContract {

    interface Model {
        List<Product> getAllProducts();

        Product getProductById(long id);

        void deleteProduct(Product product);

        void addProduct(Product product);

        void updateProduct(Product product);
    }

    interface View {
        void showProducts(List<Product> mProducts);

        void showAddProductForm();

        void showEditProductForm(Product product);

        void showDeleteProductPromt(Product product);

        void showGoogleSearch(Product product);

        void showEmptyText();

        void hideEmptyText();

        void showMessage(String message);

    }

    interface Presenter {
        void loadProducts();

        void onAddProductButtonClicked();

        void onAddToCartButtonClicked(Product product);

        void onDeleteProductButtonClicked(Product product);

        void onEditProductButtonCLicked(Product product);

        void updateProduct(Product product);

        void onGoogleButtonSearchClicked();

        Product getProduct(long id);

        void addProduct(Product product);

        void deletedProduct(Product product);

    }
}
