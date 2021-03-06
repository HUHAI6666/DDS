<%@page import="cn.java.entity.Rule"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/easyui/themes/icon.css">
	<script type="text/javascript" src="<%=basePath %>static/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>static/js/jsonHandler.js"></script>
</head>
<body>
<div>
	<h2>&nbsp;&nbsp;&nbsp;&nbsp;配置脱敏规则</h2>
</div>
<div style="width: 700px; margin-left: 10px; border:1px solid #000">
	<form id="form1" action="" style="margin-left: 20px; margin-top: 10px;">
		<input name="id" value="0" type="hidden">
		<p>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：
			<select name="name" style="width: 550px">
				<option value="10" >---</option>
				<option value="11" >李**(只显示第一个汉字，其他隐藏为2个星号)</option>
				<option value="12" >字典随机重组(随机取姓和名，进行组合)</option>
			</select>
		</p>
		
		<p>身份证号：
			<select name="idNo" style="width: 550px">
				<option value="20" >---</option>
				<option value="21" >**************1234(显示最后四位，其他隐藏)</option>
				<option value="22" >123456********1234(显示前六位和后四位，其他隐藏)</option>
			</select>
		</p>
		
		<p>手机号码：
			<select name="phone" style="width: 550px">
				<option value="30" >---</option>
				<option value="31" >135****6810(显示前三位，后四位，其他隐藏)</option>
				<option value="32" >135****0000(显示前三位，后四位偏移，其他隐藏)</option>
			</select>
		</p>
		
		<p>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：
			<select name="nickName" style="width: 550px">
				<option value="40" >---</option>
				<option value="41" >a***b(只显示第一个和最后一个字符)</option>
				<option value="42" >数字无效化(昵称中的数字变成随机字母)</option>
			</select>
		</p>
			
		<p>电子邮箱：
			<select name="email" style="width: 550px">
				<option value="50" >---</option>
				<option value="51" >d**@126.com(邮箱前缀仅显示第一个数字/字母，其他隐藏，@及后面的地址显示)</option>
				<option value="52" >dax123@***.com(@后面具体邮箱类型隐藏)</option>
			</select>
		</p>
		
		<p>用&nbsp;&nbsp;户&nbsp;&nbsp;名：
			<select name="userName" style="width: 550px">
				<option value="60" >---</option>
				<option value="61" >123*******(用户名仅显示前三位，统一长度十位)</option>
				<option value="62" >随机截取(随机生成用户名的起始位置和结束位置的值并截取)</option>
			</select>
		</p>
		
		<p>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
			<select name="password" style="width: 550px">
				<option value="70" >---</option>
				<option value="71" >只显示数字(加密后密码中的数字)</option>
				<option value="72" >只显示字母(加密后密码中的字母)</option>
			</select>
		</p>
		
		<p>
			<a href="javascript:void(0)" id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-left: 620px;">确定</a>
		</p>
	</form>
</div>

<script type="text/javascript">
	$(function(){
		
		$.ajax({
			url:'<%=basePath %>front/initRule',
			type:'post',
			success:function(result){
				if(result.result){
					var rule = result.data;
					$("#form1").form('clear');
					$("#form1").form('load',rule);
				}
				else{
					alert("规则列表为空，获取规则列表失败!");
				}
			},
			error:function(result){
				alert("获取规则列表失败!");
			}
		});
		
		$("#ok").click(function(){
			$.ajax({
				url:'<%=basePath %>front/addRule',
				type:'post',
				data:$("#form1").serialize(),
				dataType:'json',
			    /* contentType:'application/json', */
				success:function(result){
					if(result.result == "success"){
						alert("保存成功!");
					}
					else{
						alert("保存失败!");
					}
				},
				error:function(result){
					alert("保存失败!");
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