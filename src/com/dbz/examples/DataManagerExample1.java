package com.dbz.examples;

import com.dbz.bl.ConnectionProvider;
import com.dbz.bl.DataManager;
import com.dbz.bl.IDataManager;
import com.dbz.bl.intermediates.Animal;
import com.dbz.bl.intermediates.Table;
import com.dbz.bl.intermediates.UpdatableTable;
import com.dbz.bl.query.GetAnimalsByName;
import com.dbz.bl.query.Query;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by brian on 4/9/16.
 */
public class DataManagerExample1 implements IDataManager.ExecEventHandler,
        IDataManager.CommitEventHandler,
        IDataManager.InvalidCommitHandler,
        IDataManager.InvalidExecHandler {

    /**
     *
     * This example is good for a big class that does lots db operations as
     * you can handle them all in one place
     *
     * See example2 for another option
     *
     * @throws SQLException
     */
    public void doStuff() throws SQLException {
        DataManager dm = new DataManager(ConnectionProvider.getConnection());
        // Create a new Animal
        Animal animal = new Animal("bob", 1, 2, "F", 12);

        // Add it to the DB
        dm.commit(animal, this, this);

        // Now query it
        dm.exec(new GetAnimalsByName("bob"), this, this);


    }

    @Override
    public void onCommit(UpdatableTable query) {
        //handle successful commit
    }

    @Override
    public void onExec(Query query, List<Table> results) {
        // This is an ugly cast. If someone can come up with a clean way
        // to fix this, go for it. otherwise lets just deal with it.
        List<Animal> animalsNamedBob = (List<Animal>)(List) results;

    }

    @Override
    public void onError(UpdatableTable query, Exception e) {
        //handle stuff
    }

    @Override
    public void onError(Query query, Exception e) {
        //handle stuff
    }

}
