package com.dbz.gui;

import com.dbz.bl.IDataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class EmployeePanel extends JPanel
{
    public static final String TITLE = "Employees";

    private static JButton getEmployeeInfo = new JButton("Get Employee Contact Info");
    private static JButton removeEmployee = new JButton("Remove Employee");
    private static JButton addEmployee = new JButton("Add Employee");
    private static JFormattedTextField personIdSelect;
    private static JFormattedTextField selarySelect;
    private static JComboBox jobSelect;
    private static JScrollPane employeeviewpane;
    private static JTable employeeview;

    private final IDataManager adm;

    public EmployeePanel(IDataManager adm)
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
        setLayout(new BorderLayout());

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(addEmployee);
//        add input fields for PersonID, salaray, Job.

        personIdSelect = new JFormattedTextField(NumberFormat.getIntegerInstance());
        buttons.add(personIdSelect);
        buttons.add(getEmployeeInfo);
        buttons.add(removeEmployee);

        DefaultTableModel tm = new DefaultTableModel();
        tm.addColumn("Employee Table");

        employeeview = new JTable(tm);
        employeeview.setAutoCreateRowSorter(true);
        employeeviewpane = new JScrollPane(employeeview);

        add(buttons, BorderLayout.NORTH);
        add(employeeviewpane, BorderLayout.SOUTH);
    }
}
