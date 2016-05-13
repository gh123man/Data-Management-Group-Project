package com.dbz.bl.intermediates.RealTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brian on 4/5/16.
 */
public class Animal extends KeyedTable {

    public static final String ID           = "ID";
    public static final String NAME         = "Name";
    public static final String ANIMAL_CLASS = "AnimalClassID";
    public static final String EXHIBIT_ID   = "ExhibitID";
    public static final String GENDER       = "Gender";
    public static final String AGE          = "Age";
    public static final String[] columnNames = new String[] { ID, NAME, ANIMAL_CLASS, EXHIBIT_ID, GENDER, AGE };

    public enum Gender {
        Male,
        Female
    }

    private String mName, mGender;
    public Integer mAnimalClassId, mExhibitId, mAge;
    private boolean mCName, mCGender, mCAnimalClassId, mCExhibitId, mCAge;

    public Animal(String name, Integer animalClass, Integer exhibitId, String gender, Integer age) {
        mName = name;
        mAnimalClassId = animalClass;
        mExhibitId = exhibitId;
        mGender = gender;
        mAge = age;
        mCName = mCGender = mCAnimalClassId = mCExhibitId = mCAge = true;
    }

    // ONLY FOR BACKEND USE - may need refactoring
    // Cant be protected due to package structure
    public Animal(Integer id, String name, Integer animalClass, Integer exhibitId, String gender, Integer age) {
        setId(id);
        mName = name;
        mAnimalClassId = animalClass;
        mExhibitId = exhibitId;
        mGender = gender;
        mAge = age;
        mCName = mCGender = mCAnimalClassId = mCExhibitId = mCAge = false;
    }

    public String getName() {
        return mName;
    }
    public Integer getAnimalClassId() {
        return mAnimalClassId;
    }
    public Integer getExhibitId() {
        return mExhibitId;
    }
    public String getGender() {
        return mGender;
    }
    public Integer getAge() {
        return mAge;
    }

    public void setName(String v) {
        mName = v;
        mCName = true;
    }
    public void setmAnimalClassId(Integer v) {
        mAnimalClassId = v;
        mCAnimalClassId = true;
    }
    public void setExhibitId(Integer v) {
        mExhibitId = v;
        mCExhibitId = true;
    }
    public void setGender(Gender g) {
        mGender = g == Gender.Female ? "F" : "M";
        mCGender = true;
    }

    @Override
    public String getTableName() {
        return Animal.class.getSimpleName();
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    @Override
    public Map<String, Object> getChanged() {
        HashMap<String, Object> changelist = new HashMap<>();
        if (mCName) changelist.put(NAME, mName);
        if (mCAnimalClassId) changelist.put(ANIMAL_CLASS, mAnimalClassId);
        if (mCExhibitId) changelist.put(EXHIBIT_ID, mExhibitId);
        if (mCGender) changelist.put(GENDER, mGender);
        if (mCAge) changelist.put(AGE, mAge);
        return changelist;
    }
}
