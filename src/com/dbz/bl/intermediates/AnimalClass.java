package com.dbz.bl.intermediates;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class AnimalClass extends Table {

    public static final String NAME = "Name";

    private Integer mId;
    private String mName;

    private boolean mCName;

    public AnimalClass(String name) {
        mName = name;
        mCName = false;
    }

    protected AnimalClass(Integer id, String name) {
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
        return AnimalClass.class.getName();
    }

    @Override
    protected Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCName) changelist.put(NAME, mName);
        return changelist;
    }

    @Override
    protected boolean isNew() {
        return mId == null;
    }
}
