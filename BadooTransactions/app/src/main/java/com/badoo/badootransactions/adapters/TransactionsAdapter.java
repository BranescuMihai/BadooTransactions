package com.badoo.badootransactions.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.badoo.badootransactions.R;
import com.badoo.badootransactions.helper.ConversionHelper;
import com.badoo.badootransactions.model.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Copyright (c) 2017 Badoo
 */

public class TransactionsAdapter extends BaseAdapter {

    private ArrayList<Transaction> transactions;
    private HashMap<String, Double> conversions;

    public TransactionsAdapter(ArrayList<Transaction> transactions, HashMap<String, Double> conversions) {
        this.transactions = transactions;
        this.conversions = conversions;
    }

    @Override
    public int getCount() {
        return transactions.size();
    }

    @Override
    public Object getItem(int position) {
        return transactions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TransactionViewHolder transactionViewHolder;
        Context context = parent.getContext();

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.transaction_element_view, parent, false);

            convertView.setEnabled(false);
            convertView.setOnClickListener(null);

            transactionViewHolder = new TransactionViewHolder();
            transactionViewHolder.transactionSum = (TextView) convertView.findViewById(R.id.transaction_element_title);
            transactionViewHolder.transactionSumConverted = (TextView) convertView.findViewById(R.id.transaction_element_subtitle);

            convertView.setTag(transactionViewHolder);
        } else {
            transactionViewHolder = (TransactionViewHolder) convertView.getTag();
        }

        final Transaction transaction = transactions.get(position);

        if (transaction != null) {
            transactionViewHolder.transactionSum.setText(String.format(context
                            .getString(R.string.transaction_unconverted_text),
                    ConversionHelper.getType(transaction.getCurrency()), transaction.getAmount()));

            BigDecimal amount = ConversionHelper.getConvertedValue(transaction.getAmount(),
                    transaction.getCurrency(), conversions);

            //acceptable number of digits
            amount = amount.setScale(4, RoundingMode.CEILING);

            transactionViewHolder.transactionSumConverted.setText(String.format(context
                    .getString(R.string.transaction_conversion_text), amount.toString()));
        }

        return convertView;
    }

    private static class TransactionViewHolder {
        TextView transactionSum;
        TextView transactionSumConverted;
    }

}
