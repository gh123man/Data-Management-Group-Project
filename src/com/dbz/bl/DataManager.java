package com.dbz.bl;

import com.dbz.bl.intermediates.RealTable.UpdatableTable;
import com.dbz.bl.query.Query;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by brian on 4/6/16.
 */
public class DataManager implements IDataManager {

    private final DataManagerBackend mDataManagerBackend;

    public DataManager(Connection conn) {
        mDataManagerBackend = new DataManagerBackend(conn);
    }

    public DataManager(DataManagerBackend mgnr) {
        if (mgnr == null) {
            throw new IllegalArgumentException("DataManagerBackend must not be null.");
        }
        mDataManagerBackend = mgnr;
    }

    public void commit(final UpdatableTable table, final CommitEventHandler handler, final InvalidCommitHandler errorHandler) {
        new Thread(() -> {
            try {
                mDataManagerBackend.commit(table);
                handler.onCommit(table);
            } catch (DataManagerBackend.InvalidRequestException e) {
                errorHandler.onError(table, e);
            } catch (SQLException e) {
                errorHandler.onError(table, e);
            }
        }).start();
    }

    public void exec(final Query query, final ExecEventHandler handler, final InvalidExecHandler errorHandler)  {
        new Thread(() -> {
            try {
                handler.onExec(query, mDataManagerBackend.exec(query));
            } catch (DataManagerBackend.InvalidRequestException e) {
                errorHandler.onError(query, e);
            }
        }).start();
    }

}
