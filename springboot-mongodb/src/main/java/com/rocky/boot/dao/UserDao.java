package com.rocky.boot.dao;

import com.rocky.boot.entry.UserEntity;

/**
 * Created by Rocky on 2017-10-16.
 */
public interface UserDao  {

    public void saveUser(UserEntity user);

    public UserEntity findUserByUserName(String userName);

    public int updateUser(UserEntity user);

    public void deleteUserById(Long id);

}
