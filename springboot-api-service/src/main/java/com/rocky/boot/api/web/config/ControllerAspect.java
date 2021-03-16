package com.rocky.boot.api.web.config;

import cn.hutool.json.JSONUtil;
import com.rocky.boot.common.constant.KeyConstants;
import com.rocky.boot.common.model.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author rocky
 * Description: Controller层切面
 * Created in 2021/2/2
 */
@Aspect
@Slf4j
@Component
public class ControllerAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Resource
    private HttpServletRequest request;

    /**
     * 定义一个切面
     */
    @Pointcut("execution(public * com.rocky.boot.api.web.controller..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * controller执行之前
     *
     * @param joinPoint JoinPoint
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // TODO 获取用户信息，临时使用admin
        String username = "admin";
        log.info("用户:{} 类型:{} URL:{} 参数:{} 开始执行...", username, request.getMethod(), request.getRequestURI(), JSONUtil.toJsonStr(joinPoint.getArgs()));
    }

    /**
     * controller执行之后
     *
     * @param controllerResult Object
     */
    @AfterReturning(pointcut = "controllerAspect()", returning = "controllerResult")
    public void doAfterReturning(Object controllerResult) {
        if (controllerResult instanceof BaseResult) {
            BaseResult baseResult = (BaseResult) controllerResult;
            baseResult.setRequestId(MDC.get(KeyConstants.TRACE_ID));
        }
        log.info("类型:{} URL:{} 执行结束，总耗时{}毫秒", request.getMethod(), request.getRequestURI(), System.currentTimeMillis() - startTime.get());
        startTime.remove();
    }
}
