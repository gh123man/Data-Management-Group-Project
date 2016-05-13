package com.dbz.bl.query;

import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/12/2016.
 */
public class MoveAnimal extends Query
{
    private int id, newExhibit;

    public MoveAnimal(int id, int newExhibit)
    {
        this.id = id;
        this.newExhibit = newExhibit;
    }

    @Override
    public String getQuery()
    {
        return "UPDATE Animal SET ExhibitId = " + this.newExhibit + " WHERE ID = " + this.id + ";";
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException
    {
        return null;
    }
}
