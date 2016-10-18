package com.spring.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.ContextInsertDao;

@Controller
public class ContextInsertController {

	//����Dao��Ľӿڶ���MysqlQueryPageDao
	ContextInsertDao contextInsertDao;
	
	//MysqlQueryPageDao��setter��getter����
	public ContextInsertDao getContextInsertDao() {
		return contextInsertDao;
	}
	public void setContextInsertDao(
			ContextInsertDao contextInsertDao) {
		this.contextInsertDao = contextInsertDao;
	}
	
	@RequestMapping("contextInsertDao.action")
	public ModelAndView contextInsert(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> mytest_list;
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		System.out.println("contextInsertController");
		mytest_list = contextInsertDao.insertRecord(request.getParameter("username"),request.getParameter("context"));
		model.addAttribute("mytest",mytest_list);
		//����viewName�Ͳ�ѯ�����model
		return new ModelAndView("/index.jsp",model);
	
	}
}
