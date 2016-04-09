package com.dbz.bl.intermediates;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Person implements UpdatableTable {

    public static final String FIRST_NAME     = "FirstName";
    public static final String MIDDLE_INITIAL = "MiddleInitial";
    public static final String LAST_NAME      = "LastName";
    public static final String ADDRESS_ID     = "AddressID";
    public static final String[] contactNames = new String[]{FIRST_NAME, MIDDLE_INITIAL, LAST_NAME, ADDRESS_ID};


    private Integer mId;
    private String mFirstName, mMiddleInitial, mLastName;
    public Integer mAddressId;
    private boolean mCFirstName, mCMiddleInitial, mCLastName, mCAddressId;

    public Person(String fname, String middleI, String lname, Integer addressId) {
        mFirstName = fname;
        mMiddleInitial = middleI;
        mLastName = lname;
        mAddressId = addressId;
        mCFirstName = mCMiddleInitial = mCLastName = mCAddressId = true;
    }

    // ONLY FOR BACKEND USE - may need refactoring
    // Cant be protected due to package structure
    public  Person(Integer id, String fname, String middleI, String lname, Integer addressId) {
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
    public List<String> getColumnNames() {
        return Arrays.asList(contactNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCFirstName) changelist.put(FIRST_NAME, mFirstName);
        if (mCMiddleInitial) changelist.put(MIDDLE_INITIAL, mMiddleInitial);
        if (mCLastName) changelist.put(LAST_NAME, mLastName);
        if (mCAddressId) changelist.put(ADDRESS_ID, mAddressId);
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
