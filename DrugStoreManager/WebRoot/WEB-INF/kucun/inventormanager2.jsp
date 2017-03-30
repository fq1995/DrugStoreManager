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
<title>库存下限预警</title>
<link href="<%=basePath%>css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/kucunmanager_operation2.js" type="text/javascript" charset="utf-8"></script>

<style type="text/css">
	th,td  
	{  
 	white-space: nowrap;  
	}  
	
</style> 
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">基本操作</a></li>
			<li><img src="<%=basePath%>images/next.gif" ></li>
			<li><a href="#">药品管理</a></li>
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
				
				<li id="btn_stats" class="click"><span><img src="<%=basePath%>images/t04.png" /></span>统计</li>
				
				<li id="btn_buy" class="click"><span><img src="<%=basePath%>images/buy.gif" /></span>添加进采购单</li>
				
				<li id="btn_warn2" class="click"><span><img src="<%=basePath%>images/warn.png" /></span>有效期预警</li>
				
				<li id="btn_warn3" class="click"><span><img src="<%=basePath%>images/warn.png" /></span>过期药品</li>
				
				<li id="btn_doprint" class="click"><span><img src="<%=basePath%>images/dayin.png" /></span>打印报表</li>
			</ul>


			<ul class="toolbar1">
				<li style="border:0px"> <input class="form-control" placeholder="输入需要查询的药品名" value="${requestScope.keyword }" style="width:180px;" type="text" id="keyword" name="keyword"/></li>&nbsp;&nbsp;
				<li style="border:0px"><button id="btn_selectUser" type="button" class="btn btn-info btn-sm">查询</button></li>
				
				<%-- <li><span><img src="<%=basePath%>images/t05.png" /></span>设置</li> --%>
			</ul>

		</div>

		<div style="overflow:auto; width:100%; height:auto;">

		<table class="tablelist">
			<thead>
				<tr>
					<th style="width:40px"><input id="all" name="all" type="checkbox" value=""/></th>
					<th style="width:50px">序号</th>
					<th style="width:70px">药品编号</th>
					<th>药品名</th>
					<th style="width:70px">剂型</th>
					<th style="width:50px">单位</th>
					<th style="width:50px">类别</th>
					<th>厂 家</th>
					<th>批准文号</th>
					<th>修改人</th>
					<th>入库日期</th>
					<th>库存数量</th>
					<th>库存下限</th>
					<th>产品说明</th>
					<th>建单时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.page.list}" var="inventor"
					varStatus="state">
					<tr>
						<td style="width:40px"><input name="id_check" type="checkbox"
							value="${inventor.stockId }" id="${inventor.stockId}"/></td>
						<td style="width:50px">${state.count }</td>
						<td style="width:70px">${inventor.drugBean.drugCode }</td>
						<td>${inventor.drugBean.drugName }</td>
						<td style="width:70px">${inventor.drugBean.dosageformBean.dosageform }</td>
						<td style="width:50px">${inventor.drugBean.drugUnitBean.unitname }</td>
						<td>${inventor.drugBean.drugCategoryBean.category }</td>
						<td>${inventor.drugBean.manufacturer }</td>
						<td>${inventor.drugBean.approvalNumber }</td>
						<td>${inventor.drugBean.modifier }</td>
						<td>${inventor.drugBean.modifyTime }</td>
						<c:choose>
						<c:when test="${f eq 'warn' }">
						<td style="background-color: #F7C709">${inventor.drugBean.stocknumber }</td>
						<td style="background-color: #E6421A">${inventor.stocklimit }</td>
						</c:when>
						<c:otherwise>
						<td>${inventor.drugBean.stocknumber }</td>
						<td>${inventor.stocklimit }</td>
						</c:otherwise>
						</c:choose>
						<td>${inventor.drugBean.memo }</td>
						<td>${inventor.date }</td>
					
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>

		<div class="pagin">
			<div class="message">
				共<i class="blue">${page.total }</i>条记录，当前显示第&nbsp;<i class="blue">${page.currPage }</i>页
			</div>
			<ul class="paginList">
				<c:if test="${page.perIndex > 0}">
					<li class="paginItem"><a 
						href="${pageContext.request.contextPath }/inventor_showWarn.action?currPage=1&keyword=${keyword}">首页</a></li>
					<li class="paginItem"><a 
						href="${pageContext.request.contextPath }/inventor_showWarn.action?currPage=${page.perIndex}&keyword=${keyword}"><span
							class="pagepre"></span></a></li>
				</c:if>
				<c:forEach begin="1" end="${page.totalPage }" var="p">
					<c:choose>
						<c:when test="${p eq page.currPage}">
							<li class="paginItem"><a  href="#" style="background-color:#337AB7;color:#FFFFFF">${p}</a></li>
						</c:when>
						<c:otherwise>
							<li class="paginItem"><a 
								href="${pageContext.request.contextPath }/inventor_showWarn.action?currPage=${p}&keyword=${keyword}">${p}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${page.nextIndex > 0}">
					<li class="paginItem"><a id="next"
						href="${pageContext.request.contextPath }/inventor_showWarn.action?currPage=${page.nextIndex}&keyword=${keyword}"><span
							class="pagenxt"></span></a></li>
					<li class="paginItem"><a id="total"
						href="${pageContext.request.contextPath }/inventor_showWarn.action?currPage=${page.totalPage}&keyword=${keyword}">尾页</a></li>
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
</body>
<script type="text/javascript">
$('.tablelist tbody tr:odd').addClass('odd');
function jump(){
var pc = $("#select_jumpPage option:selected").text();
window.location.href="${pageContext.request.contextPath}/inventor_showWarn.action?keyword=${keyword}&currPage="+pc;
} 
</script>

</html>
