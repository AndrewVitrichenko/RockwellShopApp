package com.rockwellstudios.rockwellshop.ui.transaction;

import com.rockwellstudios.rockwellshop.MainApplication;
import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.model.Transaction;
import com.rockwellstudios.rockwellshop.ui.customerlist.CustomerListContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by andrew on 29.09.17.
 */

public class TransactionPresenter implements TransactionContract.Presenter, OnDatabaseOperationCompleteListener {

    private TransactionContract.View mView;
//    @Inject
    TransactionContract.Model mRepository;
//    @Inject
    CustomerListContract.Model mCustomerRepository;

    public TransactionPresenter() {
        MainApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void bindView(TransactionContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void unbindView() {
        mView = null;
    }

    @Override
    public void loadTransactions() {
        List<Transaction> availableTransactions = mRepository.getAllTransactions();
        if (availableTransactions != null && !availableTransactions.isEmpty()) {
            mView.hideEmptyText();
            mView.showTransactions(availableTransactions);
        } else {
            mView.showEmptyText();
        }
    }

    @Override
    public void onDeleteItemButtonClicked(Transaction transaction) {
        mView.showConfirmDeletePromt(transaction);
    }

    @Override
    public void editTransaction(Transaction transaction) {
        mRepository.updateTransaction(transaction, this);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        mRepository.deleteTransaction(transaction.getId(), this);
    }

    @Override
    public Customer getCustomerById(long id) {
        return mCustomerRepository.getCustomerById(id);
    }

    @Override
    public void onDatabaseOperationFailed(String error) {
        mView.showMessage("Error message : " + error);
    }

    @Override
    public void onDatabaseOperationSucceed(String message) {
        mView.showMessage("Success message : " + message);
    }
}
