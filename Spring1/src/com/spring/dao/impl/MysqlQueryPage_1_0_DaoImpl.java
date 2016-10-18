package com.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.MysqlQueryPage_1_0_Dao;

/*
 * SpringMVC+MySQL查询分页技术（1.0版本）
 * 控制层类 
 * public class MysqlQueryPage_1_0_DaoImpl extends JdbcDaoSupport implements MysqlQueryPage_1_0_Dao
 */

public class MysqlQueryPage_1_0_DaoImpl extends JdbcDaoSupport implements MysqlQueryPage_1_0_Dao {
	
	//定义一个全局变量，用来表示每页显示的记录数，PAGE_SIZE，这个参数和数据库查询的参数要同步
	private static int PAGE_SIZE = 100;

	//用List<Map<String, Object>>来封装查询结果
	@Override
	public List<Map<String, Object>> mysqlQueryPage_1_0() {
		//定义一个String类型的变量
		String sql;
		//定义一个List集合，用来保存查询结果
		List<Map<String, Object>> mytest_list;
		//定义一个Mytest对象，用来封装查询结果
		
		sql = "select * from mytest order by myid limit 0,"+ PAGE_SIZE + ";";
		//用jdbcTemplate模板的queryForList方法
		mytest_list = this.getJdbcTemplate().queryForList(sql);

		return mytest_list;
	}

	//进行全表查询，获得数据表中的总记录数，把这个整型的数据传到controller
	@Override
	public long getCountsOfTable() {
		//定义一个整型变量count_of_mytest
		long count_of_mytest = 0;
		//定义一个String类型的变量
		String sql = "select count(*) as count_of_mytest from mytest order by myid";
		//1，生成一个List<Map<String,Mytest>>对象mytest_list
		List<Map<String, Object>> mytest_list;
		
		mytest_list = this.getJdbcTemplate().queryForList(sql);
		
		count_of_mytest = (Long) mytest_list.get(0).get("count_of_mytest");
		
		return count_of_mytest;
	}

	/*
	 * 根据id查询数据库（这个ID是当前页码current_page），返回List<Map<String, Object>>(non-Javadoc)
	 * @see com.spring.dao.MysqlQueryPageDao#mysqlQueryPage_1_3()
	 */
	@Override
	public List<Map<String, Object>> mysqlQueryPage_1_3(int start_point) {
		//定义一个String类型的变量
		String sql;
		//定义一个List集合，用来保存查询结果
		List<Map<String, Object>> mytest_list;
		//定义一个Mytest对象，用来封装查询结果
		
		sql = "select * from mytest order by myid limit " + start_point + ","+ PAGE_SIZE + ";";
		//用jdbcTemplate模板的queryForList方法
		mytest_list = this.getJdbcTemplate().queryForList(sql);

		return mytest_list;
	}

}
