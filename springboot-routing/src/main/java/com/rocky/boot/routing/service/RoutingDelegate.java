package com.rocky.boot.routing.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : rocky
 * @date : created in 2021/5/12
 */

public interface RoutingDelegate {

    /**
     * 请求转发
     * @param request 请求
     * @param response 响应
     * @param delegatePrefix 源请求前缀
     * @param routePrefix 路由请求前缀
     * @return ResponseEntity<?>
     */
    ResponseEntity<?> redirect(HttpServletRequest request, HttpServletResponse response, String delegatePrefix, String routePrefix);
}
