package com.rocky.boot.api.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : rocky
 * @date : created in 2023/2/28
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "springboot.rest")
public class RestTemplateProp {

    /**
     * 连接池存活时间
     */
    private long timeToLive = 300 * 1000;

    /**
     * 最大连接数
     */
    private int maxTotal = 200;

    /**
     * 每个路由的默认最大连接数
     */
    private int defaultMaxPerRoute = 100;

    /**
     * 重试次数
     */
    private int retryCount = 0;

    /**
     * 连接超时时间/毫秒 （连接上服务器（握手成功）的时间 超出抛错：connect time out）
     */
    private int connectTimeout = 60 * 1000;

    /**
     * 数据读取超时时间（SocketTimeout）/毫秒 （服务器返回数据（response）的时间 超出抛错：read time out）
     */
    private int readTimeout = 240 * 1000;

    /**
     * 客户端从连接池中获取连接的超时时间/毫秒 （超时未拿到可用连接抛错：Timeout waiting for connection from pool）
     */
    private int connectionRequestTimeout = 30 * 1000;
}
