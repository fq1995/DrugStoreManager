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
<title>员工修改</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript">
 	$(function(){
 		var tel = $("#tel").val();
 		var title = $("#title").val();
 		var startdate = $("#startdate").val();
 		if(tel !="" || title !="" || startdate !=""){
 			$("#add").attr("disabled",false);   
 		}
 	});
 	
 	
	$(function() {
 		$("#empName").css("background-color",""); 
 		$("#password").css("background-color",""); 
 		$("#empCode").css("background-color",""); 
 		$("#relti").html("");
 		//员工编号非空
 		$("#empCode").blur(function(){
 			var name = $("input[name='empCode']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >10){
 	        	$("#empCode").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#empCode").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//员工名非空
 		$("#empName").blur(function(){
 			var name = $("input[name='empName']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >4){
 	        	$("#empName").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#empName").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//校验手机号
 		$("#tel").blur(function(){
 			$("#relti").html("");
 			var tel = $("#tel").val();
 	        if (!tel) {
 	            //alert("手机号不能为空！");
 	            $("#relti").html("手机号不能为空！");
 	            return false;
 	        }
 	        //正则表达式
 	        var reg = /(1[3-9]\d{9}$)/;
 	        if (!reg.test(tel))
 	        {
 	           //alert("请输入正确格式的手机号码！");
 	           $("#relti").html("请输入正确格式的手机号码！");
 	            return false;
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
		<form action="emp_updateEmp.action" method="post">
			<input type="hidden" name="currPage" value="1">
			<input type="hidden" name="time">
			<input type="hidden" name="empId" value="${emp.empId }">

			<ul class="forminfo">
				<li><label>员工编号</label><input name="empCode" type="text" id="empCode" value="${emp.empCode }" readonly="readonly"
					class="form-control" style="width:200px; display:inline" placeholder="请输入员工编号"/><i style="color: red">${message2}</i></li>
				<li ><label>员工性名</label><input name="empName" type="text" id="empName" value="${emp.empName }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入员工名"/><i>员工性名不能超过4个字符</i><i style="color: red">${message}</i></li>
				<li><label>性别</label><cite>
					<input name="sex" type="radio" value="1" checked="checked"<c:if test="${emp.sex }==1"> checked="checked"</c:if> />男&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="sex" type="radio" value="0" <c:if test="${emp.sex }==0"> checked="checked"</c:if> />女</cite></li>
				<li ><label>年龄</label><input name="age" type="text" id="age" value="${emp.age }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入年龄"/></li>
				<li ><label>电话</label><input name="tel" type="text" id="tel" value="${emp.tel }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入电话"/>&nbsp;&nbsp;&nbsp;&nbsp;<span id="relti" style="color:red;display:inline-block"></span></li>
				<li ><label>职位</label><input name="title" type="text" id="title" value="${emp.title }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入职位"/></li>
				<li ><label>开始工作时间</label>
					<input name="startdate" id="startdate" class="form-control Wdate" type="text" style="width:200px; display:inline" value="${emp.startdate }" onClick="WdatePicker()"/><i>请输入开始时间</i></li>				
				<li><input id="add" type="submit" class="btn btn-info btn-sm" disabled value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" onclick="javascript:history.go(-1);" value="返回" /></li>
			</ul>
		</form>
	</div>
</body>