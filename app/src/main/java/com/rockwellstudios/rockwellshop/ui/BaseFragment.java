package com.rockwellstudios.rockwellshop.ui;

import android.support.v4.app.Fragment;

import butterknife.Unbinder;

/**
 * Created by andrew on 23.06.17.
 */

public class BaseFragment extends Fragment {

    protected Unbinder unbinder;


    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
