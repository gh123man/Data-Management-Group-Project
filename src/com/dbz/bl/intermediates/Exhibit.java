package com.dbz.bl.intermediates;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brian on 4/4/16.
 */
public class Exhibit extends Table {

    public static final String LOCATION        = "Location";
    public static final String ANIMAL_CAPACITY = "AnimalCapacity";

    private Integer mId;
    public Integer mLocation, mAnimalCapacity;
    private boolean mCLocation, mCAnimalCapacity;

    public Exhibit(Integer location, Integer animalCap) {
        mLocation = location;
        mAnimalCapacity = animalCap;
        mCLocation = mCAnimalCapacity = false;
    }

    protected Exhibit(Integer id, Integer location, Integer animalCap) {
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
        return Exhibit.class.getName();
    }

    @Override
    protected Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCLocation) changelist.put(LOCATION, mLocation);
        if (mCAnimalCapacity) changelist.put(ANIMAL_CAPACITY, mAnimalCapacity);
        return changelist;
    }
}
