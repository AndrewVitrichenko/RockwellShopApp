package com.rockwellstudios.rockwellshop.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.ui.checkout.CheckoutFragment;
import com.rockwellstudios.rockwellshop.ui.customerlist.CustomerListFragment;
import com.rockwellstudios.rockwellshop.ui.productlist.ProductListFragment;

/**
 * Created by Andrew on 09.07.2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final int NUM_ITEMS = 3;
    private Context mContext;

    public ViewPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment selectedFragment = null;
        switch (position) {
            case 0:
                selectedFragment = new ProductListFragment();
                break;
            case 1:
                selectedFragment = new CustomerListFragment();
                break;
            case 2:
                selectedFragment = new CheckoutFragment();
                break;
        }
        return selectedFragment;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String mTitle = "";
        switch (position) {
            case 0:
                mTitle = mContext.getString(R.string.label_products);
                break;
            case 1:
                mTitle = mContext.getString(R.string.label_customers);
                break;
            case 2:
                mTitle = mContext.getString(R.string.label_shopping_cart);
                break;
        }
        return mTitle;
    }
}
