package com.dbz.bl;

import com.dbz.bl.intermediates.UpdatableTable;
import com.dbz.bl.query.Query;

import java.sql.Connection;

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

    public void commit(final UpdatableTable table, final CommitEventHandler handler, final InvalidRequestHandler errorHandler) {
        new Thread(() -> {
            try {
                mDataManagerBackend.commit(table);
                handler.onCommit();
            } catch (DataManagerBackend.InvalidRequestException e) {
                errorHandler.onError(e);
            }
        }).start();
    }

    public void exec(final Query query, final GetEventHandler handler, final InvalidRequestHandler errorHandler)  {
        new Thread(() -> {
            try {
                handler.onGet(mDataManagerBackend.exec(query));
            } catch (DataManagerBackend.InvalidRequestException e) {
                errorHandler.onError(e);
            }
        }).start();
    }

}
