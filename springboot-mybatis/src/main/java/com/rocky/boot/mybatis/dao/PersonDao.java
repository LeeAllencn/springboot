package com.rocky.boot.mybatis.dao;

import com.rocky.boot.mybatis.core.Mapper;
import com.rocky.boot.mybatis.model.Person;

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
