package com.dbz.gui;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.VirtualTable.ExhibitOverview;
import com.dbz.bl.intermediates.VirtualTable.Expense;
import com.dbz.bl.query.ExhibitOverviewQuery;
import com.dbz.bl.query.ExpenseBreakDownQuery;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ZooManagementPanel extends JPanel
{
    public static final String TITLE = "Zoo Management";

    private static JButton getCapacitiesAvailability = new JButton("Show Exhibit Capacities/Availability");
    private static JButton getExpenseBreakdown = new JButton("Get Expense Breakdown");
    private static JButton getFoodQuantity = new JButton("Food Quantity");
    private static JScrollPane dataviewpane;
    private static JTable mgmtview;

    private final IDataManager adm;

    public ZooManagementPanel(IDataManager adm)
    {
        getCapacitiesAvailability.addActionListener(e -> {
            DefaultTableModel tm = new DefaultTableModel();
            tm.addColumn("Name");
            tm.addColumn("Capacity");
            tm.addColumn("Availability");

            adm.exec(new ExhibitOverviewQuery(), (query, results) -> {
                List<ExhibitOverview> exhibits = (List<ExhibitOverview>) (List) results;
                exhibits.forEach(ex -> {
                    System.out.println(ex.getName());
                    Object[] obj = {ex.getName(), ex.getCapacity(), ex.getAnimalCount()};
                    tm.addRow(obj);
                });
                mgmtview.setModel(tm);
            });

        });

        getExpenseBreakdown.addActionListener(e -> {
            DefaultTableModel tm = new DefaultTableModel();
            tm.addColumn("Cost class");
            tm.addColumn("$$$");

            adm.exec(new ExpenseBreakDownQuery(), (query, results) -> {
                List<Expense> expenses = (List<Expense>)(List) results;
                expenses.forEach(exp -> {
                    Object[] obj = { exp.getName(), exp.getAmount() };
                    tm.addRow(obj);
                });
                mgmtview.setModel(tm);
            });
        });

        getFoodQuantity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query getFoodQuantity.");
            }
        });

        this.adm = adm;
        setLayout(new BorderLayout());

        JPanel buttons = new JPanel();
        buttons.add(getCapacitiesAvailability);
        buttons.add(getExpenseBreakdown);
        buttons.add(getFoodQuantity);
        add(buttons, BorderLayout.NORTH);

        DefaultTableModel tm = new DefaultTableModel();
        tm.addColumn("Welcome to DBZ");
        mgmtview = new JTable(tm);
        mgmtview.setAutoCreateRowSorter(true);
        dataviewpane = new JScrollPane(mgmtview);

        add(dataviewpane, BorderLayout.SOUTH);
    }
}
