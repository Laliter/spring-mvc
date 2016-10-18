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
	
	//����һ��flag��������ʶ��ѯ���
	boolean flag = false;
	
	//����һ��int���͵ı�������������ҳ�洫�ݹ����Ĳ���
	int myid = 0;
	//����һ��String���͵ı�������������ҳ�洫�ݹ����Ĳ���
	String myname = null;
	
	//����dao����MysqlQueryCriteriaDaoע��MysqlQueryCriteriaController
	MysqlQueryCriteriaDao mysqlQueryCriteriaDao;
	//MysqlQueryCriteriaDaoImpl�����setter��getter����
	public MysqlQueryCriteriaDao getMysqlQueryCriteriaDao() {
		return mysqlQueryCriteriaDao;
	}

	public void setMysqlQueryCriteriaDao(MysqlQueryCriteriaDao mysqlQueryCriteriaDao) {
		this.mysqlQueryCriteriaDao = mysqlQueryCriteriaDao;
	}
	
	/*
	 * ��һ�ַ�������request����������ҳ�洫�ݹ�����������
	 */
	/*
	@RequestMapping("mysqlQueryCriteria.action")
	public ModelAndView mysqlQueryCriteriaByRequest(HttpServletRequest request,HttpServletResponse response){
		
		//myid = (Integer) request.getAttribute("myid"); 
		myid = Integer.parseInt(request.getParameter("myid"));
		//�������
		System.out.println("��JSPҳ���ȡ���Ĳ���Ϊ��" + myid);
		//����һ��ʱ�����date_before_query_object���ڶ���һ��long����date_before_query���������µ�ǰ��ʱ�̣�
		Date date_before_query_object = new Date();
		long date_before_query = 0;
		date_before_query = date_before_query_object.getTime();
		flag = mysqlQueryCriteriaDao.selectRecordByName(myid);
		
		//�����ѯ�ɹ�������flag=true������ӡ����ִ��ʱ�䵽����̨������flag=false��
		if(flag == true){
			//����һ��ʱ�����date_after_query_object���ڶ���һ��long����date_after_query���������µ�ǰ��ʱ�̣�
			Date date_after_query_object = new Date();
			long date_after_query = 0;
			date_after_query = date_after_query_object.getTime();
			//�������
			System.out.println("��ѯ���õ�ʱ���ԼΪ��" + (date_after_query - date_before_query));
			System.out.println("��ѯ�ɹ���MysqlQueryCriteriaController���е�mysqlQueryCriteriaByRequest����...");
			return new ModelAndView("/MysqlQuerySuccess.jsp");
		}
		else{
			System.out.println("��ѯʧ�ܣ�MysqlQueryCriteriaController���е�mysqlQueryCriteriaByRequest����...");
			return new ModelAndView("/MysqlQueryFail.jsp");
		}
		
	}
    */
	/*
	 * �ڶ��ַ�������@ModelAttributeע�ⷽʽ������ҳ�洫�ݹ����Ĳ���
	 */

	@RequestMapping("mysqlQueryCriteria.action")
	public ModelAndView mysqlQueryCriteriaByModelAttribute(@RequestParam("myid")int myid ,ModelMap model){
		//�������
		System.out.println("ʹ��SpringMVCע�ⷽʽ����JSPҳ���ȡ��myid��ֵΪ��" + myid);
		//����һ��ʱ�����date_before_query_object���ڶ���һ��long����date_before_query���������µ�ǰ��ʱ�̣�
		Date date_before_query_object = new Date();
		long date_before_query = 0;
		date_before_query = date_before_query_object.getTime();
		
		flag = mysqlQueryCriteriaDao.selectRecordByName(myid);

		//�����ѯ�ɹ�������flag=true������ӡ����ִ��ʱ�䵽����̨������flag=false��
		if(flag == true){
			//����һ��ʱ�����date_after_query_object���ڶ���һ��long����date_after_query���������µ�ǰ��ʱ�̣�
			Date date_after_query_object = new Date();
			long date_after_query = 0;
			date_after_query = date_after_query_object.getTime();
			//�������
			System.out.println("��ѯ���õ�ʱ���ԼΪ��" + (date_after_query - date_before_query));
			System.out.println("��ѯ�ɹ���MysqlQueryCriteriaController���е�mysqlQueryCriteriaByModelAttribute����...");
			return new ModelAndView("/MysqlQuerySuccess.jsp");
		}
		else{
			System.out.println("��ѯʧ�ܣ�MysqlQueryCriteriaController���е�mysqlQueryCriteriaByModelAttribute����...");
			return new ModelAndView("/MysqlQueryFail.jsp");
		}
	}
	
	/*
	 * MySQL��ѯ���ԣ�ͨ��myid��myname2���ֶ�����ѯ
	 */
	@RequestMapping("mysqlQueryCriteriaByName.action")
	public ModelAndView mysqlQueryCriteriaByRequest(HttpServletRequest request,HttpServletResponse response){
		
		myid = Integer.parseInt(request.getParameter("myid"));
		myname = request.getParameter("myname");
		//�������
		System.out.println("��JSPҳ���ȡ���Ĳ���Ϊ��" + myid);
		//����һ��ʱ�����date_before_query_object���ڶ���һ��long����date_before_query���������µ�ǰ��ʱ�̣�
		Date date_before_query_object = new Date();
		long date_before_query = 0;
		date_before_query = date_before_query_object.getTime();
		flag = mysqlQueryCriteriaDao.selectRecordByMyname(myid, myname);
		
		//�����ѯ�ɹ�������flag=true������ӡ����ִ��ʱ�䵽����̨������flag=false��
		if(flag == true){
			//����һ��ʱ�����date_after_query_object���ڶ���һ��long����date_after_query���������µ�ǰ��ʱ�̣�
			Date date_after_query_object = new Date();
			long date_after_query = 0;
			date_after_query = date_after_query_object.getTime();
			//�������
			System.out.println("��ѯ���õ�ʱ���ԼΪ��" + (date_after_query - date_before_query));
			System.out.println("��ѯ�ɹ���MysqlQueryCriteriaController���е�mysqlQueryCriteriaByRequest����...");
			return new ModelAndView("/MysqlQuerySuccess.jsp");
		}
		else{
			System.out.println("��ѯʧ�ܣ�MysqlQueryCriteriaController���е�mysqlQueryCriteriaByRequest����...");
			return new ModelAndView("/MysqlQueryFail.jsp");
		}
		
	}

}
