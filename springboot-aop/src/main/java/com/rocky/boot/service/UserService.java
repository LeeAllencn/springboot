package com.rocky.boot.service;

import com.rocky.boot.aop.LogRecord;
import com.rocky.boot.dao.UserDAO;
import com.rocky.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Rocky on 2017-12-12.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @LogRecord(key="add is user")
    public Integer add(User user) {
        userDAO.save(user);
        return 40;
    }
}
