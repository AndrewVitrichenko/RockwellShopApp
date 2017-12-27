package com.rockwellstudios.rockwellshop.ui.transaction;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.rockwellstudios.rockwellshop.MainApplication;
import com.rockwellstudios.rockwellshop.common.ShoppingCart;
import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.data.DataBaseHelper;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.model.LineItem;
import com.rockwellstudios.rockwellshop.model.Product;
import com.rockwellstudios.rockwellshop.model.Transaction;
import com.rockwellstudios.rockwellshop.util.DbConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by andrew on 27.12.17.
 */

public class TransactionSqlRepository implements TransactionContract.Model {

    private SQLiteDatabase database;

    @Inject
    ShoppingCart mShoppingCart;

    public TransactionSqlRepository() {
        database = DataBaseHelper.getInstance().getWritableDatabase();
        MainApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        if (database != null) {
            Cursor cursor = database.rawQuery(DbConstants.SELECT_ALL_TRANSACTIONS, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        transactions.add(Transaction.getTransactionFromCursor(cursor));
                        cursor.moveToNext();
                    }
                }
                cursor.close();
            }
        }
        return transactions;
    }

    @Override
    public Transaction getTransactionById(long id) {
        if (database != null) {
            String query = String.format(Locale.getDefault(),
                    "%s %d", DbConstants.SELECT_TRANSACTION_BY_ID, id);
            Cursor cursor = database.rawQuery(query, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    return Transaction.getTransactionFromCursor(cursor);
                }
                cursor.close();
            }
        }
        return null;
    }

    @Override
    public void saveTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            try {
                database.insertOrThrow(DbConstants.TRANSACTION_TABLE, null,
                        Transaction.getCvFromTransaction(transaction));
                listener.onDatabaseOperationSucceed("Success");
            } catch (SQLException e) {
                listener.onDatabaseOperationFailed(e.getMessage());
            }
        }
    }

    @Override
    public void updateTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            int result = database.update(DbConstants.TRANSACTION_TABLE,
                    Transaction.getCvFromTransaction(transaction),
                    DbConstants.COLUMN_ID + " = " + transaction.getId(), null);
            if (result == 1) {
                listener.onDatabaseOperationSucceed("Success");
            } else {
                listener.onDatabaseOperationFailed("Fail");
            }
        }
    }


    @Override
    public void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener) {
        if (database != null) {
            int result = database.delete(DbConstants.TRANSACTION_TABLE,
                    DbConstants.COLUMN_ID + " = " + id, null);
            if (result > 0) {
                listener.onDatabaseOperationSucceed("Success");
            } else {
                listener.onDatabaseOperationFailed("Error");
            }
        }
    }

    @Override
    public List<LineItem> getAllLineItems() {
        return mShoppingCart.getShoppingCart();
    }

}
