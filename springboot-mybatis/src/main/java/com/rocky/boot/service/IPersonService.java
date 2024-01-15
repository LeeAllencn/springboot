package com.rocky.boot.service;

import com.rocky.boot.model.Person;

import java.util.List;

/**
 *
 * @author Rocky
 * @date 2017-08-14
 */
public interface IPersonService {

    /**
     * 查询
     * @param id id
     * @return Person
     */
    Person queryById(int id);

    /**
     * 查询列表
     * @return List
     */
    List<Person> queryAll();
}
