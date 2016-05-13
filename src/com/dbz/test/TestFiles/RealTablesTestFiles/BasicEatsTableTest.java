package com.dbz.test.TestFiles.RealTablesTestFiles;

import junit.framework.TestCase;
import com.dbz.bl.intermediates.RealTable.Eats;
import static org.junit.Assert.*;

/**
 * Created by Chad on 5/13/2016.
 */
public class BasicEatsTableTest extends TestCase {

    public void testEatsClassConstruction() throws Exception {
        Eats tEats = new Eats(0, 1, 2);
    }

    public void testSetAnimalID() throws Exception {
        int ID = 2;

        Eats tEats = new Eats(0, 1, 2);

        tEats.setAnimalId(ID);

        if(tEats.getmAnimalId() != ID)
            fail();
    }

    public void testSetExhibitID() throws Exception {
        int ID = 2;

        Eats tEats = new Eats(0, 1, 2);

        tEats.setExhibitId(ID);

        if(tEats.getExhibitId() != ID)
            fail();
    }

    public void testSetDailyQuantity() throws Exception {
        int Q = 2;

        Eats tEats = new Eats(0, 1, 2);

        tEats.setDailyQuantity(Q);

        if(tEats.getDailyQuantity() != Q)
            fail();
    }
}