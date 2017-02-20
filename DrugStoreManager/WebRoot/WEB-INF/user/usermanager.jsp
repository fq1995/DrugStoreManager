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
<title>用户管理</title>
<link href="<%=basePath%>css/style1.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/usermanager_operation.js" type="text/javascript" charset="utf-8"></script>
<%-- <script src="<%=basePath%>js/usermanager_page.js" type="text/javascript" charset="utf-8"></script> --%>
<script type="text/javascript">
	$(function(){
		var keyword = $("#keyword").val;
	})
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">基本操作</a></li>
			<li><img src="<%=basePath%>images/next.gif" ></li>
			<li><a href="#">用户管理</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				
				<li id="btn_addUser"  class="click"><span><img
						src="<%=basePath%>images/t01.png" /></span>添加</li>
				<li id="btn_updateUser" class="click"><span><img
						src="<%=basePath%>images/t02.png"  /></span>修改</li>
				<li id="btn_deleteUser" class="click"><span><img src="<%=basePath%>images/t03.png" /></span>删除</li>
				
				<%-- <li id="" class="click"><span><img src="<%=basePath%>images/t04.png" /></span>统计</li> --%>
			
				<li id="btn_print" class="click"><span><img src="<%=basePath%>images/dayin.png" /></span>打印报表</li>
			</ul>


			<ul class="toolbar1">
				<li style="border:0px"> <input class="form-control" placeholder="输入需要查询的用户名" style="width:180px;" type="text" id="keyword" name="keyword" value="${keyword }"/></li>&nbsp;&nbsp;
				 <button id="btn_selectUser" type="button" class="btn btn-info btn-sm">查询</button>
				
				<%-- <li><span><img src="<%=basePath%>images/t05.png" /></span>设置</li> --%>
			</ul>

		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th style="width: 50px"><input id="all" name="all" type="checkbox" value="" /></th>
					<th style="width: 50px">序号</th>
					<th>用户编号</th>
					<th>账号</th>
					<th>用户名</th>
					<th>昵称</th>
					<th>密码</th>
					<th>是否审核</th>
					<th>添加日期</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.page.list}" var="user"
					varStatus="state">
					<tr>
						<td style="width: 50px"><input name="id_check" type="checkbox"
							value="${user.userId }" id="${user.userId}"/></td>
						<td style="width: 50px">${state.count }</td>
						<td>${user.userCode }</td>
						<td>${user.email }</td>
						<td>${user.username }</td>
						<td>${user.nickname }</td>
						<td>${user.password }</td>
						<c:choose>
							<c:when test="${user.status eq 1}">
								<td>是</td>
							</c:when>
							<c:otherwise>
								<td>否</td>
							</c:otherwise>
						</c:choose>
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
						href="${pageContext.request.contextPath }/user_showUser.action?currPage=1&keyword=${keyword}">首页</a></li>
					<li class="paginItem"><a
						href="${pageContext.request.contextPath }/user_showUser.action?currPage=${page.perIndex}&keyword=${keyword}"><span
							class="pagepre"></span></a></li>
				</c:if>
				<c:forEach begin="1" end="${page.totalPage }" var="p">
					<c:choose>
						<c:when test="${p eq page.currPage}">
							<li class="paginItem"><a href="#" style="background-color:#337AB7;color:#FFFFFF">${p}</a></li>
						</c:when>
						<c:otherwise>
							<li class="paginItem"><a
								href="${pageContext.request.contextPath }/user_showUser.action?currPage=${p}&keyword=${keyword}">${p}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${page.nextIndex > 0}">
					<li class="paginItem"><a
						href="${pageContext.request.contextPath }/user_showUser.action?currPage=${page.nextIndex}&keyword=${keyword}"><span
							class="pagenxt"></span></a></li>
					<li class="paginItem"><a
						href="${pageContext.request.contextPath }/user_showUser.action?currPage=${page.totalPage}&keyword=${keyword}">尾页</a></li>
				</c:if>
				&nbsp;
				&nbsp;
				<div class="form-group" style="display:inline-block">
				    <select id="select_jumpPage" class="form-control" style="width:60px;height:30px" onchange="jump()" > 
				      <c:forEach begin="1" end="${requestScope.page.totalPage}" var="page">
						<c:if test="${page==requestScope.page.currPage}">
							<li class="">
							<option value="${page}" selected>${page}</option>
							</li>
						</c:if>
						<c:if test="${page!=requestScope.page.currPage}">
							<li class="">
							<option value="${page}">${page}</option>
							</li>
						</c:if>
					   </c:forEach>
				     </select>
				 </div>
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
window.location.href="${pageContext.request.contextPath}/user_showUser.action?keyword=${keyword}&currPage=" + pc;
} 
</script>
</body>

</html>
