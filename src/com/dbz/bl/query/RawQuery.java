package com.dbz.bl.query;

/**
 * Created by brian on 4/9/16.
 */
public class RawQuery extends Query {

    private String mQueryString;

    public RawQuery (String rawQueryString) {
        if (rawQueryString == null) throw new IllegalArgumentException("Query string must not be null");
        mQueryString = rawQueryString;
    }

    @Override
    public String getQuery() {
        return mQueryString;
    }
}
