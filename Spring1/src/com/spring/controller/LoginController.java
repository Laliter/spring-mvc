package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * Spring MVC框架搭建流程第X步：Controller文件讲解
 * 1，如果你了解struts2，可以把Spring MVC的Controller理解成Struts2的Action；
 * 2，首先，Controller是MVC模式中的C，控制器，主要负责从DispatcherServlet接收请求，然后调用model处理请求；
 * 3，类的注解@Controller，我们如何来理解这个注解呢？我的理解，SpringMVC中Controller类LoginController的
 *    注解，效果和Struts2中Action类继承ActionServlet是一样的原理。之所有用注解代替继承，是为了更加符合MVC
 *    模式的规范，进一步降低业务逻辑代码与servletAPI的依赖关系。
 */

@Controller
public class LoginController {
	/*
	 * @RequestMapping
	 * 1，这是Spring MVC 的注解；
	 * 2，他的功能是接收用户的请求，也就是接收JSP页面发出的请求，例如用户登录请求；
	 * 3，@RequestMapping注解，是建立JSP页面请求与Controller之间的映射关系；
	 * 4，@RequestMapping 可以理解为Struts2中struts.xml中配置的action；
	 * 5，SpringMVC比Struts2进步的一点，就是Spring不再需要配置文件来映射JSP和java文件之间的关系；
	 * 6，SpringMVC的这种设计，优点是什么？有没有缺点？大家思考
	 */
	@RequestMapping("UserLogin.action")
	public ModelAndView login(){
		//测试输出
		System.out.println("--------SPRING MVC 测试输出--------");
		/*
		 * ModelAndView
		 * 1，返回模型和视图给Spring核心控制器DispatcherServlet；
		 * 2，
		 */
		return new ModelAndView("/success.jsp");
	}
}
