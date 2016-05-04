package com.dbz.gui;

import com.dbz.bl.DataManager;
import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.Exhibit;
import com.dbz.bl.intermediates.Table;
import com.dbz.bl.query.Query;
import com.dbz.bl.query.RawQuery;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ZooManagementPanel extends JPanel
{
    public static final String TITLE = "Zoo Management";

    private static JButton getCapacitiesAvailability = new JButton("Show Exhibit Capacities/Availability");
    private static JButton getExpenseBreakdown = new JButton("Get Expense Breakdown");
    private static JScrollPane dataviewpane;
    private static JTable mgmtview;

    private final IDataManager adm;

    public ZooManagementPanel(IDataManager adm)
    {
        getCapacitiesAvailability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DefaultTableModel tm = new DefaultTableModel();
                tm.addColumn("Name");
                tm.addColumn("Capacity");
                tm.addColumn("Availability");

//                TODO: Query here needs to get all exhibits capacities, names, and the number of animals assigned
//                to each exhibit ID. Query needs to do availability_exhibit - animal_count_assigned_exhibit for
//                availability.
                adm.exec(new RawQuery("Select * From Exhibit"), new IDataManager.ExecEventHandler() {
                    @Override
                    public void onExec(Query query, List<Table> results) {
                        List<Exhibit> exhibits = (List<Exhibit>) (List) results;
                        for (Exhibit exh : exhibits)
                        {
                            Object[] obj = {exh.getLocation(), exh.getmAnimalCapacity(), exh.getmAnimalCapacity()};
                            tm.addRow(obj);
                        }
                    }
                }, new IDataManager.InvalidExecHandler() {
                    @Override
                    public void onError(Query query, Exception e) {
                        System.err.println("Error requesting exhibits");
                    }
                });
                mgmtview.setModel(tm);
            }
        });

        getExpenseBreakdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DefaultTableModel tm = new DefaultTableModel();
                tm.addColumn("Cost class");
                tm.addColumn("$$$");

                adm.exec(new RawQuery(""), new IDataManager.ExecEventHandler() {
                    @Override
                    public void onExec(Query query, List<Table> results) {
                        /*
                        List<ExpenseBreakdown> expenses = (List<ExpenseBreakdown>)(List) results;
                        for (ExpenseBreakdown expense : expenses)
                        {
                            Object[] obj =
                            {
                                expense.getClass(), // Employee-salary/Food/Etc
                                expense.getDollarAmount(),
                            };
                            tm.addRow(obj);
                        }
                        * */
                    }
                }, new IDataManager.InvalidExecHandler() {
                    @Override
                    public void onError(Query query, Exception e) {

                    }
                });
                mgmtview.setModel(tm);;
            }
        });

        this.adm = adm;
        setLayout(new BorderLayout());

        JPanel buttons = new JPanel();
        buttons.add(getCapacitiesAvailability);
        buttons.add(getExpenseBreakdown);
        add(buttons, BorderLayout.NORTH);

        DefaultTableModel tm = new DefaultTableModel();
        tm.addColumn("Welcome to DBZ");
        mgmtview = new JTable(tm);
        dataviewpane = new JScrollPane(mgmtview);

        add(dataviewpane, BorderLayout.SOUTH);
    }
}
