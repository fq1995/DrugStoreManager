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
 	$(function() {
		$("#drugName").css("background-color", "");
		$("#modifier").css("background-color", "");
		$("#drugCode").css("background-color", "");
		$("#dosageform").css("background-color",""); 
 		$("#unitname").css("background-color",""); 
 		$("#category").css("background-color",""); 
 		$("#salepeice").css("background-color",""); 
 		$("#mess").html("");
 		
 		//销售价
 		$("#salepeice").blur(function(){
 			var name = $("#salepeice").val(); 
 			var reg = /^[0-9].*$/;
 	        if($.trim(name) == "" || name.length == 0 ){
 	        	$("#salepeice").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        	$("#mess").html("请输入正确格式的价格");
 	        }else if(!reg.test(name)){
 	        	$("#salepeice").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);  
 	        	$("#mess").html("请输入正确格式的价格");
 	        }else{
 	        	$("#salepeice").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        	$("#mess").html("");
 	        }
 		});
 		
 		
 		//剂型
 		$("#dosageform").blur(function(){
 			var name = $("#dosageform").val(); 
 	        if($.trim(name) == "" || name.length == 0 ){
 	        	$("#dosageform").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#dosageform").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//单位
 		$("#unitname").blur(function(){
 			var name = $("#unitname").val(); 
 	        if($.trim(name) == "" || name.length == 0 ){
 	        	$("#unitname").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#unitname").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//类别
 		$("#category").blur(function(){
 			var name = $("#category").val(); 
 	        if($.trim(name) == "" || name.length == 0 ){
 	        	$("#category").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#category").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
		//药品编号非空
		$("#drugCode").blur(function() {
			var name = $("input[name='drugCode']").val();
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
			var name = $("input[name='drugName']").val();
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
						$("#message").css("color","green");
						$("#message").html("药品名可用");
					} else if ("药品名不可用" == data) {
						$("#message").css("color","red");
						$("#message").html("该药品已存在");
						$("#add").attr("disabled", true);
						$("#drugName").css("background-color", "#FFB9B9");
					} else {
						$("#message").css("color","red");
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

		function check(){
		 	
 			var name = $("input[name='drugName']").val(); 
 			var pass = $("#modifier").val();
 			var dosageform = $("#dosageform").val(); 
	 		var unitname = $("#unitname").val(); 
	 		var category = $("#category").val(); 
	 		var salepeice = $("#salepeice").val(); 
	 		var reg = /^[0-9].*$/;
 			if(!reg.test(salepeice) || $.trim(category) == "" || category.length == 0 || $.trim(category) == "" || category.length == 0 || $.trim(unitname) == "" || unitname.length == 0 || $.trim(dosageform) == "" || dosageform.length == 0 || $.trim(name) == "" || name.length == 0 || $.trim(pass) == "" || pass.length == 0 ){
 				$("#add").attr("disabled",false);  
 				return false;
 			}else{
 				return true;
 			}
 			
 		};
 		
 		//保存按钮
 		$("#add").click(function(){
 			if(!check()){
 				$("#add").attr("disabled",true);  
 				var name = $("input[name='drugName']").val(); 
 	 			var pass = $("#modifier").val();
 	 			var dosageform = $("#dosageform").val(); 
 	 			var unitname = $("#unitname").val(); 
 	 			var category = $("#category").val(); 
 	 			var salepeice = $("#salepeice").val(); 
 	 			var reg = /^[0-9].*$/;
 	 			if($.trim(name) == "" || name.length == 0){
 	 				alert("请输入药品名");
 	 				return false;
 	 			}else if($.trim(dosageform) == "" || dosageform.length == 0){
 	 				alert("请选择药品剂型");
 	 				return false;
 	 			}else if($.trim(unitname) == "" || unitname.length == 0){
 	 				alert("请选择药品单位");
 	 				return false;
 	 			}else if($.trim(category) == "" || category.length == 0){
 	 				alert("请选择药品类别");
 	 				return false;
 	 			}else if($.trim(salepeice) == "" || salepeice.length == 0 || !reg.test(salepeice)){
 	 				alert("请输入正确格式的价格");
 	 				return false;
 	 			}else if($.trim(pass) == "" || pass.length == 0){
 	 				alert("请输入修改人");
 	 				return false;
 	 			}
 			}else if(check()){
 				$("#add").attr("disabled",false); 
 				$("form").submit(); 
 			};
 		});
 		
 		
	});
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">药品管理</a></li>
			<li><img src="<%=basePath%>images/next.gif" ></li>
			<li><a href="#">药品列表</a></li>
			<li><img src="images/next.gif"></li>
			<li><a href="#">药品修改</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="drug_updateDrug.action" method="post" enctype="multipart/form-data">
			<input type="hidden" name="currPage" value="1">
			<input type="hidden" name="time">
			<input type="hidden" name="drugId" value="${drug.drugId }">

			<ul class="forminfo">
				<li><label>药品编号</label><input name="drugCode" type="text" id="drugCode" value="${drug.drugCode }" readonly="readonly"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品编号" /><i></i><i style="color: red">${message2}</i></li>
				<li><label>药品名</label><input name="drugName" type="text" id="drugName" value="${drug.drugName }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品名"/><i>必填</i><i style="color: red">${message}</i></li>
				<li><label>剂型</label>
					<select class="form-control" style="width:200px;height:34px;display: inline;"  name="dosageformBean.dosageformId"  id="dosageform">
						<option value="${drug.dosageformBean.dosageformId }">${drug.dosageformBean.dosageform }</option>
					<c:forEach items="${dosageformList }" var="dosageform">
						<option value="${dosageform.dosageformId }">${dosageform.dosageform }</option>
					</c:forEach>
					</select><i>必填</i></li>
				<li><label>单位</label>
					<select class="form-control" style="width:200px;height:34px;display: inline;"  name="drugUnitBean.unitnameId" id="unitname">
						<option value="${drug.drugUnitBean.unitnameId }">${drug.drugUnitBean.unitname }</option>
					<c:forEach items="${drugUnitList }" var="drugUnit">
						<option value="${drugUnit.unitnameId }">${drugUnit.unitname }</option>
					</c:forEach>
					</select><i>必填</i></li>
				<li><label>类别</label>
					<select class="form-control" style="width:200px;height:34px;display: inline;"  name="drugCategoryBean.categoryId" id="category">
						<option value="${drug.drugCategoryBean.categoryId }">${drug.drugCategoryBean.category }</option>
					<c:forEach items="${drugCategoryList }" var="drugCategory">
						<option value="${drugCategory.categoryId }">${drugCategory.category }</option>
					</c:forEach>
					</select><i>必填</i></li>
				<li><label>销售价</label><input name="salepeice" type="text" id="salepeice" value="${drug.salepeice }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售价"/><i>必填</i><i
					id="mess" style="color: red"></i></li>
				<li><label>厂商</label><input name="manufacturer" type="text" id="manufacturer" value="${drug.manufacturer }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入生产厂商"/><i></i></li>
				<li><label>产品说明</label><input name="memo" type="text" id="memo" value="${drug.memo }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入产品说明"/><i></i></li>
				<li><label>药品图片</label>
				<img alt="" src="${pageContext.request.contextPath}/upload/${drug.oldName}" style="width:80px;height:48px ">
				<input name="photo" type="file" id="photo" style="border: none"></li>				
				<li><label>批准文号</label><input name="approvalNumber" type="text" id="approvalNumber" value="${drug.approvalNumber }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入批准文号"/><i></i></li>
				<li><label>修改人</label><input name="modifier" type="text" id="modifier" value="${drug.modifier }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入修改人"/><i>必填</i></li>
				
				<li><input id="add" type="submit" class="btn btn-info btn-sm" value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" onclick="javascript:history.go(-1);" value="返回" /></li>
			</ul>
		</form>
	</div>
</body>