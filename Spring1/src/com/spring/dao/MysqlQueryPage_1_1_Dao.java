package com.spring.dao;

import java.util.List;
import java.util.Map;

/*
 * SpringMVC+MySQL��ѯ��ҳ������1.1�汾��
 * Dao��ӿ� public interface MysqlQueryPage_1_1_Dao
 */
public interface MysqlQueryPage_1_1_Dao {
	//�����ݲ�����ֱ�Ӳ�ѯ��myid��1��100�ļ�¼
	public List<Map<String, Object>> mysqlQueryPage_1_0();
	//����ȫ���ѯ��������ݱ��е��ܼ�¼���������long���͵����ݴ���controller
	public long getCountsOfTable();
	//����id��ѯ���ݿ⣨���ID�ǵ�ǰҳ��current_page��������List<Map<String, Object>>
	public List<Map<String, Object>> mysqlQueryPage_1_3(int start_point);
}
