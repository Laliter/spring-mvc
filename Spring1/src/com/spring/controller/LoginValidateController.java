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
	//定义2个变量，用来接收页面传递过来的数据
	String username;
	String password;
	
	User user = new User();
	
	/*
	 * 功能描述：
	 * 1，从登陆页面login.jsp传递用户名和密码2个参数到Controller；
	 * 2，验证用户名和密码是否正确，如果正确，把用户名和密码传递到success.jsp页面；
	 */
	
	/*
	 * SpringMVC中，Controller获取JSP页面的参数有很多种方式：
	 * 1，通过request对象；
	 * 2，用@ModelAttribute注解获取form表单的数据，POST请求
	 * 3，用@RequestParam注解方式获取页面参数
	 */
	
	//1，通过request对象；
	@RequestMapping("UserLoginValidate_1.action")
	public ModelAndView getUserInfo(HttpServletRequest request,HttpServletResponse response){
		username = request.getParameter("username");
		password = request.getParameter("password");
		//测试输出
		System.out.println("username：" + username);
		System.out.println("username是一个定义的String类型的变量，username.getClass()" + username.getClass());
		System.out.println("user是一个定义java对象，user.getClass()" + user.getClass());
		System.out.println("user与user.getClass()有何区别：" + user);
		System.out.println("user.toString()：" + user.toString());
		System.out.println("username.toString()：" + username.toString());
		System.out.println("user.hashCode()：" + user.hashCode());
		System.out.println("username.hashCode()：" + username.hashCode());
		System.out.println("password：" + password);
		user.setUsername(username);
		user.setPassword(password);
		Map<String,User> model = new HashMap<String,User>();
		model.put("user", user);
		
		//验证用户名和密码是否正确，如果正确，返回true；否则返回false
		if("admin".equals(username) && "CHINA".equals(password)){
			return new ModelAndView("/success.jsp",model);
		}else{
			return new ModelAndView("/fail.jsp");
		}
	}
	
	//2，用@ModelAttribute注解获取form表单的数据，POST请求
	@RequestMapping("UserLoginValidate_2.action")
	public ModelAndView loginValidate(@ModelAttribute("user")User user){
		//测试输出
		System.out.println("用@ModelAttribute注解获取form表单的数据：" + user.getUsername());
		System.out.println("用@ModelAttribute注解获取form表单的数据：" + user.getPassword());
		//验证用户名和密码是否正确，如果正确，返回true；否则返回false
		if("admin".equals(user.getUsername()) && "CHINA".equals(user.getPassword())){
			return new ModelAndView("/success.jsp");
		}else{
			return new ModelAndView("/fail.jsp");
		}
	}
	
	//3，用@RequestParam注解方式获取页面参数，用于绑定单个请求参数的值
	@RequestMapping("UserLoginValidate_3.action")
	public ModelAndView loginValidate3(@RequestParam("username")String username ,ModelMap model){
		//测试输出
		System.out.println("用@RequestParam注解获取form表单的数据：" + username);
		//验证用户名和密码是否正确，如果正确，返回true；否则返回false
		if("admin".equals(username)){
			return new ModelAndView("/success.jsp");
		}else{
			return new ModelAndView("/fail.jsp");
		}
	}
	
}
