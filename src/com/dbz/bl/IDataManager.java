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
        void onCommit(UpdatableTable query);
    }

    interface ExecEventHandler {
        void onExec(Query query, List<Table> results);
    }

    interface InvalidCommitHandler {
        void onError(UpdatableTable query, Exception e);
    }

    interface InvalidExecHandler {
        void onError(Query query, Exception e);
    }

    void commit(final UpdatableTable table, final CommitEventHandler handler, final InvalidCommitHandler errorHandler);

    void exec(final Query query, final ExecEventHandler handler, final InvalidExecHandler errorHandler);
}
