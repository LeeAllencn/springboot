package com.rocky.boot.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rocky.boot.api.aop.MethodLogRecord;
import com.rocky.boot.api.mapper.UserMapper;
import com.rocky.boot.api.model.User;
import com.rocky.boot.api.service.IUserService;
import com.rocky.boot.api.web.request.UserCreateReq;
import com.rocky.boot.api.web.request.UserUpdateReq;
import com.rocky.boot.api.web.response.UserDetailResp;
import com.rocky.boot.api.web.response.UserListResp;
import com.rocky.boot.common.exceptions.ParameterVerificationException;
import com.rocky.boot.common.model.PageParam;
import com.rocky.boot.common.model.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author rocky
 * Description:
 * Created in 2021/1/30
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    /**
     * MethodLogRecord(key = "userId") 用于注解类切面的测试
     * @param userId 用户id
     * @return UserDetailResp
     */
    @MethodLogRecord(key = "userId")
    @Override
    public UserDetailResp getUser(Integer userId) {
        UserDetailResp userDetailResp = new UserDetailResp();
        User user = userMapper.selectById(userId);
        BeanUtils.copyProperties(user, userDetailResp);
        return userDetailResp;
    }

    @Override
    public UserDetailResp getUserByUsername(String username) {
        UserDetailResp userDetailResp = new UserDetailResp();
        User user = userMapper.getUserByUsername(username);
        BeanUtils.copyProperties(user, userDetailResp);
        return userDetailResp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUser(UserCreateReq userCreateReq) {
        if (isExit(userCreateReq.getUsername())) {
           throw new ParameterVerificationException("用户名已存在");
        }
        User newUser = new User();
        BeanUtils.copyProperties(userCreateReq, newUser);
        Date date = new Date();
        newUser.setCreateTime(date);
        newUser.setUpdateTime(date);
        userMapper.insert(newUser);
    }

    @Override
    public PageResult<UserListResp> listUsers(String search, PageParam pageParam) {
        Page<User> userPage = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>().like(StringUtils.isNotBlank(search), User::getUsername, search);
        IPage<User> userPageResult = userMapper.selectPage(userPage, userLambdaQueryWrapper);
        PageResult<UserListResp> userListRespPageResult = new PageResult<>();
        userListRespPageResult.setCurrentPage(userPageResult.getCurrent());
        userListRespPageResult.setPageSize(userPageResult.getSize());
        userListRespPageResult.setTotal(userPageResult.getTotal());
        userListRespPageResult.setData(userPageResult.getRecords().stream().map(item -> {
            UserListResp userListResp = new UserListResp();
            BeanUtils.copyProperties(item, userListResp);
            return userListResp;
        }).collect(Collectors.toList()));
        return userListRespPageResult;
    }

    @Override
    public void updateUser(Integer userId, UserUpdateReq userUpdateReq) {
        User user = userMapper.selectById(userId);
        BeanUtils.copyProperties(userUpdateReq, user);
        user.setUpdateTime(new Date());
        userMapper.updateById(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userMapper.deleteById(userId);
    }

    @Override
    public Boolean isExit(String username) {
        User user = userMapper.getUserByUsername(username);
        return user != null;
    }
}
