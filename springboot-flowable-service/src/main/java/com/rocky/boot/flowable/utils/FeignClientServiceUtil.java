package com.rocky.boot.flowable.utils;

import com.rocky.boot.flowable.config.FlowableServiceProp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.WeakHashMap;

/**
 * @author : rocky
 * @date : created in 2023/4/18
 */
@Component
public class FeignClientServiceUtil {

    @Resource
    private FlowableServiceProp config;

    private String configKey;

    private WeakHashMap<String, Object> feignCache = new WeakHashMap<>();

    public <T> T getRemoteService(String serviceName, Class<T> clazz) {
        String url = config.getServices().get(serviceName);
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("未找到" + serviceName + "服务对应的请求地址");
        }
        String key = serviceName + ":" + clazz.getSimpleName();
        String newConfigKey = config.getServerReadTimeOut() + "" + config.getServerConnectTimeout();
        if (!StringUtils.equalsIgnoreCase(newConfigKey, configKey)) {
            feignCache.clear();
            configKey = newConfigKey;
        }
        T service = (T) feignCache.get(key);
        if (service != null) {
            return service;
        }
        String intern = key.intern();
        synchronized (intern) {
            service = (T) feignCache.get(key);
            if (service == null) {
                service = FeignClientUtil.getRemoteService(url, clazz, config.getServerReadTimeOut(), config.getServerConnectTimeout());
                feignCache.put(key, service);
            }
        }
        return service;
    }
}
