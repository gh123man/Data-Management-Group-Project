package com.dbz.bl.query;

import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by brian on 4/9/16.
 */
public class RawQuery extends Query {

    private String mQueryString;

    public Table mapResult(ResultSet rs) throws SQLException
    {
        return null;
    }

    public RawQuery (String rawQueryString) {
        if (rawQueryString == null) throw new IllegalArgumentException("Query string must not be null");
        mQueryString = rawQueryString;
    }

    @Override
    public String getQuery() {
        return mQueryString;
    }
}
