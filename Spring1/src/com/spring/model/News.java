package com.spring.model;

import java.sql.Date;

public class News {
	int newsid;
	private String context;
	private String username;
	
	//getter¡¢setter·½·¨
	public int getNewsid() {
		return newsid;
	}
	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
