package com.dbz.bl.intermediates.VirtualTable;

import com.dbz.bl.intermediates.Table;

/**
 * Created by brian on 5/4/16.
 */
public class Expense implements Table {

    private String mName;
    private Float mAmnt;

    public Expense(String name, Float amnt) {
        mName = name;
        mAmnt = amnt;
    }

    public String getName() {
        return mName;
    }

    public Float getAmount() {
        return mAmnt;
    }
}
