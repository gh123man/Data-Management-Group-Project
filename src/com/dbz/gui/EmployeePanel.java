package com.dbz.gui;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.RealTable.Employee;
import com.dbz.bl.query.RawQuery;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeePanel extends JPanel
{
    public static final String TITLE = "Employees";

    private static JButton getEmployeeInfo = new JButton("Get Employee Contact Info");
    private static JButton removeEmployee = new JButton("Remove Employee");
    private static JButton addEmployee = new JButton("Add Employee");
    private static JTextField newPersonId = new JTextField(5);
    private static JTextField newSalaryAmount = new JTextField(7);
    private static JTextField newJobTitle = new JTextField(10);
    private static JTable employeeview;

    private final IDataManager adm;
    private final int ID_COL_IDX = 0;

    private class EmployeeTableModel extends DefaultTableModel
    {
        public boolean isCellEditable(int row, int col)
        {
            return ID_COL_IDX != col;
        }
    }

    private EmployeeTableModel getPopulatedTableModel()
    {
        EmployeeTableModel tm = new EmployeeTableModel();
        for (String col : Employee.columnNames)
            tm.addColumn(col);

        Object[] o1 = {1, 30000, "Handler"};
        tm.addRow(o1);
        Object[] o2 = {2, 123123, "ceo"};
        tm.addRow(o2);

        System.out.println("TODO: add query getEmployeeInfo.");
        adm.exec(new RawQuery(""), (query, results) -> {
            java.util.List<Employee> employees = (java.util.List<Employee>)(java.util.List) results;
            for (Employee employee : employees) {
                Object[] obj = {employee.getPersonId(), employee.getSalary(), employee.getJob()};
                tm.addRow(obj);
            }
        });

        return tm;
    }

    public EmployeePanel(IDataManager adm)
    {
        getEmployeeInfo.addActionListener(e -> employeeview.setModel(getPopulatedTableModel()));

        removeEmployee.addActionListener(e -> {
            for (int row : employeeview.getSelectedRows())
            {
                System.out.println("TODO: add query removeEmployee.");
                System.out.println("Deleting Employee " + row + ", ID: " + employeeview.getValueAt(row, ID_COL_IDX));
                adm.exec(new RawQuery(""), ((query, results) -> {
                }));
            }

            employeeview.setModel(getPopulatedTableModel());
        });

        addEmployee.addActionListener(e -> {
            String[] newEmployee = {newPersonId.getText(), newSalaryAmount.getText(), newJobTitle.getText()};

            if (newEmployee[0].length() != 0 && newEmployee[1].length() != 0 && newEmployee[2].length() != 0)
            {
                System.out.print("new employee: ");
                for (String s : newEmployee)
                    System.out.print(s + ", ");
                System.out.println();

                adm.exec(new RawQuery(""), ((query, results) -> {
                    System.out.println("TODO: add query addEmployee.");
                }));
            }

            employeeview.setModel(getPopulatedTableModel());
        });

        this.adm = adm;
        setLayout(new BorderLayout());

        JPanel queries = new JPanel(new FlowLayout());
        JPanel crud = new JPanel((new FlowLayout()));
        JPanel addPanel = new JPanel(new FlowLayout());
        addPanel.add(new JLabel("ID"));
        addPanel.add(newPersonId);
        addPanel.add(new JLabel("Salary"));
        addPanel.add(newSalaryAmount);
        addPanel.add(new JLabel("Job"));
        addPanel.add(newJobTitle);
        addPanel.add(addEmployee);
        addPanel.setBorder(BorderFactory.createEtchedBorder());
        crud.add(addPanel);
        crud.add(removeEmployee);

        queries.add(getEmployeeInfo);

        EmployeeTableModel tm = getPopulatedTableModel();
        employeeview = new JTable(tm);
        employeeview.setAutoCreateRowSorter(true);
        JScrollPane employeeviewpane = new JScrollPane(employeeview);

        add(queries, BorderLayout.NORTH);
        add(crud, BorderLayout.SOUTH);
        add(employeeviewpane, BorderLayout.CENTER);
    }
}
