package com.rockwellstudios.rockwellshop.ui.customerlist;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.core.listeners.OnCustomerSelectedListener;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.model.Product;
import com.rockwellstudios.rockwellshop.ui.BaseFragment;
import com.rockwellstudios.rockwellshop.ui.BaseListFragment;
import com.rockwellstudios.rockwellshop.ui.productlist.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerListFragment extends BaseListFragment implements OnCustomerSelectedListener {

    private CustomerListAdapter mAdapter;

    public CustomerListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        List<Customer> mCustomers = new ArrayList<>();
        mAdapter = new CustomerListAdapter(mCustomers, getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);

        if (mCustomers.size() < 1) {
            showEmptyTextMessage();
        } else {
            hideEmptyTextMessage();
        }

        return mRootView;
    }

    @Override
    public int getEmptyText() {
        return R.string.no_customer_found;
    }

    @Override
    public void onSelectCustomer(Customer customer) {

    }

    @Override
    public void onLongClickCustomer(Customer customer) {

    }
}
