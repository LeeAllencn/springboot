package com.rocky.boot.repository;

import com.rocky.boot.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SysUserRepository extends JpaRepository<SysUser, Long>{
	
	SysUser findByUsername(String username);

}
