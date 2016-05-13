package com.dbz.bl.intermediates.VirtualTable;

import com.dbz.bl.intermediates.Table;

/**
 * Created by brian on 5/4/16.
 */
public class ExhibitOverview implements Table {

    private final String mName;
    private final Integer mNumExhibits, mAnimalCapacity, mAnimalCount;

    public ExhibitOverview(String name, Integer numExhibits, Integer animalCapacity, Integer animalCount) {
        mName = name;
        mNumExhibits = numExhibits;
        mAnimalCount = animalCount;
        mAnimalCapacity = animalCapacity;
    }

    public String getName() {
        return mName;
    }

    public Integer getNumExhibits() {
        return mNumExhibits;
    }

    public Integer getAnimalCapacity() {
        return mAnimalCapacity;
    }

    public Integer getAnimalCount() {
        return mAnimalCount;
    }

}
