package com.dbz.bl.intermediates.RealTable;

import com.dbz.bl.intermediates.UpdatableTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Employee implements UpdatableTable {
    public static final String PERSON_ID = "PersonID";
    public static final String SALARY    = "Salary";
    public static final String JOB       = "Job";
    public static final String[] columnNames = new String[] { PERSON_ID, SALARY, JOB };

    private Integer mId;
    public Integer mPersonId, mSalary, mJob;
    private boolean mCPersonId, mCSalary, mCJob;

    public Employee(Integer personId, Integer salary, Integer job) {
        mPersonId = personId;
        mSalary = salary;
        mJob = job;
        mCPersonId = mCSalary = mCJob = true;
    }

    // ONLY FOR BACKEND USE - may need refactoring
    // Cant be protected due to package structure
    public  Employee(Integer id, Integer personId, Integer salary, Integer job) {
        mId = id;
        mPersonId = personId;
        mSalary = salary;
        mJob = job;
        mCPersonId = mCSalary = mCJob = false;
    }

    public Integer getPersonId() {
        return mPersonId;
    }
    public Integer getSalary() {
        return mSalary;
    }
    public Integer getJob() {
        return mJob;
    }

    public void setPersonId(Integer v) {
        mPersonId = v;
        mCPersonId = true;
    }
    public void setSalary(Integer v) {
        mSalary = v;
        mCSalary = true;
    }
    public void setJob(Integer v) {
        mJob = v;
        mCJob = true;
    }

    @Override
    public String getTableName() {
        return Employee.class.getName();
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCPersonId) changelist.put(PERSON_ID, mPersonId);
        if (mCSalary) changelist.put(SALARY, mSalary);
        if (mCJob) changelist.put(JOB, mJob);
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
