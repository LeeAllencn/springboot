package com.rocky.boot.routing.controller;

import com.rocky.boot.routing.service.RoutingDelegate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : rocky
 * @date : created in 2021/5/8
 */

@RestController
@RequestMapping("/delegate")
public class RoutingController {

    /**
     * 源请求前缀
     */
    private final static String DELEGATE_PREFIX = "/delegate";

    /**
     * 路由请求前缀
     */
    private final static String ROUTE_PREFIX = "/api/v1";

    @Resource
    private RoutingDelegate routingDelegate;

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> forward(HttpServletRequest request, HttpServletResponse response) {
        return routingDelegate.redirect(request, response, DELEGATE_PREFIX, ROUTE_PREFIX);
    }

}
