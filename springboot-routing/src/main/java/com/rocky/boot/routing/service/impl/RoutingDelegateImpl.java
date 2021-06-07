package com.rocky.boot.routing.service.impl;

import com.rocky.boot.routing.service.RoutingDelegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

/**
 * @author : rocky
 * @date : created in 2021/5/12
 */
@Slf4j
@Service
public class RoutingDelegateImpl implements RoutingDelegate {

    @Value("${api.service.host}")
    private String apiServiceHost;

    @Override
    public ResponseEntity<?> redirect(HttpServletRequest request, HttpServletResponse response, String delegatePrefix, String routePrefix) {
        // 构建转发的uri
        String redirectUri = buildRedirectUri(request, delegatePrefix, routePrefix);

        // 构建转发的url
        String redirectUrl = apiServiceHost + redirectUri;

        // 构建请求
        RequestEntity<?> requestEntity = null;
        try {
            requestEntity = buildRequestEntity(request, redirectUrl);
        } catch (Exception e) {
            log.error("构建请求异常：", e);
        }

        // 发送请求
        return route(requestEntity);
    }

    private String buildRedirectUri(HttpServletRequest request, String delegatePrefix, String routePrefix) {
        String queryString = request.getQueryString();
        return request.getRequestURI().replace(delegatePrefix, routePrefix) + (queryString != null ? "?" + queryString : "");
    }

    private RequestEntity<?> buildRequestEntity(HttpServletRequest request, String redirectUrl) throws IOException, URISyntaxException {
        String method = request.getMethod();
        HttpMethod httpMethod = HttpMethod.resolve(method);
        MultiValueMap<String, String> headers = parseRequestHeader(request);
        byte[] body = parseRequestBody(request);
        return new RequestEntity<>(body, headers, httpMethod, new URI(redirectUrl));
    }

    private MultiValueMap<String, String> parseRequestHeader(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            for (String headerValue : headerValues) {
                headers.add(headerName, headerValue);
            }
        }
        return headers;
    }

    private byte[] parseRequestBody(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }

    private ResponseEntity<?> route(RequestEntity<?> requestEntity) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(requestEntity, String.class);
    }
}
