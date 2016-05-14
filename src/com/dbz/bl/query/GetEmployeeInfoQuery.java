package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.Employee;
import com.dbz.bl.intermediates.Table;
import com.dbz.bl.intermediates.VirtualTable.EmployeeInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/6/2016.
 */
public class GetEmployeeInfoQuery extends Query {

    @Override
    public String getQuery() {
        return "SELECT " +
                "Person.ID as pId, " +
                "Person.FirstName as fname, " +
                "Person.MiddleInitial as mi," +
                "Person.LastName as lName, " +
                "Employee.ID as eId, " +
                "Employee.Salary, " +
                "Address.ID as aId, " +
                "Address.Street1 as s1," +
                "Address.Street2 as s2, " +
                "Address.City, " +
                "Address.State," +
                "Address.ZipCode,  " +
                "JobType.Name as Job " +
                "FROM Employee LEFT JOIN " +
                "Person ON Person.ID = Employee.PersonID " +
                "LEFT JOIN Address ON Address.ID = Person.AddressID " +
                "LEFT JOIN JobType ON JobType.ID = Employee.Job";
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException {
        return new EmployeeInfo(rs.getString("ZipCode"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("mi"),
                rs.getString("s1"),
                rs.getString("s2"),
                rs.getString("State"),
                rs.getBigDecimal("Salary"),
                rs.getString("Job"),
                rs.getInt("pId"),
                rs.getInt("eId"),
                rs.getInt("aId")
        );
    }
}
