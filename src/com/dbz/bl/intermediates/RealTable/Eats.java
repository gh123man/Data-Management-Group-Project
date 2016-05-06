package com.dbz.bl.intermediates.RealTable;

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
    public static final String DAILY_QUANTITY   = "DailyQuantity";
    public static final String[] columnNames = new String[] { ANIMAL_ID, FOOD_ID, DAILY_QUANTITY };

    public Integer mAnimalId, mFoodId, mDailyQuantity;
    private boolean mCAnimalId, mCFoodId, mCDailyQuantity;
    private boolean mIsNew;

    public static Eats makeNew(Integer animalId, Integer exhibitId, Integer dailyQuantity) {
        return new Eats(animalId, exhibitId, dailyQuantity).setNew(true);
    }

    public Eats(Integer animalId, Integer exhibitId, Integer dailyQuantity) {
        mIsNew = false;
        mAnimalId = animalId;
        mFoodId = exhibitId;
        mDailyQuantity = dailyQuantity;
        mCAnimalId = mCFoodId = mCDailyQuantity = false;
    }

    private Eats setNew(boolean isNew) {
        mIsNew = isNew;
        mCAnimalId = mCFoodId = mCDailyQuantity = isNew;
        return this;
    }

    public Integer getmAnimalId() {
        return mAnimalId;
    }
    public Integer getExhibitId() {
        return mFoodId;
    }
    public Integer getDailyQuantity() { return mDailyQuantity; }

    public void setAnimalId(Integer v) {
        mAnimalId = v;
        mCAnimalId = true;
    }
    public void setExhibitId(Integer v) {
        mFoodId = v;
        mCFoodId = true;
    }
    public void setDailyQuantity(Integer v) {
        mDailyQuantity = v;
        mCDailyQuantity = true;
    }

    @Override
    public String getTableName() {
        return Eats.class.getSimpleName();
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
        if (mCDailyQuantity) changelist.put(DAILY_QUANTITY, mDailyQuantity);
        return changelist;
    }

    @Override
    public boolean isNew() {
        return mIsNew;
    }

    @Override
    public String getInsertCond() {

        return String.format("%s=%d and %s=%d and %s=%d",
                             ANIMAL_ID, mAnimalId,
                             FOOD_ID, mFoodId,
                             DAILY_QUANTITY, mDailyQuantity);
    }
}
