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
	//��¼��֤���ܣ���dao�㣬������Ҫ�û��������룬�����ݿ������֤��
	//����������Ҫ����һ�������User����List��װ
	public User loginValidate(String username,String password);
	
}
