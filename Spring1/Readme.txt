Spring Web MVC��һ�ֻ���Java��ʵ����Web MVC���ģʽ�������������͵�������Web��ܣ���ʹ����MVC�ܹ�ģʽ��˼�룬��web�����ְ
����������������ָ�ľ���ʹ������-��Ӧģ�ͣ���ܵ�Ŀ�ľ��ǰ������Ǽ򻯿�����Spring Web MVCҲ��Ҫ�������ճ�Web�����ġ�

֪ʶ�㣺
1��SpringMVC��֧��JSTL�ģ�����jstl��ǩ������Ҳ�ǽϺõġ����ԣ����ǿ����û�����JSTL���ʽ����ʾ���ݣ���ʹ��������ǩ��һ���ĵ���ʹ��JSTL��ǩ�⣬����ҲҪ�ȵ���JSTL��ǩ�⣬Ҳ������jspͷ�ļ��м���������

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

��SpringMVC�����ʹ��JSTL��ǩ�⣬��Ҫjar����֧�֣�2�������jar����jstl.jar �� standard.jar
2��<c:forEach>��ǩ���﷨��
�﷨��<c:forEach var="name" items="Collection" varStatus="statusName" begin="begin" end="end" step="step"></c:forEach>

1������spring��mysql

	<!-- ����dao��ʵ���࣬����jdbcTemplateע��userDao -->
       <bean id="userDao" class="com.spring.dao.impl.UserDaoImpl">
           <property name="jdbcTemplate" ref="jdbcTemplate"></property>
       </bean>
	 
	<!-- ����Controller��ʵ���࣬����daoע��Controller -->	
       <bean id="userController" class="com.spring.controller.UserController">
           <property name="userDao" ref="userDao"></property>
       </bean>



2��mysql���ݽṹ
	>���ֶ�1���ֶ�2...
	>news:newsid,context,username
	>user:username,password
	>comment:text,hisname,id
3����¼
	>��ע�� �û���:���� admin:123 
	>login.jsp �����û���������¼ ��UserLoginValidate_4.actionӳ���UserController ������ͨ��UserDao��֤��¼�� ����¼�ɹ� ����ModelAndView��index.jsp ���򷵻ص�fail.jsp
4����ҳ	
	>index.jsp�����ҳ���� ��mysqlQueryDao.actionӳ���MysqlQueryController������ ͨ��MysqlQueryDao���ز�ѯ����洢��List������ װ�� model���ظ�index.jsp��ʾ����news
5��������
	>��index.jsp���������<a href="<%=basePath%>commentQueryDao.action?newsid=${item.newsid}" >����</a>ӳ���CommentQueryControllerͨ��CommentQueryDao���ز�ѯ����洢��list����װ��model���ظ�MysqlShowById.jsp��ʾ����news���������ۡ�
6������
	>����Ϣ����index.jsp��contextInsertDao.actionӳ���ContextInsertController������ ֮��ContextInsertDao�����ݲ���
	>�����ۣ���MysqlShowById.jsp��commentInsertDao.actionӳ���CommentInsertController������ ֮��CommentInsertDao�����ݲ���
7��ע��
	>��context.jsp��regDao.actionӳ���RegController������ ֮��RegDao�����ݲ���
