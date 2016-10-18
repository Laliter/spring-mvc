package com.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.MysqlQueryPage_1_1_Dao;

/*
 * SpringMVC+MySQL查询分页技术（1.1版本）
 * Dao层接口实现类  public class MysqlQueryPage_1_1_DaoImpl implements MysqlQueryPage_1_1_Dao
 */

public class MysqlQueryPage_1_1_DaoImpl extends JdbcDaoSupport implements MysqlQueryPage_1_1_Dao {
	
	//定义一个全局变量，用来表示数据库查询出来的总记录数，TOTAL_RECORDS，这个参数和数据库查询的参数要同步
	private static long TOTAL_RECORDS = 0;
	
	//定义一个全局变量，用来表示每页显示的记录数，PAGE_SIZE，这个参数和controller层的的参数要同步
	private static int PAGE_SIZE = 100;
	
	//定义一个全局变量，用来表示数据库查询的起点位置，也就是游标的起始位置
	public static int START_POINT = 0;
	
	//定义一个全局变量，用来表示SQL查询语句
	private static String SQL = null;

	@Override
	public List<Map<String, Object>> mysqlQueryPage_1_0() {
		//定义一个List集合，用来保存查询结果
		List<Map<String, Object>> mytest_list;

		SQL = "select * from mytest order by myid limit 0,"+ PAGE_SIZE + ";";
		
		mytest_list = this.getJdbcTemplate().queryForList(SQL);
		
		return mytest_list;
	}

	//进行全表查询，获得数据表中的总记录数，把这个整型的数据传到controller
	@Override
	public long getCountsOfTable() {

		SQL = "select count(*) as TOTAL_RECORDS from mytest order by myid";

		List<Map<String, Object>> mytest_list;
		
		mytest_list = this.getJdbcTemplate().queryForList(SQL);
		
		TOTAL_RECORDS = (Long) mytest_list.get(0).get("TOTAL_RECORDS");
		
		return TOTAL_RECORDS;
	}

	/*
	 * 根据id查询数据库（这个ID是当前页码current_page），返回List<Map<String, Object>>(non-Javadoc)
	 * @see com.spring.dao.MysqlQueryPageDao#mysqlQueryPage_1_3()
	 */
	@Override
	public List<Map<String, Object>> mysqlQueryPage_1_3(int START_POINT) {
		//定义一个List集合，用来保存查询结果
		List<Map<String, Object>> mytest_list;
		
		SQL = "select * from mytest order by myid limit " + START_POINT + ","+ PAGE_SIZE + ";";
		
		mytest_list = this.getJdbcTemplate().queryForList(SQL);
		
		return mytest_list;
	}
}
