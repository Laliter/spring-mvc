<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>SpringMVC+MySQL查询分页技术（1.1版本）</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="CSS/myapplications.css" type="text/css" />
<script type="text/javascript">
//首页功能
function gotoFirstPage(){
    //获取当前项目的根路径
    var base_path = document.getElementById("base_path").value;
    //当前的action请求
    var action_path = 'mysqlQueryPage_1_1.action?current_page=1';
    //需要发送的完整请求
    var path = base_path + action_path;
    //alert(path);
    location.href = path;
}

//到达尾页功能
function get_url_from_a(){
    //获取当前项目的根路径
    var base_path = document.getElementById("base_path").value;
    //当前的action请求
    var action_path = 'mysqlQueryPage_1_1.action';
    //获取用户输入文本框中的页码
    var page_appointed = document.getElementById("page_appointed").value;
    
    //对用户输入的值进行校验
    //1，如果用户输入的是字母或者其他特殊字符，提示用户输入数字
    var regex = /^[0-9]+$/;
    if(!regex.test(page_appointed)){
        alert("您输入的不是有效的页码...");
    }
    else{
        var path = base_path + action_path + '?current_page=' + page_appointed;
        //测试输出
        location.href = path;
    }
}

//获取被选中的a标签的值
function getUrl(e) {
    e = e || event;
    var target = e.target || e.srcElement,
        href;
    if (target.tagName === 'A') {
        href = target.href;
    }
}

//上一页功能
function goToPreviousPage(){
    //获取当前项目的根路径
    var base_path = document.getElementById("base_path").value;
    //当前的action请求
    var action_path = 'mysqlQueryPage_1_1_previous_page.action';
    //获取隐藏域中，用户上一次访问的页码
    var previous_page = document.getElementById("previous_page").value;
    //alert(previous_page);
    //完整的path
    var path = base_path + action_path + '?previous_page=' + previous_page + '&flag=0';
    //发送请求
    location.href = path;
}

function goToNextPage(){
    //获取当前项目的根路径
    var base_path = document.getElementById("base_path").value;
    //当前的action请求
    var action_path = 'mysqlQueryPage_1_1_previous_page.action';
    //获取隐藏域中，用户上一次访问的页码
    var previous_page = document.getElementById("previous_page").value;
    //alert(previous_page);
    //完整的path
    var path = base_path + action_path + '?previous_page=' + previous_page + '&flag=1';
    //发送请求
    location.href = path;
}

</script>
</head>
<body onclick="getUrl(arguments[0])">

<!-- 隐藏标签传值 -->
<input type="hidden" id="base_path" value="<%=basePath%>"/>
<input type="hidden" id="previous_page" value="${previous_page}"/>

<!-- 分页操作按钮栏 -->
<div id="container">   
	<table class="zebra">
		<thead>
        	<tr>
			<th><a href="javascript:void(0)" onclick="gotoFirstPage();">首</a></th>
			<th><a href="javascript:void(0)" onclick="goToPreviousPage();return false;">上</a></th>
<c:forEach items="${total_pages}" var="item">
            <th><a href="<%=basePath%>mysqlQueryPage_1_1.action?current_page=${item}" onclick="getUrl(e);">${item}</a></th>
</c:forEach>
			<th><a href="javascript:void(0)" onclick="goToNextPage();return false;">下</a></th>
			<th><a href="<%=basePath%>mysqlQueryPage_1_1.action?current_page=8080">尾</a></th>
			<th>到第<input type="text" name="page_appointed" id="page_appointed"/>页
			<a href="#" onclick="get_url_from_a();return false;">确定</a>
			</th>
			<th>共${count_pages }页</th>
            </tr>
		</thead>
	</table>
</div>

<div id="container">   
	<table class="zebra">
    <caption>SpringMVC+MySQL查询分页技术（1.1版本）</caption>
		<thead>
        	<tr>
				<th>项目名称</th>
				<th>简单描述</th>
				<th>用到的技术</th>
				<th>查看详情</th>
            </tr>
		</thead>
        <tbody>
        	<tr>
            	<td>MySQL数据库中的字段名</td>
                <td>MySQL数据库中的字段名</td>
                <td>MySQL数据库中的字段名</td>
                <td></td>
            </tr>
            <c:forEach items="${mytest}" var="item">
        	<tr>
            	<td>${item.myid}</td>
                <td>${item.mydata}</td>
                <td>${item.myname}</td>
                <td>操作</td>
            </tr>
            </c:forEach>
        </tbody>
	</table>
</div>
</body>
</html>
