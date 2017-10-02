package com.rockwellstudios.rockwellshop.ui.checkout;

import com.rockwellstudios.rockwellshop.MainApplication;
import com.rockwellstudios.rockwellshop.common.ShoppingCart;
import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.model.LineItem;
import com.rockwellstudios.rockwellshop.model.Transaction;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by andrew on 28.09.17.
 */

public class CheckoutPresenter implements CheckoutContract.Presenter, OnDatabaseOperationCompleteListener {

    private CheckoutContract.View mView;
//    @Inject
    CheckoutContract.Model mRepository;
    @Inject
    ShoppingCart mCart;

    private double subTotal = 0.0;
    private double total;
    private double tax;
    private double taxRate = 0.08;
    private String selectedPayment = "";
    private boolean paid = false;

    public CheckoutPresenter(){MainApplication.getInstance().getAppComponent().inject(this);}


    @Override
    public void bindView(CheckoutContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void unbindView() {
        mView = null;
    }

    @Override
    public void loadLineItems() {
        List<LineItem> lineItems = mRepository.getAllLineItems();
        if (lineItems != null && !lineItems.isEmpty()){
            mView.hideEmptyText();
            mView.showLineItems(lineItems);
        } else {
            mView.showEmptyText();
        }
    }

    private void calculateTotals(List<LineItem> availableLineItems){
        subTotal = 0.0;
        total = 0.0;
        tax = 0.0;

        for (LineItem item : availableLineItems){
            subTotal += item.getSumPrice();
        }

        tax = subTotal * taxRate;
        total = tax + subTotal;
        mView.showCartTotals(tax,subTotal,total);


    }

    @Override
    public void onCheckoutButtonClicked() {
        mView.showConfirmCheckout();
    }

    @Override
    public void onDeleteItemButtonClicked(LineItem item) {
        mCart.removeItemFromCart(item);
        loadLineItems();
    }

    @Override
    public void checkout() {
        if (mCart.getShoppingCart() == null || mCart.getShoppingCart().isEmpty()){
            mView.showMessage("Cart is empty.");
            return;
        }
        if (mCart.getSelectedCustomer() == null || mCart.getSelectedCustomer().getId() == 0){
            mView.showMessage("No customer is selected.");
            return;
        }

        Transaction transaction = new Transaction();
        transaction.setCustomerId(mCart.getSelectedCustomer().getId());
        transaction.setLineItems(mCart.getShoppingCart());
        transaction.setTaxAmount(tax);
        transaction.setSubTotalAmount(subTotal);
        transaction.setTotalAmount(total);
        transaction.setPaymentType(selectedPayment);
        transaction.setPaid(paid);
        mRepository.saveTransaction(transaction,this);
    }

    @Override
    public void onClearButtonClicked() {
        mView.showConfirmClearCart();
    }

    @Override
    public void clearShoppingCart() {
        mCart.clearAllItemsFromCart();
        loadLineItems();
    }

    @Override
    public void setPaymentType(String paymentType) {
        selectedPayment = paymentType;
    }

    @Override
    public void markAsPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public void onItemQtyChanged(LineItem item, int qty) {
        mCart.updateItemQty(item,qty);
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
