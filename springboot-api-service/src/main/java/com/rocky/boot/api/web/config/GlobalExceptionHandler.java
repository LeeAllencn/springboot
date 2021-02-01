package com.rocky.boot.api.web.config;

import cn.hutool.json.JSONUtil;
import com.rocky.boot.common.constant.KeyConstants;
import com.rocky.boot.common.model.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
        BaseResult<Void> baseResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            // 对象参数校验异常统一处理
            List<ObjectError> allErrors = ((BindException) e).getBindingResult().getAllErrors();
            List<String> allErrorMessages = new ArrayList<>();
            for (ObjectError error : allErrors) {

            }
        } else {

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
