package com.rocky.boot.api.service;

import com.rocky.boot.api.web.request.UserCreateReq;
import com.rocky.boot.api.web.request.UserUpdateReq;
import com.rocky.boot.api.web.response.UserDetailResp;
import com.rocky.boot.api.web.response.UserListResp;
import com.rocky.boot.common.model.PageParam;
import com.rocky.boot.common.model.PageResult;

/**
 * @author rocky
 * Description:
 * Created in 2021/1/30
 */
public interface IUserService {

    /**
     * 获取用户详情
     *
     * @param userId userId
     * @return UserDetailResp
     */
    UserDetailResp getUser(Integer userId);

    /**
     * 通过用户名获取用户详情
     * @param username 用户名
     * @return UserDetailResp
     */
    UserDetailResp getUserByUsername(String username);

    /**
     * 创建用户
     * @param userCreateReq userCreateReq
     */
    void saveUser(UserCreateReq userCreateReq);

    /**
     * 获取用户列表
     * @param search search
     * @param pageParam pageParam
     * @return PageResult<UserListResp>
     */
    PageResult<UserListResp> listUsers(String search, PageParam pageParam);

    /**
     * 更新用户
     * @param userId userId
     * @param userUpdateReq userUpdateReq
     */
    void updateUser(Integer userId, UserUpdateReq userUpdateReq);

    /**
     * 删除用户
     * @param userId userId
     */
    void deleteUser(Integer userId);

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return Boolean
     */
    Boolean isExit(String username);
}
