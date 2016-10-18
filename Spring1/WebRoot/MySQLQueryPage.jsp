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
    
    <title>MySQL数据库查询优化</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link rel="stylesheet" href="CSS/myapplications.css" type="text/css" />

  </head>
  
<body>

<div id="container">   

	<table class="zebra">
    <caption>My Application Three：MySQL数据库查询分页技术</caption>
		<thead>
        	<tr>
				<th>版本</th>
				<th>功能描述</th>
				<th>使用到的技术</th>
				<th>体验功能</th>
            </tr>
		</thead>
        <tbody>
        	<tr>
            	<td>1.0</td>
                <td>首页+尾页+所有页展示+到达指定页码</td>
                <td>SpringMVC3.0+JS+CSS</td>
                <td><a href="<%=basePath%>mysqlQueryPage_1_0.action?current_page=1" target="view_window">进入体验一下</a></td>
            </tr>
        	<tr>
            	<td>1.1（page_size自定义）</td>
                <td>上一页、下一页、下拉框、增加等功能</td>
                <td>SpringMVC3.0+JS+CSS</td>
                <td><a href="<%=basePath%>mysqlQueryPage_1_1.action?current_page=1" target="view_window">进入体验一下</a></td>
            </tr>
        </tbody>
	</table>
</div>

<div>

    <form class="STYLE-NAME">
    <h2>MySQL查询分页技术原理
    <span></span>
    </h2>
    <h4>简要概述</h4>
    
    <label>
    <span>&nbsp;&nbsp;1，目前实现分页功能，主要有2种方式：<br>
    &nbsp;&nbsp;&nbsp;&nbsp;第一种每次翻页都修改SQL，向SQL传入相关参数去数据库实时查出该页的数据并显示；<br>
    &nbsp;&nbsp;&nbsp;&nbsp;第二种查出数据库某张表的全部数据，再通过在业务逻辑里面进行处理去取得某些数据并显示。</span>
    </label><br>
    <label>
    <span>&nbsp;&nbsp;2，Spring如何返回JSON数据，这个是处理分页的一个难点</span>
    </label><br>
    <label>
    <span>&nbsp;&nbsp;3，mytest表中有三个字段：myid、mydata、myname</span>
    </label><br>
    <label>
    <span>&nbsp;&nbsp;4，mytest表中共有72993条记录</span>
    </label><br>

    <h4>实现的功能：</h4>
    <label>
    <span>&nbsp;&nbsp;1，通过mydata和myname2个字段，估算出查询数据表中第1条记录所用的时间（第1条记录为：1，A0，B0）；</span><br>
    <span>&nbsp;&nbsp;2，通过mydata和myname2个字段，估算出查询数据表中第36000条记录所用的时间（第36000条记录为：36000，A6879，B6879）；</span><br>
    <span>&nbsp;&nbsp;3，通过mydata和myname2个字段，估算出查询数据表中第72993条记录所用的时间（第72993条记录为：72993，A2999，B2999）；</span><br>
    </label>
    </form>
</div>

<div>
    <h2>功能演示
    <span></span>
    </h2>
    <label>
    <span>SpringMVC分页1.0版本</span>
    </label>
<form action="mysqlQueryPage.action" method="post" class="STYLE-NAME"> 
    <label>
    <span>不传递参数到服务器，直接查询结果，马上体验一下吧</span><br>
    <input id="record1" type="text" name="myid"  />
    <input id="record2" type="submit" name="record2" value="点击查询" />
    </label>
</form>

<form action="mysqlQueryCriteriaByName.action" method="post" class="STYLE-NAME"> 
    <label>
    <span>请输入一个数据库中的记录，然后等待系统查询以后返回的结果...</span><br>
    id:<input id="record3" type="text" name="myid"  />
    myname:<input id="record4" type="text" name="myname"  />
    <input id="record2" type="submit" name="submit" onclick="alertMsg_2(1);" />
    </label>
</form>

</div>
   
</body>
</html>
