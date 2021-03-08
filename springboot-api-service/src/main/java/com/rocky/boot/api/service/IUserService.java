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
     * @param userId
     * @return
     */
    UserDetailResp getUser(Integer userId);

    /**
     * 创建用户
     * @param userCreateReq
     */
    void saveUser(UserCreateReq userCreateReq);

    /**
     * 获取用户列表
     * @param search
     * @param pageParam
     * @return
     */
    PageResult<UserListResp> listUsers(String search, PageParam pageParam);

    /**
     * 更新用户
     * @param userId
     * @param userUpdateReq
     */
    void updateUser(Integer userId, UserUpdateReq userUpdateReq);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Integer userId);
}
