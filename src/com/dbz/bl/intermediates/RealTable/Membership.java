package com.dbz.bl.intermediates.RealTable;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Membership extends KeyedTable {

    public static final String PERSON_ID       = "PersonID";
    public static final String EXPIRATION_DATE = "ExpirationDate";
    public static final String[] columnNames = new String[] { PERSON_ID, EXPIRATION_DATE };

    private Date mExpirationDate;
    private Integer mPersonId;

    private boolean mCPersonId, mCExpirationDate;

    public Membership(Integer personId, Date date) {
        mPersonId = personId;
        mExpirationDate = date;
        mCExpirationDate = mCPersonId = true;
    }

    // ONLY FOR BACKEND USE - may need refactoring
    // Cant be protected due to package structure
    public  Membership(Integer id, Integer personId, Date date) {
        setId(id);
        mPersonId = personId;
        mExpirationDate = date;
        mCExpirationDate = mCPersonId = false;
    }

    public Integer getPersonId() {
        return mPersonId;
    }
    public Date getExperationDate() {
        return mExpirationDate;
    }


    public void setPersonId(Integer v) {
        mPersonId = v;
        mCPersonId = true;
    }
    public void setDate(Date v) {
        mExpirationDate = v;
        mCExpirationDate = true;
    }

    @Override
    public String getTableName() {
        return Membership.class.getSimpleName();
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCPersonId) changelist.put(PERSON_ID, mPersonId);
        if (mCExpirationDate) changelist.put(EXPIRATION_DATE, mExpirationDate);
        return changelist;
    }

}
