package com.rockwellstudios.rockwellshop.data;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.rockwellstudios.rockwellshop.common.MainActivity;
import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.model.Product;
import com.rockwellstudios.rockwellshop.ui.customerlist.CustomerListSqlRepository;
import com.rockwellstudios.rockwellshop.ui.productlist.ProductListSqlRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 27.12.17.
 */

public class SimpleFillDataService extends IntentService {

    public SimpleFillDataService() {
        super("SimpleFillDataService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        List<Product> products = SampleProductData.getSampleProducts();

        ProductListSqlRepository repository = new ProductListSqlRepository();

        for (Product product : products) {
            repository.addProduct(product, new OnDatabaseOperationCompleteListener() {
                @Override
                public void onDatabaseOperationFailed(String error) {
                    Log.i("Product inserting ", error);
                }

                @Override
                public void onDatabaseOperationSucceed(String message) {
                    Log.i("Product inserting ", "Success");
                }
            });
        }

        List<Customer> customers = SampleCustomerData.getSampleCustomers();

        CustomerListSqlRepository customerListSqlRepository = new CustomerListSqlRepository();

        for (Customer customer : customers) {
            customerListSqlRepository.addCustomer(customer, new OnDatabaseOperationCompleteListener() {
                @Override
                public void onDatabaseOperationFailed(String error) {
                    Log.i("Customer inserting ", error);
                }

                @Override
                public void onDatabaseOperationSucceed(String message) {
                    Log.i("Customer inserting ", "Success");
                }
            });
        }

        List<String> categories = new ArrayList<>();
        categories.add("Electronics");
        categories.add("Computers");
        categories.add("Toys");
        categories.add("Garden");
        categories.add("Kitchen");
        categories.add("Clothing");
        categories.add("Health");

        for (String category : categories) {
            repository.createOrGetCategoryId(category, new OnDatabaseOperationCompleteListener() {
                @Override
                public void onDatabaseOperationFailed(String error) {
                    Log.i("Category inserting ", error);
                }

                @Override
                public void onDatabaseOperationSucceed(String message) {
                    Log.i("Category inserting ", "Success");
                }
            });
        }

        Intent activityIntent = new Intent(this, MainActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(activityIntent);
    }
}
