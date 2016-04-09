package com.dbz.gui;

import com.dbz.bl.DataManager;
import com.dbz.bl.ConnectionManager;
import com.dbz.bl.ConnectionProvider;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main
{
    private static DataManager adm;

    private static JFrame frame = new JFrame("DBZ");
    private static JPanel mainPanel = new JPanel();
    private static JTabbedPane tabbedPane = new JTabbedPane();

    private static JPanel mgmtTab;
    private static JPanel employeeTab;
    private static JPanel animalTab;
    private static JPanel customerTab;

    public static void main(String[] args)
    {
        try {
            adm = new DataManager(new ConnectionManager(ConnectionProvider.getConnection()));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new FlowLayout());
        frame.getContentPane().add(mainPanel);

        mgmtTab = new ZooManagementPanel(adm);
        employeeTab = new EmployeePanel(adm);
        animalTab = new AnimalPanel(adm);
        customerTab = new CustomerPanel(adm);

        tabbedPane.add("Zoo Management", mgmtTab);
        tabbedPane.add("Employees", employeeTab);
        tabbedPane.add("Animals", animalTab);
        tabbedPane.add("Customers", customerTab);

        mainPanel.add(tabbedPane);

        frame.pack();
        frame.setVisible(true);
    }
}
