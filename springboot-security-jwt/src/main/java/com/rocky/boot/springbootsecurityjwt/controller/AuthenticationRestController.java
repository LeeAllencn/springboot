package com.rocky.boot.springbootsecurityjwt.controller;

import com.rocky.boot.springbootsecurityjwt.security.JwtAuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rocky
 * @Description:
 * @Date: Created in 2019/3/19
 */
@RestController
public class AuthenticationRestController {

    @PostMapping(value = "${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) {
        return null;
    }
}
