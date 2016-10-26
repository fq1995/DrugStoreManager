<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8 />
<title>药品单位修改</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
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
 		$("#unitname").css("background-color",""); 
 		//药品单位名非空
 		$("#unitname").blur(function(){
 			var name = $("input[name='unitname']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ){
 	        	$("#unitname").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#unitname").css("background-color",""); 
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
			<li><img src="images/next.gif" ></li>
			<li><a href="#">表单</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="unit_updateUnit.action" method="post">
			<input type="hidden" name="unitnameId" value="${unit.unitnameId }"/>
			<ul class="forminfo">
				<li><label>药品单位名</label><input name="unitname" type="text" id="unitname" value="${unit.unitname }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品单位名"/><i>必填</i><i style="color: red">${message}</i></li>
				<li><input id="add" type="submit" class="btn btn-info btn-sm" disabled value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" onclick="javascript:history.go(-1);" value="返回" /></li>
			</ul>
		</form>
	</div>
</body>