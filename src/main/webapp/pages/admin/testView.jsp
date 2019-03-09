<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>new jsp</title>
	<link rel="icon" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
</head>
<body>
	<h1>hello 视图解析器</h1>
	<img src="<%=basePath %>/static/images/1.gif">
</body>
</html>