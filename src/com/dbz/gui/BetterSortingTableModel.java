package com.dbz.gui;

import javax.swing.table.DefaultTableModel;

/**
 * Created by brian on 5/13/16.
 */
public class BetterSortingTableModel extends DefaultTableModel {

    public Class getColumnClass(int col) { //Trick to get sort to work correctly on our floats
        Class returnValue;
        if ((col >= 0) && (col < getColumnCount())) {
            returnValue = getValueAt(0, col).getClass();
        } else {
            returnValue = Object.class;
        }
        return returnValue;
    }
}
