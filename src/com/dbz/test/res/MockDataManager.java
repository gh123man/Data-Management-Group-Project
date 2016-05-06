package com.dbz.test.res;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.RealTable.Employee;
import com.dbz.bl.intermediates.RealTable.Membership;
import com.dbz.bl.intermediates.RealTable.UpdatableTable;
import com.dbz.bl.intermediates.Table;
import com.dbz.bl.intermediates.VirtualTable.ExhibitOverview;
import com.dbz.bl.intermediates.VirtualTable.Expense;
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
        if (query instanceof ExhibitOverviewQuery) {
            handler.onExec(query, new ArrayList<Table>() {{
                add(new ExhibitOverview("Exhibit1", 100, 20));
                add(new ExhibitOverview("Exhibit2", 1001, 201));
                add(new ExhibitOverview("Exhibit3", 1002, 202));
                add(new ExhibitOverview("Exhibit4", 1003, 203));
            }});

        } else if (query instanceof ExpenseBreakDownQuery) {
            handler.onExec(query, new ArrayList<Table>() {{
                add(new Expense("expense1", 20f));
                add(new Expense("expense2", 30f));
                add(new Expense("expense3", 40f));
            }});

        } else if (query instanceof GetMailingListQuery) {
            handler.onExec(query, new ArrayList<Table>() {{
                add(new Membership(1, new Date(123432)));
                add(new Membership(2, new Date(123432)));
            }});
        } else if (query instanceof GetEmployeeInfoQuery) {
            handler.onExec(query, new ArrayList<Table>() {{
                add(new Employee(1, 30000, 1));
                add(new Employee(2, 50000, 6));
            }});
        }
    }
}
