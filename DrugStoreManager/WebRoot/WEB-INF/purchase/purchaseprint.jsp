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
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.5/jquery.min.js"></script>
		<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
		<script src="<%=basePath%>js/kucunmanager_operation.js" type="text/javascript" charset="utf-8"></script>

		<style type="text/css">
			th,
			td {
				white-space: nowrap;
			}
		</style>
	</head>

	<body>
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">基本操作</a>
				</li>
				<li><img src="<%=basePath%>images/next.gif"></li>
				<li>
					<a href="#">药品管理</a>
				</li>
			</ul>
		</div>
		
		<form role="form" action="print_printPurchase.action">
			<div class="form-group">
				<h1><span class="label label-info">打印进货报表</span></h1>
				<p style="height:40px"></p>
				<div style="padding-left:30px" class="input-group">
            		<span class="input-group-addon">请选择时间</span>
            		<input name="purchasedate" id="purchasedate" class="form-control" type="text" style="width:200px; display:inline" onfocus="WdatePicker({el:this,skin:'whyGreen',maxDate:'%y-%M',dateFmt:'yyyy年M月',vel:'date'})"/>			
        			<input name="date" id="date" type="hidden" />
        			<button style="margin-left:30px" type="submit" class="btn btn-primary">打印</button>
        		</div>
			</div>
		</form>
		
	</body>
</html>