package com.spring.dao;

import java.util.List;
import java.util.Map;

/*
 * SpringMVC+MySQL查询分页技术（1.0版本）
 * 控制层类 public interface MysqlQueryPage_1_0_Dao
 */
public interface MysqlQueryPage_1_0_Dao {
	//不传递参数，直接查询出myid从1到100的记录
	public List<Map<String, Object>> mysqlQueryPage_1_0();
	//进行全表查询，获得数据表中的总记录数，把这个long类型的数据传到controller
	public long getCountsOfTable();
	//根据id查询数据库（这个ID是当前页码current_page），返回List<Map<String, Object>>
	public List<Map<String, Object>> mysqlQueryPage_1_3(int start_point);
	
}
