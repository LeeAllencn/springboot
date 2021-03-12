package com.rocky.boot.common.client;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author rocky
 * Description: OkHttpClient配置类
 * Created in 2021/3/12
 */
@Configuration
public class OkHttpClientConfig {

    @Resource
    private OkHttpConf okHttpConf;

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(okHttpConf.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(okHttpConf.getReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(okHttpConf.getWriteTimeout(), TimeUnit.MILLISECONDS)
                .connectionPool(new ConnectionPool(okHttpConf.getMaxIdleConnections(), okHttpConf.getKeepAliveDuration(), TimeUnit.MINUTES));
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }
}
