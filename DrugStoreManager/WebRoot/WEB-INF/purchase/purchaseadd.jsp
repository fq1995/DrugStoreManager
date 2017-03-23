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
<title>药品进货添加</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
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
 		//返回
 		$("#return").click(function(){
 			location.href = "${pageContext.request.contextPath}/pse_showPurchase.action?currPage=1";
 		});
 		
 		
 		$("#drugName").css("background-color",""); 
 		$("#modifier").css("background-color",""); 
 		$("input[name='drugBean.drugCode']").css("background-color",""); 
 		
 		//药品编号非空
 		$("input[name='drugBean.drugCode']").blur(function(){
 			var name = $("input[name='drugBean.drugCode']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >10){
 	        	$("input[name='drugBean.drugCode']").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("input[name='drugBean.drugCode']").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//药品名非空
 		$("input[name='drugBean.drugName']").blur(function(){
 			var name = $("input[name='drugBean.drugName']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ){
 	        	$("input[name='drugBean.drugName']").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("input[name='drugBean.drugName']").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//修改人
 		$("input[name='drugBean.modifier']").blur(function(){
 			var pass = $("input[name='drugBean.modifier']").val();
 			if ($.trim(pass) == "" || pass.length == 0) {
 				$("input[name='drugBean.modifier']").css("background-color","#FFB9B9"); 
 				$("#add").attr("disabled",true);   
 			}else{
 				$("input[name='drugBean.modifier']").css("background-color",""); 
 				$("#add").attr("disabled",false);   
 	        }
 		});
 		//数量
 		$("#amount").blur(function(){
 			var volue = $("#amount").val();
 			if(!isNaN(volue)){
 				if($.trim(volue) == "" || volue.length == 0){
 	 				alert("数量不能为空");
 	 			}
 				else if(volue <= 0){
 	 				alert("数量必须大于0");
 				}
 			}else{
 			   alert("输入的不是数字");
 			}
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
		<form action="pse_addPurchase.action" method="post">
			<input type="hidden" name="currPage" value="1">
			<input type="hidden" name="time">

			<ul class="forminfo">
				<li><label>进货单编号</label><input name="purchaseCode" type="text" id="purchaseCode" value="${requestScope.pseCode+1 }" readonly="readonly"
					class="form-control" style="width:200px; display:inline" placeholder="请输入进货单编号"/><i>必填</i></li>
				<li><label>药品编号</label><input name="drugBean.drugCode" type="text" id="drugBean.drugCode" value="${requestScope.drugCode+1 }" readonly="readonly"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品编号"/><i>必填</i></li>
				<li><label>药品名</label><input name="drugBean.drugName" type="text" id="drugBean.drugName"
					class="form-control" style="width:200px; display:inline" placeholder="请输入药品名"/><i>必填</i></li>
				<li><label>剂型</label>
					<select class="form-control" style="width:200px;height:34px"  name="drugBean.dosageformBean.dosageformId">
						<option value="">请选择</option>
					<c:forEach items="${dosageformList }" var="dosageform">
						<option value="${dosageform.dosageformId }">${dosageform.dosageform }</option>
					</c:forEach>
					</select></li>
				<li><label>单位</label>
					<select class="form-control" style="width:200px;height:34px" name="drugBean.drugUnitBean.unitnameId">
						<option value="">请选择</option>
					<c:forEach items="${drugUnitList }" var="drugUnit">
						<option value="${drugUnit.unitnameId }">${drugUnit.unitname }</option>
					</c:forEach>
					</select></li>
				<li><label>类别</label>
					<select class="form-control" style="width:200px;height:34px"  name="drugBean.drugCategoryBean.categoryId">
						<option value="">请选择</option>
					<c:forEach items="${drugCategoryList }" var="drugCategory">
						<option value="${drugCategory.categoryId }">${drugCategory.category }</option>
					</c:forEach>
					</select></li>
				<li><label>数量</label><input name="amount" type="text" id="amount"
					class="form-control" style="width:200px; display:inline" placeholder="请输入数量"/><i></i></li>
				<li ><label>生产日期</label>
					<input name="productionDate" id="productionDate" class="form-control" type="text" style="width:200px; display:inline"  onClick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/><i>请输入生产日期</i></li>	
				<li ><label>有效期</label>
					<input name="validityDate" id="validityDate" class="form-control" type="text" style="width:200px; display:inline"  onClick="WdatePicker({skin:'whyGreen'})"/><i>请输入有效期</i></li>	
											
				<li><label>进价</label><input name="purchaseprice" type="text" id="purchaseprice"
					class="form-control" style="width:200px; display:inline" placeholder="请输入进价"/><i></i></li>
				<li ><label>进货日期</label>
					<input name="purchasedate" id="purchasedate" class="form-control" type="text" style="width:200px; display:inline"  onClick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/><i>请输入进货日期</i></li>				
				
				<li><label>供货商</label>
					<select class="form-control" style="width:200px;height:34px"  name="supplierBean.supplierId">
						<option value="">请选择</option>
					<c:forEach items="${supplierList }" var="supplier">
						<option value="${supplier.supplierId }">${supplier.supplier }</option>
					</c:forEach>
					</select></li>
				
				<li><label>厂商</label><input name="drugBean.manufacturer" type="text" id="drugBean.manufacturer"
					class="form-control" style="width:200px; display:inline" placeholder="请输入生产厂商"/><i></i></li>
				<li><label>产品说明</label><input name="drugBean.memo" type="text" id="drugBean.memo"
					class="form-control" style="width:200px; display:inline" placeholder="请输入产品说明"/><i></i></li>
				<li><label>批准文号</label><input name="drugBean.approvalNumber" type="text" id="drugBean.approvalNumber"
					class="form-control" style="width:200px; display:inline" placeholder="请输入批准文号"/><i></i></li>
				<li><label>修改人</label><input name="drugBean.modifier" type="text" id="drugBean.modifier"  value="${sessionScope.username }" readonly="readonly"
					class="form-control" style="width:200px; display:inline" placeholder="请输入修改人"/><i></i></li>
				
				<li><input id="add" type="submit" class="btn btn-info btn-sm" disabled value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" value="返回" /></li>
			</ul>
		</form>
	</div>
</body>