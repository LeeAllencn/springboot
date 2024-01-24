package com.rocky.boot.repository;

import com.rocky.boot.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author rocky
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long>{

	/**
	 * 通过用户名查询
	 * @param username 用户名
	 * @return SysUser
	 */
	SysUser findByUsername(String username);

}
