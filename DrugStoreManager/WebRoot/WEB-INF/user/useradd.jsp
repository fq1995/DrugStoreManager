<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >
<html>
<head>
<meta charset=utf-8 />
<title>用户添加</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
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
		$("#add").focus(function () {
			var reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
			var username = $("#username").val();
			var nickname = $("#nickname").val();
			var name = $("input[name='email']").val();
			var pass = $("#password").val();
			
			
			if(null == username || $.trim(username) == ''){
				alert("请输入用户名");
				$("#username").focus();
			}else if(username.length >4){
				alert("用户名长度不大于4位");
				$("#username").focus();
			}else if(null == nickname || $.trim(nickname) == ''){
				alert("请输入昵称");
				$("#nickname").focus();
			}else if(nickname.length > 20){
				alert("昵称长度不能大于20位");
				$("#nickname").focus();
	        }else if(null == name || $.trim(name) == ''){
				alert("请输入邮箱");
				$("input[name='email']").focus();
			}else if (!reg.test(name)){
		        alert("请输入正确格式的邮箱！");
		        $("input[name='email']").focus();
		    }
		});
	});
 	$(function() {
 		$("#username").css("background-color",""); 
 		$("#password").css("background-color",""); 
 		$("#usercode").css("background-color",""); 
 		$("#email").css("background-color",""); 
 		$("#nickname").css("background-color",""); 
 		
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
 			$("#nametips").val("");
 	        if($.trim(name) == "" || name.length == 0 ||name.length >4){
 	        	$("#username").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#username").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//昵称
  		$("#nickname").blur(function(){
  			$("#mess").html("");
  			$(".tip_").html("");
  		    $("#tip_").html("");
			var nickname = $("#nickname").val();
	  		$.ajax({
				url:'user_validateNickName.action',
				type:'POST',
				data:{'nickname':nickname},
				dataType:'json',
				success:function(data){ 
					if($.trim(nickname) == "" || nickname.length == 0 ){
						$("#nickname").css("background-color","#FFB9B9"); 
			            $(".tip_").html("<a style='color:red'>昵称不能为空</a>");
			            
			        }else if(nickname.length > 20){
			        	$(".tip_").html("<a style='color:red'>昵称长度不能大于20位</a>");
			        }  
					else if(data == "用户名不可用"){
						$("#nickname").css("background-color","#FFB9B9"); 
						$("#mess").html("昵称已存在");
					}
					
					else if(data=="用户名可用"){
						$("#nickname").css("background-color",""); 
						$("#add").attr("disabled",false); 
			        }
			     }  
	  		});
		});
  		
  	//邮箱
  		$("#email").blur(function(){
  			$("#mes").html("");
  			$(".tip_1").html("");
			var email = $("#email").val();
			 var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	  		$.ajax({
				url:'user_validateEmail.action',
				type:'POST',
				data:{'email':email},
				dataType:'json',
				success:function(data){ 
					if($.trim(email) == "" || email.length == 0){
						$("#email").css("background-color","#FFB9B9"); 
			            $(".tip_1").html("<a style='color:red'>&nbsp;&nbsp;&nbsp;&nbsp;邮箱不能为空</a>");
			            
			        }else if (!reg.test(email)){
			        	$("#email").css("background-color","#FFB9B9"); 
			 	           $("#mes").html("请输入正确格式的邮箱！");
			 	            return false;
			 	    }
					else if(data == "邮箱名不可用"){
						$("#email").css("background-color","#FFB9B9"); 
						$("#mes").html("邮箱已存在");
					}
					
					else if(data=="邮箱名可用"){
						$("#email").css("background-color","");
						$("#add").attr("disabled",false); 
			        }
			     }  
	  		});
	  		
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
			<li><a href="#">用户管理</a></li>
			<li><img src="images/next.gif" ></li>
			<li><a href="#">添加用户</a></li>
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
				<li ><label>用户名</label><input name="username" type="text" id="username" autofocus="autofocus"
					class="form-control" style="width:200px; display:inline" placeholder="请输入用户名"/><i>用户名不能超过4个字符</i><i id="nametips" style="color: red">${message}</i></li>
				<li ><label>昵称</label><input name="nickname" type="text" id="nickname"
					class="form-control" style="width:200px; display:inline" placeholder="请输入昵称"/><i>昵称不能超过20个字符</i>&nbsp;&nbsp;<span style="display: inline-block;" class="tip_" name="tip_"></span><i id="mess" style="color: red"></i></li>
				<li ><label>邮箱</label><input name="email" type="text" id="email"
					class="form-control" style="width:200px; display:inline" placeholder="请输入邮箱"/><span style="display: inline-block;" class="tip_1" name="tip_"></span><i id="mes" style="color: red"></i></li>
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