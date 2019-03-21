package com.rocky.boot.controller;

import com.rocky.boot.jwt.JwtAuthenticationRequest;
import com.rocky.boot.jwt.JwtAuthenticationResponse;
import com.rocky.boot.jwt.JwtTokenUtil;
import com.rocky.boot.jwt.JwtUser;
import com.rocky.boot.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rocky
 * Description:
 * Created in 2019/3/21
 */
@RestController
@RequestMapping("/user")
public class AuthenticationRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    /**
     * 用户登录
     * @param jwtAuthenticationRequest
     * @param device
     * @return
     */
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest, Device device) {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtAuthenticationRequest.getUsername(), jwtAuthenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    /*@GetMapping("/refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(null));
        }
    }*/

    @GetMapping
    public ResponseEntity<?> getCurrentUserInfo(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        SysUser user = (SysUser) userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
