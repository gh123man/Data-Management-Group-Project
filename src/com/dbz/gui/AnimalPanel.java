package com.dbz.gui;

import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.RealTable.Animal;
import com.dbz.bl.intermediates.RealTable.AnimalClass;
import com.dbz.bl.intermediates.VirtualTable.AnimalRecord;
import com.dbz.bl.query.DeleteByIdQuery;
import com.dbz.bl.query.GetAnimalsQuery;
import com.dbz.bl.query.GetClass;
import com.dbz.bl.query.MoveAnimal;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.Vector;

public class AnimalPanel extends JPanel {
    public static final String TITLE = "Animals";
    private static JTextField inputName = new JTextField(7);
    private static JTextField inputExhibitId = new JTextField(3);
    private static JTextField inputAge = new JTextField(3);
    private static JButton getAnimals = new JButton("Get Animals");
    private static JButton removeAnimal = new JButton("Remove Selected Animal(s)");
    private static JButton addAnimal = new JButton("Add Animal");
    private static JButton moveAnimal = new JButton("Move Animal");
    private static LeftAlignedJTable animalview;

    private final int ID_COL_IDX = 0;

    private final IDataManager adm;

    private JComboBox genderBox, classBox;

    private class AnimalTableModel extends BetterSortingTableModel {
        public boolean isCellEditable(int row, int col) {
            return col != ID_COL_IDX;
        }
    }

    private AnimalTableModel getPopulatedTableModel() {
        AnimalTableModel tm = new AnimalTableModel();

        tm.addColumn("ID"); //This gets hidden
        tm.addColumn("Name");
        tm.addColumn("Class");
        tm.addColumn("Exhibit ID");
        tm.addColumn("Gender");
        tm.addColumn("Age");

        adm.exec(new GetAnimalsQuery(), ((query, results) -> {
            java.util.List<AnimalRecord> animals = (java.util.List<AnimalRecord>) (java.util.List) results;
            for (AnimalRecord animal : animals) {
                Object[] obj = {
                        animal.getAnimalID(),
                        animal.getName(),
                        animal.getAnimalClass(),
                        animal.getExhibitID(),
                        animal.getGender(),
                        animal.getAge()
                };
                tm.addRow(obj);
            }
        }));

        return tm;
    }

    private void clearJInputs() {
        inputName.setText("");
        inputExhibitId.setText("");
        inputAge.setText("");
    }

    public AnimalPanel(IDataManager adm) {
        getAnimals.addActionListener(e -> {
            animalview.setModel(getPopulatedTableModel());
            // This allows us to prevent display of the ID, but preserve it for use in update/remove queries.
            animalview.removeColumn(animalview.getColumnModel().getColumn(ID_COL_IDX));
        });

        removeAnimal.addActionListener(e -> {
            for (int row : animalview.getSelectedRows()) {
                final Integer animalID = (Integer) animalview.getModel().getValueAt(row, ID_COL_IDX);
                adm.exec(new DeleteByIdQuery(Animal.class.getSimpleName(), animalID),
                        (query, results) -> {
                            // I don't think there is a result we care about.
                        });
            }
            animalview.setModel(getPopulatedTableModel());
            animalview.removeColumn(animalview.getColumnModel().getColumn(ID_COL_IDX));
        });


        addAnimal.addActionListener(e -> {
            Integer lclass;
            String lname, lexhibitid, lgender, lage;
//                lid = inputId.getText();
            lname = inputName.getText();

            // TODO Change this to a dropdown, users shouldn't need to know the numeric ID.
            lclass = ((AnimalClass) classBox.getSelectedItem()).getId();
            lexhibitid = inputExhibitId.getText();
            lgender = (String) genderBox.getSelectedItem();
            lage = inputAge.getText();
            //Not sure what to change here    V
            if ((lname.length() /** lclass.length()*/ * lexhibitid.length() * lgender.length() * lage.length()) == 0)
                return;

//                TODO
            Animal animal = new Animal(lname, lclass,
                    Integer.parseInt(lexhibitid),
                    lgender,
                    Integer.parseInt(lage));
            adm.commit(animal,
                    (a) -> {
                        System.out.println(((Animal) a).getId());
                    },
                    (a, b) -> b.printStackTrace());

//                Clear inputs
            clearJInputs();
            animalview.setModel(getPopulatedTableModel());
            animalview.removeColumn(animalview.getColumnModel().getColumn(ID_COL_IDX));
        });

        moveAnimal.addActionListener(e -> {
            Integer lid;
            String lexhibitid;
            lid = (Integer) animalview.getModel().getValueAt(animalview.getSelectedRow(), ID_COL_IDX);
            lexhibitid = inputExhibitId.getText();

            if (lid == null || lexhibitid.length() == 0) {
                return;
            }

            adm.exec(new MoveAnimal(lid, Integer.parseInt(lexhibitid)), ((query, results) -> {
                // TODO Do anything with results?
            }));
            clearJInputs();
            animalview.setModel(getPopulatedTableModel());
            animalview.removeColumn(animalview.getColumnModel().getColumn(ID_COL_IDX));
        });

        this.adm = adm;
        setLayout(new BorderLayout());

        JPanel queries = new JPanel(new FlowLayout());
        queries.add(getAnimals);

        JPanel crud = new JPanel(new FlowLayout());
        crud.add(new JLabel("Name"));
        crud.add(inputName);
        crud.add(new JLabel("Class"));


        // ----- HOLY HELL THIS HIS HACKY IM SO SORRY ----
        Vector model = new Vector();
        adm.exec(new GetClass(), (query, results) -> {
            java.util.List<AnimalClass> classes = (java.util.List<AnimalClass>) (java.util.List) results;
            classes.forEach(c -> model.addElement(c));
        });
        classBox = new JComboBox(model);
        classBox.setRenderer(new BasicComboBoxRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    AnimalClass item = (AnimalClass) value;
                    setText(item.getName());
                }
                return this;
            }
        });
        crud.add(classBox);
        // ---------- END HACKY STUFF ----------
        crud.add(new JLabel("Exhibit"));
        crud.add(inputExhibitId);
        crud.add(new JLabel("Gender"));
        crud.add(new JComboBox<String>() {{
            genderBox = this;
            addItem("M");
            addItem("F");
        }});
        crud.add(new JLabel("Age"));
        crud.add(inputAge);
        crud.add(addAnimal);
        crud.add(moveAnimal);
        crud.setBorder(BorderFactory.createEtchedBorder());

        JPanel modify = new JPanel(new FlowLayout());
        modify.add(crud);
        modify.add(removeAnimal);

        animalview = new LeftAlignedJTable(getPopulatedTableModel());

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
