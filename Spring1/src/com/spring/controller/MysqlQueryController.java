package com.spring.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.MysqlQueryDao;
@Controller
public class MysqlQueryController {
		
		//定义Dao层的接口对象MysqlQueryPageDao
		MysqlQueryDao mysqlQueryDao;
		
		//MysqlQueryPageDao的setter、getter方法
		public MysqlQueryDao getMysqlQueryDao() {
			return mysqlQueryDao;
		}
		public void setMysqlQueryDao(
				MysqlQueryDao mysqlQueryDao) {
			this.mysqlQueryDao = mysqlQueryDao;
		}
		
		@RequestMapping("mysqlQueryDao.action")
		public ModelAndView mysqlQuery(ModelMap model,HttpServletRequest request,HttpServletResponse response){
			
			List<Map<String, Object>> mytest_list;
			
			mytest_list = mysqlQueryDao.selectRecord();
			
			//把查询结果封装到org.springframework.ui.ModelMap中
			model.addAttribute("mytest",mytest_list);
			
			
			//返回viewName和查询结果集model
			return new ModelAndView("/index.jsp",model);
		
		}

}
