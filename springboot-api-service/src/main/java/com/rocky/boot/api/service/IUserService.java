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
}
