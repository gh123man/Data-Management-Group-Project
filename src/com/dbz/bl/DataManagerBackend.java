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
public class DataManagerBackend {
    private final Connection mConn;

    public DataManagerBackend(Connection conn) {
        if (conn == null) {
            throw new IllegalArgumentException("Connection must not be null.");
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
    }

    /**
     * Takes a query instance
     *  - Returns a result set from the query result
     * @param query
     * @return
     */
    public List<Table> exec(Query query) throws InvalidRequestException {
        // TODO
        try {
            executeQuery(query);
        } catch (SQLException e) {
            throw new InvalidRequestException(query);
        }
        return null;
    }

    // TODO Handle stored procedures
    private ResultSet executeQuery(Query query) throws SQLException {
        ResultSet rs = null;
        try (Statement stmt = mConn.createStatement()) {
            if ( stmt.execute(query.getQuery()) ) {
                rs = stmt.getResultSet();
            }
            stmt.close();
        }
        return rs;
    }

    // We could just throw the SQL exception instead of using this.
    public static class InvalidRequestException extends Exception {
        private static final String ERR_FORMAT = String.format("The following query is invalid: [%s]");
        public InvalidRequestException(Query query) {
            super(String.format(query.getQuery()));
        }
    }

    public static void main(String[] args) throws Exception {
        String queryStr = "CREATE TABLE IF NOT EXISTS Person( ID INT PRIMARY KEY AUTO_INCREMENT, FirstName VARCHAR(128) NOT NULL, MiddleInitial VARCHAR(1) NOT NULL, LastName VARCHAR(128) NOT NULL);";
        Connection conn = ConnectionProvider.getConnection();
        DataManagerBackend mgr = new DataManagerBackend(conn);
        mgr.executeQuery(new Query(queryStr));
        conn.close();
    }
}
