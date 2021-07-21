package com.rocky.boot.external.client.sample.factory;

import com.rocky.boot.common.client.OkHttpClientFactory;
import com.rocky.boot.external.client.sample.service.SampleClientService;
import com.rocky.boot.external.client.sample.service.impl.SampleClientServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.WeakHashMap;

/**
 * @author : rocky
 * @date : created in 2021/7/1
 */
@Component
public class SampleClientServiceFactory {

    private static final WeakHashMap<String, Object> CACHE_MAP = new WeakHashMap<>();

    @Resource
    private OkHttpClientFactory okHttpClientFactory;

    /**
     * 创建Sample客户端服务
     * @param baseUrl 动态参数：Sample服务的基础url
     * @param token 动态参数：调用Sample服务的token
     * @return SampleClientService
     */
    public SampleClientService createSampleClientService(String baseUrl, String token) {
        String key = (baseUrl+token).intern();
        Object service = CACHE_MAP.get(key);
        if (service == null) {
            synchronized (key) {
                if (service == null) {
                    SampleClientServiceImpl sampleClientService = new SampleClientServiceImpl(okHttpClientFactory, baseUrl, token);
                    CACHE_MAP.put(key, sampleClientService);
                    return sampleClientService;
                }
                return (SampleClientService) service;
            }
        }
        return (SampleClientService) service;
    }
}
