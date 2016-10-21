<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>剂型修改</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">
 	$(function(){
 		var str = "";
 		var mydate = new Date();
 		str += mydate.getFullYear()+"-";
 		str += mydate.getMonth()+1+"-";
 		str += mydate.getDate();
 		$("input[name='time']").val(str);
 	});
 	$(function() {
 		$("#dosageform").css("background-color",""); 
 		//药品剂型名非空
 		$("#dosageform").blur(function(){
 	        var name = $("input[name='dosageform']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ){
 	        	$("#dosageform").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);
 	        }else{
 	        	$("#dosageform").css("background-color","");
 	        	$("#add").attr("disabled",false); 
 	        }
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
		<form action="form_updateForm.action" method="post">
			<input type="hidden" name="currPage" value="1">
			<input type="hidden" name="dosageformId" value="${form.dosageformId }">
			<!-- <input type="hidden" name="time"> -->
			<ul class="forminfo">
				<li><label>药品剂型名</label><input name="dosageform" type="text" id="dosageform" value="${form.dosageform }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品剂型名" /><i></i><i style="color: red">${message}</i></li>
				<li><input id="add" type="submit" class="btn btn-info btn-sm" disabled value="确认保存" /></li> 
			</ul>
		</form>
	</div>
</body>