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
		//����һ��boolean���͵�flag
		boolean flag = false;
		//����һ��final���͵�mytest�����������ղ�ѯ���
		final Mytest mytest = new Mytest();
		//SQL���
		String sql = "select myid,mydata,myname from mytest where myid='" + myid +"'";
		
		this.getJdbcTemplate().query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException{
				//��װ��ѯ�����mytest������
				mytest.setMyid(rs.getInt("myid"));
				mytest.setMydata(rs.getString("mydata"));
				mytest.setMyname(rs.getString("myname"));
			}
		});
		//�жϣ�����������Ϊ�գ�����true�����򣬷���false
		if(mytest.getMyid() > 0){
			System.out.println("��ѯ���������ݣ�" + mytest.getMyid());
			System.out.println("��ѯ���������ݣ�" + mytest.getMydata());
			System.out.println("��ѯ���������ݣ�" + mytest.getMyname());
			flag = true;
		}
		else{
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean selectRecordByMyname(int myid,String myname) {
		//����һ��boolean���͵�flag
		boolean flag = false;
		//����һ��final���͵�mytest�����������ղ�ѯ���
		final Mytest mytest = new Mytest();
		//SQL���
		String sql = "select myid,mydata,myname from mytest where myid='" + myid +"' and myname='" + myname + "'";
		
		this.getJdbcTemplate().query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException{
				//��װ��ѯ�����mytest������
				mytest.setMyid(rs.getInt("myid"));
				mytest.setMydata(rs.getString("mydata"));
				mytest.setMyname(rs.getString("myname"));
			}
		});
		//�жϣ�����������Ϊ�գ�����true�����򣬷���false
		if(mytest.getMyid() > 0){
			System.out.println("��ѯ���������ݣ�" + mytest.getMyid());
			System.out.println("��ѯ���������ݣ�" + mytest.getMydata());
			System.out.println("��ѯ���������ݣ�" + mytest.getMyname());
			flag = true;
		}
		else{
			flag = false;
		}
		return flag;
	}

}
