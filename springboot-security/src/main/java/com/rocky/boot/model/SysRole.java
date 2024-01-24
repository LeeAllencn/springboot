package com.rocky.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author rocky
 */
@Entity
public class SysRole {
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 角色名称
	 */
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
