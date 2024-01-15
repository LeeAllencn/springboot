package com.rocky.boot.api.annotation.handler;

import com.alibaba.fastjson.JSONObject;
import com.rocky.boot.api.annotation.OperationalAudit;
import com.rocky.boot.common.constant.ParamTypeConstants;
import com.rocky.boot.common.model.BaseResult;
import com.rocky.boot.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author : rocky
 * @date : created in 2021/9/5
 */
@Slf4j
@Aspect
@Component
public class OperationalAuditHandler {

    @Resource
    private HttpServletRequest request;

    /**
     * 定义一个注解方式的切面
     */
    @Pointcut("@annotation(com.rocky.boot.api.annotation.OperationalAudit)")
    public void annotationPointcut() {}

    @AfterReturning(value = "annotationPointcut() && @annotation(operationalAudit)", returning = "result")
    public void afterReturning(OperationalAudit operationalAudit, BaseResult<?> result) throws IOException {
        if (operationalAudit.paramType().equalsIgnoreCase(ParamTypeConstants.BODY_JSON)) {
            // 从json body获取参数
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = null;
            String content;

            try {
                bufferedReader =  request.getReader() ;
                char[] charBuffer = new char[128];
                int bytesRead;
                while ( (bytesRead = bufferedReader.read(charBuffer)) != -1 ) {
                    sb.append(charBuffer, 0, bytesRead);
                }

            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }

            content = sb.toString();

            JSONObject jsonObject = JsonUtil.json2Obj(content);
            String resourceName = (String) jsonObject.get(operationalAudit.resourceName());
            log.info("资源名称为:{}", resourceName);

        }

        log.info(operationalAudit.toString());
        log.info(result.toString());
    }
}
