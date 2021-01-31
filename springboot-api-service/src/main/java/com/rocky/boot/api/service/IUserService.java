package com.rocky.boot.api.service;

import com.rocky.boot.api.web.request.UserCreateReq;
import com.rocky.boot.api.web.response.UserDetailResp;

/**
 * @author rocky
 * Description:
 * Created in 2021/1/30
 */
public interface IUserService {

    UserDetailResp getUserDetail(Integer userId);

    void createUser(UserCreateReq userCreateReq);
}
