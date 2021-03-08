package com.rocky.boot.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rocky.boot.api.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author rocky
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}