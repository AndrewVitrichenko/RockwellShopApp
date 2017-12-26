package com.rockwellstudios.rockwellshop.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rockwellstudios.rockwellshop.util.DbConstants;

/**
 * Created by Andrew on 18.06.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "rockwell_shop.db";
    private static final String LOG_TAG = "DB_LOG";

    private static DataBaseHelper sDataBaseHelperInstance;

    public static void createInstance(Context mContext) {
        if (sDataBaseHelperInstance == null) {
            sDataBaseHelperInstance = new DataBaseHelper(mContext);
        }
    }

    public static synchronized DataBaseHelper getInstance() {
        return sDataBaseHelperInstance;
    }


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_RETAILER_TABLE);
        db.execSQL(CREATE_TRANSACTION_TABLE);
        Log.d(LOG_TAG, "Database created");
        Log.d(LOG_TAG, "Create Product: " + CREATE_PRODUCT_TABLE);
        Log.d(LOG_TAG, "Create Customer: " + CREATE_CUSTOMER_TABLE);
        Log.d(LOG_TAG, "Create Retailer: " + CREATE_RETAILER_TABLE);
        Log.d(LOG_TAG, "Create Category: " + CREATE_CATEGORY_TABLE);
        Log.d(LOG_TAG, "Create SalesTransaction: " + CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //String to create a customer table
    private static final String CREATE_CUSTOMER_TABLE =
            "CREATE TABLE " + DbConstants.CUSTOMER_TABLE + "("
                    + DbConstants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DbConstants.COLUMN_NAME + " TEXT NOT NULL, "
                    + DbConstants.COLUMN_EMAIL + " TEXT, "
                    + DbConstants.COLUMN_IMAGE_PATH + " TEXT, "
                    + DbConstants.COLUMN_PHONE + " TEXT, "
                    + DbConstants.COLUMN_STREET1 + " TEXT, "
                    + DbConstants.COLUMN_STREET2 + " TEXT, "
                    + DbConstants.COLUMN_CITY + " TEXT, "
                    + DbConstants.COLUMN_STATE + " TEXT, "
                    + DbConstants.COLUMN_ZIP + " TEXT, "
                    + DbConstants.COLUMN_NOTE + " TEXT, "
                    + DbConstants.COLUMN_DATE_CREATED + " BIGINT, "
                    + DbConstants.COLUMN_LAST_UPDATED + " BIGINT " + ")";


    //String to create a product table
    private static final String CREATE_PRODUCT_TABLE =
            "CREATE TABLE " + DbConstants.PRODUCT_TABLE + "("
                    + DbConstants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DbConstants.COLUMN_NAME + " TEXT NOT NULL, "
                    + DbConstants.COLUMN_DESCRIPTION + " TEXT, "
                    + DbConstants.COLUMN_PROMO_MESSAGE + " TEXT, "
                    + DbConstants.COLUMN_PRICE + " NUMERIC, "
                    + DbConstants.COLUMN_PURCHASE_PRICE + " NUMERIC, "
                    + DbConstants.COLUMN_IMAGE_PATH + " TEXT, "
                    + DbConstants.COLUMN_CATEGORY_ID + " INTEGER, "
                    + DbConstants.COLUMN_CATEGORY_NAME + " TEXT, "
                    + DbConstants.COLUMN_DATE_CREATED + " BIGINT, "
                    + DbConstants.COLUMN_LAST_UPDATED + " BIGINT, "
                    + "FOREIGN KEY(category_id) REFERENCES category(_id)" + ")";


    //String to create a retailer table
    private static final String CREATE_RETAILER_TABLE =
            "CREATE TABLE " + DbConstants.RETAILER_TABLE + "("
                    + DbConstants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DbConstants.COLUMN_NAME + " TEXT NOT NULL, "
                    + DbConstants.COLUMN_EMAIL + " TEXT, "
                    + DbConstants.COLUMN_PHONE + " TEXT, "
                    + DbConstants.COLUMN_STREET1 + " TEXT, "
                    + DbConstants.COLUMN_STREET2 + " TEXT, "
                    + DbConstants.COLUMN_CITY + " TEXT, "
                    + DbConstants.COLUMN_STATE + " TEXT, "
                    + DbConstants.COLUMN_ZIP + " TEXT, "
                    + DbConstants.COLUMN_INDUSTRY + " TEXT, "
                    + DbConstants.COLUMN_DATE_CREATED + " BIGINT, "
                    + DbConstants.COLUMN_LAST_UPDATED + " BIGINT, "
                    + DbConstants.COLUMN_CONTACT_PERSON + " TEXT " + ")";


    //String to create a transaction table
    //String to create a transaction table
    private static final String CREATE_TRANSACTION_TABLE =
            "CREATE TABLE " + DbConstants.TRANSACTION_TABLE + "("
                    + DbConstants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + DbConstants.COLUMN_CUSTOMER_ID + " INTEGER, "
                    + DbConstants.COLUMN_DATE_CREATED + " BIGINT, "
                    + DbConstants.COLUMN_SUB_TOTAL_AMOUNT + " NUMERIC, "
                    + DbConstants.COLUMN_LINE_ITEMS + " TEXT, "
                    + DbConstants.COLUMN_TAX_AMOUNT + " NUMERIC, "
                    + DbConstants.COLUMN_PAYMENT_STATUS + " INTEGER, "
                    + DbConstants.COLUMN_PAYMENT_TYPE + " TEXT, "
                    + DbConstants.COLUMN_TOTAL_AMOUNT + " NUMERIC, "
                    + DbConstants.COLUMN_LAST_UPDATED + " BIGINT, "
                    + "FOREIGN KEY(customer_id) REFERENCES customer(_id)" + ")";


    //String to create a category table
    private static final String CREATE_CATEGORY_TABLE =
            "CREATE TABLE " + DbConstants.CATEGORY_TABLE + "("
                    + DbConstants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DbConstants.COLUMN_NAME + " TEXT NOT NULL, "
                    + DbConstants.COLUMN_DATE_CREATED + " BIGINT, "
                    + DbConstants.COLUMN_LAST_UPDATED + " BIGINT " + ")";

}
