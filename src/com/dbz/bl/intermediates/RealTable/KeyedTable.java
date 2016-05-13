package com.dbz.bl.intermediates.RealTable;

/**
 * Created by brian on 5/13/16.
 */
public abstract class KeyedTable implements UpdatableTable {

    private Integer mId;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    @Override
    public boolean isNew() {
        return mId == null;
    }

    @Override
    public String getInsertCond() {
        return "ID = " + mId;
    }


}
