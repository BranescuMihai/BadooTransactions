package com.badoo.badootransactions.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.badoo.badootransactions.R;
import com.badoo.badootransactions.adapters.ProductsAdapter;
import com.badoo.badootransactions.adapters.ProductsAdapterActions;
import com.badoo.badootransactions.base.BaseActivity;
import com.badoo.badootransactions.helper.Constants;
import com.badoo.badootransactions.model.Product;
import com.badoo.badootransactions.transactions.TransactionsActivity;

import java.util.ArrayList;

/**
 * Copyright (c) 2017 Badoo
 */
public class ProductsActivity extends BaseActivity implements ProductsContract.View, ProductsAdapterActions {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        initView();
    }

    @Override
    protected void initView() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.product_page_title);
        }

        ProductsContract.Presenter presenter = new ProductsPresenter(this);

        ArrayList<Product> products = presenter.getProducts();
        if (products == null) {
            displayNoAvailableProducts();
            return;
        }

        ListView productList = (ListView) findViewById(R.id.product_list);
        productList.setAdapter(new ProductsAdapter(this, presenter.getProducts()));
    }

    @Override
    public void onProductClicked(Product product) {
        Intent openDetailsIntent = new Intent(this, TransactionsActivity.class);
        openDetailsIntent.putExtra(Constants.EXTRA_PRODUCT, product);
        startActivity(openDetailsIntent);
    }

    private void displayNoAvailableProducts() {
        TextView errorView = (TextView) findViewById(R.id.error_view);
        errorView.setVisibility(View.VISIBLE);
    }
}
