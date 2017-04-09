package com.badoo.badootransactions.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Copyright (c) 2017 Badoo
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void initView();

    public Context getContext() {
        return this;
    }
}
