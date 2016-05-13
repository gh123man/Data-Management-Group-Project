package com.dbz.gui;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.RealTable.JobType;
import com.dbz.bl.intermediates.VirtualTable.EmployeeInfo;
import com.dbz.bl.query.GetEmployeeInfoQuery;
import com.dbz.bl.query.JobQuery;
import com.dbz.bl.query.RawQuery;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class EmployeePanel extends JPanel
{
    public static final String TITLE = "Employees";

    private static JButton getEmployeeInfo = new JButton("Get Employee Contact Info");
    private static JButton removeEmployee = new JButton("Remove Selected Employee(s)");
    private static JButton addEmployee = new JButton("Add Employee");
    private static JTextField newSalaryAmount = new JTextField(7);
    private static JComboBox newJobSelect;
    private static JTextField newFName = new JTextField(7);
    private static JTextField newMI = new JTextField(1);
    private static JTextField newLName = new JTextField(7);
    private static JTextField newAddr1 = new JTextField(10);
    private static JTextField newAddr2 = new JTextField(6);
    private static JTextField newState = new JTextField(2);
    private static JTextField newZip = new JTextField(5);


    private static LeftAlignedJTable employeeview;

    private final IDataManager adm;
    private final int ID_COL_IDX = 0;

    private Map<String, Integer> jobs = new HashMap<>();

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
        tm.addColumn("First Name");
        tm.addColumn("Middle Initial");
        tm.addColumn("Last Name");
        tm.addColumn("Job Title");
        tm.addColumn("Salary $");
        tm.addColumn("Street 1");
        tm.addColumn("Street 2");
        tm.addColumn("State");
        tm.addColumn("Zip Code");


        adm.exec(new GetEmployeeInfoQuery(), (query, results) -> {
            java.util.List<EmployeeInfo> employees = (java.util.List<EmployeeInfo>)(java.util.List) results;
            for (EmployeeInfo employee : employees) {
                Object[] obj = {
                        employee.getFname(),
                        employee.getMi(),
                        employee.getLname(),
                        employee.getJob(),
                        employee.getSalary(),
                        employee.getS1(),
                        employee.getS2(),
                        employee.getState(),
                        employee.getZip()
                };
                tm.addRow(obj);
            }
        });

        return tm;
    }

    private void populateKnownJobs()
    {
        adm.exec(new JobQuery(), (query, results) -> {
            for (JobType job : (java.util.List<JobType>)(java.util.List)results)
            {
                jobs.put(job.getName(), job.getId());
            }
        });
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
//            Onbject[] newEmployee = {newSalaryAmount.getText(), jobs.get((String)newJobSelect.getSelectedItem())};

            String newSalary = newSalaryAmount.getText();
            Integer jobType = jobs.get(newJobSelect.getSelectedItem());
            if (newSalary.length() != 0 && jobType != null)
            {
                System.out.println("new employee: jobid = " + jobType + " salary: " + newSalary);

                adm.exec(new RawQuery(""), ((query, results) -> {
                    System.out.println("TODO: add query addEmployee.");
                }));

//                Clear on success.
//                newPersonId.setText("");
                newSalaryAmount.setText("");
//                newJobTitle.setText("");
            }
//            TODO: add back in when the full employee query exists
            employeeview.setModel(getPopulatedTableModel());
        });

        this.adm = adm;
        setLayout(new BorderLayout());

        JPanel queries = new JPanel(new FlowLayout());
        JPanel bottomCont = new JPanel((new BorderLayout()));
        JPanel crud = new JPanel((new BorderLayout()));
        JPanel addPanel = new JPanel(new FlowLayout());
        JPanel addPanel1 = new JPanel(new FlowLayout());
//        addPanel.add(new JLabel("ID"));
//        addPanel.add(newPersonId);
        addPanel.add(new JLabel("First"));
        addPanel.add(newFName);
        addPanel.add(new JLabel("M.I."));
        addPanel.add(newMI);
        addPanel.add(new JLabel("Last"));
        addPanel.add(newLName);
        addPanel.add(new JLabel("Salary"));
        addPanel.add(newSalaryAmount);

        addPanel1.add(new JLabel("Street1"));
        addPanel1.add(newAddr1);
        addPanel1.add(new JLabel("Street2"));
        addPanel1.add(newAddr2);
        addPanel1.add(new JLabel("State"));
        addPanel1.add(newState);
        addPanel1.add(new JLabel("Zip"));
        addPanel1.add(newZip);
        addPanel1.add(new JLabel("Job"));
        populateKnownJobs();
        newJobSelect = new JComboBox(jobs.keySet().toArray());
        addPanel.add(newJobSelect);
        addPanel1.add(addEmployee);
        crud.add(addPanel, BorderLayout.NORTH);
        crud.add(addPanel1, BorderLayout.SOUTH);
        crud.setBorder(BorderFactory.createEtchedBorder());

        bottomCont.add(crud, BorderLayout.WEST);
        bottomCont.add(removeEmployee, BorderLayout.EAST);

        queries.add(getEmployeeInfo);


        employeeview = new LeftAlignedJTable(getPopulatedTableModel());
        employeeview.setAutoCreateRowSorter(true);
        employeeview.getTableHeader().setReorderingAllowed(false);
        JScrollPane employeeviewpane = new JScrollPane(employeeview);

        add(queries, BorderLayout.NORTH);
        add(bottomCont, BorderLayout.SOUTH);
        add(employeeviewpane, BorderLayout.CENTER);
    }
}
