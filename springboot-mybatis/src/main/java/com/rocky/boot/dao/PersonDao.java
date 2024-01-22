package com.rocky.boot.dao;

import com.rocky.boot.core.Mapper;
import com.rocky.boot.model.Person;

import java.util.List;

/**
 *
 * @author Rocky
 * @date 2017-08-14
 */
public interface PersonDao extends Mapper<Person> {
    /**
     * 查询
     * @param id id
     * @return Person
     */
    Person selectById(int id);

    /**
     * 查询列表
     * @return List
     */
    List<Person> selectAllData();
}
