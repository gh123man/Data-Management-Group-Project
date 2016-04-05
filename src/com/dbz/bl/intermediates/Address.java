package com.dbz.bl.intermediates;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Address extends Table {

    public static final String STREET_1 = "Street1";
    public static final String STREET_2 = "Street2";
    public static final String CITY     = "City";
    public static final String STATE    = "State";
    public static final String ZIP_CODE = "ZipCOde";

    private Integer mId;
    private String mStreet1, mStreet2, mCity, mState, mZipCode;
    private boolean mCStreet1, mCStreet2, mCCity, mCState, mCZipCode;

    public Address(String street1, String street2, String city, String state, String zipCode) {
        mStreet1 = street1;
        mStreet2 = street2;
        mCity = city;
        mState = state;
        mZipCode = zipCode;
        mCStreet1 = mCStreet2 = mCCity = mCState = mCZipCode = false;
    }

    protected Address(Integer id, String street1, String street2, String city, String state, String zipCode) {
        mId = id;
        mStreet1 = street1;
        mStreet2 = street2;
        mCity = city;
        mState = state;
        mZipCode = zipCode;
        mCStreet1 = mCStreet2 = mCCity = mCState = mCZipCode = false;
    }

    public Integer getID() {
        return mId;
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
        return Address.class.getName();
    }

    @Override
    protected Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCStreet1) changelist.put(STREET_1, mStreet1);
        if (mCStreet2) changelist.put(STREET_2, mStreet2);
        if (mCCity) changelist.put(CITY, mCity);
        if (mCState) changelist.put(STATE, mState);
        if (mCZipCode) changelist.put(ZIP_CODE, mZipCode);
        return changelist;
    }

    @Override
    protected boolean isNew() {
        return mId == null;
    }
}
