package com.rocky.boot.enums;

/**
 * Created by Rocky on 2017-10-10.
 */
public enum UserTypeEnum {
    INSIDE("inside"), OUTSIDE("outside");

    private String name;

    private UserTypeEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
