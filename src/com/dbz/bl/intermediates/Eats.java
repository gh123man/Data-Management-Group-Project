package com.dbz.bl.intermediates;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/5/16.
 */
public class Eats implements UpdatableTable {

    public static final String EMPLOYEE_ID = "AnimalID";
    public static final String EXHIBIT_ID  = "FoodID";
    public static final String[] columnNames = new String[] { EMPLOYEE_ID, EXHIBIT_ID };

    public Integer mAnimalId, mExhibitId;
    private boolean mCAnimalId, mCExhibitId;
    private boolean mIsNew;

    public static Eats makeNew(Integer animalId, Integer exhibitId) {
        return new Eats(animalId, exhibitId).setNew(true);
    }

    public Eats(Integer animalId, Integer exhibitId) {
        mIsNew = false;
        mAnimalId = animalId;
        mExhibitId = exhibitId;
        mCAnimalId = mCExhibitId = false;
    }

    private Eats setNew(boolean isNew) {
        mIsNew = isNew;
        return this;
    }

    public Integer getmAnimalId() {
        return mAnimalId;
    }
    public Integer getExhibitId() {
        return mExhibitId;
    }

    public void setAnimalId(Integer v) {
        mAnimalId = v;
        mCAnimalId = true;
    }
    public void setExhibitId(Integer v) {
        mExhibitId = v;
        mCExhibitId = true;
    }

    @Override
    public String getTableName() {
        return Eats.class.getName();
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCAnimalId) changelist.put(EMPLOYEE_ID, mAnimalId);
        if (mCExhibitId) changelist.put(EXHIBIT_ID, mExhibitId);
        return changelist;
    }

    @Override
    public boolean isNew() {
        return mIsNew;
    }
}
