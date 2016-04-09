package com.dbz.gui;

import com.dbz.bl.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZooManagementPanel extends JPanel
{
    private static JButton getExhibitCapacities = new JButton("Get Exhibit Capacities");
    private static JButton getExhibitAvailability = new JButton("Get Exhibit Availability");
    private static JButton getExpenseBreakdown = new JButton("Get Expense Breakdown");
    private final DataManager adm;

    public ZooManagementPanel(DataManager adm)
    {
        getExhibitCapacities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query getExhibitCapacities.");
            }
        });

        getExhibitAvailability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query getExhibitAvailability.");
            }
        });

        getExpenseBreakdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query getExpenseBreakdown.");
            }
        });

        this.adm = adm;
        setLayout(new FlowLayout());
        add(getExhibitCapacities);
        add(getExhibitAvailability);
        add(getExpenseBreakdown);
    }
}
