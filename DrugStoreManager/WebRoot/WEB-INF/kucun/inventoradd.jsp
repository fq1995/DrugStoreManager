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
<title>库存药品添加</title>
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
 		$("#drugName").css("background-color",""); 
 		$("#modifier").css("background-color",""); 
 		$("#drugCode").css("background-color",""); 
 		$("#salepeice").css("background-color","");
 		
 		$("#dosageform").css("background-color",""); 
 		$("#unitname").css("background-color",""); 
 		$("#category").css("background-color",""); 
 		
 		
 		//售价
 		$("#salepeice").blur(function(){
 			var name = $("#salepeice").val(); 
 	        if($.trim(name) == "" || name.length == 0 ){
 	        	$("#salepeice").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#salepeice").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
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
 		$("#drugCode").blur(function(){
 			var name = $("input[name='drugBean.drugCode']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >10){
 	        	$("#drugCode").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#drugCode").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//药品名非空
 		$("#drugName").blur(function(){
 			var name = $("input[name='drugBean.drugName']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ){
 	        	$("#drugName").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#drugName").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//修改人非空
 		$("#modifier").blur(function(){
 			var pass = $("#modifier").val();
 			if ($.trim(pass) == "" || pass.length == 0) {
 				$("#modifier").css("background-color","#FFB9B9"); 
 				$("#add").attr("disabled",true);   
 			}else{
 				$("#modifier").css("background-color",""); 
 				$("#add").attr("disabled",false);   
 	        }
 		}); 
 		
 		//数量
 		
 		$("#stocknumber").blur(function(){
 			var number = $("#stocknumber").val();
 			var reg = /^\+?[1-9][0-9]*$/;
 			if ($.trim(number) == "" || number.length == 0) {
 				$("#stocknumber").css("background-color","#FFB9B9"); 
 				$("#add").attr("disabled",true);   
 			}else if(!reg.test(number)){
 				$("#stocknumber").css("background-color","#FFB9B9"); 
 				$("#add").attr("disabled",true);  
 			}else{
 				$("#stocknumber").css("background-color",""); 
 			}
 		});
 		//下限
 		$("#stocklimit").blur(function(){
 			var number = $("#stocklimit").val();
 			var reg = /^\+?[1-9][0-9]*$/;
 			if ($.trim(number) == "" || number.length == 0) {
 				$("#stocklimit").css("background-color","#FFB9B9"); 
 				$("#add").attr("disabled",true);   
 			}else if(!reg.test(number)){
 				$("#stocklimit").css("background-color","#FFB9B9"); 
 				$("#add").attr("disabled",true);  
 			}else{
 				$("#stocklimit").css("background-color",""); 
 				$("#add").attr("disabled",false);  
 			}
 		});
 		
 		function check(){
 	
 			var name = $("input[name='drugBean.drugName']").val(); 
 			var pass = $("#modifier").val();
 			var number = $("#stocknumber").val();
 			var limit = $("#stocklimit").val();
 			var dosageform = $("#dosageform").val(); 
	 		var unitname = $("#unitname").val(); 
	 		var category = $("#category").val(); 
	 		var salepeice = $("#salepeice").val();
	 		var reg = /^\+?[1-9][0-9]*$/;
 			if(!reg.test(number) || !reg.test(number) || $.trim(category) == "" || category.length == 0 || $.trim(unitname) == "" || unitname.length == 0 || $.trim(dosageform) == "" || dosageform.length == 0 || $.trim(name) == "" || name.length == 0 || $.trim(pass) == "" || pass.length == 0 || $.trim(number) == "" || number.length == 0 && $.trim(limit) == "" || limit.length == 0 || $.trim(salepeice) == "" || salepeice.length == 0){
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
 				var name = $("input[name='drugBean.drugName']").val(); 
 	 			var pass = $("#modifier").val();
 	 			var number = $("#stocknumber").val();
 	 			var limit = $("#stocklimit").val();
 	 			var dosageform = $("#dosageform").val(); 
 	 			var unitname = $("#unitname").val(); 
 	 			var category = $("#category").val(); 
 	 			var salepeice = $("#salepeice").val();
 	 			var reg = /^\+?[1-9][0-9]*$/;
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
 	 			}else if($.trim(salepeice) == "" || salepeice.length == 0){
 	 				alert("请输入销售价格");
 	 				return false;
 	 			}else if($.trim(pass) == "" || pass.length == 0){
 	 				alert("请输入修改人");
 	 				return false;
 	 			}else if($.trim(number) == "" || number.length == 0 || !reg.test(number)){
 	 				alert("请输入数量");
 	 				return false;
 	 			}else if($.trim(limit) == "" || limit.length == 0 || !reg.test(limit)){
 	 				alert("请输入库存下限");
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
			<li><a href="#">首页</a></li>
			<li><img src="images/next.gif" ></li>
			<li><a href="#">表单</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="inventor_addInventor.action" method="post">
			<input type="hidden" name="currPage" value="1">
			<input type="hidden" name="time">

			<ul class="forminfo">
				<li><label>库存单编号</label><input name="stockCode" type="text" id="stockCode" value="${requestScope.stockCode+1 }" readonly="readonly"
					class="form-control" style="width:200px; display:inline" placeholder="请输入库存单编号" /><i></i></li>
				<li><label>药品编号</label><input name="drugBean.drugCode" type="text" id="drugCode" value="${requestScope.drugCode+1 }" readonly="readonly"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品编号"/><i></i><i style="color: red">${message2}</i></li>
				<li><label>药品名</label><input name="drugBean.drugName" type="text" id="drugName"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品名"/><i>必填</i><i style="color: red">${message}</i></li>
				<li><label>剂型</label>
					<select class="form-control" style="width:200px;height:34px;display: inline;"  name="drugBean.dosageformBean.dosageformId" id="dosageform">
						<option value="">请选择</option>
					<c:forEach items="${dosageformList }" var="dosageform">
						<option value="${dosageform.dosageformId }">${dosageform.dosageform }</option>
					</c:forEach>
					</select><i>必填</i></li>
				<li><label>单位</label>
					<select class="form-control" style="width:200px;height:34px;display: inline;" name="drugBean.drugUnitBean.unitnameId" id="unitname">
						<option value="">请选择</option>
					<c:forEach items="${drugUnitList }" var="drugUnit">
						<option value="${drugUnit.unitnameId }">${drugUnit.unitname }</option>
					</c:forEach>
					</select><i>必填</i></li>
				<li><label>类别</label>
					<select class="form-control" style="width:200px;height:34px;display: inline;"  name="drugBean.drugCategoryBean.categoryId" id="category">
						<option value="">请选择</option>
					<c:forEach items="${drugCategoryList }" var="drugCategory">
						<option value="${drugCategory.categoryId }">${drugCategory.category }</option>
					</c:forEach>
					</select><i>必填</i></li>
				<li><label>厂商</label><input name="drugBean.manufacturer" type="text" id="manufacturer"
					class="form-control" style="width:200px; display:inline" placeholder="请输入生产厂商"/><i></i></li>
				<li><label>售价</label><input name="drugBean.salepeice" type="text" id="salepeice"
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售价格"/><i>必填</i></li>
				<li><label>产品说明</label><input name="drugBean.memo" type="text" id="memo"
					class="form-control" style="width:200px; display:inline" placeholder="请输入产品说明"/><i></i></li>
				<li><label>批准文号</label><input name="drugBean.approvalNumber" type="text" id="approvalNumber"
					class="form-control" style="width:200px; display:inline" placeholder="请输入批准文号"/><i></i></li>
				<li><label>修改人</label><input name="drugBean.modifier" type="text" id="modifier"
					class="form-control" style="width:200px; display:inline" placeholder="请输入修改人"/><i>必填</i></li>
				<li><label>库存数量</label><input name="drugBean.stocknumber" type="text" id="stocknumber"
					class="form-control" style="width:200px; display:inline" placeholder="请输入库存数量"/><i>必填</i></li>
				<li><label>库存下限</label><input name="stocklimit" type="text" id="stocklimit"
					class="form-control" style="width:200px; display:inline" placeholder="请输入库存下限"/><i>必填</i></li>
				
				<li><input id="add" type="submit" class="btn btn-info btn-sm" value="确认保存" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" onclick="javascript:history.go(-1);" value="返回" /></li>
			</ul>
		</form>
	</div>
</body>