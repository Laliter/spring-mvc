package com.spring.model;

public class User {
	//User具有2个属性，姓名和密码
	private String username;
	private String password;
	//生成相应的setter、getter方法
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
