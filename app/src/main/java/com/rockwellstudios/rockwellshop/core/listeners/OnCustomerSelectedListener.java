package com.rockwellstudios.rockwellshop.core.listeners;


import com.rockwellstudios.rockwellshop.model.Customer;

public interface OnCustomerSelectedListener {
    void onSelectCustomer(Customer customer);
    void onLongClickCustomer(Customer customer);
}
