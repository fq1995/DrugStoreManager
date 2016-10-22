<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>药店管理系统</title>
    <link href="<%=basePath%>css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/icon.css" />
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src='<%=basePath%>js/outlook2.js'> </script>
	<script type="text/javascript">
	
		function currentTime() {
			var d = new Date(), str = '';
			str += d.getFullYear() + '年';
			str += d.getMonth() + 1 + '月';
			str += d.getDate() + '日   ';
			str += d.getHours() + ':';
			str += d.getMinutes() + ':';
			str += d.getSeconds() + '';
			return str;
		}
		setInterval(function() {
			$('#time').html(currentTime)
		}, 1000);
	</script>
    <script type="text/javascript">

	 var _menus = {"menus":[
						{"menuid":"1","icon":"icon-sys","menuname":"基本操作",
							"menus":[
									{"menuid":"13","menuname":"用户管理","icon":"icon-users","url":"${pageContext.request.contextPath}/user_showUser.action?currPage=1"},
									{"menuid":"14","menuname":"角色管理","icon":"icon-role","url":"${pageContext.request.contextPath}/role_showRole.action?currPage=1"},
									{"menuid":"15","menuname":"权限设置","icon":"icon-set","url":"${pageContext.request.contextPath}/per_showPer.action?currPage=1"},
									{"menuid":"16","menuname":"系统日志","icon":"icon-log","url":"demo1.html"}
								]
						},{"menuid":"8","icon":"icon-sys","menuname":"员工管理",
							"menus":[{"menuid":"21","menuname":"员工列表","icon":"icon-nav","url":"${pageContext.request.contextPath}/emp_showEmp.action?currPage=1"},
									{"menuid":"22","menuname":"视频监控","icon":"icon-nav","url":"demo1.html"}
								]
						},{"menuid":"56","icon":"icon-sys","menuname":"药品管理",
							"menus":[{"menuid":"31","menuname":"药品列表","icon":"icon-nav","url":"${pageContext.request.contextPath}/drug_showDrug.action?currPage=1"},
									{"menuid":"32","menuname":"药品剂型","icon":"icon-nav","url":"${pageContext.request.contextPath}/form_showForm.action?currPage=1"},
									{"menuid":"32","menuname":"药品单位","icon":"icon-nav","url":"${pageContext.request.contextPath}/unit_showUnit.action?currPage=1"},
									{"menuid":"32","menuname":"药品类别","icon":"icon-nav","url":"${pageContext.request.contextPath}/category_showCategory.action?currPage=1"}
								]
						},{"menuid":"28","icon":"icon-sys","menuname":"库存管理",
							"menus":[{"menuid":"41","menuname":"库存列表","icon":"icon-nav","url":"${pageContext.request.contextPath}/inventor_showInventor.action?currPage=1"},
									{"menuid":"42","menuname":"报表统计","icon":"icon-nav","url":"demo1.html"},
									{"menuid":"43","menuname":"添加支出","icon":"icon-nav","url":"demo2.html"}
								]
						},{"menuid":"39","icon":"icon-sys","menuname":"商城管理",
							"menus":[{"menuid":"51","menuname":"商品分类","icon":"icon-nav","url":"demo.html"},
									{"menuid":"52","menuname":"商品列表","icon":"icon-nav","url":"demo1.html"},
									{"menuid":"53","menuname":"商品订单","icon":"icon-nav","url":"demo2.html"}
								]
						}
				]};
        //设置登录窗口
       /*  function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        } */
        //关闭登录窗口
         function closePwd() {
            $('#w').window('close');
        }
 
        //时间(点击其他页面不刷新)
 		/* setInterval(function() {
 		    var now = (new Date()).toLocaleString();
 		    $('#time').text(now);
 		}, 1000); */
        

					//修改密码
					/* function serverLogin() {
						var $newpass = $('#txtNewPass');
						var $rePass = $('#txtRePass');

						if ($newpass.val() == '') {
							msgShow('系统提示', '请输入密码！', 'warning');
							return false;
						}
						if ($rePass.val() == '') {
							msgShow('系统提示', '请在一次输入密码！', 'warning');
							return false;
						}

						if ($newpass.val() != $rePass.val()) {
							msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
							return false;
						}

						$.post('/ajax/editpassword.ashx?newpass='
								+ $newpass.val(), function(msg) {
							msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg,
									'info');
							$newpass.val('');
							$rePass.val('');
							close();
						})

					} */
			
					$(function() {
						/* openPwd();

						$('#editpass').click(function() {
							$('#w').window('open');
						});

						$('#btnEp').click(function() {
							serverLogin();
						})

						$('#btnCancel').click(function() {
							closePwd();
						})
  */
						$('#loginOut')
								.click(
										function() {
											$.messager
													.confirm(
															'系统提示',
															'您确定要退出本次登录吗?',
															function(r) {

																if (r) {
																	location.href = '<%=basePath%>login.jsp';
																}
															});
										})
					}); 
				</script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="<%=basePath%>images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(<%=basePath%>images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span id="opt_info" border="false" region="center" style=" width: 20px; height: 20px;"></span>
       
        <span style="float:right; padding-right:20px;" class="head"> <span id="time" style="..."></span>&nbsp;&nbsp;欢迎&nbsp;&nbsp;<span name="username" id="username">${username}</span>  <a style="text-decoration:none;cursor: hand;cursor: pointer;" href="#" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="<%=basePath%>images/blocks.gif" width="20" height="20" align="absmiddle" /> 药店管理系统</span>
        <span name="time"></span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer"></div>
    </div>
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
<div id="nav" class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
				
			</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >

		</div>
    </div>
    
    
    <!--修改密码窗口-->
    <!-- <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="Password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div> -->

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>