<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户修改</title>
    <link href="css/style1.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="js/jquery-1.6.2.min.js"></script>
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
  		$("#supplier").css("background-color",""); 
  		$("#name").css("background-color",""); 
  		$("#supplierCode").css("background-color",""); 
  		
  		//供货商编号非空
  		$("#supplierCode").blur(function(){
  			var name = $("input[name='supplierCode']").val(); 
  	        if($.trim(name) == "" || name.length == 0){
  	        	$("#supplierCode").css("background-color","#FFB9B9"); 
  	        	$("#add").attr("disabled",true);   
  	        }else{
  	        	$("#supplierCode").css("background-color",""); 
  	        	$("#add").attr("disabled",false);   
  	        }
  		});
  		
  		//供货商名非空
  		$("#supplier").blur(function(){
  			var name = $("input[name='supplier']").val(); 
  	        if($.trim(name) == "" || name.length == 0 ){
  	        	$("#supplier").css("background-color","#FFB9B9"); 
  	        	$("#add").attr("disabled",true);   
  	        }else{
  	        	$("#supplier").css("background-color",""); 
  	        	$("#add").attr("disabled",false);   
  	        }
  		});
  		
  		//密码
  		$("#name").blur(function(){
  			var pass = $("#name").val();
  			if ($.trim(pass) == "" || pass.length == 0) {
  				$("#name").css("background-color","#FFB9B9"); 
  			}else{
  				$("#name").css("background-color",""); 
  				$("#add").attr("disabled",false);   
  	        }
  		});
  		
  		//电话
 		$("#tel").blur(function(){
 			$("#relti").html("");
 			var tel = $("#tel").val();
 	        if ($.trim(tel) == "" || tel.length == 0 ) {
 	            //alert("手机号不能为空！");
 	            $("#relti").html("手机号不能为空！");
 	            return false;
 	        }
 	        //正则表达式
 	        var reg = /(1[3-9]\d{9}$)/;
 	        if (!reg.test(tel))
 	        {
 	           //alert("请输入正确格式的手机号码！");
 	           $("#relti").html("请输入正确格式的手机号码！");
 	            return false;
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
        <li><img src="images/next.gif" style="margin-top:13px"></li>
        <li><a href="#">表单</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="sup_updateSupplier.action" method="post">
 	<input type="hidden" name="supplierId" value="${supplier.supplierId}">
    
        <ul class="forminfo">
				<li><label>供货商编号</label><input name="supplierCode" type="text" id="supplierCode" value="${supplier.supplierCode }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入供货商编号"/><i>必填</i><i style="color: red">${message2}</i></li>
				<li ><label>供货商名</label><input name="supplier" type="text" id="supplier" value="${supplier.supplier }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入供货商名"/><i>必填</i><i style="color: red">${message}</i></li>
				<li><label>联系人</label><input name="name" type="text" id="name" value="${supplier.name }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入联系人"/><i>必填</i></li>
				<li><label>联系电话</label><input name="tel" type="text" id="tel" value="${supplier.tel }"
					class="form-control" style="width:200px; display:inline" placeholder="请输入联系电话"/>&nbsp;&nbsp;&nbsp;&nbsp;<span id="relti" style="color:red;display:inline-block"></span></li>
				<li><label>是否审核</label><cite>
					<input name="status" type="radio" value="1" checked="checked"/>是&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="status" type="radio" value="0" />否</cite></li>
				<li><input id="add" type="submit" class="btn btn-info btn-sm" disabled value="确认保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="return" type="button" class="btn btn-info btn-sm" onclick="javascript:history.go(-1);" value="返回" />
				</li>
			</ul>
    </form>

</div>

</body>
