package com.badoo.badootransactions.products;

import com.badoo.badootransactions.helper.JsonParser;
import com.badoo.badootransactions.model.Product;
import com.badoo.badootransactions.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Copyright (c) 2017 Badoo
 */

class ProductsPresenter implements ProductsContract.Presenter {

    private ProductsContract.View view;

    ProductsPresenter(ProductsContract.View view) {
        this.view = view;
    }

    @Override
    public ArrayList<Product> getProducts() {

        ArrayList<Transaction> transactionsResponse = JsonParser.readTransactions(view.getContext());
        HashMap<String, ArrayList<Transaction>> productMapping = new HashMap<>();

        if(transactionsResponse == null)
            return null;

        for (Transaction transaction : transactionsResponse) {
            String productName = transaction.getProductName();
            if (productMapping.containsKey(productName)) {
                ArrayList<Transaction> productTransactions = productMapping.get(productName);
                productTransactions.add(transaction);
                productMapping.put(productName, productTransactions);
            } else {
                ArrayList<Transaction> productTransactions = new ArrayList<>();
                productTransactions.add(transaction);
                productMapping.put(productName, productTransactions);
            }
        }

        ArrayList<Product> products = new ArrayList<>();
        for (String key : productMapping.keySet()) {
            products.add(new Product(productMapping.get(key)));
        }
        return products;
    }
}
