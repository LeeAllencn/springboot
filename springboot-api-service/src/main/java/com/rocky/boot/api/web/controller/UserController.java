package com.rocky.boot.api.web.controller;

import com.rocky.boot.api.annotation.OperationalAudit;
import com.rocky.boot.api.service.IUserService;
import com.rocky.boot.api.web.request.UserCreateReq;
import com.rocky.boot.api.web.request.UserUpdateReq;
import com.rocky.boot.api.web.response.UserDetailResp;
import com.rocky.boot.api.web.response.UserListResp;
import com.rocky.boot.common.model.BaseResult;
import com.rocky.boot.common.model.PageParam;
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
    @OperationalAudit(operate = "创建用户", resourceName = "username", paramType = "body_json", queryClass = "com.rocky.boot.api.mapper.UserMapper")
    public BaseResult<Void> createUser(@RequestBody @Validated UserCreateReq userCreateReq) {
        userService.saveUser(userCreateReq);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "按照用户名称模糊查询", dataType = "string", paramType = "query")
    })
    @GetMapping
    public BaseResult<PageResult<UserListResp>> getUserList(@RequestParam(value = "search", required = false) String search, PageParam pageParam) {
        PageResult<UserListResp> userListRespPageResult = userService.listUsers(search, pageParam);
        return ResultGenerator.genSuccessResult(userListRespPageResult);
    }

    @ApiOperation(value = "获取用户详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "path", required = true)
    })
    @GetMapping("/{userId}")
    public BaseResult<UserDetailResp> getUserDetail(@PathVariable Integer userId) {
        UserDetailResp userDetailResp = userService.getUser(userId);
        return ResultGenerator.genSuccessResult(userDetailResp);
    }

    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "path", required = true)
    })
    @PutMapping("/{userId}")
    public BaseResult<Void> editUserInfo(@PathVariable Integer userId, @RequestBody @Validated UserUpdateReq userUpdateReq) {
        userService.updateUser(userId, userUpdateReq);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "path", required = true)
    })
    @DeleteMapping("/{userId}")
    public BaseResult<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResultGenerator.genSuccessResult();
    }

}
