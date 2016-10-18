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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <bodyonclick="getUrl(arguments[0])">
<input type="hidden" id="base_path" value="<%=basePath%>"/>
  
  <div id="container">   
	
       <tbody>
       	
           <c:forEach items="${mytest}" var="item">
       	<tr>
           	<td>${item.hisname}</td>
           	<td>评论说:</td>
               <td>${item.text}</td>
               
               
           </tr>
        <br>
           </c:forEach>
       </tbody>
	
</div>
<%String nid=request.getParameter("newsid");
 %>
    <form action="commentInsertDao.action?username=${username}" method="post">

<input type="hidden" name="newsid" value="<%=request.getParameter("newsid")%>">
   <textarea name="context" cols="45" rows="8" aria-required="true"></textarea> 
    <p><input name="submit" type="submit" value="发表">
    
</form>
   
  </body>
</html>
