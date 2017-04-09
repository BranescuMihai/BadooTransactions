package com.badoo.badootransactions.transactions;

import com.badoo.badootransactions.helper.ConversionHelper;
import com.badoo.badootransactions.helper.DijkstraAlgorithm;
import com.badoo.badootransactions.helper.JsonParser;
import com.badoo.badootransactions.model.ConversionEdge;
import com.badoo.badootransactions.model.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Copyright (c) 2017 Badoo
 */

class TransactionsPresenter implements TransactionsContract.Presenter {

    private DijkstraAlgorithm dijkstraAlgorithm;
    private TreeSet<String> availableTypes;
    private HashMap<String, Double> conversions;

    TransactionsPresenter(TransactionsContract.View view) {

        availableTypes = new TreeSet<>();
        ArrayList<ConversionEdge> edges = JsonParser.readConversions(view.getContext());

        if (edges == null)
            return;

        for (ConversionEdge edge : edges) {
            edge.setRateValue(Double.parseDouble(edge.getRate()));
            availableTypes.add(edge.getTo());
            availableTypes.add(edge.getFrom());
        }
        dijkstraAlgorithm = new DijkstraAlgorithm(edges);
    }

    @Override
    public String getTotal(ArrayList<Transaction> transactions) {

        if (availableTypes.isEmpty())
            return "";

        BigDecimal sum = new BigDecimal(0);
        if (conversions == null) {
            getConversions();
        }

        for (Transaction transaction : transactions) {
            BigDecimal amount = ConversionHelper.getConvertedValue(transaction.getAmount(),
                    transaction.getCurrency(), conversions);
            sum = sum.add(amount);
        }

        //acceptable number of digits
        sum = sum.setScale(4, RoundingMode.CEILING);

        return sum.toString();
    }

    @Override
    public HashMap<String, Double> getConversions() {
        conversions = new HashMap<>();

        for (String availableType : availableTypes) {
            dijkstraAlgorithm.execute(availableType);
            conversions.put(availableType, dijkstraAlgorithm.conversionRate("GBP"));
        }

        return conversions;
    }
}
