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
        return "SELECT Location.Name as Name, " +
                "(SELECT COUNT(*) WHERE Exhibit.Location=Location.ID) as NumExhibits, " +
                "(SELECT SUM(Exhibit.AnimalCapacity) WHERE Exhibit.Location=Location.ID) as AnimalCapacity, " +
                "(SELECT count(*) FROM Animal where Exhibit.ID = Animal.ExhibitID) as AnimalCount " +
                "FROM Exhibit " +
                "LEFT JOIN Location " +
                "ON Exhibit.Location = Location.ID " +
                "GROUP BY Name, NumExhibits, AnimalCapacity, AnimalCount";
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException {
        return new ExhibitOverview(rs.getString("Name"), rs.getInt("NumExhibits"), rs.getInt("AnimalCapacity"), rs.getInt("AnimalCount"));
    }
}
