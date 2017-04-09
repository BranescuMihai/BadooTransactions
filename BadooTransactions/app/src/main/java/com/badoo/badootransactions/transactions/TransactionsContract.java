package com.badoo.badootransactions.transactions;

import android.content.Context;

import com.badoo.badootransactions.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Copyright (c) 2017 Badoo
 */

interface TransactionsContract {

    interface View {

        Context getContext();
    }

    interface Presenter {

        /**
         * @param transactions all transactions associated with the selected products
         * @return the sum of all transactions converted to GBP
         */
        String getTotal(ArrayList<Transaction> transactions);

        /**
         * @return a hashmap of conversion rates from all currencies to GBP
         */
        HashMap<String, Double> getConversions();
    }
}
