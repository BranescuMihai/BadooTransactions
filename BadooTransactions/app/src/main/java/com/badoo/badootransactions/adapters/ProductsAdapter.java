package com.badoo.badootransactions.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.badoo.badootransactions.R;
import com.badoo.badootransactions.model.Product;

import java.util.ArrayList;

/**
 * Copyright (c) 2017 Badoo
 */

public class ProductsAdapter extends BaseAdapter {

    private ArrayList<Product> products;
    private ProductsAdapterActions productsAdapterActions;

    public ProductsAdapter(ProductsAdapterActions productsAdapterActions, ArrayList<Product> products) {
        this.products = products;
        this.productsAdapterActions = productsAdapterActions;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductViewHolder productViewHolder;
        Context context = parent.getContext();

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_element_view, parent, false);

            productViewHolder = new ProductViewHolder();
            productViewHolder.productName = (TextView) convertView.findViewById(R.id.product_element_title);
            productViewHolder.transactionsCount = (TextView) convertView.findViewById(R.id.product_element_subtitle);
            productViewHolder.productContainer = (LinearLayout) convertView.findViewById(R.id.product_element_container);

            convertView.setTag(productViewHolder);

        } else {
            productViewHolder = (ProductViewHolder) convertView.getTag();
        }

        final Product product = products.get(position);

        if (product != null) {
            productViewHolder.productName.setText(product.getName());
            productViewHolder.transactionsCount
                    .setText(String.format(context.getString(R.string.product_count_text), product.getCount()));
            productViewHolder.productContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productsAdapterActions.onProductClicked(product);
                }
            });
        }

        return convertView;
    }

    private static class ProductViewHolder {
        LinearLayout productContainer;
        TextView productName;
        TextView transactionsCount;
    }
}
