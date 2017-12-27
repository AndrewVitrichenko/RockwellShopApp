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

    public ProductListSqlRepository() {
        database = DataBaseHelper.getInstance().getWritableDatabase();
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
                    "%s %d", DbConstants.SELECT_PRODUCT_BY_ID, id);
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
                long categoryId = createOrGetCategoryId(product.getCategoryName(), listener);
                database.insertOrThrow(DbConstants.PRODUCT_TABLE, null,
                        Product.getContentValuesFromProduct(product, categoryId));
                listener.onDatabaseOperationSucceed("Success");
            } catch (SQLException e) {
                listener.onDatabaseOperationFailed(e.getMessage());
            }
        }
    }

    public long createOrGetCategoryId(String categoryName, OnDatabaseOperationCompleteListener listener) {
        Category foundCategory = getCategory(categoryName);
        if (foundCategory == null) {
            foundCategory = addCategory(categoryName, listener);
        }
        return foundCategory.getId();
    }

    private Category addCategory(String categoryName, OnDatabaseOperationCompleteListener listener) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        saveCategory(category, listener);
        return category;
    }

    private void saveCategory(Category category, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            try {
                database.insertOrThrow(DbConstants.CATEGORY_TABLE, null, Category.getCvFromCategory(category));
            } catch (SQLException e) {
                listener.onDatabaseOperationFailed(e.getMessage());
            }
        }
    }

    private Category getCategory(String categoryName) {
        Category category = null;
        if (database != null) {
            Cursor cursor = database.rawQuery(String.format(Locale.getDefault(), "%s \"%s\"",
                    DbConstants.SELECT_CATEGORY_BY_NAME, categoryName), null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    category = Category.getCategoryFromCursor(cursor);
                }
                cursor.close();
            }
        }
        return category;
    }

    @Override
    public void updateProduct(Product product, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            int result = database.update(DbConstants.PRODUCT_TABLE, Product.getContentValuesFromProduct(product,
                    product.getCategoryId()),
                    DbConstants.COLUMN_ID + " = " + product.getId(), null);
            if (result == 1) {
                listener.onDatabaseOperationSucceed("Success");
            } else {
                listener.onDatabaseOperationFailed("Fail");
            }
        }
    }
}
