package com.rocky.boot.config;

import com.rocky.boot.jwt.JwtAuthenticationEntryPoint;
import com.rocky.boot.jwt.JwtAuthenticationTokenFilter;
import com.rocky.boot.security.CustomUserService;
import com.rocky.boot.service.impl.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author rocky
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{//1 需继承WebSecurityConfigurerAdapter

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Bean
	UserDetailsService customUserService(){ //2 注册自定义CustomUserService的bean
//		return new CustomUserService();
		return new JwtUserDetailsServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
		return new JwtAuthenticationTokenFilter();
	}

	/**
	 * 用户认证
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//3 添加自定义的user detail service 认证
		auth
				.userDetailsService(customUserService())
				.passwordEncoder(passwordEncoder());
		
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
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				// 开始请求权限配置
				.authorizeRequests()
				// 请求匹配/admin/**，只有拥有ROLE_ADMIN角色的用户可以访问
//                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
				// 请求匹配/user/**，拥有ROLE_ADMIN或ROLE_USER角色的用户都可访问
//                .antMatchers("/user/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
				// 请求匹配/auth/**，用户可任意访问
				.antMatchers("/user/auth/**").permitAll()
				.antMatchers("/user/registration/**").permitAll()
				// 其余所有的请求都需要认证后（登录后）才能访问
				.anyRequest().authenticated() //4 所有请求需要认证即登录后才能访问
				.and()
				.formLogin()
					.loginPage("/login")
					.failureUrl("/login?error")
					.permitAll() //5 定制登录行为，登录页面可任意访问
				.and()
				.logout().permitAll(); //6 定制注销行为，注销请求可任意访问

		// Custom JWT based security filter
		http
				.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
}
