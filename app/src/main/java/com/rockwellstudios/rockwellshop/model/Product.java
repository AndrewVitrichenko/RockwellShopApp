package com.rockwellstudios.rockwellshop.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.rockwellstudios.rockwellshop.util.DbConstants;

/**
 * Created by Andrew on 18.06.2017.
 */
public class Product {

    private long id;
    private String productName;
    private String description;
    private String promoMessage;
    private double salePrice;
    private double purchasePrice;
    private String imagePath;
    private long categoryId;
    private String categoryName;
    private long dateAdded;
    private long dateOfLastTransaction;

    public Product() {
        id = 0;
        productName = "";
        description = "";
        promoMessage = "";
        salePrice = 0;
        purchasePrice = 0;
        imagePath = "";
        categoryId = 0;
        categoryName = "";
        dateAdded = 0;
        dateOfLastTransaction = 0;
    }

    public Product(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.promoMessage = product.getPromoMessage();
        this.salePrice = product.getSalePrice();
        this.purchasePrice = product.getPurchasePrice();
        this.imagePath = product.getImagePath();
        this.categoryId = product.getCategoryId();
        this.categoryName = product.getCategoryName();
        this.dateAdded = product.getDateAdded();
        this.dateOfLastTransaction = product.getDateOfLastTransaction();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPromoMessage() {
        return promoMessage;
    }

    public void setPromoMessage(String promoMessage) {
        this.promoMessage = promoMessage;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public long getDateOfLastTransaction() {
        return dateOfLastTransaction;
    }

    public void setDateOfLastTransaction(long dateOfLastTransaction) {
        this.dateOfLastTransaction = dateOfLastTransaction;
    }

    public static Product getProductFromCursor(Cursor cursor) {
        Product product = new Product();
        product.setId(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_ID)));
        product.setProductName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_NAME)));
        product.setDescription(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_DESCRIPTION)));
        product.setPromoMessage(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_PROMO_MESSAGE)));
        product.setSalePrice(cursor.getDouble(cursor.getColumnIndex(DbConstants.COLUMN_PRICE)));
        product.setPurchasePrice(cursor.getDouble(cursor.getColumnIndex(DbConstants.COLUMN_PURCHASE_PRICE)));
        product.setImagePath(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_IMAGE_PATH)));
        product.setCategoryId(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_CATEGORY_ID)));
        product.setCategoryName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_CATEGORY_NAME)));
        product.setDateAdded(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_DATE_CREATED)));
        product.setDateOfLastTransaction(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_LAST_UPDATED)));
        return product;
    }

    public static ContentValues getContentValuesFromProduct(Product product, long categoryId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.COLUMN_NAME, product.getProductName());
        contentValues.put(DbConstants.COLUMN_DESCRIPTION, product.getDescription());
        contentValues.put(DbConstants.COLUMN_PROMO_MESSAGE, product.getPromoMessage());
        contentValues.put(DbConstants.COLUMN_PRICE, product.getSalePrice());
        contentValues.put(DbConstants.COLUMN_PURCHASE_PRICE, product.getPurchasePrice());
        contentValues.put(DbConstants.COLUMN_IMAGE_PATH, product.getImagePath());
        contentValues.put(DbConstants.COLUMN_CATEGORY_ID, categoryId);
        contentValues.put(DbConstants.COLUMN_CATEGORY_NAME, product.getCategoryName());
        contentValues.put(DbConstants.COLUMN_DATE_CREATED, System.currentTimeMillis());
        contentValues.put(DbConstants.COLUMN_LAST_UPDATED, System.currentTimeMillis());
        return contentValues;
    }

}
