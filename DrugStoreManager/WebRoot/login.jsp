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
    <link rel="shortcut icon" type="image/ico" href="/images/favicon.ico" />
    <title>Login</title>
    <link href="css/styles.css" type="text/css" media="screen" rel="stylesheet" />
    <link href="css/jquery-ui-1.8.16.custom.css" rel="stylesheet">
    <script src="js/jquery-1.6.2.min.js"></script>
    <script src="js/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="js/jquery.keyboard.extension-typing.js"></script>
    <link type="text/css" href="css/keyboard.css" rel="stylesheet" />
    <!-- <script type="text/javascript" src="js/jquery.keyboard.js"></script> -->
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
                系统登录</h2>
        </div>
        <div id="darkbannerwrap">
        </div>
        <form name="form1" method="post" action="user_login.action">
            <fieldset class="form">
                <p>
                    <label class="loginlabel" for="user_name">
                        用户账号:</label>
                    <input class="logininput ui-keyboard-input ui-widget-content ui-corner-all" name="username"
                           id="username" type="text" value="" placeholder="请输入账号"/>
                </p>
                <p>
                    <label class="loginlabel" for="password">
                        密码:</label>
                    <span>
                        <input class="logininput"   name="password" id="password" type="password" placeholder="请输入密码"/><img
                            id="passwd" class="tooltip" alt="Click to open the virtual keyboard" title="Click to open the virtual keyboard"
                            src="images/keyboard.png" />
                    </span>
                </p>
                <p>
                    <label class="loginlabel" for="yanzheng">
                        验证码:</label>
                    <span><input class="logininput ui-keyboard-input ui-widget-content ui-corner-all" name="yanzheng" type="text" value="" onclick="JavaScript:this.value=''" style="width: 250px;" placeholder="请输入验证码"/></span><cite>X3D5S</cite>
                </p>
                <div style="background-color: blue">
                    <button id="loginbtn" type="submit" class="positive" name="Submit">
                        <img src="images/key.png" alt="Announcement" />登录</button>
                <ul id="forgottenpassword">
                    <li class="boldtext">|</li>
                    <li>
                        <input id="remember" type="checkbox" name="remember" id="rememberMe"><label for="rememberMe">记住我</label></li>
                </ul>
                <button id="resetbtn" type="RESET" class="positive" name="Reset" style="width: 69px;">重置</button>
                </div>
            </fieldset>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $('#password').keyboard({
            openOn: null,
            stayOpen: true,
            layout: 'qwerty'
        }).addTyping();
        $('#passwd').click(function() {
            $('#password').getkeyboard().reveal();
        })

        $(".logininput").blur(function() {
            if ($(this).val() == "") {
                $(this).css("border-color", "red");
            }
            else
                $(this).css("border-color", "#D9D6C4");
        })

        $("#loginbtn").click(function() {
            var k = 0;
            var ajaxhtml = "";
            $(".logininput").each(function(i, obj) {
                if ($(obj).val().trim() == "") {
                    k++;
                    $(this).css("border-color", "red");
                    $(this).focus();
                    return false;
                }
            });
            if (k != 0) return;
            ajaxhtml = $("#loginbtn").html();
            $("#loginbtn").html("Loading....  <img src='images/loading.gif' alt='Announcement' /> ");
            $("#loginbtn").attr("disabled", "disabled","disabled");

        })
    });

</script>
</body>