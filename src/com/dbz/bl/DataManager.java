package com.dbz.bl;

import com.dbz.bl.intermediates.Table;
import com.dbz.bl.intermediates.UpdatableTable;
import com.dbz.bl.query.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by brian on 4/5/16.
 */
public class DataManager {
    private final ConnectionManager mConn;

    public DataManager(ConnectionManager conn) {
        if (conn == null) {
            throw new IllegalArgumentException("ConnectionManager must not be null.");
        }
        mConn = conn;
    }

    /**
     * Takes a table instance
     *  - If the table instance exists, update changed fields
     *  - If the table instance is new, insert the data
     * @param table
     */
    public void commit(UpdatableTable table) throws InvalidRequestException {
        // TODO Convert to queries and execute
    }

    /**
     * Takes a query instance
     *  - Returns a result set from the query result
     * @param query
     * @return
     */
    public List<Table> get(Query query) throws InvalidRequestException {
        // TODO
        try {
            mConn.executeQuery(query);
        } catch (SQLException e) {
            throw new InvalidRequestException(query);
        }
        return null;
    }

    // We could just throw the SQL exception instead of using this.
    public static class InvalidRequestException extends Exception {
        private static final String ERR_FORMAT = String.format("The following query is invalid: [%s]");
        public InvalidRequestException(Query query) {
            super(String.format(query.getQuery()));
        }
    }
}
