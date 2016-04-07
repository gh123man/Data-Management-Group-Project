package com.dbz.gui;

import com.dbz.bl.AsyncDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel extends JPanel
{
    private static JButton getMailingList = new JButton("Get Mailing List");
    private final AsyncDataManager adm;

    public CustomerPanel(AsyncDataManager adm)
    {
        getMailingList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query getMailingList.");
            }
        });

        this.adm = adm;
        
        setLayout(new FlowLayout());
        add(getMailingList);
    }
}
