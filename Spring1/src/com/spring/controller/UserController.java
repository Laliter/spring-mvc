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
	//����2����������������ҳ�洫�ݹ���������
	String username;
	String password;
	
	User user;
	
	//����Dao��Ľӿڶ���UserDao
	UserDao userDao;
	
	//�ṩuserDao��setter��getter����
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	//��¼���ܣ�1����request�����ȡ�����ݣ��Ѿ�����ͨ��
	@RequestMapping("UserLoginValidate_4.action")
	public ModelAndView userLoginRequest(HttpServletRequest request,HttpServletResponse response){
		
		username = request.getParameter("username");
		password = request.getParameter("password");

		user = userDao.loginValidate(username, password);
		
		//��֤�û����������Ƿ���ȷ�������ȷ������true�����򷵻�false
		if(username.equals(user.getUsername()) && password.equals(user.getPassword()) ){
			//�������
			System.out.println("��¼�ɹ�...");
			Map<String,User> model = new HashMap<String,User>();
			model.put("user", user);
			HttpSession session = request.getSession(); 
			session.setAttribute("username", user.getUsername());
			return new ModelAndView("/index.jsp",model);
		}else{
			//�������
			System.out.println("��¼ʧ��...");
			return new ModelAndView("/fail.jsp");
		}
		
	}
	
}
