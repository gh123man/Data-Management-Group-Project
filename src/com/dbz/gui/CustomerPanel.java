package com.dbz.gui;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.RealTable.Membership;
import com.dbz.bl.intermediates.Table;
import com.dbz.bl.query.GetMailingList;
import com.dbz.bl.query.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static com.dbz.bl.IDataManager.ExecEventHandler;
import static com.dbz.bl.IDataManager.InvalidExecHandler;

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
        getMailingList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                /// We'll make new table models each click.
                DefaultTableModel tm = new DefaultTableModel();

                for (String column : Membership.columnNames)
                    tm.addColumn(column);

                adm.exec(new GetMailingList(), new ExecEventHandler() {
                @Override
                public void onExec(Query query, List<Table> results) {
                    List<Membership> membs = (List<Membership>)(List)results ;

                    for (Membership memb : membs)
                    {
                        Object[] obj = {memb.getPersonId(), memb.getExperationDate()};
                        tm.addRow(obj);
                    }
                }
                }, new InvalidExecHandler() {
                @Override
                public void onError(Query query, Exception e) {
                    System.err.println("Error getting mailing list.");
                }
                });
                mailinglist.setModel(tm);
            }
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
        dataviewpane = new JScrollPane(mailinglist);

        view.add(dataviewpane);
        add(view, BorderLayout.SOUTH);
    }
}
