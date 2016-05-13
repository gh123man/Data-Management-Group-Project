package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.JobType;
import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/13/2016.
 */
public class JobQuery extends Query
{
    @Override
    public String getQuery()
    {
        return "Select * From JobType";
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException
    {
        return new JobType(rs.getInt(JobType.ID), rs.getString(JobType.NAME));
    }
}
