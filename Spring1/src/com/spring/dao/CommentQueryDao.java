package com.spring.dao;

import java.util.List;
import java.util.Map;

public interface CommentQueryDao {
	public List<Map<String, Object>> selectRecord(int newsid);
}
