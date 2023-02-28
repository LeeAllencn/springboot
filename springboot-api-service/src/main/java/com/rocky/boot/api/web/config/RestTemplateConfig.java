package com.rocky.boot.api.web.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author : rocky
 * @date : created in 2023/2/28
 */
@Configuration
public class RestTemplateConfig {

    @Resource
    private RestTemplateProp restTemplateProp;

    @Bean
    public RestTemplate restTemplate() {
        // 连接池配置
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(restTemplateProp.getTimeToLive(), TimeUnit.MILLISECONDS);
        manager.setMaxTotal(restTemplateProp.getMaxTotal());
        manager.setDefaultMaxPerRoute(restTemplateProp.getDefaultMaxPerRoute());

        // httpClient配置
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create()
                .setConnectionManager(manager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setRetryHandler(new DefaultHttpRequestRetryHandler(restTemplateProp.getRetryCount(), false));
        CloseableHttpClient httpClient = httpClientBuilder.build();

        // clientHttpRequestFactory配置
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        clientHttpRequestFactory.setConnectTimeout(restTemplateProp.getConnectTimeout());
        clientHttpRequestFactory.setReadTimeout(restTemplateProp.getReadTimeout());
        clientHttpRequestFactory.setConnectionRequestTimeout(restTemplateProp.getConnectionRequestTimeout());

        // restTemplate配置
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        return restTemplate;
    }
}
