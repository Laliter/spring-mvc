package com.spring.dao;

public interface MysqlQueryCriteriaDao {
	//根据用户输入的myid字段，查询数据库
	public boolean selectRecordByName(int myid);
	//根据用户输入的mydata字段，查询数据库
	public boolean selectRecordByMyname(int myid,String myname);
}
