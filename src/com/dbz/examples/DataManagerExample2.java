package com.dbz.examples;

import com.dbz.bl.ConnectionProvider;
import com.dbz.bl.DataManager;
import com.dbz.bl.intermediates.Animal;

import java.sql.SQLException;

/**
 * Created by brian on 4/9/16.
 */
public class DataManagerExample2 {

    /**
     *
     * This example is good for one-off queries. less structure code
     * but the lambdas will stack up if you use a ton of them.
     * (you can use inner classes as well, but lambdas are cleaner)
     *
     * See example2 for another option
     *
     * @throws SQLException
     */
    public void doStuff() throws SQLException {
        DataManager dm = new DataManager(ConnectionProvider.getConnection());
        Animal animal = new Animal("a", 1, 2, "F", 12);
        dm.commit(animal, (table) -> {
            // Handle success
        }, (table, e) -> {
            // Handle fail
        });
    }

}
