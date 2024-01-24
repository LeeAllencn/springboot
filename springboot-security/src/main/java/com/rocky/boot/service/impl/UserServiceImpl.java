package com.rocky.boot.service.impl;

import com.rocky.boot.model.User;
import com.rocky.boot.repository.UserRepository;
import com.rocky.boot.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lixin
 * Description:
 * Created in 2019/3/23
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}
