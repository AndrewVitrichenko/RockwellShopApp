package com.rockwellstudios.rockwellshop.ui.customerlist;

import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.data.SampleCustomerData;
import com.rockwellstudios.rockwellshop.model.Customer;

import java.util.List;

/**
 * Created by andrew on 29.09.17.
 */

public class CustomerInMemoryRepository implements CustomerListContract.Model {
    @Override
    public List<Customer> getAllCustomers() {
        return SampleCustomerData.getSampleCustomers();
    }

    @Override
    public Customer getCustomerById(long id) {
        return null;
    }

    @Override
    public void addCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void deleteCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void updateCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }
}
