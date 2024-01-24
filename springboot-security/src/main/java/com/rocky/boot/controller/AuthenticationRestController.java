package com.rocky.boot.controller;

import com.rocky.boot.jwt.JwtAuthenticationRequest;
import com.rocky.boot.jwt.JwtAuthenticationResponse;
import com.rocky.boot.jwt.JwtTokenUtil;
import com.rocky.boot.jwt.JwtUser;
import com.rocky.boot.model.User;
import com.rocky.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author rocky
 * Description:
 * Created in 2019/3/21
 */
@RestController
@RequestMapping("/user")
public class AuthenticationRestController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private IUserService userService;

    @Value("${jwt.header}")
    private String tokenHeader;

    /**
     * 用户注册
     * @param user user
     * @return ResponseEntity
     */
    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@RequestBody User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword().trim());
        user.setPassword(encodePassword);
        userService.addUser(user);
        return ResponseEntity.ok("注册用户成功!");
    }

    /**
     * 用户登录
     * @param jwtAuthenticationRequest request
     * @param device device
     * @return ResponseEntity
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

    /**
     * 刷新token
     * @param request request
     * @return ResponseEntity
     */
    @GetMapping("/refresh")
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
    }

    /**
     * 获取用户信息
     * @param request request
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> getCurrentUserInfoFromToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
