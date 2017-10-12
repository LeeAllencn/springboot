package com.rocky.boot.dao;

import com.rocky.boot.model.Person;

import java.util.List;

/**
 * Created by Rocky on 2017-08-14.
 */
public interface PersonDao {
    Person selectById(int id);

    List<Person> selectAll();
}
