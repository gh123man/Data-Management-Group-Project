package com.dbz.test.TestFiles.RealTablesTestFiles;

import junit.framework.TestCase;
import com.dbz.bl.intermediates.RealTable.AnimalClass;
import static org.junit.Assert.*;

/**
 * Created by Chad on 5/13/2016.
 */
public class BasicAnimalClassTableTest extends TestCase {

    public void testAnimalClassConstruction1() throws Exception {
        AnimalClass testAC = new AnimalClass("N");
    }

    public void testAnimalClassConstruction2() throws Exception {
        AnimalClass testAC = new AnimalClass(0, "N");
    }

    public void testSetName() throws Exception {
        String Name = "Test Name";

        AnimalClass testAC = new AnimalClass("N");

        testAC.setName(Name);

        if(testAC.getName() != Name)
            fail();
    }
}