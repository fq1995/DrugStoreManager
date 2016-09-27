<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath%>css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/usermanager.js"></script>
<script src="<%=basePath%>js/usermanager_operation.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=basePath%>js/usermanager_page.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	
 	/* $(function() {

		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		}); 
		
	});  */
	
	/* function choose(){
            //获取最上端的checkbox的对象
            var all=document.getElementsByName("all")[0];
            var ids=document.getElementsByName("id_check");
            if(all.checked){
                //当前是选中的状态
               for(var i in ids){
                   ids[i].checked=true;
               }
            }else{
                //当前是取消的状态
                for(var i in ids){
                    ids[i].checked=false;
                }
            } 
        } */
 
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">基本操作</a></li>
			<li><img src="images/next.gif" style="margin-top:13px"></li>
			<li><a href="#">用户管理</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li id="btn_addUser" class="click"><span><img
						src="<%=basePath%>images/t01.png" /></span>添加</li>
				<li id="btn_updateUser" class="click"><span><img
						src="<%=basePath%>images/t02.png"  /></span>修改</li>
				<li id="btn_deleteUser" class="click"><span><img src="<%=basePath%>images/t03.png" /></span>删除</li>
				<li id="" class="click"><span><img src="<%=basePath%>images/t04.png" /></span>统计</li>
			</ul>


			<ul class="toolbar1">
				<li><span><img src="<%=basePath%>images/t05.png" /></span>设置</li>
			</ul>

		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th><input id="all" name="all" type="checkbox" value="" /></th>
					<th>序号</th>
					<th>用户编号</th>
					<th>用户名</th>
					<th>密码</th>
					<th>权限</th>
					<th>是否审核</th>
					<th>添加日期</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.page.list}" var="user"
					varStatus="state">
					<tr>
						<td><input name="id_check" type="checkbox"
							value="${state.count }" id="${user.userid}"/></td>
						<td>${user.userid }</td>
						<td>${user.usercode }</td>
						<td>${user.username }</td>
						<td>${user.password }</td>
						<td>${user.rolecode }</td>
						<td>是</td>
						<td>${user.addtime }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<div class="pagin">
			<div class="message">
				共<i class="blue">${page.total }</i>条记录，当前显示第&nbsp;<i class="blue">${page.currPage }</i>页
			</div>
			<ul class="paginList">
				<c:if test="${page.perIndex > 0}">
					<li class="paginItem"><a
						href="${pageContext.request.contextPath }/user_showUser.action?currPage=1">首页</a></li>
					<li class="paginItem"><a
						href="${pageContext.request.contextPath }/user_showUser.action?currPage=${page.perIndex}"><span
							class="pagepre"></span></a></li>
				</c:if>
				<c:forEach begin="1" end="${page.totalPage }" var="p">
					<c:choose>
						<c:when test="${p eq page.currPage}">
							<li class="paginItem"><a href="#">[${p}]</a></li>
						</c:when>
						<c:otherwise>
							<li class="paginItem"><a
								href="${pageContext.request.contextPath }/user_showUser.action?currPage=${p}">${p}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${page.nextIndex > 0}">
					<li class="paginItem"><a
						href="${pageContext.request.contextPath }/user_showUser.action?currPage=${page.nextIndex}"><span
							class="pagenxt"></span></a></li>
					<li class="paginItem"><a
						href="${pageContext.request.contextPath }/user_showUser.action?currPage=${page.totalPage}">尾页</a></li>
				</c:if>
				&nbsp;
				<select id="select_jumpPage" style="height: 30px" onchange="jump()">
					<c:forEach begin="1" end="${requestScope.page.totalPage}" var="page">
						<c:if test="${page==requestScope.page.currPage}">
							<li class="paginItem">
							<option value="${page}" selected>${page}</option>
							</li>
						</c:if>
						<c:if test="${page!=requestScope.page.currPage}">
							<li class="paginItem">
							<option value="${page}">${page}</option>
							</li>
						</c:if>
					</c:forEach>
				</select> &nbsp;
			</ul>
		</div>


		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="<%=basePath%>images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp;
				<input name="" type="button" class="cancel" value="取消" />
			</div>

		</div>




	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
		function jump(){
			var pc = $("#select_jumpPage option:selected").text();
			window.location.href="${pageContext.request.contextPath}/user_showUser.action?currPage=" + pc;
		} 
	</script>

</body>

</html>
