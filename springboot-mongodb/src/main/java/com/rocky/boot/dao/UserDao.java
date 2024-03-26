package com.rocky.boot.dao;

import com.rocky.boot.entry.UserEntity;

/**
 *
 * @author Rocky
 * @date 2017-10-16
 */
public interface UserDao  {

    /**
     * save user
     * @param user user
     */
    void saveUser(UserEntity user);

    /**
     * find user
     * @param userName userName
     * @return Object
     */
    UserEntity findUserByUserName(String userName);

    /**
     * update user
     * @param user user
     * @return int
     */
    int updateUser(UserEntity user);

    /**
     * delete user
     * @param id id
     */
    void deleteUserById(Long id);

}
