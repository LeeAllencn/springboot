package com.rocky.boot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Rocky on 2017-11-22.
 */
/*@Getter
@Setter
@ToString*/
@Data
public class User {

    private String name;

    private int age;

    private String address;
}
