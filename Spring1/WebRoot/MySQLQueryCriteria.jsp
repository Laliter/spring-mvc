<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<style type="text/css">
        #alertMsg {
            display: none;
            width: 400px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 1px 1px 10px black;
            padding: 10px;
            font-size: 12px;
            position: absolute;
            text-align: center;
            background: #fff;
            z-index: 100000;
        }

        #alertMsg_info {
            color: black;
            padding: 2px 15px;
            line-height: 1.6em;
            text-align: center;
        }

        #alertMsg_btn1, #alertMsg_btn2 {
            display: inline-block;
            background: url(images/gray_btn.png) no-repeat left top;
            padding-left: 3px;
            color: #000000;
            font-size: 12px;
            text-decoration: none;
            margin-right: 10px;
            cursor: pointer;
        }

        #alertMsg_btn1 cite, #alertMsg_btn2 cite {
            line-height: 24px;
            display: inline-block;
            padding: 0 13px 0 10px;
            background: url(images/gray_btn.png) no-repeat right top;
            font-style: normal;
        }
</style>

<script>

function alertMsg(mode) { 
    //先判断输入是否为空
    var input_value = document.getElementById("record1").value;
    var input_value2 = document.getElementById("record3").value;
    if(input_value < 1){
        alert("请输入合法的值...");
        return false;
    }else if(input_value2 < 1 ){
        alert("请输入合法的值...");
        return false;
    }else{
        //mode为空，即只有一个确认按钮，mode为1时有确认和取消两个按钮
        msg = '正在为您查询数据，请稍后...';
        //mode = mode || 0;
        var top = document.body.scrollTop || document.documentElement.scrollTop;
        var isIe = (document.all) ? true : false;
        var isIE6 = isIe && !window.XMLHttpRequest;
        var sTop = document.documentElement.scrollTop || document.body.scrollTop;
        var sLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
        var winSize = function(){
            var xScroll, yScroll, windowWidth, windowHeight, pageWidth, pageHeight;
            // innerHeight获取的是可视窗口的高度，IE不支持此属性
            if (window.innerHeight && window.scrollMaxY) {
                xScroll = document.body.scrollWidth;
                yScroll = window.innerHeight + window.scrollMaxY;
            } else if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac
                xScroll = document.body.scrollWidth;
                yScroll = document.body.scrollHeight;
            } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
                xScroll = document.body.offsetWidth;
                yScroll = document.body.offsetHeight;
            }

            if (self.innerHeight) {    // all except Explorer
                windowWidth = self.innerWidth;
                windowHeight = self.innerHeight;
            } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
                windowWidth = document.documentElement.clientWidth;
                windowHeight = document.documentElement.clientHeight;
            } else if (document.body) { // other Explorers
                windowWidth = document.body.clientWidth;
                windowHeight = document.body.clientHeight;
            }

            // for small pages with total height less then height of the viewport
            if (yScroll < windowHeight) {
                pageHeight = windowHeight;
            } else {
                pageHeight = yScroll;
            }

            // for small pages with total width less then width of the viewport
            if (xScroll < windowWidth) {
                pageWidth = windowWidth;
            } else {
                pageWidth = xScroll;
            }

            return{
                'pageWidth':pageWidth,
                'pageHeight':pageHeight,
                'windowWidth':windowWidth,
                'windowHeight':windowHeight
            };
        }();
        //alert(winSize.pageWidth);
        //遮罩层
        var styleStr = 'top:0;left:0;position:absolute;z-index:10000;background:#666;width:' + winSize.pageWidth + 'px;height:' +  (winSize.pageHeight + 30) + 'px;';
        styleStr += (isIe) ? "filter:alpha(opacity=80);" : "opacity:0.8;"; //遮罩层DIV
        var shadowDiv = document.createElement('div'); //添加阴影DIV
        shadowDiv.style.cssText = styleStr; //添加样式
        shadowDiv.id = "shadowDiv";
        //如果是IE6则创建IFRAME遮罩SELECT
        if (isIE6) {
            var maskIframe = document.createElement('iframe');
            maskIframe.style.cssText = 'width:' + winSize.pageWidth + 'px;height:' + (winSize.pageHeight + 30) + 'px;position:absolute;visibility:inherit;z-index:-1;filter:alpha(opacity=0);';
            maskIframe.frameborder = 0;
            maskIframe.src = "about:blank";
            shadowDiv.appendChild(maskIframe);
        }
        document.body.insertBefore(shadowDiv, document.body.firstChild); //遮罩层加入文档
        //弹出框
        var styleStr1 = 'display:block;position:fixed;_position:absolute;left:' + (winSize.windowWidth / 2 - 200) + 'px;top:' + (winSize.windowHeight / 2 - 250) + 'px;_top:' + (winSize.windowHeight / 2 + top - 150)+ 'px;'; //弹出框的位置
        var alertBox = document.createElement('div');
        alertBox.id = 'alertMsg';
        alertBox.style.cssText = styleStr1;
        //创建弹出框里面的内容P标签
        var alertMsg_info = document.createElement('p');
        alertMsg_info.id = 'alertMsg_info';
        alertMsg_info.innerHTML = msg;
        alertBox.appendChild(alertMsg_info);
        Box.appendChild(btn1);
        
        //创建图片效果里面的内容P标签
        var alertImg = document.createElement('img');
        alertImg.id = 'alertImg';
        alertImg.src = 'images/zebratable.png';
        alertBox.appendChild(alertImg);

        document.body.appendChild(alertBox);
    }
}

</script>
  </head>
  
<body>

<div id="container">   

	<table class="zebra">
    <caption>My Application Two：MySQL数据库查询优化</caption>
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
            	<td>MySQL数据库查询优化</td>
                <td>7万条记录的table的单表查询优化</td>
                <td>SpringMVC3.0+MySQL数据库5.5</td>
                <td></td>
            </tr>
        </tbody>
	</table>
</div>

<div>

    <form class="STYLE-NAME">
    <h2>MySQL单表查询优化技术1.0
    <span></span>
    </h2>
    <h4>功能描述概要：</h4>
    
    <label>
    <span>&nbsp;&nbsp;1，数据库名称：learn_system</span>
    </label><br>
    <label>
    <span>&nbsp;&nbsp;2，表名mytest</span>
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
    <span>模拟实验，是在不考虑网络延迟的情况下进行的。</span>
    </label>
<form action="mysqlQueryCriteria.action" method="post" class="STYLE-NAME"> 
    <label>
    <span>请输入一个数据库中的记录，然后等待系统查询以后返回的结果...</span><br>
    <input id="record1" type="text" name="myid"  />
    <input id="record2" type="submit" name="record2" onclick="alertMsg(1);" />
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
