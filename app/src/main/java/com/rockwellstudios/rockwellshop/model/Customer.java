package com.rockwellstudios.rockwellshop.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.rockwellstudios.rockwellshop.util.DbConstants;

/**
 * Created by Andrew on 18.06.2017.
 */

public class Customer {

    private long id;
    private String customerName;
    private String emailAddress;
    private String phoneNumber;
    private String profileImagePath;
    private String streetAddress;
    private String streetAddressTwo;
    private String city;
    private String state;
    private String postalCode;
    private String note;
    private long dateAdded;
    private long dateOfLastTransaction;

    public Customer() {
        id = 0;
        customerName = "";
        emailAddress = "";
        phoneNumber = "";
        profileImagePath = "empty";
        streetAddress = "";
        streetAddressTwo = "";
        city = "";
        state = "";
        postalCode = "";
        note = "";
        dateAdded = 0L;
        dateOfLastTransaction = 0L;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddressTwo() {
        return streetAddressTwo;
    }

    public void setStreetAddressTwo(String streetAddressTwo) {
        this.streetAddressTwo = streetAddressTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public static Customer getCustomerFromCursor(Cursor cursor) {
        Customer customer = new Customer();
        customer.setId(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_ID)));
        customer.setCustomerName(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_NAME)));
        customer.setEmailAddress(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_EMAIL)));
        customer.setPhoneNumber(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_PHONE)));
        customer.setProfileImagePath(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_IMAGE_PATH)));
        customer.setStreetAddress(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_STREET1)));
        customer.setStreetAddressTwo(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_STREET2)));
        customer.setCity(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_CITY)));
        customer.setState(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_STATE)));
        customer.setPostalCode(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_ZIP)));
        customer.setNote(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_NOTE)));
        customer.setDateAdded(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_DATE_CREATED)));
        customer.setDateOfLastTransaction(cursor.getLong(cursor.getColumnIndex(DbConstants.COLUMN_LAST_UPDATED)));
        return customer;
    }

    public static ContentValues getCvFromCustomer(Customer customer) {
        ContentValues cv = new ContentValues();
        cv.put(DbConstants.COLUMN_NAME, customer.getCustomerName());
        cv.put(DbConstants.COLUMN_EMAIL, customer.getEmailAddress());
        cv.put(DbConstants.COLUMN_PHONE, customer.getPhoneNumber());
        cv.put(DbConstants.COLUMN_IMAGE_PATH, customer.getProfileImagePath());
        cv.put(DbConstants.COLUMN_STREET1, customer.getStreetAddress());
        cv.put(DbConstants.COLUMN_STREET2, customer.getStreetAddressTwo());
        cv.put(DbConstants.COLUMN_CITY, customer.getCity());
        cv.put(DbConstants.COLUMN_STATE, customer.getState());
        cv.put(DbConstants.COLUMN_ZIP, customer.getPostalCode());
        cv.put(DbConstants.COLUMN_NOTE, customer.getNote());
        cv.put(DbConstants.COLUMN_DATE_CREATED, System.currentTimeMillis());
        cv.put(DbConstants.COLUMN_LAST_UPDATED, System.currentTimeMillis());
        return cv;
    }
}
