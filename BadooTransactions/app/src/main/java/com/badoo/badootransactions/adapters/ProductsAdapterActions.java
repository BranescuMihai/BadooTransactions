package com.badoo.badootransactions.adapters;

import com.badoo.badootransactions.model.Product;

/**
 * Copyright (c) 2017 Badoo
 */

public interface ProductsAdapterActions {

    /**
     * Used to start the details screen
     *
     * @param product is used to get the transaction list
     */
    void onProductClicked(Product product);
}
