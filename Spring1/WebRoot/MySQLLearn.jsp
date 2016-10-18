<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>My Applications By 2015</title>
	<link rel="stylesheet" href="CSS/myapplications.css" type="text/css" />
</head>
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
    var input_value = document.getElementById("name").value;
    if(input_value < 1 ){
        alert("至少导入1条数据...");
        return false;
    }else{
        //mode为空，即只有一个确认按钮，mode为1时有确认和取消两个按钮
        msg = input_value + '条数据正在导入数据库，这可能需要一段时间，请稍后...';
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
        
        /*
        //创建按钮
        var btn1 = document.createElement('a');
        btn1.id = 'alertMsg_btn1';
        btn1.href = 'javas' + 'cript:void(0)';
        btn1.innerHTML = '<cite>确定</cite>';
        btn1.onclick = function () {
            document.body.removeChild(alertBox);
            document.body.removeChild(shadowDiv);
            return true;
        };
        alertBox.appendChild(btn1);
        */
        
        //创建图片效果里面的内容P标签
        //var alertImg_p = document.createElement('p');
        //alertImg_p.id = 'alertImg_p';
        //alertImg_p.innerHTML = '<img src="images/zebratable.png"/>';
        var alertImg = document.createElement('img');
        alertImg.id = 'alertImg';
        alertImg.src = 'images/zebratable.png';
        //alertImg_p.appendChild(alertImg);
        alertBox.appendChild(alertImg);
        /*
        //取消按钮，可以选择，要还是不要，根据需求来
        if (mode === 1) {
            var btn2 = document.createElement('a');
            btn2.id = 'alertMsg_btn2';
            btn2.href = 'javas' + 'cript:void(0)';
            btn2.innerHTML = '<cite>取消</cite>';
            btn2.onclick = function () {
                document.body.removeChild(alertBox);
                document.body.removeChild(shadowDiv);
                return false;
            };
            alertBox.appendChild(btn2);
        }
        */
        document.body.appendChild(alertBox);
    }
}
</script>

<body>

<div id="container">   

	<table class="zebra">
    <caption>My Application One：MySQL数据库优化技术研究</caption>
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
            	<td>MySQL数据库优化</td>
                <td>对数据库插入数据的小程序</td>
                <td>S2SH+MySQL数据库</td>
                <td></td>
            </tr>
        </tbody>
	</table>
</div>

<div>

    <form action="dataIntoMysql.action" method="post" class="STYLE-NAME">
    <h1>Contact Form
    <span>MySQL优化技术1.0版本</span>
    </h1>
    <label>
    <span>输入需要导入的记录总数:</span>
    <input id="name" type="text" name="userInput" />
    </label>

    <label>
    <span>&nbsp;</span>
    <input type="submit" class="button" value="提交导入数据" onclick="alertMsg(1);" />
    </label>
    </form>
</div>

<div>

    <form action="" method="post" class="STYLE-NAME">
    <h1>Contact Form
    <span>MySQL优化技术1.1版本</span>
    </h1>
    <label>
    <span>输入需要导入的记录总数:</span>
    <input id="name" type="text" name="name" />
    </label>

    <label>
    <span>Your Email :</span>
    <input id="email" type="text" name="email"  />
    </label>
    </form>
</div>
   
</body>
</html>
