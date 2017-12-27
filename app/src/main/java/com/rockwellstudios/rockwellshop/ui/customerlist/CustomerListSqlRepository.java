package com.rockwellstudios.rockwellshop.ui.customerlist;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.data.DataBaseHelper;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.model.Product;
import com.rockwellstudios.rockwellshop.util.DbConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by andrew on 27.12.17.
 */

public class CustomerListSqlRepository implements CustomerListContract.Model {

    private SQLiteDatabase database;

    public CustomerListSqlRepository() {
        database = DataBaseHelper.getInstance().getWritableDatabase();
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        if (database != null) {
            Cursor cursor = database.rawQuery(DbConstants.SELECT_ALL_CUSTOMERS, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        customers.add(Customer.getCustomerFromCursor(cursor));
                        cursor.moveToNext();
                    }
                }
                cursor.close();
            }
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(long id) {
        if (database != null) {
            String query = String.format(Locale.getDefault(),
                    "%s %d", DbConstants.SELECT_CUSTOMER_BY_ID, id);
            Cursor cursor = database.rawQuery(query, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    return Customer.getCustomerFromCursor(cursor);
                }
                cursor.close();
            }
        }
        return null;
    }

    @Override
    public void addCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            try {
                database.insertOrThrow(DbConstants.CUSTOMER_TABLE, null,
                        Customer.getCvFromCustomer(customer));
                listener.onDatabaseOperationSucceed("Success");
            } catch (SQLException e) {
                listener.onDatabaseOperationFailed(e.getMessage());
            }
        }
    }

    @Override
    public void deleteCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            int result = database.delete(DbConstants.CUSTOMER_TABLE,
                    DbConstants.COLUMN_ID + " = " + customer.getId(), null);
            if (result > 0) {
                listener.onDatabaseOperationSucceed("Success");
            } else {
                listener.onDatabaseOperationFailed("Error");
            }
        }
    }

    @Override
    public void updateCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            int result = database.update(DbConstants.CUSTOMER_TABLE, Customer.getCvFromCustomer(customer),
                    DbConstants.COLUMN_ID + " = " + customer.getId(), null);
            if (result == 1) {
                listener.onDatabaseOperationSucceed("Success");
            } else {
                listener.onDatabaseOperationFailed("Fail");
            }
        }
    }
}
