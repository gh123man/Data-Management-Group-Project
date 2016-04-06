package com.dbz.bl;

import com.dbz.bl.intermediates.Table;
import com.dbz.bl.query.Query;
import com.dbz.bl.query.ResultSet;

/**
 * Created by brian on 4/5/16.
 */
public class DataManager {

    public DataManager() {
        //Should take connection
    }

    /**
     * Takes a table instance
     *  - If the table instance exists, update changed fields
     *  - If the table instance is new, insert the data
     * @param table
     */
    public void commit(Table table) {

    }

    /**
     * Takes a query instance
     *  - Returns a result set from the query result
     * @param query
     * @return
     */
    public ResultSet get(Query query) {
        return null;
    }
}
