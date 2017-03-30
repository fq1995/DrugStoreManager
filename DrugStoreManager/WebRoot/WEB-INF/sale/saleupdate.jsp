<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html >
<html>
<head>
<meta charset=utf-8 />
<title>药品销售修改</title>
<link href="css/style1.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.5/themes/icon.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
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
/*  		function funB(){
			var g = $('#drugname').combogrid('grid');	// 获取数据表格对象
 		var r = g.datagrid('getSelected');	// 获取选择的行
			if(null != r){
 			var salepeice = r.salepeice;
 			$("#salepeice").val(salepeice);
			}
			setTimeout(funB, 1000);
		} */
		
		
		
		//返回
		$("#return").click(function(){
			location.href = "${pageContext.request.contextPath}/sale_showSale.action?currPage=1";
		});
		
		//药品名称
		/* $("#drugname").blur(function(){
			var g = $('#drugname').combogrid('grid');	// 获取数据表格对象
			var r = g.datagrid('getSelected');	// 获取选择的行
			var drugid = r.drugId;
			alert(drugid);
			$.ajax({
				url:'sale_validateid.action',
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
		}); */
		//单价
		$("#salepeice").focus(function(){
			var g = $('#drugname').combogrid('grid');	// 获取数据表格对象
			var r = g.datagrid('getSelected');	// 获取选择的行
			var salepeice = r.salepeice;
			$("#salepeice").val(salepeice);
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
					if($.trim(tel) == "" || tel.length == 0 ){
		 	        	$("#suppliertel").css("background-color","#FFB9B9"); 
		 	        	$("#add").attr("disabled",true);   
		 	        }else if(data == "无此会员"){
						$("#mess").html("无此会员");
					}else if(data=="会员可用"){
						$("#salepeice").val((saleprice*0.9).toFixed(2));
						$("#mess").html("会员可用");
						$("#suppliertel").css("background-color",""); 
		 	        	$("#add").attr("disabled",false);   
			        }
			     }  
	  		});
		});
		
		
		//销售数量、总金额
		$("#salesVolume").blur(function() {
			var volue = $("#salesVolume").val();
			var saleprice = $("#salepeice").val();
			if(!isNaN(volue)){
				if($.trim(volue) == "" || volue.length == 0){
					$("#salesVolume").css("background-color","#FFB9B9"); 
	 	        $("#add").attr("disabled",true);   
	 				alert("数量不能为空");
	 			}
				else if(volue <= 0){
					$("#salesVolume").css("background-color","#FFB9B9"); 
	 	        $("#add").attr("disabled",true);   
	 				alert("数量必须大于0");
	 			}else{
	 				$("#totalamount").val((volue*saleprice).toFixed(2));
	 				$("#salesVolume").css("background-color",""); 
	 				$("#add").attr("disabled",false);   
	 			}
			}else{
				$("#salesVolume").css("background-color","#FFB9B9"); 
 	        $("#add").attr("disabled",true);   
			    alert("输入的不是数字");
			}
		});
		
	});
	
	$(function() {
		$("#drugname").css("background-color","");
		$("#salepeice").css("background-color","");
		$("#totalamount").css("background-color","");
		$("#modifier").css("background-color","");
		$("#suppliertel").css("background-color",""); 
		$("#salesVolume").css("background-color",""); 
		
		//药品名非空
		$("#drugName").blur(function(){
			var g = $('#drugname').combogrid('grid');	// 获取数据表格对象
			var r = g.datagrid('getSelected');	// 获取选择的行
			var name = r.drugName;
	        if($.trim(name) == "" || name.length == 0 ){
	        	$("#drugName").css("background-color","#FFB9B9"); 
	        	$("#add").attr("disabled",true);   
	        }else{
	        	$("#drugName").css("background-color",""); 
	        	$("#add").attr("disabled",false);   
	        }
		});
		
		//药品单价非空
		$("#salepeice").blur(function(){
			var name = $("input[name='drugBean.salepeice']").val(); 
			var reg = /(^[1-9]\d*(\.\d{1,2})?$)|(^[0]{1}(\.\d{1,2})?$)/;	
	        if($.trim(name) == "" || name.length == 0 ){
	        	$("#salepeice").css("background-color","#FFB9B9"); 
	        	$("#add").attr("disabled",true);   
	        }else if(!reg.test(name)){
	        	$("#salepeice").css("background-color","#FFB9B9"); 
	        	$("#add").attr("disabled",true);   
	        }else{
	        	$("#salepeice").css("background-color",""); 
	        	$("#add").attr("disabled",false);   
	        }
		});
		
		//药品总价非空
		$("#totalamount").blur(function(){
			var name = $("input[name='totalamount']").val(); 
			var reg = /(^[1-9]\d*(\.\d{1,2})?$)|(^[0]{1}(\.\d{1,2})?$)/;	
			
	        if($.trim(name) == "" || name.length == 0 ){
	        	$("#totalamount").css("background-color","#FFB9B9"); 
	        	$("#add").attr("disabled",true);   
	        }else if(!reg.test(name)){
	        	$("#totalamount").css("background-color","#FFB9B9"); 
	        	$("#add").attr("disabled",true);   
	        }else{
	        	$("#totalamount").css("background-color",""); 
	        	$("#add").attr("disabled",false);   
	        }
		});
		
		//修改人
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
		
		
		//提交
		$("#form").submit(function(){
			var g = $('#drugname').combogrid('grid');	// 获取数据表格对象
			var r = g.datagrid('getSelected');	// 获取选择的行
			var name = r.drugName;
			var salepeice = $("input[name='drugBean.salepeice']").val(); 
			var tel = $("#suppliertel").val();
			var totalamount = $("input[name='totalamount']").val(); 
			var modifier = $("#modifier").val();
			var volue = $("#salesVolume").val();
			
			if($.trim(name) == "" || name.length == 0 ){
	        	$("#drugName").css("background-color","#FFB9B9"); 
	        	alert("请选择药品名称");
	        	return false;
	        }else if($.trim(salepeice) == "" || salepeice.length == 0 ||name.length >10){
	        	$("#salepeice").css("background-color","#FFB9B9"); 
	        	alert("请输入药品单价");
	        	return false; 
	        }else if($.trim(volue) == "" || volue.length == 0 ){
 	        $("#salesVolume").css("background-color","#FFB9B9"); 
 	        alert("请输入销售数量");
 	        return false;
	 	}else if($.trim(totalamount) == "" || totalamount.length == 0 ){
	        	$("#totalamount").css("background-color","#FFB9B9"); 
	        	alert("请输入销售总价");
	        	return false; 
	        }else if($.trim(modifier) == "" || modifier.length == 0) {
				$("#modifier").css("background-color","#FFB9B9"); 
				alert("请输入修改人");
				return false; 
			}
	        
	       /*  else{
				$.ajax({
	 				url:'sale_addSale.action',
	 				type:'POST',
	 				data:{'tel':tel},
	 				dataType:'json',
	 				success:function(data){
	 					 
	 			     }  
	 	  		});
			} */
			
			
			
		});
		
	});
	$(function() {
		$.ajax({
			 url: 'sale_comboGrid.action',
			 dataType: 'json',
			success: function(data) {
			}
		});
//		var drugId = $("#input[name='drugBean.drugId']").val();
//		$('#drugname').combogrid('grid').datagrid('selectRecord',drugId);
		$('#drugname').combogrid({
			onLoadSuccess: function(){
			var id = $("#drugId").val();
			$('#drugname').combogrid('grid').datagrid('selectRecord',id);
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
		<form action="sale_updateSale.action" method="post">
			<input type="hidden" name="currPage" value="1"/>
			<input type="hidden" name="time"/>
			<input type="hidden" name="salesId" id="salesId" value="${sale.salesId }"/>
			
			<input type="hidden" name="drugBean.drugCode" id="drugCode" value="${sale.drugBean.drugCode }">
			<input type="hidden" name="userBean.userCode" value="${sale.userBean.userCode }">
			<input type="hidden" name="drugId" id="drugId"  value="${sale.drugBean.drugId }">
			
			<ul class="forminfo">
				<li><label>销售单编号</label><input name="salesCode" type="text" id="salesCode" value="${sale.salesCode }"
					class="form-control" style="width:200px; display:inline" readonly="readonly" placeholder="请输入销售单编号" /><i>必填</i></li>
				<li><label>药品名称</label>
					<%-- <select id="drugname" class="form-control" style="width:200px;height:34px"  name="drugBean.drugId">
						<option value="${sale.drugBean.drugId }">${sale.drugBean.drugName }</option>
					<c:forEach items="${drugList }" var="drug">
						<option value="${drug.drugId }">${drug.drugName }</option>
					</c:forEach>
					</select> --%>
					<select class="easyui-combogrid" style="width:18%" id="drugname" name="drugBean.drugId" data-options="
						panelWidth: 770,
						idField: 'drugId',
						textField: 'drugName',
						url: '${pageContext.request.contextPath}/json/drug.json',
						method: 'post',
						columns: [[
							{field:'drugName',title:'药品名称',width:110,align:'center'},
							{field:'dosageform',title:'药品剂型',width:80,align:'center'},
							{field:'unitname',title:'药品单位',width:80,align:'center'},
							{field:'category',title:'药品类别',width:80,align:'center'},
							{field:'approvalNumber',title:'批准文号',width:200,align:'center'},
							{field:'manufacturer',title:'生产厂商',width:200,align:'center'}
						]],
						fitColumns: true
					">
					</select>
				</li>
				<%-- <li><label>查询会员</label>
					<input name="tel" type="text" id="suppliertel" value="${tel}"
					class="form-control" style="width:200px; display:inline" placeholder="请输入客户电话"/><i></i><i id="mess" style="color: red"></i>
				</li> --%>
				<li><label>查询会员</label>
					<input name="suppliertel" type="text" id="suppliertel" 
					class="form-control" style="width:200px; display:inline" placeholder="请输入客户电话"/><i>必填</i><i id="mess" style="color: red"></i></li>
				<li><label>销售单价</label><input name="drugBean.salepeice" type="text" id="salepeice" value="${sale.salepeice }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售价格"/><i>必填</i></li>
				<li><label>销售数量</label><input name="salesVolume" type="text" id="salesVolume" value="${sale.salesVolume }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售数量"/><i>必填</i></li>
					
				<li><label>销售金额</label><input name="totalamount" type="text" id="totalamount" value="${sale.totalamount }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入销售金额"/><i>必填</i></li>
				
			 	<li><label>操作人</label>
					<select class="form-control" style="width:200px;height:34px"  name="userBean.userId">
						<option value="${sale.userBean.userId }">${sale.userBean.username }</option>
					<c:forEach items="${userList }" var="user">
						<option value="${user.userId }">${user.username }</option>
					</c:forEach>
					</select></li> 
				<li><input id="add" type="submit" class="btn btn-info btn-sm"  value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" value="返回" /></li>
			</ul>
		</form>
	</div>
	
</body>