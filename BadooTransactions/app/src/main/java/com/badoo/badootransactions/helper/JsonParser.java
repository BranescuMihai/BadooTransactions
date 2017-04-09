package com.badoo.badootransactions.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.badoo.badootransactions.model.ConversionEdge;
import com.badoo.badootransactions.model.Transaction;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Copyright (c) 2017 Badoo
 */

public class JsonParser {

    private static final String TAG = JsonParser.class.getName();

    /**
     * @param context used to load the assets
     * @return a list of transactions from the json file
     */
    public static ArrayList<Transaction> readTransactions(Context context) {
        Type transactionsListType = new TypeToken<ArrayList<Transaction>>() {
        }.getType();
        return new Gson().fromJson(loadJsonFromAssets(context, "transactions.json"),
                transactionsListType);
    }

    /**
     * @param context used to load the assets
     * @return a list of rates from the json file
     */
    public static ArrayList<ConversionEdge> readConversions(Context context) {
        Type conversionListType = new TypeToken<ArrayList<ConversionEdge>>() {
        }.getType();
        return new Gson().fromJson(loadJsonFromAssets(context, "rates.json"),
                conversionListType);
    }

    private static String loadJsonFromAssets(Context context, String fileName) {
        String json;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e(TAG, ex.toString());
            return null;
        }
        return json;
    }
}
