package com.dbz.bl;

import com.dbz.bl.intermediates.Table;
import com.dbz.bl.intermediates.UpdatableTable;
import com.dbz.bl.query.Query;

import java.util.List;

/**
 * Created by brian on 4/9/16.
 */
public interface IDataManager {

    interface CommitEventHandler {
        void onCommit();
    }

    interface GetEventHandler {
        void onGet(List<Table> results);
    }

    interface InvalidRequestHandler {
        void onError(DataManagerBackend.InvalidRequestException  e);
    }

    void commit(final UpdatableTable table, final CommitEventHandler handler, final InvalidRequestHandler errorHandler);

    void exec(final Query query, final GetEventHandler handler, final InvalidRequestHandler errorHandler);
}
