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
<title>后台</title>
<!-- 导入easyui的资源文件 -->
<link rel="stylesheet" type="text/css"	href="<%=basePath %>/static/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"	href="<%=basePath %>/static/js/easyui/themes/icon.css">
<script type="text/javascript"	src="<%=basePath %>/static/js/easyui/jquery.min.js"></script>
<script type="text/javascript"	src="<%=basePath %>/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="<%=basePath %>/static/js/pages/main.js"></script>
<script type="text/javascript"	src="<%=basePath %>/static/js/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'North Title',split:true" style="height: 100px;"></div>
	<div data-options="region:'south',title:'South Title',split:true" style="height: 100px;"></div>
	<!-- <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>  -->
	<div data-options="region:'west',title:'West',split:true,iconCls:'icon-world'"	style="width: 200px;">
		<ul id="tt" class="easyui-tree" data-options="lines:true,animate:true,checkbox:true"></ul>
	</div>
	<div data-options="region:'center',title:'Center',noheader:true" style="padding: 5px; background: #eee;">
		<div id="tab" class="easyui-tabs" data-options="fit:true">
			<div title="首页" data-options="iconCls:'icon-house'" style="padding: 20px; ">欢迎</div>
		</div>
	</div>
</body>
</html>