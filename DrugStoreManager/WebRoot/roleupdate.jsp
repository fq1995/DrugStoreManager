<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户修改</title>
    <link href="css/style1.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript">
    $(function() {
    	$("#rolename").css("background-color",""); 
 		$("#rolename").css("background-color",""); 
 		//角色名非空
 		$("#rolename").blur(function(){
 	        var name = $("input[name='rolename']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >10){
 	        	$("#rolename").css("background-color","#FFB9B9"); 
 	        	$("#update").attr("disabled",true);   
 	        }else{
 	        	$("#rolename").css("background-color",""); 
 	        	$("#update").attr("disabled",false);   
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
        <li><img src="images/next.gif" style="margin-top:13px"></li>
        <li><a href="#">表单</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="role_updateRole.action" method="post">
    <input type="hidden" name="roleid" value="${role.roleid}">
   
        <ul class="forminfo">
            <li><label>角色名</label><input id="rolename" name="rolename" type="text" class="form-control" 
            style="width:200px; display:inline" placeholder="请输入角色名" value="${role.rolename}"/><i>角色名不能超过10个字符</i><i style="color: red">${message}</i></li>
            <li><input id="update" type="submit" class="btn btn-info btn-sm" disabled value="确认保存"/></li>
        </ul>
    </form>

</div>

</body>
