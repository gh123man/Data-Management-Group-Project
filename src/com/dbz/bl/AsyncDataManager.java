package com.dbz.bl;

import com.dbz.bl.intermediates.Table;
import com.dbz.bl.intermediates.UpdatableTable;
import com.dbz.bl.query.Query;

import java.util.List;

/**
 * Created by brian on 4/6/16.
 */
public class AsyncDataManager {

    private final DataManager mDataManager;

    private interface InvalidRequestHandler {
        void onError(DataManager.InvalidRequestException  e);
    }

    public interface CommitEventHandler {
        void onCommit();
    }

    public interface GetEventHandler {
        void onGet(List<Table> results);
    }


    public AsyncDataManager(ConnectionManager conn) {
        mDataManager = new DataManager(conn);
    }

    public AsyncDataManager(DataManager mgnr) {
        if (mgnr == null) {
            throw new IllegalArgumentException("DataManager must not be null.");
        }
        mDataManager = mgnr;
    }

    public void commit(final UpdatableTable table, final CommitEventHandler handler, final InvalidRequestHandler errorHandler) {
        new Thread(() -> {
            try {
                mDataManager.commit(table);
                handler.onCommit();
            } catch (DataManager.InvalidRequestException e) {
                errorHandler.onError(e);
            }
        }).start();
    }

    public void get(final Query query, final GetEventHandler handler, final InvalidRequestHandler errorHandler)  {
        new Thread(() -> {
            try {
                handler.onGet(mDataManager.get(query));
            } catch (DataManager.InvalidRequestException e) {
                errorHandler.onError(e);
            }
        }).start();
    }

}
