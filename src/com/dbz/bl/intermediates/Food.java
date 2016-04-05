package com.dbz.bl.intermediates;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Food extends Table {

    public static final String NAME = "Name";
    public static final String UNIT_COST = "UnitCost";

    private Integer mId;
    private String mName;
    private Float mUnitCost;

    private boolean mCName, mCUnitCost;

    public Food(String name, Float unitCost) {
        mName = name;
        mUnitCost = unitCost;
        mCName = mCUnitCost = false;
    }

    protected Food(Integer id, String name, Float unitCost) {
        mId = id;
        mName = name;
        mUnitCost = unitCost;
        mCName = mCUnitCost = false;
    }

    public Integer getID() {
        return mId;
    }
    public String getName() {
        return mName;
    }
    public Float getUnitCost() {
        return mUnitCost;
    }

    public void setName(String v) {
        mName = v;
        mCName = true;
    }

    public void setUnitCost(Float v) {
        mUnitCost = v;
        mCUnitCost = true;
    }

    @Override
    public String getTableName() {
        return JobType.class.getName();
    }

    @Override
    protected Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCName) changelist.put(NAME, mName);
        if (mCUnitCost) changelist.put(UNIT_COST, mUnitCost);
        return changelist;
    }
}
