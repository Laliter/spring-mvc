package com.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.MysqlQueryPage_1_1_Dao;

/*
 * SpringMVC+MySQL��ѯ��ҳ������1.1�汾��
 * Dao��ӿ�ʵ����  public class MysqlQueryPage_1_1_DaoImpl implements MysqlQueryPage_1_1_Dao
 */

public class MysqlQueryPage_1_1_DaoImpl extends JdbcDaoSupport implements MysqlQueryPage_1_1_Dao {
	
	//����һ��ȫ�ֱ�����������ʾ���ݿ��ѯ�������ܼ�¼����TOTAL_RECORDS��������������ݿ��ѯ�Ĳ���Ҫͬ��
	private static long TOTAL_RECORDS = 0;
	
	//����һ��ȫ�ֱ�����������ʾÿҳ��ʾ�ļ�¼����PAGE_SIZE�����������controller��ĵĲ���Ҫͬ��
	private static int PAGE_SIZE = 100;
	
	//����һ��ȫ�ֱ�����������ʾ���ݿ��ѯ�����λ�ã�Ҳ�����α����ʼλ��
	public static int START_POINT = 0;
	
	//����һ��ȫ�ֱ�����������ʾSQL��ѯ���
	private static String SQL = null;

	@Override
	public List<Map<String, Object>> mysqlQueryPage_1_0() {
		//����һ��List���ϣ����������ѯ���
		List<Map<String, Object>> mytest_list;

		SQL = "select * from mytest order by myid limit 0,"+ PAGE_SIZE + ";";
		
		mytest_list = this.getJdbcTemplate().queryForList(SQL);
		
		return mytest_list;
	}

	//����ȫ���ѯ��������ݱ��е��ܼ�¼������������͵����ݴ���controller
	@Override
	public long getCountsOfTable() {

		SQL = "select count(*) as TOTAL_RECORDS from mytest order by myid";

		List<Map<String, Object>> mytest_list;
		
		mytest_list = this.getJdbcTemplate().queryForList(SQL);
		
		TOTAL_RECORDS = (Long) mytest_list.get(0).get("TOTAL_RECORDS");
		
		return TOTAL_RECORDS;
	}

	/*
	 * ����id��ѯ���ݿ⣨���ID�ǵ�ǰҳ��current_page��������List<Map<String, Object>>(non-Javadoc)
	 * @see com.spring.dao.MysqlQueryPageDao#mysqlQueryPage_1_3()
	 */
	@Override
	public List<Map<String, Object>> mysqlQueryPage_1_3(int START_POINT) {
		//����һ��List���ϣ����������ѯ���
		List<Map<String, Object>> mytest_list;
		
		SQL = "select * from mytest order by myid limit " + START_POINT + ","+ PAGE_SIZE + ";";
		
		mytest_list = this.getJdbcTemplate().queryForList(SQL);
		
		return mytest_list;
	}
}
