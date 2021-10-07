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
     * 根据名称查询
     * @param name 名称
     * @return User
     */
    @Select("select * from user where name = #{name}")
    User queryByName(@Param("name") String name);
}