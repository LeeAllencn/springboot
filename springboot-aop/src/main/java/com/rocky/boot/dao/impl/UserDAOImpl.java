package com.rocky.boot.dao.impl;

import com.rocky.boot.dao.UserDAO;
import com.rocky.boot.model.User;
import org.springframework.stereotype.Component;

/**
 * Created by Rocky on 2017-12-12.
 */
@Component("userDAO")
public class UserDAOImpl implements UserDAO {

    public Integer save(User user) {
        System.out.println("user saved!");
        return 20;
    }

    public void detele() {
        System.out.println("user delete!");
    }

}
