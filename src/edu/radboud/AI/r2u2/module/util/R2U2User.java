package edu.radboud.ai.r2u2.module.util;

/**
 * Created by Pieter Marsman on 3-7-2014.
 */
public class R2U2User {

    public Integer age;
    public Boolean isMan;
    private String name;

    public R2U2User(String name) {
        this.name = name;
        age = null;
        isMan = null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String man;
        if (isMan != null)
            man = isMan ? " is man" : " is female";
        else
            man = "";
        String ageString;
        if (age != null)
            ageString = " (" + String.valueOf(age) + ")";
        else
            ageString = "";
        return "User " + name + ageString + man;
    }
}
