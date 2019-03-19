package com.rocky.boot.springbootsecurityjwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author rocky
 * @Description:
 * @Date: Created in 2019/3/19
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 用户认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    /**
     * 请求授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭默认打开的跨域保护
                .csrf().disable()
                // 开始请求权限配置
                .authorizeRequests()
                // 请求匹配/admin/**，只有拥有ROLE_ADMIN角色的用户可以访问
                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
                // 请求匹配/user/**，拥有ROLE_ADMIN或ROLE_USER角色的用户都可访问
                .antMatchers("/user/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
                // 请求匹配/auth/**，用户可任意访问
                .antMatchers("/auth/**").permitAll()
                // 其余所有的请求都需要认证后（登录后）才能访问
                .anyRequest().authenticated();
    }
}
