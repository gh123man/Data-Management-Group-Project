package com.dbz.bl.intermediates;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Location extends Table {

    public static final String NAME = "Name";

    private Integer mId;
    private String mName;

    private boolean mCName;

    public Location(String name) {
        mName = name;
        mCName = false;
    }

    protected Location(Integer id, String name) {
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
    protected Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCName) changelist.put(NAME, mName);
        return changelist;
    }
}
