package com.dbz.test.TestFiles;

import com.dbz.bl.IDataManager;
import com.dbz.test.res.MockDataManager;
import junit.framework.TestCase;

import static org.junit.Assert.*;

/**
 * Created by Chad on 5/11/2016.
 */
public class MockDataManagerTest extends TestCase {

    public void testDisplayMockGUI() throws Exception {
        System.out.println("Test Create Mock GUI");

        IDataManager testMDM = new MockDataManager();
        com.dbz.gui.Main testMG = new com.dbz.gui.Main(testMDM);
        testMG.display();

    }
}