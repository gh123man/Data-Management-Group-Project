package com.dbz.gui;

import com.dbz.bl.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeePanel extends JPanel
{
    private static JButton getEmployeeInfo = new JButton("Get Employee Contact Info");
    private static JButton removeEmployee = new JButton("Remove Employee");
    private static JButton addEmployee = new JButton("Add Employee");
    private final DataManager adm;

    public EmployeePanel(DataManager adm)
    {
        getEmployeeInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query getEmployeeInfo.");
            }
        });

        removeEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query removeEmployee.");
            }
        });

        addEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query addEmployee.");
            }
        });

        this.adm = adm;
        setLayout(new FlowLayout());
        add(getEmployeeInfo);
        add(removeEmployee);
        add(addEmployee);
    }
}
