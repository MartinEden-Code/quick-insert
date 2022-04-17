package com.amg.domain;

import java.util.Date;

/**
 * (User)实体类
 *
 * @author Amg
 * @since 2021-11-17 14:37:34
 */
public class User {
	
	/**
	 * 用户主键id，自增
	 */
	private Long id;
	/**
	 * 用户名称
	 */
	private String username;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	public User() {
	}
	
	public User(String username ,Date createTime) {
		this.username = username;
		this.createTime = createTime;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
