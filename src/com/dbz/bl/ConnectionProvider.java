package com.dbz.bl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Angel Lomeli on 4/4/16.
 */
public class ConnectionProvider {
    private static final String DB_NAME = "./DBZ.db";
    private static final String DB_FILEPATH = DB_NAME + ".mv.db";
    private static final String JDBC_URL_FORMAT = "jdbc:h2:%s";

    private static Connection sConn = null;

    private ConnectionProvider() {}

    /**
     * @return  Connection to the DBZ database. If a connection has already been established, this method will return
     *          that connection.
     */
    public static Connection getConnection() throws SQLException {
        if (sConn == null || !sConn.isValid(1)) {
            try {
                Class.forName("org.h2.Driver");
                sConn = DriverManager.getConnection(String.format(JDBC_URL_FORMAT, DB_NAME));
            } catch(ClassNotFoundException e) {
                // We can throw/wrap this, but if it happens there's no recovery.
                throw new RuntimeException(e);
            }
        }
        return sConn;
    }

    /**
     * Convenience method to determine whether the database has already been loaded
     * to avoid dealing with the data CSVs unless necessary.
     *
     * @return  true if the database already exists with the expected records.
     */
    public static boolean isDbAlreadyInitialized() {
        // TODO Check for more than just the artifact H2 creates in case the
        // create table initialization succeeded but loading the CSV data failed.
        Path dbPath = Paths.get( DB_FILEPATH );
        return Files.exists( dbPath );
    }

    public static void main( String[] args ) throws Exception {
        Path dbPath = Paths.get( DB_FILEPATH );
        if ( Files.exists( dbPath ) ) {
            Files.delete( dbPath );
        }
        Connection conn = ConnectionProvider.getConnection();
        // Do whatever you want with the connection.
        conn.close();
        System.out.printf( "File exists at project root: %b\n", Files.exists( dbPath ) );
    }
}
