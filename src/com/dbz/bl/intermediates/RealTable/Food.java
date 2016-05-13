package com.dbz.bl.intermediates.RealTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Food extends KeyedTable {

    public static final String NAME = "Name";
    public static final String UNIT_COST = "UnitCost";
    public static final String[] columnNames = new String[] { NAME, UNIT_COST };

    private String mName;
    private Float mUnitCost;

    private boolean mCName, mCUnitCost;

    public Food(String name, Float unitCost) {
        mName = name;
        mUnitCost = unitCost;
        mCName = mCUnitCost = true;
    }

    // ONLY FOR BACKEND USE - may need refactoring
    // Cant be protected due to package structure
    public  Food(Integer id, String name, Float unitCost) {
        setId(id);
        mName = name;
        mUnitCost = unitCost;
        mCName = mCUnitCost = false;
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
        return Food.class.getSimpleName();
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCName) changelist.put(NAME, mName);
        if (mCUnitCost) changelist.put(UNIT_COST, mUnitCost);
        return changelist;
    }

}
