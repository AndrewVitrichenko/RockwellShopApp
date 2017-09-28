package com.rockwellstudios.rockwellshop.model;

/**
 * Created by Andrew on 18.06.2017.
 */

public class LineItem extends Product{

    private int quantity;

    public LineItem(Product product ,int qty){
        super(product);
        this.setQuantity(qty);
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSumPrice(){
        return getSalePrice() * quantity;
    }
}
