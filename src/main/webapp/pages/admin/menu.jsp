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
<title>菜单管理</title>
<!-- 导入easyui的资源文件 -->
<link rel="stylesheet" type="text/css"	href="<%=basePath %>/static/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"	href="<%=basePath %>/static/js/easyui/themes/icon.css">
<script type="text/javascript"	src="<%=basePath %>/static/js/easyui/jquery.min.js"></script>
<script type="text/javascript"	src="<%=basePath %>/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="<%=basePath %>/static/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<table id="dg" data-options="fit:true"></table>  
	<div id="add">
		<center style="padding-top:20px">
			<form id="form1" action="">
				<p>菜单名称：<input type="text" name="menuName"></p>
					<p>展开状态：
						<select name="state">
						<option value="open">open</option>
							<option value="closed" selected="selected">closed</option>
						</select>
					</p>
					<p>菜单图标：<input type="text" name="iconCls"></p>
					<p>菜单链接：<input type="text" name="href"></p>
					<p>父级菜单主键：
						<select name="pid">
							<option value="1">系统管理</option>
							<option value="4">权限管理</option>
						</select>
					</p>
					<p>
						<a href="javascript:void(0)" id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
						<a href="javascript:void(0)" id="cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
					</p>
			</form>
			
		</center>
		
	</div>
	<script type="text/javascript">
		$(function(){
			$('#dg').datagrid({    
			    url:'/admin/getAllMenu.do',   
			    pagination:true,
			   /*  singleSelect:true, */
			    columns:[[    
					{checkbox:true},    
			        {field:'id',title:'主键',width:100,align:'center'},    
			        {field:'text',title:'菜单名称',width:100,align:'center',formatter:red},    
			        {field:'state',title:'菜单展开状态',width:100,align:'center'},
			        {field:'iconCls',title:'菜单图标',width:100,align:'center'},    
			        {field:'pid',title:'上一级菜单主键',width:100,align:'center'},    
			        {field:'href',title:'菜单链接',width:200,align:'center'} 
			    ]],
			    toolbar:[
			             {
			            	 iconCls:"icon-add",
			            	 text:"添加菜单",
			            	 handler:add,
			             },'-',
			             {
			            	 iconCls:"icon-edit",
			            	 text:"修改菜单",
			            	 handler:function(){
			            		 alert("修改菜单")
			            	 }
			             },'-',
			             {
			            	 iconCls:"icon-remove",
			            	 text:"删除菜单",
			            	 handler:function(){
			            		 alert("删除菜单")
			            	 }
			             }
			    ]
			});
			$("#ok").click(function(){
				$.ajax({
					url:'/admin/addMenu.do',
					type:'post',
					data:$("#form1").serializeObject(),
					dataType:'json',
					contentType:'application/json',
					success:function(result){
						
					}
					
				})
				
			})
			$("#cancel").click(function(){
				$("#add").window('close');
			})
		})
		function red(val,row){
			if(row.pid==0){
				return "<span style='color:red'>"+val+"</span>";
			}
			else{
				return val;
			}
		}
		function add(){
			$("#add").window({
				iconCls:"icon-add",
				title:"添加菜单",
				width:400,
				height:300,
				draggable:false,
				collapsible:false,
				resizable:false,
				minimizable:false,
				maximizable:false
			});
			$.messager.show({
				title:'我的消息',
				msg:'消息将在5秒后关闭。',
				timeout:5000,
				showType:'slide'
			})
		}
		
	</script>
</body>
</html>