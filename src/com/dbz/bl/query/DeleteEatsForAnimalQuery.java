package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.Eats;
import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by angel on 5/13/16.
 */
public class DeleteEatsForAnimalQuery extends Query {
    private final Integer mAnimalID;

    public DeleteEatsForAnimalQuery(Integer animalID) {
        mAnimalID = animalID;
    }

    @Override
    public String getQuery() {
        return String.format("DELETE FROM %s WHERE %s=%d", Eats.class.getSimpleName(), Eats.ANIMAL_ID, mAnimalID);
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException {
        return null;
    }
}
