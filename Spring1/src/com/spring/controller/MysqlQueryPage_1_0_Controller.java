package com.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.MysqlQueryPage_1_0_Dao;

/*
 * SpringMVC+MySQL查询分页技术（1.0版本）
 * 控制层类 public class MysqlQueryPage_1_0_Controller
 */

@Controller
public class MysqlQueryPage_1_0_Controller {
	
	//定义一个全局变量，用来表示总页数，如果总页码小于10，程序可能存在异常，因为小于10页的功能还没有完善
	public static int TOTAL_PAGES = 0;
	
	//定义一个int变量，用来接收前台参数，这个变量表示当前需要查询的页码
	public static int CURRENT_PAGE = 1;
	
	//定义一个全局变量，用来表示每页显示的记录数，PAGE_SIZE，这个参数和数据库查询的参数要同步
	private static int PAGE_SIZE = 100;
	
	//定义Dao层的接口对象MysqlQueryPageDao
	MysqlQueryPage_1_0_Dao mysqlQueryPage_1_0_Dao;
	
	//MysqlQueryPageDao的setter、getter方法
	public MysqlQueryPage_1_0_Dao getMysqlQueryPage_1_0_Dao() {
		return mysqlQueryPage_1_0_Dao;
	}
	public void setMysqlQueryPage_1_0_Dao(
			MysqlQueryPage_1_0_Dao mysqlQueryPage_1_0_Dao) {
		this.mysqlQueryPage_1_0_Dao = mysqlQueryPage_1_0_Dao;
	}
	
	@RequestMapping("mysqlQueryPage_1_0.action")
	public ModelAndView mysqlQueryPage_1_4(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		//定义一个long变量，用来接收总记录数
		long count_of_mytest = 0;
		//定义一个Map<String, List<Map<String, Object>>>，用来接收查询结果
		List<Map<String, Object>> mytest_list;
		//创建一个整型数组
		@SuppressWarnings("rawtypes")
		ArrayList mytest_array = new ArrayList();
		//定义一个int变量，用来标识查询游标起点start_point
		int start_point = 0;
		
		//获取表中的总记录数，也就是总页数
		count_of_mytest = mysqlQueryPage_1_0_Dao.getCountsOfTable();
		TOTAL_PAGES = (int) (count_of_mytest / PAGE_SIZE) + 1;
		
		//这里做一个判断，
		//如果总页码TOTAL_PAGES <= 10 ，或者current_page为空，则直接显示所有的页数；
		//如果总页码TOTAL_PAGES > 10 ，并且current_page不为空，则再做判断；
		if((request.getParameter("current_page")!= null) && (TOTAL_PAGES > 10)){
			//获取current_page，用户点击的当前页码
			CURRENT_PAGE = Integer.parseInt(request.getParameter("current_page"));
			
			//如果current_page小于等于5，也就是说，用户点了2345这4个页面的其中一个页面，此时我们：
			if(CURRENT_PAGE <= 5){
				//定义一个int变量，用来标识查询游标起点start_point
				start_point = PAGE_SIZE * (CURRENT_PAGE - 1);
			}else if((CURRENT_PAGE > 5) && (CURRENT_PAGE <= TOTAL_PAGES)){
				//定义一个int变量，用来标识查询游标起点start_point
				start_point = 100 * (CURRENT_PAGE - 1);
			}else if(CURRENT_PAGE == 8080){
				//定义一个int变量，用来标识查询游标起点start_point
				start_point = PAGE_SIZE * (TOTAL_PAGES - 1);
			}else{
				//异常
				System.out.println("程序进入这里，发生了没有捕获的异常...");
			}
			
			//调用Dao层的方法，实现查询功能
			mytest_list = mysqlQueryPage_1_0_Dao.mysqlQueryPage_1_3(start_point);
			//调用工具类
			mytest_array = this.generateMapFromInt_2(TOTAL_PAGES,CURRENT_PAGE);
			//把查询结果封装到org.springframework.ui.ModelMap中
			model.addAttribute("mytest",mytest_list);
			model.addAttribute("total_pages", mytest_array);
			
			//返回viewName和查询结果集model
			return new ModelAndView("/MySQLQueryPage_1_0.jsp",model);
		}
		else{
			
			//调用Dao层的方法，实现查询功能
			mytest_list = mysqlQueryPage_1_0_Dao.mysqlQueryPage_1_0();
			//调用工具类generateMapFromInt_1，生成map
			mytest_array = this.generateMapFromInt_1(TOTAL_PAGES);
			//把查询结果封装到org.springframework.ui.ModelMap中
			model.addAttribute("mytest",mytest_list);
			model.addAttribute("total_pages", mytest_array);
			
			//返回viewName和查询结果集model
			return new ModelAndView("/MySQLQueryPage_1_0.jsp",model);
		}
	}

	/*
	 * 工具类一：总页码TOTAL_PAGES小于10的情况
	 * 根据传入的整型数字，创建一个数组，如果页码小于10，则把所有页面封装进去，传递到前台
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList generateMapFromInt_1(int TOTAL_PAGES){
		//创建一个整型数组
		ArrayList mytest_array = new ArrayList();

		for(int i=0;i<TOTAL_PAGES;i++){
			mytest_array.add(i, i+1);
		}
		return mytest_array;
	}
	
	/*
	 * 工具类二：总页码TOTAL_PAGES大于10的情况
	 * 根据传入的整型数字，创建一个数组，只传递需要显示的10个页码到前台
	 * 封装以current_page为中心的10个页码，这里我们约定current_page前面的4个
	 * 和current_page后面的5个，一共10个页码。
	 * 三种情况：
	 * 1，如果用户点击的是第 1/2/3/4页或者是首页，我们应该显示第1页到第10页，而不能出现负数；
	 * 2，如果用户点击的是倒数第1/2/3/4或者尾页，我们应该显示最后10页，而不能出现大于TOTAL_PAGES的页码；
	 * 3，其他情况；
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList generateMapFromInt_2(int TOTAL_PAGES,int CURRENT_PAGE){
		//创建一个整型数组
		ArrayList mytest_array_10 = new ArrayList();
		
		//如果TOTAL_PAGES - CURRENT_PAGE < 5，也就是说当前页CURRENT_PAGE后面不足以显示5个页码了。
		if(TOTAL_PAGES - CURRENT_PAGE < 5){
			//起始页码
			int start_page = TOTAL_PAGES;
		    //把数据封装到map中，反向输出
			for(int i=0;i<10;i++){
				mytest_array_10.add(i, start_page - (9 - i));
			}
		//如果TOTAL_PAGES - CURRENT_PAGE >= 5,分以下几种情况：
		//如果CURRENT_PAGE < 5，也就是说用户点击的是前4页或者是首页
		}else if(CURRENT_PAGE < 5){
			//起始页码
			int start_page = 1;
		    //把数据封装到map中
			for(int i=0;i<10;i++){
				mytest_array_10.add(i, start_page + i);
			}
		}
		//如果(CURRENT_PAGE > 5) && ((CURRENT_PAGE - TOTAL_PAGES) > 5)，其他情况
		else{
			//起始页码
			int start_page = CURRENT_PAGE - 4;
		    //把数据封装到map中
			for(int i=0;i<10;i++){
				mytest_array_10.add(i, start_page + i);
			}
		}
		return mytest_array_10;
	}
}
