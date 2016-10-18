Spring Web MVC是一种基于Java的实现了Web MVC设计模式的请求驱动类型的轻量级Web框架，即使用了MVC架构模式的思想，将web层进行职
责解耦，基于请求驱动指的就是使用请求-响应模型，框架的目的就是帮助我们简化开发，Spring Web MVC也是要简化我们日常Web开发的。

知识点：
1、SpringMVC是支持JSTL的，并且jstl标签的性能也是较好的。所以，我们可以用基本的JSTL表达式来显示数据，和使用其他标签库一样的道理，使用JSTL标签库，我们也要先导入JSTL标签库，也就是在jsp头文件中加入声明：

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

在SpringMVC框架中使用JSTL标签库，需要jar包的支持，2个必须的jar包：jstl.jar 和 standard.jar
2、<c:forEach>标签的语法：
语法：<c:forEach var="name" items="Collection" varStatus="statusName" begin="begin" end="end" step="step"></c:forEach>

1、配置spring，mysql

	<!-- 配置dao层实现类，并将jdbcTemplate注入userDao -->
       <bean id="userDao" class="com.spring.dao.impl.UserDaoImpl">
           <property name="jdbcTemplate" ref="jdbcTemplate"></property>
       </bean>
	 
	<!-- 配置Controller层实现类，并将dao注入Controller -->	
       <bean id="userController" class="com.spring.controller.UserController">
           <property name="userDao" ref="userDao"></property>
       </bean>



2、mysql数据结构
	>表：字段1，字段2...
	>news:newsid,context,username
	>user:username,password
	>comment:text,hisname,id
3、登录
	>已注册 用户名:密码 admin:123 
	>login.jsp 输入用户名密码后登录 由UserLoginValidate_4.action映射给UserController 控制器通过UserDao验证登录后 若登录成功 返回ModelAndView到index.jsp 否则返回到fail.jsp
4、首页	
	>index.jsp点击首页链接 由mysqlQueryDao.action映射给MysqlQueryController控制器 通过MysqlQueryDao返回查询结果存储在List容器里 装入 model返回给index.jsp显示所有news
5、评论区
	>从index.jsp点击链接由<a href="<%=basePath%>commentQueryDao.action?newsid=${item.newsid}" >更多</a>映射给CommentQueryController通过CommentQueryDao返回查询结果存储在list容器装入model返回给MysqlShowById.jsp显示该条news的所有评论。
6、发表
	>发消息：从index.jsp由contextInsertDao.action映射给ContextInsertController控制器 之后ContextInsertDao将数据插入
	>发评论：从MysqlShowById.jsp由commentInsertDao.action映射给CommentInsertController控制器 之后CommentInsertDao将数据插入
7、注册
	>从context.jsp由regDao.action映射给RegController控制器 之后RegDao将数据插入
