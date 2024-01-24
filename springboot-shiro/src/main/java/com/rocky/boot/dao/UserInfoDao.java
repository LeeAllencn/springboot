package com.rocky.boot.dao;

import com.rocky.boot.entry.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Rocky
 * @date 2017-09-19
 */
public interface UserInfoDao extends CrudRepository<UserInfo,Long> {

    /**
     * 通过username查找用户信息
     * @param username 用户名
     * @return UserInfo
     */
    UserInfo findByUsername(String username);
}
