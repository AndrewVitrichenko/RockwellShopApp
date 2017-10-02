package com.rockwellstudios.rockwellshop.ui.customerlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.core.listeners.OnCustomerSelectedListener;
import com.rockwellstudios.rockwellshop.model.Customer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerHolder> {

    private List<Customer> mCustomers;
    private final Context mContext;
    private final OnCustomerSelectedListener mListener;
    private boolean shouldHighlightSelectedCustomer = false;
    private int selectedPosition = 0;
    private RequestOptions options;

    public CustomerListAdapter(Context context, OnCustomerSelectedListener listener) {
        mContext = context;
        mListener = listener;
        options = new RequestOptions();
        options.fitCenter();
        options.error(R.drawable.profile_icon);
    }

    public void setCustomers(List<Customer> customers){
        mCustomers = customers;
        notifyDataSetChanged();
    }


    @Override
    public CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_customer_list, parent, false);
        return new CustomerHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CustomerHolder holder, int position) {
        final Customer selectedCustomer = mCustomers.get(position);

        holder.customerName.setText(selectedCustomer.getCustomerName());
        holder.customerEmail.setText(selectedCustomer.getEmailAddress());
        Glide.with(mContext)
                .load(selectedCustomer.getProfileImagePath())
                .apply(options)
                .into(holder.customerHeadShot);

        if (shouldHighlightSelectedCustomer) {
            if (selectedPosition == position) {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.lightBlue));
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        if (mCustomers != null) {
            return mCustomers.size();
        } else {
            return 0;
        }
    }

    public class CustomerHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.image_view_customer_head_shot)
        ImageView customerHeadShot;
        @BindView(R.id.text_view_customer_email)
        TextView customerEmail;
        @BindView(R.id.text_view_customer_name)
        TextView customerName;

        public CustomerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            shouldHighlightSelectedCustomer = true;
            selectedPosition = getLayoutPosition();
            Customer selectedCustomer = mCustomers.get(selectedPosition);
            mListener.onSelectCustomer(selectedCustomer);
            notifyDataSetChanged();

        }

        @Override
        public boolean onLongClick(View v) {
            Customer selectedCustomer = mCustomers.get(selectedPosition);
            mListener.onLongClickCustomer(selectedCustomer);
            return true;
        }
    }
}
