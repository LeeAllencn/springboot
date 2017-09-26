package com.rocky.boot.dao.test2;

import com.rocky.boot.entity.UserEntity;

import java.util.List;

/**
 * Created by Rocky on 2017-09-26.
 */
public interface User2Mapper {

    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(Long id);

}