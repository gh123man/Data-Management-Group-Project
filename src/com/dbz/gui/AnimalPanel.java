package com.dbz.gui;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.RealTable.Animal;
import com.dbz.bl.query.DeleteByIdQuery;
import com.dbz.bl.query.GetAnimalsQuery;
import com.dbz.bl.query.MoveAnimal;
import com.dbz.bl.query.RawQuery;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalPanel extends JPanel
{
    public static final String TITLE = "Animals";
    private static JTextField inputName = new JTextField(7);
    private static JTextField inputClassId = new JTextField(3);
    private static JTextField inputExhibitId= new JTextField(3);
    private static JTextField inputGender = new JTextField(6);
    private static JTextField inputAge = new JTextField(3);
    private static JButton getAnimals = new JButton("Get Animals");
    private static JButton removeAnimal = new JButton("Remove Selected Animal(s)");
    private static JButton addAnimal = new JButton("Add Animal");
    private static JButton moveAnimal = new JButton("Move Animal");
    private static JTable animalview;

    private final int ID_COL_IDX = 0;

    private final IDataManager adm;

    private class AnimalTableModel extends DefaultTableModel
    {
        public boolean isCellEditable(int row, int col)
        {
            return col != ID_COL_IDX;
        }
    }

    private AnimalTableModel getPopulatedTableModel()
    {
        AnimalTableModel tm = new AnimalTableModel();

        for (String col : Animal.columnNames) {
            tm.addColumn(col);
        }

        adm.exec(new GetAnimalsQuery(), ((query, results) -> {
            java.util.List<Animal> animals = (java.util.List<Animal>)(java.util.List) results;
            for (Animal animal : animals)
            {
                Object[] obj = {
                        animal.getID(),
                        animal.getName(),
                        animal.getAnimalClassId(),
                        animal.getExhibitId(),
                        animal.getGender(),
                        animal.getAge()
                };
                tm.addRow(obj);
            }
        }));

        return tm;
    }

    private void clearJInputs()
    {
        inputName.setText("");
        inputClassId.setText("");
        inputExhibitId.setText("");
        inputGender.setText("");
        inputAge.setText("");
    }

    public AnimalPanel(IDataManager adm)
    {
        getAnimals.addActionListener(e -> {
            animalview.setModel(getPopulatedTableModel());
            // This allows us to prevent display of the ID, but preserve it for use in update/remove queries.
            animalview.removeColumn(animalview.getColumnModel().getColumn(ID_COL_IDX));
        });

        removeAnimal.addActionListener(e -> {
            for (int row : animalview.getSelectedRows()) {
                final Integer animalID = (Integer) animalview.getModel().getValueAt(row, ID_COL_IDX);
                System.out.println("TODO: add query removeAnimal.");
                System.out.println("Deleting Animal " + row + ", ID: " + animalID);

                adm.exec(new DeleteByIdQuery(Animal.class.getSimpleName(), animalID),
                        (query, results) -> {
//                            I don't think there is a result we care about.
                        });
            }
            animalview.setModel(getPopulatedTableModel());
        });


        addAnimal.addActionListener(e -> {
            String lname,lclass,lexhibitid,lgender,lage;
//                lid = inputId.getText();
            lname = inputName.getText();
            lclass = inputClassId.getText();
            lexhibitid = inputExhibitId.getText();
            lgender = inputGender.getText();
            lage = inputAge.getText();

            if ((lname.length() * lclass.length() * lexhibitid.length() * lgender.length() * lage.length()) == 0)
                return;

//                TODO
            System.out.println("TODO: add query addAnimal.");
            adm.exec(new RawQuery(""), ((query, results) -> {

            }));

//                Clear inputs
            clearJInputs();
            animalview.setModel(getPopulatedTableModel());
        });


        moveAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lid, lexhibitid;
                lid = (String) animalview.getModel().getValueAt(animalview.getSelectedRow(), ID_COL_IDX);
                lexhibitid = inputExhibitId.getText();

                if ((lid.length() * lexhibitid.length()) == 0)
                    return;

                adm.exec(new MoveAnimal(Integer.parseInt(lid), Integer.parseInt(lexhibitid)), ((query, results) -> {

                }));
                clearJInputs();
                animalview.setModel(getPopulatedTableModel());
            }
        });

        this.adm = adm;
        setLayout(new BorderLayout());

        JPanel queries = new JPanel(new FlowLayout());
        queries.add(getAnimals);

        JPanel crud = new JPanel(new FlowLayout());
        crud.add(new JLabel("Name"));
        crud.add(inputName);
        crud.add(new JLabel("Class"));
        crud.add(inputClassId);
        crud.add(new JLabel("Exhibit"));
        crud.add(inputExhibitId);
        crud.add(new JLabel("Gender"));
        crud.add(inputGender);
        crud.add(new JLabel("Age"));
        crud.add(inputAge);
        crud.add(addAnimal);
        crud.add(moveAnimal);
        crud.setBorder(BorderFactory.createEtchedBorder());

        JPanel modify = new JPanel(new FlowLayout());
        modify.add(crud);
        modify.add(removeAnimal);

        animalview = new JTable(getPopulatedTableModel());

        // This allows us to prevent display of the ID, but preserve it for use in update/remove queries.
        animalview.removeColumn(animalview.getColumnModel().getColumn(ID_COL_IDX));

        animalview.setAutoCreateRowSorter(true);
        animalview.getTableHeader().setReorderingAllowed(false);
        JScrollPane animalviewpane = new JScrollPane(animalview);

        add(queries, BorderLayout.NORTH);
        add(animalviewpane, BorderLayout.CENTER);
        add(modify, BorderLayout.SOUTH);
    }
}
