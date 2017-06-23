package com.rockwellstudios.rockwellshop.ui.customerlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.ui.BaseFragment;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerListFragment extends BaseFragment {

    private View mRootView;

    public CustomerListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_customer_list, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

}
