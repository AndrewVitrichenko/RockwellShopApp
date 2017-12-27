package com.rockwellstudios.rockwellshop.ui.transaction;

import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.model.LineItem;
import com.rockwellstudios.rockwellshop.model.Transaction;

import java.util.List;

/**
 * Created by andrew on 29.09.17.
 */

public interface TransactionContract {

    interface Model{
        List<Transaction> getAllTransactions();

        List<LineItem> getAllLineItems();

        void saveTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener);

        void updateTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener);

        Transaction getTransactionById(long id);

        void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener);
    }

    interface View{
        void showTransactions(List<Transaction> transactions);

        void showEmptyText();

        void hideEmptyText();

        void showConfirmDeletePromt(Transaction transaction);

        void showMessage(String message);
    }

    interface Presenter{

        void bindView(TransactionContract.View mView);

        void unbindView();

        void loadTransactions();

        void onDeleteItemButtonClicked(Transaction transaction);

        void editTransaction(Transaction transaction);

        void deleteTransaction(Transaction transaction);

        Customer getCustomerById(long id);


    }
}
