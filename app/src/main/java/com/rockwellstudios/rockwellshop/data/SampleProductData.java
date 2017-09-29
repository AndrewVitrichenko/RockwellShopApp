package com.rockwellstudios.rockwellshop.data;

import com.rockwellstudios.rockwellshop.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 29.09.17.
 */

public class SampleProductData {

    public static List<Product> getSampleProducts() {
        List<Product> products = new ArrayList<>();

        Product product = new Product();
        product.setProductName("Car");
        product.setCategoryName("Electronics");
        product.setDescription("Just car");
        product.setImagePath("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcWzAJb0c3t1waqim9j4-vBqXWHXcgjzV8FRARcxTOp8wxtvBc");
        product.setSalePrice(25000.99);
        products.add(product);

        product = new Product();
        product.setProductName("Car");
        product.setCategoryName("Electronics");
        product.setDescription("Just another car");
        product.setImagePath("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmx1CKUrR95QWchNegsHuOAnSOfkotdxw2iheS2DhZ58dbaJzx");
        product.setSalePrice(30000.99);
        products.add(product);

        product = new Product();
        product.setProductName("Printer");
        product.setCategoryName("Electronics");
        product.setDescription("Just printer");
        product.setImagePath("http://thewirecutter.com/wp-content/uploads/2016/09/all-in-one-printers-lowres-8270219.jpg");
        product.setSalePrice(200.10);
        products.add(product);

        product = new Product();
        product.setProductName("Computer");
        product.setCategoryName("Electronics");
        product.setDescription("Just computer");
        product.setImagePath("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWEjpRYXIlK8aJhMXsfAPQ6SVH74Ee-HY1cTzRzkvNR-HtlDz4eg");
        product.setSalePrice(1000.50);
        products.add(product);


        return products;

    }
}
