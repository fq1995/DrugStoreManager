<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>剂型添加</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.6.2.min.js"></script>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript">
 	$(function() {
 		$("#dosageform").css("background-color",""); 
 		//药品剂型名非空
 		$("#dosageform").blur(function(){
 	        var name = $("input[name='dosageform']").val(); 
 	       	$.ajax({
				url:'form_validateName.action',
				type:'POST',
				data:{'name':name},
				dataType:'json',
				success:function(data){ 
					if($.trim(name) == "" || name.length == 0 ){
		 	        	$("#dosageform").css("background-color","#FFB9B9"); 
		 	        	$("#add").attr("disabled",true);
		 	        }else if("药品剂型名可用" ==data){
		 	        	$("#dosageform").css("background-color","");
		 	        	$("#add").attr("disabled",false); 
		 	        	$("#message").html("药品剂型名可用");
		 	        }else if("药品剂型名不可用" ==data){
		 	        	$("#message").html("药品剂型名不可用");
		 	        	$("#add").attr("disabled",true);
		 	        	$("#dosageform").css("background-color","#FFB9B9"); 
		 	        }else{
		 	        	$("#message").html("药品剂型不可为空");
		 	        }
			     },
				 error:function(data){  
		         }  
	  		});
 	        
 		});
 		
 	});
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><img src="images/next.gif"></li>
			<li><a href="#">表单</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="form_addForm.action" method="post">
			<input type="hidden" name="currPage" value="1">
			<ul class="forminfo">
				<li><label>药品剂型名</label><input name="dosageform" type="text" id="dosageform" autofocus="autofocus"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品剂型名" /><i id="message" style="color: red">${message}</i></li>
				<li><input id="add" type="submit" class="btn btn-info btn-sm" disabled value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" onclick="javascript:history.go(-1);" value="返回" /></li> 
			</ul>
		</form>
	</div>
</body>