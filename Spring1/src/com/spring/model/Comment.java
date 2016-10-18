package com.spring.model;

import java.sql.Date;

public class Comment {
	private String text;
	private String hisname;
	private int id;
	 public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHisname() {
		return hisname;
	}
	public void setHisname(String hisname) {
		this.hisname = hisname;
	}
	
	
}
