package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.Animal;
import com.dbz.bl.intermediates.RealTable.AnimalClass;
import com.dbz.bl.intermediates.RealTable.Exhibit;
import com.dbz.bl.intermediates.Table;
import com.dbz.bl.intermediates.VirtualTable.AnimalRecord;

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
        StringBuilder query = new StringBuilder();
        query.append("SELECT a.").append(Animal.ID).append(", a.").append(Animal.NAME)
                                 .append(", ac.").append(AnimalClass.NAME)
                                 .append(", ").append(Animal.EXHIBIT_ID).append(", ")
                                 .append(Animal.GENDER).append(", ").append(Animal.AGE)
             .append(" FROM ").append(Animal.class.getSimpleName()).append(" a inner join ")
                              .append(AnimalClass.class.getSimpleName()).append(" ac on a.")
                              .append(Animal.ANIMAL_CLASS).append("=ac.id");
        return query.toString();
//        return "SELECT * FROM " + Animal.class.getSimpleName() + ";";
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException
    {
        return new AnimalRecord( rs.getInt(1),
                                 rs.getString(2),
                                 rs.getString(3),
                                 rs.getInt(4),
                                 rs.getString(5),
                                 rs.getInt(6) );
    }
}
