package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.UserDao;
import com.spring.model.User;

@Controller
public class UserController {
	//定义2个变量，用来接收页面传递过来的数据
	String username;
	String password;
	
	User user;
	
	//定义Dao层的接口对象UserDao
	UserDao userDao;
	
	//提供userDao的setter、getter方法
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	//登录功能，1，用request对象获取表单数据，已经测试通过
	@RequestMapping("UserLoginValidate_4.action")
	public ModelAndView userLoginRequest(HttpServletRequest request,HttpServletResponse response){
		
		username = request.getParameter("username");
		password = request.getParameter("password");

		user = userDao.loginValidate(username, password);
		
		//验证用户名和密码是否正确，如果正确，返回true；否则返回false
		if(username.equals(user.getUsername()) && password.equals(user.getPassword()) ){
			//测试输出
			System.out.println("登录成功...");
			Map<String,User> model = new HashMap<String,User>();
			model.put("user", user);
			HttpSession session = request.getSession(); 
			session.setAttribute("username", user.getUsername());
			return new ModelAndView("/index.jsp",model);
		}else{
			//测试输出
			System.out.println("登录失败...");
			return new ModelAndView("/fail.jsp");
		}
		
	}
	
}
