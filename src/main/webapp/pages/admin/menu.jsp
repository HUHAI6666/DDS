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
<link rel="icon" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
<link rel="bookmark" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath %>static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jsonHandler.js"></script>
<script type="text/javascript"	src="<%=basePath %>static/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<table id="dg" data-options="fit:true"></table>  
	<div id="add" class="addDiv" hidden="hidden">
		<center style="padding-top:20px">
			<form id="form1" action="">
				<input type="text" name="id" hidden="hidden">
				<p>菜单名称：<input type="text" name="text" id="text" style="width: 160px ;height: 16px"></p>
				<p>展开状态：
					<select name="state" id="state" style="width: 160px ;height: 22px">
						<option value="open">open</option>
						<option value="closed" selected="selected">closed</option>
					</select>
				</p>
				<p>菜单图标：
					<select name="iconCls" id="iconCls" style="width: 160px ;height: 22px">
						<option value="icon-print" selected="selected">icon-print</option>
						<option value="icon-help">icon-help</option>
						<option value="icon-system">icon-system</option>
						<option value="icon-money">icon-money</option>
						<option value="icon-manager">icon-manager</option>
						<option value="icon-tip">icon-tip</option>
						<option value="icon-login">icon-login</option>
						<option value="icon-ok">icon-ok</option>
						<option value="icon-cancel">icon-cancel</option>
						<option value="icon-no">icon-no</option>
						<option value="icon-user">icon-user</option>
					</select>
				</p>
				<p>菜单链接：<input type="text" name="href" id="href" style="width: 160px ;height: 16px"></p>
				<p>父级菜单主键：
					<select name="pid" id="pid" style="width: 140px ;height: 22px">
						<option value="0"></option>
						<option value="1">系统管理</option>
						<option value="4">数据展示</option>
						<option value="7">文件上传</option>
						<option value="11">规则配置</option>
					</select>
				</p>
				<p>
					<a href="javascript:void(0)" id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" 
						style="margin-right: 10px; margin-top: 7px;">确定</a>
					<a href="javascript:void(0)" id="save" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" 
						style="margin-right: 10px; margin-top: 7px;">保存</a>
					<a href="javascript:void(0)" id="cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" 
						style="margin-top: 7px;">取消</a>
				</p>
			</form>
			
		</center>
		
	</div>
	<script type="text/javascript">
		$(function(){
			$('#dg').datagrid({    
			    url:'/admin/getAllMenu',   
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
			            	 handler:edit,
			             },'-',
			             {
			            	 iconCls:"icon-remove",
			            	 text:"删除菜单",
			            	 handler:remove,
			             }
			    ]
			});
			$("#ok").click(function(){
				var parm2 = $("#form1").serializeObject();
				$.ajax({
					url:'/admin/addMenu',
					type:'post',
					data: parm2,
					dataType:'json',
					contentType: "application/json;charset=UTF-8",
					success:function(result){
						alert(result.result);
						window.location.reload();
						$("#save").show();
					},
					error:function(){
						alert("添加失败!");
					}
				})
			})
			
			$("#save").click(function(){
				$.ajax({
					url:'/admin/editMenu',
					type:'post',
					data: $("#form1").serializeObject(),
					dataType:'json',
					contentType: "application/json;charset=UTF-8",
					success:function(result){
						alert(result.result);
						//$('#tt').tree('reload');
						window.location.reload();
						$("#ok").show();
					},
					error:function(){
						alert("修改失败!");
					}
				})
			})
			
			
			$("#cancel").click(function(){
				$("#add").window('close');
				$("#save").show();
				$("#ok").show();
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
			$(".addDiv").removeAttr("hidden");
			$("#add").window({
				iconCls:"icon-add",
				title:"添加菜单",
				width:400,
				height:300,
				modal:true,
				draggable:true,
				collapsible:false,
				resizable:false,
				minimizable:false,
				maximizable:false,
				onBeforeClose: function () { //当面板关闭之前触发的事件
                	$("#save").show();
                }
			});
			$("#text").val("");
			$("#state").val("closed");
			$("#iconCls").val("icon-print");
			$("#href").val("");
			$("#pid").val("0");
			$("#save").hide();
			$.messager.show({
				title:'我的消息',
				msg:'消息将在5秒后关闭。',
				timeout:5000,
				showType:'slide'
			})
		}
		
		function edit(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length != 1){
				alert("请选择一条记录!");
				return ;
			}
			var row = rows[0];
			$(".addDiv").removeAttr("hidden");
			$("#ok").hide();
			$("#add").window({
				iconCls:"icon-edit",
				title:"修改菜单",
				width:400,
				height:300,
				modal:true,
				draggable:true,
				collapsible:false,
				resizable:false,
				minimizable:false,
				maximizable:false,
				onBeforeClose: function () {
    				$("#ok").show();
                }
			});
			$("#form1").form('load',row);
		}
		
		function remove(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length < 1){
				alert("请至少选择一条记录!");
				return ;
			}
			var parm = new Array();
			var i;
			for(i = 0 ; i < rows.length ; i++){
				var row = rows[i];
				parm[i] = row.id;
				
			}
			$.messager.confirm('确认','确定删除菜单!',function(r){    
			    if (r){  //点击了确认按钮  
			    	$.ajax({
						url:'/admin/removeMenu',
						type:'post',
						data: JSON.stringify(parm),
						dataType:'json',
						contentType: "application/json;charset=UTF-8",
						success:function(result){
							alert(result.result);
							//$('#tt').tree('reload');
							window.location.reload();
						},
						error:function(){
							alert("删除失败!");
						}
					});
			    }   
			});  
		}
		
	</script>
</body>
</html>