package com.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.CommentInsertDao;


public class CommentInsertDaoImpl extends JdbcDaoSupport implements CommentInsertDao  {
	
	public  List<Map<String, Object>> insertRecord(int nid,String username,String context)
	{
		String sql;
		
	
		
		List<Map<String, Object>> mytest_list;
		
		sql = "insert into comment values('"+context+"','"+username+"',"+(nid)+");";
		this.getJdbcTemplate().execute(sql);
		sql = "select * from comment where id="+nid+";";
		//用jdbcTemplate模板的queryForList方法
		mytest_list = this.getJdbcTemplate().queryForList(sql);
		return mytest_list;
	}

	
	
}
