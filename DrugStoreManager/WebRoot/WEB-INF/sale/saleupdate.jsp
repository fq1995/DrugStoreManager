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
<title>药品销售修改</title>
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
		<form action="sale_updateSale.action" method="post">
			<input type="hidden" name="currPage" value="1"/>
			<input type="hidden" name="time"/>
			<input type="hidden" name="salesId" id="salesId" value="${sale.salesId }"/>
			
			<input type="hidden" name="drugBean.drugCode" id="drugCode" value="${sale.drugBean.drugCode }">
			<input type="hidden" name="memberBean.memberCode" value="${sale.memberBean.memberCode }">
			<input type="hidden" name="userBean.userCode" value="${sale.userBean.userCode }">
			
			
			<ul class="forminfo">
				<li><label>销售单编号</label><input name="salesCode" type="text" id="salesCode" value="${sale.salesCode }"
					class="form-control" style="width:200px; display:inline" readonly="readonly" placeholder="请输入销售单编号" /><i>必填</i></li>
				<li><label>药品名称</label>
					<select class="form-control" style="width:200px;height:34px"  name="drugBean.drugId">
						<option value="${sale.drugBean.drugId }">${sale.drugBean.drugName }</option>
					<c:forEach items="${drugList }" var="drug">
						<option value="${drug.drugId }">${drug.drugName }</option>
					</c:forEach>
					</select></li>
				<li><label>查询会员</label>
					<select class="form-control" style="width:200px;height:34px"  name="memberBean.memberId">
						<option value="${sale.memberBean.memberId}">${sale.memberBean.memberName }</option>
					<c:forEach items="${memberList }" var="member">
						<option value="${member.memberId }">${member.memberName }</option>
					</c:forEach>
					</select></li>
				<li><label>销售单价</label><input name="salepeice" type="text" id="salepeice" value="${sale.salepeice }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售价格"/><i>必填</i></li>
				<li><label>销售数量</label><input name="salesVolume" type="text" id="salesVolume" value="${sale.salesVolume }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售数量"/><i>必填</i></li>
			 	<li><label>操作人</label>
					<select class="form-control" style="width:200px;height:34px"  name="userBean.userId">
						<option value="${sale.userBean.userId }">${sale.userBean.username }</option>
					<c:forEach items="${userList }" var="user">
						<option value="${user.userId }">${user.username }</option>
					</c:forEach>
					</select></li> 
					
				<li><label>销售金额</label><input name="totalamount" type="text" id="totalamount" value="${sale.totalamount }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售金额"/><i>必填</i></li>
				
				<li><input id="add" type="submit" class="btn btn-info btn-sm"  value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" onclick="javascript:history.go(-1);" value="返回" /></li>
			</ul>
		</form>
	</div>
</body>