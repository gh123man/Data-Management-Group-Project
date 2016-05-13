package com.dbz.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 * Created by brian on 5/13/16.
 */
public class LeftAlignedJTable extends JTable {

    public LeftAlignedJTable() {
    }

    public LeftAlignedJTable(TableModel tm) {
        super(tm);
    }

    @Override
    public void setModel(TableModel dataModel) {
        super.setModel(dataModel);
        for (int i = 0; i < dataModel.getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(new LeftRenderer());
        }
    }

    public class LeftRenderer extends DefaultTableCellRenderer {
        public LeftRenderer() {
            super();
            setHorizontalAlignment(SwingConstants.LEFT);
        }
    }

}


