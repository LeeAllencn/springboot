package com.rocky.boot.dao.test2;

import com.rocky.boot.entity.UserEntity;

import java.util.List;

/**
 *
 * @author Rocky
 * @date 2017-09-26
 */
public interface User2Mapper {

    /**
     * get all
     * @return list
     */
    List<UserEntity> getAll();

    /**
     * get one
     * @param id id
     * @return Object
     */
    UserEntity getOne(Long id);

    /**
     * insert
     * @param user user
     */
    void insert(UserEntity user);

    /**
     * update
     * @param user user
     */
    void update(UserEntity user);

    /**
     * delete
     * @param id id
     */
    void delete(Long id);

}