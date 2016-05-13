package com.dbz.bl.intermediates.RealTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class JobType extends KeyedTable {

    public static final String NAME = "Name";
    public static final String ID = "ID";
    public static final String[] columnNames = new String[] { ID, NAME };

    private String mName;

    private boolean mCName;

    public JobType(String name) {
        mName = name;
        mCName = true;
    }

    // ONLY FOR BACKEND USE - may need refactoring
    // Cant be protected due to package structure
    public  JobType(Integer id, String name) {
        setId(id);
        mName = name;
        mCName = false;
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
        return JobType.class.getSimpleName();
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

}
