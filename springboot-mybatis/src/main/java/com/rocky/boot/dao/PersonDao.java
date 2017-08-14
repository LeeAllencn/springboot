package com.rocky.boot.dao;

import com.rocky.boot.model.Person;

/**
 * Created by Rocky on 2017-08-14.
 */
public interface PersonDao {
    Person selectById(int id);
}
