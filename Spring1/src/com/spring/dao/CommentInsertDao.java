package com.spring.dao;

import java.util.List;
import java.util.Map;

public interface CommentInsertDao {
	public  List<Map<String, Object>> insertRecord(int nid,String username,String context);
}
