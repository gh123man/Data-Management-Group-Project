package com.dbz.bl.intermediates;

import java.util.List;
import java.util.Map;

/**
 * Created by angel on 4/5/16.
 */
public interface UpdatableTable extends Table {
    public String getTableName();
    public List<String> getColumnNames();
    public Map<String, Object> getChanged();
    public boolean isNew();
    public String getInsertCond();
}
