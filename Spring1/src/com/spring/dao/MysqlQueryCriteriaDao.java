package com.spring.dao;

public interface MysqlQueryCriteriaDao {
	//�����û������myid�ֶΣ���ѯ���ݿ�
	public boolean selectRecordByName(int myid);
	//�����û������mydata�ֶΣ���ѯ���ݿ�
	public boolean selectRecordByMyname(int myid,String myname);
}
