package com.spring.dao;

import java.util.List;
import java.util.Map;

public interface MysqlQueryDao {
	
	public List<Map<String, Object>> selectRecord();
	
}
