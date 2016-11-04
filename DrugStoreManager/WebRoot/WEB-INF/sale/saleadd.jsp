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
<title>药品销售添加</title>
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
 		//药品名称
 		$("#drugname").blur(function(){
 			var drugid = $("#drugname").val();
 			$.ajax({
 				url:'form_validateName.action',
 				type:'POST',
 				data:{'drugid':drugid},
 				dataType:'json',
 				success:function(data){ 
 					if(data != 0){
		 				$("#salepeice").val(data);
 					}
 			     },
 				 error:function(data){  
 					alert("请选择药品名称");
 		         }  
 	  		});
 		});
 		
 		//会员电话
 		$("#suppliertel").blur(function(){
 	 		var tel = $("#suppliertel").val();
 	 		var saleprice = $("#salepeice").val();
 	 		$.ajax({
 				url:'sale_validateTel.action',
 				type:'POST',
 				data:{'tel':tel},
 				dataType:'json',
 				success:function(data){ 
 					if(data == "无此会员"){
 						$("#mess").html("无此会员");
 					}
 					else if(data=="会员可用"){
 						$("#salepeice").val((saleprice*0.9).toFixed(2));
 						$("#mess").html("会员可用");
 			        }
 			     }  
 	  		});
 		});
 		
 		
 		//销售总金额
 		$("#salesVolume").blur(function() {
 			var volue = $("#salesVolume").val();
 			var saleprice = $("#salepeice").val();
 			if(!isNaN(volue)){
 				if($.trim(volue) == "" || volue.length == 0){
 	 				alert("数量不能为空");
 	 			}
 				else if(volue <= 0){
 	 				alert("数量必须大于0");
 	 			}else{
 	 				$("#totalamount").val(volue*saleprice);
 	 			}
 				
 			}else{
 			   alert("输入的不是数字");
 			}
 		});
 		
 	});
 	
 	$(function() {
/* 		$("#drugBean.drugName").css("background-color",""); 
 		$("#drugBean.modifier").css("background-color",""); 
 		$("#drugBean.drugCode").css("background-color",""); 
 		
  		//药品编号非空
 		$("#drugBean.drugCode").blur(function(){
 			var name = $("input[name='drugBean.drugCode']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ||name.length >10){
 	        	$("#drugBean.drugCode").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#drugBean.drugCode").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//药品名非空
 		$("#drugBean.drugName").blur(function(){
 			var name = $("input[name='drugBean.drugName']").val(); 
 	        if($.trim(name) == "" || name.length == 0 ){
 	        	$("#drugBean.drugName").css("background-color","#FFB9B9"); 
 	        	$("#add").attr("disabled",true);   
 	        }else{
 	        	$("#drugBean.drugName").css("background-color",""); 
 	        	$("#add").attr("disabled",false);   
 	        }
 		});
 		
 		//修改人
 		$("#drugBean.modifier").blur(function(){
 			var pass = $("#drugBean.modifier").val();
 			if ($.trim(pass) == "" || pass.length == 0) {
 				$("#drugBean.modifier").css("background-color","#FFB9B9"); 
 				$("#add").attr("disabled",true);   
 			}else{
 				$("#drugBean.modifier").css("background-color",""); 
 				$("#add").attr("disabled",false);   
 	        }
 		}); */
 		
 		/* $("select[name='drugBean.drugId']").blur(function(){
 			var drugId = $("select[name='drugBean.drugId']").val(); 
 			$.ajax({
  				url:'sale_selectDrugByName.action',
  				type:'POST',
  				data:{'drugId':drugId},
  				dataType:'json'
  				
  	  		});
 		
 		}); */
 		
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
		<form action="sale_addSale.action" method="post">
			<input type="hidden" name="currPage" value="1">
			<input type="hidden" name="time">

			<ul class="forminfo">
				<li><label>销售单编号</label><input name="salesCode" type="text" id="salesCode" value="${requestScope.saleCode+1 }" readonly="readonly"
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售单编号" /><i>必填</i></li>
				<li><label>药品名称</label>
					<select class="form-control" style="width:200px;height:34px" id="drugname" name="drugBean.drugId">
				 		<option value="">请选择</option>
					<c:forEach items="${drugList }" var="drug">
						<option value="${drug.drugId }">${drug.drugName }</option>
					</c:forEach> 
					</select>
					</li>
					
				
				<li><label>查询会员</label>
					<input name="memberBean.suppliertel" type="text" id="suppliertel" 
					class="form-control" style="width:200px; display:inline" placeholder="请输入客户电话"/><i>必填</i><i id="mess" style="color: red"></i>
				</li>
				
				<li><label>销售单价</label><input name="drugBean.salepeice" type="text" id="salepeice" 
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售价格"/><i>必填</i></li>
				<li><label>销售数量</label><input name="salesVolume" type="text" id="salesVolume"
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售数量"/><i>必填</i></li>
				<li><label>销售金额</label><input name="totalamount" type="text" id="totalamount" 
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售金额"/><i>必填</i></li>
				
			 	<li><label>操作人</label>
					<select class="form-control" style="width:200px;height:34px"  name="userBean.userId">
						<option value="">请选择</option>
					<c:forEach items="${userList }" var="user">
						<option value="${user.userId }">${user.username }</option>
					</c:forEach>
					</select></li> 
					
				<li><input id="add" type="submit" class="btn btn-info btn-sm"  value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" onclick="javascript:history.go(-1);" value="返回" /></li>
			</ul>
		</form>
	</div>
</body>