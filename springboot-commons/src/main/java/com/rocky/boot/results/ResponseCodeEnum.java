package com.rocky.boot.results;

/**
 * @author rocky
 * @Description: 状态码枚举
 * 这里的枚举信息会直接展示给用户，主要用户友好性，不需要声明那些用户看不懂的信息
 * 如果需要给开发者看错误信息，直接返回失败，再打个服务器log
 * @Date: Created in 2019/3/14
 */
public enum ResponseCodeEnum {
    /**
     * HTTP常用状态码的语义
     */
    SUCCESS(true, 200, "操作成功！"),
    FAIL(false, 400, "操作失败！"),
    UNAUTHORIZED(false, 401, "未认证！"),
    NOT_FOUND(false, 404, "资源不存在！"),
    INTERNAL_SERVER_ERROR(false, 500, "服务器内部错误！"),

    /**
     * 自定义常用状态码的语义
     */
    AUTH_USERNAME_NONE(false,20001,"请输入账号！"),
    AUTH_PASSWORD_NONE(false,20002,"请输入密码！"),
    AUTH_VERIFYCODE_NONE(false,20003,"请输入验证码！"),
    AUTH_ACCOUNT_NOTEXISTS(false,20004,"账号不存在！"),
    AUTH_CREDENTIAL_ERROR(false,20005,"账号或密码错误！"),
    AUTH_LOGIN_ERROR(false,20006,"登陆过程出现异常请尝试重新操作！"),
    AUTH_LOGIN_APPLYTOKEN_FAIL(false,20007,"申请令牌失败！"),
    AUTH_LOGIN_TOKEN_SAVEFAIL(false,20008,"存储令牌失败！"),
    AUTH_LOGIN_VERTIFYCODE_GENERATE(false,20009,"错误次数达到三次，请输入验证码！"),
    AUTH_VERTIFYCODE_ERROR(false,20010,"验证码不正确，请重试！"),
    AUTH_VERTIFYCODE_TIMEOUT(false,20011,"验证码已过期，请刷新获取新的验证码！"),
    AUTH_ACCOUNT_IS_FREEZING(false,20012,"账户被冻结，请联系管理员!");

    ResponseCodeEnum(Boolean isSuccess, Integer code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    /**
     * 操作是否成功
     */
    private Boolean isSuccess;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
