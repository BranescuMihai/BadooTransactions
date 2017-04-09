package com.badoo.badootransactions.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Copyright (c) 2017 Badoo
 */

public class Product implements Parcelable {

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    private int count;
    private String name;
    private ArrayList<Transaction> transactions;

    public Product(@NonNull ArrayList<Transaction> transactions) {
        if (!transactions.isEmpty()) {
            setCount(transactions.size());
            setTransactions(transactions);
            setName(transactions.get(0).getProductName());
        }
    }

    private Product(Parcel in) {
        this.count = in.readInt();
        this.name = in.readString();
        this.transactions = new ArrayList<>();
        in.readList(this.transactions, Transaction.class.getClassLoader());
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    //private because this should always be equal to the transactions list length
    private void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeString(this.name);
        dest.writeList(this.transactions);
    }
}
