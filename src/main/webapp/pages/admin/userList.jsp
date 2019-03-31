<%@page import="cn.java.entity.User"%>
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
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath %>static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jsonHandler.js"></script>
<script type="text/javascript"	src="<%=basePath %>static/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<%
	User user=(User)session.getAttribute("session_user");
	String localUsername = null;
	if(user!=null){
		localUsername = user.getUsername();
	}
%>
<body>
	<table id="dg" data-options="fit:true"></table>  
	<div id="add" class="addDiv" hidden="hidden">
		<center style="padding-top:20px">
			<form id="form1" action="">
				<input type="text" name="id" hidden="hidden">
				<p>用户名：<input type="text" name="username" id="username" style="width: 156px;height: 16px;margin-left: 3px;"></p>
				<p>密&nbsp;&nbsp; 码：<input type="text" name="password" id="password" style="width: 156px;height: 16px;margin-left: 3px;"></p>
				<p>角&nbsp;&nbsp; 色：
					<select name="role" id="role" style="width: 160px ;height: 22px">
						<option value="1">管理员</option>
						<!-- <option value="closed" selected="selected">closed</option> -->
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
			    url:'/admin/getAllUser',   
			    pagination:true,
			   /*  singleSelect:true, */
			    columns:[[    
					{checkbox:true},    
			        {field:'username',title:'用户名',width:200,align:'center'},    
			        {field:'password',title:'密码',width:500,align:'center'},
			        {field:'role',title:'角色',width:200,align:'center',formatter:getRole},    
			    ]],
			    toolbar:[
			             {
			            	 iconCls:"icon-add",
			            	 text:"添加用户",
			            	 handler:add,
			             },'-',
			             {
			            	 iconCls:"icon-edit",
			            	 text:"修改用户",
			            	 handler:edit,
			             },'-',
			             {
			            	 iconCls:"icon-remove",
			            	 text:"删除用户",
			            	 handler:remove,
			             }
			    ]
			});
			$("#ok").click(function(){
				var name = $("#username").val();
				if(name == null || name == undefined || name == ""){
					alert("用户名不能为空!");
					return ;
				}
				var pwd = $("#password").val();
				if(pwd == null || pwd == undefined || pwd == ""){
					alert("密码不能为空!");
					return ;
				}
				var role = $("#role").val();
				if(role == null || role == undefined || role == ""){
					alert("用户角色不能为空!");
					return ;
				}
				var parm2 = $("#form1").serializeObject();
				var params = {};
				params.username = name;
				$.ajax({
					url:'/admin/checkUsername',
					type:'post',
					data: params,
					dataType:'json',
					success:function(result){
						if(result.result == "success"){
							$.ajax({
								url:'/admin/addUser',
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
						}
						else{
							alert("用户名已被占用!");
						}
					},
					error:function(){
						alert("添加失败!");
					}
				})
			})
			
			$("#save").click(function(){
				var pwd = $("#password").val();
				if(pwd == null || pwd == undefined || pwd == ""){
					alert("密码不能为空!");
					return ;
				}
				var role = $("#role").val();
				if(role == null || role == undefined || role == ""){
					alert("用户角色不能为空!");
					return ;
				}
				$.ajax({
					url:'/admin/editUser',
					type:'post',
					data: $("#form1").serializeObject(),
					dataType:'json',
					contentType: "application/json;charset=UTF-8",
					success:function(result){
						alert(result.result);
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
		
		function getRole(val,row){
			if(val == 1){
				return "管理员";
			}
			else
				return "普通用户";
		}
		
		function add(){
			$(".addDiv").removeAttr("hidden");
			$("#add").window({
				iconCls:"icon-add",
				title:"添加用户",
				width:400,
				height:240,
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
			$("#username").removeAttr("disabled");
			$("#username").val("");
			$("#password").val("");
			$("#role").val("1");
			$("#save").hide();
		}
		
		function edit(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length != 1){
				alert("请选择一条记录!");
				return ;
			}
			var row = rows[0];
			$(".addDiv").removeAttr("hidden");
			$("#username").attr("disabled","disabled");
			$("#ok").hide();
			$("#add").window({
				iconCls:"icon-edit",
				title:"修改用户",
				width:400,
				height:240,
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
			$("#password").val("");
		}
		
		function remove(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length < 1){
				alert("请至少选择一条记录!");
				return ;
			}
			var localUsername = <%=localUsername%>;
			var parm = new Array();
			var i;
			for(i = 0 ; i < rows.length ; i++){
				debugger;
				var row = rows[i];
				parm[i] = row.id;
				if(row.username == localUsername){
					alert("不能删除当前用户!");
					return ;
				}
				
			}
			$.messager.confirm('确认','确定删除用户!',function(r){    
			    if (r){  //点击了确认按钮  
			    	$.ajax({
						url:'/admin/removeUser',
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