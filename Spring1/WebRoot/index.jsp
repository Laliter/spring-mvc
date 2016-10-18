<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <base href="<%=basePath%>">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>share news, share happiness</title>
	<link rel="stylesheet" href="CSS/myapplications.css" type="text/css" />
</head>

<body onclick="getUrl(arguments[0])">
<input type="hidden" id="base_path" value="<%=basePath%>"/>
欢迎您：${username}<br>  
<a href="login.jsp">重新登录</a><br>  


<form action="contextInsertDao.action?username=${username}" method="post">
<br>
   <textarea name="context" cols="45" rows="8" ></textarea> 
    <p><input name="submit" type="submit" value="发表">
    </p>
    </form>
    
  <a href="<%=basePath%>mysqlQueryDao.action" >首页</a> 

<div id="container">   
	
       <tbody>
       	
           <c:forEach items="${mytest}" var="item">
       	<tr>
           	<td>${item.username}</td>
           	<td>发表了：</td>
               <td>${item.context}</td>
              
               <td><a href="<%=basePath%>commentQueryDao.action?newsid=${item.newsid}&username=${username}" >更多</a></td>
               <br>
               <br>
               <br>
           </tr>
           </c:forEach>
       </tbody>
	
</div>

<br>
</body>
</html>
