package com.rockwellstudios.rockwellshop.ui.checkout;

import com.rockwellstudios.rockwellshop.core.listeners.OnDatabaseOperationCompleteListener;
import com.rockwellstudios.rockwellshop.model.LineItem;
import com.rockwellstudios.rockwellshop.model.Transaction;

import java.util.List;

/**
 * Created by andrew on 27.07.17.
 */

public class CheckoutContract {

    interface Model {
        List<LineItem> getAllLineItems();

        void saveTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener);

        void updateTransaction(Transaction transaction);

    }

    interface View {
        void showLineItems(List<LineItem> items);

        void showEmptyText();

        void hideEmptyText();

        void showCartTotals(double tax, double subTotal, double total);

        void showConfirmCheckout();

        void showConfirmClearCart();

        void showMessage(String message);

    }

    interface Presenter {

        void bindView(CheckoutContract.View mView);

        void unbindView();

        void loadLineItems();

        void onCheckoutButtonClicked();

        void onDeleteItemButtonClicked(LineItem item);

        void checkout();

        void onClearButtonClicked();

        void clearShoppingCart();

        void setPaymentType(String paymentType);

        void markAsPaid(boolean paid);

        void onItemQtyChanged(LineItem item, int qty);
    }
}
