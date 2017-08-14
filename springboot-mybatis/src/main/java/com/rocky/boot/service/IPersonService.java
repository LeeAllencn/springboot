package com.rocky.boot.service;

import com.rocky.boot.model.Person;

/**
 * Created by Rocky on 2017-08-14.
 */
public interface IPersonService {
    Person queryById(int id);
}
