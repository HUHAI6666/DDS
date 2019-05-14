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
<script type="text/javascript" src="<%=basePath%>static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/jquery.form.min.js"></script>
</head>
<body>
	<div style="height:50px">
		<table>
			<tr>
				<th>
					<h2>&nbsp;&nbsp;&nbsp;&nbsp;数据文件上传</h2>
				</th>
				<th>
					<h3>&nbsp;(请选择txt文件)</h3>
				</th>
			</tr>
		</table>
		
	</div>
	
	<div style="margin-top: 20px ; margin-left: 20px">
		<form id="tf" method="post" action="" enctype="multipart/form-data">
			&nbsp;&nbsp;&nbsp;&nbsp; <input type="file" id="file_sc" name="file"><br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="提交" style=" margin-left: 50px; margin-top: 10px;" onclick="uploadFiles();">
		</form>
	</div>
 
	<div id="YWaitDialog" align="center" 
		style="background-color: #e0e0e0; margin: auto; display:none ;height: 150px; width: 300px;">
		<p style="text-align: center; vertical-align: central;">
			 <img src="<%=basePath%>static/images/001.gif" />
		</p>
		<p align="center">请等待，正在上传数据!</p>
	</div>

	<script type="text/javascript">
		
		function uploadFiles() {
			
			var file = $("#file_sc").val();
			if (file == "") {
				alert("请选择文件！！！");
				return false;
			} else {
				var fileType = checkFile(file);
				if (fileType != "txt") {
					alert("选择的文件无效！请重新选择");
					$("#file_sc").val("");
					return false;
				}
			}

			var uploadFile = new FormData($("#tf")[0]);

			console.log(uploadFile);
			if ("undefined" != typeof (uploadFile) && uploadFile != null && uploadFile != "") {
				$.ajax({
					url : '<%=basePath%>front/fileUpload',
					type:'POST',
					data:uploadFile,
					async: true,  
					cache: false, 
					contentType: false, //不设置内容类型
					processData: false, //不处理数据
					beforeSend:function () {
						$("#YWaitDialog").show(); 
				    }, 
				    complete: function () {
				    	$("#YWaitDialog").hide();
				    },
					success:function(data){
						$("#YWaitDialog").hide();
						console.log(data);
						//alert(data.result);
						notice();
						$("#file_sc").val("");
					},
					error:function(){
						$("#YWaitDialog").hide();
						alert("上传失败！");
						$("#file_sc").val("");
					}
				})
			 }else {
				alert("选择的文件无效！请重新选择");
			 }
				
		}   
		
		function notice() {
			alert("上传成功!");
		}
		
		function checkFile(file) {
			 var index = file.lastIndexOf(".");
			 if(index!=-1){
				 var subfix = file.substring(index+1);
				 return subfix.toLocaleLowerCase();
			 }
			 return "";
		 }
		
		 function test1(){
			 var option = {
			       url : '<%=basePath%>front/fileUpload',
			       type : 'POST',
			       dataType : 'json',
			       headers : {"ClientCallMode" : "ajax"}, //添加请求头部
			       success : function(data) {
			       	  if(data.result == "上传成功"){
			       		  alert("个人用户已成功升级为企业用户！");
			          }
			          else{
			              alert("企业用户升级失败,请联系管理员！");
			              return;
			          }
			       },
			       error: function(data) {
			          alert("企业用户升级失败,请联系管理员！");
			       }
			  };
			 $("#tf").ajaxSubmit(option);
		 }
		 
		 function test(){
			 var form = new FormData(document.getElementById("tf"));
//	             var req = new XMLHttpRequest();
//	             req.open("post", "${pageContext.request.contextPath}/public/testupload", false);
//	             req.send(form);
             $.ajax({
                 url:'<%=basePath%>front/fileUpload',
				 type : "post",
				 data : form,
				 processData : false,
				 contentType : false,
				 success : function(data) {
					window.clearInterval(timer);
					console.log("over..");
				 },
				 error : function(e) {
					alert("错误！！");
					window.clearInterval(timer);
				 }
			 });
			 get();//此处为上传文件的进度条
		}
	</script>
</body>
</html>