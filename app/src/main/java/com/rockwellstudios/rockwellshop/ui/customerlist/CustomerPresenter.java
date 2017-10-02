package com.rockwellstudios.rockwellshop.ui.customerlist;

import com.rockwellstudios.rockwellshop.MainApplication;
import com.rockwellstudios.rockwellshop.common.ShoppingCart;
import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Andrew on 07.08.2017.
 */

public class CustomerPresenter implements CustomerListContract.Presenter, OnDatabaseOperationCompleteListener {

    private CustomerListContract.View mView;

    @Inject
    CustomerListContract.Model mRepository;

    @Inject
    ShoppingCart shoppingCart;

    @Inject
    Bus mBus;

    public CustomerPresenter() {
        MainApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void bindView(CustomerListContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void loadCustomers() {
        List<Customer> customers = mRepository.getAllCustomers();
        if (customers != null && customers.size() > 0) {
            mView.hideEmptyText();
            mView.showCustomers(customers);
        } else {
            mView.showEmptyText();
        }
    }

    @Override
    public Customer getCustomerById(long id) {
        return mRepository.getCustomerById(id);
    }

    @Override
    public void onCustomerSelected(Customer customer) {
        shoppingCart.setCustomer(customer);
    }

    @Override
    public void onAddCustomerButtonClicked() {
        mView.showAddCustomerForm();
    }

    @Override
    public void addCustomer(Customer customer) {
        mRepository.addCustomer(customer, this);
    }

    @Override
    public void onDeleteCustomerButtonClicked(Customer customer) {
        mView.showDeleteCustomerPromt(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        mRepository.deleteCustomer(customer, this);
    }

    @Override
    public void onEditCustomerButtonClicked(Customer customer) {
        mView.showEditCustomerForm(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        mRepository.updateCustomer(customer, this);
    }

    @Override
    public void onDatabaseOperationFailed(String error) {
        mView.showMessage(error);
    }

    @Override
    public void onDatabaseOperationSucceed(String message) {
        mView.showMessage(message);
    }
}
