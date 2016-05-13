package com.dbz.bl.intermediates.VirtualTable;

import com.dbz.bl.intermediates.Table;

import java.math.BigDecimal;

/**
 * Created by brian on 5/13/16.
 */
public class EmployeeInfo implements Table {
    private String mFname, mLname, mMi, mS1, mS2, mState, mZip;
    private BigDecimal mSalary;

    public EmployeeInfo(String mZip, String mFname, String mLname, String mMi, String mS1, String mS2, String mState, BigDecimal mSalary) {
        this.mZip = mZip;
        this.mFname = mFname;
        this.mLname = mLname;
        this.mMi = mMi;
        this.mS1 = mS1;
        this.mS2 = mS2;
        this.mState = mState;
        this.mSalary = mSalary;
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
    }
}
