package com.dbz.bl.intermediates;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Person extends Table {

    public static final String FIRST_NAME     = "FirstName";
    public static final String MIDDLE_INITIAL = "MiddleInitial";
    public static final String LAST_NAME      = "LastName";
    public static final String ADDRESS_ID     = "AddressID";

    private Integer mId;
    private String mFirstName, mMiddleInitial, mLastName;
    public Integer mAddressId;
    private boolean mCFirstName, mCMiddleInitial, mCLastName, mCAddressId;

    public Person(String fname, String middleI, String lname, Integer addressId) {
        mFirstName = fname;
        mMiddleInitial = middleI;
        mLastName = lname;
        mAddressId = addressId;
        mCFirstName = mCMiddleInitial = mCLastName = mCAddressId = false;
    }

    protected Person(Integer id, String fname, String middleI, String lname, Integer addressId) {
        mId = id;
        mFirstName = fname;
        mMiddleInitial = middleI;
        mLastName = lname;
        mAddressId = addressId;
        mCFirstName = mCMiddleInitial = mCLastName = mCAddressId = false;
    }

    public Integer getID() {
        return mId;
    }
    public String getFirstName() {
        return mFirstName;
    }
    public String getMiddleInitial() {
        return mMiddleInitial;
    }
    public String getLastName() {
        return mLastName;
    }

    public Integer getmAddressId() {
        return mAddressId;
    }

    public void setFirstname(String v) {
        mFirstName = v;
        mCFirstName = true;
    }
    public void setMiddleInitial(String v) {
        mMiddleInitial = v;
        mCMiddleInitial = true;
    }
    public void setLastName(String v) {
        mLastName = v;
        mCLastName = true;
    }
    public void setAddressId(Integer v) {
        mAddressId = v;
        mCAddressId = true;
    }

    @Override
    public String getTableName() {
        return Person.class.getName();
    }

    @Override
    protected Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCFirstName) changelist.put(FIRST_NAME, mFirstName);
        if (mCMiddleInitial) changelist.put(MIDDLE_INITIAL, mMiddleInitial);
        if (mCLastName) changelist.put(LAST_NAME, mLastName);
        if (mCAddressId) changelist.put(ADDRESS_ID, mAddressId);
        return changelist;
    }

    @Override
    protected boolean isNew() {
        return mId == null;
    }
}
