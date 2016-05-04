package com.dbz.bl.query;

import com.dbz.bl.intermediates.Table;
import com.dbz.bl.intermediates.VirtualTable.ExhibitOverview;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by brian on 5/4/16.
 */
public class ExhibitOverviewQuery extends Query {

    @Override
    public String getQuery() {
        return "SELECT Location.Name as Name, Exhibit.AnimalCapacity as Cap, " +
                "(SELECT count(*) FROM Animal where Exhibit.ID = Animal.ExhibitID) as AnimalCount " +
                "FROM Exhibit " +
                "LEFT JOIN Location " +
                "ON Exhibit.Location = Location.ID " +
                "GROUP BY Name, Cap;";
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException
    {
        return new ExhibitOverview(rs.getString("Name"), rs.getInt("Cap"), rs.getInt("AnimalCount"));
    }
}
