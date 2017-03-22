<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8 />
<title>药品采购单修改</title>

<link href="css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function() {
		var str = "";
		var mydate = new Date();
		str += mydate.getFullYear() + "-";
		str += mydate.getMonth() + 1 + "-";
		str += mydate.getDate();
		$("input[name='time']").val(str);

	});
	$(function() {
		$("#drugName").css("background-color", "");
		$("#modifier").css("background-color", "");
		$("#drugCode").css("background-color", "");

		//药品编号非空
		$("#drugCode").blur(function() {
			var name = $("input[name='drugBean.drugCode']").val();
			if ($.trim(name) == "" || name.length == 0 || name.length > 10) {
				$("#drugCode").css("background-color", "#FFB9B9");
				$("#add").attr("disabled", true);
			} else {
				$("#drugCode").css("background-color", "");
				$("#add").attr("disabled", false);
			}
		});

		//药品名非空
		$("#drugName").blur(function() {
			$("#message").html("");
			var name = $("input[name='drugBean.drugName']").val();
			$.ajax({
				url : 'drug_validateName.action',
				type : 'POST',
				data : {
					'name' : name
				},
				dataType : 'json',
				success : function(data) {
					if ($.trim(name) == "" || name.length == 0) {
						$("#drugName").css("background-color", "#FFB9B9");
						$("#add").attr("disabled", true);
					} else if ("药品名可用" == data) {
						$("#drugName").css("background-color", "");
						$("#add").attr("disabled", false);
						$("#message").html("药品名可用");
					} else if ("药品名不可用" == data) {
						$("#message").html("药品名不可用");
						$("#add").attr("disabled", true);
						$("#drugName").css("background-color", "#FFB9B9");
					} else {
						$("#message").html("药品名不可为空");
					}
				},
				error : function(data) {
				}
			});

		});

		//修改人
		$("#modifier").blur(function() {
			var pass = $("#modifier").val();
			if ($.trim(pass) == "" || pass.length == 0) {
				$("#modifier").css("background-color", "#FFB9B9");
				$("#add").attr("disabled", true);
			} else {
				$("#modifier").css("background-color", "");
				$("#add").attr("disabled", false);
			}
		});

	});
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">药品采购单管理</a></li>
			<li><img src="<%=basePath%>images/next.gif" ></li>
			<li><a href="#">药品采购单列表</a></li>
			<li><img src="images/next.gif"></li>
			<li><a href="#">药品采购单修改</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="buy_addBuy.action" method="post">
			<input type="hidden" name="currPage" value="1"> <input
				type="hidden" name="time">

			<ul class="forminfo">
				<li><label>采购单编号</label><input name="drugbuyCode" type="text"
					id="drugCode" value="${buy.drugbuyCode }"
					readonly="readonly" class="form-control"
					style="width: 200px; display: inline" placeholder="请输入药品编号" /><i>必填</i><i
					style="color: red">${message2}</i></li>
				<li><label>药品编号</label><input name="drugBean.drugCode" type="text"
					id="drugCode" value="${buy.drugBean.drugCode }"
					readonly="readonly" class="form-control"
					style="width: 200px; display: inline" placeholder="请输入药品编号" /><i>必填</i><i
					style="color: red">${message2}</i></li>
				<li><label>药品名</label><input name="drugBean.drugName" type="text"
					id="drugName" class="form-control" value="${buy.drugBean.drugName }"
					style="width: 200px; display: inline" placeholder="请输入药品名" /><i>必填</i><i
					id="message" style="color: red">${message}</i></li>
				<li><label>剂型</label>
					<select id="form" class="form-control" style="width:200px;height:34px"  name="drugBean.dosageformBean.dosageformId" value="drugBean.dosageformBean.dosageformId">
						<option value="${buy.drugBean.dosageformBean.dosageformId }">${buy.drugBean.dosageformBean.dosageform }</option>
					<c:forEach items="${dosageformList }" var="dosageform">
						<option value="${dosageform.dosageformId }">${dosageform.dosageform }</option>
					</c:forEach>
					</select></li>
				<li><label>单位</label>
					<select class="form-control" style="width:200px;height:34px"  name="drugBean.drugUnitBean.unitnameId">
						<option value="${buy.drugBean.drugUnitBean.unitnameId }">${buy.drugBean.drugUnitBean.unitname }</option>
					<c:forEach items="${drugUnitList }" var="drugUnit">
						<option value="${drugUnit.unitnameId }">${drugUnit.unitname }</option>
					</c:forEach>
					</select></li>
				<li><label>类别</label>
					<select class="form-control" style="width:200px;height:34px"  name="drugBean.drugCategoryBean.categoryId">
						<option value="${buy.drugBean.drugCategoryBean.categoryId }">${buy.drugBean.drugCategoryBean.category }</option>
					<c:forEach items="${drugCategoryList }" var="drugCategory">
						<option value="${drugCategory.categoryId }">${drugCategory.category }</option>
					</c:forEach>
					</select></li>
				
				<li><label>厂商</label><input name="drugBean.manufacturer" type="text"
					id="manufacturer" class="form-control" value="${buy.drugBean.manufacturer}"
					style="width: 200px; display: inline" placeholder="请输入生产厂商" /><i></i></li>
				
				<li><label>批准文号</label><input name="drugBean.approvalNumber" type="text"
					id="approvalNumber" class="form-control" value="${buy.drugBean.approvalNumber}"
					style="width: 200px; display: inline" placeholder="请输入批准文号" /><i></i></li>
				
				<li><label>采购数量</label><input name="mount" type="text"
					id="mount" class="form-control" value="${buy.mount}" 
					style="width: 200px; display: inline" placeholder="请输入采购数量" /><i></i></li>	
					
				<li><label>修改人</label><input name="modifier" type="text"
					id="modifier" class="form-control" value="${buy.modifier }"
					style="width: 200px; display: inline" placeholder="请输入修改人" /><i>必填</i></li>
				<li><input id="add" type="submit" class="btn btn-info btn-sm"
					disabled value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp; <input
					id="return" type="button" class="btn btn-info btn-sm"
					onclick="javascript:history.go(-1);" value="返回" /></li>
			</ul>


			
		</form>
	</div>
</body>