package com.rockwellstudios.rockwellshop.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rockwellstudios.rockwellshop.util.DbConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 18.06.2017.
 */

public class Transaction {
    private long id;
    private long customerId;
    private double subTotalAmount;
    private double taxAmount;
    private double totalAmount;
    private boolean paid;
    private String paymentType;
    private long transactionDate;
    private long modifiedDate;

    // this property cannot be persisted
    private List<LineItem> lineItems;

    // but this will
    private String jsonLineItems;

    public Transaction() {
        id = 0;
        customerId = -1;
        subTotalAmount = 0;
        taxAmount = 0;
        totalAmount = 0;
        paid = false;
        paymentType = "";
        transactionDate = 0;
        modifiedDate = 0;
        lineItems = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(double subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<LineItem> getLineItems() {
        Gson gson = new Gson();
        List<LineItem> result = gson.fromJson(getJsonLineItems(), new TypeToken<ArrayList<LineItem>>() {
        }.getType());
        return result;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
        Gson gson = new Gson();
        String jsonItems = gson.toJson(lineItems);
        this.setJsonLineItems(jsonItems);
    }

    public String getJsonLineItems() {
        return jsonLineItems;
    }

    public void setJsonLineItems(String jsonLineItems) {
        this.jsonLineItems = jsonLineItems;
    }

    public static Transaction getTransactionFromCursor(Cursor cursor) {
        Transaction transaction = new Transaction();
        transaction.setId(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_ID)));
        transaction.setJsonLineItems(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_LINE_ITEMS)));
        transaction.setCustomerId(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_CUSTOMER_ID)));
        transaction.setSubTotalAmount(cursor.getDouble(cursor.getColumnIndex(DbConstants.COLUMN_SUB_TOTAL_AMOUNT)));
        transaction.setTaxAmount(cursor.getDouble(cursor.getColumnIndex(DbConstants.COLUMN_TAX_AMOUNT)));
        transaction.setTotalAmount(cursor.getDouble(cursor.getColumnIndex(DbConstants.COLUMN_TOTAL_AMOUNT)));
        transaction.setPaid(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_PAYMENT_STATUS)) > 0);
        transaction.setPaymentType(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_PAYMENT_TYPE)));
        transaction.setTransactionDate(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_DATE_CREATED)));
        transaction.setModifiedDate(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_LAST_UPDATED)));
        return transaction;
    }

    public static ContentValues getCvFromTransaction(Transaction transaction) {
        ContentValues cv = new ContentValues();
//        cv.put(DbConstants.COLUMN_ID, transaction.getId());
        cv.put(DbConstants.COLUMN_LINE_ITEMS, transaction.getJsonLineItems());
        cv.put(DbConstants.COLUMN_CUSTOMER_ID, transaction.getCustomerId());
        cv.put(DbConstants.COLUMN_SUB_TOTAL_AMOUNT, transaction.getSubTotalAmount());
        cv.put(DbConstants.COLUMN_TAX_AMOUNT, transaction.getTaxAmount());
        cv.put(DbConstants.COLUMN_TOTAL_AMOUNT, transaction.getTotalAmount());
        cv.put(DbConstants.COLUMN_PAYMENT_STATUS, convertBooleanToInt(transaction.isPaid()));
        cv.put(DbConstants.COLUMN_PAYMENT_TYPE, transaction.getPaymentType());
        cv.put(DbConstants.COLUMN_DATE_CREATED, System.currentTimeMillis());
        cv.put(DbConstants.COLUMN_LAST_UPDATED, System.currentTimeMillis());
        return cv;
    }

    private static int convertBooleanToInt(boolean paid) {
        return paid ? 1 : 0;
    }


}
