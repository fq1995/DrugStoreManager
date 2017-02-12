<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>药品管理</title>
<link href="<%=basePath%>css/style1.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/drugmanager_operation.js" type="text/javascript" charset="utf-8"></script>

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
			<li><a href="#">药品管理</a></li>
			<li><img src="<%=basePath%>images/next.gif" ></li>
			<li><a href="#">药品列表</a></li>
			<li><img src="<%=basePath%>images/next.gif" ></li>
			<li><a href="#">多条件查询</a></li>
		</ul>
	</div>

	
	<div style="padding: 10px 10px 10px;">
	    <form class="bs-example bs-example-form" action="drug_showDrugByOptions.action?currPage=1" method="post">
	        <div class="input-group">
	            <span class="input-group-addon">药品名</span>
	            <input value="${drugName }" name="drugName" id="drugName" style="width:180px" type="text" class="form-control" placeholder="请输入药品名" >
	            
	            <span class="input-group-addon">剂型</span>
	            <select class="form-control" style="width:200px;height:34px" name="dosageformBean.dosageformId">
	            		<c:choose>
	            			<c:when test="${dosageform.dosageformId eq null }">
	            				<option value="">请选择剂型</option>
	            			</c:when>
	            			<c:otherwise>
	            				<option value="${dosageform.dosageformId }">${dosageform.dosageform }</option>
	            			</c:otherwise>
	            		</c:choose>
							
					<c:forEach items="${dosageformList }" var="dosageform">
						<option value="${dosageform.dosageformId }">${dosageform.dosageform }</option>
					</c:forEach>
				</select>
	            
	            <span class="input-group-addon">单位</span>
	            <select class="form-control" style="width:200px;height:34px" name="drugUnitBean.unitnameId">
						<c:choose>
	            			<c:when test="${unit.unitnameId eq null }">
	            				<option value="">请选择单位</option>
	            			</c:when>
	            			<c:otherwise>
	            				<option value="${unit.unitnameId }">${unit.unitname }</option>
	            			</c:otherwise>
	            		</c:choose>
						
					<c:forEach items="${drugUnitList }" var="drugUnit">
						<option value="${drugUnit.unitnameId }">${drugUnit.unitname }</option>
					</c:forEach>
				</select>
	            
	            <span class="input-group-addon">类别</span>
	            <select class="form-control" style="width:200px;height:34px"  name="drugCategoryBean.categoryId">
						<c:choose>
	            			<c:when test="${category.categoryId eq null }">
	            				<option value="">请选择类别</option>
	            			</c:when>
	            			<c:otherwise>
	            				<option value="${category.categoryId }">${category.category }</option>
	            			</c:otherwise>
	            		</c:choose>
						
					<c:forEach items="${drugCategoryList }" var="drugCategory">
						<option value="${drugCategory.categoryId }">${drugCategory.category }</option>
					</c:forEach>
				</select>
				
	        </div>
	        <br>
	        <div class="input-group">
	            <span class="input-group-addon" style="width:70px">厂商</span>
	            <input id="manufacturer" name="manufacturer" value="${manufacturer }" style="width:180px" type="text" class="form-control" placeholder="请输入厂商">
	            
	            <span class="input-group-addon">添加时间</span>
	            
	            <input id="modifyTime" name="modifyTime"  value="<fmt:formatDate value="${modifyTime }" pattern="yyyy-MM-dd"/>" style="width:180px" type="text" class="form-control" placeholder="请输入添加时间" onClick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})">
	            
	            <span class="input-group-addon">修改人</span>
	            <input id="modifier" name="modifier" value="${modifier }" style="width:180px" type="text" class="form-control" value="${modifier }" placeholder="请输入修改人">
	            
	            <input style="width:180px;margin-left:50px" type="submit" class="form-control btn btn-info btn-sm" placeholder="twitterhandle" id="select" value="查询">
	            
	        </div>
	        
	      
	    </form>
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
					<th >厂 家</th>
					<th>批准文号</th>
					<th>修改人</th>
					<th>添加日期</th>
					<th>产品说明</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.list}" var="drug"
					varStatus="state">
					<tr>
						<td style="width:40px"><input name="id_check" type="checkbox"
							value="${drug.drugId }" id="${drug.drugId }"/></td>
						<td style="width:50px">${state.count }</td>
						<td style="width:70px">${drug.drugCode }</td>
						<td>${drug.drugName }</td>
						<td style="width:70px">${drug.dosageformBean.dosageform }</td>
						<td style="width:50px">${drug.drugUnitBean.unitname }</td>
						<td>${drug.drugCategoryBean.category }</td>
						<td>${drug.manufacturer }</td>
						<td>${drug.approvalNumber }</td>
						<td>${drug.modifier }</td>
						<td>${drug.modifyTime }</td>
						<td>${drug.memo }</td>
					
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
						href="${pageContext.request.contextPath }/drug_showDrugByOptions.action?currPage=1&modifyTime=${modifyTime }&manufacturer=${manufacturer }&drugCategoryBean.categoryId=${category.categoryId }&drugUnitBean.unitnameId=${unit.unitnameId}&dosageformBean.dosageformId=${dosageform.dosageformId }&modifier=${modifier }&keyword=${keyword}&drugName=${drugName}">首页</a></li>
					<li class="paginItem"><a 
						href="${pageContext.request.contextPath }/drug_showDrugByOptions.action?currPage=${page.perIndex}&modifyTime=${modifyTime }&manufacturer=${manufacturer }&drugCategoryBean.categoryId=${category.categoryId }&drugUnitBean.unitnameId=${unit.unitnameId}&dosageformBean.dosageformId=${dosageform.dosageformId }&modifier=${modifier }&keyword=${keyword}&drugName=${drugName}"><span
							class="pagepre"></span></a></li>
				</c:if>
				<c:forEach begin="1" end="${page.totalPage }" var="p">
					<c:choose>
						<c:when test="${p eq page.currPage}">
							<li class="paginItem"><a  href="#" style="background-color:#337AB7;color:#FFFFFF">${p}</a></li>
						</c:when>
						<c:otherwise>
							<li class="paginItem"><a 
								href="${pageContext.request.contextPath }/drug_showDrugByOptions.action?currPage=${p}&drugCategoryBean.categoryId=${category.categoryId }&modifyTime=${modifyTime }&manufacturer=${manufacturer }&drugUnitBean.unitnameId=${unit.unitnameId}&dosageformBean.dosageformId=${dosageform.dosageformId }&modifier=${modifier }&keyword=${keyword}&drugName=${drugName}">${p}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${page.nextIndex > 0}">
					<li class="paginItem"><a id="next"
						href="${pageContext.request.contextPath }/drug_showDrugByOptions.action?currPage=${page.nextIndex}&drugCategoryBean.categoryId=${category.categoryId }&modifyTime=${modifyTime }&manufacturer=${manufacturer }&drugUnitBean.unitnameId=${unit.unitnameId}&dosageformBean.dosageformId=${dosageform.dosageformId }&modifier=${modifier }&keyword=${keyword}&drugName=${drugName}"><span
							class="pagenxt"></span></a></li>
					<li class="paginItem"><a id="total"
						href="${pageContext.request.contextPath }/drug_showDrugByOptions.action?currPage=${page.totalPage}&drugCategoryBean.categoryId=${category.categoryId }&modifyTime=${modifyTime }&manufacturer=${manufacturer }&drugUnitBean.unitnameId=${unit.unitnameId}&dosageformBean.dosageformId=${dosageform.dosageformId }&modifier=${modifier }&keyword=${keyword}&drugName=${drugName}">尾页</a></li>
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
	
</body>
<script type="text/javascript">
$('.tablelist tbody tr:odd').addClass('odd');
function jump(){
var pc = $("#select_jumpPage option:selected").text();
window.location.href="${pageContext.request.contextPath}/drug_showDrugByOptions.action?drugName=${drugName}&modifyTime=${modifyTime }&manufacturer=${manufacturer }&drugCategoryBean.categoryId=${category.categoryId }&drugUnitBean.unitnameId=${unit.unitnameId}&dosageformBean.dosageformId=${dosageform.dosageformId }&modifier=${modifier }&keyword=${keyword}&currPage="+pc;
} 
</script>

</html>
