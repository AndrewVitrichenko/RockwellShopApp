package com.rockwellstudios.rockwellshop.ui.checkout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rockwellstudios.rockwellshop.R;
import com.rockwellstudios.rockwellshop.core.listeners.CartActionListener;
import com.rockwellstudios.rockwellshop.model.LineItem;
import com.rockwellstudios.rockwellshop.util.Formatter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrew on 26.06.17.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {

    private List<LineItem> mLineItems;
    private Context mContext;
    private CartActionListener mListener;

    private RequestOptions options;


    public CheckoutAdapter(Context mContext, List<LineItem> mLineItems, CartActionListener mListener) {
        this.mContext = mContext;
        this.mLineItems = mLineItems;
        this.mListener = mListener;
        options = new RequestOptions();
        options.fitCenter();
        options.error(R.drawable.profile_icon);
    }


    @Override
    public CheckoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_shopping_card_list, parent);
        return new CheckoutViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CheckoutViewHolder holder, int position) {
        LineItem lineItem = mLineItems.get(position);
        Glide.with(mContext).load(lineItem.getImagePath()).apply(options).into(holder.productImage);
        holder.productName.setText(lineItem.getProductName());
        holder.productPrice.setText(Formatter.formatCurrency(lineItem.getPurchasePrice()));
        holder.editQty.setText(lineItem.getQuantity());
    }

    @Override
    public int getItemCount() {
        if (mLineItems != null) {
            return mLineItems.size();
        } else {
            return 0;
        }
    }

    public class CheckoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.text_view_product_name)
        TextView productName;
        @BindView(R.id.text_view_price)
        TextView productPrice;
        @BindView(R.id.edit_text_qty)
        EditText editQty;
        @BindView(R.id.button_delete)
        Button btnDelete;

        public CheckoutViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnDelete.setOnClickListener(this);
            editQty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LineItem item = mLineItems.get(getLayoutPosition());
                    updateItemQuantity(item);
                }
            });

        }

        @Override
        public void onClick(View view) {
            LineItem item = mLineItems.get(getLayoutPosition());
            mListener.onItemDelete(item);

        }
    }

    private void updateItemQuantity(final LineItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_enter_item_qty, null);
        builder.setView(rootView);
        View titleView = inflater.inflate(R.layout.dialog_title, null);
        TextView title = titleView.findViewById(R.id.text_view_dialog_title);
        title.setText(item.getProductName());
        builder.setCustomTitle(titleView);

        final EditText editQuantity = rootView.findViewById(R.id.edit_text_item_qty);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!editQuantity.getText().toString().isEmpty()) {
                    int qtyEntered = Integer.parseInt(editQuantity.getText().toString());
                    mListener.onItemQuantityChange(item, qtyEntered);
                } else {
                    Toast.makeText(mContext, "Invalid qty!", Toast.LENGTH_SHORT).show();
                }
            }
        })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });


    }
}
