package com.rockwellstudios.rockwellshop.ui.productlist;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.data.DataBaseHelper;
import com.rockwellstudios.rockwellshop.model.Category;
import com.rockwellstudios.rockwellshop.model.Product;
import com.rockwellstudios.rockwellshop.util.DbConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Andrew on 24.12.2017.
 */

public class ProductListSqlRepository implements ProductListContract.Model {

    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public ProductListSqlRepository() {
        dbHelper = DataBaseHelper.getInstance();
        database = dbHelper.getWritableDatabase();
    }


    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        if (database != null) {
            Cursor cursor = database.rawQuery(DbConstants.SELECT_ALL_PRODUCTS, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        products.add(Product.getProductFromCursor(cursor));
                        cursor.moveToNext();
                    }
                }
                cursor.close();
            }
        }
        return products;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        if (database != null) {
            Cursor cursor = database.rawQuery(DbConstants.SELECT_ALL_CATEGORIES, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        categories.add(Category.getCategoryFromCursor(cursor));
                        cursor.moveToNext();
                    }
                }
                cursor.close();
            }
        }
        return categories;
    }

    @Override
    public Product getProductById(long id) {
        if (database != null) {
            String query = String.format(Locale.getDefault(),
                    "SELECT * FROM %s WHERE %s = %d", DbConstants.PRODUCT_TABLE, DbConstants.COLUMN_ID, id);
            Cursor cursor = database.rawQuery(query, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    return Product.getProductFromCursor(cursor);
                }
                cursor.close();
            }
        }
        return null;
    }

    @Override
    public void deleteProduct(Product product, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            int result = database.delete(DbConstants.PRODUCT_TABLE,
                    DbConstants.COLUMN_ID + " = " + product.getId(), null);
            if (result > 0) {
                listener.onDatabaseOperationSucceed("Success");
            } else {
                listener.onDatabaseOperationFailed("Error");
            }
        }
    }

    @Override
    public void addProduct(Product product, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            try {
                database.insertOrThrow(DbConstants.PRODUCT_TABLE, null,
                        Product.getContentValuesFromProduct(product));
                listener.onDatabaseOperationSucceed("Success");
            } catch (SQLException e) {
                listener.onDatabaseOperationFailed(e.getMessage());
            }
        }
    }

    @Override
    public void updateProduct(Product product, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            int result = database.update(DbConstants.PRODUCT_TABLE, Product.getContentValuesFromProduct(product),
                    DbConstants.COLUMN_ID + " = " + product.getId(), null);
            if (result == 1) {
                listener.onDatabaseOperationSucceed("Success");
            } else {
                listener.onDatabaseOperationFailed("Fail");
            }
        }
    }
}
