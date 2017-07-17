package com.rockwellstudios.rockwellshop.ui.checkout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.core.listeners.CartActionListener;
import com.rockwellstudios.rockwellshop.model.Customer;
import com.rockwellstudios.rockwellshop.model.LineItem;
import com.rockwellstudios.rockwellshop.ui.BaseFragment;
import com.rockwellstudios.rockwellshop.ui.customerlist.CustomerListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckoutFragment extends BaseFragment implements CartActionListener {

    @BindView(R.id.checkout_list_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.sub_total_text_view)
    TextView mSubTotalTextView;
    @BindView(R.id.tax_text_view)
    TextView mTaxTextView;
    @BindView(R.id.total_amount_text_view)
    TextView mTotalAmountTextView;
    @BindView(R.id.radio_group_payment_type)
    RadioGroup mGroupPaymentType;
    @BindView(R.id.button_card)
    RadioButton mButtonCard;
    @BindView(R.id.button_cash)
    RadioButton mButtonCash;
    @BindView(R.id.button_paypal)
    RadioButton mButtonPaypal;
    @BindView(R.id.clear_cart_button)
    Button mButtonClearCart;
    @BindView(R.id.checkout_button)
    Button mButtonCheckOut;

    private View mRootView;
    private CheckoutAdapter mAdapter;


    public CheckoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_checkout, container, false);
        unbinder = ButterKnife.bind(this,mRootView);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mlayoutManager);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<LineItem> mLineItems = new ArrayList<>();
        mAdapter = new CheckoutAdapter(getActivity(),mLineItems, this);
        mRecyclerView.setAdapter(mAdapter);
        mEmptyText.setText(R.string.no_items_found);

        if (mLineItems.size() < 1) {
            showEmptyTextMessage();
        } else {
            hideEmptyTextMessage();
        }
    }

    @Override
    public void onItemDelete(LineItem item) {

    }

    @Override
    public void onItemQuantityChange(LineItem item, int newQuantity) {

    }

    protected void hideEmptyTextMessage() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyText.setVisibility(View.GONE);
    }

    protected void showEmptyTextMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
    }
}
