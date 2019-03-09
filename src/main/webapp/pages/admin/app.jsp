<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="icon" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
</head>
<body>
<h1>我是app管理页面</h1>
</body>
</html>