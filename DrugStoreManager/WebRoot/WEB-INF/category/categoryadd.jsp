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
<title>药品类别添加</title>
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
 		$("#category").css("background-color",""); 
 		//药品类别名非空
 		$("#category").blur(function(){
 			var name = $("input[name='category']").val(); 
 			$.ajax({
				url:'category_validateName.action',
				type:'POST',
				data:{'name':name},
				dataType:'json',
				success:function(data){ 
					if($.trim(name) == "" || name.length == 0 ){
						$("#category").css("background-color","#FFB9B9"); 
		 	        	$("#add").attr("disabled",true);
		 	        }else if("药品类别名可用" ==data){
		 	        	$("#category").css("background-color",""); 
		 	        	$("#add").attr("disabled",false); 
		 	        	$("#message").html("药品类别名可用");
		 	        }else if("药品类别名不可用" ==data){
		 	        	$("#message").html("药品类别名不可用");
		 	        	$("#add").attr("disabled",true);
		 	        	$("#category").css("background-color","#FFB9B9"); 
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
			<li><img src="images/next.gif" ></li>
			<li><a href="#">表单</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="category_addCategory.action" method="post">
			<input type="hidden" name="currPage" value="1">
			<ul class="forminfo">
				<li><label>药品类别名</label><input name="category" type="text" id="category"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品类别名"/><i>必填</i><i id="message" style="color: red">${message}</i></li>
				<li><input id="add" type="submit" class="btn btn-info btn-sm" disabled value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" onclick="javascript:history.go(-1);" value="返回" /></li>
			</ul>
		</form>
	</div>
</body>