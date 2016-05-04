package com.dbz.gui;

import com.dbz.bl.DataManager;
import com.dbz.bl.IDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalPanel extends JPanel
{
    public static final String TITLE = "Animals";
    private static JButton getAnimals = new JButton("Get Animals");
    private static JButton getFoodQuantity = new JButton("Food Quantity");
    private static JButton removeAnimal = new JButton("Remove Animal");
    private static JButton addAnimal = new JButton("Add Animal");
    private static JButton moveAnimal = new JButton("Move Animal");
    private final IDataManager adm;

    public AnimalPanel(IDataManager adm)
    {
        getAnimals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query getAnimals.");
            }
        });

        getFoodQuantity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query getFoodQuantity.");
            }
        });

        removeAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query removeAnimal.");
            }
        });


        addAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query addAnimal.");
            }
        });


        moveAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO: add query moveAnimal.");
            }
        });

        this.adm = adm;
        setLayout(new FlowLayout());
        add(getAnimals);
        add(getFoodQuantity);
        add(removeAnimal);
        add(addAnimal);
        add(moveAnimal);
    }
}
