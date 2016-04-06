package com.dbz.bl.query;

import java.util.Map;

/**
 * Created by brian on 4/5/16.
 */
public class Query {
    private final String mQuery;
    private Map<String, Object> mParams;

    public Query (String query) {
        this(query, null);
    }

    public Query (String query, Map<String, Object> params) {
        mQuery = query;
        mParams = params;
    }

    public String getQuery() {
        return mQuery;
    }

    public Map<String, Object> getParams() {
        return mParams;
    }

    public void setParams(Map<String, Object> params) {
        mParams = params;
    }
}
