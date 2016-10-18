package com.spring.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.MysqlQueryDao;

public class MysqlQueryDaoImpl extends JdbcDaoSupport implements MysqlQueryDao {
	
	@Override
	public List<Map<String, Object>> selectRecord() {
		

		//��List<Map<String, Object>>����װ��ѯ���
		
		//����һ��String���͵ı���
		String sql;
		//����һ��List���ϣ����������ѯ���
		List<Map<String, Object>> mytest_list;
		
		
		sql = "select * from news order by newsid;";
		//��jdbcTemplateģ���queryForList����
		mytest_list = this.getJdbcTemplate().queryForList(sql);

		return mytest_list;
	}
}
