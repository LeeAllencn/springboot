package com.rocky.boot.common.enums;

import lombok.Getter;

import java.text.MessageFormat;

/**
 * @author rocky
 * Description: 自定义结果返回码
 * Created in 2021/1/29
 */
@Getter
public enum ResultCodeEnum {

    /**
     * 错误码为字符串类型，共 5 位，分成两个部分：错误产生来源+四位数字编号
     * 错误产生来源分为 A/B/C
     * A 表示错误来源于用户，比如参数错误
     * B 表示错误来源于当前系统，往往是业务逻辑出错
     * C 表示错误来源于第三方服务，比如CDN服务出错
     */
    SUCCESS("00000", "SUCCESS", 200),

    PARAMETER_VERIFICATION_FAILED("A0400", "Parameter Verification Failed : [{0}]", 400),
    NOT_FOUND("A0404", "Not Found : [{0}]", 404),

    FILE_UPLOAD_FAILED("B0301", "File upload failed:[{0}]", 500),
    INTERNAL_SERVER_ERROR("B0500", "Internal Server Error", 500);

    /**
     * 自定义业务编码
     */
    private final String code;

    /**
     * 提示信息
     */
    private final String message;

    /**
     * http状态码
     */
    private final Integer statusCode;

    ResultCodeEnum(String code, String message, Integer statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    /**
     * 包装web提示信息
     *
     * @param args 参数
     * @return String
     */
    public String getWebMessage(Object... args) {
        if (args != null && args.length > 0) {
            return MessageFormat.format(getMessage(), args);
        }
        return getMessage();
    }
}
