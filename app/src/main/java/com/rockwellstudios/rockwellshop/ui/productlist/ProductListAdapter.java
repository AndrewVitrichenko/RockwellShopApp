package com.rockwellstudios.rockwellshop.ui.productlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.core.listeners.OnProductSelectedListener;
import com.rockwellstudios.rockwellshop.model.Product;
import com.rockwellstudios.rockwellshop.util.Formatter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrew on 26.06.17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private List<Product> mProducts;
    private Context mContext;
    private final OnProductSelectedListener mListener;
    private RequestOptions options;

    public ProductListAdapter(List<Product> mProducts, Context mContext, OnProductSelectedListener mListener) {
        this.mProducts = mProducts;
        this.mContext = mContext;
        this.mListener = mListener;
        options = new RequestOptions();
        options.fitCenter();
        options.error(R.drawable.default_image);
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_list, parent);
        return new ProductViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (mProducts != null) {
            Product product = mProducts.get(position);
            Glide.with(mContext).load(product.getImagePath()).apply(options).into(holder.ivProduct);
            holder.tvProductCategory.setText(product.getCategoryName());
            String shortDescription = product.getDescription().substring(0, Math.min(product.getDescription().length(), 70));
            holder.tvProductDescription.setText(shortDescription);
            holder.tvProductName.setText(product.getProductName());
            holder.tvProductPrice.setText(Formatter.formatCurrency(product.getSalePrice()));
        }
    }

    @Override
    public int getItemCount() {
        if (mProducts != null) {
            return mProducts.size();
        } else {
            return 0;
        }
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

        @BindView(R.id.textview_product_category)
        TextView tvProductCategory;
        @BindView(R.id.textview_product_name)
        TextView tvProductName;
        @BindView(R.id.product_image)
        ImageView ivProduct;
        @BindView(R.id.textview_product_price)
        TextView tvProductPrice;
        @BindView(R.id.image_view_add_to_cart_button)
        ImageView ivAddToCart;
        @BindView(R.id.textview_product_description)
        TextView tvProductDescription;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Product selectedProduct = mProducts.get(getLayoutPosition());
            mListener.onSelectProduct(selectedProduct);
        }

        @Override
        public boolean onLongClick(View view) {
            Product selectedProduct = mProducts.get(getLayoutPosition());
            mListener.onLongClickProduct(selectedProduct);
            return true;
        }
    }
}
