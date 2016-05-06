package com.dbz.bl.intermediates.RealTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/5/16.
 */
public class EmployeeExhibit implements UpdatableTable {

    public static final String TABLE_NAME  = "Employee_Exhibit";
    public static final String EMPLOYEE_ID = "EmployeeID";
    public static final String EXHIBIT_ID  = "ExhibitID";
    public static final String[] columnNames = new String[] { EMPLOYEE_ID, EXHIBIT_ID };

    public Integer mEmployeeId, mExhibitId;
    private boolean mCEmployeeId, mCExhibitId;
    private boolean mIsNew;

    public static EmployeeExhibit makeNew(Integer employeeId, Integer exhibitId) {
        return new EmployeeExhibit(employeeId, exhibitId).setNew(true);
    }

    public EmployeeExhibit(Integer employeeId, Integer exhibitId) {
        mIsNew = false;
        mEmployeeId = employeeId;
        mExhibitId = exhibitId;
        mCEmployeeId = mCExhibitId = true;
    }

    private EmployeeExhibit setNew(boolean isNew) {
        mIsNew = isNew;
        return this;
    }

    public Integer getEmployeeId() {
        return mEmployeeId;
    }
    public Integer getExhibitId() {
        return mExhibitId;
    }

    public void setEmployeeId(Integer v) {
        mEmployeeId = v;
        mCEmployeeId = true;
    }
    public void setExhibitId(Integer v) {
        mExhibitId = v;
        mCExhibitId = true;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCEmployeeId) changelist.put(EMPLOYEE_ID, mEmployeeId);
        if (mCExhibitId) changelist.put(EXHIBIT_ID, mExhibitId);
        return changelist;
    }

    @Override
    public boolean isNew() {
        return mIsNew;
    }

    @Override
    public String getInsertCond() {
        return EMPLOYEE_ID + " = " + mEmployeeId + " and " + EXHIBIT_ID + " = " + mExhibitId;
    }
}
