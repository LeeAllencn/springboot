package com.rocky.boot.service;

import com.rocky.boot.model.Person;

import java.util.List;

/**
 * Created by Rocky on 2017-08-14.
 */
public interface IPersonService {
    Person queryById(int id);

    List<Person> queryAll();
}
