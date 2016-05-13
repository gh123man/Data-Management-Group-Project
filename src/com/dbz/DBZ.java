package com.dbz;

import com.dbz.bl.ConnectionProvider;
import com.dbz.bl.DataManager;
import com.dbz.bl.DataManagerBackend;
import com.dbz.bl.IDataManager;
import com.dbz.bl.query.Query;
import com.dbz.bl.query.RawQuery;
import com.dbz.gui.Main;
import com.dbz.data.DataImport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by angel on 5/4/16.
 */
public class DBZ {
    private static final String SCHEMA_PATH= "schema.sql";

    /**
     * Initialize a database with the default filename, then start up a Swing
     * client to allow the user to interact with the database.
     */
    public static void main(String[] args) {
        try {
            initializeDB();
        } catch (IOException|SQLException|DataManagerBackend.InvalidRequestException e) {
            System.err.printf("%s\nDBZ terminating.", e.getMessage());
            return;
        }

        try {
            initializeGUI();
        } catch (SQLException e) {
            System.err.println("Unable to create connection for GUI. Terminating.");
        }
    }

    private static void initializeDB() throws SQLException, IOException, DataManagerBackend.InvalidRequestException {
        if (!ConnectionProvider.isDbAlreadyInitialized()) {
            // Note: While it may be preferred to just create the connection in main and pass it into both methods, the
            //       act of creating a connection generates the artifact that isDbAlreadyInitialized checks for.
            //       TODO When isDbAlreadyInitialized is given a better init check, consider moving conn up to main.
            Connection dbConn = ConnectionProvider.getConnection();
            createTables(dbConn);
            loadTableDataFromCsvs(dbConn);
        }
    }

    // TODO - Probably just use the SQLException instead of wrapping up a query in the InvalidRequestException
    private static void createTables(Connection dbConn) throws IOException, DataManagerBackend.InvalidRequestException {
        if (dbConn == null) {
            throw new IllegalArgumentException("Connection used to create tables must not be null.");
        }
        String fullSchema = new String(Files.readAllBytes(Paths.get(SCHEMA_PATH)));
        Query createTables = new RawQuery(fullSchema);
        DataManagerBackend mgr = new DataManagerBackend(dbConn);
        mgr.exec(createTables);
    }

    private static void loadTableDataFromCsvs(Connection dbConn) throws SQLException  {
        DataImport d = new DataImport(dbConn);
        d.makeEverything();
    }

    public static void initializeGUI() throws SQLException {
        Connection dbConn = ConnectionProvider.getConnection();
        IDataManager adm = new DataManager(dbConn);
        Main guiMain = new Main(adm);
        guiMain.display();
    }
}
