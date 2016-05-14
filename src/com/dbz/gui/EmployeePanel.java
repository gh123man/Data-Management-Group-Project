package com.dbz.gui;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.RealTable.Address;
import com.dbz.bl.intermediates.RealTable.Employee;
import com.dbz.bl.intermediates.RealTable.JobType;
import com.dbz.bl.intermediates.RealTable.Person;
import com.dbz.bl.intermediates.VirtualTable.EmployeeInfo;
import com.dbz.bl.query.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class EmployeePanel extends JPanel
{
    public static final String TITLE = "Employees";

    private static JButton getEmployeeInfo = new JButton("Get Employee Contact Info");
    private static JButton removeEmployee = new JButton("Remove Selected Employee(s)");
    private static JButton addEmployee = new JButton("Add Employee");
    private static JTextField newSalaryAmount = new JTextField(7);
    private static JComboBox newJobSelect = new JComboBox();
    private static JTextField newFName = new JTextField(7);
    private static JTextField newMI = new JTextField(1);
    private static JTextField newLName = new JTextField(7);
    private static JTextField newAddr1 = new JTextField(10);
    private static JTextField newAddr2 = new JTextField(6);
    private static JTextField newCity = new JTextField(6);
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
            return false;
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
            DefaultComboBoxModel<String> cbm = new DefaultComboBoxModel<>(new Vector(jobs.keySet()));
            newJobSelect.setModel(cbm);
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

            try
            {
                String fname = newFName.getText();
                String mi = newMI.getText();
                String lname = newLName.getText();
                int newSalary = Integer.parseInt(newSalaryAmount.getText());
                Integer jobType = jobs.get(newJobSelect.getSelectedItem());
                String street1 = newAddr1.getText();
                String street2 = newAddr2.getText();
                String city = newCity.getText();
                String state = newState.getText();
                if (state.length() != 2)
                {
                    return;
                }
                String zip = newZip.getText();
                if (zip.length() != 5)
                {
                    return;
                }

                if (fname.length() * lname.length() * street1.length() * state.length() * zip.length() * city.length() == 0)
                    return;

                System.out.println("new employee: jobid = " + jobType + " salary: " + newSalary);

                final Integer[] addrId = new Integer[1];
                // Address first
                Address newAddress = new Address(street1, street2, city, state, zip);
                adm.commit(newAddress, (a) -> {
                    addrId[0] = ((Address)a).getId();
                    Person newPerson = new Person(fname, mi, lname, addrId[0]);
                    final Integer[] personId = new Integer[1];
                    // Person second
                    adm.commit(newPerson, (a1) -> {
                        personId[0] = ((Person)a1).getId();
                        // Last to Employee
                        Employee newEmployee = new Employee(personId[0], newSalary, jobType);
                        adm.commit(newEmployee, (a2) -> {

                        }, (a2, b) -> {
                            System.err.println("err committing employee");

                            return;
                        });
                    }, (a3,b) -> {
                        System.err.println("err committing person");
                        return;
                    });
                }, (a, b) -> {
                    System.err.println("err committing address");
                    return;
                });

                // Clear on success.
                newFName.setText("");
                newLName.setText("");
                newMI.setText("");
                newSalaryAmount.setText("");
                newAddr1.setText("");
                newCity.setText("");
                newAddr2.setText("");
                newState.setText("");
                newZip.setText("");
            } catch (Exception exc)
            {

            }
            employeeview.setModel(getPopulatedTableModel());
        });

        this.adm = adm;
        setLayout(new BorderLayout());

        JPanel queries = new JPanel(new FlowLayout());
        JPanel bottomCont = new JPanel((new BorderLayout()));
        JPanel crud = new JPanel((new BorderLayout()));
        JPanel addPanel = new JPanel(new FlowLayout());
        JPanel addPanel1 = new JPanel(new FlowLayout());
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
        addPanel1.add(new JLabel("City"));
        addPanel1.add(newCity);
        addPanel1.add(new JLabel("State"));
        addPanel1.add(newState);
        addPanel1.add(new JLabel("Zip"));
        addPanel1.add(newZip);
        addPanel1.add(new JLabel("Job"));
        populateKnownJobs();

        DefaultComboBoxModel<String> cbm = new DefaultComboBoxModel<>();

        for (String job : jobs.keySet())
        {
            cbm.addElement(job);
        }

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
