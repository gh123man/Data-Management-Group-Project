package com.dbz.bl.intermediates.VirtualTable;

import com.dbz.bl.intermediates.Table;

/**
 * Created by angel on 5/13/16.
 */
public class AnimalRecord implements Table {
    private final Integer mID;
    private final String mName;
    private final String mAnimalClass;
    private final Integer mExhibitID;
    private final String mGender;
    private final Integer mAge;

    public AnimalRecord(Integer ID, String name, String animalClassName, Integer exhibitID, String gender, Integer age) {
        mID = ID;
        mName = name;
        mAnimalClass = animalClassName;
        mExhibitID = exhibitID;
        mGender = gender;
        mAge = age;
    }

    public Integer getAnimalID() { return mID; }
    public String getName() { return mName; }
    public String getAnimalClass() { return mAnimalClass; }
    public Integer getExhibitID() { return mExhibitID; }
    public String getGender() { return mGender; }
    public Integer getAge() { return mAge; }
}
