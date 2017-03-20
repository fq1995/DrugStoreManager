<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <title>药店管理系统-注册</title>
    <link href="css/styles.css" type="text/css" media="screen" rel="stylesheet" />
    <link href="css/jquery-ui-1.8.16.custom.css" rel="stylesheet">
    <script src="js/jquery-1.6.2.min.js"></script>
    <script src="js/jquery-ui-1.8.16.custom.min.js"></script>
    <!-- <script type="text/javascript" src="js/jquery.keyboard.extension-typing.js"></script>  -->
    <link type="text/css" href="css/keyboard.css" rel="stylesheet" />
    <script type="text/javascript" src="js/register.js"></script>
    <script type="text/javascript">
    $(function(){
 		var str = "";
 		var mydate = new Date();
 		str += mydate.getFullYear()+"-";
 		str += mydate.getMonth()+1+"-";
 		str += mydate.getDate();
 		$("input[name='time']").val(str);
 		 
 	});
    
    </script>
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
                系统注册</h2>
        </div>
        <div id="darkbannerwrap">
        </div>
        <form name="form1" method="post" action="user_registUser.action">
        	<input type="hidden" name="time">
            <fieldset class="form">
            <p>
                    <label class="loginlabel" for="username">
                      用户名:</label>
                    <input class="logininput ui-keyboard-input ui-widget-content ui-corner-all" name="username"
                           id="username" type="text" value="" placeholder="请输入用户名"/>&nbsp;&nbsp;<img name="duihao6" alt="" src="images/yes.jpg" style="display:none"/><span class="tip_name" name="tip_name"></span><i class="exit" style="color: red"></i><i id="messs" style="color: red"></i>
                </p>
                <p>
                    <label class="loginlabel" for="nickname">
                      昵称:</label>
                    <input class="logininput ui-keyboard-input ui-widget-content ui-corner-all" name="nickname"
                           id="nickname" type="text" value="" placeholder="请输入昵称"/>&nbsp;&nbsp;<img name="duihao1" alt="" src="images/yes.jpg" style="display:none"/><span class="tip_" name="tip_"></span><i class="exit" style="color: red"></i><i id="mess" style="color: red"></i>
                </p>
                <p>
                    <label class="loginlabel" for="email">
                        邮箱:</label>
                    <input class="logininput ui-keyboard-input ui-widget-content ui-corner-all" name="email"
                           id="email" type="text" value="" placeholder="请输入邮箱"/>&nbsp;&nbsp;<img name="duihao0" alt="" src="images/yes.jpg" style="display:none"/><span class="tip_1" name="tip_"></span><i class="exit" style="color: red">${message}</i><i id="mes" style="color: red"></i>
                </p>
                <p>
                    <label class="loginlabel" for="password">
                        密码:</label>
                    <span>
                        <input class="logininput ui-keyboard-input ui-widget-content ui-corner-all "   name="password" id="password" type="password" placeholder="请输入密码" />&nbsp;&nbsp;<img name="duihao2" alt="" src="images/yes.jpg" style="display:none"/><span class="tip_pass" name="tip_"></span>
                    </span>
                </p>
                <p>
                    <label class="loginlabel" for="password2">
                        确定密码:</label>
                    <span>
                        <input class="logininput ui-keyboard-input ui-widget-content ui-corner-all" name="password2" id="password2" type="password" placeholder="请再次输入密码"/><img name="duihao3" alt="" src="images/yes.jpg" style="display:none"/><span class="tip_pass2" name="tip_"></span>
                    </span>
				</P>
				<p>
                    <label class="loginlabel" for="yanzheng">
                        验证码:</label>
                    <span><input id="inputVerifyCode" class="logininput ui-keyboard-input ui-widget-content ui-corner-all" name="yanzheng"
                                 type="text" value="" onclick="JavaScript:this.value=''" style="width: 80px; " placeholder="请输入验证码"/></span>
                    <span style="width:30px;height:25px"></span>
                    <span><img id="verifyCode" src="validateImg" onclick="change()" style="width:100px ;height: 25px"/></span>
                    <img name="duihao4" alt="" src="images/yes.jpg" style="display:none"/>
                    <span id="tishi" style="width:40px;height:25px;color:red"></span>
                </p>
               	
                <div>
                    <button id="loginbtn" type="submit" class="positive" name="Submit">
                        <img src="images/key.png" alt="Announcement"/>注册</button>
               <!--  <ul id="forgottenpassword">
                    <li class="boldtext">|</li>
                    <li>
                        <input id="remember" type="checkbox" name="remember" id="rememberMe"><label for="rememberMe">记住我</label></li>
                </ul> -->
                	<button id="resetbtn" type="RESET" class="positive" name="Reset" style="width: 69px; padding-left:0px" onclick="clean()">重置</button>
                </div>


            </fieldset>
        </form>
    </div>
</div>

</body>
