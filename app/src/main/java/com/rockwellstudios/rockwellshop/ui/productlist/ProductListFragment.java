package com.rockwellstudios.rockwellshop.ui.productlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.core.listeners.OnProductSelectedListener;
import com.rockwellstudios.rockwellshop.model.Product;
import com.rockwellstudios.rockwellshop.ui.BaseListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends BaseListFragment implements OnProductSelectedListener,ProductListContract.View {


    private ProductListAdapter mAdapter;
    private ProductListContract.Presenter mPresenter;

    public ProductListFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // setup adapter
        mAdapter = new ProductListAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter = new ProductPresenter();
        mPresenter.bindView(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadProducts();
    }

    @Override
    public int getEmptyText() {
        return R.string.no_product_found;
    }


    @Override
    public void onSelectProduct(Product selectedProduct) {

    }

    @Override
    public void onLongClickProduct(Product selectedProduct) {

    }

    @Override
    public void showProducts(List<Product> mProducts) {
        mAdapter.setProducts(mProducts);
    }

    @Override
    public void showAddProductForm() {

    }

    @Override
    public void showEditProductForm(Product product) {

    }

    @Override
    public void showDeleteProductPromt(Product product) {

    }

    @Override
    public void showGoogleSearch(Product product) {

    }

    @Override
    public void showEmptyText() {
        showEmptyTextMessage();
    }

    @Override
    public void hideEmptyText() {
        hideEmptyTextMessage();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
