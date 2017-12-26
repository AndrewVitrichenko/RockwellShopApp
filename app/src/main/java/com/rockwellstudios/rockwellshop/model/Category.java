package com.rockwellstudios.rockwellshop.model;

import android.database.Cursor;

import com.rockwellstudios.rockwellshop.util.DbConstants;

/**
 * Created by Andrew on 18.06.2017.
 */

public class Category {

    private long id;
    private String categoryName;

    public Category() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static Category getCategoryFromCursor(Cursor cursor) {
        Category category = new Category();
        category.setId(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_ID)));
        category.setCategoryName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_CATEGORY_NAME)));
        return category;
    }
}
