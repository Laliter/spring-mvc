package com.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.CommentQueryDao;


@Controller
public class CommentQueryController {
	//����Dao��Ľӿڶ���MysqlQueryPageDao
	CommentQueryDao commentQueryDao;
	
			//MysqlQueryPageDao��setter��getter����
			public CommentQueryDao getCommentQueryDao() {
				return commentQueryDao;
			}
			public void setCommentQueryDao(
				CommentQueryDao commentQueryDao) {
				this.commentQueryDao = commentQueryDao;
			}
			
			@RequestMapping("commentQueryDao.action")
			public ModelAndView commentQuery(ModelMap model,HttpServletRequest request,HttpServletResponse response){
				
				List<Map<String, Object>> mytest_list;
				System.out.println("OK!");
				
				mytest_list = commentQueryDao.selectRecord(Integer.parseInt(request.getParameter("newsid")));
				
				//�Ѳ�ѯ�����װ��org.springframework.ui.ModelMap��
				model.addAttribute("mytest",mytest_list);
				
				
				//����viewName�Ͳ�ѯ�����model
				return new ModelAndView("/MysqlShowById.jsp",model);
			
			}
}
