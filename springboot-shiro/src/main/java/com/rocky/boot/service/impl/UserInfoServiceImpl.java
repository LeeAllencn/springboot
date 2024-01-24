package com.rocky.boot.service.impl;

import com.rocky.boot.dao.UserInfoDao;
import com.rocky.boot.entry.UserInfo;
import com.rocky.boot.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author Rocky
 * @date 2017-09-19
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}
