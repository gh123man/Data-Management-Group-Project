package com.dbz.bl.intermediates.VirtualTable;

import com.dbz.bl.intermediates.Table;

import java.util.List;

/**
 * Created by brian on 5/4/16.
 */
public class ExhibitOverview implements Table {

    private String mName;
    private Integer mCap, mAnimalCount;

    public ExhibitOverview(String name, Integer cap, Integer count) {
        mName = name;
        mCap = cap;
        mAnimalCount = count;
    }

    public String getName() {
        return mName;
    }

    public Integer getCapacity() {
        return mCap;
    }

    public Integer getAnimalCount() {
        return mAnimalCount;
    }

}
