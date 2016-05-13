package com.dbz.test.TestFiles.RealTablesTestFiles;

import com.dbz.bl.intermediates.RealTable.Animal;
import junit.framework.TestCase;

import static org.junit.Assert.*;

/**
 * Created by Chad on 5/13/2016.
 */
public class BasicAnimalTableTest extends TestCase {

    public void testAnimalConstruction1() throws Exception {
        Animal testAnimal = new Animal("N", 0, 1, "G", 2);
    }

    public void testAnimalConstruction2() throws Exception {
        Animal testAnimal = new Animal(0, "N", 0, 1, "G", 2);
    }

    public void testSetName() throws Exception {

        String name = "Test Name";

        Animal testAnimal = new Animal("N", 0, 1, "G", 2);

        testAnimal.setName(name);

        if(testAnimal.getName() != name)
            fail();
    }

    public void testSetAnimalClassID() throws Exception {

        int id = 1;

        Animal testAnimal = new Animal("N", 0, 1, "G", 2);

        testAnimal.setmAnimalClassId(id);

        if(testAnimal.getAnimalClassId() != id)
            fail();
    }

    public void testSetExhibit() throws Exception {

        int exhibit = 1;

        Animal testAnimal = new Animal("N", 0, 1, "G", 2);

        testAnimal.setExhibitId(exhibit);

        if(testAnimal.getExhibitId() != exhibit)
            fail();
    }

}