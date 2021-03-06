package com.rockwellstudios.rockwellshop.ui.addproduct;


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
public class AddProductDialogFragment extends BaseFragment {

    private View mRootView;


    public AddProductDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_add_product_dialog, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

}
