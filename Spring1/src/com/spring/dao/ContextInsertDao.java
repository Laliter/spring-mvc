package com.spring.dao;

import java.util.List;
import java.util.Map;

public interface ContextInsertDao {
	public  List<Map<String, Object>> insertRecord(String username,String context);
}
