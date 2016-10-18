package com.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.CommentQueryDao;

public class CommentQueryDaoImpl extends JdbcDaoSupport implements CommentQueryDao {

	@Override
	public List<Map<String, Object>> selectRecord(int newsid) {
		// TODO Auto-generated method stub


		//��List<Map<String, Object>>����װ��ѯ���
		
		//����һ��String���͵ı���
		String sql;
		//����һ��List���ϣ����������ѯ���
		List<Map<String, Object>> mytest_list;
		
		
		sql = "select * from comment where id="+newsid+";";
		//��jdbcTemplateģ���queryForList����
		mytest_list = this.getJdbcTemplate().queryForList(sql);
			
		return mytest_list;
	}

}
