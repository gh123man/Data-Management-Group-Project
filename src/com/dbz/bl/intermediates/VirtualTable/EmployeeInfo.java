package com.dbz.bl.intermediates.VirtualTable;

import com.dbz.bl.intermediates.Table;

import java.math.BigDecimal;

/**
 * Created by brian on 5/13/16.
 */
public class EmployeeInfo implements Table {
    private String mFname, mLname, mMi, mS1, mS2, mState, mZip, mJob;
    private BigDecimal mSalary;
    private Integer pId;
    private Integer eId;
    private Integer aId;

    public EmployeeInfo(String mZip, String mFname, String mLname, String mMi, String mS1, String mS2, String mState, BigDecimal mSalary, String job, int pid, int eid, int aid) {
        this.mZip = mZip;
        this.mFname = mFname;
        this.mLname = mLname;
        this.mMi = mMi;
        this.mS1 = mS1;
        this.mS2 = mS2;
        this.mState = mState;
        this.mSalary = mSalary;
        mJob = job;
        pId = pid;
        eId = eid;
        aId = aid;
    }

    public String getFname() {
        return mFname;
    }

    public void setFname(String mFname) {
        this.mFname = mFname;
    }

    public String getLname() {
        return mLname;
    }

    public void setLname(String mLname) {
        this.mLname = mLname;
    }

    public String getMi() {
        return mMi;
    }

    public void setMi(String mMi) {
        this.mMi = mMi;
    }

    public String getS1() {
        return mS1;
    }

    public void setS1(String mS1) {
        this.mS1 = mS1;
    }

    public String getS2() {
        return mS2;
    }

    public void setS2(String mS2) {
        this.mS2 = mS2;
    }

    public String getState() {
        return mState;
    }

    public void setState(String mState) {
        this.mState = mState;
    }

    public String getZip() {
        return mZip;
    }

    public void setZip(String mZip) {
        this.mZip = mZip;
    }

    public BigDecimal getSalary() {
        return mSalary;
    }

    public void setSalary(BigDecimal mSalary) {
        this.mSalary = mSalary;
    }public String getmJob() {
        return mJob;
    }

    public void setJob(String mJob) {
        this.mJob = mJob;
    }

    public String getJob() {
        return mJob;
    }
    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }


}
