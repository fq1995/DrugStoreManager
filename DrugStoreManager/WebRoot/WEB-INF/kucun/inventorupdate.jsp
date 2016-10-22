<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8 />
<title>药品修改</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript">
 	$(function(){
 		var str = "";
 		var mydate = new Date();
 		str += mydate.getFullYear()+"-";
 		str += mydate.getMonth()+1+"-";
 		str += mydate.getDate();
 		$("input[name='time']").val(str);
 		
 	});
 	/* $(function() {
 		
 		
 		
 		$("#drugName").css("background-color",""); 
 		$("#modifier").css("background-color",""); 
 		$("#drugCode").css("background-color",""); 
 		
 		//药品编号非空
 		$("#drugCode").blur(function(){
 			var name = $("input[name='drugCode']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >10){
 	        	$("#drugCode").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        	return false;
 	        }else{
 	        	$("#drugCode").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//药品名非空
 		$("#drugName").blur(function(){
 			var name = $("input[name='drugName']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ){
 	        	$("#drugName").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        	return false;
 	        }else{
 	        	$("#drugName").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//修改人
 		$("#modifier").blur(function(){
 			var pass = $("#modifier").val();
 			if ($.trim(pass) == "" || pass.length == 0) {
 				$("#modifier").css("background-color","#FFB9B9"); 
 				$("#add").attr("disabled",true);   
 				return false;
 			}else{
 				$("#modifier").css("background-color",""); 
 				$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		
 		
 	}); */
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><img src="images/next.gif" ></li>
			<li><a href="#">表单</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="inventor_updateInventor.action" method="post">
			<input type="hidden" name="currPage" value="1">
			<input type="hidden" name="time">
			<input type="hidden" name="drugId" value="${inventor.drugBean.drugId }">
			<input type="hidden" name="stockId" value="${inventor.stockId }">

			<ul class="forminfo">
				<li><label>库存单编号</label><input name="stockCode" type="text" id="stockCode" value="${inventor.stockCode }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入库存单编号" /><i>必填</i></li>
				<li><label>药品编号</label><input name="drugCode" type="text" id="drugCode" value="${drug.drugCode }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品编号" /><i>必填</i><i style="color: red">${message2}</i></li>
				<li><label>药品名</label><input name="drugName" type="text" id="drugName" value="${drug.drugName }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品名"/><i>必填</i><i style="color: red">${message}</i></li>
				<li><label>剂型</label>
					<select id="form" class="form-control" style="width:200px;height:34px"  name="drugBean.dosageformBean.dosageformId">
						<option value="${inventor.drugBean.dosageformBean.dosageformId }">${inventor.drugBean.dosageformBean.dosageform }</option>
					<c:forEach items="${dosageformList }" var="dosageform">
						<option value="${dosageform.dosageformId }">${dosageform.dosageform }</option>
					</c:forEach>
					</select></li>
				<li><label>单位</label>
					<select class="form-control" style="width:200px;height:34px"  name="drugBean.drugUnitBean.unitnameId">
						<option value="${inventor.drugBean.drugUnitBean.unitnameId }">${inventor.drugBean.drugUnitBean.unitname }</option>
					<c:forEach items="${drugUnitList }" var="drugUnit">
						<option value="${drugUnit.unitnameId }">${drugUnit.unitname }</option>
					</c:forEach>
					</select></li>
				<li><label>类别</label>
					<select class="form-control" style="width:200px;height:34px"  name="drugBean.drugCategoryBean.categoryId">
						<option value="${inventor.drugBean.drugCategoryBean.categoryId }">${inventor.drugBean.drugCategoryBean.category }</option>
					<c:forEach items="${drugCategoryList }" var="drugCategory">
						<option value="${drugCategory.categoryId }">${drugCategory.category}</option>
					</c:forEach>
					</select></li>
				<li><label>厂商</label><input name="manufacturer" type="text" id="manufacturer" value="${inventor.drugBean.manufacturer }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入生产厂商"/><i></i></li>
				<li><label>产品说明</label><input name="memo" type="text" id="memo" value="${inventor.drugBean.memo }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入产品说明"/><i></i></li>
				<li><label>批准文号</label><input name="approvalNumber" type="text" id="approvalNumber" value="${inventor.drugBean.approvalNumber}"
					class="form-control" style="width:200px; display:inline" placeholder="请输入批准文号"/><i></i></li>
				<li><label>修改人</label><input name="modifier" type="text" id="modifier" value="${inventor.drugBean.modifier }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入修改人"/><i>必填</i></li>
				<li><label>库存数量</label><input name="stocknumber" type="text" id="stocknumber" value="${inventor.stocknumber }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入库存数量"/><i>必填</i></li>
				<li><label>库存下限</label><input name="stocklimit" type="text" id="stocklimit" value="${inventor.stocklimit }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入库存下限"/><i>必填</i></li>
				
				<li><input id="add" type="submit" class="btn btn-info btn-sm" disabled value="确认保存" /></li>
			</ul>
		</form>
	</div>
</body>