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
<!-- 导入easyui的资源文件 -->
<link rel="stylesheet" type="text/css"	href="<%=basePath %>static/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"	href="<%=basePath %>static/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath %>static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"	src="<%=basePath %>static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="<%=basePath %>static/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<table id="dg1" data-options="fit:true"></table>  
	<!-- <div id="add">
		<center style="padding-top:20px">
			<form id="form1" action="">
					<p>  ID：<input type="text" name="id"></p>
					<p>  CT：<input type="text" name="ct"></p>
					<p>NAME：<input type="text" name="mn"></p>
					<p>
						<a href="javascript:void(0)" id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
						<a href="javascript:void(0)" id="cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
					</p>
			</form>
			
		</center>
	</div> -->
	<script type="text/javascript">
		$(function(){
			$('#dg1').datagrid({    
			    url:'/front/getAllData',   
			    pagination:true,
			    rownumbers:true,
			   /*  singleSelect:true, */
			    columns:[[    
			        {field:'id',title:'ID',width:200,align:'center'},    
			        {field:'ct',title:'CT',width:200,align:'center'},    
			        {field:'mn',title:'NAME',width:200,align:'center'},
			    ]],
			   /*  toolbar:[
			             {
			            	 iconCls:"icon-add",
			            	 text:"添加项",
			            	 handler:add,
			             },'-',
			             {
			            	 iconCls:"icon-edit",
			            	 text:"修改项",
			            	 handler:function(){
			            		 alert("修改菜单")
			            	 }
			             },'-',
			             {
			            	 iconCls:"icon-remove",
			            	 text:"删除项",
			            	 handler:function(){
			            		 alert("删除菜单")
			            	 }
			             }
			    ] */
			});
			$("#ok").click(function(){
				$.ajax({
					url:'/front/addData',
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
		
		function add(){
			$("#add").window({
				iconCls:"icon-add",
				title:"添加菜单",
				width:400,
				height:300,
				draggable:true,
				collapsible:false,
				resizable:false,
				minimizable:false,
				maximizable:false
			});
		}
		
	</script>
</body>
</html>