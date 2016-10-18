package com.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.CommentQueryDao;
import com.spring.dao.ContextInsertDao;

public class ContextInsertDaoImpl extends JdbcDaoSupport implements ContextInsertDao  {
	public  List<Map<String, Object>> insertRecord(String username,String context){
		String sql;
		int nid;
	
		System.out.println("contextinsertDao");
		List<Map<String, Object>> mytest_list;
		sql = "select max(newsid) from news;";
		nid = this.getJdbcTemplate().queryForInt(sql);
		sql = "insert into news values('"+username+"','"+context+"',"+(nid+1)+");";
		this.getJdbcTemplate().execute(sql);
		sql = "select * from news order by newsid;";
		//用jdbcTemplate模板的queryForList方法
		mytest_list = this.getJdbcTemplate().queryForList(sql);
		return mytest_list;
	}
}
