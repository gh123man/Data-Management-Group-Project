package com.dbz.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by brian on 5/13/16.
 */
public class LeftAlignedJTable extends JTable {

    public LeftAlignedJTable() {
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        int rendererWidth = component.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
        return component;
    }

    public class LeftRenderer extends DefaultTableCellRenderer {
        public LeftRenderer() {
            super();
            setHorizontalAlignment(SwingConstants.LEFT);
        }
    }

}


