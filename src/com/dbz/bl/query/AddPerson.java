package com.dbz.bl.query;

import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/13/2016.
 */
public class AddPerson extends Query
{
    public AddPerson(String fname, String mi, String lname)
    {

    }

    public AddPerson(String fname, String mi, String lname, int addressId)
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
