<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <title>药店管理系统-登录</title>
    
    <link href="css/styles.css" type="text/css" media="screen" rel="stylesheet" />
    <link href="css/jquery-ui-1.8.16.custom.css" rel="stylesheet">
    <script src="js/jquery-1.6.2.min.js"></script>
    <script src="js/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="js/jquery.keyboard.extension-typing.js"></script>
    <link type="text/css" href="css/keyboard.css" rel="stylesheet" />
    <script type="text/javascript" src="js/login.js"></script>
    <style type="text/css">
    	#an{
    		position:relative;
    		right:40px;
    	}
    	#resetbtn{
    		position:relative;
    		right:70px;
    	}
    	#forgottenpassword a {
    		
    		color:#C5C3C8;
		}
    	
    </style>
    
</head>
<body id="login">
<div id="wrappertop">
</div>
<div id="wrapper">
    <div id="content">
        <div id="header" style="margin: 0px auto;position: relative" >
            <h1 style="color: darkslateblue ;font-size: 30px;font-family: 微软雅黑;position:relative;left: 120px">
                药店管理系统
            </h1>
           
        </div>
        <div id="darkbanner" class="banner320">
        
            <h2>
                系统登录    </h2>
                
        </div>
        <div id="darkbannerwrap">
        </div>
      
        <form name="form1" method="post" action="user_login.action">
         
            <fieldset class="form">
            <span id="ti"><center><font id="tish">${tishi }</font></center></span>
                <p>
         <label class="loginlabel" for="email">
                        用户账号:</label>
                    <input class="logininput ui-keyboard-input ui-widget-content ui-corner-all " name="email"
                           id="email" type="text" value="" placeholder="请输入邮箱账号"/>&nbsp;&nbsp;<img name="duihao1" alt="" src="images/yes.jpg" style="display:none"/><span class="tip_" name="tip_"></span>
                </p>
                    <!-- <label class="loginlabel" for="username">
                        用户名称:</label>
                    <input class="logininput ui-keyboard-input ui-widget-content ui-corner-all " name="username"
                           id="username" type="text" value="" placeholder="请输入账号"/>&nbsp;&nbsp;<img name="duihao1" alt="" src="images/yes.jpg" style="display:none"/><span class="tip_" name="tip_"></span>
                </p> -->
                <p>
                    <label class="loginlabel" for="password">
                        密码:</label>
                    <span>
                        <input class="logininput" name="password" id="password" type="password" placeholder="请输入密码"/>&nbsp;&nbsp;<img name="duihao2" alt="" src="images/yes.jpg" style="display:none"/><span class="tip_pass" name="tip_"></span>
                    </span>
                </p>
                <p>
                    <label class="loginlabel" for="yanzheng">
                        验证码:</label>
                    <span><input id="inputVerifyCode" class="logininput ui-keyboard-input ui-widget-content ui-corner-all" name="yanzheng" type="text" value="" onclick="JavaScript:this.value=''" style="width: 80px;" placeholder="请输入验证码"/>
                     <span><img id="verifyCode" src="validateImg" onclick="change()" style="width:100px ;height: 25px"/></span>
                     <img name="duihao4" alt="" src="images/yes.jpg" style="display:none"/>
                    <span id="tishi" style="width:40px;height:25px;color:red"></span>
                </p>
                <div id="an">
                    <button  id="loginbtn" type="submit" class="positive" name="Submit">
                        <img src="images/key.png" alt="" />登录</button>
                <ul id="forgottenpassword">
                    <li class="boldtext">|</li>
                    <li>
                        <input id="remember" type="checkbox" name="remember" id="rememberMe"><label for="rememberMe">记住我</label>&nbsp;<a href="register.jsp" style="text-decoration:none; cursor: hand;cursor: pointer;" id="register">还未注册</a></li>
                </ul>
                	<button style="display:inline-block" id="resetbtn" type="RESET" class="positive" name="Reset" style="width: 69px;" onclick="clean()">重置</button>
                </div>
            </fieldset>
        </form>
    </div>
</div>

</body>