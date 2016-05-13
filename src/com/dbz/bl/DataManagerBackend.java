package com.dbz.bl;

import com.dbz.bl.intermediates.RealTable.UpdatableTable;
import com.dbz.bl.intermediates.Table;
import com.dbz.bl.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public void commit(UpdatableTable table) throws InvalidRequestException, SQLException {
        String query;
        // Following the isNew procedure - Commit the table as a new row in the table
        if (table.isNew()) {
            String[] columnNames = table.getColumnNames().toArray(new String[0]);
            query = String.format("INSERT INTO %s (%s) VALUES (%s)",
                    table.getTableName(),
                    String.join(",", columnNames),
                    paramMapToInsertString(columnNames, table.getChanged()));
        } else {
            query = "UPDATE " + table.getTableName() + " SET " + paramMapToUpdateString(table.getChanged()) + " WHERE " + table.getInsertCond();
        }
        executeQuery(query);
        // TODO: make the Insert section populate the ID and return the original object.
        // This will require some refactoring
    }

    /**
     * Takes a query instance
     *  - Returns a result set from the query result
     * @param query
     * @return
     */
    public List<Table> exec(Query query) throws InvalidRequestException {
        ArrayList<Table> results = new ArrayList<>();
        try {
            try (Statement stmt = mConn.createStatement()) {
                if (stmt.execute(query.getQuery())) {
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        results.add(query.mapResult(rs));
                    }
                    rs.close();
                }
                stmt.close();

            }
        } catch (SQLException e) {
            e.printStackTrace(); //Very helpful for debugging
            throw new InvalidRequestException(query);
        }
        return results;
    }

    private ResultSet executeQuery(String query) throws SQLException {
        ResultSet rs = null;
        try (Statement stmt = mConn.createStatement()) {
            if ( stmt.execute(query) ) {
                rs = stmt.getResultSet();
            }
            stmt.close();
        }
        return rs;
    }

    private static String paramToString(Object param) {
        if (param instanceof Integer)
            return Integer.toString((Integer) param);
        else if (param instanceof Float)
            return Float.toString((Float) param);
        else if ((param) instanceof String)
            // TODO Implement proper escaping. This is a temporary workaround to make records work.
            return "'" + ((String) param).replace("'", "''") + "'";
        else if (param instanceof Date) {
            return String.format("parsedatetime(%s, %s)", param.toString(), param.toString(), "yyyy-MM-dd");
        }
        return null;
    }

    private static String paramMapToInsertString(String[] orderedKeys, Map<String, Object> in) {
        // TODO This is an ugly workaround to map ordering not matching column name ordering. Can be better implemented.

        ArrayList<String> params = new ArrayList<>();
        for( String key : orderedKeys ) {
            params.add(paramToString(in.get(key)));
        }
        return params.stream().reduce((a, b) -> a + ", " + b).get();
    }

    private static String paramMapToUpdateString(Map<String, Object> in) {
        ArrayList<String> operations = new ArrayList<>();
        in.entrySet().stream().forEach((entry) -> operations.add(entry.getKey() + " = " + paramToString(entry.getValue())));
        return operations.stream().reduce((a, b) -> a + ", " + b).get();
    }

    // We could just throw the SQL exception instead of using this.
    public static class InvalidRequestException extends Exception {
        private static final String ERR_FORMAT = String.format("The following query is invalid: [%s]");
        public InvalidRequestException(Query query) {
            super(String.format(query.getQuery()));
        }
    }
}
