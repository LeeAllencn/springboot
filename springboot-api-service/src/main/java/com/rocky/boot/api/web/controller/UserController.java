package com.rocky.boot.api.web.controller;

import com.rocky.boot.api.service.IUserService;
import com.rocky.boot.api.web.request.UserCreateReq;
import com.rocky.boot.api.web.response.UserDetailResp;
import com.rocky.boot.api.web.response.UserListResp;
import com.rocky.boot.common.model.BaseResult;
import com.rocky.boot.common.model.PageResult;
import com.rocky.boot.common.model.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author rocky
 * Description: 用户控制器
 * Created in 2021/1/29
 */
@Api(tags = {"用户控制器"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Resource
    private IUserService userService;

    @ApiOperation(value = "创建用户")
    @PostMapping
    public BaseResult<Void> createUser(@RequestBody @Validated UserCreateReq userCreateReq) {
//        userService.createUser(userCreateReq);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "按照用户名称模糊查询", dataType = "string", paramType = "query")
    })
    @GetMapping
    public BaseResult<PageResult<UserListResp>> getUserList(@RequestParam String search) {
        return ResultGenerator.genSuccessResult(null);
    }

    @ApiOperation(value = "获取用户详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "path", required = true)
    })
    @GetMapping("/{userId}")
    public BaseResult<UserDetailResp> getUserDetail(@PathVariable Integer userId) {
        UserDetailResp userDetailResp = userService.getUserDetail(userId);
        return ResultGenerator.genSuccessResult(userDetailResp);
    }

    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "path", required = true)
    })
    @PutMapping("/{userId}")
    public BaseResult<Void> editUserInfo(@PathVariable Integer userId) {
        return ResultGenerator.genSuccessResult(null);
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "path", required = true)
    })
    @DeleteMapping("/{userId}")
    public BaseResult<Void> deleteUser(@PathVariable Integer userId) {
        return ResultGenerator.genSuccessResult(null);
    }

}
