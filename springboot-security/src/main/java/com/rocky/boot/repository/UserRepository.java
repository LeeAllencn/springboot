package com.rocky.boot.repository;

import com.rocky.boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rocky
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 查询用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);
}
