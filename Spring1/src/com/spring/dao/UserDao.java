/**
 * 
 */
package com.spring.dao;

import com.spring.model.User;

/**
 * @author Administrator
 *
 */
public interface UserDao {
	//登录验证功能，在dao层，我们需要用户名和密码，对数据库进行验证。
	//这里我们需要返回一个结果集User，用List封装
	public User loginValidate(String username,String password);
	
}
