package com.dbz.gui;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.RealTable.Membership;
import com.dbz.bl.query.GetMailingListQuery;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerPanel extends JPanel
{
    public static final String TITLE = "Customers";

    // GUI components
    private static JButton getMailingList = new JButton("Get Mailing List");
    private static JScrollPane dataviewpane;
    private static JTable mailinglist;

    // DB components
    private final IDataManager adm;

    public CustomerPanel(IDataManager adm)
    {
        getMailingList.addActionListener(e -> {
            /// We'll make new table models each click.
            DefaultTableModel tm = new DefaultTableModel();

            for (String column : Membership.columnNames)
                tm.addColumn(column);

            adm.exec(new GetMailingListQuery(), (query, results) -> {
                List<Membership> membs = (List<Membership>)(List) results;
                for (Membership memb : membs) {
                    Object[] obj = {memb.getPersonId(), memb.getExperationDate()};
                    tm.addRow(obj);
                }
                mailinglist.setModel(tm);
            });

        });
        this.adm = adm;

        setLayout(new BorderLayout());
        JPanel buttons = new JPanel();
        buttons.add(getMailingList);
        add(buttons, BorderLayout.NORTH);

        JPanel view = new JPanel(new FlowLayout());
        DefaultTableModel tm = new DefaultTableModel();

        /// On this page we know the column names, so well display them before a query ever goes off.
        for (String s : Membership.columnNames)
            tm.addColumn(s);

        mailinglist = new JTable(tm);
        mailinglist.setAutoCreateRowSorter(true);
        dataviewpane = new JScrollPane(mailinglist);

        view.add(dataviewpane);
        add(view, BorderLayout.SOUTH);
    }
}
