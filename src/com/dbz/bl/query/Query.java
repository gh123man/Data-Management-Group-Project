package com.dbz.bl.query;

import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by brian on 4/5/16.
 */
public abstract class Query {
    public abstract String getQuery();
    public abstract Table mapResult(ResultSet rs) throws SQLException;

    protected static String genericSelect(String what, String table, String where, String is) {
        return "SELECT " + what + " FROM " + table + " WHERE " + where + " = " + is;
    }
}
