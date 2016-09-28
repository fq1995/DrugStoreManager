<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户修改</title>
    <link href="css/style1.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript">
    $(function() {
 		$("#username").css("background-color",""); 
 		$("#password").css("background-color",""); 
 		//用户名非空
 		$("#username").blur(function(){
 	        var name = $("input[name='username']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >10){
 	        	$("#username").css("background-color","#FFB9B9"); 
 	        }else{
 	        	$("#username").css("background-color",""); 
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
        <li><a href="#">表单</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="user_updateUser.action" method="post">
    <input type="hidden" name="userid" value="${user.userid}">
    <input type="hidden" name="id" value="${user.userid}">
 
        <ul class="forminfo">
            <li><label>用户名</label><input id="username" name="username" type="text" class="dfinput" value="${user.username}"/><i>用户名不能超过10个字符</i><i style="color: red">${message}</i></li>
            <li><label>密码</label><input id="password" name="password" type="text" class="dfinput" value="${user.password}"/><i>密码长度在6~10位之间</i></li>
            <li><label>是否审核</label><cite><input name="status" type="radio" value="1" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;<input name="status" type="radio" value="0" />否</cite></li>
            <li><input name="" type="submit" class="btn" value="确认保存"/></li>
        </ul>
    </form>

</div>

</body>
