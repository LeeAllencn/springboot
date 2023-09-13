package com.rocky.boot.flowable.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author : rocky
 * @date : created in 2023/4/18
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "flowable")
public class FlowableServiceProp {

    /**
     * 服务名称和地址的对应关系
     */
    private Map<String, String> services;

    /**
     * 轮询20次，每次间隔30秒
     */
    private String repeat = "R20/PT30S";

    private String processEventRetryRepeat = "R3/PT10M";

    private String processTimeoutRepeat = "P1D";

    private long serverReadTimeOut = 600;

    private long serverConnectTimeout = 600;
}
