package com.rocky.boot.security;

import com.rocky.boot.model.SysUser;
import com.rocky.boot.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author rocky
 * 1 自定义需要实现UserDetailsService接口
 */
public class CustomUserService implements UserDetailsService {
	@Autowired
	SysUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) { //2 重写loadUserByUsername方法获得用户
		
		SysUser user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		//3 直接返回给Spring Security使用
		return user;
	}

}
