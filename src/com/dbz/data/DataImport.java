package com.dbz.data;

import com.dbz.bl.DataManager;
import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.RealTable.*;
import com.dbz.bl.intermediates.Table;
import com.dbz.bl.query.Query;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
/**
 * Created by justincohen on 5/9/16.
 */
public class DataImport implements IDataManager.ExecEventHandler,
        IDataManager.CommitEventHandler,
        IDataManager.InvalidCommitHandler {
    private static final String CSV_BASE_PATH = "src/com/dbz/data/csv/";


    private Connection dbConn;
    private DataManager dm;

    public DataImport(Connection db){
        this.dbConn = db;
        this.dm = new DataManager(this.dbConn);
    }

    public void makePeople() throws SQLException {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(CSV_BASE_PATH + "names.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()){
            String l = scan.nextLine();
            String[] data = l.split(",");
            Person p = new Person(data[0],data[1],data[2],Integer.parseInt(data[3]));
            dm.commit(p,this,this);
        }
        scan.close();
    }

    public void makeAddress() throws SQLException{
        Scanner scan = null;
        try {
            scan = new Scanner(new File(CSV_BASE_PATH + "addresses.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()){
            String l = scan.nextLine();
            String[] data = l.split(",");
            Address a = new Address(data[0],data[1],data[2],data[3],data[4]);
            dm.commit(a,this,this);
        }
        scan.close();
    }

    public void makeAnimal(){
        Scanner scan = null;
        try {
            scan = new Scanner(new
                    File(CSV_BASE_PATH + "animal.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()){
            String l = scan.nextLine();
            String[] data = l.split(",");
            Animal a  = new Animal(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[2]),data[3],Integer.parseInt(data[4]));
            dm.commit(a,this,this);
        }
        scan.close();
    }

    public void makeAnimalClass(){
        Scanner scan = null;
        try {
            scan = new Scanner(new
                    File(CSV_BASE_PATH + "animal_class.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()){
            String l = scan.nextLine();
            String[] data = l.split(",");
            AnimalClass c = new AnimalClass(data[0]);
            dm.commit(c,this,this);
        }
        scan.close();

    }

    public void makeEats(){
        Scanner scan = null;
        try {
            scan = new Scanner(new
                    File(CSV_BASE_PATH + "eats.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()){
            String l = scan.nextLine();
            String[] data = l.split(",");
            Eats e = Eats.makeNew(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]));
            dm.commit(e,this,this);
        }
        scan.close();
    }

    public void makeEmployee(){
        Scanner scan = null;
        try {
            scan = new Scanner(new
                    File(CSV_BASE_PATH + "employee.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {
            String l = scan.nextLine();
            String[] data = l.split(",");
            Employee e  = new Employee(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2]));
            dm.commit(e,this,this);
        }
        scan.close();
    }

    public void makeEmployeeExhibit(){
        Scanner scan = null;
        try {
            scan = new Scanner(new
                    File(CSV_BASE_PATH + "employee_exhibit.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {
            String l = scan.nextLine();
            String[] data = l.split(",");
            EmployeeExhibit e = EmployeeExhibit.makeNew(Integer.parseInt(data[0]),Integer.parseInt(data[1]));
            dm.commit(e,this,this);
        }
        scan.close();
    }

    public void makeExhibit(){
        Scanner scan = null;
        try {
            scan = new Scanner(new
                    File(CSV_BASE_PATH + "exhibit.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {
            String l = scan.nextLine();
            String[] data = l.split(",");
            Exhibit e = new Exhibit(Integer.parseInt(data[0]),Integer.parseInt(data[1]));
            dm.commit(e,this,this);
        }
        scan.close();
    }

    public void makeFood(){
        Scanner scan = null;
        try {
            scan = new Scanner(new
                    File(CSV_BASE_PATH + "food.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {
            String l = scan.nextLine();
            String[] data = l.split(",");
            Food f  = new Food(data[0],Float.parseFloat(data[1]));
            dm.commit(f,this,this);
        }
        scan.close();
    }

    public void makeJobType(){
        Scanner scan = null;
        try {
            scan = new Scanner(new
                    File(CSV_BASE_PATH + "jobs.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {
            String l = scan.nextLine();
            String[] data = l.split(",");
            JobType j = new JobType(data[0]);
            dm.commit(j,this,this);
        }
        scan.close();
    }

    public void makeLocation(){
        Scanner scan = null;
        try {
            scan = new Scanner(new
                    File(CSV_BASE_PATH + "location.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {
            String l = scan.nextLine();
            String[] data = l.split(",");
            Location loc = new Location(data[0],Integer.parseInt(data[1]));
            dm.commit(loc,this,this);
        }
        scan.close();
    }

    public void makeMembership(){
        Scanner scan = null;
        try {
            scan = new Scanner(new
                    File(CSV_BASE_PATH + "memberships.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {
            String l = scan.nextLine();
            String[] data = l.split(",");
            String [] date = data[1].split("-");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            Membership m = new Membership(Integer.parseInt(data[0]), new Date(cal.getTimeInMillis()));
            dm.commit(m, this, this);
        }
        scan.close();
    }

    public void makeEverything() throws SQLException{
        // Order is important due to foreign keys with the not null constraint.
        makeAddress();
        makePeople();
        makeMembership();
//        makeJobType();
//        makeLocation();
//        makeExhibit();
//        makeEmployee();
//        makeFood();
//        makeAnimalClass();
//        makeAnimal();
//        makeEats();
//        makeEmployeeExhibit();
    }


    @Override
    public void onCommit(UpdatableTable query) {
        //handle successful commit
    }

    @Override
    public void onExec(Query query, List<Table> results) {
        // This is an ugly cast. If someone can come up with a clean way
        // to fix this, go for it. otherwise lets just deal with it.
    }

    @Override
    public void onError(UpdatableTable query, Exception e) {
        //handle stuff
        e.printStackTrace();
    }
/*
    public static void main(String[] args) throws SQLException{
        Connection dbConn = null;
        dbConn = ConnectionProvider.getConnection();
        DataImport d = new DataImport(dbConn);
        d.makeEverything();

    }
*/
}
