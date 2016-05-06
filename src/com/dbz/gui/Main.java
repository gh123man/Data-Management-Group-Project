package com.dbz.gui;

import com.dbz.bl.IDataManager;

import javax.swing.*;
import java.awt.*;

public class Main
{
    private static final String MAIN_TITLE="DBZ";
    private static IDataManager adm;

    private static JFrame frame = new JFrame(MAIN_TITLE);
    private static JPanel mainPanel = new JPanel();
    private static JTabbedPane tabbedPane = new JTabbedPane();

    private static JPanel mgmtTab;
    private static JPanel employeeTab;
    private static JPanel animalTab;
    private static JPanel customerTab;

    public Main(IDataManager adm) {
        if (adm == null) {
            // We can't do anything useful without a working data manager.
            throw new IllegalArgumentException( "The data manager must not be null." );
        }
        this.adm = adm;
        initGui();
    }

    private void initGui() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel.setLayout(new FlowLayout());
        frame.getContentPane().add(mainPanel);

        mgmtTab = new ZooManagementPanel(adm);
        employeeTab = new EmployeePanel(adm);
        animalTab = new AnimalPanel(adm);
        customerTab = new CustomerPanel(adm);

        tabbedPane.add(ZooManagementPanel.TITLE, mgmtTab);
        tabbedPane.add(EmployeePanel.TITLE, employeeTab);
        tabbedPane.add(AnimalPanel.TITLE, animalTab);
        tabbedPane.add(CustomerPanel.TITLE, customerTab);

        mainPanel.add(tabbedPane);
    }

    public void display() {
        frame.pack();
        frame.setVisible(true);
    }
}
