package com.dbz.bl.intermediates.RealTable;

import com.dbz.bl.intermediates.UpdatableTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/5/16.
 */
public class Eats implements UpdatableTable {

    public static final String ANIMAL_ID = "AnimalID";
    public static final String FOOD_ID   = "FoodID";
    public static final String[] columnNames = new String[] { ANIMAL_ID, FOOD_ID };

    public Integer mAnimalId, mFoodId;
    private boolean mCAnimalId, mCFoodId;
    private boolean mIsNew;

    public static Eats makeNew(Integer animalId, Integer exhibitId) {
        return new Eats(animalId, exhibitId).setNew(true);
    }

    public Eats(Integer animalId, Integer exhibitId) {
        mIsNew = false;
        mAnimalId = animalId;
        mFoodId = exhibitId;
        mCAnimalId = mCFoodId = false;
    }

    private Eats setNew(boolean isNew) {
        mIsNew = isNew;
        mCAnimalId = mCFoodId = true;
        return this;
    }

    public Integer getmAnimalId() {
        return mAnimalId;
    }
    public Integer getExhibitId() {
        return mFoodId;
    }

    public void setAnimalId(Integer v) {
        mAnimalId = v;
        mCAnimalId = true;
    }
    public void setExhibitId(Integer v) {
        mFoodId = v;
        mCFoodId = true;
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
        if (mCAnimalId) changelist.put(ANIMAL_ID, mAnimalId);
        if (mCFoodId) changelist.put(FOOD_ID, mFoodId);
        return changelist;
    }

    @Override
    public boolean isNew() {
        return mIsNew;
    }

    @Override
    public String getInsertCond() {
        return ANIMAL_ID + " = " + mAnimalId + " and " + FOOD_ID + " = " + mFoodId;
    }
}
