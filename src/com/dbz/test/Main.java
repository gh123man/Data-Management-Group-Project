package com.dbz.test;

import com.dbz.bl.IDataManager;
import com.dbz.test.res.MockDataManager;

/**
 * Created by brian on 5/4/16.
 */
public class Main {
    public static void main(String[] args) {
        IDataManager adm = new MockDataManager();
        com.dbz.gui.Main guiMain = new com.dbz.gui.Main(adm);
        guiMain.display();
    }
}
