package com.rocky.boot.dao;

import com.rocky.boot.model.User;

/**
 * Created by Rocky on 2017-12-12.
 */
public interface UserDAO {
    public Integer save(User user);
    public void detele();
}
