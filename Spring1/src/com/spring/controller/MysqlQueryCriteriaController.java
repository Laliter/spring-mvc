package com.spring.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.MysqlQueryCriteriaDao;


@Controller
public class MysqlQueryCriteriaController {
	
	//定义一个flag，用来标识查询结果
	boolean flag = false;
	
	//定义一个int类型的变量，用来接收页面传递过来的参数
	int myid = 0;
	//定义一个String类型的变量，用来接收页面传递过来的参数
	String myname = null;
	
	//配置dao，将MysqlQueryCriteriaDao注入MysqlQueryCriteriaController
	MysqlQueryCriteriaDao mysqlQueryCriteriaDao;
	//MysqlQueryCriteriaDaoImpl对象的setter、getter方法
	public MysqlQueryCriteriaDao getMysqlQueryCriteriaDao() {
		return mysqlQueryCriteriaDao;
	}

	public void setMysqlQueryCriteriaDao(MysqlQueryCriteriaDao mysqlQueryCriteriaDao) {
		this.mysqlQueryCriteriaDao = mysqlQueryCriteriaDao;
	}
	
	/*
	 * 第一种方法，用request对象来接收页面传递过过来的数据
	 */
	/*
	@RequestMapping("mysqlQueryCriteria.action")
	public ModelAndView mysqlQueryCriteriaByRequest(HttpServletRequest request,HttpServletResponse response){
		
		//myid = (Integer) request.getAttribute("myid"); 
		myid = Integer.parseInt(request.getParameter("myid"));
		//测试输出
		System.out.println("从JSP页面获取到的参数为：" + myid);
		//生成一个时间对象date_before_query_object，在定义一个long变量date_before_query，用来记下当前的时刻；
		Date date_before_query_object = new Date();
		long date_before_query = 0;
		date_before_query = date_before_query_object.getTime();
		flag = mysqlQueryCriteriaDao.selectRecordByName(myid);
		
		//如果查询成功，返回flag=true，并打印程序执行时间到控制台；否则flag=false；
		if(flag == true){
			//生成一个时间对象date_after_query_object，在定义一个long变量date_after_query，用来记下当前的时刻；
			Date date_after_query_object = new Date();
			long date_after_query = 0;
			date_after_query = date_after_query_object.getTime();
			//测试输出
			System.out.println("查询所用的时间大约为：" + (date_after_query - date_before_query));
			System.out.println("查询成功，MysqlQueryCriteriaController类中的mysqlQueryCriteriaByRequest方法...");
			return new ModelAndView("/MysqlQuerySuccess.jsp");
		}
		else{
			System.out.println("查询失败，MysqlQueryCriteriaController类中的mysqlQueryCriteriaByRequest方法...");
			return new ModelAndView("/MysqlQueryFail.jsp");
		}
		
	}
    */
	/*
	 * 第二种方法，用@ModelAttribute注解方式，接收页面传递过来的参数
	 */

	@RequestMapping("mysqlQueryCriteria.action")
	public ModelAndView mysqlQueryCriteriaByModelAttribute(@RequestParam("myid")int myid ,ModelMap model){
		//测试输出
		System.out.println("使用SpringMVC注解方式，从JSP页面获取的myid的值为：" + myid);
		//生成一个时间对象date_before_query_object，在定义一个long变量date_before_query，用来记下当前的时刻；
		Date date_before_query_object = new Date();
		long date_before_query = 0;
		date_before_query = date_before_query_object.getTime();
		
		flag = mysqlQueryCriteriaDao.selectRecordByName(myid);

		//如果查询成功，返回flag=true，并打印程序执行时间到控制台；否则flag=false；
		if(flag == true){
			//生成一个时间对象date_after_query_object，在定义一个long变量date_after_query，用来记下当前的时刻；
			Date date_after_query_object = new Date();
			long date_after_query = 0;
			date_after_query = date_after_query_object.getTime();
			//测试输出
			System.out.println("查询所用的时间大约为：" + (date_after_query - date_before_query));
			System.out.println("查询成功，MysqlQueryCriteriaController类中的mysqlQueryCriteriaByModelAttribute方法...");
			return new ModelAndView("/MysqlQuerySuccess.jsp");
		}
		else{
			System.out.println("查询失败，MysqlQueryCriteriaController类中的mysqlQueryCriteriaByModelAttribute方法...");
			return new ModelAndView("/MysqlQueryFail.jsp");
		}
	}
	
	/*
	 * MySQL查询测试：通过myid和myname2个字段来查询
	 */
	@RequestMapping("mysqlQueryCriteriaByName.action")
	public ModelAndView mysqlQueryCriteriaByRequest(HttpServletRequest request,HttpServletResponse response){
		
		myid = Integer.parseInt(request.getParameter("myid"));
		myname = request.getParameter("myname");
		//测试输出
		System.out.println("从JSP页面获取到的参数为：" + myid);
		//生成一个时间对象date_before_query_object，在定义一个long变量date_before_query，用来记下当前的时刻；
		Date date_before_query_object = new Date();
		long date_before_query = 0;
		date_before_query = date_before_query_object.getTime();
		flag = mysqlQueryCriteriaDao.selectRecordByMyname(myid, myname);
		
		//如果查询成功，返回flag=true，并打印程序执行时间到控制台；否则flag=false；
		if(flag == true){
			//生成一个时间对象date_after_query_object，在定义一个long变量date_after_query，用来记下当前的时刻；
			Date date_after_query_object = new Date();
			long date_after_query = 0;
			date_after_query = date_after_query_object.getTime();
			//测试输出
			System.out.println("查询所用的时间大约为：" + (date_after_query - date_before_query));
			System.out.println("查询成功，MysqlQueryCriteriaController类中的mysqlQueryCriteriaByRequest方法...");
			return new ModelAndView("/MysqlQuerySuccess.jsp");
		}
		else{
			System.out.println("查询失败，MysqlQueryCriteriaController类中的mysqlQueryCriteriaByRequest方法...");
			return new ModelAndView("/MysqlQueryFail.jsp");
		}
		
	}

}
