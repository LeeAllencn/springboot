package com.rocky.boot.service.impl;

import com.rocky.boot.core.AbstractService;
import com.rocky.boot.dao.PersonDao;
import com.rocky.boot.model.Person;
import com.rocky.boot.service.IPersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author Rocky
 * @date 2017-08-14
 */
@Service
public class PersonServiceImpl extends AbstractService<Person> implements IPersonService {

    @Resource
    private PersonDao personDao;

    @Override
    public Person queryById(int id) {
        return personDao.selectById(id);
    }

    @Override
    public List<Person> queryAll() {
        return personDao.selectAllData();
    }
}
