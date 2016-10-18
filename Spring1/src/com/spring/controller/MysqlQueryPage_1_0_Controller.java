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
 * SpringMVC+MySQL��ѯ��ҳ������1.0�汾��
 * ���Ʋ��� public class MysqlQueryPage_1_0_Controller
 */

@Controller
public class MysqlQueryPage_1_0_Controller {
	
	//����һ��ȫ�ֱ�����������ʾ��ҳ���������ҳ��С��10��������ܴ����쳣����ΪС��10ҳ�Ĺ��ܻ�û������
	public static int TOTAL_PAGES = 0;
	
	//����һ��int��������������ǰ̨���������������ʾ��ǰ��Ҫ��ѯ��ҳ��
	public static int CURRENT_PAGE = 1;
	
	//����һ��ȫ�ֱ�����������ʾÿҳ��ʾ�ļ�¼����PAGE_SIZE��������������ݿ��ѯ�Ĳ���Ҫͬ��
	private static int PAGE_SIZE = 100;
	
	//����Dao��Ľӿڶ���MysqlQueryPageDao
	MysqlQueryPage_1_0_Dao mysqlQueryPage_1_0_Dao;
	
	//MysqlQueryPageDao��setter��getter����
	public MysqlQueryPage_1_0_Dao getMysqlQueryPage_1_0_Dao() {
		return mysqlQueryPage_1_0_Dao;
	}
	public void setMysqlQueryPage_1_0_Dao(
			MysqlQueryPage_1_0_Dao mysqlQueryPage_1_0_Dao) {
		this.mysqlQueryPage_1_0_Dao = mysqlQueryPage_1_0_Dao;
	}
	
	@RequestMapping("mysqlQueryPage_1_0.action")
	public ModelAndView mysqlQueryPage_1_4(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		//����һ��long���������������ܼ�¼��
		long count_of_mytest = 0;
		//����һ��Map<String, List<Map<String, Object>>>���������ղ�ѯ���
		List<Map<String, Object>> mytest_list;
		//����һ����������
		@SuppressWarnings("rawtypes")
		ArrayList mytest_array = new ArrayList();
		//����һ��int������������ʶ��ѯ�α����start_point
		int start_point = 0;
		
		//��ȡ���е��ܼ�¼����Ҳ������ҳ��
		count_of_mytest = mysqlQueryPage_1_0_Dao.getCountsOfTable();
		TOTAL_PAGES = (int) (count_of_mytest / PAGE_SIZE) + 1;
		
		//������һ���жϣ�
		//�����ҳ��TOTAL_PAGES <= 10 ������current_pageΪ�գ���ֱ����ʾ���е�ҳ����
		//�����ҳ��TOTAL_PAGES > 10 ������current_page��Ϊ�գ��������жϣ�
		if((request.getParameter("current_page")!= null) && (TOTAL_PAGES > 10)){
			//��ȡcurrent_page���û�����ĵ�ǰҳ��
			CURRENT_PAGE = Integer.parseInt(request.getParameter("current_page"));
			
			//���current_pageС�ڵ���5��Ҳ����˵���û�����2345��4��ҳ�������һ��ҳ�棬��ʱ���ǣ�
			if(CURRENT_PAGE <= 5){
				//����һ��int������������ʶ��ѯ�α����start_point
				start_point = PAGE_SIZE * (CURRENT_PAGE - 1);
			}else if((CURRENT_PAGE > 5) && (CURRENT_PAGE <= TOTAL_PAGES)){
				//����һ��int������������ʶ��ѯ�α����start_point
				start_point = 100 * (CURRENT_PAGE - 1);
			}else if(CURRENT_PAGE == 8080){
				//����һ��int������������ʶ��ѯ�α����start_point
				start_point = PAGE_SIZE * (TOTAL_PAGES - 1);
			}else{
				//�쳣
				System.out.println("����������������û�в�����쳣...");
			}
			
			//����Dao��ķ�����ʵ�ֲ�ѯ����
			mytest_list = mysqlQueryPage_1_0_Dao.mysqlQueryPage_1_3(start_point);
			//���ù�����
			mytest_array = this.generateMapFromInt_2(TOTAL_PAGES,CURRENT_PAGE);
			//�Ѳ�ѯ�����װ��org.springframework.ui.ModelMap��
			model.addAttribute("mytest",mytest_list);
			model.addAttribute("total_pages", mytest_array);
			
			//����viewName�Ͳ�ѯ�����model
			return new ModelAndView("/MySQLQueryPage_1_0.jsp",model);
		}
		else{
			
			//����Dao��ķ�����ʵ�ֲ�ѯ����
			mytest_list = mysqlQueryPage_1_0_Dao.mysqlQueryPage_1_0();
			//���ù�����generateMapFromInt_1������map
			mytest_array = this.generateMapFromInt_1(TOTAL_PAGES);
			//�Ѳ�ѯ�����װ��org.springframework.ui.ModelMap��
			model.addAttribute("mytest",mytest_list);
			model.addAttribute("total_pages", mytest_array);
			
			//����viewName�Ͳ�ѯ�����model
			return new ModelAndView("/MySQLQueryPage_1_0.jsp",model);
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
