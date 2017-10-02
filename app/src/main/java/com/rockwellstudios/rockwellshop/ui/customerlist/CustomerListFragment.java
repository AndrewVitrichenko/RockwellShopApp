package com.rockwellstudios.rockwellshop.ui.customerlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.core.listeners.OnCustomerSelectedListener;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.ui.BaseListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerListFragment extends BaseListFragment implements OnCustomerSelectedListener, CustomerListContract.View {

    private CustomerListAdapter mAdapter;
    private CustomerListContract.Presenter mPresenter;

    public CustomerListFragment() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new CustomerListAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new CustomerPresenter();
        mPresenter.bindView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadCustomers();
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

    @Override
    public void showCustomers(List<Customer> customers) {
        mAdapter.setCustomers(customers);
    }

    @Override
    public void showAddCustomerForm() {

    }

    @Override
    public void showDeleteCustomerPromt(Customer customer) {

    }

    @Override
    public void showEditCustomerForm(Customer customer) {

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
