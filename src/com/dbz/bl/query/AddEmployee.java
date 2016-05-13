package com.dbz.bl.query;

import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/13/2016.
 */
public class AddEmployee extends Query
{
    public AddEmployee(int personId, int salary, int jobId)
    {

    }

    @Override
    public String getQuery()
    {
        return null;
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException
    {
        return null;
    }
}
