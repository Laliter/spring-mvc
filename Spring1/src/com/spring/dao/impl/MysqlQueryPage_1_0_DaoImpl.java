package com.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.MysqlQueryPage_1_0_Dao;

/*
 * SpringMVC+MySQL��ѯ��ҳ������1.0�汾��
 * ���Ʋ��� 
 * public class MysqlQueryPage_1_0_DaoImpl extends JdbcDaoSupport implements MysqlQueryPage_1_0_Dao
 */

public class MysqlQueryPage_1_0_DaoImpl extends JdbcDaoSupport implements MysqlQueryPage_1_0_Dao {
	
	//����һ��ȫ�ֱ�����������ʾÿҳ��ʾ�ļ�¼����PAGE_SIZE��������������ݿ��ѯ�Ĳ���Ҫͬ��
	private static int PAGE_SIZE = 100;

	//��List<Map<String, Object>>����װ��ѯ���
	@Override
	public List<Map<String, Object>> mysqlQueryPage_1_0() {
		//����һ��String���͵ı���
		String sql;
		//����һ��List���ϣ����������ѯ���
		List<Map<String, Object>> mytest_list;
		//����һ��Mytest����������װ��ѯ���
		
		sql = "select * from mytest order by myid limit 0,"+ PAGE_SIZE + ";";
		//��jdbcTemplateģ���queryForList����
		mytest_list = this.getJdbcTemplate().queryForList(sql);

		return mytest_list;
	}

	//����ȫ���ѯ��������ݱ��е��ܼ�¼������������͵����ݴ���controller
	@Override
	public long getCountsOfTable() {
		//����һ�����ͱ���count_of_mytest
		long count_of_mytest = 0;
		//����һ��String���͵ı���
		String sql = "select count(*) as count_of_mytest from mytest order by myid";
		//1������һ��List<Map<String,Mytest>>����mytest_list
		List<Map<String, Object>> mytest_list;
		
		mytest_list = this.getJdbcTemplate().queryForList(sql);
		
		count_of_mytest = (Long) mytest_list.get(0).get("count_of_mytest");
		
		return count_of_mytest;
	}

	/*
	 * ����id��ѯ���ݿ⣨���ID�ǵ�ǰҳ��current_page��������List<Map<String, Object>>(non-Javadoc)
	 * @see com.spring.dao.MysqlQueryPageDao#mysqlQueryPage_1_3()
	 */
	@Override
	public List<Map<String, Object>> mysqlQueryPage_1_3(int start_point) {
		//����һ��String���͵ı���
		String sql;
		//����һ��List���ϣ����������ѯ���
		List<Map<String, Object>> mytest_list;
		//����һ��Mytest����������װ��ѯ���
		
		sql = "select * from mytest order by myid limit " + start_point + ","+ PAGE_SIZE + ";";
		//��jdbcTemplateģ���queryForList����
		mytest_list = this.getJdbcTemplate().queryForList(sql);

		return mytest_list;
	}

}
