package com.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.CommentQueryDao;

public class CommentQueryDaoImpl extends JdbcDaoSupport implements CommentQueryDao {

	@Override
	public List<Map<String, Object>> selectRecord(int newsid) {
		// TODO Auto-generated method stub


		//用List<Map<String, Object>>来封装查询结果
		
		//定义一个String类型的变量
		String sql;
		//定义一个List集合，用来保存查询结果
		List<Map<String, Object>> mytest_list;
		
		
		sql = "select * from comment where id="+newsid+";";
		//用jdbcTemplate模板的queryForList方法
		mytest_list = this.getJdbcTemplate().queryForList(sql);
			
		return mytest_list;
	}

}
