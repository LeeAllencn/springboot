package com.rocky.boot.common.client;

import lombok.Data;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/12
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "springboot.okhttp")
public class OkHttpConf {

    /**
     * OkHttp3 默认值
     */
    private int connectTimeout = 10000;
    private int readTimeout = 10000;
    private int writeTimeout = 10000;
    private int maxIdleConnections = 5;
    private long keepAliveDuration = 5L;
    private HttpLoggingInterceptor.Level logLevel = NONE;

}
