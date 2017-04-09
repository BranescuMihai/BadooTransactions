package com.badoo.badootransactions.transactions;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.badoo.badootransactions.R;
import com.badoo.badootransactions.adapters.TransactionsAdapter;
import com.badoo.badootransactions.base.BaseActivity;
import com.badoo.badootransactions.helper.Constants;
import com.badoo.badootransactions.model.Product;

import java.util.HashMap;

/**
 * Copyright (c) 2017 Badoo
 */
public class TransactionsActivity extends BaseActivity implements TransactionsContract.View {

    TransactionsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        initView();
    }

    @Override
    protected void initView() {
        presenter = new TransactionsPresenter(this);
        ListView transactionList = (ListView) findViewById(R.id.transaction_list);
        TextView totalView = (TextView) findViewById(R.id.transactions_sum_view);
        Product selectedProduct = getIntent().getParcelableExtra(Constants.EXTRA_PRODUCT);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(String.format(getString(R.string.transactions_page_title),
                    selectedProduct.getName()));
        }

        HashMap<String, Double> conversions = presenter.getConversions();
        if (conversions.isEmpty()) {
            totalView.setVisibility(View.GONE);
            displayNoAvailableConversions();
            return;
        }

        TransactionsAdapter transactionsAdapter = new TransactionsAdapter(selectedProduct.getTransactions(), conversions);
        transactionList.setAdapter(transactionsAdapter);

        String total = presenter.getTotal(selectedProduct.getTransactions());
        totalView.setText(String.format(getString(R.string.transactions_sum_text), total));
    }

    private void displayNoAvailableConversions() {
        TextView errorView = (TextView) findViewById(R.id.error_view);
        errorView.setVisibility(View.VISIBLE);
    }
}
