package com.rockwellstudios.rockwellshop.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by andrew on 26.06.17.
 */

public class Formatter {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");

    public static String formatCurrency(double amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    public static String formatDate(long date) {
        return dateFormat.format(new Date(date));
    }
}
