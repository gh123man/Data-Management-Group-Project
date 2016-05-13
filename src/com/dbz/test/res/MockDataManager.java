package com.dbz.test.res;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.RealTable.Animal;
import com.dbz.bl.intermediates.RealTable.Employee;
import com.dbz.bl.intermediates.RealTable.Membership;
import com.dbz.bl.intermediates.RealTable.UpdatableTable;
import com.dbz.bl.intermediates.Table;
import com.dbz.bl.intermediates.VirtualTable.ExhibitOverview;
import com.dbz.bl.intermediates.VirtualTable.Expense;
import com.dbz.bl.intermediates.VirtualTable.MembershipRecord;
import com.dbz.bl.query.*;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by brian on 5/4/16.
 */
public class MockDataManager implements IDataManager {
    @Override
    public void commit(UpdatableTable table, CommitEventHandler handler, InvalidCommitHandler errorHandler) {

    }

    @Override
    public void exec(Query query, ExecEventHandler handler) {
        System.out.println("MockDataManager: " + query.getQuery());
        if (query instanceof ExhibitOverviewQuery) {
            handler.onExec(query, new ArrayList<Table>() {{
                add(new ExhibitOverview("Exhibit1", 100, 25, 20));
                add(new ExhibitOverview("Exhibit2", 1001, 52, 201));
                add(new ExhibitOverview("Exhibit3", 1002, 25, 202));
                add(new ExhibitOverview("Exhibit4", 1003, 52, 203));
            }});

        } else if (query instanceof ExpenseBreakDownQuery) {
            handler.onExec(query, new ArrayList<Table>() {{
                add(new Expense("expense1", 20f));
                add(new Expense("expense2", 30f));
                add(new Expense("expense3", 40f));
            }});

        } else if (query instanceof GetMailingListQuery) {
            handler.onExec(query, new ArrayList<Table>() {{
                add(new MembershipRecord("Big Tom", "123 Fake St", "Apt 2", "Rochester", "NY", "14620", new Date(123431)));
                add(new MembershipRecord("David Shapiro", "87 RIT Boulevard", "", "Rochester", "NY", "14546",new Date(123432)));
            }});
        } else if (query instanceof GetEmployeeInfoQuery) {
            handler.onExec(query, new ArrayList<Table>() {{
                add(new Employee(1, 30000, 1));
                add(new Employee(2, 50000, 6));
            }});
        } else if (query instanceof GetAnimalsQuery) {
            handler.onExec(query, new ArrayList<Table>() {{
                add(new Animal(0, "Human", 33, 0, "MALE", 5));
                add(new Animal(1, "Crocodile", 2, 0, "MALE", 34));
            }});
        }
    }
}
