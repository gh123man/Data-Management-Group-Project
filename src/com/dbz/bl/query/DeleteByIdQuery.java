package com.dbz.bl.query;

import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/6/2016.
 */
public class DeleteByIdQuery extends Query
{
    private String table;
    private int id;

    public DeleteByIdQuery(String table, int id)
    {
        this.table = table;
        this.id = id;
    }

    @Override
    public String getQuery()
    {
        return "DELETE FROM " + table + " WHERE ID = " + id;
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException
    {
        return null;
    }
}
