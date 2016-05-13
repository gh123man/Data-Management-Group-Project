package com.dbz.gui;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.RealTable.Employee;
import com.dbz.bl.query.GetEmployeeInfoQuery;
import com.dbz.bl.query.RawQuery;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeePanel extends JPanel
{
    public static final String TITLE = "Employees";

    private static JButton getEmployeeInfo = new JButton("Get Employee Contact Info");
    private static JButton removeEmployee = new JButton("Remove Selected Employee(s)");
    private static JButton addEmployee = new JButton("Add Employee");
    private static JTextField newPersonId = new JTextField(5);
    private static JTextField newSalaryAmount = new JTextField(7);
    private static JTextField newJobTitle = new JTextField(10);
    private static LeftAlignedJTable employeeview;

    private final IDataManager adm;
    private final int ID_COL_IDX = 0;

    private class EmployeeTableModel extends BetterSortingTableModel
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

        adm.exec(new GetEmployeeInfoQuery(), (query, results) -> {
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
//        TODO: This button can probably be removed since its queried implicitly -- but it is there as a manual option.
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

//                Clear on success.
                newPersonId.setText("");
                newSalaryAmount.setText("");
                newJobTitle.setText("");
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

        employeeview = new LeftAlignedJTable(getPopulatedTableModel());
        employeeview.setAutoCreateRowSorter(true);
        employeeview.getTableHeader().setReorderingAllowed(false);
        JScrollPane employeeviewpane = new JScrollPane(employeeview);

        add(queries, BorderLayout.NORTH);
        add(crud, BorderLayout.SOUTH);
        add(employeeviewpane, BorderLayout.CENTER);
    }
}
