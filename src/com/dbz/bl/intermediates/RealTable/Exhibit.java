package com.dbz.bl.intermediates.RealTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Exhibit implements UpdatableTable {

    public static final String LOCATION        = "Location";
    public static final String ANIMAL_CAPACITY = "AnimalCapacity";
    public static final String[] columnNames = new String[] { LOCATION, ANIMAL_CAPACITY };

    private Integer mId;
    public Integer mLocation, mAnimalCapacity;
    private boolean mCLocation, mCAnimalCapacity;

    public Exhibit(Integer location, Integer animalCap) {
        mLocation = location;
        mAnimalCapacity = animalCap;
        mCLocation = mCAnimalCapacity = true;
    }

    // ONLY FOR BACKEND USE - may need refactoring
    // Cant be protected due to package structure
    public  Exhibit(Integer id, Integer location, Integer animalCap) {
        mId = id;
        mLocation = location;
        mAnimalCapacity = animalCap;
        mCLocation = mCAnimalCapacity = false;
    }

    public Integer getLocation() {
        return mLocation;
    }
    public Integer getmAnimalCapacity() {
        return mAnimalCapacity;
    }

    public void setLocation(Integer v) {
        mLocation = v;
        mCLocation = true;
    }
    public void setAnimalCapacity(Integer v) {
        mAnimalCapacity = v;
        mCAnimalCapacity = true;
    }

    @Override
    public String getTableName() {
        return Exhibit.class.getSimpleName();
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCLocation) changelist.put(LOCATION, mLocation);
        if (mCAnimalCapacity) changelist.put(ANIMAL_CAPACITY, mAnimalCapacity);
        return changelist;
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
