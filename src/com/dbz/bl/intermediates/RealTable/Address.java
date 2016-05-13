package com.dbz.bl.intermediates.RealTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Address extends KeyedTable {

    public static final String STREET_1 = "Street1";
    public static final String STREET_2 = "Street2";
    public static final String CITY     = "City";
    public static final String STATE    = "State";
    public static final String ZIP_CODE = "ZipCode";
    public static final String[] columnNames = new String[] { STREET_1, STREET_2, CITY, STATE, ZIP_CODE };

    private String mStreet1, mStreet2, mCity, mState, mZipCode;
    private boolean mCStreet1, mCStreet2, mCCity, mCState, mCZipCode;

    public Address(String street1, String street2, String city, String state, String zipCode) {
        mStreet1 = street1;
        mStreet2 = street2;
        mCity = city;
        mState = state;
        mZipCode = zipCode;
        mCStreet1 = mCStreet2 = mCCity = mCState = mCZipCode = true;
    }

    // ONLY FOR BACKEND USE - may need refactoring
    // Cant be protected due to package structure
    public  Address(Integer id, String street1, String street2, String city, String state, String zipCode) {
        setId(id);
        mStreet1 = street1;
        mStreet2 = street2;
        mCity = city;
        mState = state;
        mZipCode = zipCode;
        mCStreet1 = mCStreet2 = mCCity = mCState = mCZipCode = false;
    }

    public String getStreet1() {
        return mStreet1;
    }
    public String getmStreet2() {
        return mStreet2;
    }
    public String getCity() {
        return mCity;
    }
    public String getState() {
        return mState;
    }
    public String getZipCode() {
        return mZipCode;
    }

    public void setStreet1(String v) {
        mStreet1 = v;
        mCStreet1 = true;
    }

    public void setStreet2(String v) {
        mStreet2 = v;
        mCStreet2 = true;
    }

    public void setCity(String v) {
        mCity = v;
        mCCity = true;
    }

    public void setState(String v) {
        mState = v;
        mCState = true;
    }

    public void setZipCode(String v) {
        mZipCode = v;
        mCZipCode = true;
    }

    @Override
    public String getTableName() {
        return Address.class.getSimpleName();
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCStreet1) changelist.put(STREET_1, mStreet1);
        if (mCStreet2) changelist.put(STREET_2, mStreet2);
        if (mCCity) changelist.put(CITY, mCity);
        if (mCState) changelist.put(STATE, mState);
        if (mCZipCode) changelist.put(ZIP_CODE, mZipCode);
        return changelist;
    }

}
