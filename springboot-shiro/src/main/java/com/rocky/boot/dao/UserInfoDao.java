package com.rocky.boot.dao;

import com.rocky.boot.entry.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Rocky on 2017-09-19.
 */
public interface UserInfoDao extends CrudRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}
