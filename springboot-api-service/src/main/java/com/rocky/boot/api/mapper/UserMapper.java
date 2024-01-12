package com.rocky.boot.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rocky.boot.api.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author rocky
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名称查询
     * @param username 用户名称
     * @return User
     */
    @Select("select * from user where username = #{username} and is_deleted = 0")
    User getUserByUsername(@Param("username") String username);
}