package com.rockwellstudios.rockwellshop.ui.customerlist;

import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.model.Customer;

import java.util.List;

/**
 * Created by andrew on 27.07.17.
 */

public interface CustomerListContract {

    interface Model {
        List<Customer> getAllCustomers();

        Customer getCustomerById(long id);

        void addCustomer(Customer customer,OnDatabaseOperationCompleteListener listener);

        void deleteCustomer(Customer customer,OnDatabaseOperationCompleteListener listener);

        void updateCustomer(Customer customer,OnDatabaseOperationCompleteListener listener);
    }

    interface View {
        void showCustomers(List<Customer> customers);

        void showAddCustomerForm();

        void showDeleteCustomerPromt(Customer customer);

        void showEditCustomerForm(Customer customer);

        void showEmptyText();

        void hideEmptyText();

        void showMessage(String message);
    }

    interface Presenter {

        void bindView(CustomerListContract.View mView);

        void loadCustomers();

        Customer getCustomerById(long id);

        void onCustomerSelected(Customer customer);

        void onAddCustomerButtonClicked();

        void addCustomer(Customer customer);

        void onDeleteCustomerButtonClicked(Customer customer);

        void deleteCustomer(Customer customer);

        void onEditCustomerButtonClicked(Customer customer);

        void updateCustomer(Customer customer);

    }
}
