package com.dbz.bl.query;

import com.dbz.bl.intermediates.Table;
import com.dbz.bl.intermediates.VirtualTable.Expense;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by brian on 5/4/16.
 */
public class ExpenseBreakDownQuery extends Query {

    @Override
    public String getQuery() {
        return "SELECT (Select 'Salary: ' || Person.FirstName || ' ' || Person.MiddleInitial || '. ' || Person.LastName From Person where ID = Employee.PersonId) as Name, Salary as amnt FROM Employee " +
                "UNION " +
                "SELECT 'Animal: ' || Food.Name as Name, UnitCost as amnt FROM Food";
    }

    @Override
    public Table mapResult(ResultSet rs) throws SQLException {
        return new Expense(rs.getString("Name"), rs.getFloat("amnt"));
    }
}
