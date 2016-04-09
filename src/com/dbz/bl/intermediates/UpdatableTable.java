package com.dbz.bl.intermediates;

import java.util.Map;

/**
 * Created by angel on 4/5/16.
 */
public interface UpdatableTable extends Table {
    public Map<String, Object> getChanged();
    public boolean isNew();
    public String getInsertCond();
}
