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

import com.spring.dao.MysqlQueryPage_1_1_Dao;

/*
 * SpringMVC+MySQL查询分页技术（1.1版本）
 * 控制层类 public class MysqlQueryPage_1_1_Controller
 */

@Controller
public class MysqlQueryPage_1_1_Controller {
	
	//定义一个全局变量，用来表示数据库查询出来的总记录数，TOTAL_RECORDS，这个参数和数据库查询的参数要同步
	private static long TOTAL_RECORDS = 0;
	
	//定义一个全局变量，用来表示总页数
	private static int TOTAL_PAGES = 0;
	
	//定义一个int变量，用来接收前台参数，这个变量表示当前需要查询的页码
	private static int CURRENT_PAGE = 1;
	
	//定义一个全局变量，用来表示数据库查询的起点位置，也就是游标的起始位置
	private static int START_POINT = 0;
	
	//定义一个全局变量，用来表示每页显示的记录数，PAGE_SIZE，这个参数和数据库查询的参数要同步
	private static int PAGE_SIZE = 100;
	
	//定义一个全局变量，用来表示尾页，也就是最后一页
	private static int PAGE_8080 = 8080;
	
	//定义一个Map<String, List<Map<String, Object>>>，用来接收查询结果
	List<Map<String, Object>> mytest_list;
	
	//创建一个整型数组，用来保存需要传递到前台的页码
	@SuppressWarnings("rawtypes")
	ArrayList mytest_array = new ArrayList();
	
	//定义Dao层的接口对象MysqlQueryPageDao
	MysqlQueryPage_1_1_Dao mysqlQueryPage_1_1_Dao;
	//MysqlQueryPage_1_1_Dao的setter、getter方法
	public MysqlQueryPage_1_1_Dao getMysqlQueryPage_1_1_Dao() {
		return mysqlQueryPage_1_1_Dao;
	}
	public void setMysqlQueryPage_1_1_Dao(
			MysqlQueryPage_1_1_Dao mysqlQueryPage_1_1_Dao) {
		this.mysqlQueryPage_1_1_Dao = mysqlQueryPage_1_1_Dao;
	}

	/*
	 * SpringMVC+MySQL查询分页技术（1.1版本）
	 * 分页基本功能
	 * 包括：首页、尾页、到达指定页、用户输入页码
	 */
	@RequestMapping("mysqlQueryPage_1_1.action")
	public ModelAndView mysqlQueryPage_1_4(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		//获取表中的总记录数，也就是总页数
		TOTAL_RECORDS = mysqlQueryPage_1_1_Dao.getCountsOfTable();
		TOTAL_PAGES = (int) (TOTAL_RECORDS / PAGE_SIZE) + 1;
		//把TOTAL_PAGES传递到客户端
		model.addAttribute("count_pages", TOTAL_PAGES);

		//如果总页码TOTAL_PAGES <= 10 ，或者CURRENT_PAGE为空，则直接显示所有的页数；
		//如果总页码TOTAL_PAGES > 10 ，并且CURRENT_PAGE不为空，则再做判断；
		if(TOTAL_PAGES > 10){
			//获取CURRENT_PAGE，用户点击的当前页码
			CURRENT_PAGE = Integer.parseInt(request.getParameter("current_page"));
			//把当前页码传递到客户端，保存到隐藏域
			model.addAttribute("previous_page", CURRENT_PAGE);
			//测试输出
			System.out.println("从数据库中查询的总记录数TOTAL_RECORDS为：" + TOTAL_RECORDS);
			System.out.println("每页显示的记录数PAGE_SIZE为：" + PAGE_SIZE);
			System.out.println("总页数TOTAL_PAGES为：" + TOTAL_PAGES);
			System.out.println("当前页CURRENT_PAGE为：：" + CURRENT_PAGE);
			//如果CURRENT_PAGE小于等于5，也就是说，用户点了2345这4个页面的其中一个页面，此时我们：
			if(CURRENT_PAGE <= 5){
				START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				//调用Dao层的方法，实现查询功能
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
			//如果CURRENT_PAGE > 5
			}else if((CURRENT_PAGE > 5) && (CURRENT_PAGE <= TOTAL_PAGES)){
				START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				//调用Dao层的方法，实现查询功能
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
			//如果CURRENT_PAGE = PAGE_8080，也就是最后一页
			}else if(CURRENT_PAGE == PAGE_8080){
				START_POINT = PAGE_SIZE * (TOTAL_PAGES - 1);
				//调用Dao层的方法，实现查询功能
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
			}else{
				//调用Dao层的方法，实现查询功能
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_0();
			}
			//调用工具类generateMapFromInt_1，生成map
			mytest_array = this.generateMapFromInt_2(TOTAL_PAGES, CURRENT_PAGE);
			//把查询结果封装到org.springframework.ui.ModelMap中
			model.addAttribute("mytest",mytest_list);
			model.addAttribute("total_pages", mytest_array);
			//返回viewName和查询结果集model
			return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
		}
		/*
		 * 1，TOTAL_PAGES <= 10
		 */
		else if(TOTAL_PAGES <= 10){
			//获取CURRENT_PAGE，用户点击的当前页码
			CURRENT_PAGE = Integer.parseInt(request.getParameter("current_page"));
			//把当前页码传递到客户端，保存到隐藏域
			model.addAttribute("previous_page", CURRENT_PAGE);
			//如果CURRENT_PAGE小于等于5，也就是说，用户点了2345这4个页面的其中一个页面，此时我们：
			if(CURRENT_PAGE > 0){
				//如果CURRENT_PAGE = PAGE_8080，也就是最后一页
				if(CURRENT_PAGE == PAGE_8080){
					START_POINT = PAGE_SIZE * (TOTAL_PAGES - 1);
				}
				//如果CURRENT_PAGE > 0 且 CURRENT_PAGE 不是最后一页
				else{
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//调用Dao层的方法，实现查询功能
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
			}
			else{
				//把当前页码传递到客户端，保存到隐藏域
				model.addAttribute("previous_page", CURRENT_PAGE);
				//调用Dao层的方法，实现查询功能
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_0();
			}
			//调用工具类generateMapFromInt_1，生成map
			mytest_array = this.generateMapFromInt_1(TOTAL_PAGES);
			//把查询结果封装到org.springframework.ui.ModelMap中
			model.addAttribute("mytest",mytest_list);
			model.addAttribute("total_pages", mytest_array);
			//返回viewName和查询结果集model
			return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
		}
		/*
		 * 跳转到这段代码，说明可能是以下几种情况：
		 */
		else{
			//把当前页码传递到客户端，保存到隐藏域
			model.addAttribute("previous_page", CURRENT_PAGE);
			//测试代码
			System.out.println("跳转到这段代码，说明可能是以下几种情况：");
			//调用Dao层的方法，实现查询功能
			mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_0();
			//调用工具类generateMapFromInt_1，生成map
			mytest_array = this.generateMapFromInt_1(TOTAL_PAGES);
			//把查询结果封装到org.springframework.ui.ModelMap中
			model.addAttribute("mytest",mytest_list);
			model.addAttribute("total_pages", mytest_array);
			//返回viewName和查询结果集model
			return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
		}
	}
	/*
	 * SpringMVC+MySQL查询分页技术（1.1版本）
	 * 上一页功能 、下一页功能
	 */
	@RequestMapping("mysqlQueryPage_1_1_previous_page.action")
	public ModelAndView mysqlQueryPage_1_1_2(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		//定义一个int，接收前台传递过来的previous_page，也就是用户点击上一页按钮前所在的页码数
		int previous_page = 1;
		//定义一个int，接收前台传递过来的flag，判断用户是点击了上一页按钮，还是点击了下一页按钮
		int flag = 0;
		//获取2个参数，一个是previous_page，一个是flag
		previous_page = Integer.parseInt(request.getParameter("previous_page"));
		flag = Integer.parseInt(request.getParameter("flag"));
		//获取表中的总记录数，也就是总页数
		TOTAL_RECORDS = mysqlQueryPage_1_1_Dao.getCountsOfTable();
		TOTAL_PAGES = (int) (TOTAL_RECORDS / PAGE_SIZE) + 1;
		//把TOTAL_PAGES传递到客户端
		model.addAttribute("count_pages", TOTAL_PAGES);
		/*
		 * 如果TOTAL_PAGES <= 10
		 */
		if(TOTAL_PAGES <= 10){
			//如果flag=0，则代表的是上一页；执行上一页操作，实现上一页功能
			if(flag == 0){
				//1，如果previous_page是第一页或者首页，则仍然跳转到第一页
				//2，如果previous_page是8080，也就是输入页码功能的最后一页，则跳转到倒数第二页
				//3，其他情况下，跳转到（previous_page -1）页
				if((previous_page == 1) || (previous_page ==0 )){
					CURRENT_PAGE = 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//2，如果previous_page是PAGE_8080，也就是输入页码功能的最后一页，则跳转到倒数第二页
				else if(previous_page == PAGE_8080){
					CURRENT_PAGE = TOTAL_PAGES - 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//3，其他情况下，跳转到（previous_page -1）页
				else{
					CURRENT_PAGE = previous_page - 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//调用Dao层的方法，实现查询功能
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
				//调用工具类generateMapFromInt_1，生成map
				mytest_array = this.generateMapFromInt_1(TOTAL_PAGES);
				//把查询结果封装到org.springframework.ui.ModelMap中
				//把当前页码传递到客户端，保存到隐藏域
				model.addAttribute("previous_page", CURRENT_PAGE);
				model.addAttribute("mytest",mytest_list);
				model.addAttribute("total_pages", mytest_array);
				//返回viewName和查询结果集model
				return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
			}
			//如果flag=1，则代表的是下一页；执行下一页操作，实现下一页功能
			else{
				//1，如果previous_page是最后一页或者PAGE_8080，则仍然跳转到最后一页；
				if((previous_page == PAGE_8080) || (previous_page == TOTAL_PAGES)){
					CURRENT_PAGE = TOTAL_PAGES;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//2，其他情况下，跳转到（previous_page + 1）页；
				else{
					CURRENT_PAGE = previous_page + 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//调用Dao层的方法，实现查询功能
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
				//调用工具类generateMapFromInt_1，生成map
				mytest_array = this.generateMapFromInt_1(TOTAL_PAGES);
				//把查询结果封装到org.springframework.ui.ModelMap中
				//把当前页码传递到客户端，保存到隐藏域
				model.addAttribute("previous_page", CURRENT_PAGE);
				model.addAttribute("mytest",mytest_list);
				model.addAttribute("total_pages", mytest_array);
				//返回viewName和查询结果集model
				return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
			}
		}
		//如果TOTAL_PAGES > 10
		else{
			//如果flag=0，则代表的是上一页；执行上一页操作，实现上一页功能
			if(flag == 0){
				//1，如果previous_page是第一页或者首页，则仍然跳转到第一页
				//2，如果previous_page是8080，也就是输入页码功能的最后一页，则跳转到倒数第二页
				//3，其他情况下，跳转到（previous_page -1）页
				if((previous_page == 1) || (previous_page ==0 )){
					CURRENT_PAGE = 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//2，如果previous_page是PAGE_8080，也就是输入页码功能的最后一页，则跳转到倒数第二页
				else if(previous_page == PAGE_8080){
					CURRENT_PAGE = TOTAL_PAGES - 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//3，其他情况下，跳转到（previous_page -1）页
				else{
					CURRENT_PAGE = previous_page - 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//调用Dao层的方法，实现查询功能
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
				//调用工具类generateMapFromInt_1，生成map
				mytest_array = this.generateMapFromInt_2(TOTAL_PAGES, CURRENT_PAGE);
				//把查询结果封装到org.springframework.ui.ModelMap中
				//把当前页码传递到客户端，保存到隐藏域
				model.addAttribute("previous_page", CURRENT_PAGE);
				model.addAttribute("mytest",mytest_list);
				model.addAttribute("total_pages", mytest_array);
				//返回viewName和查询结果集model
				return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
			}
			//如果flag=1，则代表的是下一页；执行下一页操作，实现下一页功能
			else{
				//1，如果previous_page是最后一页或者PAGE_8080，则仍然跳转到最后一页；
				if((previous_page == PAGE_8080) || (previous_page == TOTAL_PAGES)){
					CURRENT_PAGE = TOTAL_PAGES;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//2，其他情况下，跳转到（previous_page + 1）页；
				else{
					CURRENT_PAGE = previous_page + 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//调用Dao层的方法，实现查询功能
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
				//调用工具类generateMapFromInt_1，生成map
				mytest_array = this.generateMapFromInt_2(TOTAL_PAGES, CURRENT_PAGE);
				//把查询结果封装到org.springframework.ui.ModelMap中
				//把当前页码传递到客户端，保存到隐藏域
				model.addAttribute("previous_page", CURRENT_PAGE);
				model.addAttribute("mytest",mytest_list);
				model.addAttribute("total_pages", mytest_array);
				//返回viewName和查询结果集model
				return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
			}
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
		//这里也需要做出一个判断，就是如果当前接收过来的CURRENT_PAGE等于0，这时就是第一次加载的时候。
		//这时我们只传递前10个页码到前台
		//把数据封装到ArrayList中
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