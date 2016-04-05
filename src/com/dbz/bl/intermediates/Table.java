package com.dbz.bl.intermediates;

import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public abstract class Table {

    public abstract String getTableName();
    protected abstract Map<String, Object> getChanged();
}
