package com.rocky.boot.api.web.controller;

import com.rocky.boot.api.service.IUserService;
import com.rocky.boot.api.web.request.UserCreateReq;
import com.rocky.boot.api.web.response.UserDetailResp;
import com.rocky.boot.api.web.response.UserListResp;
import com.rocky.boot.common.model.BaseResult;
import com.rocky.boot.common.model.PageResult;
import com.rocky.boot.common.model.ResultGenerator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author rocky
 * Description: 用户控制器
 * Created in 2021/1/29
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping
    public BaseResult<Void> createUser(@RequestBody @Validated UserCreateReq userCreateReq) {
//        userService.createUser(userCreateReq);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping
    public BaseResult<PageResult<UserListResp>> getUserList(@RequestParam String search) {
        return ResultGenerator.genSuccessResult(null);
    }

    @GetMapping("/{userId}")
    public BaseResult<UserDetailResp> getUserDetail(@PathVariable Integer userId) {
        UserDetailResp userDetailResp = userService.getUserDetail(userId);
        return ResultGenerator.genSuccessResult(userDetailResp);
    }

    @PutMapping("/{userId}")
    public BaseResult<Void> editUserInfo(@PathVariable Integer userId) {
        return ResultGenerator.genSuccessResult(null);
    }

    @DeleteMapping("/{userId}")
    public BaseResult<Void> deleteUser(@PathVariable Integer userId) {
        return ResultGenerator.genSuccessResult(null);
    }

}
