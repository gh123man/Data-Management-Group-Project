package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.Membership;
import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 4/10/2016.
 */
public class GetMailingListQuery extends Query {

    @Override
    public String getQuery() {
        return "SELECT " + Membership.PERSON_ID + ", " + Membership.EXPIRATION_DATE + " FROM " + Membership.class.getSimpleName();
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException {
        return new Membership(rs.getInt(Membership.PERSON_ID), rs.getDate(Membership.EXPIRATION_DATE));
    }
}
