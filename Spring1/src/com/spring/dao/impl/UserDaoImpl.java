
package com.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.dao.UserDao;
import com.spring.model.User;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	/* 
	 * 1��SpringMVCҲ��JDBC�����˷�װ��������Ҫ�̳�JdbcDaoSupport��
	 * 2��
	 */
	@Override
	public User loginValidate(String username, String password) {

		final User user_final = new User();
		//SQL
		String sql = "select * from user where username=? and password=?";
		
		this.getJdbcTemplate().query(sql, new Object[]{username,password}, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException{

				//�������
				//System.out.println("country:" + rs.getString("country"));
				//System.out.println("username:" + rs.getString("username"));
				user_final.setUsername(rs.getString("username"));
				user_final.setPassword(rs.getString("password"));
				//�������
				System.out.println("��user���Ƿ���ֵ:" + user_final.getUsername());
				System.out.println("��user���Ƿ���ֵ:" + user_final.getPassword());
			}
		});
		
		return user_final;
	}

}
