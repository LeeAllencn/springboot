package com.rocky.boot.service.impl;

import com.rocky.boot.dao.UserMapper;
import com.rocky.boot.model.User;
import com.rocky.boot.service.UserService;
import com.rocky.boot.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/11/27.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
