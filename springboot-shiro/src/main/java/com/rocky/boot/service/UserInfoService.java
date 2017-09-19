package com.rocky.boot.service;

import com.rocky.boot.entry.UserInfo;

/**
 * Created by Rocky on 2017-09-19.
 */
public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}
