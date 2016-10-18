package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.User;

@Controller
public class LoginValidateController {
	//����2����������������ҳ�洫�ݹ���������
	String username;
	String password;
	
	User user = new User();
	
	/*
	 * ����������
	 * 1���ӵ�½ҳ��login.jsp�����û���������2��������Controller��
	 * 2����֤�û����������Ƿ���ȷ�������ȷ�����û��������봫�ݵ�success.jspҳ�棻
	 */
	
	/*
	 * SpringMVC�У�Controller��ȡJSPҳ��Ĳ����кܶ��ַ�ʽ��
	 * 1��ͨ��request����
	 * 2����@ModelAttributeע���ȡform�������ݣ�POST����
	 * 3����@RequestParamע�ⷽʽ��ȡҳ�����
	 */
	
	//1��ͨ��request����
	@RequestMapping("UserLoginValidate_1.action")
	public ModelAndView getUserInfo(HttpServletRequest request,HttpServletResponse response){
		username = request.getParameter("username");
		password = request.getParameter("password");
		//�������
		System.out.println("username��" + username);
		System.out.println("username��һ�������String���͵ı�����username.getClass()" + username.getClass());
		System.out.println("user��һ������java����user.getClass()" + user.getClass());
		System.out.println("user��user.getClass()�к�����" + user);
		System.out.println("user.toString()��" + user.toString());
		System.out.println("username.toString()��" + username.toString());
		System.out.println("user.hashCode()��" + user.hashCode());
		System.out.println("username.hashCode()��" + username.hashCode());
		System.out.println("password��" + password);
		user.setUsername(username);
		user.setPassword(password);
		Map<String,User> model = new HashMap<String,User>();
		model.put("user", user);
		
		//��֤�û����������Ƿ���ȷ�������ȷ������true�����򷵻�false
		if("admin".equals(username) && "CHINA".equals(password)){
			return new ModelAndView("/success.jsp",model);
		}else{
			return new ModelAndView("/fail.jsp");
		}
	}
	
	//2����@ModelAttributeע���ȡform�������ݣ�POST����
	@RequestMapping("UserLoginValidate_2.action")
	public ModelAndView loginValidate(@ModelAttribute("user")User user){
		//�������
		System.out.println("��@ModelAttributeע���ȡform�������ݣ�" + user.getUsername());
		System.out.println("��@ModelAttributeע���ȡform�������ݣ�" + user.getPassword());
		//��֤�û����������Ƿ���ȷ�������ȷ������true�����򷵻�false
		if("admin".equals(user.getUsername()) && "CHINA".equals(user.getPassword())){
			return new ModelAndView("/success.jsp");
		}else{
			return new ModelAndView("/fail.jsp");
		}
	}
	
	//3����@RequestParamע�ⷽʽ��ȡҳ����������ڰ󶨵������������ֵ
	@RequestMapping("UserLoginValidate_3.action")
	public ModelAndView loginValidate3(@RequestParam("username")String username ,ModelMap model){
		//�������
		System.out.println("��@RequestParamע���ȡform�������ݣ�" + username);
		//��֤�û����������Ƿ���ȷ�������ȷ������true�����򷵻�false
		if("admin".equals(username)){
			return new ModelAndView("/success.jsp");
		}else{
			return new ModelAndView("/fail.jsp");
		}
	}
	
}
