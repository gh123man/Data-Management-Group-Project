package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.Animal;
import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by brian on 4/9/16.
 */
public class GetAnimalsByName extends Query {

    private String mName;
    public GetAnimalsByName(String name) {
        mName = name;
    }
    @Override
    public String getQuery() {
        return genericSelect("*", Animal.class.getName(), Animal.NAME, mName);
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException {
        return new Animal(rs.getInt(Animal.ID), rs.getString(Animal.NAME), rs.getInt(Animal.ANIMAL_CLASS), rs.getInt(Animal.EXHIBIT_ID), rs.getString(Animal.GENDER), rs.getInt(Animal.AGE));
    }
}
