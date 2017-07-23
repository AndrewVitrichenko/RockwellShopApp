package com.rockwellstudios.rockwellshop.common;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.model.LineItem;
import com.rockwellstudios.rockwellshop.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 18.06.2017.
 */

public class ShoppingCart implements ShoppingCartContract {

    private List<LineItem> shoppingCart;
    private Customer selectedCustomer;
    private final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final String LOG_TAG = ShoppingCart.class.getSimpleName();
    private static boolean DEBUG = false;

    public ShoppingCart(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
        initShoppingCart();
    }

    private void initShoppingCart() {
        shoppingCart = new ArrayList<>();
        selectedCustomer = new Customer();
        Gson gson = new Gson();

        if (sharedPreferences.getBoolean(Constants.OPEN_CART_EXISTS, false)) {
            String serializedItems = sharedPreferences.getString(Constants.SERIALIZED_CART_ITEMS, "");

            if (DEBUG) {
                Log.d(LOG_TAG, "Serialized items " + serializedItems);
            }

            String serializedCustomer = sharedPreferences.getString(Constants.SERIALIZED_CUSTOMER, "");

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

            editor.putString(Constants.SERIALIZED_CART_ITEMS, serializedItems).apply();
            editor.putString(Constants.SERIALIZED_CUSTOMER, serializedCustomer).apply();
            editor.putBoolean(Constants.OPEN_CART_EXISTS, true).apply();
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
    }

    @Override
    public void removeItemFromCart(LineItem item) {
        shoppingCart.remove(item);
    }

    @Override
    public void clearAllItemsFromCart() {
        shoppingCart.clear();
    }

    @Override
    public List<LineItem> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void setCustomer(Customer customer) {
        selectedCustomer = customer;
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
    }

    @Override
    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    @Override
    public void completeCheckout() {
        shoppingCart.clear();
    }
}
