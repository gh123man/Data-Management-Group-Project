package com.dbz.gui;

import com.dbz.bl.IDataManager;
import com.dbz.test.res.MockDataManager;

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
    private static JPanel membershipTab;

    public static void main(String[] args)
    {
        MockDataManager mdm = new MockDataManager();
        Main main = new Main(mdm);
        main.display();
    }

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
        membershipTab = new MembershipPanel(adm);

        tabbedPane.add(ZooManagementPanel.TITLE, mgmtTab);
        tabbedPane.add(EmployeePanel.TITLE, employeeTab);
        tabbedPane.add(AnimalPanel.TITLE, animalTab);
        tabbedPane.add(MembershipPanel.TITLE, membershipTab);

        mainPanel.add(tabbedPane);
    }

    public void display() {
        frame.pack();
        frame.setVisible(true);
    }
}
