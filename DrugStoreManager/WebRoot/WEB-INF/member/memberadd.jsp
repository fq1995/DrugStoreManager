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
<title>会员添加</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript">
 	$(function() {
 		
 		
 		$("#memberName").css("background-color",""); 
 		$("#password").css("background-color",""); 
 		$("#memberCode").css("background-color",""); 
 		$("#relti").html("");
 		//会员编号非空
 		$("#memberCode").blur(function(){
 			var name = $("input[name='memberCode']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >10){
 	        	$("#memberCode").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#memberCode").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//会员名非空
 		$("#memberName").blur(function(){
 			var name = $("input[name='memberName']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >4){
 	        	$("#memberName").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#memberName").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
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
		<form action="member_addMember.action" method="post">
			<input type="hidden" name="currPage" value="1">
			<input type="hidden" name="time">

			<ul class="forminfo">
				<li><label>会员编号</label><input name="memberCode" type="text" id="memberCode"
					class="form-control" style="width:200px; display:inline" placeholder="请输入会员编号"/><i style="color: red">${message2}</i></li>
				<li ><label>会员性名</label><input name="memberName" type="text" id="memberName"
					class="form-control" style="width:200px; display:inline" placeholder="请输入会员名"/><i>会员性名不能超过4个字符</i><i style="color: red">${message}</i></li>
				<li><label>性别</label><cite>
					<input name="sex" type="radio" value="1" checked="checked"<c:if test="sex==1"> checked="checked"</c:if> />男&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="sex" type="radio" value="0" <c:if test="sex==0"> checked="checked"</c:if> />女</cite></li>
				<li ><label>年龄</label><input name="age" type="text" id="age"
					class="form-control" style="width:200px; display:inline" placeholder="请输入年龄"/></li>
				<li ><label>电话</label><input name="suppliertel" type="text" id="suppliertel"
					class="form-control" style="width:200px; display:inline" placeholder="请输入电话"/>&nbsp;&nbsp;&nbsp;&nbsp;<span id="relti" style="color:red;display:inline-block"></span></li>
				<li ><label>地址</label><input name="address" type="text" id="address"
					class="form-control" style="width:200px; display:inline" placeholder="请输入地址"/></li>
				<li ><label>会员等级</label><input name="memberLevel" type="text" id="memberLevel"
					class="form-control" style="width:200px; display:inline" placeholder="请输入会员等级"/></li>
				<li ><label>积分</label><input name="integration" type="text" id="integration"
					class="form-control" style="width:200px; display:inline" placeholder="请输入积分"/></li>
					
				<li><input id="add" type="submit" class="btn btn-info btn-sm" disabled value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" onclick="javascript:history.go(-1);" value="返回" /></li>
			</ul>
		</form>
	</div>
</body>