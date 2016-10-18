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
 * SpringMVC+MySQL��ѯ��ҳ������1.1�汾��
 * ���Ʋ��� public class MysqlQueryPage_1_1_Controller
 */

@Controller
public class MysqlQueryPage_1_1_Controller {
	
	//����һ��ȫ�ֱ�����������ʾ���ݿ��ѯ�������ܼ�¼����TOTAL_RECORDS��������������ݿ��ѯ�Ĳ���Ҫͬ��
	private static long TOTAL_RECORDS = 0;
	
	//����һ��ȫ�ֱ�����������ʾ��ҳ��
	private static int TOTAL_PAGES = 0;
	
	//����һ��int��������������ǰ̨���������������ʾ��ǰ��Ҫ��ѯ��ҳ��
	private static int CURRENT_PAGE = 1;
	
	//����һ��ȫ�ֱ�����������ʾ���ݿ��ѯ�����λ�ã�Ҳ�����α����ʼλ��
	private static int START_POINT = 0;
	
	//����һ��ȫ�ֱ�����������ʾÿҳ��ʾ�ļ�¼����PAGE_SIZE��������������ݿ��ѯ�Ĳ���Ҫͬ��
	private static int PAGE_SIZE = 100;
	
	//����һ��ȫ�ֱ�����������ʾβҳ��Ҳ�������һҳ
	private static int PAGE_8080 = 8080;
	
	//����һ��Map<String, List<Map<String, Object>>>���������ղ�ѯ���
	List<Map<String, Object>> mytest_list;
	
	//����һ���������飬����������Ҫ���ݵ�ǰ̨��ҳ��
	@SuppressWarnings("rawtypes")
	ArrayList mytest_array = new ArrayList();
	
	//����Dao��Ľӿڶ���MysqlQueryPageDao
	MysqlQueryPage_1_1_Dao mysqlQueryPage_1_1_Dao;
	//MysqlQueryPage_1_1_Dao��setter��getter����
	public MysqlQueryPage_1_1_Dao getMysqlQueryPage_1_1_Dao() {
		return mysqlQueryPage_1_1_Dao;
	}
	public void setMysqlQueryPage_1_1_Dao(
			MysqlQueryPage_1_1_Dao mysqlQueryPage_1_1_Dao) {
		this.mysqlQueryPage_1_1_Dao = mysqlQueryPage_1_1_Dao;
	}

	/*
	 * SpringMVC+MySQL��ѯ��ҳ������1.1�汾��
	 * ��ҳ��������
	 * ��������ҳ��βҳ������ָ��ҳ���û�����ҳ��
	 */
	@RequestMapping("mysqlQueryPage_1_1.action")
	public ModelAndView mysqlQueryPage_1_4(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		//��ȡ���е��ܼ�¼����Ҳ������ҳ��
		TOTAL_RECORDS = mysqlQueryPage_1_1_Dao.getCountsOfTable();
		TOTAL_PAGES = (int) (TOTAL_RECORDS / PAGE_SIZE) + 1;
		//��TOTAL_PAGES���ݵ��ͻ���
		model.addAttribute("count_pages", TOTAL_PAGES);

		//�����ҳ��TOTAL_PAGES <= 10 ������CURRENT_PAGEΪ�գ���ֱ����ʾ���е�ҳ����
		//�����ҳ��TOTAL_PAGES > 10 ������CURRENT_PAGE��Ϊ�գ��������жϣ�
		if(TOTAL_PAGES > 10){
			//��ȡCURRENT_PAGE���û�����ĵ�ǰҳ��
			CURRENT_PAGE = Integer.parseInt(request.getParameter("current_page"));
			//�ѵ�ǰҳ�봫�ݵ��ͻ��ˣ����浽������
			model.addAttribute("previous_page", CURRENT_PAGE);
			//�������
			System.out.println("�����ݿ��в�ѯ���ܼ�¼��TOTAL_RECORDSΪ��" + TOTAL_RECORDS);
			System.out.println("ÿҳ��ʾ�ļ�¼��PAGE_SIZEΪ��" + PAGE_SIZE);
			System.out.println("��ҳ��TOTAL_PAGESΪ��" + TOTAL_PAGES);
			System.out.println("��ǰҳCURRENT_PAGEΪ����" + CURRENT_PAGE);
			//���CURRENT_PAGEС�ڵ���5��Ҳ����˵���û�����2345��4��ҳ�������һ��ҳ�棬��ʱ���ǣ�
			if(CURRENT_PAGE <= 5){
				START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				//����Dao��ķ�����ʵ�ֲ�ѯ����
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
			//���CURRENT_PAGE > 5
			}else if((CURRENT_PAGE > 5) && (CURRENT_PAGE <= TOTAL_PAGES)){
				START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				//����Dao��ķ�����ʵ�ֲ�ѯ����
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
			//���CURRENT_PAGE = PAGE_8080��Ҳ�������һҳ
			}else if(CURRENT_PAGE == PAGE_8080){
				START_POINT = PAGE_SIZE * (TOTAL_PAGES - 1);
				//����Dao��ķ�����ʵ�ֲ�ѯ����
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
			}else{
				//����Dao��ķ�����ʵ�ֲ�ѯ����
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_0();
			}
			//���ù�����generateMapFromInt_1������map
			mytest_array = this.generateMapFromInt_2(TOTAL_PAGES, CURRENT_PAGE);
			//�Ѳ�ѯ�����װ��org.springframework.ui.ModelMap��
			model.addAttribute("mytest",mytest_list);
			model.addAttribute("total_pages", mytest_array);
			//����viewName�Ͳ�ѯ�����model
			return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
		}
		/*
		 * 1��TOTAL_PAGES <= 10
		 */
		else if(TOTAL_PAGES <= 10){
			//��ȡCURRENT_PAGE���û�����ĵ�ǰҳ��
			CURRENT_PAGE = Integer.parseInt(request.getParameter("current_page"));
			//�ѵ�ǰҳ�봫�ݵ��ͻ��ˣ����浽������
			model.addAttribute("previous_page", CURRENT_PAGE);
			//���CURRENT_PAGEС�ڵ���5��Ҳ����˵���û�����2345��4��ҳ�������һ��ҳ�棬��ʱ���ǣ�
			if(CURRENT_PAGE > 0){
				//���CURRENT_PAGE = PAGE_8080��Ҳ�������һҳ
				if(CURRENT_PAGE == PAGE_8080){
					START_POINT = PAGE_SIZE * (TOTAL_PAGES - 1);
				}
				//���CURRENT_PAGE > 0 �� CURRENT_PAGE �������һҳ
				else{
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//����Dao��ķ�����ʵ�ֲ�ѯ����
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
			}
			else{
				//�ѵ�ǰҳ�봫�ݵ��ͻ��ˣ����浽������
				model.addAttribute("previous_page", CURRENT_PAGE);
				//����Dao��ķ�����ʵ�ֲ�ѯ����
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_0();
			}
			//���ù�����generateMapFromInt_1������map
			mytest_array = this.generateMapFromInt_1(TOTAL_PAGES);
			//�Ѳ�ѯ�����װ��org.springframework.ui.ModelMap��
			model.addAttribute("mytest",mytest_list);
			model.addAttribute("total_pages", mytest_array);
			//����viewName�Ͳ�ѯ�����model
			return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
		}
		/*
		 * ��ת����δ��룬˵�����������¼��������
		 */
		else{
			//�ѵ�ǰҳ�봫�ݵ��ͻ��ˣ����浽������
			model.addAttribute("previous_page", CURRENT_PAGE);
			//���Դ���
			System.out.println("��ת����δ��룬˵�����������¼��������");
			//����Dao��ķ�����ʵ�ֲ�ѯ����
			mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_0();
			//���ù�����generateMapFromInt_1������map
			mytest_array = this.generateMapFromInt_1(TOTAL_PAGES);
			//�Ѳ�ѯ�����װ��org.springframework.ui.ModelMap��
			model.addAttribute("mytest",mytest_list);
			model.addAttribute("total_pages", mytest_array);
			//����viewName�Ͳ�ѯ�����model
			return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
		}
	}
	/*
	 * SpringMVC+MySQL��ѯ��ҳ������1.1�汾��
	 * ��һҳ���� ����һҳ����
	 */
	@RequestMapping("mysqlQueryPage_1_1_previous_page.action")
	public ModelAndView mysqlQueryPage_1_1_2(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		//����һ��int������ǰ̨���ݹ�����previous_page��Ҳ�����û������һҳ��ťǰ���ڵ�ҳ����
		int previous_page = 1;
		//����һ��int������ǰ̨���ݹ�����flag���ж��û��ǵ������һҳ��ť�����ǵ������һҳ��ť
		int flag = 0;
		//��ȡ2��������һ����previous_page��һ����flag
		previous_page = Integer.parseInt(request.getParameter("previous_page"));
		flag = Integer.parseInt(request.getParameter("flag"));
		//��ȡ���е��ܼ�¼����Ҳ������ҳ��
		TOTAL_RECORDS = mysqlQueryPage_1_1_Dao.getCountsOfTable();
		TOTAL_PAGES = (int) (TOTAL_RECORDS / PAGE_SIZE) + 1;
		//��TOTAL_PAGES���ݵ��ͻ���
		model.addAttribute("count_pages", TOTAL_PAGES);
		/*
		 * ���TOTAL_PAGES <= 10
		 */
		if(TOTAL_PAGES <= 10){
			//���flag=0������������һҳ��ִ����һҳ������ʵ����һҳ����
			if(flag == 0){
				//1�����previous_page�ǵ�һҳ������ҳ������Ȼ��ת����һҳ
				//2�����previous_page��8080��Ҳ��������ҳ�빦�ܵ����һҳ������ת�������ڶ�ҳ
				//3����������£���ת����previous_page -1��ҳ
				if((previous_page == 1) || (previous_page ==0 )){
					CURRENT_PAGE = 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//2�����previous_page��PAGE_8080��Ҳ��������ҳ�빦�ܵ����һҳ������ת�������ڶ�ҳ
				else if(previous_page == PAGE_8080){
					CURRENT_PAGE = TOTAL_PAGES - 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//3����������£���ת����previous_page -1��ҳ
				else{
					CURRENT_PAGE = previous_page - 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//����Dao��ķ�����ʵ�ֲ�ѯ����
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
				//���ù�����generateMapFromInt_1������map
				mytest_array = this.generateMapFromInt_1(TOTAL_PAGES);
				//�Ѳ�ѯ�����װ��org.springframework.ui.ModelMap��
				//�ѵ�ǰҳ�봫�ݵ��ͻ��ˣ����浽������
				model.addAttribute("previous_page", CURRENT_PAGE);
				model.addAttribute("mytest",mytest_list);
				model.addAttribute("total_pages", mytest_array);
				//����viewName�Ͳ�ѯ�����model
				return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
			}
			//���flag=1������������һҳ��ִ����һҳ������ʵ����һҳ����
			else{
				//1�����previous_page�����һҳ����PAGE_8080������Ȼ��ת�����һҳ��
				if((previous_page == PAGE_8080) || (previous_page == TOTAL_PAGES)){
					CURRENT_PAGE = TOTAL_PAGES;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//2����������£���ת����previous_page + 1��ҳ��
				else{
					CURRENT_PAGE = previous_page + 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//����Dao��ķ�����ʵ�ֲ�ѯ����
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
				//���ù�����generateMapFromInt_1������map
				mytest_array = this.generateMapFromInt_1(TOTAL_PAGES);
				//�Ѳ�ѯ�����װ��org.springframework.ui.ModelMap��
				//�ѵ�ǰҳ�봫�ݵ��ͻ��ˣ����浽������
				model.addAttribute("previous_page", CURRENT_PAGE);
				model.addAttribute("mytest",mytest_list);
				model.addAttribute("total_pages", mytest_array);
				//����viewName�Ͳ�ѯ�����model
				return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
			}
		}
		//���TOTAL_PAGES > 10
		else{
			//���flag=0������������һҳ��ִ����һҳ������ʵ����һҳ����
			if(flag == 0){
				//1�����previous_page�ǵ�һҳ������ҳ������Ȼ��ת����һҳ
				//2�����previous_page��8080��Ҳ��������ҳ�빦�ܵ����һҳ������ת�������ڶ�ҳ
				//3����������£���ת����previous_page -1��ҳ
				if((previous_page == 1) || (previous_page ==0 )){
					CURRENT_PAGE = 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//2�����previous_page��PAGE_8080��Ҳ��������ҳ�빦�ܵ����һҳ������ת�������ڶ�ҳ
				else if(previous_page == PAGE_8080){
					CURRENT_PAGE = TOTAL_PAGES - 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//3����������£���ת����previous_page -1��ҳ
				else{
					CURRENT_PAGE = previous_page - 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//����Dao��ķ�����ʵ�ֲ�ѯ����
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
				//���ù�����generateMapFromInt_1������map
				mytest_array = this.generateMapFromInt_2(TOTAL_PAGES, CURRENT_PAGE);
				//�Ѳ�ѯ�����װ��org.springframework.ui.ModelMap��
				//�ѵ�ǰҳ�봫�ݵ��ͻ��ˣ����浽������
				model.addAttribute("previous_page", CURRENT_PAGE);
				model.addAttribute("mytest",mytest_list);
				model.addAttribute("total_pages", mytest_array);
				//����viewName�Ͳ�ѯ�����model
				return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
			}
			//���flag=1������������һҳ��ִ����һҳ������ʵ����һҳ����
			else{
				//1�����previous_page�����һҳ����PAGE_8080������Ȼ��ת�����һҳ��
				if((previous_page == PAGE_8080) || (previous_page == TOTAL_PAGES)){
					CURRENT_PAGE = TOTAL_PAGES;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//2����������£���ת����previous_page + 1��ҳ��
				else{
					CURRENT_PAGE = previous_page + 1;
					START_POINT = PAGE_SIZE * (CURRENT_PAGE - 1);
				}
				//����Dao��ķ�����ʵ�ֲ�ѯ����
				mytest_list = mysqlQueryPage_1_1_Dao.mysqlQueryPage_1_3(START_POINT);
				//���ù�����generateMapFromInt_1������map
				mytest_array = this.generateMapFromInt_2(TOTAL_PAGES, CURRENT_PAGE);
				//�Ѳ�ѯ�����װ��org.springframework.ui.ModelMap��
				//�ѵ�ǰҳ�봫�ݵ��ͻ��ˣ����浽������
				model.addAttribute("previous_page", CURRENT_PAGE);
				model.addAttribute("mytest",mytest_list);
				model.addAttribute("total_pages", mytest_array);
				//����viewName�Ͳ�ѯ�����model
				return new ModelAndView("/MySQLQueryPage_1_1.jsp",model);
			}
		}
	}
	/*
	 * ������һ����ҳ��TOTAL_PAGESС��10�����
	 * ���ݴ�����������֣�����һ�����飬���ҳ��С��10���������ҳ���װ��ȥ�����ݵ�ǰ̨
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList generateMapFromInt_1(int TOTAL_PAGES){
		//����һ����������
		ArrayList mytest_array = new ArrayList();
		//����Ҳ��Ҫ����һ���жϣ����������ǰ���չ�����CURRENT_PAGE����0����ʱ���ǵ�һ�μ��ص�ʱ��
		//��ʱ����ֻ����ǰ10��ҳ�뵽ǰ̨
		//�����ݷ�װ��ArrayList��
		for(int i=0;i<TOTAL_PAGES;i++){
			mytest_array.add(i, i+1);
		}
		return mytest_array;
	}
	
	/*
	 * �����������ҳ��TOTAL_PAGES����10�����
	 * ���ݴ�����������֣�����һ�����飬ֻ������Ҫ��ʾ��10��ҳ�뵽ǰ̨
	 * ��װ��current_pageΪ���ĵ�10��ҳ�룬��������Լ��current_pageǰ���4��
	 * ��current_page�����5����һ��10��ҳ�롣
	 * ���������
	 * 1������û�������ǵ� 1/2/3/4ҳ��������ҳ������Ӧ����ʾ��1ҳ����10ҳ�������ܳ��ָ�����
	 * 2������û�������ǵ�����1/2/3/4����βҳ������Ӧ����ʾ���10ҳ�������ܳ��ִ���TOTAL_PAGES��ҳ�룻
	 * 3�����������
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList generateMapFromInt_2(int TOTAL_PAGES,int CURRENT_PAGE){
		//����һ����������
		ArrayList mytest_array_10 = new ArrayList();
		//���TOTAL_PAGES - CURRENT_PAGE < 5��Ҳ����˵��ǰҳCURRENT_PAGE���治������ʾ5��ҳ���ˡ�
		if(TOTAL_PAGES - CURRENT_PAGE < 5){
			//��ʼҳ��
			int start_page = TOTAL_PAGES;
		    //�����ݷ�װ��map�У��������
			for(int i=0;i<10;i++){
				mytest_array_10.add(i, start_page - (9 - i));
			}
		//���TOTAL_PAGES - CURRENT_PAGE >= 5,�����¼��������
		//���CURRENT_PAGE < 5��Ҳ����˵�û��������ǰ4ҳ��������ҳ
		}else if(CURRENT_PAGE < 5){
			//��ʼҳ��
			int start_page = 1;
		    //�����ݷ�װ��map��
			for(int i=0;i<10;i++){
				mytest_array_10.add(i, start_page + i);
			}
		}
		//���(CURRENT_PAGE > 5) && ((CURRENT_PAGE - TOTAL_PAGES) > 5)���������
		else{
			//��ʼҳ��
			int start_page = CURRENT_PAGE - 4;
		    //�����ݷ�װ��map��
			for(int i=0;i<10;i++){
				mytest_array_10.add(i, start_page + i);
			}
		}
		return mytest_array_10;
	}
}