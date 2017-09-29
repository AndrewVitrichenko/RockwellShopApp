package com.rockwellstudios.rockwellshop.ui.customerlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.core.listeners.OnCustomerSelectedListener;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.ui.BaseListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerListFragment extends BaseListFragment implements OnCustomerSelectedListener {

    private CustomerListAdapter mAdapter;

    public CustomerListFragment() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Customer> mCustomers = new ArrayList<>();
        mAdapter = new CustomerListAdapter(mCustomers, getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);

        if (mCustomers.size() < 1) {
            showEmptyTextMessage();
        } else {
            hideEmptyTextMessage();
        }

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
