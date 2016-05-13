package com.dbz.bl.query;

import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/13/2016.
 */
public class AddPerson extends Query
{
    private String fname, mi, lname;
    private Integer addressId = null;

    public AddPerson(String fname, String mi, String lname)
    {
        setClassFields(fname, mi, lname);
    }

    public AddPerson(String fname, String mi, String lname, int addressId)
    {
        this.addressId = addressId;
        setClassFields(fname, mi, lname);;
    }

    private void setClassFields(String fname, String mi, String lname)
    {
        this.fname = fname;
        this.mi = mi;
        this.lname = lname;
    }

    @Override
    public String getQuery()
    {
        String query = "";
        query += "INSERT INTO Person (FirstName,MiddleInitial,LastName,AddressId) Values (";
        query += fname + ",";
        query += mi + ",";
        query += lname + ",";
        query += addressId + ");";
        return query;
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException
    {
        return null;
    }
}
