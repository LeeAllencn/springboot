package com.rocky.boot.service.impl;

import com.rocky.boot.dao.PersonDao;
import com.rocky.boot.model.Person;
import com.rocky.boot.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Rocky on 2017-08-14.
 */
@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public Person queryById(int id) {
        return personDao.selectById(id);
    }
}
