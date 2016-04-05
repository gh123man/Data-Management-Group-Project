package com.dbz.bl.intermediates;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Employee extends Table {
    public static final String PERSON_ID = "PersonID";
    public static final String SALARY    = "Salary";
    public static final String JOB       = "Job";

    private Integer mId;
    public Integer mPersonId, mSalary, mJob;
    private boolean mCPersonId, mCSalary, mCJob;

    public Employee(Integer personId, Integer salary, Integer job) {
        mPersonId = personId;
        mSalary = salary;
        mJob = job;
        mCPersonId = mCSalary = mCJob = false;
    }

    protected Employee(Integer id, Integer personId, Integer salary, Integer job) {
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
    protected Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCPersonId) changelist.put(PERSON_ID, mPersonId);
        if (mCSalary) changelist.put(SALARY, mSalary);
        if (mCJob) changelist.put(JOB, mJob);
        return changelist;
    }
}
