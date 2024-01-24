package com.rocky.boot.service;

import com.rocky.boot.model.User;

/**
 * @author rocky
 * Description:
 * Created in 2019/3/23
 */
public interface IUserService {
    /**
     * 用户注册接口
     * @param user user
     */
    void addUser(User user);
}
