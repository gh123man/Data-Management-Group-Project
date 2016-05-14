package com.dbz.bl.query;

import com.dbz.bl.intermediates.RealTable.Employee;
import com.dbz.bl.intermediates.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Patrick on 5/13/2016.
 */
public class AddEmployee extends Query
{
    Integer personId, salary, jobId;

    public AddEmployee(int personId, int salary, int jobId)
    {
        this.personId = personId;
        this.salary = salary;
        this.jobId = jobId;
    }

    @Override
    public String getQuery()
    {
        String query = "Insert Into Employee (PersonID, Salary, Job) Values (";
        query += personId + "," + salary + "," + jobId + ");";
        return query;
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException
    {
        return new Employee(
                rs.getInt(Employee.ID),
                rs.getInt(Employee.PERSON_ID),
                rs.getInt(Employee.SALARY),
                rs.getInt(Employee.JOB));
    }
}
