package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.Animal;
import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/6/2016.
 */
public class GetAnimalsQuery extends Query
{
    @Override
    public String getQuery()
    {
        return "SELECT * FROM " + Animal.class.getSimpleName() + ";";
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException
    {
        return new Animal(  rs.getInt(Animal.ID),
                            rs.getString(Animal.NAME),
                            rs.getInt(Animal.ANIMAL_CLASS),
                            rs.getInt(Animal.EXHIBIT_ID),
                            rs.getString(Animal.GENDER),
                            rs.getInt(Animal.AGE));
    }
}
