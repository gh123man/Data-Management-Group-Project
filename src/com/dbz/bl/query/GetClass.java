package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.AnimalClass;
import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by brian on 5/13/16.
 */
public class GetClass extends Query {
    @Override
    public String getQuery() {
        return "SELECT * FROM AnimalClass";
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException {
        return new AnimalClass(rs.getInt("ID"), rs.getString("Name"));
    }
}
