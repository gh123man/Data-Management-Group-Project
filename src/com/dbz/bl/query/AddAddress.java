package com.dbz.bl.query;

import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/13/2016.
 */
public class AddAddress extends Query
{
    public AddAddress(String street1, String street2, String city, String state, String zip)
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
