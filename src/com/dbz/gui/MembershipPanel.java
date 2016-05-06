package com.dbz.gui;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.VirtualTable.MembershipRecord;
import com.dbz.bl.query.GetMailingListQuery;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class MembershipPanel extends JPanel
{
    public static final String TITLE = "Memberships";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    // GUI components
    private static JButton getMailingList = new JButton("Get Mailing List");
    private static JScrollPane dataviewpane;
    private static JTable mailinglist;

    // DB components
    private final IDataManager adm;

    public MembershipPanel(IDataManager adm) {
        final DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Member Name");
        tableModel.addColumn("Street Address 1");
        tableModel.addColumn("Street Address 2");
        tableModel.addColumn("City");
        tableModel.addColumn("State");
        tableModel.addColumn("ZIP Code");
        tableModel.addColumn("Expiration Date");

        getMailingList.addActionListener(e -> {
            adm.exec(new GetMailingListQuery(), (query, results) -> {
                tableModel.setRowCount(0);

                List<MembershipRecord> membs = (List<MembershipRecord>)(List) results;
                for (MembershipRecord memb : membs) {
                    Object[] obj = {memb.getFullName(), memb.getStreetAddress1(), memb.getStreetAddress2(),
                                    memb.getCityName(), memb.getStateAbbrev(), memb.getZIPCode(),
                                    dateFormat.format(memb.getMemberExpireDate())};
                    tableModel.addRow(obj);
                }
                mailinglist.setModel(tableModel);
            });

        });
        this.adm = adm;

        setLayout(new BorderLayout());
        JPanel buttons = new JPanel();
        buttons.add(getMailingList);
        add(buttons, BorderLayout.NORTH);

        JPanel view = new JPanel(new FlowLayout());

        mailinglist = new JTable(tableModel);
        mailinglist.setAutoCreateRowSorter(true);
        dataviewpane = new JScrollPane(mailinglist);

        view.add(dataviewpane);
        add(view, BorderLayout.SOUTH);
    }
}
