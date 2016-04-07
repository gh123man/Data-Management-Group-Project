package com.dbz.gui;

import com.dbz.bl.AsyncDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalPanel extends JPanel
{
    private static JButton getAnimals = new JButton("Get Animals");
    private static JButton getFoodQuantity = new JButton("Food Quantity");
    private static JButton removeAnimal = new JButton("Remove Animal");
    private static JButton addAnimal = new JButton("Add Animal");
    private static JButton moveAnimal = new JButton("Move Animal");
    private final AsyncDataManager adm;

    public AnimalPanel(AsyncDataManager adm)
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
