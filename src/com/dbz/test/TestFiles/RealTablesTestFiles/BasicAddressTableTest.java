package com.dbz.test.TestFiles.RealTablesTestFiles;

import junit.framework.TestCase;
import com.dbz.bl.intermediates.RealTable.Address;

import static org.junit.Assert.*;

/**
 * Created by Chad on 5/13/2016.
 */
public class BasicAddressTableTest extends TestCase {

    public void testAddressConstruction1() throws Exception {
        Address testAddress = new Address("T1", "T2", "C", "ST", "Zip");
    }

    public void testAddressConstruction2() throws Exception {
        Address testAddress = new Address("T1", "T2", "C", "ST", "Zip");
    }

    public void testSetStreet1() throws Exception {

        String st1 = "Test Street 1";

        Address testAddress = new Address("T1", "T2", "C", "ST", "Zip");

        testAddress.setStreet1(st1);

        if(testAddress.getStreet1() != st1)
            fail();
    }

    public void testSetStreet2() throws Exception {

        String st2 = "Test Street 2";

        Address testAddress = new Address("T1", "T2", "C", "ST", "Zip");

        testAddress.setStreet2(st2);

        if(testAddress.getmStreet2() != st2)
            fail();
    }

    public void testSetCity() throws Exception {

        String city = "Test City";

        Address testAddress = new Address("T1", "T2", "C", "ST", "Zip");

        testAddress.setCity(city);

        if(testAddress.getCity() != city)
            fail();
    }

    public void testSetState() throws Exception {

        String state = "Test State";

        Address testAddress = new Address("T1", "T2", "C", "ST", "Zip");

        testAddress.setState(state);

        if(testAddress.getState() != state)
            fail();
    }

    public void testSetZipCode() throws Exception {

        String zip = "Test Zip";

        Address testAddress = new Address("T1", "T2", "C", "ST", "Zip");

        testAddress.setZipCode(zip);

        if(testAddress.getZipCode() != zip)
            fail();
    }
}