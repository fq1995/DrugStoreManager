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
<title>用户添加</title>
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
 		$("#username").css("background-color",""); 
 		$("#password").css("background-color",""); 
 		$("#usercode").css("background-color",""); 
 		
 		//用户编号非空
 		$("#usercode").blur(function(){
 			var name = $("input[name='usercode']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >10){
 	        	$("#usercode").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#usercode").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//用户名非空
 		$("#username").blur(function(){
 			var name = $("input[name='username']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >10){
 	        	$("#username").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#username").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//密码
 		$("#password").blur(function(){
 			var pass = $("#password").val();
 			if ($.trim(pass) == "" || pass.length == 0) {
 				$("#password").css("background-color","#FFB9B9"); 
 			}else if (pass.length<6 || pass.length>10) {
 				$("#password").css("background-color","#FFB9B9"); 
 			}else{
 				$("#password").css("background-color",""); 
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
		<form action="user_addUser.action" method="post">
			<input type="hidden" name="currPage" value="1">
			<input type="hidden" name="time">

			<ul class="forminfo">
				<li><label>用户编号</label><input readonly="readonly" name="userCode" type="text" id="userCode" value="${requestScope.userCode+1 }"
					class="form-control" style="width:200px; display:inline"/></li>
				<li ><label>用户名</label><input name="username" type="text" id="username"
					class="form-control" style="width:200px; display:inline" placeholder="请输入用户名"/><i>用户名不能超过10个字符</i><i style="color: red">${message}</i></li>
				<li><label>密码</label><input name="password" type="password" id="password"
					class="form-control" style="width:200px; display:inline" placeholder="请输入密码"/><i>密码长度在6~10位之间</i></li>
				<li><label>是否审核</label><cite>
					<input name="status" type="radio" value="1" checked="checked"<c:if test="status==1"> checked="checked"</c:if> />是&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="status" type="radio" value="0" <c:if test="status==0"> checked="checked"</c:if> />否</cite></li>
				<li><input id="add" type="submit" class="btn btn-info btn-sm" disabled value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" onclick="javascript:history.go(-1);" value="返回" /></li>
			</ul>
		</form>
	</div>
</body>