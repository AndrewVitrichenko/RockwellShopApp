package com.rockwellstudios.rockwellshop.core.listeners.events;

import com.rockwellstudios.rockwellshop.model.Customer;

/**
 * Created by andrew on 27.07.17.
 */

public class CustomerSelectedEvent {

    private final Customer selectedCustomer;
    private final boolean clearCustomer;

    public CustomerSelectedEvent(Customer selectedCustomer, boolean clearCustomer){
        this.selectedCustomer = selectedCustomer;
        this.clearCustomer = clearCustomer;
    }

    public Customer getSelectedCustomer(){
        return selectedCustomer;
    }

    public boolean isClearCustomer(){
        return clearCustomer;
    }
}
