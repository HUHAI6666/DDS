<%@page import="cn.java.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据脱敏系统</title>
<link rel="icon" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
<!-- 导入easyui的资源文件 -->
<link rel="stylesheet" type="text/css"	href="<%=basePath %>static/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"	href="<%=basePath %>static/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath %>static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"	src="<%=basePath %>static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="<%=basePath %>static/js/pages/main.js"></script>
<script type="text/javascript"	src="<%=basePath %>static/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<%
	User user=(User)session.getAttribute("session_user");
	String name	= null;
	if(user!=null){
		name = user.getUsername();
	}
	
%>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 50px; background-color: darkseagreen;">
		<div style=" margin-top: 8px; margin-left: 3px; font-size: 20px; height: 48px ;position: absolute;" >
			<span style="font-family: Microsoft JhengHei;color: red; font-style: oblique;font-weight:bold">D D S </span>
			<span> &nbsp;数据脱敏系统</span>
		</div>
		<div style="float: right;  height: 48px; font-size: 20px; margin-right: 7px;">
			<img src="../../static/images/timg.jpg" style="margin-top: 7px; float: right; " onclick="loginOut();"/>
		</div>
		<div style="float: right;  height: 48px; margin-right: 10px;">
			<input type="text" id="hiddenUsername" hidden="hidden" value=<%=name%>>
			<table style="font-size: 12px; margin-top: 2px;">
					<tr><th>欢迎您:</th></tr>
					<tr><th>亲爱的<%=name%></th></tr>
			</table>
		</div>
		
		
	</div>
	
	<!--<div data-options="region:'south',title:'South Title',split:true" style="height: 100px;"></div> -->
	<!-- <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>  -->
	<div data-options="region:'west',title:'Navigation',split:true,iconCls:'icon-world'" style="width: 200px;">
		<ul id="tt" class="easyui-tree" data-options="lines:true,animate:true,checkbox:true"></ul>
	</div>
	<div data-options="region:'center'">
	<!-- <div id="tab" class="easyui-tabs" data-options="fit:true">
			<div title="首页" data-options="iconCls:'icon-house'" style="padding: 20px; font-size: 20px;">Welcome to Data desensitization system</div>
		</div> -->
		<iframe id="mainFrame" frameborder="0" src="/pages/admin/index.jsp" style="width: 100%;height: calc(100% - 4px);"></iframe>
	</div>
	
<script type="text/javascript">
	function loginOut(){
		$.messager.confirm('确认','确定退出系统!',function(r){    
		    if (r){  //点击了确认按钮  
		    	$.ajax({
					url:'<%=basePath %>admin/logout',
					type:'get',
				});
				window.location.href="<%=basePath %>pages/admin/login.jsp";
		    }   
		});  
		
	}
</script>
	
</body>
</html>