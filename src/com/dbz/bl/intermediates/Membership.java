package com.dbz.bl.intermediates;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Membership extends Table {

    public static final String PERSON_ID       = "PersonID";
    public static final String EXPERATION_DATE = "ExpirationDate";

    private Integer mId;
    private Date mExperationDate;
    private Integer mPersonId;

    private boolean mCPersonId, mCExperationDate;

    public Membership(Integer personId, Date date) {
        mPersonId = personId;
        mExperationDate = date;
        mCExperationDate = mCPersonId = false;
    }

    protected Membership(Integer id, Integer personId, Date date) {
        mId = id;
        mPersonId = personId;
        mExperationDate = date;
        mCExperationDate = mCPersonId = false;
    }

    public Integer getID() {
        return mId;
    }
    public Integer getPersonId() {
        return mPersonId;
    }
    public Date getExperationDate() {
        return mExperationDate;
    }


    public void setPersonId(Integer v) {
        mPersonId = v;
        mCPersonId = true;
    }
    public void setDate(Date v) {
        mExperationDate = v;
        mCExperationDate = true;
    }

    @Override
    public String getTableName() {
        return Membership.class.getName();
    }

    @Override
    protected Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCPersonId) changelist.put(PERSON_ID, mPersonId);
        if (mCExperationDate) changelist.put(EXPERATION_DATE, mExperationDate);
        return changelist;
    }

}
