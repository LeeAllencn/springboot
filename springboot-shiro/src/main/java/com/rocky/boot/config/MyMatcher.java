package com.rocky.boot.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义密码匹配器
 *
 * @author Rocky
 * @date 2017-12-05
 */
public class MyMatcher extends HashedCredentialsMatcher {

    /**
     * 创建缓存的对象
     */
    private final Cache<String, AtomicInteger> passwordRetryCache;

    public MyMatcher(CacheManager cacheManager) {
        // 赋予缓存对象，此处获取的是我们在ehcache.xml文件中配置,注意getCache("")获取的是xml中的name
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // 获取用户名
        String username = (String) token.getPrincipal();
        // 获取用户登录的次数
        AtomicInteger retryCount = passwordRetryCache.get(username);
        // 如果用户未登陆过
        if (retryCount == null) {
            // 新建一个登录次数
            retryCount = new AtomicInteger(0);
            // 放入缓存中
            passwordRetryCache.put(username, retryCount);
        }
        // 如果用户登录次数超过三次（此处可根据需要自定义）
        if (retryCount.incrementAndGet() > 3) {
            // 抛出用户锁定异常类
            throw new ExcessiveAttemptsException();
        }
        // 判断用户是否可用，即是否为正确的账号密码
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches){
            // 移除缓存中用户的登录次数
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
