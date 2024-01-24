package com.rocky.boot.service;

import com.rocky.boot.entry.UserInfo;

/**
 *
 * @author Rocky
 * @date 2017-09-19
 */
public interface UserInfoService {
    /**
     * 通过username查找用户信息
     * @param username 用户名
     * @return UserInfo
     */
    UserInfo findByUsername(String username);
}
