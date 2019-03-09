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
<script type="text/javascript" src="<%=basePath %>static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"	src="<%=basePath %>static/js/easyui/plugins/jquery.form.js"></script>
</head>
<body>
<%
    String mess=(String)session.getAttribute("message");
    if("".equals(mess) || mess==null){
         
    }
 
 	else{
%>
    	<script type="text/javascript">
        alert("<%=mess%>");
		</script>
 
<%   session.setAttribute("message", ""); 
 }
%>
    <h2>文件上传</h2>
    <form id="fileForm" method="post" action="/front/fileUpload" enctype="multipart/form-data">
    	&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="file" id="file_sc" name="file"><br>
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<input type="submit" value="提交" >
	</form>
	
	<script type="text/javascript">
		$(function(){
			$("#fileForm").ajaxSubmit(function(data){
				alert("表单提交已成功!");
				/* if(data==true){  
		            alert("上传成功2！");     
		        }  */
			})
			
			/** 验证文件是否导入成功  */  
		    /*$("#fileForm").ajaxForm(function(data){    
		        if(data==true){  
		            alert("上传成功1！");     
		        }  
		    });     
			*/
			
			/* $("#fileForm").bind("submit", function(){  
				var file=$("#file_sc").val();
				if(file == ""){  
					alert("请选择文件！！！");
					return false;  
				}  
			}); */
			
		});
		 function ajaxSubmitForm() {
			 var file=$("#file_sc").val();
				if(file == ""){  
					alert("请选择文件！！！");
					return false;  
				}  
		 }
	</script>
</body>

</html>