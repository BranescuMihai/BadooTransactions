package com.badoo.badootransactions.helper;

import android.util.Log;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Copyright (c) 2017 Badoo
 */

public class ConversionHelper {

    private static final String TAG = ConversionHelper.class.getName();

    /**
     * @param unformattedType used to get the currency as 3 letters (Ex: USD)
     * @return the formatted currency type
     */
    public static String getType(String unformattedType) {
        switch (unformattedType) {
            case "USD":
                return "$";
            case "AUD":
                return "A$";
            case "CAD":
                return "CA$";
            case "GBP":
                return "£";
            case "EUR":
                return "€";
            default:
                Log.d(TAG, "Currency was not defined");
                return unformattedType;
        }
    }

    /**
     * @param unconvertedValue the value of the transaction as String
     * @param type             the type of the transaction in 3 letters format (Ex: USD)
     * @param conversions      conversion rates to GBP
     * @return the value of the transaction converted to GBP
     */
    public static BigDecimal getConvertedValue(String unconvertedValue, String type, HashMap<String, Double> conversions) {
        BigDecimal amount = new BigDecimal(unconvertedValue);
        return amount.multiply(BigDecimal.valueOf(conversions.get(type)));
    }
}
