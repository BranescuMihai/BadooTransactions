package com.badoo.badootransactions.products;

import android.content.Context;

import com.badoo.badootransactions.model.Product;

import java.util.ArrayList;

/**
 * Copyright (c) 2017 Badoo
 */

public interface ProductsContract {

    interface View {

        Context getContext();
    }

    interface Presenter {

        /**
         * @return all products associated with the transaction list from json
         */
        ArrayList<Product> getProducts();
    }
}
