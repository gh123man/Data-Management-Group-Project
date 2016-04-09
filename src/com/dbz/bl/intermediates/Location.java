package com.dbz.bl.intermediates;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Location implements UpdatableTable {

    public static final String NAME = "Name";
    public static final String[] columnNames = new String[] { NAME };

    private Integer mId;
    private String mName;

    private boolean mCName;

    public Location(String name) {
        mName = name;
        mCName = true;
    }

    // ONLY FOR BACKEND USE - may need refactoring
    // Cant be protected due to package structure
    public  Location(Integer id, String name) {
        mId = id;
        mName = name;
        mCName = false;
    }

    public Integer getID() {
        return mId;
    }
    public String getName() {
        return mName;
    }

    public void setName(String v) {
        mName = v;
        mCName = true;
    }

    @Override
    public String getTableName() {
        return Location.class.getName();
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCName) changelist.put(NAME, mName);
        return changelist;
    }

    @Override
    public boolean isNew() {
        return mId == null;
    }

    @Override
    public String getInsertCond() {
        return "ID = " + mId;
    }
}
