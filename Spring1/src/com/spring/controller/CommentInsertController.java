package com.spring.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.CommentInsertDao;
@Controller
public class CommentInsertController {

	//定义Dao层的接口对象MysqlQueryPageDao
	CommentInsertDao commentInsertDao;
	
	//MysqlQueryPageDao的setter、getter方法
	public CommentInsertDao getCommentInsertDao() {
		return commentInsertDao;
	}
	public void setCommentInsertDao(
			CommentInsertDao commentInsertDao) {
		this.commentInsertDao = commentInsertDao;
	}
	
	@RequestMapping("commentInsertDao.action")
	public ModelAndView contextInsert(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		List<Map<String, Object>> mytest_list;
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(request.getParameter("newsid")+request.getParameter("context")+request.getParameter("username"));
		System.out.println("contextInsertController");
		mytest_list = commentInsertDao.insertRecord(Integer.parseInt(request.getParameter("newsid")),request.getParameter("username"),request.getParameter("context"));
		model.addAttribute("mytest",mytest_list);
		//返回viewName和查询结果集model
		return new ModelAndView("/MysqlShowById.jsp",model);
	
	}
}
