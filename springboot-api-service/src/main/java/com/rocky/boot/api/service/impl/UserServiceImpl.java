package com.rocky.boot.api.service.impl;

import com.rocky.boot.api.mapper.UserMapper;
import com.rocky.boot.api.model.User;
import com.rocky.boot.api.service.IUserService;
import com.rocky.boot.api.web.request.UserCreateReq;
import com.rocky.boot.api.web.response.UserDetailResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author rocky
 * Description:
 * Created in 2021/1/30
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetailResp getUserDetail(Integer userId) {
        UserDetailResp userDetailResp = new UserDetailResp();
        User user = userMapper.selectById(userId);
        BeanUtils.copyProperties(user, userDetailResp);
        return userDetailResp;
    }

    @Override
    public void createUser(UserCreateReq userCreateReq) {
        User newUser = new User();
        BeanUtils.copyProperties(userCreateReq, newUser);
        userMapper.insert(newUser);
    }
}
