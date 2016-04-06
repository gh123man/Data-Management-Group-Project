package com.dbz.bl;

import com.dbz.bl.query.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by angel on 4/6/16.
 */
public class ConnectionManager {
    private final Connection mConn;

    public ConnectionManager(Connection conn) {
        if (conn == null) {
            throw new IllegalArgumentException("Connection must not be null.");
        }
        mConn = conn;
    }

    // TODO Handle stored procedures
    public ResultSet executeQuery(Query query) throws SQLException {
        ResultSet rs = null;
        try (Statement stmt = mConn.createStatement()) {
            if ( stmt.execute(query.getQuery()) ) {
                rs = stmt.getResultSet();
            }
            stmt.close();
        }
        return rs;
    }

    public static void main(String[] args) throws Exception {
        String queryStr = "CREATE TABLE IF NOT EXISTS Person( ID INT PRIMARY KEY AUTO_INCREMENT, FirstName VARCHAR(128) NOT NULL, MiddleInitial VARCHAR(1) NOT NULL, LastName VARCHAR(128) NOT NULL);";
        Connection conn = ConnectionProvider.getConnection();
        ConnectionManager mgr = new ConnectionManager(conn);
        mgr.executeQuery(new Query(queryStr));
        conn.close();
    }
}
