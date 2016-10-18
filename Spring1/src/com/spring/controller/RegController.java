package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.RegDao;
@Controller
public class RegController  {
	String username;
	String password;
	//定义Dao层的接口对象UserDao
RegDao regDao;
	
	//提供userDao的setter、getter方法
	public RegDao getRegDao() {
		return regDao;
	}
	public void setRegDao(RegDao regDao) {
		this.regDao = regDao;
	}
	@RequestMapping("regDao.action")
	public ModelAndView regModelAndView(ModelMap model,HttpServletRequest request,HttpServletResponse response){
	username = request.getParameter("username");
	password = request.getParameter("password");
	regDao.reg(username, password);
	HttpSession session = request.getSession(); 
	session.setAttribute("username", username);
	return new ModelAndView("/index.jsp",model);
	}
}
