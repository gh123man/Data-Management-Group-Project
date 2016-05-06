package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.Employee;
import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/6/2016.
 */
public class GetEmployeeInfoQuery extends Query
{

    @Override
    public String getQuery()
    {
        return "SELECT * FROM " + Employee.class.getSimpleName();
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException
    {
        return new Employee(rs.getInt(Employee.PERSON_ID), rs.getInt(Employee.SALARY), rs.getInt(Employee.JOB));
    }
}
