package com.dbz.bl.intermediates;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Membership implements UpdatableTable {

    public static final String PERSON_ID       = "PersonID";
    public static final String EXPIRATION_DATE = "ExpirationDate";
    public static final String[] columnNames = new String[] { PERSON_ID, EXPIRATION_DATE };

    private Integer mId;
    private Date mExpirationDate;
    private Integer mPersonId;

    private boolean mCPersonId, mCExperationDate;

    public Membership(Integer personId, Date date) {
        mPersonId = personId;
        mExpirationDate = date;
        mCExperationDate = mCPersonId = true;
    }

    protected Membership(Integer id, Integer personId, Date date) {
        mId = id;
        mPersonId = personId;
        mExpirationDate = date;
        mCExperationDate = mCPersonId = false;
    }

    public Integer getID() {
        return mId;
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
        mCExperationDate = true;
    }

    @Override
    public String getTableName() {
        return Membership.class.getName();
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCPersonId) changelist.put(PERSON_ID, mPersonId);
        if (mCExperationDate) changelist.put(EXPIRATION_DATE, mExpirationDate);
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
