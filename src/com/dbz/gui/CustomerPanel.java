package com.dbz.gui;

import com.dbz.bl.DataManager;
import com.dbz.bl.intermediates.Membership;
import com.dbz.bl.intermediates.Table;
import com.dbz.bl.query.Query;
import com.dbz.bl.query.RawQuery;

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
    // GUI components
    private static JButton getMailingList = new JButton("Get Mailing List");
    private static JScrollPane dataview = new JScrollPane();
    private static JTable mailinglist;

    // DB components
    private final DataManager adm;

    public CustomerPanel(DataManager adm)
    {
        getMailingList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                /// We'll make new table models each click.
                DefaultTableModel tm = new DefaultTableModel();

                for (String column : Membership.columnNames)
                    tm.addColumn(column);

                adm.exec(new RawQuery("Select * From Membership"), new ExecEventHandler() {
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
        dataview = new JScrollPane(mailinglist);

        view.add(dataview);
        add(view, BorderLayout.SOUTH);
    }
}
