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

    UserDetailResp getUser(Integer userId);

    void saveUser(UserCreateReq userCreateReq);

    PageResult<UserListResp> listUsers(String search, PageParam pageParam);

    void updateUser(Integer userId, UserUpdateReq userUpdateReq);

    void deleteUser(Integer userId);
}
