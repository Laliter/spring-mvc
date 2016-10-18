package com.spring.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.RegDao;

public class RegDaoImpl extends JdbcDaoSupport implements RegDao {
	
	@Override
	public void reg(String username, String password) {
		// TODO Auto-generated method stub
		String sql;
		sql = "insert into user values('"+username+"','"+password+"');";
		this.getJdbcTemplate().execute(sql);
	}
	
}
