package com.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.MysqlQueryCriteriaDao;
import com.spring.model.Mytest;

public class MysqlQueryCriteriaDaoImpl extends JdbcDaoSupport implements MysqlQueryCriteriaDao {

	@Override
	public boolean selectRecordByName(int myid) {
		//定义一个boolean类型的flag
		boolean flag = false;
		//定义一个final类型的mytest对象，用来接收查询结果
		final Mytest mytest = new Mytest();
		//SQL语句
		String sql = "select myid,mydata,myname from mytest where myid='" + myid +"'";
		
		this.getJdbcTemplate().query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException{
				//封装查询结果到mytest对象中
				mytest.setMyid(rs.getInt("myid"));
				mytest.setMydata(rs.getString("mydata"));
				mytest.setMyname(rs.getString("myname"));
			}
		});
		//判断，如果结果集不为空，返回true；否则，返回false
		if(mytest.getMyid() > 0){
			System.out.println("查询出来的数据：" + mytest.getMyid());
			System.out.println("查询出来的数据：" + mytest.getMydata());
			System.out.println("查询出来的数据：" + mytest.getMyname());
			flag = true;
		}
		else{
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean selectRecordByMyname(int myid,String myname) {
		//定义一个boolean类型的flag
		boolean flag = false;
		//定义一个final类型的mytest对象，用来接收查询结果
		final Mytest mytest = new Mytest();
		//SQL语句
		String sql = "select myid,mydata,myname from mytest where myid='" + myid +"' and myname='" + myname + "'";
		
		this.getJdbcTemplate().query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException{
				//封装查询结果到mytest对象中
				mytest.setMyid(rs.getInt("myid"));
				mytest.setMydata(rs.getString("mydata"));
				mytest.setMyname(rs.getString("myname"));
			}
		});
		//判断，如果结果集不为空，返回true；否则，返回false
		if(mytest.getMyid() > 0){
			System.out.println("查询出来的数据：" + mytest.getMyid());
			System.out.println("查询出来的数据：" + mytest.getMydata());
			System.out.println("查询出来的数据：" + mytest.getMyname());
			flag = true;
		}
		else{
			flag = false;
		}
		return flag;
	}

}
