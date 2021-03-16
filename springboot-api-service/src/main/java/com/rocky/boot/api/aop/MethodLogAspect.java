package com.rocky.boot.api.aop;

import com.rocky.boot.api.web.response.UserDetailResp;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author rocky
 * Description: 方法日志注解切面
 * Created in 2021/3/11
 */
@Slf4j
@Aspect
@Component
public class MethodLogAspect {

    /**
     * 定义一个注解方式的切面
     */
    @Pointcut("@annotation(com.rocky.boot.api.aop.MethodLogRecord)")
    public void annotationPointCut() {
    }

    /**
     * 方法执行之前执行，也就是在spring管理的对象的方法前执行前执行此方法
     *
     * @param point JoinPoint
     * @param userId 用户id
     */
    @Before("annotationPointCut() && args(userId,..)")
    public void beforeMethod(JoinPoint point, Integer userId) {
        //切入点是加注释的那个方法
        System.out.println("Method : " + point.getSignature().getName());
        //方法参数集合
        Object[] s = point.getArgs();
        System.out.println("Method args : " + s[0].toString());
        System.out.println("start begin .........." + "userId :" + userId);
    }

    /**
     * AfterReturning 是目标方法执行且返回后执行， rvt 方法的返回值
     *
     * @param rvt 返回结果
     */
    @AfterReturning(value = "annotationPointCut()", returning = "rvt")
    public void afterMethod(Object rvt) {
        //此处获取返回值为null，因为 @Around 的 advice 执行方法后没有把方法的返回值 返回，所以在AfterReturning 中获取的返回值为null
        System.out.println("start AfterReturning ....... and return : " + rvt);
    }

    /**
     * @param pjp ProceedingJoinPoint
     * @param log log
     * @throws Throwable 异常
     */
    @Around("annotationPointCut()&& @annotation(log)")
    public Object aroundMethod(ProceedingJoinPoint pjp, MethodLogRecord log) throws Throwable {
        System.out.println("method around start ... LogRecord  key : " + log.key());
        //获取目标对象的返回值,打印返回值，但是我不return ，AfterReturning 拿不到 返回值
        UserDetailResp resp = (UserDetailResp) pjp.proceed();

        System.out.println("method around end ... and method return :" + resp);
        return resp;
    }
}
