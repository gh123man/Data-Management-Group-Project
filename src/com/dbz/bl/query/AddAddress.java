package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.Address;
import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/13/2016.
 */
public class AddAddress extends Query
{
    private String street1, street2, city, state, zip;

    public AddAddress(String street1, String street2, String city, String state, String zip)
    {
        this.street1 = street1;
        this.street2 = street2.length() > 0 ? street2 : "";
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    @Override
    public String getQuery()
    {
        String query = "Insert Into Address (Street1,Street2,City,State,ZipCode) Values (";
        query += "\"" + street1 + "\",\"" + street2 + "\",\"" + city + "\",\"" + state + "\",\"" + zip + "\");";
        return query;
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException
    {
        return new Address( rs.getInt(Address.ID),
                            rs.getString(Address.STREET_1),
                            rs.getString(Address.STREET_2),
                            rs.getString(Address.CITY),
                            rs.getString(Address.STATE),
                            rs.getString(Address.ZIP_CODE));
    }
}
