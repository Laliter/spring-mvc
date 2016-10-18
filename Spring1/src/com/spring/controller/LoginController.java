package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * Spring MVC��ܴ���̵�X����Controller�ļ�����
 * 1��������˽�struts2�����԰�Spring MVC��Controller����Struts2��Action��
 * 2�����ȣ�Controller��MVCģʽ�е�C������������Ҫ�����DispatcherServlet��������Ȼ�����model��������
 * 3�����ע��@Controller�����������������ע���أ��ҵ���⣬SpringMVC��Controller��LoginController��
 *    ע�⣬Ч����Struts2��Action��̳�ActionServlet��һ����ԭ��֮������ע�����̳У���Ϊ�˸��ӷ���MVC
 *    ģʽ�Ĺ淶����һ������ҵ���߼�������servletAPI��������ϵ��
 */

@Controller
public class LoginController {
	/*
	 * @RequestMapping
	 * 1������Spring MVC ��ע�⣻
	 * 2�����Ĺ����ǽ����û�������Ҳ���ǽ���JSPҳ�淢�������������û���¼����
	 * 3��@RequestMappingע�⣬�ǽ���JSPҳ��������Controller֮���ӳ���ϵ��
	 * 4��@RequestMapping �������ΪStruts2��struts.xml�����õ�action��
	 * 5��SpringMVC��Struts2������һ�㣬����Spring������Ҫ�����ļ���ӳ��JSP��java�ļ�֮��Ĺ�ϵ��
	 * 6��SpringMVC��������ƣ��ŵ���ʲô����û��ȱ�㣿���˼��
	 */
	@RequestMapping("UserLogin.action")
	public ModelAndView login(){
		//�������
		System.out.println("--------SPRING MVC �������--------");
		/*
		 * ModelAndView
		 * 1������ģ�ͺ���ͼ��Spring���Ŀ�����DispatcherServlet��
		 * 2��
		 */
		return new ModelAndView("/success.jsp");
	}
}
