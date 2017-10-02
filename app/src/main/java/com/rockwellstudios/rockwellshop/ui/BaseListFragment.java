package com.rockwellstudios.rockwellshop.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.model.Product;
import com.rockwellstudios.rockwellshop.ui.productlist.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrew on 04.07.17.
 */

public abstract class BaseListFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.empty_text)
    protected TextView mEmptyText;
    @BindView(R.id.fab)
    protected FloatingActionButton mFab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_base_list, container,false);
        unbinder = ButterKnife.bind(this, mRootView);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mlayoutManager);
        mEmptyText.setText(getEmptyText());
        return mRootView;
    }

    public abstract int getEmptyText();

    protected void hideEmptyTextMessage() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyText.setVisibility(View.GONE);
    }

    protected void showEmptyTextMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
    }


}
