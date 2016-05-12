package com.dbz.bl.intermediates.RealTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Location implements UpdatableTable {

    public static final String NAME = "Name";
    public static final String EXHIBIT_CAPACITY = "ExhibitCapacity";
    public static final String[] columnNames = new String[] { NAME,EXHIBIT_CAPACITY };

    private Integer mId, mExhibitCapacity;
    private String mName;


    private boolean mCName;
    private boolean mCExhibitCapacity;

    public Location(String name, Integer ExhibitCapacity) {
        mName = name;
        mExhibitCapacity = ExhibitCapacity;
        mCName = true;
        mCExhibitCapacity = true;
    }

    // ONLY FOR BACKEND USE - may need refactoring
    // Cant be protected due to package structure
    public  Location(Integer id, String name, Integer ExhibitCapacity) {
        mId = id;
        mName = name;
        mExhibitCapacity = ExhibitCapacity;
        mCName = false;
        mCExhibitCapacity = false;

    }

    public Integer getID() {
        return mId;
    }
    public String getName() {
        return mName;
    }
    public Integer getmExhibitCapacity(){
        return mExhibitCapacity;
    }

    public void setName(String v) {
        mName = v;
        mCName = true;
    }

    public void setExhibitCapacity(Integer v){
        mExhibitCapacity = v;
        mCExhibitCapacity = true;
    }

    @Override
    public String getTableName() {
        return Location.class.getSimpleName();
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCName) changelist.put(NAME, mName);
        if (mCExhibitCapacity) changelist.put(EXHIBIT_CAPACITY, mExhibitCapacity);
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
