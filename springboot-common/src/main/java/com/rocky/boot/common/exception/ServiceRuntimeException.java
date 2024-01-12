package com.rocky.boot.common.exception;

/**
 * @author rocky
 * Description: 自身服务运行异常
 * Created in 2021/2/9
 */
public class ServiceRuntimeException extends RuntimeException {

    /**
     * 业务编码
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;


}
