package com.rockwellstudios.rockwellshop.ui.productlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.core.listeners.OnProductSelectedListener;
import com.rockwellstudios.rockwellshop.model.LineItem;
import com.rockwellstudios.rockwellshop.model.Product;
import com.rockwellstudios.rockwellshop.ui.BaseFragment;
import com.rockwellstudios.rockwellshop.ui.BaseListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends BaseListFragment implements OnProductSelectedListener {


    private ProductListAdapter mAdapter;

    public ProductListFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // setup adapter
        List<Product> mProducts = new ArrayList<>();
        mAdapter = new ProductListAdapter(mProducts, getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);

        if (mProducts.size() < 1) {
            showEmptyTextMessage();
        } else {
            hideEmptyTextMessage();
        }
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
}
