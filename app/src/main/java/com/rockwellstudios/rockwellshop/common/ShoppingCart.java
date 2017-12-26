package com.rockwellstudios.rockwellshop.common;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rockwellstudios.rockwellshop.MainApplication;
import com.rockwellstudios.rockwellshop.core.events.CustomerSelectedEvent;
import com.rockwellstudios.rockwellshop.core.events.UpdateToolbarEvent;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.model.LineItem;
import com.rockwellstudios.rockwellshop.util.DbConstants;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Andrew on 18.06.2017.
 */

public class ShoppingCart implements ShoppingCartContract {

    @Inject
    Bus mBus;

    private List<LineItem> shoppingCart;
    private Customer selectedCustomer;
    private final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final String LOG_TAG = ShoppingCart.class.getSimpleName();
    private static boolean DEBUG = false;

    public ShoppingCart(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
        MainApplication.getInstance().getAppComponent().inject(this);
        initShoppingCart();
    }

    private void initShoppingCart() {
        shoppingCart = new ArrayList<>();
        selectedCustomer = new Customer();
        Gson gson = new Gson();

        if (sharedPreferences.getBoolean(DbConstants.OPEN_CART_EXISTS, false)) {
            String serializedItems = sharedPreferences.getString(DbConstants.SERIALIZED_CART_ITEMS, "");

            if (DEBUG) {
                Log.d(LOG_TAG, "Serialized items " + serializedItems);
            }

            String serializedCustomer = sharedPreferences.getString(DbConstants.SERIALIZED_CUSTOMER, "");

            if (DEBUG) {
                Log.d(LOG_TAG, "Serialized customer " + serializedCustomer);
            }

            if (!serializedItems.isEmpty()) {
                shoppingCart = gson.fromJson(serializedItems, new TypeToken<List<LineItem>>() {
                }.getType());
            }

            if (!serializedCustomer.isEmpty()) {
                selectedCustomer = gson.fromJson(serializedCustomer, Customer.class);
            }
        }

        populateToolBar();

    }

    public void saveCartToPreferences() {
        if (shoppingCart != null) {
            Gson gson = new Gson();
            String serializedItems = gson.toJson(shoppingCart);

            if (DEBUG) {
                Log.d(LOG_TAG, "Saving serialized items " + serializedItems);
            }
            String serializedCustomer = gson.toJson(selectedCustomer);
            if (DEBUG) {
                Log.d(LOG_TAG, "Saving customer " + serializedCustomer);
            }

            editor.putString(DbConstants.SERIALIZED_CART_ITEMS, serializedItems).apply();
            editor.putString(DbConstants.SERIALIZED_CUSTOMER, serializedCustomer).apply();
            editor.putBoolean(DbConstants.OPEN_CART_EXISTS, true).apply();
        }
    }

    @Override
    public void addItemToCart(LineItem item) {
        boolean isItemInCart = false;
        int itemPosition;

        for (LineItem tempItem : shoppingCart) {
            if (tempItem.getId() == item.getId()) {
                isItemInCart = true;
                itemPosition = shoppingCart.indexOf(tempItem);
                tempItem.setQuantity(tempItem.getQuantity() + item.getQuantity());
                shoppingCart.set(itemPosition, tempItem);
                break;
            }
        }

        if (!isItemInCart) {
            shoppingCart.add(item);
        }

        populateToolBar();
    }

    @Override
    public void removeItemFromCart(LineItem item) {
        shoppingCart.remove(item);
        if (shoppingCart.size() == 0) {
            mBus.post(new CustomerSelectedEvent(new Customer(), true));
        }
        populateToolBar();
    }

    @Override
    public void clearAllItemsFromCart() {
        shoppingCart.clear();

        editor.putString(DbConstants.SERIALIZED_CART_ITEMS, "").apply();
        editor.putString(DbConstants.SERIALIZED_CUSTOMER, "").apply();
        editor.putBoolean(DbConstants.OPEN_CART_EXISTS, false).apply();

        populateToolBar();
        mBus.post(new CustomerSelectedEvent(new Customer(), true));
    }

    @Override
    public List<LineItem> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void setCustomer(Customer customer) {
        selectedCustomer = customer;
        mBus.post(new CustomerSelectedEvent(selectedCustomer, false));
    }

    @Override
    public void updateItemQty(LineItem item, int qty) {
        boolean itemAlreadyInCart = false;
        int itemPosition;
        for (LineItem tempItem : shoppingCart) {
            if (tempItem.getId() == item.getId()) {
                itemAlreadyInCart = true;
                itemPosition = shoppingCart.indexOf(tempItem);
                tempItem.setQuantity(qty);
                shoppingCart.set(itemPosition, tempItem);
                break;
            }
        }

        if (!itemAlreadyInCart) {
            item.setQuantity(qty);
            shoppingCart.add(item);
        }

        populateToolBar();
    }

    public void populateToolBar() {
        mBus.post(new UpdateToolbarEvent(shoppingCart));
    }

    @Override
    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    @Override
    public void completeCheckout() {
        shoppingCart.clear();
        populateToolBar();
        mBus.post(new CustomerSelectedEvent(new Customer(), true));
    }
}
