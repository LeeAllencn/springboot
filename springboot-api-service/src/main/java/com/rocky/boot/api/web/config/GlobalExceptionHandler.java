package com.rocky.boot.api.web.config;

import cn.hutool.json.JSONUtil;
import com.rocky.boot.common.constant.KeyConstants;
import com.rocky.boot.common.enums.ResultCodeEnum;
import com.rocky.boot.common.model.BaseResult;
import com.rocky.boot.common.model.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author rocky
 * Description: 全局异常处理器
 * Created in 2021/1/31
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Throwable.class})
    public void exceptionHandler(Throwable e, HttpServletResponse response) {
        log.error("Controller execute error! message = {}", e.getMessage(), e);
        BaseResult<Void> baseResult;
        if (e instanceof BindException) {
            // 绑定参数校验异常统一处理
            BindException bindException = (BindException) e;
            List<FieldError> fieldErrors = bindException.getFieldErrors();
            StringBuilder builder = new StringBuilder();
            fieldErrors.forEach(item -> builder.append(item.getField()).append(":").append(item.getDefaultMessage()).append(";\n"));
            baseResult = ResultGenerator.getFailResult(ResultCodeEnum.PARAMETER_VERIFICATION_FAILED, builder.toString());
            response.setStatus(ResultCodeEnum.PARAMETER_VERIFICATION_FAILED.getStatusCode());
        } else if (e instanceof NoHandlerFoundException) {
            // 404 异常处理
            NoHandlerFoundException noHandlerFoundException = (NoHandlerFoundException) e;
            baseResult = ResultGenerator.getFailResult(ResultCodeEnum.NOT_FOUND, noHandlerFoundException.getMessage());
            response.setStatus(ResultCodeEnum.NOT_FOUND.getStatusCode());
        } else {
            // 未知异常统一处理
            baseResult = ResultGenerator.getFailResult(ResultCodeEnum.INTERNAL_SERVER_ERROR);
            response.setStatus(ResultCodeEnum.INTERNAL_SERVER_ERROR.getStatusCode());
        }
        String traceId = MDC.get(KeyConstants.TRACE_ID);
        baseResult.setRequestId(traceId);
        try {
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSONUtil.toJsonStr(baseResult));
        } catch (IOException ioe) {
            log.error("Unhandled exception : ", ioe);
        }
    }
}
