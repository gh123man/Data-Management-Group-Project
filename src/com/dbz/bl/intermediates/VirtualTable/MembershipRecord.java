package com.dbz.bl.intermediates.VirtualTable;

import com.dbz.bl.intermediates.Table;

import java.util.Date;

/**
 * Created by angel on 5/6/16.
 */
public class MembershipRecord implements Table {
    private final String mFullName;
    private final String mStreetAddress1;
    private final String mStreetAddress2;
    private final String mCityName;
    private final String mStateAbbrev;
    private final String mZipCode;
    private final Date mMemberExpireDate;

    public MembershipRecord(String fullName, String streetAddress1, String streetAddress2,
                            String cityName, String stateAbbrev, String zipCode, Date memberExpireDate ) {
        mFullName = fullName;
        mStreetAddress1 = streetAddress1;
        mStreetAddress2 = streetAddress2;
        mCityName = cityName;
        mStateAbbrev = stateAbbrev;
        mZipCode = zipCode;
        mMemberExpireDate = memberExpireDate;
    }

    public String getFullName()         { return mFullName; }
    public String getStreetAddress1()   { return mStreetAddress1; }
    public String getStreetAddress2()   { return mStreetAddress2; }
    public String getCityName()         { return mCityName; }
    public String getStateAbbrev()      { return mStateAbbrev; }
    public String getZIPCode()          { return mFullName; }
    public Date getMemberExpireDate()   { return mMemberExpireDate; }
}
